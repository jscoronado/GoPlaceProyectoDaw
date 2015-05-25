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
package com.goplace.control.route.generic.specific.implementation;

import javax.servlet.http.HttpServletRequest;
import com.goplace.control.operation.generic.specific.implementation.AsistenciaControlOperationGenSpImpl;
import com.goplace.control.operation.publicinterface.ControlOperationInterface;
import com.goplace.control.route.generic.implementation.ControlRouteGenImpl;
import com.goplace.helper.ExceptionBooster;
import com.goplace.helper.ParameterCooker;

public class AsistenciaControlRouteGenSpImpl extends ControlRouteGenImpl {
    @Override
    public String execute(HttpServletRequest request, ControlOperationInterface oControl) throws Exception {
        AsistenciaControlOperationGenSpImpl oAsistenciaControl = (AsistenciaControlOperationGenSpImpl) oControl;
        String operation = ParameterCooker.prepareOperation(request);
        String jsonResult = "";
        try {
            switch (operation) {
                case "seguirevento":
                    jsonResult = oAsistenciaControl.seguirEvento(request);
                    break;
                case "dejarevento":
                    jsonResult = oAsistenciaControl.dejarEvento(request);
                    break;
                case "existeasistencia":
                    jsonResult = oAsistenciaControl.existeAsistencia(request);
                    break;
                default:
                    jsonResult = super.execute(request, oControl);
                    break;
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":execute ERROR: " + ex.getMessage()));
        }
        return jsonResult;
    }
}
