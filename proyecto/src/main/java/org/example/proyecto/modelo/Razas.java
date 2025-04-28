package org.example.proyecto.modelo;

import java.time.LocalDate;

public class Razas {
  private Integer idRaza;
  private String tipo;
  private LocalDate fechaAlta;
  private LocalDate fechaModificacion;
  private Integer idPerros;

    public Razas(Integer idRaza, String tipo, LocalDate fechaAlta, LocalDate fechaModificacion, Integer idPerros) {
        this.idRaza = idRaza;
        this.tipo = tipo;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
        this.idPerros = idPerros;
    }

    public Integer getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(Integer idRaza) {
        this.idRaza = idRaza;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Integer getIdPerros() {
        return idPerros;
    }

    public void setIdPerros(Integer idPerros) {
        this.idPerros = idPerros;
    }
}
