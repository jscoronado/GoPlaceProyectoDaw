<%-- 
 Copyright (C) July 2014 Rafael Aznar

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
--%>

<%@page import="java.sql.Connection"%>
<%@page import="com.goplace.bean.generic.specific.implementation.UsuarioBeanGenSpImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%
    int  id_usuario;
    String nombre, apellidos, login;
    UsuarioBeanGenSpImpl user = (UsuarioBeanGenSpImpl) request.getSession().getAttribute("usuarioBean");
    //if (user != null) {
       id_usuario = user.getId();
       nombre = user.getNombre();
       apellidos = user.getApellidos();
       login = user.getLogin();
    //}
%>
<div class="row">
    <div class="col-md-3 col-sm-3 perfilMain">
        <div class="fotoPerfil"><img src="<%=request.getContextPath()%>/images/user.png" class="foto" alt="Foto usuario"></div><br/>
        <h3 class="nomargin"><a href="#"><%=nombre%><%=apellidos%></a></h3>
    </div>
    <div class="col-md-9 col-sm-9 inicioMain" id="comentariosgp"></div>
</div>
