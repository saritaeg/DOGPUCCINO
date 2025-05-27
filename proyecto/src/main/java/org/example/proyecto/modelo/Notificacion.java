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
    private int clienteId;     // para identificar la solicitud (Cliente_ID)
    private int perroId;       // para identificar el perro (Perro_ID)
    private LocalDate fechaCita;  // solo si aplica para citas

    private String estado;




    public Notificacion() {
    }

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
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
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
