<%-- 
 GoPlace - Jose M Coronado Aroca
--%>


<%@page import="com.goplace.helper.EstadoHelper"%>
<%@page import="com.goplace.bean.generic.specific.implementation.UsuarioBeanGenSpImpl"%>
<%@page import="com.goplace.helper.AppInformationHelper"%>

<%
    int id_tipousuario = 0, id_usuario = 0;
    String nombre, apellidos;
    UsuarioBeanGenSpImpl user = (UsuarioBeanGenSpImpl) request.getSession().getAttribute("usuarioBean");
    if (user != null) {
        id_tipousuario = user.getId_tipousuario();
        id_usuario = user.getId();
        nombre = user.getNombre();
        apellidos = user.getApellidos();
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
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/effects.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
        <link href='http://fonts.googleapis.com/css?family=Quicksand|Raleway:200|Oswald' rel='stylesheet' type='text/css'>
    </head>


    <body>
        <div class="pagePrincipal">
            <header class="title row">
                <div class="col-md-10 col-sm-10 logoMain">
                    <a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/images/logo.png" alt="Logo GoPlace"></a>
                </div>
                <ul class="nav nav-tabs navbar-left">
                    <li class="actived"><a href="#/publicacion/inicio" class="glyphicon glyphicon-home"><span> Inicio</span></a></li>
                    <li><a href="#/usuario/view/<%=user.getId()%>" class="glyphicon glyphicon-user"><span> Perfil</span></a></li>
                    <li><a href="#/publicacion/descubre/view/<%=user.getId_ciudad()%>" class="glyphicon glyphicon-comment"><span> Descubre</span></a></li>
                    <li class="publicarbtn"><a href="#/publicacion/new" class="glyphicon glyphicon-send"><span> Publicar</span></a></li>
                </ul>
                <ul class="nav nav-tabs navbar-right">
                    <li><a href="#/usuario/view/<%=user.getId()%>" class="glyphicon glyphicon-user"><span><%=user.getNombre()%> <%=user.getApellidos()%></span></a></li>
                    <li class="cerrarbtn"><a href="<%=logout%>" class="glyphicon glyphicon-off"><span> Cerrar Sesion</span></a></li>
                </ul>
            </header>

            <main>
                <div id="principalpag"><h1>Estas dentro de GoPlace</h1></div>
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
        <script type="text/javascript"  src="./js/vendor/jquery-1.11.1.min.js"></script>
        <script type="text/javascript"  src="./js/vendor/bootstrap.min.js"></script>
        <script type="text/javascript"  src="./js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>

        <script type="text/javascript"  src="./js/vendor/moment.js"></script>
        <script type="text/javascript"  src="./js/vendor/moment.locale.es.js"></script>
        <script type="text/javascript"  src="./js/vendor/bootstrap-datetimepicker.min.js"></script>

        <script type="text/javascript"  src="./js/vendor/path.min.js"></script> 
        <script type="text/javascript"  src="./js/vendor/bootstrapValidator.min.js"></script>
        <script type="text/javascript"  src="./js/vendor/language/es_ES.js"></script>
        <script type="text/javascript"  src="./js/vendor/creole-parser.js"></script>            

        <script type="text/javascript" src="<%=request.getContextPath()%>/js/vendor/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/vendor/bootstrap.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>

        <script src="<%=request.getContextPath()%>/js/generic/view.js" charset="UTF-8"></script>    
        <script src="<%=request.getContextPath()%>/js/generic/param.js" charset="UTF-8"></script>
        <script src="<%=request.getContextPath()%>/js/generic/ajax.js" charset="UTF-8"></script>
        <script src="<%=request.getContextPath()%>/js/generic/util.js" charset="UTF-8"></script>
        <script src="<%=request.getContextPath()%>/js/generic/model.js" charset="UTF-8"></script>        
        <script src="<%=request.getContextPath()%>/js/generic/control.js" charset="UTF-8"></script> 
        <script src="<%=request.getContextPath()%>/js/generic/initialization.js" charset="UTF-8"></script>

        <script type="text/javascript">
                var path = '<%=request.getContextPath()%>';
                var myuser = <%=id_usuario%>;
                var mylevel = <%=id_tipousuario%>;
        </script>

        <script src="<%=request.getContextPath()%>/js/specific/inicioRedSocial/model.js" charset="UTF-8"></script>
        <script src="<%=request.getContextPath()%>/js/specific/inicioRedSocial/control.js" charset="UTF-8"></script>
        <script src="<%=request.getContextPath()%>/js/specific/inicioRedSocial/routes.js" charset="UTF-8"></script>
        <!-- view.js de inicioRedSocial -->

        <script src="<%=request.getContextPath()%>/js/specific/publicacion/view.js" charset="UTF-8"></script>
        <script src="<%=request.getContextPath()%>/js/specific/publicacion/model.js" charset="UTF-8"></script>
        <script src="<%=request.getContextPath()%>/js/specific/publicacion/control.js" charset="UTF-8"></script>
        <script src="<%=request.getContextPath()%>/js/specific/publicacion/routes.js" charset="UTF-8"></script>

        <script src="<%=request.getContextPath()%>/js/specific/usuario/view.js" charset="UTF-8"></script>
        <script src="<%=request.getContextPath()%>/js/specific/usuario/model.js" charset="UTF-8"></script>
        <script src="<%=request.getContextPath()%>/js/specific/usuario/control.js" charset="UTF-8"></script>
        <script src="<%=request.getContextPath()%>/js/specific/usuario/routes.js" charset="UTF-8"></script>


        <script type="text/javascript">

                $(document).ready(function () {

                    //$('#indexContenidoJsp').addClass('animated slideInDown');
                    //$('#menuSuperior').addClass('animated slideInLeft');
                    //$('#menuLateral').addClass('animated slideInRight');

                    //
                    inicializacion();
                    fPublicacionRoutes();
                    fUsuarioRoutes();
                    fInicioRedSocialRoutes();
                    Path.listen();

                });

        </script>           
    </body>
</html>

