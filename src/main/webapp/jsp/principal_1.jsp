<%-- 
 GoPlace - Jose M Coronado Aroca
--%>


<%@page import="com.goplace.helper.EstadoHelper"%>
<%@page import="com.goplace.bean.generic.specific.implementation.UsuarioBeanGenSpImpl"%>
<%@page import="com.goplace.helper.AppInformationHelper"%>

<%
    int id_tipousuario = 0, id_usuario = 0;
    String nombre;
    UsuarioBeanGenSpImpl user = (UsuarioBeanGenSpImpl) request.getSession().getAttribute("admitted");
    if (user != null) {
        id_tipousuario = user.getId_tipousuario();
        id_usuario = user.getId();
        nombre = user.getNombre();
    }
    
    String logout = request.getContextPath() + "/control?op=logout";
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"> <!-- Zoom en el touch -->
        <title>GoPlace</title>
        <meta name="author" content="Jose Miguel Coronado Aroca">
        <meta name="description" content="Web Developer">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/effects.css">
        <link href='http://fonts.googleapis.com/css?family=Quicksand|Raleway:200|Oswald' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/vendor/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/vendor/bootstrap.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/inicio.js" charset="UTF-8"></script>
    </head>

    
    <body>
        <div class="pagePrincipal">
            <header class="title row">
                <div class="col-md-10 col-sm-10 logoMain">
                    <a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/images/logo.png" alt="Logo GoPlace"></a>
                </div>
                <ul class="nav nav-tabs navbar-left">
                    <li class="actived"><a href="#" class="glyphicon glyphicon-home"><span> Inicio</span></a></li>
                    <li><a href="#" class="glyphicon glyphicon-user"><span> Perfil</span></a></li>
                    <li><a href="#" class="glyphicon glyphicon-comment"><span> Descubre</span></a></li>
                    <li class="publicarbtn"><a href="#" class="glyphicon glyphicon-send"><span> Publicar</span></a></li>
                </ul>
                <ul class="nav nav-tabs navbar-right">
                    <li>
                        <form class="navbar-form navbar-left" role="search">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Buscar en GoPlace">
                                <button type="submit" class="btn glyphicon glyphicon-search"></button>
                            </div>
                        </form>
                    </li>
                    <li class="cerrarbtn"><a href="<%=logout%>" class="glyphicon glyphicon-off"><span> Cerrar Sesion</span></a></li>
                </ul>
            </header>

            <main>
                <div class="principalpag">
                        <!--<h1 class="titulo">Has Iniciado Sesion</h1>-->
                    <div class="row">
                        <div class="col-md-3 col-sm-3 perfilMain">
                            <div class="fotoPerfil"><img src="<%=request.getContextPath()%>/images/user.png" class="foto" alt="Foto usuario"></div><br/>
                            <h3 class="nomargin"><a href="#">Jose Miguel Coronado Aroca</a></h3>
                        </div>
                        <div class="col-md-9 col-sm-9 inicioMain" >
                            <div class="publicacion row">
                                <div class="col-md-1">
                                    <img src="<%=request.getContextPath()%>/images/user.png" class="fotoPub" alt="Foto usuario">
                                </div>
                                <div class="col-md-11">
                                    <a href="#">Jose Miguel Coronado Aroca</a> <span class="nick">@joshco</span><br/>
                                    <h4><a href="#">Titulo del acondecimiento</a></h4>
                                    <span>Descripcion del acondecimiento en tu ciudad etc etc etc</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
            <script>
            <%
                String espacio = "&nbsp;&nbsp;&nbsp;&nbsp;";
            %>
            </script>
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

