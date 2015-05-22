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


function fUsuarioRoutes() {

    Path.map("#/usuario").to(function () {
        $('#indexContenidoJsp').spinner();
        oUsuarioControl.list($('#principalpag'), param().defaultizeUrlObjectParameters({}), null, oUsuarioModel, oUsuarioView);
        //usuarioControl.modalListEventsLoading(usuarioObject, usuarioView, $('#principalpag'), param().defaultizeUrlObjectParameters({}), null);        
        $('#indexContenidoJsp').empty();
        //$('#indexContenidoJsp').append(oUsuarioControl.getClassNameUsuario());
        return false;
    });

    Path.map("#/usuario/list/:url").to(function () {
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oUsuarioControl.list($('#principalpag'), paramsObject, null, oUsuarioModel, oUsuarioView);
        return false;
    });

    Path.map("#/usuario/view/:id").to(function () {
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oUsuarioControl.view($('#principalpag'), paramsObject['id'], oUsuarioModel, oUsuarioView);

        return false;
    });
    
    Path.map("#/perfil/:id").to(function () {
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oUsuarioControl.perfil($('#principalpag'), paramsObject['id'], oUsuarioModel, oUsuarioView);

        return false;
    });

    Path.map("#/usuario/edit/:id").to(function () {
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oUsuarioControl.edit($('#principalpag'), paramsObject['id'], oUsuarioModel, oUsuarioView);
    });

    /*
    Path.map("#/usuario/new").to(function () {
        oUsuarioControl.new($('#indexContenido'), null, oUsuarioModel, oUsuarioView);
        return false;
    });

    Path.map("#/usuario/new/:url").to(function () {
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oUsuarioControl.new($('#indexContenido'), paramsObject, oUsuarioModel, oUsuarioView);
        return false;
    });

    Path.map("#/usuario/remove/:id").to(function () {
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oUsuarioControl.remove($('#principalpag'), paramsObject['id'], oUsuarioModel, oUsuarioView);
        return false;
    });
     */
}