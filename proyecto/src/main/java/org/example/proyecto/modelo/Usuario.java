package org.example.proyecto.modelo;

import java.time.LocalDate;

public class Usuario {
    public enum rol{Cliente,Protectora}
    private Integer idUsuario;
    private String contrasenia;
    private rol rol;
    private LocalDate fechaAlta;
    private LocalDate fechaModificacion;
    private Integer idClientes;
    private Integer cifProtectora;


    public Usuario(Integer idUsuario, String contrasenia, rol rol, LocalDate fechaAlta,
                   LocalDate fechaModificacion, Integer idClientes, Integer cifProtectora) {
        this.idUsuario = idUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
        this.idClientes = idClientes;
        this.cifProtectora = cifProtectora;
    }
    public Usuario(String contrasenia) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public rol getRol() {
        return rol;
    }

    public void setRol(rol rol) {
        this.rol = rol;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(Integer idClientes) {
        this.idClientes = idClientes;
    }

    public Integer getCifProtectora() {
        return cifProtectora;
    }

    public void setCifProtectora(Integer cifProtectora) {
        this.cifProtectora = cifProtectora;
    }


}
