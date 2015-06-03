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

var publicacionModel = function (strClase) {
    this.clase = strClase;
};
publicacionModel.prototype = new model('publicacion');
publicacionModel.prototype.getClassNamePublicacion = function () {
    return this.getClassName() + "Modelo";
};
var oPublicacionModel = new publicacionModel('publicacion');

publicacionModel.prototype.cargaEventos = function (id1) {
    
    $.when(ajax().ajaxCallSync(this.urlJson + '&op=getpage&systemfilter=id_ciudad&systemfilteroperator=equals&systemfiltervalue='+id1+'&rpp=5&order=fechapub&ordervalue=asc', 'GET', '')).done(function (data) {
        pagina_objs = data;
    });
    
    return pagina_objs;
};

publicacionModel.prototype.getAdminEvento = function (id1) {
    
    $.when(ajax().ajaxCallSync(this.urlJson + '&op=getadminevento&id='+id1, 'GET', '')).done(function (data) {
        id_admin = data;
    });
    
    return id_admin;
};

publicacionModel.prototype.usuariosEventos = function (id1) {
    $.when(ajax().ajaxCallSync('/goplace/json?ob=asistencia' + '&op=getpage&systemfilter=id_publicacion&rpp=8&systemfilteroperator=equals&systemfiltervalue=' + id1, 'GET', '')).done(function (data) {
        pagina_objs = data;
    });
    
    return pagina_objs;
};

publicacionModel.prototype.seguirEvento = function (id) {
    $.when(ajax().ajaxCallSync('/goplace/json?ob=asistencia&op=seguirevento&id=' + id, 'GET', '')).done(function (data) {
        feedback = data;
    });
    return feedback;
};
publicacionModel.prototype.dejarEvento = function (id) {
    $.when(ajax().ajaxCallSync('/goplace/json?ob=asistencia&op=dejarevento&id=' + id, 'GET', '')).done(function (data) {
        feedback = data;                           
    });
    return feedback;
};
publicacionModel.prototype.existeAsistencia = function (id) {
    $.when(ajax().ajaxCallSync('/goplace/json?ob=asistencia&op=existeasistencia&id=' + id, 'GET', '')).done(function (data) {
        feedback = data;                           
    });
    return feedback;
};