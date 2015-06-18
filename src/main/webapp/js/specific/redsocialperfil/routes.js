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


function fRedsocialperfilRoutes() {

//    Path.map("#/redsocialperfil").to(function () {
//        $('#indexContenidoJsp').spinner();
//        control('redsocialperfil').list($('#principalpag'), param().defaultizeUrlObjectParameters({}), null);
//        //redsocialperfilControl.modalListEventsLoading(redsocialperfilObject, redsocialperfilView, $('#principalpag'), param().defaultizeUrlObjectParameters({}), null);        
//        $('#indexContenidoJsp').empty();
//        return false;
//    });

    Path.map("#/redsocialperfil").to(function () {
        $('#indexContenidoJsp').spinner();
        oRedsocialperfilControl.list($('#principalpag'), param().defaultizeUrlObjectParameters({}), null, oRedsocialperfilModel, oRedsocialperfilView);
        //redsocialperfilControl.modalListEventsLoading(redsocialperfilObject, redsocialperfilView, $('#principalpag'), param().defaultizeUrlObjectParameters({}), null);        
        $('#indexContenidoJsp').empty();
        $('#indexContenidoJsp').append(oRedsocialperfilControl.getClassNameRedsocialperfil());
        return false;
    });

    Path.map("#/redsocialperfil/list/:url").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oRedsocialperfilControl.list($('#principalpag'), paramsObject, null, oRedsocialperfilModel, oRedsocialperfilView);
        $('#indexContenidoJsp').empty();
        return false;
    });

    Path.map("#/redsocialperfil/view/:id").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oRedsocialperfilControl.view($('#principalpag'), paramsObject['id'], oRedsocialperfilModel, oRedsocialperfilView);
        $('#indexContenidoJsp').empty();

        return false;
    });

    Path.map("#/redsocialperfil/edit/:id").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oRedsocialperfilControl.edit($('#principalpag'), paramsObject['id'], oRedsocialperfilModel, oRedsocialperfilView, null);
        $('#indexContenidoJsp').empty();
    });
    Path.map("#/redsocialperfil/new").to(function () {
        $('#indexContenidoJsp').spinner();
        //var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oRedsocialperfilControl.new($('#principalpag'), null, oRedsocialperfilModel, oRedsocialperfilView, null);
        $('#indexContenidoJsp').empty();
        return false;
    });
    Path.map("#/redsocialperfil/new/:url").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oRedsocialperfilControl.new($('#principalpag'), paramsObject, oRedsocialperfilModel, oRedsocialperfilView, null);
        $('#indexContenidoJsp').empty();
        return false;
    });

    Path.map("#/redsocialperfil/remove/:id").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oRedsocialperfilControl.remove($('#principalpag'), paramsObject['id'], oRedsocialperfilModel, oRedsocialperfilView);
        $('#indexContenidoJsp').empty();
        return false;
    });

    Path.map("#/redsocialperfil/duplicate/:id").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oRedsocialperfilControl.duplicate($('#principalpag'), paramsObject['id'], oRedsocialperfilModel, oRedsocialperfilView);
        $('#indexContenidoJsp').empty();
        return false;
    });
}