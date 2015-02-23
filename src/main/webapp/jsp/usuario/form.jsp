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
<%@page import="com.goplace.bean.generic.specific.implementation.UsuarioBeanGenSpImpl"%>
<%UsuarioBeanGenSpImpl user = (UsuarioBeanGenSpImpl) request.getSession().getAttribute("usuarioBean");%>
<%
    int id = user.getId();
    String genero = user.getGenero();
%>

<form class="form-horizontal" role="form" action="#" id="usuarioForm" name="formulario">
    <div class="form-group">
        <label class="col-sm-2 control-label" for="id">Id:</label>
        <div class="col-sm-2">
            <input type="text" id="id" class="form-control"  name="id" placeholder="id" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"  for="nombre">Nombre:</label>
        <div class="col-sm-6">
            <input type="text" id="nombre" class="form-control"  name="nombre" size="15" placeholder="Pon aquí tu nombre" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"  for="apellidos">Apellidos:</label>
        <div class="col-sm-6">
            <input type="text" id="apellidos" class="form-control"  name="apellidos" size="15" placeholder="Pon aquí tus apellidos" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"  for="correo">Correo:</label>
        <div class="col-sm-6">
            <input type="text" id="correo" class="form-control"  name="correo" size="15" placeholder="Pon aquí tu correo" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"  for="login">Login:</label>
        <div class="col-sm-6">
            <input type="text" id="login" class="form-control"  name="login" size="15" placeholder="Pon aquí tu login" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"  for="password">Contraseña:</label>
        <div class="col-sm-6">
            <input type="password" id="password" class="form-control"  name="password" size="15" placeholder="Pon aquí tu contraseña" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="fecha_group">Fecha Nacimiento:</label> 
        <div class="col-sm-3">           
            <div class='input-group date' id='fecha_group'>
                <input type='text' class="form-control" id='fecha' name="fecha_group" placeholder="Fecha de nacimiento" />
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="obj_ciudad_id">Ciudad: </label> 
        <div class="col-sm-2">              
            <input readonly="true"  class="form-control"  id="obj_ciudad_id" class="input-mini" name="id_ciudad" type="text" size="5" maxlength="5" />  
        </div>
        <div class="col-sm-1">              
            <a class="btn btn-primary btn-sm" id="obj_ciudad_button" href="#"><i class="glyphicon glyphicon-search"></i></a>
        </div>        
        <label class="col-sm-7" for="obj_ciudad_desc" id="obj_ciudad_desc"></label>                     
    </div>
    <input type="hidden" name="genero" value="<%=genero%>" />
    <div class="form-group">
        <label class="col-sm-2 control-label"  for="estado">Estado:</label>
        <div class="col-sm-6">
            <input type="text" id="estado" class="form-control"  name="estado" size="15" placeholder="Pon aquí tu estado" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="obj_tipousuario_id">Tipo de usuario: </label> 
        <div class="col-sm-2">              
            <input readonly="true"  class="form-control"  id="obj_tipousuario_id" class="input-mini" name="id_tipousuario" type="text" size="5" maxlength="5" />  
        </div>
        <div class="col-sm-1">              
            <a class="btn btn-primary btn-sm" id="obj_tipousuario_button" href="#"><i class="glyphicon glyphicon-search"></i></a>
        </div>        
        <label class="col-sm-7" for="obj_usuario_desc" id="obj_tipousuario_desc"></label>                     
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <div id="messages"></div>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-primary" id="submitForm">Guardar</button>
        </div>
    </div>

</form>


<script type="text/javascript">

    $(document).ready(function () {
        //http://jqueryvalidation.org/documentation/
        $('#usuarioForm')
                .bootstrapValidator({
                    container: '#messages',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        nombre: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe introducir un nombre'
                                },
                                stringLength: {
                                    max: 255,
                                    message: 'El nombre debe tener como máximo 255 caracteres'
                                }
                            }
                        },
                        apellidos: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe introducir apellidos'
                                }
                            }
                        },
                        correo: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe introducir un correo'
                                }
                            }
                        },
                        login: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe introducir un login'
                                }
                            }
                        },
                        password: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe introducir una contraseña'
                                },
                                stringLength: {
                                    min: 4,
                                    message: 'La contraseña debe tener como minimo 4 caracteres'
                                }
                            }
                        },
                        fecha_group: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe introducir una fecha de nacimiento'
                                },
                                date: {
                                    format: 'DD/MM/YYYY',
                                    message: 'La fecha de alta no tiene formato DD/MM/YYYY'
                                }
                            }
                        },
                        id_ciudad: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe elegir una ciudad'
                                },
                                integer: {
                                    message: 'El identificador de ciudad debe ser un entero'
                                }
                            }
                        },
                        estado: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe introducir un estado'
                                },
                                stringLength: {
                                    max: 140,
                                    message: 'El estado debe tener como máximo 140 caracteres'
                                }
                            }
                        },
                        id_tipousuario: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe elegir un tipo de usuario'
                                },
                                integer: {
                                    message: 'El identificador de tipo de usuario debe ser un entero'
                                }
                            }
                        }
                    }
                })
                .on('change', '[name="id_ciudad"]', function () {
                    $('#usuarioForm').bootstrapValidator('revalidateField', 'id_ciudad');
                })

                .on('change', '[name="id_tipousuario"]', function () {
                    $('#usuarioForm').bootstrapValidator('revalidateField', 'id_tipousuario');
                })
                ;
        $('#fecha_group').on('dp.change dp.show', function (e) {
// Revalidate the date when user change it
            $('#usuarioForm').bootstrapValidator('revalidateField', 'fecha_group');
        });
    });



</script>
