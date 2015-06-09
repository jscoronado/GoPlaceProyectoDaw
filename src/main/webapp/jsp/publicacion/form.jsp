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

<%
    int id_tipousuario = 0, id_usuario = 0;

    UsuarioBeanGenSpImpl user = (UsuarioBeanGenSpImpl) request.getSession().getAttribute("usuarioBean");
    if (user != null) {
        id_tipousuario = user.getId_tipousuario();
        id_usuario = user.getId();
    }
%>

<div class="col-md-12 col-xs-12 form_publicacion">

    <form class="form-horizontal form_gp" role="form" action="#" id="publicacionForm" name="formulario">

        <div class="col-md-6 col-xs-12 section_form_gp">
            <div class="form-group hidden-xs hidden-sm hidden-md hidden-lg ">
                <h3 class="col-sm-12 control-label" for="id">Id</h3>
                <div class="col-sm-2">
                    <input type="text" id="id" class="form-control"  name="id" placeholder="id" />
                </div>
            </div>
            <div class="form-group">
                <h3 class="col-sm-12 control-label"  for="titulo">Titulo</h3>
                <div class="col-sm-12">
                    <input type="text" id="titulo" class="form-control"  name="titulo" size="15" placeholder="Inserta un Titulo" />
                </div>
            </div>
            <div class="form-group">
                <h3 class="col-sm-12 control-label"  for="descripcion">Descripcion</h3>
                <div class="col-sm-12">
                    <textarea type="text" id="descripcion" class="form-control"  name="descripcion" placeholder="Inserta una Descripcion" ></textarea>
                </div>
            </div>
        </div>

        <div class="col-md-6 col-xs-12 section_form_gp">

            <div class="form-group hidden-xs hidden-sm hidden-md hidden-lg ">
                <h3 class="col-sm-12 control-label" for="obj_usuario_id">Usuario</h3> 
                <div class="col-sm-2">              
                    <input  class="form-control"  id="obj_usuario_id" class="input-mini" name="id_usuario" type="text" size="5" maxlength="5" value=" <%=id_usuario%>" readonly/>  
                </div>       
                <label class="col-sm-7" for="obj_usuario_desc" id="obj_usuario_desc"></label>                     
            </div>

            <div class="col-md-12 col-xs-12 nopadding">        
                <div class="form-group col-md-4 col-sm-4 col-xs-12 nopadding">
                    <h3 class="col-md-12 control-label" for="obj_ciudad_id">Ciudad</h3> 
                    <div class="col-md-8 col-xs-11 padding-left">              
                        <input  class="form-control"  id="obj_ciudad_id" class="input-mini" name="id_ciudad" type="text" size="5" maxlength="5" readonly/>  
                    </div>
                    <div class="col-md-4 col-xs-1 padding-right">              
                        <a class="btn btn-primary btn-sm btn_gp_search" id="obj_ciudad_button" href="#"><i class="glyphicon glyphicon-search"></i></a>
                    </div>        
                    <label class="col-sm-8" for="obj_ciudad_desc" id="obj_ciudad_desc"></label>                     
                </div>

                <div class="form-group col-md-8 col-sm-12 col-xs-12 nopadding">
                    <h3 class="col-sm-12 control-label"  for="direccion">Dirección</h3>
                    <div class="col-sm-12">
                        <input type="text" id="direccion" class="form-control"  name="direccion" size="15" placeholder="Inserta la direccion del Evento" />
                    </div>
                </div>
            </div>    

            <div class="col-md-12 col-xs-12 nopadding">        
                <div class="form-group col-md-4 col-sm-4 col-xs-12 nopadding">
                    <h3 class="col-md-12 control-label" for="obj_tipopublicacion_id">Tipo Evento</h3> 
                    <div class="col-md-8 col-xs-11 padding-left">              
                        <input  class="form-control"  id="obj_tipopublicacion_id" class="input-mini" name="id_tipopublicacion" type="text" size="5" maxlength="5" readonly/>  
                    </div>
                    <div class="col-md-4 col-xs-1 padding-right">              
                        <a class="btn btn-primary btn-sm btn_gp_search" id="obj_tipopublicacion_button" href="#"><i class="glyphicon glyphicon-search"></i></a>
                    </div>        
                    <label class="col-sm-8" for="obj_tipopublicacion_desc" id="obj_tipopublicacion_desc"></label>                     
                </div>

                <div class="form-group col-md-8 col-xs-12 nopadding">
                    <h3 class="col-sm-12 control-label"  for="fechapub">Fecha del Evento</h3>
                    <div class="col-sm-12">
                        <input type="date" id="fechapub" class="form-control" name="fechapub" placeholder="Indica fecha y hora del Evento" required>
                    </div>
                </div>
            </div> 

        </div>
        <div class="form-group col-md-12 col-xs-12">
            <div class="col-sm-10 col-md-12 col-xs-12">
                <button class="btn btn-primary" id="submitForm">PUBLICAR</button>
            </div>
        </div>


    </form>
</div>

<div class="col-md-6 col-xs-12 message_form">
    <div id="messages"></div>
</div>



<script type="text/javascript">

    $(document).ready(function () {

        //http://jqueryvalidation.org/documentation/
        $('#publicacionForm')
                .bootstrapValidator({
                    container: '#messages',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        titulo: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe introducir un titulo'
                                },
                                stringLength: {
                                    max: 140,
                                    message: 'El titulo debe tener como máximo 140 caracteres'
                                }
                            }
                        },
                        descripcion: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe introducir una descripción'
                                },
                                stringLength: {
                                    max: 255,
                                    message: 'La descripción debe tener como máximo 255 caracteres'
                                }
                            }
                        },
                        direccion: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe introducir una dirección'
                                },
                                stringLength: {
                                    max: 255,
                                    message: 'La dirección debe tener como máximo 255 caracteres'
                                }
                            }
                        },
                        id_usuario: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe elegir un usuario'
                                },
                                integer: {
                                    message: 'El identificador de usuario debe ser un entero'
                                }
                            }
                        },
                        id_ciudad: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe elegir un ciudad'
                                },
                                integer: {
                                    message: 'El identificador de ciudad debe ser un entero'
                                }
                            }
                        },
                        fechapub: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe introducir una fecha'
                                }
                            }
                        },
                        id_tipopublicacion: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe elegir un tipopublicacion'
                                },
                                integer: {
                                    message: 'El identificador de tipopublicacion debe ser un entero'
                                }
                            }
                        }
                    }
                })
                .on('change', '[name="id_usuario"]', function () {
                    $('#publicacionForm').bootstrapValidator('revalidateField', 'id_usuario');
                });
    });
</script>
