package com.safa.seg;

import com.joey.utils.Perro;
import com.joey.utils.RazaPerro;

import java.util.Objects;

public class PerroDeJose extends Perro {
    private String paseadoPor;
    public PerroDeJose(String nombre,RazaPerro raza){
        super(nombre,raza,null);
        this.paseadoPor = null;
    }

    public String getPaseadoPor() {
        return paseadoPor;
    }

    public void setPaseadoPor(String paseadoPor) {
        this.paseadoPor = paseadoPor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PerroDeJose that = (PerroDeJose) o;
        return Objects.equals(paseadoPor, that.paseadoPor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), paseadoPor);
    }

    @Override
    public String toString() {
        return super.toString() + ">> Paseado por: "+paseadoPor;
    }
}
