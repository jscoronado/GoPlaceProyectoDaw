/*
 * Copyright (C) 2014 a021008858z
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
package net.daw.bean.generic.specific.implementation;

import com.google.gson.annotations.Expose;
import net.daw.bean.generic.implementation.BeanGenImpl;
import net.daw.bean.publicinterface.BeanInterface;

/**
 *
 * @author a021008858z
 */
public class V_Usuario_ProveedorBeanGenSpImpl extends BeanGenImpl implements BeanInterface{
    
    public V_Usuario_ProveedorBeanGenSpImpl() {
    }

    public V_Usuario_ProveedorBeanGenSpImpl(Integer id) {
        super(id);
    }
    
    @Expose
    private String nombre = "";
    //private String presentacion = "";
    @Expose(serialize = false)
    private Integer id_usuario = 0; 
    @Expose(serialize = false)
    private UsuarioBeanGenSpImpl obj_usuario = null;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
    
    
    
    }
    
    
