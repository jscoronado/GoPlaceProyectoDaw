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


var comentarioView = function (strClase) {
    this.clase = strClase;
};
comentarioView.prototype = new view('comentario');
comentarioView.prototype.getClassNameComentario = function () {
    return this.getClassName() + "Vista";
};
var oComentarioView = new comentarioView('comentario');


comentarioView.prototype.loadButtons = function (id) {

    var botonera = "";
    botonera += '<div class="btn-toolbar" role="toolbar"><div class="btn-group btn-group-xs">';
    botonera += '<a class="btn btn-default view" id="' + id + '"  href="control#/' + this.clase + '/view/' + id + '"><i class="glyphicon glyphicon-eye-open"></i></a>';
    botonera += '<a class="btn btn-default edit" id="' + id + '"  href="control#/' + this.clase + '/edit/' + id + '"><i class="glyphicon glyphicon-pencil"></i></a>';
    botonera += '<a class="btn btn-default remove" id="' + id + '"  href="control#/' + this.clase + '/remove/' + id + '"><i class="glyphicon glyphicon-remove"></i></a>';
    //botonera += 
    botonera += '</div></div>';
    return botonera;

}
comentarioView.prototype.loadFormValues = function (valores, campos) {
//                    $('#comentario_form #titulo').val(valores['titulo']);
//                    $('#comentario_form #contenido').val(valores['contenido']);
//                    $('#comentario_form #alta').val(valores['alta']);
//                    $('#comentario_form #cambio').val(valores['cambio']);
//                    $('#comentario_form #hits').val(valores['hits']);
//                    $('#comentario_form #id_usuario').val(valores['id_usuario']);
//                    $('#comentario_form #etiquetas').val(valores['etiquetas']);
//                    $('#comentario_form #publicado').val(valores['publicado']);
//                    $('#comentario_form #portada').val(valores['portada']);
    this.doFillForm(valores, campos);
};

comentarioView.prototype.getFormValues = function () {
    var valores = [];
//                    valores['titulo'] = $('#comentario_form #titulo');
//                    valores['contenido'] = $('#comentario_form #contenido');
//                    valores['alta'] = $('#comentario_form #alta');
//                    valores['cambio'] = $('#comentario_form #cambio');
//                    valores['hits'] = $('#comentario_form #hits');
//                    valores['id_usuario'] = $('#comentario_form #id_usuario');
//                    valores['etiquetas'] = $('#comentario_form #etiquetas');
//                    valores['publicado'] = $('#comentario_form #publicado');
//                    valores['portada'] = $('#comentario_form #portada');

    var disabled = $('#comentarioForm').find(':input:disabled').removeAttr('disabled');
    valores = $('#comentarioForm').serializeObject();
    disabled.attr('disabled', 'disabled');
    return valores;
};

comentarioView.prototype.getPanel = function (contenido) {
    return '<div class="col-md-12 form_comentario">' + contenido + '</div></div>';
};

comentarioView.prototype.doEventsLoading = function () {
    var thisObject = this;
    
    $('#comentarioForm #obj_usuario_button').unbind('click');
    $("#comentarioForm #obj_usuario_button").click(function () {
        var oControl = oUsuarioControl;  //para probar dejar comentario
        //vista('comentario').cargaModalBuscarClaveAjena('#modal01', "comentario");

        $("#comentarioForm").append(thisObject.getEmptyModal());
        util().loadForm('#modal01', thisObject.getFormHeader('Elección de usuario'), "", thisObject.getFormFooter(), true);

        $('#comentarioForm').append(thisObject.getEmptyModal());

        oControl.list('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), true, oUsuarioModel, oUsuarioView);
        oControl.modalListEventsLoading('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), function (id) {
            $('#obj_usuario_id').val(id).change();
            $('#obj_usuario_desc').text(decodeURIComponent(oUsuarioModel.getMeAsAForeignKey(id)));
            $('#modal01').modal('hide');

        },oUsuarioModel, oUsuarioView);
        return false;
    });
    
    $('#comentarioForm #obj_publicacion_button').unbind('click');
    $("#comentarioForm #obj_publicacion_button").click(function () {
        var oControl = oPublicacionControl;  //para probar dejar comentario
        //vista('usuario').cargaModalBuscarClaveAjena('#modal01', "comentario");

        $("#comentarioForm").append(thisObject.getEmptyModal());
        util().loadForm('#modal01', thisObject.getFormHeader('Elección de publicacion'), "", thisObject.getFormFooter(), true);

        $('#comentarioForm').append(thisObject.getEmptyModal());

        oControl.list('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), true, oPublicacionModel, oPublicacionView);
        oControl.modalListEventsLoading('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), function (id) {
            $('#obj_publicacion_id').val(id).change();
            $('#obj_publicacion_desc').text(decodeURIComponent(oPublicacionModel.getMeAsAForeignKey(id)));
            $('#modal01').modal('hide');

        },oPublicacionModel, oPublicacionView);
        return false;
    });
};

comentarioView.prototype.okValidation = function (f) {
    $('#comentarioForm').on('success.form.bv', f);
};