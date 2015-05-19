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

<form class="form-horizontal" role="form" action="#" id="comentarioForm" name="formulario">
    <div class="form-group hidden-xs hidden-sm hidden-md hidden-lg">
        <label class="col-sm-2 control-label" for="id">Id:</label>
        <div class="col-sm-2">
            <input type="text" id="id" class="form-control"  name="id" placeholder="id" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"  for="titulo">Comentario:</label>
        <div class="col-sm-6">
            <input type="text" id="coment" class="form-control"  name="coment" size="15" placeholder="Inserta un Comentario" />
        </div>
    </div>
    <div class="form-group hidden-xs hidden-sm hidden-md hidden-lg">
        <label class="col-sm-2 control-label" for="obj_usuario_id">Usuario: </label> 
        <div class="col-sm-2">              
            <input  class="form-control"  id="obj_usuario_id" class="input-mini" name="id_usuario" type="text" size="5" maxlength="5" value=" <%=id_usuario%>" readonly/>  
        </div>    
        <label class="col-sm-7" for="obj_usuario_desc" id="obj_usuario_desc"></label>                     
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="obj_publicacion_id">Evento: </label> 
        <div class="col-sm-2">              
            <input  class="form-control"  id="obj_publicacion_id" class="input-mini" name="id_publicacion" type="text" size="5" maxlength="5" />  
        </div>
        <div class="col-sm-1">              
            <a class="btn btn-primary btn-sm" id="obj_publicacion_button" href="#"><i class="glyphicon glyphicon-search"></i></a>
        </div>        
        <label class="col-sm-7" for="obj_publicacion_desc" id="obj_publicacion_desc"></label>                     
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
        $('#comentarioForm')
                .bootstrapValidator({
                    container: '#messages',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        contenido: {
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
                        id_publicacion: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe elegir un Evento'
                                },
                                integer: {
                                    message: 'El identificador de ciudad debe ser un entero'
                                }
                            }
                        }
                    }
                })
                .on('change', '[name="id_usuario"]', function () {
                    $('#comentarioForm').bootstrapValidator('revalidateField', 'id_usuario');
                });
    });
</script>
