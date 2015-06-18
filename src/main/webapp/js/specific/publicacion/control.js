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

publicacionControl.prototype.verEvento = function (place, id_evento, oModel, oView) {
    var thisObject = this;
    $(place).empty();
    var oPublicacionModel = oModel;
    var oPublicacionView = oView;
    
    var evento = oPublicacionModel.setGenericOperation("getpage&systemfilter=id&systemfilteroperator=equals&systemfiltervalue="+id_evento,"");
    var usuarios = oPublicacionModel.usuariosEventos(id_evento);
    var coments = oComentarioModel.setGenericOperation("getpage&systemfilter=id_publicacion&systemfilteroperator=equals&systemfiltervalue="+id_evento+"&order=fechacomentario&ordervalue=desc","");
    var form = oComentarioModel.getForm();
    
    var eventos = oPublicacionView.perfilEvento(evento, usuarios, coments, form);
    $("#principalpag").html(eventos);
    
    
    /* BOTONERA EDITAR - AÑADIR/BORRAR */
    var adminEvento = oPublicacionModel.getAdminEvento(id_evento);
    
    var jsonAsistencia = oPublicacionModel.existeAsistencia(id_evento);
    var asist = jsonAsistencia.data;
    
   if (myuser == adminEvento.id) {
        $("#botones_evento").append('<a class="btn btn-goplace col-md-6 col-xs-6" href="control#/publicacion/edit/' + id_evento + '">Editar Evento</a>');
    } else {
        if(!asist){
            $("#botones_evento").append('<a class="btn btn-goplace col-md-6 col-xs-6" id=\"btn_seguirE\">Seguir Evento</a>');
        } else {
            $("#botones_evento").append('<a class="btn btn-danger col-md-6 col-xs-6" id=\"btn_dejarE\">Dejar Evento</a>');
        }        
    }
    
    $('#btn_seguirE').click(function () {
        resultado = oPublicacionModel.seguirEvento(id_evento);
        title = "Evento añadido a tu lista de eventos";
        oView.doResultOperationGP(place, resultado["status"], title, null, id_evento, true, null);
        return false;
    });
    
    $('#btn_dejarE').click(function () {
        resultado = oPublicacionModel.dejarEvento(id_evento);
        title = "Evento eliminado de tu lista de evnetos";
        oView.doResultOperationGP(place, resultado["status"], title, null, id_evento, true, null);
        return false;
    });
    
};

publicacionControl.prototype.listarEventos = function (place, oModel, oView) {
    var thisObject = this;
    $(place).empty();
    var oPublicacionModel = oModel;
    var oPublicacionView = oView;
    
    var user = myuser;
    var eventos = oPublicacionModel.setGenericOperation("geteventos&rpp=50","");
    
    var person = oUsuarioModel.setGenericOperation("get&id="+user,"");
    var pubs = oPublicacionModel.setGenericOperation("getregisters&systemfilter=id_usuario&systemfilteroperator=equals&systemfiltervalue="+user,"");
    var coments = oComentarioModel.setGenericOperation("getregisters&systemfilter=id_usuario&systemfilteroperator=equals&systemfiltervalue="+user,"");
    var friends = oAmistadModel.setGenericOperation("getregisters&systemfilter=id_usuario_1&systemfilteroperator=equals&systemfiltervalue="+user,"");
    
    var content = oPublicacionView.getEventos(eventos, person, pubs, coments, friends, "inicio");
    $("#principalpag").html(content);
};

publicacionControl.prototype.eventosUsuario = function (place, id_user, oModel, oView) {

    $(place).empty();
    var oPublicacionModel = oModel;
    var oPublicacionView = oView;
    
    var person = oUsuarioModel.setGenericOperation("get&id="+id_user,"");
    var eventos = oPublicacionModel.setGenericOperation("getpage&systemfilter=id_usuario&systemfilteroperator=equals&systemfiltervalue="+id_user+"&order=fechapub&ordervalue=asc","");
    
    var content = oPublicacionView.getEventos(eventos, person, null, null, null, "usuario");
    $("#principalpag").html(content);
};

publicacionControl.prototype.listarEventosFilter = function (place, id, oModel, oView, tipo) {
    var thisObject = this;
    $(place).empty();
    var oPublicacionModel = oModel;
    var oPublicacionView = oView;
    var data = oPublicacionModel.cargaEventos(id);
    var eventos = oPublicacionView.getEventos(data, null, null, null, null, tipo);
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

publicacionControl.prototype.eliminarComentario = function (place, id_comentario, id_evento, oModel, oView) {
    var thisObject = this;
    
    var oPublicacionModel = oModel;
    var oPublicacionView = oView;
    
    var comentario = oComentarioModel.setGenericOperation("get&id="+id_comentario,"");
    var user_coment = comentario.id_usuario;
    
    if (myuser == user_coment || myuser == "1"){
        var data = oComentarioModel.setGenericOperation("remove&id="+id_comentario,"");

        if (data.status == "200"){
            title = "Comentario eliminado";
            oView.doResultOperationGP(place, data.status, title, null, id_evento, true, "control#/evento/");
        } else{
            coment = "No hemos podido eliminar el comentario";
            oView.doResultOperationGP(place, data.status, null, coment, id_evento, true, "control#/evento/");
        }
    } else {
        coment = "No puedes eliminar el comentario de otro usuario";
        oView.doResultOperationGP(place, "400", null, coment, id_evento, true, "control#/evento/");
    }
};

