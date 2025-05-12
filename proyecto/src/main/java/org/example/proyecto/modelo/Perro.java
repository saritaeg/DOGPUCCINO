package org.example.proyecto.modelo;
public class Perro {
    private int id;
    private String nombre;
    private String fechaNacimiento;
    private String sexo;
    private String raza;
    private String patologia;
    private String descripcionPatologia;

    public Perro() {}
    public Perro(int id, String nombre, String fechaNacimiento, String sexo, String raza, String patologia, String descripcionPatologia) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.raza = raza;
        this.patologia = patologia;
        this.descripcionPatologia = descripcionPatologia;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getPatologia() {
        return patologia;
    }

    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }

    public String getDescripcionPatologia() {
        return descripcionPatologia;
    }

    public void setDescripcionPatologia(String descripcionPatologia) {
        this.descripcionPatologia = descripcionPatologia;
    }
}
