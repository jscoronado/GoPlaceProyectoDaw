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

package com.goplace.control;

import com.google.gson.Gson;
import com.goplace.bean.generic.specific.implementation.UsuarioBeanGenSpImpl;
import com.goplace.control.operation.generic.specific.implementation.AmistadControlOperationGenSpImpl;
import com.goplace.control.operation.generic.specific.implementation.AsistenciaControlOperationGenSpImpl;
import com.goplace.control.operation.generic.specific.implementation.CiudadControlOperationGenSpImpl;
import com.goplace.control.operation.generic.specific.implementation.PublicacionControlOperationGenSpImpl;
import com.goplace.control.operation.generic.specific.implementation.TipopublicacionControlOperationGenSpImpl;
import com.goplace.control.operation.generic.specific.implementation.ComentarioControlOperationGenSpImpl;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goplace.control.operation.generic.specific.implementation.TipousuarioControlOperationGenSpImpl;
import com.goplace.control.operation.generic.specific.implementation.UsuarioControlOperationGenSpImpl;
import com.goplace.control.route.generic.specific.implementation.AmistadControlRouteGenSpImpl;
import com.goplace.control.route.generic.specific.implementation.AsistenciaControlRouteGenSpImpl;
import com.goplace.control.route.generic.specific.implementation.CiudadControlRouteGenSpImpl;
import com.goplace.control.route.generic.specific.implementation.PublicacionControlRouteGenSpImpl;
import com.goplace.control.route.generic.specific.implementation.TipopublicacionControlRouteGenSpImpl;
import com.goplace.control.route.generic.specific.implementation.ComentarioControlRouteGenSpImpl;

import com.goplace.control.route.generic.specific.implementation.TipousuarioControlRouteGenSpImpl;
import com.goplace.control.route.generic.specific.implementation.UsuarioControlRouteGenSpImpl;

import com.goplace.helper.EstadoHelper;
import com.goplace.helper.EstadoHelper.Tipo_estado;
import com.goplace.helper.ExceptionBooster;
import com.goplace.helper.ParameterCooker;

public class JsonControl extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private void retardo(Integer iLast) {
        try {
            Thread.sleep(iLast);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception ex) {
                if (EstadoHelper.getTipo_estado() == Tipo_estado.Debug) {
                    Map<String, String> data = new HashMap<>();
                    data.put("status", "500");
                    data.put("message", "ERROR: " + ex.getMessage());
                    Gson gson = new Gson();
                    request.setAttribute("contenido", gson.toJson(data));
                    getServletContext().getRequestDispatcher("/jsp/messageAjax.jsp").forward(request, response);
                } else {
                    Map<String, String> data = new HashMap<>();
                    data.put("status", "500");
                    data.put("message", "Applications server error. Please, contact your administrator.");
                    Gson gson = new Gson();
                    request.setAttribute("contenido", gson.toJson(data));
                    getServletContext().getRequestDispatcher("/jsp/messageAjax.jsp").forward(request, response);
                }
                Logger.getLogger(JsonControl.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

            //----------------------------------------------------------------------          
            retardo(0);
            retardo(0); //debug delay
            String jsonResult = "";
            if (request.getSession().getAttribute("usuarioBean") != null) {
                
                UsuarioBeanGenSpImpl oUsuario = new UsuarioBeanGenSpImpl();
                oUsuario = (UsuarioBeanGenSpImpl) request.getSession().getAttribute("usuarioBean");
                switch (ParameterCooker.prepareObject(request)) {
                    case "usuario":
                        UsuarioControlRouteGenSpImpl oUsuarioRoute = new UsuarioControlRouteGenSpImpl();
                        UsuarioControlOperationGenSpImpl oUsuarioControlOperation = new UsuarioControlOperationGenSpImpl(request);
                        jsonResult = oUsuarioRoute.execute(request, oUsuarioControlOperation);
                        break;
                    case "tipousuario":
                        TipousuarioControlRouteGenSpImpl oTipousuarioRoute = new TipousuarioControlRouteGenSpImpl();
                        TipousuarioControlOperationGenSpImpl oTipousuarioControlOperation = new TipousuarioControlOperationGenSpImpl(request);
                        jsonResult = oTipousuarioRoute.execute(request, oTipousuarioControlOperation);
                        break;
                    case "publicacion":
                        PublicacionControlRouteGenSpImpl oPublicacionRoute = new PublicacionControlRouteGenSpImpl();
                        PublicacionControlOperationGenSpImpl oPublicacionControlOperation = new PublicacionControlOperationGenSpImpl(request);
                        jsonResult = oPublicacionRoute.execute(request, oPublicacionControlOperation);
                        break;
                    case "tipopublicacion":
                        TipopublicacionControlRouteGenSpImpl oTipopublicacionRoute = new TipopublicacionControlRouteGenSpImpl();
                        TipopublicacionControlOperationGenSpImpl oTipopublicacionControlOperation = new TipopublicacionControlOperationGenSpImpl(request);
                        jsonResult = oTipopublicacionRoute.execute(request, oTipopublicacionControlOperation);
                        break;
                    case "comentario":
                        ComentarioControlRouteGenSpImpl oComentarioRoute = new ComentarioControlRouteGenSpImpl();
                        ComentarioControlOperationGenSpImpl oComentarioControlOperation = new ComentarioControlOperationGenSpImpl(request);
                        jsonResult = oComentarioRoute.execute(request, oComentarioControlOperation);
                        break;
                    case "ciudad":
                        CiudadControlRouteGenSpImpl oCiudadRoute = new CiudadControlRouteGenSpImpl();
                        CiudadControlOperationGenSpImpl oCiudadControlOperation = new CiudadControlOperationGenSpImpl(request);
                        jsonResult = oCiudadRoute.execute(request, oCiudadControlOperation);
                        break;
                    case "asistencia":
                        AsistenciaControlRouteGenSpImpl oAsistenciaRoute = new AsistenciaControlRouteGenSpImpl();
                        AsistenciaControlOperationGenSpImpl oAsistenciaControlOperation = new AsistenciaControlOperationGenSpImpl(request);
                        jsonResult = oAsistenciaRoute.execute(request, oAsistenciaControlOperation);
                        break;
                    case "amistad":
                        AmistadControlRouteGenSpImpl oAmistadRoute = new AmistadControlRouteGenSpImpl();
                        AmistadControlOperationGenSpImpl oAmistadControlOperation = new AmistadControlOperationGenSpImpl(request);
                        jsonResult = oAmistadRoute.execute(request, oAmistadControlOperation);
                        break;
                    default:
                        ExceptionBooster.boost(new Exception(this.getClass().getName() + ":processRequest ERROR: no such operation"));
                }
            } else {
                jsonResult = "{\"error\" : \"No active server session\"}";
            }
            if (jsonResult.equals("error")) {
                Map<String, String> data = new HashMap<>();
                data.put("status", "403");
                data.put("message", "ERROR: You don't have permission to perform this operation");
                Gson gson = new Gson();
                request.setAttribute("contenido", gson.toJson(data));
                getServletContext().getRequestDispatcher("/jsp/messageAjax.jsp").forward(request, response);
            } else {
                request.setAttribute("contenido", jsonResult);
            }
            getServletContext().getRequestDispatcher("/jsp/messageAjax.jsp").forward(request, response);
        } catch (Exception ex) {
            if (EstadoHelper.getTipo_estado() == Tipo_estado.Debug) {
                Map<String, String> data = new HashMap<>();
                data.put("status", "500");
                data.put("message", "ERROR: " + ex.getMessage());
                Gson gson = new Gson();
                request.setAttribute("contenido", gson.toJson(data));
                getServletContext().getRequestDispatcher("/jsp/messageAjax.jsp").forward(request, response);
            } else {
                Map<String, String> data = new HashMap<>();
                data.put("status", "500");
                data.put("message", "Applications server error. Please, contact your administrator.");
                Gson gson = new Gson();
                request.setAttribute("contenido", gson.toJson(data));
                getServletContext().getRequestDispatcher("/jsp/messageAjax.jsp").forward(request, response);
            }
            Logger.getLogger(JsonControl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            //Logger.getLogger(JsonControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            //Logger.getLogger(JsonControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
