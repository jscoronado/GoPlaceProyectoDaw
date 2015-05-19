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
import com.goplace.bean.generic.specific.implementation.ComentarioBeanGenSpImpl;
import com.goplace.bean.generic.specific.implementation.PublicacionBeanGenSpImpl;
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

public class ComentarioDaoGenSpImpl extends TableDaoGenImpl<ComentarioBeanGenSpImpl> {

    private String strTableName = null;
    private String tabla = null;
    private Connection oConnection = null;
    
    public ComentarioDaoGenSpImpl(String strFuente, Connection pooledConnection) throws Exception {
        super(strFuente, "Comentario", pooledConnection);
        tabla = "comentario";
        strTableName = "comentario";
        oConnection = pooledConnection;
    }
    
    @Override
    public ComentarioBeanGenSpImpl get(ComentarioBeanGenSpImpl oComentarioBean, Integer expand) throws Exception {
        if (oComentarioBean.getId() > 0) {
            try {
                if (!oMysql.existsOne(tabla, oComentarioBean.getId())) {
                    oComentarioBean.setId(0);
                } else {
                    expand--;
                    if (expand > 0) {
                        // SET - comentarios
                        oComentarioBean.setComent(oMysql.getOne(tabla, "coment", oComentarioBean.getId()));
                        
                        // SET - fechacomentario
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String dateInString = oMysql.getOne(tabla, "fechacomentario", oComentarioBean.getId());         
                        oComentarioBean.setFechacomentario(formatter.parse(dateInString));   
                        
                        // SET - id_usuario
                        oComentarioBean.setId_usuario(Integer.parseInt(oMysql.getOne(tabla, "id_usuario", oComentarioBean.getId())));
                        
                        // SET - obj_usuario
                        UsuarioBeanGenSpImpl oUsuario = new UsuarioBeanGenSpImpl();
                        oUsuario.setId(Integer.parseInt(oMysql.getOne(tabla, "id_usuario", oComentarioBean.getId())));
                        UsuarioDaoGenSpImpl oUsuarioDAO = new UsuarioDaoGenSpImpl("usuario", oConnection);
                        oUsuario = oUsuarioDAO.get(oUsuario, AppConfigurationHelper.getJsonDepth());
                        oUsuario.setPassword(null);
                        oComentarioBean.setObj_usuario(oUsuario);
                        
                        // SET - id_publicacion
                        oComentarioBean.setId_publicacion(Integer.parseInt(oMysql.getOne(tabla, "id_publicacion", oComentarioBean.getId())));

                        // SET - obj_publicacion
                        PublicacionBeanGenSpImpl oPublicacion = new PublicacionBeanGenSpImpl();
                        oPublicacion.setId(Integer.parseInt(oMysql.getOne(tabla, "id_publicacion", oComentarioBean.getId())));
                        PublicacionDaoGenSpImpl oPublicacionDAO = new PublicacionDaoGenSpImpl("publicacion", oConnection);
                        oPublicacion = oPublicacionDAO.get(oPublicacion, AppConfigurationHelper.getJsonDepth());
                        oComentarioBean.setObj_publicacion(oPublicacion);
                        
                    }
                }
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
            }
        } else {
            oComentarioBean.setId(0);
        }
        return oComentarioBean;
    }

    @Override
    public ComentarioBeanGenSpImpl set(ComentarioBeanGenSpImpl oComentarioBean) throws Exception {
        try {
            Boolean isNew = false;
            
            if (oComentarioBean.getId() == 0) {
                oComentarioBean.setId(oMysql.insertOne(strTableName));
                isNew = true;
            }
            
            oMysql.updateOne(oComentarioBean.getId(), strTableName, "coment", oComentarioBean.getComent());
            oMysql.updateOne(oComentarioBean.getId(), strTableName, "id_usuario", oComentarioBean.getId_usuario().toString());
            oMysql.updateOne(oComentarioBean.getId(), strTableName, "id_publicacion", oComentarioBean.getId_publicacion().toString());
            
            if (isNew == false) {
                oMysql.updateOne(oComentarioBean.getId(), strTableName, "fechacomentario", oMysql.getOne(strTableName, "fechacomentario", oComentarioBean.getId()));
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      
                Date newDate = new Date();       
                String date = formatter.format(newDate);
                oMysql.updateOne(oComentarioBean.getId(), strTableName, "fechacomentario", date);
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return oComentarioBean;
    }
    
    public int getPagesComentarios(int id_publicacion, int intRegsPerPag, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        int pages = 0;
        try {
            pages = oMysql.getPagesComentarios(id_publicacion, intRegsPerPag, hmFilter);
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        }
        return pages;
    }
    
    public ArrayList<ComentarioBeanGenSpImpl> getPageComentarios(int id_publicacion, int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<ComentarioBeanGenSpImpl> arrComentario = new ArrayList<>();
        try {
            arrId = oMysql.getPageComentarios(id_publicacion, intRegsPerPag, intPage, hmFilter);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                ComentarioBeanGenSpImpl oComentarioBean = new ComentarioBeanGenSpImpl(iterador.next());
                arrComentario.add(this.get(oComentarioBean, AppConfigurationHelper.getJsonDepth()));
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return arrComentario;
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
