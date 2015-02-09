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

import com.goplace.bean.generic.specific.implementation.UsuarioBeanGenSpImpl;
import com.goplace.dao.generic.specific.implementation.UsuarioDaoGenSpImpl;
import com.goplace.helper.ExceptionBooster;
import java.sql.Connection;
import com.goplace.service.generic.implementation.TableServiceGenImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

public class UsuarioServiceGenSpImpl extends TableServiceGenImpl {

    public UsuarioServiceGenSpImpl(String strObject, String pojo, Connection con) {
        super(strObject, pojo, con);
    }

    public UsuarioDaoGenSpImpl registro(HttpServletRequest request) throws Exception {

        String nombre = request.getParameter("nombreregdef");
        String apellidos = request.getParameter("aperegdef");
        String correo = request.getParameter("emailreg");
        String user = request.getParameter("userreg");
        String password = request.getParameter("passreg");
        String passwordconf = request.getParameter("passregconf");
        String genero = request.getParameter("generoreg");
        String fecha = request.getParameter("fechareg");

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = formato.parse(fecha);

        Integer ciudad = Integer.parseInt(request.getParameter("ciudadreg"));

        oConnection.setAutoCommit(false);
        UsuarioDaoGenSpImpl oUsuarioDao = new UsuarioDaoGenSpImpl("usuario", oConnection);

        try {

            UsuarioBeanGenSpImpl oUsuario = new UsuarioBeanGenSpImpl();
            oUsuario.setNombre(nombre);
            oUsuario.setApellidos(apellidos);
            oUsuario.setCorreo(correo);
            oUsuario.setLogin(user);
            oUsuario.setPassword(password);
            oUsuario.setGenero(genero);
            oUsuario.setFecha(fechaDate);
            oUsuario.setId_ciudad(ciudad);
            oUsuario.setId_tipousuario(2);

            oUsuarioDao.set(oUsuario);

            oConnection.commit();

        } catch (Exception ex) {
            oConnection.rollback();
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":set ERROR: " + ex.getMessage()));
        }
        return oUsuarioDao;
    }
}
