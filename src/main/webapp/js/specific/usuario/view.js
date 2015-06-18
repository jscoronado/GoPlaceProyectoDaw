/* 
 * Copyright (C) 2014 raznara
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */


var usuarioView = function (strClase) {
    this.clase = strClase;
};
usuarioView.prototype = new view('usuario');
usuarioView.prototype.getClassNameUsuario = function () {
    return this.getClassName() + "Vista";
};
var oUsuarioView = new usuarioView('usuario');


usuarioView.prototype.loadButtons = function (id) {

    var botonera = "";
    botonera += '<div class="btn-toolbar" role="toolbar"><div class="btn-group btn-group-xs">';
    botonera += '<a class="btn btn-default view" id="' + id + '"  href="control#/' + this.clase + '/view/' + id + '"><i class="glyphicon glyphicon-eye-open"></i></a>';
    
    if (myuser == id_usuario || mylevel == 1) {
        botonera += '<a class="btn btn-default edit" id="' + id + '"  href="control#/' + this.clase + '/edit/' + id + '"><i class="glyphicon glyphicon-pencil"></i></a>';
        botonera += '<a class="btn btn-default remove" id="' + id + '"  href="control#/' + this.clase + '/remove/' + id + '"><i class="glyphicon glyphicon-remove"></i></a>';
    }
    
    botonera += '</div></div>';
    return botonera;

}
usuarioView.prototype.loadFormValues = function (valores, campos) {
//                    $('#usuario_form #titulo').val(valores['titulo']);
//                    $('#usuario_form #contenido').val(valores['contenido']);
//                    $('#usuario_form #alta').val(valores['alta']);
//                    $('#usuario_form #cambio').val(valores['cambio']);
//                    $('#usuario_form #hits').val(valores['hits']);
//                    $('#usuario_form #id_usuario').val(valores['id_usuario']);
//                    $('#usuario_form #etiquetas').val(valores['etiquetas']);
//                    $('#usuario_form #publicado').val(valores['publicado']);
//                    $('#usuario_form #portada').val(valores['portada']);
    this.doFillForm(valores, campos);
};

usuarioView.prototype.getFormValues = function () {
    var valores = [];
//                    valores['titulo'] = $('#usuario_form #titulo');
//                    valores['contenido'] = $('#usuario_form #contenido');
//                    valores['alta'] = $('#usuario_form #alta');
//                    valores['cambio'] = $('#usuario_form #cambio');
//                    valores['hits'] = $('#usuario_form #hits');
//                    valores['id_usuario'] = $('#usuario_form #id_usuario');
//                    valores['etiquetas'] = $('#usuario_form #etiquetas');
//                    valores['publicado'] = $('#usuario_form #publicado');
//                    valores['portada'] = $('#usuario_form #portada');

    var disabled = $('#usuarioForm').find(':input:disabled').removeAttr('disabled');
    valores = $('#usuarioForm').serializeObject();
    disabled.attr('disabled', 'disabled');
    return valores;
};

usuarioView.prototype.doEventsLoading = function () {
    var thisObject = this;
    $('#usuarioForm #obj_ciudad_button').unbind('click');
    $("#usuarioForm #obj_ciudad_button").click(function () {
        var oControl = oCiudadControl;  //para probar dejar usuario
        //vista('usuario').cargaModalBuscarClaveAjena('#modal01', "usuario");

        $("#usuarioForm").append(thisObject.getEmptyModal());
        util().loadForm('#modal01', thisObject.getFormHeader('Elección de usuario'), "", thisObject.getFormFooter(), true);

        $('#usuarioForm').append(thisObject.getEmptyModal());

        oControl.list('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), true, oCiudadModel, oCiudadView);
        oControl.modalListEventsLoading('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), function (id) {
            $('#obj_ciudad_id').val(id).change();
            $('#obj_ciudad_desc').text(decodeURIComponent(oCiudadModel.getMeAsAForeignKey(id)));
            $('#modal01').modal('hide');

        },oCiudadModel, oCiudadView);
        return false;
    });
    $('#usuarioForm #obj_tipousuario_button').unbind('click');
    $("#usuarioForm #obj_tipousuario_button").click(function () {
        var oControl = oTipousuarioControl;

        $("#usuarioForm").append(thisObject.getEmptyModal());
        util().loadForm('#modal01', thisObject.getFormHeader('Elección de tipo de usuario'), "", thisObject.getFormFooter(), true);

        $('#usuarioForm').append(thisObject.getEmptyModal());

        oControl.list('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), true, oTipousuarioModel, oTipousuarioView);
        oControl.modalListEventsLoading('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), function (id) {
            $('#obj_tipousuario_id').val(id).change();
            $('#obj_tipousuario_desc').text(decodeURIComponent(oTipousuarioModel.getMeAsAForeignKey(id)));
            $('#modal01').modal('hide');

        },oTipousuarioModel, oTipousuarioView);
        return false;
    });
    $('#contenido_button').unbind('click');
    $('#contenido_button').click(function () {
        //cabecera = '<button id="full-width" type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>' + '<h3 id="myModalLabel">Edición de contenidos</h3>';
        cabecera = thisObject.getFormHeader('Edición de contenidos');
        //pie = '<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cerrar</button>';                        
        pie = '<a class="btn btn-goplace" href="http://creoleparser.googlecode.com/svn/trunk/creoleparser/test_pages/CheatSheetPlus.html">Sintaxis</a>';
        pie += thisObject.getFormFooter();
        contenido = '<div class="row"><div class="col-md-6">';
        contenido += '<textarea type="text" id="contenidomodal" name="contenido" rows="20" cols="70" placeholder="contenido"></textarea>';
        contenido += '</div><div class="col-md-6"><div id="textoparseado"></div></div>';
        contenido += '</div>';

        $('#usuarioForm').append(thisObject.getEmptyModal());

        util().loadForm('#modal01', cabecera, contenido, pie, true);
        var texto = $('#contenido').val();
        $('#contenidomodal').val(texto);
        util().creoleParse(texto, $('#textoparseado'));
        $('#contenido').val($('#contenidomodal').val());
        $('#contenidomodal').keyup(function () {
            util().creoleParse($('#contenidomodal').val(), $('#textoparseado'));
            $('#contenido').val($('#contenidomodal').val());
        });
        return false;
    });
};

usuarioView.prototype.okValidation = function (f) {
    $('#usuarioForm').on('success.form.bv', f);
};

usuarioView.prototype.doResultOperationNotifyToUser = function (place, resultadoStatus, resultadoMessage, id, mostrar, id_usuario_2) {
    var strNombreClase = this.clase;
    if (resultadoStatus == "200") {
        mensaje = "<h3>La operacion se ha ejecutado con éxito</h3>";
    } else {
        mensaje = "<h3>ERROR</h3>";
        resultadoMessage = "Error, el usuario con ID = " + id_usuario_2 + " usuario ya es tu amigo no?.";
    }
    mensaje += "<h5>Código: " + resultadoStatus + "</h5><h5>" + resultadoMessage + "</h5>";
    $(place).append(this.getEmptyModal());
    util().loadForm('#modal01', this.getFormHeader('Respuesta del servidor'), mensaje, this.getFormFooter(), true);
    $('#modal01').css({
        'right': '20px',
        'left': '20px',
        'width': 'auto',
        'margin': '10px',
        'display': 'block'
    });

    $('#modal01').on('hidden.bs.modal', function () {
        location.reload();
    })
    ;
};

usuarioView.prototype.getPerfil = function (perfil, comentario, eventos) {
    
    espacio = ' ';
    comilla = "'";
    salto = "<br />";
    apertura = "<";
    cierre = ">";
    barra = "/";
    alm = "#";
    
plantillaPerfil = "<div class=" + "'perfilGoPlace'" + "id=" + "'perfilGoPlace'" + ">";
    
    plantillaPerfil += "<div class=" + "'cabeceraPerfil row'" + "id=" + "'cabeceraPerfil'" + ">";
        plantillaPerfil += "<img src=" + comilla + "http://www.zastavki.com/pictures/originals/2014/World___India_Young_people_resting_in_Goa_066065_.jpg" + comilla + "class=" + comilla + "fotoCabecera col-md-12 col-xs-12" + comilla + " alt=" + comilla + "Foto Cabecera de " + perfil.data.nombre + espacio + perfil.data.apellidos + comilla + ">";
    plantillaPerfil += "</div>";
    
    plantillaPerfil += "<div class=" + comilla + "content_perfil row" + comilla + ">";
    
        /* Perfil */
        plantillaPerfil += "<div class=" + comilla + "perfilgp col-md-4 col-xs-12" + comilla + ">";
            plantillaPerfil += "<div class=" + comilla + "perfil_photo col-md-12 col-xs-4" + comilla + ">";
                if(perfil.data.imagen == null || perfil.data.imagen == ""){
                    plantillaPerfil += "<img src=" + comilla + "/images/user.png" + comilla + "class=" + comilla + "foto_perfil" + comilla + " alt=" + comilla + "Foto perdil de " + perfil.data.nombre + espacio + perfil.data.apellidos + comilla + ">";
                }else {
                    plantillaPerfil += perfil.data.imagen;
                }
            plantillaPerfil += "</div>";
            plantillaPerfil += "<div class=" + comilla + "perfil_desc col-md-12 col-xs-8" + comilla + ">";
                plantillaPerfil += "<h1 class=" + comilla + "permil_name user col-md-12 col-xs-12" + perfil.data.id + comilla + ">" + perfil.data.nombre + espacio + perfil.data.apellidos + "</h1>";
                plantillaPerfil += "<span class=" + comilla + "nick perfil_nick" + comilla + "> @" + perfil.data.login + "</span><br/>";
                plantillaPerfil += "<p class=" + comilla + "perfil_estado" + comilla + ">" + perfil.data.estado + "</p>";
                plantillaPerfil += "<p class=" + comilla + "perfil_ciudad" + comilla + ">" + perfil.data.obj_ciudad.nombre + "</p>";
            plantillaPerfil += "</div>";
            
            if (myuser == perfil.data.id || myuser == "1") {
                plantillaPerfil += "<div class=" + comilla + "perfil_function col-md-12 col-sm-8 col-xs-12 boton_perfil" + comilla + " id=" + comilla + "perfil_function" + comilla + "></div>";
            }else{
                plantillaPerfil += "<div class=" + comilla + "perfil_function col-md-6 col-sm-8 col-xs-12 boton_perfil" + comilla + " id=" + comilla + "perfil_function" + comilla + "></div>";
            }   
            plantillaPerfil += "<div class=" + comilla + "perfil_friends col-md-6 col-sm-8 col-xs-12 boton_perfil" + comilla + " id=" + comilla + "perfil_friends" + comilla + ">";
                plantillaPerfil +='<a class="btn btn-goplace" href="control#/amigos/' + perfil.data.id + '">Ver amigos</a>';
            plantillaPerfil += "</div>";
            if (myuser == perfil.data.id || myuser == "1") {
            plantillaPerfil += "<div class=" + comilla + "perfil_photo_edit col-md-6 col-sm-8 col-xs-12 boton_perfil" + comilla + " id=" + comilla + "perfil_friends" + comilla + ">";
                plantillaPerfil +='<a class="btn btn-goplace" id="botonperfil" href="control#/usuario/upload/' + perfil.data.id + '">Foto de perfil</a>';
            plantillaPerfil += '</div>';
            }
        plantillaPerfil += "</div>";
        
        /* Comentarios */
        plantillaPerfil += "<div class=" + comilla + "comentariosgp col-md-4 col-xs-12" + comilla + ">";
            
            plantillaPerfil += "<h2 class=" + comilla + "col-md-12 title_perfil" + comilla +">Tus Comentarios</h2>";
            
            var coment = comentario.list;
            
            if (coment.length != 0) {
                for (i = 0; i < coment.length; i++) {
                    plantillaPerfil += "<div class=" + comilla + "comentario col-md-12 col-xs-12" + comilla + ">";
                    plantillaPerfil += "<div class=" + comilla + "col-md-2 col-xs-2 foto_comentario" + comilla + ">";
                    plantillaPerfil += "<img src=" + comilla + "/images/user.png" + comilla + "class=" + comilla + "fotoComent" + comilla + " alt=" + comilla + "Foto usuario" + coment[i].obj_usuario.id + comilla + ">";
                    plantillaPerfil += "</div>";
                    plantillaPerfil += "<div class=" + comilla + "col-md-10 col-xs-10 desc_comentario" + comilla + ">";
                    plantillaPerfil += "<span class=" + comilla + "id_comentario" + comilla + "> #" + coment[i].id + "</span>";
                    plantillaPerfil += "<p class="+ comilla + "perfil_coment" + comilla + ">" + coment[i].coment + "</p>";
                    plantillaPerfil += "<a href=" + comilla + "#/evento/" + coment[i].obj_publicacion.id + comilla + " class="+ comilla + "event_coment" + comilla + ">" + coment[i].obj_publicacion.titulo + espacio + "( " +coment[i].obj_publicacion.obj_ciudad.nombre + " )"+" </a>";
                    plantillaPerfil += "</div>";
                    plantillaPerfil += "</div>";
                }
            } else {
                plantillaPerfil += "<div class=" + comilla + "comentario col-md-12 col-xs-12" + comilla + ">";
                plantillaPerfil += "<div class=" + comilla + "col-md-2 col-xs-12 foto_comentario" + comilla + ">";
                plantillaPerfil += "<img src=" + comilla + "/images/user_admin.png" + comilla + "class=" + comilla + "fotoComent" + comilla + " alt=" + comilla + "Foto usuario admin" + comilla + ">";
                plantillaPerfil += "</div>";
                plantillaPerfil += "<div class=" + comilla + "col-md-10 col-xs-10 desc_comentario" + comilla + ">";
                plantillaPerfil += "<b>" + "Administrador" + "</b>";
                plantillaPerfil += "<span class=" + comilla + "nick" + comilla + "> @admin" + "</span><br/>";
                plantillaPerfil += "<span>¡ Haz tu primer comentario !</span>";
                plantillaPerfil += "</div>";
                plantillaPerfil += "</div>";
            }
        plantillaPerfil += "</div>";
        
        /* Eventos */
        plantillaPerfil += "<div class=" + comilla + "eventosgp col-md-4 col-xs-12" + comilla + ">";
            plantillaPerfil += "<h2 class=" + comilla + "col-md-12 title_perfil" + comilla +">Eventos Próximos</h2>";
            
            var events = eventos.list;
             
            if (events.length != 0) {
                for (i = 0; i < events.length; i++) {
                    if(oUsuarioView.compareDate(events[i].obj_publicacion.fechapub) == "1"){
                    plantillaPerfil += "<div class=" + comilla + "evento_perfil col-md-12 col-sm-6 col-xs-12" + comilla + ">";
                    plantillaPerfil += "<h4><a href=" + comilla + "#/evento/" + events[i].obj_publicacion.id + comilla + ">" + events[i].obj_publicacion.titulo + "</a></h4>";
                    plantillaPerfil += "<span class="+ comilla + "ciudad_event" + comilla + ">" + events[i].obj_publicacion.obj_ciudad.nombre + "</span><br/>";
                    plantillaPerfil += "<span class="+ comilla + "fecha_event" + comilla + ">" + events[i].obj_publicacion.fechapub + "</span><br/>";
                    plantillaPerfil += "</div>";
                    }
                }
            } else {
                plantillaPerfil += "<div class=" + comilla + "evento_perfil col-md-12 col-xs-12" + comilla + ">";
                    plantillaPerfil += "<h4 style=" + comilla + "text-align: center" + comilla + ">No tienes Eventos Próximos</h4>";
                    plantillaPerfil += "</div>";
            }
        plantillaPerfil += "</div>";
        
    plantillaPerfil += "</div>";
    
plantillaPerfil += "</div>";
    
    return plantillaPerfil;
};

usuarioView.prototype.compareDate = function (dateEvento) {

    var result;
    
    /* Date Today */
    var hoy = new Date();

    var dd = hoy.getDate();
    var mm = hoy.getMonth()+1; 
    var yyyy = hoy.getFullYear();
    var dateToday = new Date(mm + "/" + dd + "/" + yyyy);
    
    /* Date Event */
    var parts = dateEvento.split("/");
    var dateEv = new Date(parts[1] + "/" + parts[0] + "/" + parts[2]);
    
    if(dateEv >= dateToday){
        result = "1";
    } else {
        result = "0";
    }
    
    return result;
 };