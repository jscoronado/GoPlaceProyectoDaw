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


function fAsistenciaRoutes() {

//    Path.map("#/asistencia").to(function () {
//        $('#indexContenidoJsp').spinner();
//        control('asistencia').list($('#principalpag'), param().defaultizeUrlObjectParameters({}), null);
//        //asistenciaControl.modalListEventsLoading(asistenciaObject, asistenciaView, $('#principalpag'), param().defaultizeUrlObjectParameters({}), null);        
//        $('#indexContenidoJsp').empty();
//        return false;
//    });


    Path.map("#/asistencia").to(function () {
        $('#indexContenidoJsp').spinner();
        oAsistenciaControl.list($('#principalpag'), param().defaultizeUrlObjectParameters({}), null, oAsistenciaModel, oAsistenciaView);
        //asistenciaControl.modalListEventsLoading(asistenciaObject, asistenciaView, $('#principalpag'), param().defaultizeUrlObjectParameters({}), null);        
        $('#indexContenidoJsp').empty();
        //$('#indexContenidoJsp').append(oAsistenciaControl.getClassNameAsistencia());
        return false;
    });

    Path.map("#/asistencia/list/:url").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oAsistenciaControl.list($('#principalpag'), paramsObject, null, oAsistenciaModel, oAsistenciaView);
        $('#indexContenidoJsp').empty();
        return false;
    });

    Path.map("#/asistencia/view/:id").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oAsistenciaControl.view($('#principalpag'), paramsObject['id'], oAsistenciaModel, oAsistenciaView);
        $('#indexContenidoJsp').empty();

        return false;
    });

    Path.map("#/asistencia/edit/:id").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oAsistenciaControl.edit($('#principalpag'), paramsObject['id'], oAsistenciaModel, oAsistenciaView, null);
        $('#indexContenidoJsp').empty();
    });
    Path.map("#/asistencia/new").to(function () {
        $('#indexContenidoJsp').spinner();
        //var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oAsistenciaControl.new($('#principalpag'), null, oAsistenciaModel, oAsistenciaView, null);
        $('#indexContenidoJsp').empty();
        return false;
    });
    Path.map("#/asistencia/new/:url").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oAsistenciaControl.new($('#principalpag'), paramsObject, oAsistenciaModel, oAsistenciaView, null);
        $('#indexContenidoJsp').empty();
        return false;
    });

    Path.map("#/asistencia/remove/:id").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oAsistenciaControl.remove($('#principalpag'), paramsObject['id'], oAsistenciaModel, oAsistenciaView);
        $('#indexContenidoJsp').empty();
        return false;
    });
}
