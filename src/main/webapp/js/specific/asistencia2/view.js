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


var asistenciaView = function (strClase) {
    this.clase = strClase;
};
asistenciaView.prototype = new view('asistencia');
asistenciaView.prototype.getClassNameAsistencia = function () {
    return this.getClassName() + "Vista";
};
var oAsistenciaView = new asistenciaView('asistencia');


asistenciaView.prototype.loadButtons = function (id) {

    var botonera = "";
    botonera += '<div class="btn-toolbar" role="toolbar"><div class="btn-group btn-group-xs">';
    botonera += '<a class="btn btn-default view" id="' + id + '"  href="control#/' + this.clase + '/view/' + id + '"><i class="glyphicon glyphicon-eye-open"></i></a>';
    botonera += '<a class="btn btn-default edit" id="' + id + '"  href="control#/' + this.clase + '/edit/' + id + '"><i class="glyphicon glyphicon-pencil"></i></a>';
    botonera += '<a class="btn btn-default remove" id="' + id + '"  href="control#/' + this.clase + '/remove/' + id + '"><i class="glyphicon glyphicon-remove"></i></a>';
    //botonera += 
    botonera += '</div></div>';
    return botonera;

}
asistenciaView.prototype.loadFormValues = function (valores, campos) {
//                    $('#asistencia_form #titulo').val(valores['titulo']);
//                    $('#asistencia_form #contenido').val(valores['contenido']);
//                    $('#asistencia_form #alta').val(valores['alta']);
//                    $('#asistencia_form #cambio').val(valores['cambio']);
//                    $('#asistencia_form #hits').val(valores['hits']);
//                    $('#asistencia_form #id_usuario').val(valores['id_usuario']);
//                    $('#asistencia_form #etiquetas').val(valores['etiquetas']);
//                    $('#asistencia_form #publicado').val(valores['publicado']);
//                    $('#asistencia_form #portada').val(valores['portada']);
    this.doFillForm(valores, campos);
};

asistenciaView.prototype.getFormValues = function () {
    var valores = [];
//                    valores['titulo'] = $('#asistencia_form #titulo');
//                    valores['contenido'] = $('#asistencia_form #contenido');
//                    valores['alta'] = $('#asistencia_form #alta');
//                    valores['cambio'] = $('#asistencia_form #cambio');
//                    valores['hits'] = $('#asistencia_form #hits');
//                    valores['id_usuario'] = $('#asistencia_form #id_usuario');
//                    valores['etiquetas'] = $('#asistencia_form #etiquetas');
//                    valores['publicado'] = $('#asistencia_form #publicado');
//                    valores['portada'] = $('#asistencia_form #portada');

    var disabled = $('#asistenciaForm').find(':input:disabled').removeAttr('disabled');
    valores = $('#asistenciaForm').serializeObject();
    disabled.attr('disabled', 'disabled');
    return valores;
};

asistenciaView.prototype.doEventsLoading = function () {
    var thisObject = this;
    $('#asistenciaForm #obj_usuario_button').unbind('click');
    $("#asistenciaForm #obj_usuario_button").click(function () {
        var oControl = oUsuarioControl;  //para probar dejar asistencia
        //vista('usuario').cargaModalBuscarClaveAjena('#modal01', "asistencia");

        
        //$("#asistenciaForm").append(thisObject.getEmptyModal());
        
        
        
        util().loadForm('#modal01', thisObject.getFormHeader('Elección de usuario'), "", thisObject.getFormFooter(), true);

        $('#asistenciaForm').append(thisObject.getEmptyModal());

        oControl.list('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), true, oUsuarioModel, oUsuarioView);
        oControl.modalListEventsLoading('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), function (id) {
            $('#obj_usuario_id').val(id).change();
            $('#obj_usuario_desc').text(decodeURIComponent(oUsuarioModel.getMeAsAForeignKey(id)));
            $('#modal01').modal('hide');

        },oUsuarioModel, oUsuarioView);
        return false;
    });
    
    $('#asistenciaForm #obj_publicacion_button').unbind('click');
    $("#asistenciaForm #obj_publicacion_button").click(function () {
        var oControl = oPublicacionControl;  //para probar dejar asistencia
        //vista('publicacion').cargaModalBuscarClaveAjena('#modal01', "asistencia");

        
        //$("#asistenciaForm").append(thisObject.getEmptyModal());
        
        
        
        util().loadForm('#modal01', thisObject.getFormHeader('Elección de publicacion'), "", thisObject.getFormFooter(), true);

        $('#asistenciaForm').append(thisObject.getEmptyModal());

        oControl.list('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), true, oPublicacionModel, oPublicacionView);
        oControl.modalListEventsLoading('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), function (id) {
            $('#obj_publicacion_id').val(id).change();
            $('#obj_publicacion_desc').text(decodeURIComponent(oPublicacionModel.getMeAsAForeignKey(id)));
            $('#modal01').modal('hide');

        },oPublicacionModel, oUsuarioView);
        return false;
    });
};

asistenciaView.prototype.okValidation = function (f) {
    $('#asistenciaForm').on('success.form.bv', f);
};