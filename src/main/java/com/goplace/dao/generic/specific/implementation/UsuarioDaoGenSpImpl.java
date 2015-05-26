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
import com.goplace.bean.generic.specific.implementation.TipousuarioBeanGenSpImpl;
import java.sql.Connection;
import com.goplace.bean.generic.specific.implementation.UsuarioBeanGenSpImpl;
import com.goplace.dao.generic.implementation.TableDaoGenImpl;
import com.goplace.data.specific.implementation.MysqlDataSpImpl;
import com.goplace.helper.AppConfigurationHelper;
import com.goplace.helper.ExceptionBooster;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UsuarioDaoGenSpImpl extends TableDaoGenImpl<UsuarioBeanGenSpImpl> {

    /*public UsuarioDaoGenSpImpl(String strFuente, Connection pooledConnection) throws Exception {
        super(strFuente, "Usuario", pooledConnection);
    }*/
    
    private String strTableName = "usuario"; 
    private MysqlDataSpImpl oMysql = null;
    private Connection oConnection = null;
    
    public UsuarioDaoGenSpImpl(String strObject, Connection pooledConnection) throws Exception {
        super(strObject, strObject, pooledConnection);
            oConnection = pooledConnection;
            oMysql = new MysqlDataSpImpl(oConnection);
    }

    public UsuarioBeanGenSpImpl getPass(String registro, UsuarioBeanGenSpImpl oUsuario) throws Exception {
        try {
            String strId = oMysql.getId("usuario", "login", oUsuario.getLogin());
            if (strId == null) {
                oUsuario.setId(0);
            } else {
                Integer intId = Integer.parseInt(strId);
                oUsuario.setId(intId);
                String pass = oMysql.getRegister(registro, "usuario", "login", oUsuario.getLogin());
                oUsuario.setPassword(pass);
                if (!pass.equals(oUsuario.getPassword())) {
                    oUsuario.setId(0);
                }
                oUsuario = this.get(oUsuario, AppConfigurationHelper.getJsonDepth());
            }

            return oUsuario;
        } catch (Exception e) {
            throw new Exception("UsuarioDao.getPass: Error: " + e.getMessage());
        }
    }
    
    @Override
    public UsuarioBeanGenSpImpl get(UsuarioBeanGenSpImpl oUsuarioBean, Integer expand) throws Exception {
        if (oUsuarioBean.getId() > 0) {
            try {
                if (!oMysql.existsOne(strTableName, oUsuarioBean.getId())) {
                    oUsuarioBean.setId(0);
                } else {
                    expand--;
                    if (expand > 0) {
                        
                        oUsuarioBean.setNombre(oMysql.getOne(strTableName, "nombre", oUsuarioBean.getId()));
                        oUsuarioBean.setApellidos(oMysql.getOne(strTableName, "apellidos", oUsuarioBean.getId()));
                        oUsuarioBean.setCorreo(oMysql.getOne(strTableName, "correo", oUsuarioBean.getId()));
                        oUsuarioBean.setLogin(oMysql.getOne(strTableName, "login", oUsuarioBean.getId()));
                        oUsuarioBean.setPassword(oMysql.getOne(strTableName, "password", oUsuarioBean.getId()));
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateInString = oMysql.getOne(strTableName, "fecha", oUsuarioBean.getId());         
                        oUsuarioBean.setFecha(formatter.parse(dateInString)); 
                        
                        oUsuarioBean.setId_ciudad(Integer.parseInt(oMysql.getOne(strTableName, "id_ciudad", oUsuarioBean.getId())));
                        oUsuarioBean.setGenero(oMysql.getOne(strTableName, "genero", oUsuarioBean.getId()));
                        oUsuarioBean.setEstado(oMysql.getOne(strTableName, "estado", oUsuarioBean.getId()));
                        oUsuarioBean.setId_tipousuario(Integer.parseInt(oMysql.getOne(strTableName, "id_tipousuario", oUsuarioBean.getId())));
                        
                        oUsuarioBean.setImagen(oMysql.getOne(strTableName, "imagen", oUsuarioBean.getId()));
                        
                        /* Claves Ajenas */
                        CiudadBeanGenSpImpl oCiudad = new CiudadBeanGenSpImpl();
                        oCiudad.setId(Integer.parseInt(oMysql.getOne(strTableName, "id_ciudad", oUsuarioBean.getId())));
                        CiudadDaoGenSpImpl oCiudadDAO = new CiudadDaoGenSpImpl("ciudad", oConnection);
                        oCiudad = oCiudadDAO.get(oCiudad, AppConfigurationHelper.getJsonDepth());
                        oUsuarioBean.setObj_ciudad(oCiudad);
                        
                        TipousuarioBeanGenSpImpl oTipousuario = new TipousuarioBeanGenSpImpl();
                        oTipousuario.setId(Integer.parseInt(oMysql.getOne(strTableName, "id_tipousuario", oUsuarioBean.getId())));
                        TipousuarioDaoGenSpImpl oTipousuarioDAO = new TipousuarioDaoGenSpImpl("tipousuario", oConnection);
                        oTipousuario = oTipousuarioDAO.get(oTipousuario, AppConfigurationHelper.getJsonDepth()); 
                        oUsuarioBean.setObj_tipousuario(oTipousuario);
                        
                    }
                }
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
            }
        } else {
            oUsuarioBean.setId(0);
        }
        return oUsuarioBean;
    }
    
    @Override
    public UsuarioBeanGenSpImpl set(UsuarioBeanGenSpImpl oUsuarioBean) throws Exception {
        try {
            Boolean isNew = false;
            
            if (oUsuarioBean.getId() == 0) {
                oUsuarioBean.setId(oMysql.insertOne(strTableName));
                isNew = true;
            }
            
            oMysql.updateOne(oUsuarioBean.getId(), strTableName, "nombre", oUsuarioBean.getNombre());
            oMysql.updateOne(oUsuarioBean.getId(), strTableName, "apellidos", oUsuarioBean.getApellidos());
            
            oMysql.updateOne(oUsuarioBean.getId(), strTableName, "correo", oUsuarioBean.getCorreo());
            oMysql.updateOne(oUsuarioBean.getId(), strTableName, "login", oUsuarioBean.getLogin());
            
            oMysql.updateOne(oUsuarioBean.getId(), strTableName, "id_ciudad", oUsuarioBean.getId_ciudad().toString());
            oMysql.updateOne(oUsuarioBean.getId(), strTableName, "id_tipousuario", oUsuarioBean.getId_tipousuario().toString());
            
            oMysql.updateOne(oUsuarioBean.getId(), strTableName, "genero", oUsuarioBean.getGenero());
            oMysql.updateOne(oUsuarioBean.getId(), strTableName, "estado", oUsuarioBean.getEstado());
            
            if (isNew == false) {
                if (oUsuarioBean.getPassword() == null || oUsuarioBean.getPassword().equals("")) {
                    oMysql.updateOne(oUsuarioBean.getId(), strTableName, "password", oMysql.getOne(strTableName, "password", oUsuarioBean.getId())); 
                } else {
                    oMysql.updateOne(oUsuarioBean.getId(), strTableName, "password", oUsuarioBean.getPassword());
                }
                oMysql.updateOne(oUsuarioBean.getId(), strTableName, "fecha", oMysql.getOne(strTableName, "fecha", oUsuarioBean.getId()));
            } else {
                
                oMysql.updateOne(oUsuarioBean.getId(), strTableName, "password", oUsuarioBean.getPassword());
                
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      
                Date newDate = new Date();       
                String date = formatter.format(newDate);
                oMysql.updateOne(oUsuarioBean.getId(), strTableName, "fecha", date);
            }
            
            /* Set Image */
            oMysql.updateOne(oUsuarioBean.getId(), strTableName, "imagen", oUsuarioBean.getImagen());
            
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return oUsuarioBean;
    }

}
