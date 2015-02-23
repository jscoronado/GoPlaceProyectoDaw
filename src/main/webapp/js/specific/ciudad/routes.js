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


function fCiudadRoutes() {

//    Path.map("#/ciudad").to(function () {
//        $('#indexContenidoJsp').spinner();
//        control('ciudad').list($('#principalpag'), param().defaultizeUrlObjectParameters({}), null);
//        //ciudadControl.modalListEventsLoading(ciudadObject, ciudadView, $('#principalpag'), param().defaultizeUrlObjectParameters({}), null);        
//        $('#indexContenidoJsp').empty();
//        return false;
//    });

    Path.map("#/ciudad").to(function () {
        $('#indexContenidoJsp').spinner();
        oCiudadControl.list($('#principalpag'), param().defaultizeUrlObjectParameters({}), null, oCiudadModel, oCiudadView);
        //ciudadControl.modalListEventsLoading(ciudadObject, ciudadView, $('#principalpag'), param().defaultizeUrlObjectParameters({}), null);        
        $('#indexContenidoJsp').empty();
        //$('#indexContenidoJsp').append(oCiudadControl.getClassNameCiudad());
        return false;
    });

    Path.map("#/ciudad/list/:url").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oCiudadControl.list($('#principalpag'), paramsObject, null, oCiudadModel, oCiudadView);
        $('#indexContenidoJsp').empty();
        return false;
    });

    Path.map("#/ciudad/view/:id").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oCiudadControl.view($('#principalpag'), paramsObject['id'], oCiudadModel, oCiudadView);
        $('#indexContenidoJsp').empty();

        return false;
    });

    Path.map("#/ciudad/edit/:id").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oCiudadControl.edit($('#principalpag'), paramsObject['id'], oCiudadModel, oCiudadView);
        $('#indexContenidoJsp').empty();
    });

    Path.map("#/ciudad/new").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oCiudadControl.new($('#principalpag'), oCiudadModel, oCiudadView);
        $('#indexContenidoJsp').empty();
        return false;
    });

    Path.map("#/ciudad/remove/:id").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oCiudadControl.remove($('#principalpag'), paramsObject['id'], oCiudadModel, oCiudadView);
        $('#indexContenidoJsp').empty();
        return false;
    });
}