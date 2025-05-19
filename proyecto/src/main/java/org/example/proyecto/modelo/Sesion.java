package org.example.proyecto.modelo;

public class Sesion {
    private static String cifProtectora;

    public static void setCifProtectora(String cif) {
        cifProtectora = cif;
    }

    public static String getCifProtectora() {
        return cifProtectora;
    }
}
