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


function fTipopublicacionRoutes() {

//    Path.map("#/tipopublicacion").to(function () {
//        $('#indexContenidoJsp').spinner();
//        control('tipopublicacion').list($('#principalpag'), param().defaultizeUrlObjectParameters({}), null);
//        //tipopublicacionControl.modalListEventsLoading(tipopublicacionObject, tipopublicacionView, $('#principalpag'), param().defaultizeUrlObjectParameters({}), null);        
//        $('#indexContenidoJsp').empty();
//        return false;
//    });

    Path.map("#/tipopublicacion").to(function () {
        $('#indexContenidoJsp').spinner();
        oTipopublicacionControl.list($('#principalpag'), param().defaultizeUrlObjectParameters({}), null, oTipopublicacionModel, oTipopublicacionView);
        //tipopublicacionControl.modalListEventsLoading(tipopublicacionObject, tipopublicacionView, $('#principalpag'), param().defaultizeUrlObjectParameters({}), null);        
        $('#indexContenidoJsp').empty();
        //$('#indexContenidoJsp').append(oTipopublicacionControl.getClassNameTipopublicacion());
        return false;
    });

    Path.map("#/tipopublicacion/list/:url").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oTipopublicacionControl.list($('#principalpag'), paramsObject, null, oTipopublicacionModel, oTipopublicacionView);
        $('#indexContenidoJsp').empty();
        return false;
    });

    Path.map("#/tipopublicacion/view/:id").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oTipopublicacionControl.view($('#principalpag'), paramsObject['id'], oTipopublicacionModel, oTipopublicacionView);
        $('#indexContenidoJsp').empty();

        return false;
    });

    Path.map("#/tipopublicacion/edit/:id").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oTipopublicacionControl.edit($('#principalpag'), paramsObject['id'], oTipopublicacionModel, oTipopublicacionView);
        $('#indexContenidoJsp').empty();
    });

    Path.map("#/tipopublicacion/new").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oTipopublicacionControl.new($('#principalpag'), oTipopublicacionModel, oTipopublicacionView, null);
        $('#indexContenidoJsp').empty();
        return false;
    });

    Path.map("#/tipopublicacion/remove/:id").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oTipopublicacionControl.remove($('#principalpag'), paramsObject['id'], oTipopublicacionModel, oTipopublicacionView);
        $('#indexContenidoJsp').empty();
        return false;
    });
}