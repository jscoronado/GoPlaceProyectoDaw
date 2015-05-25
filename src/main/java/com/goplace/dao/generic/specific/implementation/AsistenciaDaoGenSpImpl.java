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

import com.goplace.dao.generic.implementation.TableDaoGenImpl;
import java.sql.Connection;
import com.goplace.bean.generic.specific.implementation.AsistenciaBeanGenSpImpl;
import com.goplace.dao.publicinterface.MetaDaoInterface;
import com.goplace.dao.publicinterface.TableDaoInterface;
import com.goplace.dao.publicinterface.ViewDaoInterface;
import com.goplace.helper.ExceptionBooster;

public class AsistenciaDaoGenSpImpl extends TableDaoGenImpl<AsistenciaBeanGenSpImpl> {

    public AsistenciaDaoGenSpImpl(String strFuente, Connection pooledConnection) throws Exception {
        super(strFuente, "Asistencia", pooledConnection);
    }

    public AsistenciaBeanGenSpImpl seguirEvento(AsistenciaBeanGenSpImpl oAsistenciaBean) throws Exception {
        try {
            oMysql.agregarDato("asistencia", oAsistenciaBean.getId_usuario(), oAsistenciaBean.getId_publicacion());

        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":agregarAsistencia ERROR: " + ex.getMessage()));
        }
        return oAsistenciaBean;
    }

    public AsistenciaBeanGenSpImpl dejarEvento(AsistenciaBeanGenSpImpl oAsistenciaBean) throws Exception {
        try {
            oMysql.removeDato("asistencia", oAsistenciaBean.getId_usuario(), oAsistenciaBean.getId_publicacion());

        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":removeAsistencia ERROR: " + ex.getMessage()));
        }
        return oAsistenciaBean;
    }
    
    public Boolean existeEvento(AsistenciaBeanGenSpImpl oAsistenciaBean) throws Exception {
        int oAsistencia = 0;
        Boolean amigo = false;
        try {
            oAsistencia = oMysql.existeDato("asistencia", oAsistenciaBean.getId_usuario(), oAsistenciaBean.getId_publicacion());
            if (oAsistencia > 0) {
                amigo = true;
            } else {
                amigo = false;
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":existeAsistencia ERROR: " + ex.getMessage()));
        }
        return amigo;
    }
}
