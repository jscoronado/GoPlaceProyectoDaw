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
import com.goplace.service.generic.specific.implementation.ComentarioServiceGenSpImpl;
import com.goplace.service.generic.specific.implementation.PublicacionServiceGenSpImpl;
import java.util.ArrayList;
import java.util.HashMap;

public class ComentarioControlOperationGenSpImpl extends ControlOperationGenImpl {

    private ComentarioServiceGenSpImpl oComentarioService = (ComentarioServiceGenSpImpl) oService;
    
    public ComentarioControlOperationGenSpImpl(HttpServletRequest request) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception {
        super(request);
    }

    @Override
    public String set(HttpServletRequest request) throws Exception {
        String result = "";
        if (perm) {
            UsuarioBeanGenSpImpl user = (UsuarioBeanGenSpImpl) request.getSession().getAttribute("usuarioBean");
            
            result = oComentarioService.set(ParameterCooker.prepareJson(request), user.getId(), user.getId_tipousuario());
            closeDB();
        } else {
            result = "error";
        }
        return result;
    }
    
    public String getpagescomentarios(HttpServletRequest request) throws Exception {
        String result = null;
        try {
            if (perm) {
                Integer intRegsPerPag = ParameterCooker.prepareRpp(request);
                UsuarioBeanGenSpImpl user = (UsuarioBeanGenSpImpl) request.getSession().getAttribute("usuarioBean");
                ArrayList<FilterBeanHelper> alFilter = ParameterCooker.prepareFilter(request);
                result = oComentarioService.getPagesComentarios(user.getId(), intRegsPerPag, alFilter);
                closeDB();
            } else {
                result = "Error, su usuario no tiene permisos para realizar esta operación.";
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getpages ERROR: " + ex.getMessage()));
        }
        return result;
    }

    public String getcomentarios(HttpServletRequest request) throws Exception {
        String result = null;
        int id_evento = 0;
        try {
            if (perm) {
                Integer intRegsPerPag = ParameterCooker.prepareRpp(request);
                Integer intPage = ParameterCooker.preparePage(request);
                UsuarioBeanGenSpImpl user = (UsuarioBeanGenSpImpl) request.getSession().getAttribute("usuarioBean");

                id_evento = ParameterCooker.prepareId(request);
                
                ArrayList<FilterBeanHelper> alFilter = ParameterCooker.prepareFilter(request);
                HashMap<String, String> hmOrder = ParameterCooker.prepareOrder(request);
                result = oComentarioService.getComentarios(id_evento, intRegsPerPag, intPage, alFilter, hmOrder);
                closeDB();
            } else {
                result = "Error, su usuario no tiene permisos para realizar esta operación.";
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getpage ERROR: " + ex.getMessage()));
        }
        return result;
    }
    
}
