package org.example.proyecto.modelo;

import java.time.LocalDate;

public class Perros {
    public enum sexo {Masculino,Femenino}
    public enum adoptado {Si,No}
    private Integer idPerro;
    private String nombre;
    private sexo sexo;
    private String fechaNacimiento;
    private adoptado adoptado;
    private String CIF;//Foreing key protectora
    private LocalDate fechaAlta;
    private LocalDate fechaModificacion;
    private Byte foto;

    public Perros(Integer idPerro, String nombre, Perros.sexo sexo, String fechaNacimiento,
                  Perros.adoptado adoptado, String CIF, LocalDate fechaAlta,
                  LocalDate fechaModificacion, Byte foto) {
        this.idPerro = idPerro;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.adoptado = adoptado;
        this.CIF = CIF;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
        this.foto = foto;
    }

    public Integer getIdPerro() {
        return idPerro;
    }

    public void setIdPerro(Integer idPerro) {
        this.idPerro = idPerro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public sexo getSexo() {
        return sexo;
    }

    public void setSexo(sexo sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public adoptado getAdoptado() {
        return adoptado;
    }

    public void setAdoptado(adoptado adoptado) {
        this.adoptado = adoptado;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
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

    public Byte getFoto() {
        return foto;
    }

    public void setFoto(Byte foto) {
        this.foto = foto;
    }
}
