package org.example.proyecto.modelo;

public class Sesion {
    private static Usuario usuario;


    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Sesion.usuario = usuario;
    }

    public static Usuario obtenerUsuario() {
        return usuario;
    }


}
