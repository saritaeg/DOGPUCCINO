package org.example.proyecto.modelo;

public class ResultadoLogin {
    private boolean esValido;
    private String rol;
    private String contrasenia;  // hash almacenado

    public ResultadoLogin(boolean esValido, String rol, String contrasenia) {
        this.esValido = esValido;
        this.rol = rol;
        this.contrasenia = contrasenia;
    }

    public boolean esValido() {
        return esValido;
    }

    public String getRol() {
        return rol;
    }

    public String getContrasenia() {
        return contrasenia;
    }
}
