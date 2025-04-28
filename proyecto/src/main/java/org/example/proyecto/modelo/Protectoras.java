package org.example.proyecto.modelo;

import java.time.LocalDate;

public class Protectoras {
    private String CIF;
    private String nombre;
    private String telefono;
    private String correoElectronico;
    private String calle;
    private String ciudad;
    private String redesSociales;
    private LocalDate fechaModificacion;
    private LocalDate fechaAlta;

    public Protectoras(String CIF, String nombre, String telefono, String correoElectronico,
                       String calle, String ciudad, String redesSociales,
                       LocalDate fechaModificacion, LocalDate fechaAlta) {
        this.CIF = CIF;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.calle = calle;
        this.ciudad = ciudad;
        this.redesSociales = redesSociales;
        this.fechaModificacion = fechaModificacion;
        this.fechaAlta = fechaAlta;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRedesSociales() {
        return redesSociales;
    }

    public void setRedesSociales(String redesSociales) {
        this.redesSociales = redesSociales;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
