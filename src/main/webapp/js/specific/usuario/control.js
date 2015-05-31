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

var usuarioControl = function (strClase) {
    this.clase = strClase;
};
usuarioControl.prototype = new control('usuario');
usuarioControl.prototype.getClassNameUsuario = function () {
    return this.getClassName() + "Control";
};
var oUsuarioControl = new usuarioControl('usuario');

usuarioControl.prototype.view = function (place, id, oModel, oView) {
    $(place).empty();
    var oDocumentoModel = oModel;
    oDocumentoModel.loadAggregateViewOne(id);
    $(place).append(oView.getPanel("Perfil" /*+ "de " + this.clase*/, oView.getObjectTable(oDocumentoModel.getCachedPrettyFieldNames(), oDocumentoModel.getCachedOne(), oDocumentoModel.getCachedFieldNames())));
    
    var jsonamigo = oUsuarioModel.existeAmigo(id);
    var amigo = jsonamigo.data;
    
    if (myuser == id) {
        $(place).append('<a class="btn btn-goplace" href="control#/usuario/edit/' + id + '">Editar</a>');
        $(place).append('<a class="btn btn-goplace" href="control#/usuario/remove/' + id + '">Borrar</a><br /><br />');
    } else {
        if(!amigo){
            $(place).append('<a class="btn btn-goplace" id=\"addfriend\">Añadir amigo</a>');
        } else {
            $(place).append('<a class="btn btn-danger" id=\"removefriend\">Eliminar amigo</a>');
        }        
    }
    
    $(place).append('<a class="btn btn-goplace" href="control#/amistad/list/systemfilter=id_usuario_1&systemfilteroperator=equals&systemfiltervalue=' + id + '">Ver amigos</a><br /><br />');
    
    $('#addfriend').click(function () {
        resultado = oUsuarioModel.agregarAmigo(id);
        oUsuarioView.doResultOperationNotifyToUser(place, resultado["status"], "Se ha añadido el usuario con id= " + id + " a tu lista de amigos ", resultado["message"], true, id);
        return false;
    });
    
    $('#removefriend').click(function () {
        resultado = oUsuarioModel.removeAmigo(id);
        oUsuarioView.doResultOperationNotifyToUser(place, resultado["status"], "Se ha eliminado el usuario con id= " + id + " a tu lista de amigos ", resultado["message"], true, id);
        return false;
    });
};

usuarioControl.prototype.perfil = function (place, id, oModel, oView) {
    $(place).empty();
    var oDocumentoModel = oModel;
    var perfil = oDocumentoModel.loadPerfil(id);
    var comentarios = oComentarioModel.loadComentarios(id);
    var eventos = oUsuarioModel.loadEventos(id);
    
    $(place).append(oView.getPerfil(perfil, comentarios, eventos));
    
    var jsonamigo = oUsuarioModel.existeAmigo(id);
    var amigo = jsonamigo.data;
    
    if (myuser == id) {
        $("#perfil_edit").append('<a class="btn btn-goplace" href="control#/usuario/edit/' + id + '">Editar</a>');
    } else {
        if(!amigo){
            $("#perfil_agregar").append('<a class="btn btn-goplace" id=\"addfriend\">Añadir amigo</a>');
        } else {
            $("#perfil_agregar").append('<a class="btn btn-danger" id=\"removefriend\">Eliminar amigo</a>');
        }        
    }
    
    $('#addfriend').click(function () {
        resultado = oUsuarioModel.agregarAmigo(id);
        title = "Usuario agregado a tu lista de amigos";
        oUsuarioView.doResultOperationGP(place, resultado["status"], title, null, id, true);
        return false;
    });
    
    $('#removefriend').click(function () {
        resultado = oUsuarioModel.removeAmigo(id);
        title = "Usuario eliminado de tu lista de amigos";
        oUsuarioView.doResultOperationGP(place, resultado["status"], title, null, id, true);
        return false;
    });
};

usuarioControl.prototype.list = function (place, objParams, callback, oModel, oView) {
    var thisObject = this;
    objParams = param().validateUrlObjectParameters(objParams);
    var id_usuario = objParams.systemfiltervalue;
    var hola = objParams;
    //var prueba = oRedsocialperfilModel.getOneUser();
    this.view(place, id_usuario, oUsuarioModel, oUsuarioView);
    //get all data from server in one http call and store it in cache
    var oDocumentoModel = oModel;
    oDocumentoModel.loadAggregateViewSome(objParams);
    //get html template from server and show it
    if (callback) {
        $(place).append(oView.getSpinner()).html(oView.getEmptyList());
    } else {
        $(place).append(oView.getPanel("Listado de " + oModel.getClassName(), oView.getEmptyList()));
    }
    //show page links pad
    var strUrlFromParamsWithoutPage = param().getUrlStringFromParamsObject(param().getUrlObjectFromParamsWithoutParamArray(objParams, ["page"]));
    var url = 'control#/' + this.clase + '/list/' + strUrlFromParamsWithoutPage;

    //visible fields select population, setting & event
    $('#selectVisibleFields').empty()
    oView.populateSelectVisibleFieldsBox($('#selectVisibleFields'), oDocumentoModel.getCachedCountFields());
    $('#selectVisibleFields').unbind('change');
    $("#selectVisibleFields").change(function () {
        window.location.href = "control#/" + thisObject.clase + "/list/" + param().getUrlStringFromParamsObject(param().getUrlObjectFromParamsWithoutParamArray(objParams, ['vf'])) + "&vf=" + $("#selectVisibleFields option:selected").val();
        return false;
    });
    //show the table
    var fieldNames = oDocumentoModel.getCachedFieldNames();
    if (fieldNames.length < objParams["vf"]) {
        objParams["vf"] = fieldNames.length;
    }
    if (callback) {
        var maximo = Math.max(oDocumentoModel.getCachedCountFields(), 3);
        $("#selectVisibleFields").val(maximo);
    } else {
        $("#selectVisibleFields").val(objParams["vf"]);
    }
    var prettyFieldNames = oDocumentoModel.getCachedPrettyFieldNames();
    var strUrlFromParamsWithoutOrder = param().getUrlStringFromParamsObject(param().getUrlObjectFromParamsWithoutParamArray(objParams, ["order", "ordervalue"]));
    var page = oDocumentoModel.getCachedPage();
    if (parseInt(objParams["page"]) > parseInt(oDocumentoModel.getCachedPages())) {
        objParams["page"] = parseInt(oDocumentoModel.getCachedPages());
    }
    $("#pagination").empty().append(oView.getSpinner()).html(oView.getPageLinks(url, parseInt(objParams["page"]), parseInt(oDocumentoModel.getCachedPages()), 2));

    $("#tableHeaders").empty().append(oView.getSpinner()).html(oView.getHeaderPageTable(prettyFieldNames, fieldNames, parseInt(objParams["vf"]), strUrlFromParamsWithoutOrder));
    
    id_elemento = 0;
    $("#tableBody").empty().append(oView.getSpinner()).html(function () {
        return oView.getBodyPageTable(page, fieldNames, parseInt(objParams["vf"]), function (id) {
            if (callback) {
                var botonera = "";
                botonera += '<div class="btn-toolbar" role="toolbar"><div class="btn-group btn-group-xs">';
                botonera += '<a class="btn btn-default selector_button" id="' + id + '"  href="#"><i class="glyphicon glyphicon-ok"></i></a>';
                botonera += '</div></div>';
                return botonera;
            } else {
                return oView.loadButtons(id, page[id_elemento]["id_usuario"]);
            }
            //mejor pasar documento como parametro y crear un repo global de código personalizado
        });
    });
    //show information about the query
    $("#registers").empty().append(oView.getSpinner()).html(oView.getRegistersInfo(oDocumentoModel.getCachedRegisters()));
    $("#order").empty().append(oView.getSpinner()).html(oView.getOrderInfo(objParams));
    $("#filter").empty().append(oView.getSpinner()).html(oView.getFilterInfo(objParams));
    //regs per page links
    $('#nrpp').empty().append(oView.getRppLinks(objParams));
    //filter population & event
    $('#selectFilter').empty().populateSelectBox(util().replaceObjxId(fieldNames), prettyFieldNames);
    $('#btnFiltrar').unbind('click');
    $("#btnFiltrar").click(function (event) {
        filter = $("#selectFilter option:selected").val();
        filteroperator = $("#selectFilteroperator option:selected").val();
        filtervalue = $("#inputFiltervalue").val();
        window.location.href = 'control#/' + thisObject.clase + '/list/' + param().getUrlStringFromParamsObject(param().getUrlObjectFromParamsWithoutParamArray(objParams, ['filter', 'filteroperator', 'filtervalue'])) + "&filter=" + filter + "&filteroperator=" + filteroperator + "&filtervalue=" + filtervalue;
        return false;
    });

    if (objParams["systemfilter"]) {
        //$('#newButton').prop("href", 'control#/' + thisObject.clase + '/new/' + param().getStrSystemFilters(objParams))
        $('#newButton').prop("href", 'control#/' + thisObject.clase + '/new/' + 'systemfilter=' + objParams["systemfilter"] + '&systemfilteroperator=' + objParams["systemfilteroperator"] + '&systemfiltervalue=' + objParams["systemfiltervalue"]);
    }

};

usuarioControl.prototype.upload = function (place, id, oModel, oView) {
    var thisObject = this;
    $(place).empty();
    $(place).append(oView.getPanel("Edición de " + this.clase, oView.getEmptyView("imagen", "unwrappered")));
    var oDocumentoModel = oModel;
    oDocumentoModel.loadAggregateViewOne(id);
    oView.loadFormValues(oDocumentoModel.getCachedOne(), oDocumentoModel.getCachedFieldNames());
    
};