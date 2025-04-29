package org.example.proyecto.modelo;

import java.time.LocalDate;

public class SolicitudAdopcion {
    private Integer idSolicitud;
    private Integer idCliente;
    private Integer idPerro;
    private LocalDate fechaAlta;
    private LocalDate fechaModificacion;

    public SolicitudAdopcion(Integer idSolicitud, Integer idCliente, Integer idPerro, LocalDate fechaAlta, LocalDate fechaModificacion) {
        this.idSolicitud = idSolicitud;
        this.idCliente = idCliente;
        this.idPerro = idPerro;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdPerro() {
        return idPerro;
    }

    public void setIdPerro(Integer idPerro) {
        this.idPerro = idPerro;
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
