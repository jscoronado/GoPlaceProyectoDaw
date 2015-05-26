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
import java.util.Date;
import com.goplace.bean.generic.implementation.BeanGenImpl;
import com.goplace.bean.publicinterface.BeanInterface;

public class UsuarioBeanGenSpImpl extends BeanGenImpl implements BeanInterface {

    @Expose
    private String nombre = "";
    @Expose
    private String apellidos = "";
    @Expose
    private String correo = "";
    @Expose
    private String login = "";
    @Expose(serialize = false)
    private String password = "";
    @Expose
    private Date fecha = null;
    @Expose(serialize = false)
    private Integer id_ciudad = 0;
    @Expose(deserialize = false)
    private CiudadBeanGenSpImpl obj_ciudad = null;
    @Expose
    private String genero = "";
    @Expose
    private String estado = "";
    @Expose(serialize = false)
    private Integer id_tipousuario = 0;
    @Expose(deserialize = false)
    private TipousuarioBeanGenSpImpl obj_tipousuario = null;
    @Expose
    private String imagen = "";

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getId_tipousuario() {
        return id_tipousuario;
    }

    public void setId_tipousuario(Integer id_tipousuario) {
        this.id_tipousuario = id_tipousuario;
    }

    public TipousuarioBeanGenSpImpl getObj_tipousuario() {
        return obj_tipousuario;
    }

    public void setObj_tipousuario(TipousuarioBeanGenSpImpl obj_tipousuario) {
        this.obj_tipousuario = obj_tipousuario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    
}
