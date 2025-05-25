package org.example.proyecto.modelo;

import java.time.LocalDate;

public class Notificacion {
    private String idNotificacion;
    private String tipo;
    private String mensaje;
    private LocalDate fechaEnvio;
    private String idUsuario;
    private LocalDate fechaAlta;
    private LocalDate fechaModificacion;

    public Notificacion(String idNotificacion, String tipo, String mensaje, String fechaEnvio,
                        String idUsuario, LocalDate fechaAlta, LocalDate fechaModificacion) {
        this.idNotificacion = idNotificacion;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.idUsuario = idUsuario;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
        this.fechaEnvio = LocalDate.parse(fechaEnvio);
    }

    public String getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(String idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Notificacion() {

    }


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
