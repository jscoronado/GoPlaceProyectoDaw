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
        <link rel="stylesheet" href="css/styles-mobile.css">
        <link rel="stylesheet" href="css/effects.css">
        <link href='http://fonts.googleapis.com/css?family=Quicksand|Raleway:200|Oswald|Montserrat' rel='stylesheet' type='text/css'>

    </head>

    <body>
        <div class="page">
            <header class="title row">
                <div class="col-md-6 col-sm-6 logo-goplace">
                    <a href="index.jsp"><img src="images/logo/logo-black.png" alt="Logo GoPlace" class="logo"></a>
                </div>

                <div class="col-md-6 col-sm-6 register">
                    <form id="loginForm" action="control" role="form" method="post">
                        <input type="hidden" name="op" value="login" />
                        <div class="form-group formuser col-md-5 col-sm-6 col-xs-12">
                            <input type="text" class="input-access" id="userform" placeholder="Usuario" name="userform" required>
                        </div>
                        <div class="form-group formpass col-md-5 col-sm-6 col-xs-12">
                            <input type="password" class="input-access" id="passform" placeholder="Contraseña" name="passform" required>
                        </div>
                        <!--<div class="form-remember col-md-7 col-sm-7 col-xs-12">
                            <input type="checkbox" id="recuerda" name="recuerda"> No cerrar sesión
                            <span><a href="#" id="olvidopass">¿Has olvidado la contraseña?</a></span>
                        </div>-->
                        <div class="forbutton_access col-md-2 col-sm-12 col-xs-12">
                            <button class="btn btn-lg btn-primary btn-block formbutton " type="submit">Acceder</button>
                        </div>
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
                        <div class="col-md-12 youarenew">
                            <p>¿ Eres nuevo en GoPlace ? <br/><span class="span_register">¡ Regístrate !</span></p>
                        </div>
                        <form id="registerForm" action="jsp/registro.jsp" role="form" method="post">
                            <div class="col-md-12">
                                <div class="form-group col-lg-6 col-md-6 regizq">
                                    <input type="text" class="input-register input-name" id="nombrereg" placeholder="Nombre" name="nombrereg" title="Introduzca su nombre" required>
                                </div>
                                <div class="form-group col-lg-6 col-md-6 regder">
                                    <input type="text" class="input-register input-ape" id="apereg" placeholder="Apellidos" name="apereg" title="Introduzca su Apellido" required>
                                </div>
                                <div class="form-group col-lg-6 col-md-6 regizq">
                                    <input type="mail" class="input-register" id="emailreg" pattern="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" placeholder="Correo electrónico" name="emailreg" title="mail@example.com" required>
                                </div>
                                <div class="form-group col-lg-6 col-md-6 regder">
                                    <input type="password" class="input-register" id="passreg" placeholder="Contraseña" name="passreg" title="Introduzca contraseña" required>
                                </div>
                            </div>
                            <div class="col-md-3 botonreg">
                                <button class="btn btn-lg btn-primary btn-block regbutton btn-goplace" type="submit">Regístrate en<br/>GoPlace !</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="principal_info">
                    <div class="titulo"><h2>Descubre los planes en tu ciudad</h2></div>
                    <div class="info_descubre row">
                        <div class="col-md-4 info_gp">
                            <img src="images/descubre1.png" alt="Descubre" class="logo">
                            <div class="descubre_text"><h3>Busca los planes que se celebran en tu ciudad</h3></div>
                        </div>
                        <div class="col-md-4 info_gp">
                            <img src="images/descubre2.png" alt="Encuentra" class="logo">
                            <div class="descubre_text"><h3>Crea tus propios planes y haz que lo vean los demás</h3></div>
                        </div><div class="col-md-4 info_gp">
                            <img src="images/descubre3.png" alt="Comenta" class="logo">
                            <div class="descubre_text"><h3>Comenta y queda con tus amigos de una forma mas sencilla</h3></div>
                        </div>
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