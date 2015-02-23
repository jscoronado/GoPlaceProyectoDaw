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
        pie = '<a class="btn btn-primary" href="http://creoleparser.googlecode.com/svn/trunk/creoleparser/test_pages/CheatSheetPlus.html">Sintaxis</a>';
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
        resultadoMessage = "Error, el usuario con ID = " + id_usuario_2 + " usuario ya es tu amigo.";
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