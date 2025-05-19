package org.example.proyecto.modelo;

import java.time.LocalDate;

public class Perro {
    public enum Adoptado { S, N }
    public enum Sexo { M, H }

    private int id;
    private String nombre;
    private String fechaNacimiento;
    private Adoptado adoptado;
    private Sexo sexo;
    private String raza;
    private String foto;
    private LocalDate fechaAlta;
    private LocalDate fechaModificacion;
    private String cifProtectora;

    public Perro() {}

    public Perro(int id, String nombre, String fechaNacimiento, Adoptado adoptado,
                 Sexo sexo, String raza, String foto, LocalDate fechaAlta,
                 LocalDate fechaModificacion, String cifProtectora) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.adoptado = adoptado;
        this.sexo = sexo;
        this.raza = raza;
        this.foto = foto;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
        this.cifProtectora = cifProtectora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Adoptado getAdoptado() {
        return adoptado;
    }

    public void setAdoptado(Adoptado adoptado) {
        this.adoptado = adoptado;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public String getCifProtectora() {
        return cifProtectora;
    }

    public void setCifProtectora(String cifProtectora) {
        this.cifProtectora = cifProtectora;
    }
}
