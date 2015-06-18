/*
 * Copyright (C) 2014 Rafael Aznar
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

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.goplace.bean.generic.specific.implementation.UsuarioBeanGenSpImpl;
import com.goplace.connection.implementation.BoneConnectionPoolImpl;
import com.goplace.connection.publicinterface.ConnectionInterface;
import com.goplace.dao.generic.specific.implementation.UsuarioDaoGenSpImpl;
import com.goplace.helper.EstadoHelper;
import com.goplace.helper.ParameterCooker;
import com.goplace.service.generic.specific.implementation.UsuarioServiceGenSpImpl;

public class Control extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private void retardo(Integer iLast) {
        try {
            Thread.sleep(iLast);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ConnectionInterface DataConnectionSource = null;
        Connection connection = null;

        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            //HTTP headers
            response.setHeader("page language", "java");
            response.setHeader("contentType", "text/html");
            response.setHeader("pageEncoding", "UTF-8");
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            //parameter loading
            String ob = ParameterCooker.prepareObject(request);
            String op = ParameterCooker.prepareOperation(request);
            String mode = ParameterCooker.prepareMode(request);
            //security check
            if (request.getSession().getAttribute("usuarioBean") == null) { // sin sesion
                ob = "usuario";
                if (!op.equals("inicio") && !op.equals("login02") && !op.equals("registro")) {
                    op = "login";
                    mode = "wrappered";
                }

                if (op.equalsIgnoreCase("inicio")) {
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                }

                if (request.getParameter("op").equalsIgnoreCase("login")) {
                    UsuarioBeanGenSpImpl oUsuario = new UsuarioBeanGenSpImpl();
                    String user = request.getParameter("userform");
                    String password = request.getParameter("passform");
                    if (!user.equals("") && !password.equals("")) {
                        try {
                            DataConnectionSource = new BoneConnectionPoolImpl();
                            connection = DataConnectionSource.newConnection();
                            oUsuario.setLogin(user);
                            UsuarioDaoGenSpImpl oUsuarioDao = new UsuarioDaoGenSpImpl("Usuario", connection);
                            oUsuario = oUsuarioDao.getPass("password", oUsuario);
                            String oUsuarioPass = oUsuario.getPassword();
                            if (oUsuarioPass.equals(password)) {
                                request.getSession().setAttribute("usuarioBean", oUsuario);
                                getServletContext().getRequestDispatcher("/jsp/principal.jsp").forward(request, response);
                            } else {
                                request.setAttribute("title", "Usuario o Contraseña incorrecta");
                                request.setAttribute("message", "Por favor, vuelva a iniciar sesión para acceder a GoPlace");
                                request.setAttribute("atributo", "access");
                                request.setAttribute("contenido", "/jsp/message.jsp");
                                getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                            }
                        } catch (Exception ex) {
                            if (EstadoHelper.getTipo_estado() == EstadoHelper.Tipo_estado.Debug) {
                                request.setAttribute("title", "Usuario o Contraseña incorrecta");
                                request.setAttribute("message", "Por favor, vuelva a iniciar sesión para acceder a GoPlace");
                                request.setAttribute("atributo", "access");
                                request.setAttribute("contenido", "/jsp/message.jsp");
                                getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                            } else {
                                request.setAttribute("title", "Usuario o Contraseña incorrecta");
                                request.setAttribute("message", "Por favor, vuelva a iniciar sesión para acceder a GoPlace");
                                request.setAttribute("atributo", "access");
                                request.setAttribute("contenido", "/jsp/message.jsp");
                                getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                            }
                            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                // REGISTRO
                if (request.getParameter("op").equalsIgnoreCase("registro")) {
                    UsuarioBeanGenSpImpl oUsuario = new UsuarioBeanGenSpImpl();

                    try {
                        DataConnectionSource = new BoneConnectionPoolImpl();
                        connection = DataConnectionSource.newConnection();

                        UsuarioServiceGenSpImpl oUsuarioService = new UsuarioServiceGenSpImpl("Usuario", "Usuario", connection);
                        UsuarioDaoGenSpImpl oUsuarioDao = oUsuarioService.registro(request);

                        String user = request.getParameter("userreg");
                        oUsuario.setLogin(user);
                        oUsuario = oUsuarioDao.getPass("password", oUsuario);

                        if (oUsuario != null) {
                            //request.getSession().setAttribute("usuarioBean", oUsuario);
                            //getServletContext().getRequestDispatcher("/jsp/principal.jsp").forward(request, response);
                            request.setAttribute("title", "Bienvenido " + oUsuario.getLogin() + " !");
                            request.setAttribute("message", "Por favor, inicie sesion para empezar en GoPlace");
                            request.setAttribute("atributo", "access");
                            request.setAttribute("contenido", "/jsp/message.jsp");
                            getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                        } else {
                            request.setAttribute("title", "Error al crear el usuario");
                            request.setAttribute("message", "Lo sentimos, hubo problemas al crear el usuario");
                            request.setAttribute("atributo", "incorrecto");
                            request.setAttribute("contenido", "/jsp/message.jsp");
                            getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                        }
                    } catch (Exception ex) {
                        if (EstadoHelper.getTipo_estado() == EstadoHelper.Tipo_estado.Debug) {
                            request.setAttribute("title", "Error al crear el usuario");
                            request.setAttribute("message", "Lo sentimos, hubo problemas al crear el usuario");
                            request.setAttribute("atributo", "incorrecto");
                            request.setAttribute("contenido", "/jsp/message.jsp");
                            getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                        } else {
                            request.setAttribute("title", "Error al crear el usuario");
                            request.setAttribute("message", "Lo sentimos, hubo problemas al crear el usuario");
                            request.setAttribute("atributo", "incorrecto");
                            request.setAttribute("contenido", "/jsp/message.jsp");
                            getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                        }
                        Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else { //con sesion
                if (ob.equalsIgnoreCase("usuario")) {
                    if (op.equalsIgnoreCase("inicio") || op.equalsIgnoreCase("login")) {
                        getServletContext().getRequestDispatcher("/jsp/principal.jsp").forward(request, response);
                    }
                }

                //logout
                if (request.getParameter("op").equals("logout")) {
                    request.getSession().invalidate();
                    request.setAttribute("title", "Saliendo de GoPlace");
                    request.setAttribute("message", "¡ Vuelva pronto !");
                    request.setAttribute("atributo", "out");
                    request.setAttribute("contenido", "/jsp/message.jsp");
                    getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                }
                //menu
                if (request.getParameter("ob") != null) {
                    if (request.getParameter("ob").equals("menu")) {
                        getServletContext().getRequestDispatcher("/menu.jsp").forward(request, response);
                    }
                    if (request.getParameter("ob").equals("menu2")) {
                        getServletContext().getRequestDispatcher("/menu2.jsp").forward(request, response);
                    }
                }

                //delivering jsp page
                if ("wrappered".equals(mode)) {
                    request.setAttribute("contenido", "jsp/" + ob + "/" + op + ".jsp");
                    request.setAttribute("connection", connection);
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                } else {
                    response.setContentType("text/html; charset=UTF-8");
                    request.setAttribute("connection", connection);
                    getServletContext().getRequestDispatcher("/jsp/" + ob + "/" + op + ".jsp").forward(request, response);
                }
            }

        } catch (Exception ex) {
            if (EstadoHelper.getTipo_estado() == EstadoHelper.Tipo_estado.Debug) {
                request.setAttribute("title", "404 Application server error (debug mode)");
                request.setAttribute("message", "<pre>ERROR: " + ex.getMessage() + "</pre>");
                request.setAttribute("atributo", "incorrecto");
                request.setAttribute("contenido", "/jsp/message.jsp");
                getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            } else {
                request.setAttribute("title", "404 Application server error");
                request.setAttribute("message", "Please, contact your server administrator.");
                request.setAttribute("atributo", "incorrecto");
                request.setAttribute("contenido", "/jsp/message.jsp");
                getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            }
            Logger.getLogger(JsonControl.class.getName()).log(Level.SEVERE, null, ex);
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
