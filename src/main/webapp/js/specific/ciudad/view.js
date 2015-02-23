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


var ciudadView = function (strClase) {
    this.clase = strClase;
};
ciudadView.prototype = new view('ciudad');
ciudadView.prototype.getClassNameCiudad = function () {
    return this.getClassName() + "Vista";
};
var oCiudadView = new ciudadView('ciudad');


ciudadView.prototype.loadButtons = function (id) {

    var botonera = "";
    botonera += '<div class="btn-toolbar" role="toolbar"><div class="btn-group btn-group-xs">';
    botonera += '<a class="btn btn-default view" id="' + id + '"  href="control#/' + this.clase + '/view/' + id + '"><i class="glyphicon glyphicon-eye-open"></i></a>';
    botonera += '<a class="btn btn-default edit" id="' + id + '"  href="control#/' + this.clase + '/edit/' + id + '"><i class="glyphicon glyphicon-pencil"></i></a>';
    botonera += '<a class="btn btn-default remove" id="' + id + '"  href="control#/' + this.clase + '/remove/' + id + '"><i class="glyphicon glyphicon-remove"></i></a>';
    //botonera += 
    botonera += '</div></div>';
    return botonera;

}
ciudadView.prototype.loadFormValues = function (valores, campos) {
//                    $('#ciudad_form #titulo').val(valores['titulo']);
//                    $('#ciudad_form #contenido').val(valores['contenido']);
//                    $('#ciudad_form #alta').val(valores['alta']);
//                    $('#ciudad_form #cambio').val(valores['cambio']);
//                    $('#ciudad_form #hits').val(valores['hits']);
//                    $('#ciudad_form #id_usuario').val(valores['id_usuario']);
//                    $('#ciudad_form #etiquetas').val(valores['etiquetas']);
//                    $('#ciudad_form #publicado').val(valores['publicado']);
//                    $('#ciudad_form #portada').val(valores['portada']);
    this.doFillForm(valores, campos);
};

ciudadView.prototype.getFormValues = function () {
    var valores = [];
//                    valores['titulo'] = $('#ciudad_form #titulo');
//                    valores['contenido'] = $('#ciudad_form #contenido');
//                    valores['alta'] = $('#ciudad_form #alta');
//                    valores['cambio'] = $('#ciudad_form #cambio');
//                    valores['hits'] = $('#ciudad_form #hits');
//                    valores['id_usuario'] = $('#ciudad_form #id_usuario');
//                    valores['etiquetas'] = $('#ciudad_form #etiquetas');
//                    valores['publicado'] = $('#ciudad_form #publicado');
//                    valores['portada'] = $('#ciudad_form #portada');

    var disabled = $('#ciudadForm').find(':input:disabled').removeAttr('disabled');
    valores = $('#ciudadForm').serializeObject();
    disabled.attr('disabled', 'disabled');
    return valores;
};

ciudadView.prototype.doEventsLoading = function () {
    var thisObject = this;
    $('#ciudadForm #obj_pregunta_button').unbind('click');
    $("#ciudadForm #obj_pregunta_button").click(function () {
        var oControl = oPreguntaControl;  //para probar dejar ciudad
        //vista('usuario').cargaModalBuscarClaveAjena('#modal01', "ciudad");

        
        //$("#ciudadForm").append(thisObject.getEmptyModal());
        
        
        
        util().loadForm('#modal01', thisObject.getFormHeader('Elección de pregunta'), "", thisObject.getFormFooter(), true);

        $('#ciudadForm').append(thisObject.getEmptyModal());

        oControl.list('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), true, oPreguntaModel, oPreguntaView);
        oControl.modalListEventsLoading('#modal01 #modal-body', param().defaultizeUrlObjectParameters({}), function (id) {
            $('#obj_pregunta_id').val(id).change();
            $('#obj_pregunta_desc').text(decodeURIComponent(oPreguntaModel.getMeAsAForeignKey(id)));
            $('#modal01').modal('hide');

        },oPreguntaModel, oPreguntaView);
        return false;
    });
};

ciudadView.prototype.okValidation = function (f) {
    $('#ciudadForm').on('success.form.bv', f);
};