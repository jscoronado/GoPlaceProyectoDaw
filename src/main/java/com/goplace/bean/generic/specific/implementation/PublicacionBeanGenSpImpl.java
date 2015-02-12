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

public class PublicacionBeanGenSpImpl extends BeanGenImpl implements BeanInterface {

    public PublicacionBeanGenSpImpl() {
    }

    public PublicacionBeanGenSpImpl(Integer id) {
        super(id);
    }
    
    @Expose
    private String titulo = "";
    @Expose
    private String descripcion = "";
    @Expose(serialize = false)
    private Integer id_usuario = 0;
    @Expose(deserialize = false)
    private UsuarioBeanGenSpImpl obj_usuario = null;
    @Expose(serialize = false)
    private Integer id_ciudad = 0;
    @Expose(deserialize = false)
    private CiudadBeanGenSpImpl obj_ciudad = null;
    @Expose(serialize = false)
    private Integer id_tipopublicacion = 0;
    @Expose(deserialize = false)
    private TipopublicacionBeanGenSpImpl obj_tipopublicacion = null;
    @Expose
    private Date fechapub = new Date();

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Integer getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(Integer id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public CiudadBeanGenSpImpl getObj_ciudad() {
        return obj_ciudad;
    }

    public void setObj_ciudad(CiudadBeanGenSpImpl obj_ciudad) {
        this.obj_ciudad = obj_ciudad;
    }

    public Integer getId_tipopublicacion() {
        return id_tipopublicacion;
    }

    public void setId_tipopublicacion(Integer id_tipopublicacion) {
        this.id_tipopublicacion = id_tipopublicacion;
    }

    public TipopublicacionBeanGenSpImpl getObj_tipopublicacion() {
        return obj_tipopublicacion;
    }

    public void setObj_tipopublicacion(TipopublicacionBeanGenSpImpl obj_tipopublicacion) {
        this.obj_tipopublicacion = obj_tipopublicacion;
    }

    public Date getFechapub() {
        return fechapub;
    }

    public void setFechapub(Date fechapub) {
        this.fechapub = fechapub;
    }

}
