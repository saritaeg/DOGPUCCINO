package org.example.proyecto.modelo;

import java.time.LocalDate;

public class Cita {
    private int clienteId;
    private int perroId;
    private String donacion;
    private int hora;
    private LocalDate fechaCita;
    private String estado;
    private LocalDate fechaAlta;
    private LocalDate fechaModificacion;

    public Cita() {
        this.fechaAlta = LocalDate.now();
        this.fechaModificacion = LocalDate.now();
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getPerroId() {
        return perroId;
    }

    public void setPerroId(int perroId) {
        this.perroId = perroId;
    }

    public String getDonacion() {
        return donacion;
    }

    public void setDonacion(String donacion) {
        this.donacion = donacion;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
