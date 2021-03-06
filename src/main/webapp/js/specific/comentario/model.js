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

var comentarioModel = function (strClase) {
    this.clase = strClase;
};
comentarioModel.prototype = new model('comentario');
comentarioModel.prototype.getClassNameComentario = function () {
    return this.getClassName() + "Modelo";
};
var oComentarioModel = new comentarioModel('comentario');

comentarioModel.prototype.loadComentarios = function (id1) {
    
    $.when(ajax().ajaxCallSync(this.urlJson + '&op=getpage&systemfilter=id_usuario&systemfilteroperator=equals&systemfiltervalue=' + id1 + '&order=fechacomentario&ordervalue=desc', 'GET', '')).done(function (data) {
        pagina_objs = data;
    });
    
    return pagina_objs;
};

comentarioModel.prototype.getForm = function () {
    $.when(ajax().ajaxCallSync(path + '/control?ob=comentario&op=form&mode=1', 'GET', '')).done(function (data) {
        form = data;
    });
    return form;
};
