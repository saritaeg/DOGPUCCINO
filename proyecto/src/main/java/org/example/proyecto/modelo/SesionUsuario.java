package org.example.proyecto.modelo;

public class SesionUsuario {
    private static SesionUsuario instancia;
    private Usuario usuario;

    private SesionUsuario() {
    }

    public static SesionUsuario getInstancia() {
        if (instancia == null) {
            instancia = new SesionUsuario();
        }
        return instancia;
    }

    public static void setInstancia(SesionUsuario instancia) {
        SesionUsuario.instancia = instancia;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void iniciarSesion(Usuario usuario) {
        this.usuario = usuario;
    }

    public void cerrarSesion() {
        this.usuario = null;
        instancia = null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean estaLogueado() {
        return usuario != null;
    }
}
