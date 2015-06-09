<%-- 
 GoPlace - Jose M Coronado Aroca
--%>


<%@page import="com.goplace.helper.EstadoHelper"%>
<%@page import="com.goplace.bean.generic.specific.implementation.UsuarioBeanGenSpImpl"%>
<%@page import="com.goplace.helper.AppInformationHelper"%>

<%
    int id_tipousuario = 0, id_usuario = 0;
    UsuarioBeanGenSpImpl user = (UsuarioBeanGenSpImpl) request.getSession().getAttribute("admitted");
    if (user != null) {
        id_tipousuario = user.getId_tipousuario();
        id_usuario = user.getId();
    }

    String nombreUser = request.getParameter("nombrereg");
    String apeUser = request.getParameter("apereg");
    String emailUser = request.getParameter("emailreg");
    String passUser = request.getParameter("passreg");

    String login = request.getContextPath() + "/index.jsp";
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"> <!-- Zoom en el touch -->
        <title>Registro GoPlace</title>
        <meta name="author" content="Jose Miguel Coronado Aroca">
        <meta name="description" content="Web Developer">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <link rel="stylesheet" href="../css/styles.css">
        <link rel="stylesheet" href="../css/styles-mobile.css">
        <link rel="stylesheet" href="../css/effects.css">
        <link href='http://fonts.googleapis.com/css?family=Quicksand|Raleway:200|Oswald' rel='stylesheet' type='text/css'>
    </head>

    <body>
        <div class="pageRegister">
            <header class="title row">
                <div class="col-md-12 col-sm-12 logoRegistro">
                    <a href="../index.jsp"><img src="../images/logo/logo-black.png" alt="Logo GoPlace" class="logo"></a>
                </div>
                <div class="col-md-12 col-sm-12 cabecera_registro">
                    <a href="../index.jsp">¿Ya tienes cuenta? Inicia Sesión</a>
                </div>
            </header>

            <main>
                <div class="principal">
                    <div class="registro registro_confirm row">
                        <article class="titulo">
                            <h1>Únete a GoPlace</h1>
                            <h3>¿Nuevo en GoPlace? Empieza ya!</h3>
                            <div class="form_register_gp col-md-12 col-xs-12">
                                <form id="registerFormDef" action="../control" role="form" method="post">
                                    <input type="hidden" name="op" value="registro" />

                                    <div class="col-md-12 col-xs-12 form-control-gp">
                                        <div class="col-md-6 col-xs-12">
                                            <label for="labelNombre" class="control-label">Nombre</label>
                                            <input type="text" class="form-control" id="nombreregdef" placeholder="Nombre" name="nombreregdef" value="<%=nombreUser%>" required />
                                        </div>
                                        <div class="col-md-6 col-xs-12">
                                            <label for="labelApellidos" class="control-label">Apellidos</label>
                                            <input type="text" class="form-control" id="aperegdef" placeholder="Apellidos" name="aperegdef" value="<%=apeUser%>" required>
                                        </div>
                                    </div>

                                    <div class="col-md-12 col-xs-12 form-control-gp">
                                        <div class="col-md-6 col-xs-12">
                                            <label for="labelCorreo" class="control-label">Correo electronico</label>
                                            <input type="email" class="form-control" id="emailreg" placeholder="Correo electrónico" name="emailreg" value="<%=emailUser%>" required>
                                        </div>

                                        <div class="col-md-6 col-xs-12 form_control_sex">
                                            <label for="labelSexo" class="control-label">Sexo</label><br/>
                                            <input type="radio" class="form-radio" name="generoreg" id="generoreg" value="H"> <span>Hombre</span><br/>
                                            <input type="radio" class="form-radio" name="generoreg" id="generoreg" value="M"> <span>Mujer</span>
                                        </div>
                                    </div>

                                    <div class="col-md-12 col-xs-12 form-control-gp">
                                        <div class="col-md-6 col-xs-12">
                                            <label for="labelUsuario" class="control-label">Nombre de Usuario</label>
                                            <input type="text" class="form-control" id="passreg" placeholder="Nombre de Usuario" name="userreg"  required>
                                        </div>
                                        <div class="col-md-6 col-xs-12">
                                            <label for="labelContraseña" class="control-label">Contraseña</label>
                                            <input type="password" class="form-control" id="passreg" placeholder="Contraseña" name="passreg" value="<%=passUser%>" required>
                                        </div>
                                    </div>
                                    <!--<div class="col-md-6 col-xs-12">
                                        <div class="col-md-3"><label for="labelContraseña2" class="control-label">Conf Contraseña</label></div>
                                        <div class="col-md-5"><input type="password" class="form-control col-md-8" id="passregconf" placeholder="Confirma Contraseña" name="passregconf" required></div><br/><br/>
                                    </div>-->


                                    <div class="col-md-12 col-xs-12 form-control-gp">
                                        <div class="col-md-6 col-xs-12">
                                            <label for="labelFecha" class="control-label">Fecha de Nacimiento</label>
                                            <input type="date" id="fechareg" name="fechareg" class="form-control col-md-4" required>
                                        </div>

                                        <div class="col-md-6 col-xs-12">
                                            <label for="labelCiudad" class="control-label">Ciudad</label>
                                            <select id="ciudadreg" name="ciudadreg" class="form-control col-md-8" required>
                                                <option value="">- selecciona -</option>
                                                <option value="15">A coru&#241;a</option>
                                                <option value="1">&#193;lava</option>
                                                <option value="2">Albacete</option>
                                                <option value="3">Alicante</option>
                                                <option value="4">Almer&#237;a</option>
                                                <option value="33">Asturias</option>
                                                <option value="5">&#193;vila</option>
                                                <option value="6">Badajoz</option>
                                                <option value="7">Baleares</option>
                                                <option value="8">Barcelona</option>
                                                <option value="9">Burgos</option>
                                                <option value="10">C&#225;ceres</option>
                                                <option value="11">C&#225;diz</option>
                                                <option value="39">Cantabria</option>
                                                <option value="12">Castell&#243;n</option>
                                                <option value="51">Ceuta</option>
                                                <option value="13">Ciudad Real</option>
                                                <option value="14">C&#243;rdoba</option>
                                                <option value="16">Cuenca</option>
                                                <option value="17">Girona</option>
                                                <option value="18">Granada</option>
                                                <option value="19">Guadalajara</option>
                                                <option value="20">Guip&#250;zcoa</option>
                                                <option value="21">Huelva</option>
                                                <option value="22">Huesca</option>
                                                <option value="23">Ja&#233;n</option>
                                                <option value="26">La rioja</option>
                                                <option value="35">Las palmas</option>
                                                <option value="24">Le&#243;n</option>
                                                <option value="25">Lleida</option>
                                                <option value="27">Lugo</option>
                                                <option value="28">Madrid</option>
                                                <option value="29">M&#225;laga</option>
                                                <option value="52">Melilla</option>
                                                <option value="30">Murcia</option>
                                                <option value="31">Navarra</option>
                                                <option value="32">Ourense</option>
                                                <option value="34">Palencia</option>
                                                <option value="36">Pontevedra</option>
                                                <option value="37">Salamanca</option>
                                                <option value="38">Santa cruz de tenerife</option>
                                                <option value="40">Segovia</option>
                                                <option value="41">Sevilla</option>
                                                <option value="42">Soria</option>
                                                <option value="43">Tarragona</option>
                                                <option value="44">Teruel</option>
                                                <option value="45">Toledo</option>
                                                <option value="46">Valencia</option>
                                                <option value="47">Valladolid</option>
                                                <option value="48">Vizcaya</option>
                                                <option value="49">Zamora</option>
                                                <option value="50">Zaragoza</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-12 col-xs-12">
                                        <div class="col-md-12 botonreg">
                                            <button class="btn btn-lg btn-primary btn-block regbutton btn-goplace" type="submit">Regístrate en GoPlace !</button>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </article>
                    </div>
                </div>
            </main>

            <footer class="row">
                <div class="help col-md-12">
                    <a href="#" >Acerca de</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#">Ayuda</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#">Condiciones de uso</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#">Política de privacidad y cookies</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#">Empleo</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#">Anúnciate</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#">Prensa</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#">Blog</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#">Desarrolladores</a>
                </div>
                <p/><br/><span class="cr">GoPlace © 2015</span>
            </footer>
        </div>
    </body>
</html>