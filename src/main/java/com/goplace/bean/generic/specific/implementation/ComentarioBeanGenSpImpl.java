/*
 * Copyright (C) July 2014 Rafael Aznar
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
package com.goplace.bean.generic.specific.implementation;


import com.google.gson.annotations.Expose;
import com.goplace.bean.generic.implementation.BeanGenImpl;
import com.goplace.bean.publicinterface.BeanInterface;
import java.util.Date;

public class ComentarioBeanGenSpImpl extends BeanGenImpl implements BeanInterface {

    public ComentarioBeanGenSpImpl() {
    }

    public ComentarioBeanGenSpImpl(Integer id) {
        super(id);
    }
    
    @Expose
    private String coment = "";
    @Expose(serialize = false)
    private Integer id_usuario = 0;
    @Expose(deserialize = false)
    private UsuarioBeanGenSpImpl obj_usuario = null;
    @Expose(serialize = false)
    private Integer id_publicacion = 0;
    @Expose(deserialize = false)
    private PublicacionBeanGenSpImpl obj_publicacion = null;
    @Expose
    private Date fechacomentario = new Date();

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }
    
    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public UsuarioBeanGenSpImpl getObj_usuario() {
        return obj_usuario;
    }

    public void setObj_usuario(UsuarioBeanGenSpImpl obj_usuario) {
        this.obj_usuario = obj_usuario;
    }

    public Integer getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(Integer id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public PublicacionBeanGenSpImpl getObj_publicacion() {
        return obj_publicacion;
    }

    public void setObj_publicacion(PublicacionBeanGenSpImpl obj_publicacion) {
        this.obj_publicacion = obj_publicacion;
    }

    public Date getFechacomentario() {
        return fechacomentario;
    }

    public void setFechacomentario(Date fechacomentario) {
        this.fechacomentario = fechacomentario;
    }

    

}
