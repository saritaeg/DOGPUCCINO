package org.example.proyecto.modelo;

import java.time.LocalDate;

public class Usuario {



    public enum Rol {
        CLIENTE, PROTECTORA
    }

    private Integer idUsuario;
    private String contrasenia;
    private Rol rol;
    private LocalDate fechaAlta;
    private LocalDate fechaModificacion;
    private Integer idClientes;
    private String cifProtectora;
    private Clientes cliente;
    private Protectoras protectora;

    public Usuario(Integer idUsuario, String contrasenia, Rol rol, LocalDate fechaAlta,
                   LocalDate fechaModificacion, Integer idClientes, String cifProtectora) {
        this.idUsuario = idUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
        this.idClientes = idClientes;
        this.cifProtectora = cifProtectora;
    }

    public Usuario(Integer idUsuario, String contrasenia, Rol rol, LocalDate fechaAlta,
                   LocalDate fechaModificacion, Integer idClientes, String cifProtectora, Clientes cliente) {
        this.idUsuario = idUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
        this.idClientes = idClientes;
        this.cifProtectora = cifProtectora;
        this.cliente = cliente;
    }
    public Usuario(Integer idUsuario, String contrasenia, Rol rol, LocalDate fechaAlta,
                   LocalDate fechaModificacion, Integer idClientes, String cifProtectora, Protectoras protectora) {
        this.idUsuario = idUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
        this.idClientes = idClientes;
        this.cifProtectora = cifProtectora;
        this.protectora = protectora;
    }

    public Protectoras getProtectora() {
        return protectora;
    }

    public void setProtectora(Protectoras protectora) {
        this.protectora = protectora;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Usuario(String contrasenia) {
        this.contrasenia = contrasenia;
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

    public String getCifProtectora() {
        return cifProtectora;
    }

    public void setCifProtectora(String cifProtectora) {
        this.cifProtectora = cifProtectora;
    }
}
