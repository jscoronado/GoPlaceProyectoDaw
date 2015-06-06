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


var publicacionView = function (strClase) {
    this.clase = strClase;
};
publicacionView.prototype = new view('publicacion');
publicacionView.prototype.getClassNamePublicacion = function () {
    return this.getClassName() + "Vista";
};
var oPublicacionView = new publicacionView('publicacion');


publicacionView.prototype.loadButtons = function (id) {

    var botonera = "";
    botonera += '<div class="btn-toolbar" role="toolbar"><div class="btn-group btn-group-xs">';
    botonera += '<a class="btn btn-default view" id="' + id + '"  href="control#/' + this.clase + '/view/' + id + '"><i class="glyphicon glyphicon-eye-open"></i></a>';
    botonera += '<a class="btn btn-default edit" id="' + id + '"  href="control#/' + this.clase + '/edit/' + id + '"><i class="glyphicon glyphicon-pencil"></i></a>';
    botonera += '<a class="btn btn-default remove" id="' + id + '"  href="control#/' + this.clase + '/remove/' + id + '"><i class="glyphicon glyphicon-remove"></i></a>';
    botonera += '</div></div>';
    return botonera;

}
publicacionView.prototype.loadFormValues = function (valores, campos) {
//                    $('#publicacion_form #titulo').val(valores['titulo']);
//                    $('#publicacion_form #contenido').val(valores['contenido']);
//                    $('#publicacion_form #alta').val(valores['alta']);
//                    $('#publicacion_form #cambio').val(valores['cambio']);
//                    $('#publicacion_form #hits').val(valores['hits']);
//                    $('#publicacion_form #id_usuario').val(valores['id_usuario']);
//                    $('#publicacion_form #etiquetas').val(valores['etiquetas']);
//                    $('#publicacion_form #publicado').val(valores['publicado']);
//                    $('#publicacion_form #portada').val(valores['portada']);
    this.doFillForm(valores, campos);
};

publicacionView.prototype.getFormValues = function () {
    var valores = [];
//                    valores['titulo'] = $('#publicacion_form #titulo');
//                    valores['contenido'] = $('#publicacion_form #contenido');
//                    valores['alta'] = $('#publicacion_form #alta');
//                    valores['cambio'] = $('#publicacion_form #cambio');
//                    valores['hits'] = $('#publicacion_form #hits');
//                    valores['id_usuario'] = $('#publicacion_form #id_usuario');
//                    valores['etiquetas'] = $('#publicacion_form #etiquetas');
//                    valores['publicado'] = $('#publicacion_form #publicado');
//                    valores['portada'] = $('#publicacion_form #portada');

    var disabled = $('#publicacionForm').find(':input:disabled').removeAttr('disabled');
    valores = $('#publicacionForm').serializeObject();
    disabled.attr('disabled', 'disabled');
    return valores;
};

publicacionView.prototype.doEventsLoading = function () {
    var thisObject = this;
    $('#publicacionForm #obj_usuario_button').unbind('click');
    $("#publicacionForm #obj_usuario_button").click(function () {
        var oControl = oUsuarioControl;  //para probar dejar publicacion
        //vista('publicacion').cargaModalBuscarClaveAjena('#modal01', "publicacion");

        $("#publicacionForm").append(thisObject.getEmptyModal());
        util().loadForm('#modal01', thisObject.getFormHeader('Elección de usuario'), "", thisObject.getFormFooter(), true);

        $('#publicacionForm').append(thisObject.getEmptyModal());

        oControl.list('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), true, oUsuarioModel, oUsuarioView);
        oControl.modalListEventsLoading('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), function (id) {
            $('#obj_usuario_id').val(id).change();
            $('#obj_usuario_desc').text(decodeURIComponent(oUsuarioModel.getMeAsAForeignKey(id)));
            $('#modal01').modal('hide');

        },oUsuarioModel, oUsuarioView);
        return false;
    });
    
    $('#publicacionForm #obj_ciudad_button').unbind('click');
    $("#publicacionForm #obj_ciudad_button").click(function () {
        var oControl = oCiudadControl;  //para probar dejar publicacion
        //vista('publicacion').cargaModalBuscarClaveAjena('#modal01', "publicacion");

        $("#publicacionForm").append(thisObject.getEmptyModal());
        util().loadForm('#modal01', thisObject.getFormHeader('Elección de ciudad'), "", thisObject.getFormFooter(), true);

        $('#publicacionForm').append(thisObject.getEmptyModal());

        oControl.list('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), true, oCiudadModel, oCiudadView);
        oControl.modalListEventsLoading('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), function (id) {
            $('#obj_ciudad_id').val(id).change();
            $('#obj_ciudad_desc').text(decodeURIComponent(oCiudadModel.getMeAsAForeignKey(id)));
            $('#modal01').modal('hide');

        },oCiudadModel, oCiudadView);
        return false;
    });
    
    $('#publicacionForm #obj_tipopublicacion_button').unbind('click');
    $("#publicacionForm #obj_tipopublicacion_button").click(function () {
        var oControl = oTipopublicacionControl;  //para probar dejar publicacion
        //vista('publicacion').cargaModalBuscarClaveAjena('#modal01', "publicacion");

        $("#publicacionForm").append(thisObject.getEmptyModal());
        util().loadForm('#modal01', thisObject.getFormHeader('Elección de Tipo de Publicacion'), "", thisObject.getFormFooter(), true);

        $('#publicacionForm').append(thisObject.getEmptyModal());

        oControl.list('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), true, oTipopublicacionModel, oTipopublicacionView);
        oControl.modalListEventsLoading('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), function (id) {
            $('#obj_tipopublicacion_id').val(id).change();
            $('#obj_tipopublicacion_desc').text(decodeURIComponent(oTipopublicacionModel.getMeAsAForeignKey(id)));
            $('#modal01').modal('hide');

        },oTipopublicacionModel, oTipopublicacionView);
        return false;
    });
};

publicacionView.prototype.okValidation = function (f) {
    $('#publicacionForm').on('success.form.bv', f);
};

publicacionView.prototype.getEventos = function (jason, person, pubs, coments, friends, tipo) {
    
    espacio = ' ';
    comilla = "'";
    salto = "<br />";
    apertura = "<";
    cierre = ">";
    barra = "/";
    alm = "#";
    
    if(tipo == "inicio"){
        var jsonP = jason.data.page.list;
        
        /* H1 INTRO */
        if (person.genero == "M"){
            eventos = '<h1 class="col-md-12 col-xs-12 title_intro">Bienvenido @' + person.login + '</h1>';
        } else if (person.genero == "F"){
            eventos = '<h1 class="col-md-12 col-xs-12 title_intro">Bienvenida @' + person.login + '</h1>';
        } else {
            eventos = '<h1 class="col-md-12 col-xs-12 title_intro">Bienvenido/a @' + person.login + '</h1>';
        }
        
        /* PERFIL INTRO */
        eventos += "<div class=" + "'col-md-4 col-xs-12 eventos_main'" + ">";
        eventos += "<div class=" + "'col-md-12 col-xs-12 eventos_main_content'" + ">";
            eventos += "<div class=" + comilla + "perfil_photo col-md-12 col-xs-4" + comilla + ">";
                eventos += "<a href=" + comilla + "#/perfil/" + person.id + comilla + ">";
                if(person.imagen != null){
                    eventos += person.imagen;
                }else {
                    eventos += "<img src=" + comilla + "/images/user.png" + comilla + "class=" + comilla + "foto_perfil" + comilla + " alt=" + comilla + "Foto perdil de " + person.nombre + espacio + person.apellidos + comilla + ">";
                }
                eventos += "</a>";
            eventos += "</div>";
            eventos += "<div class=" + comilla + "col-md-12 col-xs-8 perfil_home" + comilla + ">";
                eventos += "<a href=" + comilla + "#/perfil/" + person.id + comilla + ">";
                    eventos += '<h2 class="nombre_home">'+ person.nombre + " " + person.apellidos + '</h2>';
                eventos += "</a>";
                eventos += '<span class="login_home">@'+ person.login + '</span><br/>';
            eventos += "</div>";    
            eventos += "<div class=" + comilla + "col-md-12 col-xs-12 datos_home" + comilla + ">";
                eventos += "<div class=" + comilla + "col-md-4 col-xs-4 events_home" + comilla + ">";
                    eventos += '<h5 class="text_number">Eventos<br/>';
                    eventos += '<b>'+ pubs.data + '</b></h5>';
                eventos += "</div>";   
                
                eventos += "<div class=" + comilla + "col-md-4 col-xs-4 events_home" + comilla + ">";
                    eventos += '<h5 class="text_number">Comentarios<br/>';
                    eventos += '<b>'+ coments.data + '</b></h5>';
                eventos += "</div>"; 
                
                eventos += "<div class=" + comilla + "col-md-4 col-xs-4 events_home" + comilla + ">";
                    eventos += '<h5 class="text_number">Siguiendo<br/>';
                    eventos += '<b>'+ friends.data + '</b></h5>';
                eventos += "</div>"; 
                
            eventos += "</div>";
        eventos += "</div>";
        eventos += "</div>";
        
        /* EVENTOS */
        eventos += "<div class=" + "'col-md-8 col-xs-12 eventos_main'" + ">";
        eventos += "<h2 class=" + comilla + "col-md-12 inicioh2" + comilla +">Eventos de tu entorno</h2>";
        eventos += "<div class=" + "'col-md-12 col-sm-12 inicioMain'" + "id=" + "'eventosgp'" + ">";
    }else{
        jsonP = jason.list;
        
        eventos = "<div class=" + "'row  eventos_default'" + ">";
        eventos += "<h2 class=" + comilla + "col-md-12 inicioh2" + comilla +">Eventos en " + jsonP[0].obj_ciudad.nombre + "</h2>";
        eventos += "<div class=" + "'col-md-12 col-sm-12 inicioMain'" + "id=" + "'eventosgp'" + ">";
    }
    
    if (jsonP.length != 0) {
        for (i = 0; i < jsonP.length; i++) {
            eventos += "<div class=" + comilla + "col-md-6 evento evento" + jsonP[i].id + comilla + ">";
            eventos += "<div class=" + comilla + "contentpub" + comilla + ">";
            eventos += "<h4><a href=" + comilla + "#/evento/" + jsonP[i].id + comilla + ">" + jsonP[i].titulo + "</a></h4>";
            eventos += "<div class=" + comilla + "usuariopub" + comilla + ">";
            eventos += "<a href=" + comilla + "#/perfil/" + jsonP[i].obj_usuario.id + comilla + ">" + jsonP[i].obj_usuario.nombre + espacio + jsonP[i].obj_usuario.apellidos + "</a>";
            eventos += "<span class=" + comilla + "nick" + comilla + "> @" + jsonP[i].obj_usuario.login + "</span>";
            eventos += "<p class=" + comilla + "ciudadpub" + comilla + ">" + jsonP[i].obj_ciudad.nombre + "-" + jsonP[i].fechapub + "</p>";
            eventos += "</div></div></div>";
        }
    } else {
        eventos += "<div class=" + comilla + "col-md-12 evento evento" + comilla + ">";
            eventos += "<div class=" + comilla + "contentpub" + comilla + ">";
                eventos += "<h4>No tienes eventos en tu entorno!</h4>";
                eventos += "<span>Busca a tu amigos, sigue sus eventos, crea tus propios eventos y comentarios y disfruta de GOPLACE!</span>";
            eventos += "</div>";
        eventos += "</div>";
    }
    eventos += "</div>";
    eventos += "</div>";

    return eventos;
};

publicacionView.prototype.getComentarios = function (coments) {
    
    espacio = ' ';
    comilla = "'";
    salto = "<br />";
    apertura = "<";
    cierre = ">";
    barra = "/";
    alm = "#";
    
    var jsonP = coments.list;
    
    comentarios = "<div class=" + comilla + "col-md-12 col-sm-12 comentarios" + comilla + ">";
    comentarios += "<h5>" + "Comentarios ( "+ jsonP.length +" )</h5>";
    
    if (jsonP.length != 0) {
        for (i = 0; i < jsonP.length; i++) {
            comentarios += "<div class=" + comilla + "col-md-12 col-sm-12 coment" + comilla + ">";
                comentarios += "<div class=" + comilla + "col-md-2 coment_photo" + comilla + ">";
                    if (jsonP[i].obj_usuario.imagen == null){
                        comentarios += "<img src=" + comilla + "/images/user.png" + comilla + " alt=" + comilla + "Foto usuario " + i + comilla +"/>";
                    }else{
                        comentarios += jsonP[i].obj_usuario.imagen;
                    }
                    
                comentarios += "</div>";
                comentarios += "<div class=" + comilla + "col-md-10 coment_desc" + comilla + ">";
                    comentarios += "<a href=" + comilla + "#/perfil/" + jsonP[i].obj_usuario.id + comilla + ">" + jsonP[i].obj_usuario.nombre + espacio + jsonP[i].obj_usuario.apellidos + "</a><br/>";
                    comentarios += "<span class=" + comilla + "nick" + comilla + "> @" + jsonP[i].obj_usuario.login + "</span> - ";
                    comentarios += "<span class=" + comilla + "fecha" + comilla + ">" + jsonP[i].fechacomentario + "</span><br/>";
                    comentarios += "<p>" + jsonP[i].coment + "</p>";
                comentarios += "</div>";
            comentarios += "</div>";
        }
    } else {
        comentarios += "<div class=" + comilla + "col-md-12 col-sm-12 coment" + comilla + ">";
            comentarios += "<div class=" + comilla + "col-md-2 coment_photo" + comilla + ">";
                comentarios += "<img src=" + comilla + "/images/user_admin.png" + comilla + " alt=" + comilla + "Foto usuario admin" + comilla + ">";
            comentarios += "</div>";
            comentarios += "<div class=" + comilla + "col-md-10 coment_desc" + comilla + ">";
                comentarios += "<b>" + "Administrador" + "</b>";
                comentarios += "<span class=" + comilla + "nick" + comilla + "> @admin </span>";
                comentarios += "<h4>Este evento no tiene comentarios</h4>";
                comentarios += "<p>Haz tu su primer comentario!</p>";
            comentarios += "</div>";
        comentarios += "</div>";
    }
    comentarios += "</div>";
    return comentarios;
};

publicacionView.prototype.perfilEvento = function (eventos, usuarios, coments, form) {
    
    espacio = ' ';
    comilla = "'";
    salto = "<br />";
    apertura = "<";
    cierre = ">";
    barra = "/";
    alm = "#";
    
    var evento = eventos.list[0];
    var usuario = usuarios.list;
    
    descEvent = "<div class=" + "'perfilEvento'" + "id=" + "'perfilEvento'" + ">";
        descEvent += "<div class=" + comilla + "row" + comilla + ">";
            descEvent += "<h2 class=" + comilla + "title_evento col-md-12 col-xs-12" + comilla + ">" + evento.titulo + "</h2>";
        descEvent += "</div>";
        
        descEvent += "<div class=" + comilla + "row evento_descripcion" + comilla + ">";
            descEvent += "<div class=" + comilla + "col-md-6 col-xs-12 evento_info" + comilla + ">";
                
                /* DESCRIPCION */
                descEvent += "<div class=" + comilla + "col-md-12 col-sm-6 col-xs-12 caracteristicas_evento " + comilla + ">";
                    descEvent += "<div class=" + comilla + "col-md-12 col-xs-12 title_h3_event" + comilla + ">";
                        descEvent += "<h3>" + "Informacion del evento" +"</h3>";
                    descEvent += "</div>";
                    descEvent += "<p>" + evento.descripcion +"</p>";
                    descEvent += "<b>Creado por: </b><a href=" + comilla + "#/perfil/" + evento.obj_usuario.id + comilla + ">" + evento.obj_usuario.nombre + " " + evento.obj_usuario.apellidos + "</a><br/>";
                    descEvent += "<b>Ciudad: </b>" + evento.obj_ciudad.nombre + "<br/>";
                    descEvent += "<b>Dirección: </b>" + evento.direccion + "<br/>";
                    descEvent += "<b>Fecha del Evento: </b>" + evento.fechapub + "<br/>";
                descEvent += "</div>";
                descEvent += "<div class=" + comilla + "col-md-12 col-sm-6 col-xs-12" + comilla + " id=" + comilla + "botones_evento" + comilla + ">";
                descEvent += "</div>";
                
                /* USUARIOS */
                descEvent += "<div class=" + comilla + "col-md-12 col-sm-6 col-xs-12 personas_evento" + comilla + ">";
                    descEvent += "<div class=" + comilla + "col-md-12 col-xs-12 title_h3_event" + comilla + ">";
                        descEvent += "<h3>" + "Usuarios que asistiran" +"</h3>";
                    descEvent += "</div>";
                    descEvent += "<div class=" + comilla + "users col-md-12 col-xs-12" + comilla + ">";
                    
                        if (usuario.length != 0) {
                            for (i = 0; i < usuario.length; i++) {
                                descEvent += "<div class=" + comilla + "user_event col-md-3 col-xs-3" + comilla + ">";
                                    descEvent += "<a href=" + comilla + "#/perfil/" + usuario[i].obj_usuario.id + comilla + ">";
                                    if (usuario[i].obj_usuario.imagen == null){
                                        descEvent += "<img src=" + comilla + "/images/user.png" + comilla + " class=" + comilla + "fotoUser" + comilla + "/>";
                                    }else{
                                        descEvent += usuario[i].obj_usuario.imagen;
                                    }
                                    descEvent += "</a>";
                                descEvent += "</div>";
                            }
                        } else {
                            descEvent += "<h5>" + "Ningún usuario sigue este evento" +"</h5>";
                        }
                    descEvent += "</div>";
                    
                descEvent += "</div>";
                
            descEvent += "</div>";
            
            /* MAPS */
            descEvent += "<div class=" + comilla + "col-md-6 col-xs-12 evento_maps" + comilla + ">";
                descEvent += "<div class=" + comilla + "col-md-12 col-xs-12 title_h3_event" + comilla + ">";
                    descEvent += "<h3>" + "Localizacion" +"</h3>";
                descEvent += "</div>";
                descEvent += "<div class=" + comilla + "col-md-12 col-xs-12 evento_info img_maps" + comilla + ">";
                    descEvent += "<iframe src=" + comilla + "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d197294.23575548557!2d-0.36151129999999165!3d39.40778525!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd604f4cf0efb06f%3A0xb4a351011f7f1d39!2sValencia!5e0!3m2!1ses!2ses!4v1433004088943" + comilla + " width=" + comilla + "400" + comilla + " height=" + comilla + "300" + comilla + " frameborder=" + comilla + "0" + comilla + " style=" + comilla + "border:0" + comilla + "></iframe>";
                descEvent += "</div>";
            descEvent += "</div>";
            
        descEvent += "</div>";
        
        /* COMENTARIOS */
        descEvent += "<div class=" + comilla + "row evento_comentarios" + comilla + ">";
            descEvent += "<div class=" + comilla + "col-md-12 col-xs-12 coments_form" + comilla + " id=" + comilla + "coments_form" + comilla +"></div>";
            descEvent += oPublicacionView.getComentarios(coments);
        descEvent += "</div>";
        
    descEvent += "</div>";

    return descEvent;
};