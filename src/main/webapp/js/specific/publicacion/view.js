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

publicacionView.prototype.getComentarios = function (jason) {
    
    espacio = ' ';
    comilla = "'";
    salto = "<br />";
    apertura = "<";
    cierre = ">";
    barra = "/";
    alm = "#";
    
    comentarios = "<div class=" + "'row'" + ">";
    comentarios += "<div class=" + "'col-md-3 col-sm-3 perfilMain'" + ">";
    comentarios += "<div class=" + "'fotoPerfil'" + "><img src=" + "'http://localhost:8081/goplace/images/user.png'" + " class=" + "'foto'" + " alt=" + "'Foto usuario'" + "></div><br/>";
    comentarios += "<h3 class=" + "'nomargin'" + "><a href=" + "'#'" + "> Jose Miguel Coronado Aroca</a></h3>";
    comentarios += "</div><div class=" + "'col-md-9 col-sm-9 inicioMain'" + "id=" + "'comentariosgp'" + ">";
    jsonP = data.data.page.list;
    if (jsonP.length != 0) {
        for (i = 0; i < jsonP.length; i++) {
            comentarios += "<div class=" + comilla + "publicacion row" + comilla + ">";
            comentarios += "<div class=" + comilla + "col-md-1" + comilla + ">";
            comentarios += "<img src=" + comilla + "http://localhost:8081/goplace/images/user.png" + comilla + "class=" + comilla + "fotoPub" + comilla + " alt=" + comilla + "Foto usuario" + i + comilla + ">";
            comentarios += "</div>";
            comentarios += "<div class=" + comilla + "col-md-11" + comilla + ">";
            comentarios += "<a href=" + comilla + "#/usuario/view/" + jsonP[i].obj_usuario.id + comilla + ">" + jsonP[i].obj_usuario.nombre + espacio + jsonP[i].obj_usuario.apellidos + "</a>";
            comentarios += "<span class=" + comilla + "nick" + comilla + "> @" + jsonP[i].obj_usuario.login + "</span><br/>";
            comentarios += "<h4><a href=" + comilla + "#/publicacion/view/" + jsonP[i].id + comilla + ">" + jsonP[i].titulo + "</a></h4>";
            comentarios += "<span>" + jsonP[i].descripcion + "</span>";
            comentarios += "</div>";
            comentarios += "</div>";
        }
    } else {
        comentarios += "<div class=" + comilla + "publicacion row" + comilla + ">";
        comentarios += "<div class=" + comilla + "col-md-1" + comilla + ">";
        comentarios += "<img src=" + comilla + "<%=request.getContextPath()%>/images/foto.jpg" + comilla + "class=" + comilla + "fotoPub" + comilla + " alt=" + comilla + "Foto usuario admin" + comilla + ">";
        comentarios += "</div>";
        comentarios += "<div class=" + comilla + "col-md-11" + comilla + ">";
        comentarios += "<a href=" + comilla + "#/usuario/view/1" + comilla + ">" + "Administrador" + "</a>";
        comentarios += "<span class=" + comilla + "nick" + comilla + "> @admin" + "</span><br/>";
        comentarios += "<h4>Haz tu primer comentario!</h4>";
        comentarios += "<span>Comenta los planes con tus amigos</span>";
        comentarios += "</div>";
        comentarios += "</div>";
    }
    comentarios += "</div>";
    comentarios += "</div>";

    return comentarios;
};