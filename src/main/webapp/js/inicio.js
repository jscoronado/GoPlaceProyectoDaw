/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function ajaxCallSync(url, type, data) {
    return $.ajax({
        type: type,
        url: url,
        data: data,
        async: false,
        timeout: 30000
    });
}
;

function redondeo(numero) {
    redondeo = Math.round((numero) * 100) / 100;
    return redondeo;
}
;

$(document).ready(function () {

    espacio = ' ';
    comilla = "'";
    salto = "<br />";
    apertura = "<";
    cierre = ">";
    barra = "/";
    alm = "#";
    
    $.ajax({
        url: "http://localhost:8081/goplace/json?ob=publicacion&op=getcomentarios",
        type: "GET",
        dataType: "json",
        success: function (data) {
            jsonP = data.page.list;
            for (i = 0; i < jsonP.length; i++) {
                comentario += "<div class="+comilla+"publicacion row"+comilla+">";
                comentario += "<div class="+comilla+"col-md-1"+comilla+">";
                comentario += "<img src="+comilla+"<%=request.getContextPath()%>/images/foto.jpg"+comilla+"class="+comilla+"fotoPub"+comilla+" alt="+comilla+"Foto usuario"+i+comilla+">";
                comentario += "</div>";
                comentario += "<div class="+comilla+"col-md-11"+comilla+">";
                comentario += "<a href="+comilla+"#"+comilla+">" + data.page.list[i].obj_usuario.nombre + espacio + data.page.list[i].obj_usuario.apellidos + "</a>"; 
                comentario += "<span class="+comilla+"nick"+ comilla +">"+data.page.list[i].obj_usuario.login+"</span><br/>";
                comentario += "<h4><a href="+comilla+"#"+comilla+">" + data.page.list[i].titulo + "</a></h4>";
                comentario += "<span>" + data.page.list[i].descripcion + "</span>";
                comentario += "</div>";
                comentario += "</div>"; 
            }

            document.getElementById('comentariosgp').innerHTML = comentario;

            //logo += "<img src=" + comilla + "http://ficcifestival.com/images/misc/splash/logo_volkswagen.png" + comilla + "/>";
        }
    });
    return false;
});
