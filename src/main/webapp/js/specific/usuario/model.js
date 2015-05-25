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


var usuarioModel = function (strClase) {
    this.clase = strClase;
};
usuarioModel.prototype = new model('usuario');
usuarioModel.prototype.getClassNameUsuario = function () {
    return this.getClassName() + "Modelo";
};
var oUsuarioModel = new usuarioModel('usuario');

usuarioModel.prototype.getCachedPrettyFieldNames = function () {
    var prettyFieldNames = this.cPrettyFieldNames;
    var i = prettyFieldNames.indexOf("Password");    
    prettyFieldNames.splice(i, 1);
    
    return prettyFieldNames;
};

usuarioModel.prototype.getCachedFieldNames = function () {
    var fieldNames = this.cFieldNames;
    var i = fieldNames.indexOf("password");    
    fieldNames.splice(i, 1);
    
    return this.cFieldNames;
};

usuarioModel.prototype.agregarAmigo = function (id) {
    $.when(ajax().ajaxCallSync('/goplace/json?ob=amistad&op=agregaramigo&id=' + id, 'GET', '')).done(function (data) {
        feedback = data;
    });
    return feedback;
};
usuarioModel.prototype.removeAmigo = function (id) {
    $.when(ajax().ajaxCallSync('/goplace/json?ob=amistad&op=removeamigo&id=' + id, 'GET', '')).done(function (data) {
        feedback = data;                           
    });
    return feedback;
};
usuarioModel.prototype.existeAmigo = function (id) {
    $.when(ajax().ajaxCallSync('/goplace/json?ob=amistad&op=existeamigo&id=' + id, 'GET', '')).done(function (data) {
        feedback = data;                           
    });
    return feedback;
};
usuarioModel.prototype.getOne = function (id_usuario) {
    $.when(ajax().ajaxCallSync(this.urlJson + '&op=get&id_usuario=' + id_usuario, 'GET', '')).done(function (data) {
        one = data;
    });
    return one;
};

usuarioModel.prototype.loadPerfil = function (id1) {
    $.when(ajax().ajaxCallSync(this.urlJson + '&op=getaggregateviewone&id=' + id1, 'GET', '')).done(function (data) {
        pagina_objs = data;
    });
    
    return pagina_objs.data;
};

usuarioModel.prototype.loadEventos = function (id1) {
    $.when(ajax().ajaxCallSync('/goplace/json?ob=asistencia' + '&op=getpage&systemfilter=id_usuario&systemfilteroperator=equals&systemfiltervalue=' + id1, 'GET', '')).done(function (data) {
        pagina_objs = data;
    });
    
    return pagina_objs;
};
