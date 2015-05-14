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
            <%} else if (atributo.equals("access") || atributo.equals("incorrecto")) {%>
            Error de Acceso a GoPlace
            <% }%>
        </title>
        <% if (atributo.equals("out") || atributo.equals("incorrecto") || atributo.equals("access")){%>
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
            </header>

            <main>
                <div class="principal princial_login row">
                    <div class="col-md-12 col-sm-12 titulo">
                        <% if (atributo.equals("access")) {%>
                        <jsp:include page='<%=(String) request.getAttribute("contenido")%>' />             
                        <%} else if (atributo.equals("out")) {%>
                        <jsp:include page='<%=(String) request.getAttribute("contenido")%>' />
                        <%} else if (atributo.equals("incorrecto")) {%>
                        <jsp:include page='<%=(String) request.getAttribute("contenido")%>' />
                        <% } else {%>
                        <h1>Ops! Sucedió un error inesperado</h1>
                        <span>Vuelva a Inicio de GoPlace y vuelve a intentarlo</span>
                        <% } %>
                    </div>
                    
                    <div class="col-md-12 col-sm-12 contenido">
                        <% if (atributo.equals("access") || atributo.equals("out")) {%>
                        <div class="register">
                            <form id="loginForm" action="control" role="form" method="post">
                                <input type="hidden" name="op" value="login" />
                                <div class="form-group formuser col-md-8 col-sm-8 col-xs-12">
                                    <input type="text" class="input-access" id="userform" placeholder="Usuario o Email" name="userform" required>
                                </div>
                                <div class="form-group formpass col-md-8 col-sm-8 col-xs-12">
                                    <input type="password" class="input-access" id="passform" placeholder="Contraseña" name="passform" required>
                                </div>
                                <div class="form-remember col-md-8 col-sm-8 col-xs-12">
                                    <span><a href="#" id="olvidopass_access">¿Has olvidado la contraseña?</a></span>
                                </div>
                                <div class="col-md-3 col-sm-3 col-xs-12">
                                    <button class="btn btn-lg btn-primary btn-block formbutton " type="submit">Acceder</button>
                                </div>
                                <!--<input type="checkbox" ng-click="deleteTodo(todo._id)"> {{ todo.text}}-->
                            </form>
                        </div>
                        
                        <%} else if (atributo.equals("incorrecto")) {%>
                            <div class="col-md-3 col-sm-3 col-xs-12 return_gp">
                                <a href="<%=request.getContextPath()%>/index.jsp">
                                    <button class="btn btn-lg btn-primary btn-block returnbutton " type="submit">Volver a Inicio</button>
                                </a>      
                            </div>
                        <% } else {%>
                            <div class="col-md-3 col-sm-3 col-xs-12 return_gp">
                                <a href="<%=request.getContextPath()%>/index.jsp">
                                    <button class="btn btn-lg btn-primary btn-block returnbutton " type="submit">Volver a Inicio</button>
                                </a>      
                            </div>
                        <% } %>
                    
                        <% if (atributo.equals("out")) {%>
                            <div class="col-md-3 col-sm-3 col-xs-12 return_gp">
                                    <a href="<%=request.getContextPath()%>/index.jsp">
                                        <button class="btn btn-lg btn-primary btn-block returnbutton " type="submit">Volver a Inicio</button>
                                    </a>      
                            </div>
                        <% } %>
                    </div>
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

