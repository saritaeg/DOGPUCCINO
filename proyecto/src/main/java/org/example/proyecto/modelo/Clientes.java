package org.example.proyecto.modelo;

import java.time.LocalDate;

public class Clientes {
    private Integer idCliente;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String telefono;
    private String provincia;
    private String calle;
    private LocalDate fechaNacimiento;
    private LocalDate fechaAlta;
    private LocalDate fechaModificacion;

    public Clientes(Integer idCliente, String nombre, String apellido1, String apellido2,
                    String email, String telefono, String provincia, String calle,
                    LocalDate fechaNacimiento, LocalDate fechaAlta, LocalDate fechaModificacion) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.telefono = telefono;
        this.provincia = provincia;
        this.calle = calle;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
    }

    public Clientes(String nombre, String apellido1, String apellido2,
                    String email, String telefono, String provincia, String calle,
                    LocalDate fechaNacimiento
                   ) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.telefono = telefono;
        this.provincia = provincia;
        this.calle = calle;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String ciudad) {
        this.provincia = provincia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
}
