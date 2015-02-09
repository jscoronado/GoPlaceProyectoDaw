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


import java.sql.Connection;
import com.goplace.bean.generic.specific.implementation.UsuarioBeanGenSpImpl;
import com.goplace.dao.generic.implementation.TableDaoGenImpl;
import com.goplace.helper.AppConfigurationHelper;

public class UsuarioDaoGenSpImpl extends TableDaoGenImpl<UsuarioBeanGenSpImpl> {

    public UsuarioDaoGenSpImpl(String strFuente, Connection pooledConnection) throws Exception {
        super(strFuente, "Usuario", pooledConnection);
    }

    public UsuarioBeanGenSpImpl getPass(String registro, UsuarioBeanGenSpImpl oUsuario) throws Exception {
        try {

            String strRegister = oMysql.getRegister(registro, "usuario", "login", oUsuario.getLogin());
            if (strRegister == null) {
                oUsuario.setId(0);
            } else {
                oUsuario.setPassword(strRegister);
                String login = oUsuario.getLogin();
                
                oUsuario = this.get(oUsuario, AppConfigurationHelper.getJsonDepth());
            }

            return oUsuario;
        } catch (Exception e) {
            throw new Exception("UsuarioDao.getPass: Error: " + e.getMessage());
        }
    }
}
