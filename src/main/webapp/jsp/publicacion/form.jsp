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

<form class="form-horizontal" role="form" action="#" id="publicacionForm" name="formulario">
    <div class="form-group">
        <label class="col-sm-2 control-label" for="id">Id:</label>
        <div class="col-sm-2">
            <input type="text" id="id" class="form-control"  name="id" placeholder="id" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"  for="titulo">Titulo:</label>
        <div class="col-sm-6">
            <input type="text" id="titulo" class="form-control"  name="titulo" size="15" placeholder="Inserta un Titulo" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"  for="descripcion">Descripcion:</label>
        <div class="col-sm-6">
            <input type="text" id="descripcion" class="form-control"  name="descripcion" size="15" placeholder="Inserta una Descripcion" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label" for="obj_usuario_id">Usuario: </label> 
        <div class="col-sm-2">              
            <input readonly="true"  class="form-control"  id="obj_usuario_id" class="input-mini" name="id_usuario" type="text" size="5" maxlength="5" />  
        </div>
        <div class="col-sm-1">              
            <a class="btn btn-primary btn-sm" id="obj_usuario_button" href="#"><i class="glyphicon glyphicon-search"></i></a>
        </div>        
        <label class="col-sm-7" for="obj_usuario_desc" id="obj_usuario_desc"></label>                     
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
    <div class="form-group">
        <label class="col-sm-2 control-label" for="obj_tipopublicacion_id">Tipo Publicacion: </label> 
        <div class="col-sm-2">              
            <input readonly="true"  class="form-control"  id="obj_tipopublicacion_id" class="input-mini" name="id_tipopublicacion" type="text" size="5" maxlength="5" />  
        </div>
        <div class="col-sm-1">              
            <a class="btn btn-primary btn-sm" id="obj_tipopublicacion_button" href="#"><i class="glyphicon glyphicon-search"></i></a>
        </div>        
        <label class="col-sm-7" for="obj_tipopublicacion_desc" id="obj_tipopublicacion_desc"></label>                     
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
        $('#publicacionForm')
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
                        }
                        id_ciudad: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe elegir un ciudad'
                                },
                                integer: {
                                    message: 'El identificador de ciudad debe ser un entero'
                                }
                            }
                        }
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
                .on('change', '[name="id_ciudad"]', function () {
                    $('#publicacionForm').bootstrapValidator('revalidateField', 'id_ciudad');
                });
                .on('change', '[name="id_tipopublicacion"]', function () {
                    $('#publicacionForm').bootstrapValidator('revalidateField', 'id_tipopublicacion');
                });
    });
</script>
