/*
 * Copyright (C) July 2014 Rafael Aznar
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.goplace.dao.generic.specific.implementation;


import com.goplace.bean.generic.specific.implementation.CiudadBeanGenSpImpl;
import java.sql.Connection;
import com.goplace.bean.generic.specific.implementation.PublicacionBeanGenSpImpl;
import com.goplace.bean.generic.specific.implementation.TipopublicacionBeanGenSpImpl;
import com.goplace.bean.generic.specific.implementation.UsuarioBeanGenSpImpl;
import com.goplace.dao.generic.implementation.TableDaoGenImpl;
import com.goplace.helper.AppConfigurationHelper;
import com.goplace.helper.ExceptionBooster;
import com.goplace.helper.FilterBeanHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class PublicacionDaoGenSpImpl extends TableDaoGenImpl<PublicacionBeanGenSpImpl> {

    private String strTableName = null;
    private String tabla = null;
    private Connection oConnection = null;
    
    public PublicacionDaoGenSpImpl(String strFuente, Connection pooledConnection) throws Exception {
        super(strFuente, "Publicacion", pooledConnection);
        tabla = "publicacion";
        strTableName = "publicacion";
        oConnection = pooledConnection;
    }
    
    @Override
    public PublicacionBeanGenSpImpl get(PublicacionBeanGenSpImpl oPublicacionBean, Integer expand) throws Exception {
        if (oPublicacionBean.getId() > 0) {
            try {
                if (!oMysql.existsOne(tabla, oPublicacionBean.getId())) {
                    oPublicacionBean.setId(0);
                } else {
                    expand--;
                    if (expand > 0) {
                        // SET - titulo y descripcion
                        oPublicacionBean.setTitulo(oMysql.getOne(tabla, "titulo", oPublicacionBean.getId()));
                        oPublicacionBean.setDescripcion(oMysql.getOne(tabla, "descripcion", oPublicacionBean.getId()));
                        
                        // SET - fechapub
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String dateInString = oMysql.getOne(tabla, "fechapub", oPublicacionBean.getId());         
                        oPublicacionBean.setFechapub(formatter.parse(dateInString));   
                        
                        // SET - id_usuario
                        oPublicacionBean.setId_usuario(Integer.parseInt(oMysql.getOne(tabla, "id_usuario", oPublicacionBean.getId())));
                        
                        // SET - obj_usuario
                        UsuarioBeanGenSpImpl oUsuario = new UsuarioBeanGenSpImpl();
                        oUsuario.setId(Integer.parseInt(oMysql.getOne(tabla, "id_usuario", oPublicacionBean.getId())));
                        UsuarioDaoGenSpImpl oUsuarioDAO = new UsuarioDaoGenSpImpl("usuario", oConnection);
                        oUsuario = oUsuarioDAO.get(oUsuario, AppConfigurationHelper.getJsonDepth());
                        oUsuario.setPassword(null);
                        oPublicacionBean.setObj_usuario(oUsuario);
                        
                        // SET - id_ciudad
                        oPublicacionBean.setId_ciudad(Integer.parseInt(oMysql.getOne(tabla, "id_ciudad", oPublicacionBean.getId())));

                        // SET - obj_ciudad
                        CiudadBeanGenSpImpl oCiudad = new CiudadBeanGenSpImpl();
                        oCiudad.setId(Integer.parseInt(oMysql.getOne(tabla, "id_ciudad", oPublicacionBean.getId())));
                        CiudadDaoGenSpImpl oCiudadDAO = new CiudadDaoGenSpImpl("ciudad", oConnection);
                        oCiudad = oCiudadDAO.get(oCiudad, AppConfigurationHelper.getJsonDepth());
                        oPublicacionBean.setObj_ciudad(oCiudad);
                        
                        // SET - id_tipopublicacion
                        oPublicacionBean.setId_tipopublicacion(Integer.parseInt(oMysql.getOne(tabla, "id_tipopublicacion", oPublicacionBean.getId())));

                        // SET - obj_tipopublicacion
                        TipopublicacionBeanGenSpImpl oTp = new TipopublicacionBeanGenSpImpl();
                        oTp.setId(Integer.parseInt(oMysql.getOne(tabla, "id_tipopublicacion", oPublicacionBean.getId())));
                        TipopublicacionDaoGenSpImpl oTpDAO = new TipopublicacionDaoGenSpImpl("tipopublicacion", oConnection);
                        oTp = oTpDAO.get(oTp, AppConfigurationHelper.getJsonDepth());
                        oPublicacionBean.setObj_tipopublicacion(oTp);
                    }
                }
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
            }
        } else {
            oPublicacionBean.setId(0);
        }
        return oPublicacionBean;
    }

    @Override
    public PublicacionBeanGenSpImpl set(PublicacionBeanGenSpImpl oPublicacionBean) throws Exception {
        try {
            Boolean isNew = false;
            
            if (oPublicacionBean.getId() == 0) {
                oPublicacionBean.setId(oMysql.insertOne(strTableName));
                isNew = true;
            }
            
            oMysql.updateOne(oPublicacionBean.getId(), strTableName, "titulo", oPublicacionBean.getTitulo());
            oMysql.updateOne(oPublicacionBean.getId(), strTableName, "descripcion", oPublicacionBean.getDescripcion());
            oMysql.updateOne(oPublicacionBean.getId(), strTableName, "id_usuario", oPublicacionBean.getId_usuario().toString());
            oMysql.updateOne(oPublicacionBean.getId(), strTableName, "id_ciudad", oPublicacionBean.getId_ciudad().toString());
            oMysql.updateOne(oPublicacionBean.getId(), strTableName, "id_tipopublicacion", oPublicacionBean.getId_tipopublicacion().toString());
            
            if (isNew == false) {
                oMysql.updateOne(oPublicacionBean.getId(), strTableName, "fechapub", oMysql.getOne(strTableName, "fechapub", oPublicacionBean.getId()));
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      
                Date newDate = new Date();       
                String date = formatter.format(newDate);
                oMysql.updateOne(oPublicacionBean.getId(), strTableName, "fechapub", date);
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return oPublicacionBean;
    }
    
    public int getPagesEventos(int id_usuario, int intRegsPerPag, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        int pages = 0;
        try {
            pages = oMysql.getPagesEventos(id_usuario, intRegsPerPag, hmFilter);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        }
        return pages;
    }
    
    public ArrayList<PublicacionBeanGenSpImpl> getPageEventos(int id_usuario, int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<PublicacionBeanGenSpImpl> arrPublicacion = new ArrayList<>();
        try {
            arrId = oMysql.getPageEventos(id_usuario, intRegsPerPag, intPage, hmFilter);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                PublicacionBeanGenSpImpl oPublicacionBean = new PublicacionBeanGenSpImpl(iterador.next());
                arrPublicacion.add(this.get(oPublicacionBean, AppConfigurationHelper.getJsonDepth()));
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrPublicacion;
    }
    
    /*public AmistadBeanGenSpImpl agregarAmigo(AmistadBeanGenSpImpl oAmigoBean) throws Exception {
        try {

            oMysql.agregarAmigo(oAmigoBean.getId_usuario_1(), oAmigoBean.getId_usuario_2());
        
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return oAmigoBean;
    }*/
}
