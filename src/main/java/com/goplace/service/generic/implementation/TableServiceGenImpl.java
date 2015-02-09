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
package com.goplace.service.generic.implementation;

import com.goplace.service.publicinterface.ViewServiceInterface;
import com.goplace.service.publicinterface.TableServiceInterface;
import com.goplace.service.publicinterface.MetaServiceInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import com.goplace.bean.generic.implementation.BeanGenImpl;
import com.goplace.bean.publicinterface.BeanInterface;
import com.goplace.dao.generic.implementation.TableDaoGenImpl;
import com.goplace.helper.ExceptionBooster;

public abstract class TableServiceGenImpl extends ViewServiceGenImpl implements TableServiceInterface, ViewServiceInterface, MetaServiceInterface {

    public TableServiceGenImpl(String ob, String pojo, Connection con) {
        super(ob, pojo, con);
    }

    @Override
    public String remove(Integer id) throws Exception {
        String resultado = null;
        try {
            oConnection.setAutoCommit(false);
            BeanGenImpl oGenericBean = (BeanGenImpl) Class.forName("com.goplace.bean.generic.specific.implementation." + strObjectName + "BeanGenSpImpl").newInstance();
            Constructor c = Class.forName("com.goplace.dao.generic.specific.implementation." + strObjectName + "DaoGenSpImpl").getConstructor(String.class, Connection.class);
            TableDaoGenImpl oGenericDao = (TableDaoGenImpl) c.newInstance(strObjectName, oConnection);
            oGenericBean.setId(id);
            Map<String, String> data = new HashMap<>();
            if (oGenericBean != null) {
                oGenericDao.remove(oGenericBean);
                data.put("status", "200");
                data.put("message", "se ha eliminado el registro con id=" + oGenericBean.getId());
            } else {
                data.put("status", "error");
                data.put("message", "error");
            }
            Gson gson = new Gson();
            resultado = gson.toJson(data);
        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":remove ERROR: " + ex.getMessage()));
        } finally {
            oConnection.commit();
        }
        return resultado;
    }

    @Override
    public String set(String jason) throws Exception {
        String resultado = null;
        try {
            oConnection.setAutoCommit(false);
            BeanGenImpl oGenericBean = (BeanGenImpl) Class.forName("com.goplace.bean.generic.specific.implementation." + strObjectName + "BeanGenSpImpl").newInstance();
            Constructor c = Class.forName("com.goplace.dao.generic.specific.implementation." + strObjectName + "DaoGenSpImpl").getConstructor(String.class, Connection.class);
            TableDaoGenImpl oGenericDao = (TableDaoGenImpl) c.newInstance(strObjectName, oConnection);
            Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").excludeFieldsWithoutExposeAnnotation().create();
            oGenericBean = gson.fromJson(jason, oGenericBean.getClass());
            Map<String, String> data = new HashMap<>();
            if (oGenericBean != null) {
                oGenericBean = (BeanGenImpl) (BeanInterface) oGenericDao.set(oGenericBean);
                data.put("status", "200");
                data.put("message", Integer.toString(oGenericBean.getId()));
            } else {
                data.put("status", "error");
                data.put("message", "error");
            }
            resultado = gson.toJson(data);
        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        } finally {
            oConnection.commit();
        }
        return resultado;
    }

    @Override
    public String updateOne(int intId, String strTabla, String strCampo, String strValor) throws Exception {
        String data = null;
        try {
            oConnection.setAutoCommit(false);
            Constructor c = Class.forName("com.goplace.dao.generic.specific.implementation." + strObjectName + "DaoGenSpImpl").getConstructor(String.class, Connection.class);
            TableDaoGenImpl oGenericDao = (TableDaoGenImpl) c.newInstance(strObjectName, oConnection);
            int update = oGenericDao.updateOne(intId, strTabla, strCampo, strValor);
            data = "{\"data\":\"" + Integer.toString(update) + "\"}";
        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getPages ERROR: " + ex.getMessage()));
        } finally {
            oConnection.commit();
        }
        return data;
    }

}