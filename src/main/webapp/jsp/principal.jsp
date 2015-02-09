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

    </head>

    <body>
        <div class="pagePrincipal">
            <header class="title row">
                <div class="col-md-10 col-sm-10 logoRegistro">
                    <a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/images/logo.png" alt="Logo GoPlace"></a>
                </div><br/>
                <div class="cabecera">
                    <a href="<%=logout%>">Cerrar Sesion</a>
                </div>
            </header>

            <main>
                <div class="principal">
                    <article class="titulo">
                        <h1>Has Iniciado Sesion</h1>
                        <span> Una red social donde poder ver y compartir con tus amigos y otra cantidad de personas los eventos que se hacen en tu ciudad o alrededores.<br/> Obtén actualizaciones instantáneas de las cosas que te interesan!</span>
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

