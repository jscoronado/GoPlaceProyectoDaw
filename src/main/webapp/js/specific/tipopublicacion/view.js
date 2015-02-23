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


var tipopublicacionView = function (strClase) {
    this.clase = strClase;
};
tipopublicacionView.prototype = new view('tipopublicacion');
tipopublicacionView.prototype.getClassNameTipopublicacion = function () {
    return this.getClassName() + "Vista";
};
var oTipopublicacionView = new tipopublicacionView('tipopublicacion');


tipopublicacionView.prototype.loadButtons = function (id) {

    var botonera = "";
    botonera += '<div class="btn-toolbar" role="toolbar"><div class="btn-group btn-group-xs">';
    botonera += '<a class="btn btn-default view" id="' + id + '"  href="control#/' + this.clase + '/view/' + id + '"><i class="glyphicon glyphicon-eye-open"></i></a>';
    botonera += '<a class="btn btn-default edit" id="' + id + '"  href="control#/' + this.clase + '/edit/' + id + '"><i class="glyphicon glyphicon-pencil"></i></a>';
    botonera += '<a class="btn btn-default remove" id="' + id + '"  href="control#/' + this.clase + '/remove/' + id + '"><i class="glyphicon glyphicon-remove"></i></a>';
    //botonera += 
    botonera += '</div></div>';
    return botonera;

}
tipopublicacionView.prototype.loadFormValues = function (valores, campos) {
//                    $('#tipopublicacion_form #titulo').val(valores['titulo']);
//                    $('#tipopublicacion_form #contenido').val(valores['contenido']);
//                    $('#tipopublicacion_form #alta').val(valores['alta']);
//                    $('#tipopublicacion_form #cambio').val(valores['cambio']);
//                    $('#tipopublicacion_form #hits').val(valores['hits']);
//                    $('#tipopublicacion_form #id_usuario').val(valores['id_usuario']);
//                    $('#tipopublicacion_form #etiquetas').val(valores['etiquetas']);
//                    $('#tipopublicacion_form #publicado').val(valores['publicado']);
//                    $('#tipopublicacion_form #portada').val(valores['portada']);
    this.doFillForm(valores, campos);
};

tipopublicacionView.prototype.getFormValues = function () {
    var valores = [];
//                    valores['titulo'] = $('#tipopublicacion_form #titulo');
//                    valores['contenido'] = $('#tipopublicacion_form #contenido');
//                    valores['alta'] = $('#tipopublicacion_form #alta');
//                    valores['cambio'] = $('#tipopublicacion_form #cambio');
//                    valores['hits'] = $('#tipopublicacion_form #hits');
//                    valores['id_usuario'] = $('#tipopublicacion_form #id_usuario');
//                    valores['etiquetas'] = $('#tipopublicacion_form #etiquetas');
//                    valores['publicado'] = $('#tipopublicacion_form #publicado');
//                    valores['portada'] = $('#tipopublicacion_form #portada');

    var disabled = $('#tipopublicacionForm').find(':input:disabled').removeAttr('disabled');
    valores = $('#tipopublicacionForm').serializeObject();
    disabled.attr('disabled', 'disabled');
    return valores;
};

tipopublicacionView.prototype.doEventsLoading = function () {
    var thisObject = this;
    $('#tipopublicacionForm #obj_tipopublicacion_button').unbind('click');
    $("#tipopublicacionForm #obj_tipopublicacion_button").click(function () {
        var oControl = oTipopublicacionControl;  //para probar dejar tipopublicacion
        //vista('tipopublicacion').cargaModalBuscarClaveAjena('#modal01', "tipopublicacion");

        $("#tipopublicacionForm").append(thisObject.getEmptyModal());
        util().loadForm('#modal01', thisObject.getFormHeader('Elección de usuario'), "", thisObject.getFormFooter(), true);

        $('#tipopublicacionForm').append(thisObject.getEmptyModal());

        oControl.list('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), true, oTipopublicacionModel, oTipopublicacionView);
        oControl.modalListEventsLoading('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), function (id) {
            $('#obj_tipopublicacion_id').val(id).change();
            $('#obj_tipopublicacion_desc').text(decodeURIComponent(oTipopublicacionModel.getMeAsAForeignKey(id)));
            $('#modal01').modal('hide');

        },oTipopublicacionModel, oTipopublicacionView);
        return false;
    });
};

tipopublicacionView.prototype.okValidation = function (f) {
    $('#tipopublicacionForm').on('success.form.bv', f);
};