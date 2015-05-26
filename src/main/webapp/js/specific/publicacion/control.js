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

var publicacionControl = function (strClase) {
    this.clase = strClase;
};
publicacionControl.prototype = new control('publicacion');
publicacionControl.prototype.getClassNamePublicacion = function () {
    return this.getClassName() + "Control";
};
var oPublicacionControl = new publicacionControl('publicacion');

publicacionControl.prototype.listarEventos = function (place, oModel, oView) {
    var thisObject = this;
    $(place).empty();
    var oPublicacionModel = oModel;
    var oPublicacionView = oView;
    var data = oPublicacionModel.setGenericOperation("geteventos&rpp=8","");
    var eventos = oPublicacionView.getEventos(data, "inicio");
    $("#principalpag").html(eventos);
};

publicacionControl.prototype.listarEventosFilter = function (place, id, oModel, oView, tipo) {
    var thisObject = this;
    $(place).empty();
    var oPublicacionModel = oModel;
    var oPublicacionView = oView;
    var data = oPublicacionModel.cargaEventos(id);
    var eventos = oPublicacionView.getEventos(data, tipo);
    $("#principalpag").html(eventos);
};

publicacionControl.prototype.listarComentarios = function (place, id_evento, oModel, oView) {
    var thisObject = this;
    $(place).empty();
    var oComentarioModel = oModel;
    var oPublicacionView = oView;
    var data = oComentarioModel.setGenericOperation("getcomentarios&id="+id_evento+"&rpp=100","");
    var eventos = oPublicacionView.getComentarios(data);
    $("#comentariospag").html(eventos);
};

