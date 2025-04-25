package org.example.proyecto.modelo;

import java.time.LocalDate;

public class Patologias {
    private Integer idPatologia;
    private String nombrePatologia;
    private LocalDate fechaModificacion;
    private LocalDate fechaAlta;

    public Patologias(Integer idPatologia, String nombrePatologia, LocalDate fechaModificacion, LocalDate fechaAlta) {
        this.idPatologia = idPatologia;
        this.nombrePatologia = nombrePatologia;
        this.fechaModificacion = fechaModificacion;
        this.fechaAlta = fechaAlta;
    }

    public Integer getIdPatologia() {
        return idPatologia;
    }

    public void setIdPatologia(Integer idPatologia) {
        this.idPatologia = idPatologia;
    }

    public String getNombrePatologia() {
        return nombrePatologia;
    }

    public void setNombrePatologia(String nombrePatologia) {
        this.nombrePatologia = nombrePatologia;
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
