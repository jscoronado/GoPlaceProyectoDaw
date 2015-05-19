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
import com.goplace.bean.generic.specific.implementation.ComentarioBeanGenSpImpl;
import com.goplace.dao.generic.specific.implementation.ComentarioDaoGenSpImpl;
import com.goplace.helper.AppConfigurationHelper;
import com.goplace.helper.EncodingUtilHelper;
import com.goplace.helper.ExceptionBooster;
import com.goplace.helper.FilterBeanHelper;
import java.sql.Connection;
import com.goplace.service.generic.implementation.TableServiceGenImpl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class ComentarioServiceGenSpImpl extends TableServiceGenImpl {

    public ComentarioServiceGenSpImpl(String strObject, String pojo, Connection con) {
        super(strObject, pojo, con);
    }

    public String set(String jason, int id_usuario, int id_tipousuario) throws Exception {
        String resultado = null;
        try {
            oConnection.setAutoCommit(false);
            ComentarioDaoGenSpImpl oComentarioDAO = new ComentarioDaoGenSpImpl(strObjectName, oConnection);
            ComentarioBeanGenSpImpl oComentario = new ComentarioBeanGenSpImpl();
            Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm:ss").create();
            jason = EncodingUtilHelper.decodeURIComponent(jason);
            oComentario = gson.fromJson(jason, oComentario.getClass());
            if (oComentario.getId_usuario() == id_usuario || id_tipousuario == 1) {
                oComentario = oComentarioDAO.set(oComentario);
                Map<String, String> data = new HashMap<>();
                data.put("status", "200");
                data.put("message", Integer.toString(oComentario.getId()));
                resultado = gson.toJson(data);
            } else {
                Map<String, String> data = new HashMap<>();
                data.put("status", "500");
                data.put("message", "Error, el usuario no coincide con el que hay en sesión.");
                resultado = gson.toJson(data);
            }
            oConnection.commit();
        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return resultado;
    }

    @Override
    public String get(Integer id) throws Exception {
        String data = null;
        try {
            oConnection.setAutoCommit(false);
            ComentarioDaoGenSpImpl oComentarioDAO = new ComentarioDaoGenSpImpl(strObjectName, oConnection);
            ComentarioBeanGenSpImpl oComentario = new ComentarioBeanGenSpImpl(id);
            oComentario = oComentarioDAO.get(oComentario, AppConfigurationHelper.getJsonDepth());
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("dd/MM/yyyy HH:mm:ss");
            Gson gson = gsonBuilder.create();
            data = gson.toJson(oComentario);
            oConnection.commit();
        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage()));
        }
        return data;
    }
    
        public String getPageComentarios(int id_publicacion, int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder) throws Exception {
        String data = null;
        try {
            oConnection.setAutoCommit(false);
            ComentarioDaoGenSpImpl oComentarioDAO = new ComentarioDaoGenSpImpl(strObjectName, oConnection);
            List<ComentarioBeanGenSpImpl> oComentarios = oComentarioDAO.getPageComentarios(id_publicacion, intRegsPerPag, intPage, alFilter, hmOrder);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("dd/MM/yyyy HH:mm:ss");
            Gson gson = gsonBuilder.create();
            data = gson.toJson(oComentarios);
            data = "{\"list\":" + data + "}";
            oConnection.commit();
        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPage ERROR: " + ex.getMessage()));
        }
        return data;
    }
    
    public String getPagesComentarios(int id_publicacion, int intRegsPerPag, ArrayList<FilterBeanHelper> alFilter) throws Exception {
        String data = null;
        try {
            oConnection.setAutoCommit(false);
            ComentarioDaoGenSpImpl oComentarioDAO = new ComentarioDaoGenSpImpl(strObjectName, oConnection);
            int pages = oComentarioDAO.getPagesComentarios(id_publicacion, intRegsPerPag, alFilter);
            data = "{\"data\":\"" + Integer.toString(pages) + "\"}";
            oConnection.commit();
        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        }
        return data;
    }
    
    public String getComentarios(int id_usuario, int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder) throws Exception {
        String data = null;
        try {
            oConnection.setAutoCommit(false);
            String columns = this.getColumns();
            String prettyColumns = this.getPrettyColumns();
            //String types = this.getTypes();
            String page = this.getPageComentarios(id_usuario, intRegsPerPag, intPage, alFilter, hmOrder);
            String pages = this.getPagesComentarios(id_usuario, intRegsPerPag, alFilter);
            String registers = this.getCount(alFilter);
            data = "{\"data\":{"
                    + "\"columns\":" + columns
                    + ",\"prettyColumns\":" + prettyColumns
                    // + ",\"types\":" + types
                    + ",\"page\":" + page
                    + ",\"pages\":" + pages
                    + ",\"registers\":" + registers
                    + "}}";
            oConnection.commit();

        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getAggregateViewSome ERROR: " + ex.getMessage()));
        }
        return data;
    }
}
