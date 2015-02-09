<%-- 
 GoPlace - Jose M Coronado Aroca
--%>


<%@page import="com.goplace.helper.EstadoHelper"%>
<%@page import="com.goplace.bean.generic.specific.implementation.UsuarioBeanGenSpImpl"%>
<%@page import="com.goplace.helper.AppInformationHelper"%>

<%
    String login = request.getContextPath() + "/index.jsp";
    String atributo = (String) request.getAttribute("atributo");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"> <!-- Zoom en el touch -->
        <title>
            <% if (atributo.equals("out")) {%>
            Saliendo de GoPlace
            <%} else if (atributo.equals("incorrecto")) { %>
            Error de Acceso a GoPlace
            <% }%>
        </title>
        <% if (atributo.equals("out") || atributo.equals("incorrecto")) {%>
        <meta name="author" content="Jose Miguel Coronado Aroca">
        <meta name="description" content="Web Developer">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/effects.css">
        <link href='http://fonts.googleapis.com/css?family=Quicksand|Raleway:200|Oswald' rel='stylesheet' type='text/css'>
        <% }%>
    </head>

    <body>
        <div class="page">
            <header class="title row">
                <div class="col-md-10 col-sm-12 logoRegistro">
                    <a href="<%=request.getContextPath()%>/index.jsp"><img src="images/logo.png" alt="Logo GoPlace"></a>
                </div>
                <div class="cabecera">
                    <a href="<%=login%>">Iniciar Sesion</a>
                </div>
            </header>

            <main>
                <div class="principal">
                    <article class="titulo">
                        <% if (atributo.equals("out")) {%>
                        <jsp:include page='<%=(String) request.getAttribute("contenido")%>' />
                        <%} else if (atributo.equals("incorrecto")) {%>
                        <jsp:include page='<%=(String) request.getAttribute("contenido")%>' />
                        <% } else {%>
                        <h1>Prueba prueba</h1>
                        <% } %>
                    </article>
                </div>
            </main>

            <%
                String espacio = "&nbsp;&nbsp;&nbsp;&nbsp;";
            %>
            <footer class="row">
                <div class="help col-md-12"> 
                    <a href="#" >Acerca de</a> <%=espacio%>
                    <a href="#">Ayuda</a> <%=espacio%>
                    <a href="#">Condiciones de uso</a> <%=espacio%>
                    <a href="#">Política de privacidad y cookies</a> <%=espacio%>
                    <a href="#">Empleo</a> <%=espacio%>
                    <a href="#">Anúnciate</a> <%=espacio%>
                    <a href="#">Prensa</a>&nbsp; <%=espacio%>
                    <a href="#">Blog</a> <%=espacio%>
                    <a href="#">Desarrolladores</a>
                </div>
                <p/><br/><span class="cr">GoPlace © 2015</span>
            </footer>
        </div>
    </body>
</html>

