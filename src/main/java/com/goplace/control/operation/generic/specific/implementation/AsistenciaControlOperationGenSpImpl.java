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
package com.goplace.control.operation.generic.specific.implementation;

import com.goplace.bean.generic.specific.implementation.UsuarioBeanGenSpImpl;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.HttpServletRequest;
import com.goplace.control.operation.generic.implementation.ControlOperationGenImpl;
import com.goplace.helper.ExceptionBooster;
import com.goplace.helper.FilterBeanHelper;
import com.goplace.helper.ParameterCooker;
import com.goplace.service.generic.specific.implementation.AsistenciaServiceGenSpImpl;
import java.util.ArrayList;
import java.util.HashMap;

public class AsistenciaControlOperationGenSpImpl extends ControlOperationGenImpl {

    private AsistenciaServiceGenSpImpl oAsistenciaService = (AsistenciaServiceGenSpImpl) oService;

    public AsistenciaControlOperationGenSpImpl(HttpServletRequest request) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception {
        super(request);
    }

    public String seguirEvento(HttpServletRequest request) throws Exception {
        String result = null;
        String existe = null;
        try {
            if (perm) {
                UsuarioBeanGenSpImpl user = (UsuarioBeanGenSpImpl) request.getSession().getAttribute("usuarioBean");
                int id_usuario = user.getId();
                int id_publicacion = ParameterCooker.prepareId(request);
                
                existe = oAsistenciaService.existeAsistencia(id_usuario, id_publicacion);
                
                /*if (id_usuario_1 != id_usuario_2) {*/
                    result = oAsistenciaService.seguirEvento(id_usuario, id_publicacion);
                /*} else {
                    result = "Error, un usuario no puede agregarse a sí miismo.";
                }*/
                closeDB();
            } else {
                result = "Error, su usuario no tiene permisos para realizar esta operación.";
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":seguirEvento ERROR: " + ex.getMessage()));
        }
        return result;
    }

    public String dejarEvento(HttpServletRequest request) throws Exception {
        String result = null;
        try {
            if (perm) {
                UsuarioBeanGenSpImpl user = (UsuarioBeanGenSpImpl) request.getSession().getAttribute("usuarioBean");
                result = oAsistenciaService.dejarEvento(user.getId(), ParameterCooker.prepareId(request));
                closeDB();
            } else {
                result = "Error, su usuario no tiene permisos para realizar esta operación.";
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":dejarEvento ERROR: " + ex.getMessage()));
        }
        return result;
    }

    public String existeAsistencia(HttpServletRequest request) throws Exception {
        String result = null;
        try {
            if (perm) {
                UsuarioBeanGenSpImpl user = (UsuarioBeanGenSpImpl) request.getSession().getAttribute("usuarioBean");
                result = oAsistenciaService.existeAsistencia(user.getId(), ParameterCooker.prepareId(request));
                closeDB();
            } else {
                result = "Error, su usuario no tiene permisos para realizar esta operación.";
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":existeAsistencia ERROR: " + ex.getMessage()));
        }
        return result;
    }

}
