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
package com.goplace.service.generic.specific.implementation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.goplace.service.generic.implementation.TableServiceGenImpl;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import com.goplace.bean.generic.specific.implementation.AsistenciaBeanGenSpImpl;
import com.goplace.dao.generic.specific.implementation.AsistenciaDaoGenSpImpl;
import com.goplace.helper.ExceptionBooster;

public class AsistenciaServiceGenSpImpl extends TableServiceGenImpl {

    public AsistenciaServiceGenSpImpl(String strObject, String pojo, Connection con) {
        super(strObject, pojo, con);
    }
    
    public String seguirEvento(int id_usuario, int id_publicacion) throws Exception {
        String resultado = null;
        try {
            oConnection.setAutoCommit(false);
         
            AsistenciaDaoGenSpImpl oAsistenciaDAO = new AsistenciaDaoGenSpImpl(strObjectName, oConnection); 
            AsistenciaBeanGenSpImpl oAsistencia = new AsistenciaBeanGenSpImpl();
            oAsistencia.setId_usuario(id_usuario);
            oAsistencia.setId_publicacion(id_publicacion);
            
            Boolean amigo = oAsistenciaDAO.existeEvento(oAsistencia);
            
            if (!amigo) {
                Gson gson = new GsonBuilder().create();       
                oAsistencia = oAsistenciaDAO.set(oAsistencia);
                Map<String, String> data = new HashMap<>();
                data.put("status", "200");
                data.put("message", Integer.toString(oAsistencia.getId()));
                resultado = gson.toJson(data);
            } else {
                Gson gson = new GsonBuilder().create();     
                Map<String, String> data = new HashMap<>();
                data.put("status", "500");
                data.put("message", "Error, ya sigues este evento.");
                resultado = gson.toJson(data);
            }
            
            oConnection.commit();
        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return resultado;
    }
    
    public String dejarEvento(int id_usuario, int id_publicacion) throws Exception {
        String resultado = null;
        try {
            oConnection.setAutoCommit(false);
            AsistenciaDaoGenSpImpl oAsistenciaDAO = new AsistenciaDaoGenSpImpl(strObjectName, oConnection);
            AsistenciaBeanGenSpImpl oAsistencia = new AsistenciaBeanGenSpImpl();
            oAsistencia.setId_usuario(id_usuario);
            oAsistencia.setId_publicacion(id_publicacion);
            Gson gson = new GsonBuilder().create();       
            
            oAsistencia = oAsistenciaDAO.dejarEvento(oAsistencia);
            Map<String, String> data = new HashMap<>();
            data.put("status", "200");
            data.put("message", Integer.toString(oAsistencia.getId()));
            resultado = gson.toJson(data);
            oConnection.commit();
        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return resultado;
    }

    public String existeAsistencia(int id_usuario, int id_publicacion) throws Exception {
        String resultado = null;
        try {
            oConnection.setAutoCommit(false);
            AsistenciaDaoGenSpImpl oAsistenciaDAO = new AsistenciaDaoGenSpImpl(strObjectName, oConnection);
            AsistenciaBeanGenSpImpl oAsistencia = new AsistenciaBeanGenSpImpl();
            oAsistencia.setId_usuario(id_usuario);
            oAsistencia.setId_publicacion(id_publicacion);
            
            Boolean evento = oAsistenciaDAO.existeEvento(oAsistencia);
            resultado = "{\"data\":" + evento + "}";
            oConnection.commit();

        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":existeAsistencia ERROR: " + ex.getMessage()));
        }
        return resultado;
    }
}
