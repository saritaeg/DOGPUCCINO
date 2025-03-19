package com.safa.seg;

import com.joey.utils.NombreRazaPerro;
import com.joey.utils.Opcion;
import com.joey.utils.SoporteJoey;

import java.time.Year;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Ejercicio 1:");
        muestraOpcionesOrdenadas();
        System.out.println("Ejercicio 2:");
        System.out.println(creaMapaOpciones());
        System.out.println("Ejercicio 3:");
        System.out.println(esDocumentoValido("Ley 12/2024 de Protección de Datos"));
        System.out.println(esDocumentoValido("Ley 0001/2025 de Ministerio de Igualdad"));
        System.out.println(esDocumentoValido("Decreto 120/1999 de Seguridad Industrial"));
        System.out.println(esDocumentoValido("Regla 15/2018"));
        System.out.println(esDocumentoValido("Ley 21/1918 de Ministerio de Transportes"));
        System.out.println(esDocumentoValido("Ley 5/23 de Consumo"));
        List<PerroDeJose> perrosJose = crearlista();
        paseador(perrosJose);

    }
    public static void muestraOpcionesOrdenadas(){
        SoporteJoey soporte = SoporteJoey.getInstance();
        List<Opcion> opciones = soporte.getOpciones();

        opciones.stream()
                .filter(o -> o.getPrecio() < 200 && o.getDistancia() <=50)
                .sorted((o1,o2)-> {
                    int comparacion = o2.getPuntuacion().compareTo(o1.getPuntuacion());
                    return (comparacion !=0)? comparacion : o1.getPrecio().compareTo(o2.getPrecio());
                })
                .forEach(o ->System.out.println(">> Puntuacion: "+o.getPuntuacion()+o));

    }
    public static Map<Integer,List<String>>creaMapaOpciones(){
        SoporteJoey soporte = SoporteJoey.getInstance();
        List<Opcion> opciones = soporte.getOpciones();

        return opciones.stream()
                .collect(Collectors.groupingBy(
                        Opcion::getPuntuacion,
                        TreeMap::new,
                        Collectors.mapping(o ->o.getNombre().toUpperCase(), Collectors.toList() )
                ));
    }
    public static boolean esDocumentoValido(String nombreDocumento){
        int anoactual = Year.now().getValue();
        String regex = "^(Ley|Decreto|Reglamento|Orden) ([1-9]\\d{0,3})/(\\d{4})(?: de .+)?";

        Pattern pater = Pattern.compile(regex);
        Matcher matcher = pater.matcher(nombreDocumento);
        if (!matcher.matches()){
            return false;
        }
        int ano = Integer.parseInt(matcher.group(3));
        return ano >= 1975 && ano <= anoactual;
    }
    public static List<PerroDeJose> crearlista(){
        SoporteJoey soporte = SoporteJoey.getInstance();
        List<PerroDeJose> perros = Arrays.asList(
                new PerroDeJose("Chuli",soporte.getRazaPerroByName(NombreRazaPerro.BEAGLE)),
                new PerroDeJose("Cala",soporte.getRazaPerroByName(NombreRazaPerro.MASTIN)),
                new PerroDeJose("Lila",soporte.getRazaPerroByName(NombreRazaPerro.YORKSHIRE_TERRIER))
        );

        perros.sort(Comparator.comparing(PerroDeJose::getNombre));
        return perros;
    }
    public static void paseador(List<PerroDeJose>perrosJose){
        SoporteJoey soporte = SoporteJoey.getInstance();
        List<String> hermanos = soporte.getHermanosJose();
        Collections .sort(hermanos);
        for ( int i=0;i<perrosJose.size();i++){
            perrosJose.get(i).setPaseadoPor(hermanos.get(i));

        }
        perrosJose.forEach(System.out::println);
    }

}