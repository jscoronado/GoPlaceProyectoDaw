/* 
 * Copyright (C) 2014 rafa
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

var comentarioControl = function (strClase) {
    this.clase = strClase;
};
comentarioControl.prototype = new control('comentario');
comentarioControl.prototype.getClassNameComentario = function () {
    return this.getClassName() + "Control";
};
var oComentarioControl = new comentarioControl('comentario');

comentarioControl.prototype.new = function (place, id_evento, oModel, oView) {
    var thisObject = this;
    $(place).empty();
    $(place).append(oView.getPanel(oView.getEmptyForm()));
    //id must not be enabled
    $('#id').val('0').attr("disabled", true);
    $('#obj_publicacion_id').val(id_evento).attr("disabled", true);
    oView.doEventsLoading();
    $('#submitForm').unbind('click');
    $('#submitForm').click(function () {
        oView.okValidation(function (e) {
            titulo = "El comentario se ha publicado con éxito!";
            content = "Actualiza la pagina para verlo!";
            resultado = oModel.setOne({json: JSON.stringify(oView.getFormValues())});
            oView.doResultOperationGP(place, resultado["status"], titulo, content, id_evento, true, null);
            e.preventDefault();
            return false;
        });
    });
};
