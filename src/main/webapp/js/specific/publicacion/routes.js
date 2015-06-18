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


function fPublicacionRoutes() {

//    Path.map("#/publicacion").to(function () {
//        $('#indexContenidoJsp').spinner();
//        control('publicacion').list($('#indexContenido'), param().defaultizeUrlObjectParameters({}), null);
//        //publicacionControl.modalListEventsLoading(publicacionObject, publicacionView, $('#indexContenido'), param().defaultizeUrlObjectParameters({}), null);        
//        $('#indexContenidoJsp').empty();
//        return false;
//    });

    Path.map("#/publicacion").to(function () {
        $('#indexContenidoJsp').spinner();
        oPublicacionControl.list($('#indexContenido'), param().defaultizeUrlObjectParameters({}), null, oPublicacionModel, oPublicacionView);
        //publicacionControl.modalListEventsLoading(publicacionObject, publicacionView, $('#indexContenido'), param().defaultizeUrlObjectParameters({}), null);        
        $('#indexContenidoJsp').empty();
        //$('#indexContenidoJsp').append(oPublicacionControl.getClassNamePublicacion());
        return false;
    });

    Path.map("#/publicacion/edit/:id").to(function () {
        //$('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['id']));
        oPublicacionControl.edit($('#principalpag'), paramsObject['id'], oPublicacionModel, oPublicacionView, "#/evento/");
        //$('#indexContenidoJsp').empty();
    });
    Path.map("#/publicacion/new").to(function () {
        //$('#indexContenidoJsp').spinner();
        //var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oPublicacionControl.new($('#principalpag'), null, oPublicacionModel, oPublicacionView, "#/evento/");
        //$('#indexContenidoJsp').empty();
        return false;
    });
    Path.map("#/publicacion/new/:url").to(function () {
        //$('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oPublicacionControl.new($('#principalpag'), paramsObject, oPublicacionModel, oPublicacionView, "#/evento/");
        //$('#indexContenidoJsp').empty();
        return false;
    });

    Path.map("#/publicacion/remove/:id").to(function () {
        //$('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['url']));
        oPublicacionControl.remove($('#principalpag'), paramsObject['id'], oPublicacionModel, oPublicacionView);
        //$('#indexContenidoJsp').empty();
        return false;
    });
    
    Path.map("#/inicio").to(function () {
    oPublicacionControl.listarEventos($('#principalpag'), oPublicacionModel, oPublicacionView);
    return false;
    });
    
    Path.map("#/evento/:id").to(function () {
        $('#principalpag').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['id']));
        oPublicacionControl.verEvento($('#principalpag'), paramsObject['id'], oPublicacionModel, oPublicacionView);
        oComentarioControl.new($('#coments_form'), paramsObject['id'], oComentarioModel, oComentarioView, null);
 
        return false;
    });
    
    Path.map("#/eventos/usuario/:id").to(function () {
        $('#principalpag').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['id']));
        oPublicacionControl.eventosUsuario($('#principalpag'), paramsObject['id'], oPublicacionModel, oPublicacionView);
        oComentarioControl.new($('#coments_form'), paramsObject['id'], oComentarioModel, oComentarioView, null);
 
        return false;
    });
    
    Path.map("#/descubre/:id").to(function () {
        $('#indexContenidoJsp').spinner();
        var paramsObject = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['id']));
        oPublicacionControl.listarEventosFilter($('#principalpag'), paramsObject['id'], oPublicacionModel, oPublicacionView, "ciudad");
        
        return false;
    });
    
    Path.map("#/evento/:id_evento/coment/delete/:id").to(function () {
        //$('#indexContenidoJsp').spinner();
        var idComentario = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['id']));
        var idEvento = param().defaultizeUrlObjectParameters(param().getUrlObjectFromUrlString(this.params['id_evento']));
        oPublicacionControl.eliminarComentario($('#principalpag'), idComentario['id'], idEvento["id"], oPublicacionModel, oPublicacionView);

        return false;
    });
}