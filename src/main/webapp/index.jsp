<%-- 
 GoPlace - Jose M Coronado Aroca
--%>


<%@page import="com.goplace.helper.EstadoHelper"%>
<%@page import="com.goplace.bean.generic.specific.implementation.UsuarioBeanGenSpImpl"%>
<%@page import="com.goplace.helper.AppInformationHelper"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"> <!-- Zoom en el touch -->
        <title>GoPlace</title>
        <meta name="author" content="Jose Miguel Coronado Aroca">
        <meta name="description" content="Web Developer">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <link rel="stylesheet" href="css/effects.css">
        <link href='http://fonts.googleapis.com/css?family=Quicksand|Raleway:200|Oswald' rel='stylesheet' type='text/css'>

    </head>

    <body>
        <div class="page">
            <header class="title row">
                <div class="col-md-6 col-sm-6">
                    <a href="index.jsp"><img src="images/logo.png" alt="Logo GoPlace" class="logo"></a>
                </div>

                <div class="col-md-6 col-sm-6">
                    <form id="loginForm" action="control" role="form" method="post">
                        <input type="hidden" name="op" value="login" />
                        <div class="form-group formuser">
                            <input type="text" class="form-control" id="userform" placeholder="Usuario o Email" name="userform" required>
                            <input type="checkbox" id="recuerda" name="recuerda"> No cerrar sesión
                        </div>
                        <div class="form-group formpass">
                            <input type="password" class="form-control" id="passform" placeholder="Contraseña" name="passform" required>
                            <span><a href="#" id="olvidopass">¿Has olvidado la contraseña?</a></span>
                        </div>
                        <button class="btn btn-lg btn-primary btn-block formbutton" type="submit">Acceder</button>
                        <!--<input type="checkbox" ng-click="deleteTodo(todo._id)"> {{ todo.text}}-->
                    </form>
                </div>
            </header>

            <main>
                <div class="principal">
                    <article class="titulo">
                        <h1>Bienvenido a GoPlace</h1>
                        <span> Una red social donde poder ver y compartir con tus amigos y otra cantidad de personas los eventos que se hacen en tu ciudad o alrededores.<br/> Obtén actualizaciones instantáneas de las cosas que te interesan!</span>
                    </article>
                    <div class="registro row">
                        <h3>¿Eres nuevo en GoPlace? <span>Regístrate!</span></h3>
                        <form id="registerForm" action="jsp/registro.jsp" role="form" method="post">
                            <div class="col-md-9">
                                <div class="form-group reguser">
                                    <input type="text" class="form-control" id="nombrereg" placeholder="Nombre" name="nombrereg">
                                    <input type="text" class="form-control" id="apereg" placeholder="Apellidos" name="apereg">
                                </div>
                                <div class="form-group regemail">
                                    <input type="text" class="form-control" id="emailreg" placeholder="Correo electrónico" name="emailreg">
                                </div>
                                <div class="form-group regpass">
                                    <input type="password" class="form-control" id="passreg" placeholder="Contraseña" name="passreg">
                                </div>
                            </div>
                            <div class="col-md-3 botonreg">
                                <button class="btn btn-lg btn-primary btn-block regbutton" type="submit">Regístrate en<br/>GoPlace !</button>
                            </div>
                        </form>
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