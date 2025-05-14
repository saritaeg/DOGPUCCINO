package org.example.proyecto.modelo;

import java.time.LocalDate;

public class Usuario {

    // Enum Rol: Utiliza mayúsculas por convención
    public enum Rol {
        CLIENTE, PROTECTORA
    }

    private Integer idUsuario;
    private String contrasenia;
    private Rol rol;
    private LocalDate fechaAlta;
    private LocalDate fechaModificacion;
    private Integer idClientes;
    private Integer cifProtectora;

    // Constructor principal para la clase Usuario
    public Usuario(Integer idUsuario, String contrasenia, Rol rol, LocalDate fechaAlta,
                   LocalDate fechaModificacion, Integer idClientes, Integer cifProtectora) {
        this.idUsuario = idUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
        this.idClientes = idClientes;
        this.cifProtectora = cifProtectora;
    }

    // Constructor alternativo solo con la contraseña (modificado para estar completo)
    public Usuario(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    // Getters y setters
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
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
