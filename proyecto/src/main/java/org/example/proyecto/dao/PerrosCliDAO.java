package org.example.proyecto.dao;

import org.example.proyecto.modelo.Perro;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PerrosCliDAO {

    public static List<Perro> obtenerPerrosCliente(String emailCliente) {
        List<Perro> perros = new ArrayList<>();

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM perros WHERE adoptado = 'N'")) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Perro perro = new Perro();
                perro.setId(rs.getInt("id"));
                perro.setNombre(rs.getString("nombre"));
                perro.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                perro.setAdoptado(Perro.Adoptado.valueOf(rs.getString("adoptado")));
                perro.setSexo(Perro.Sexo.valueOf(rs.getString("sexo")));
                perro.setRaza(rs.getString("raza"));
                perro.setFoto(rs.getString("foto"));
                perro.setFechaAlta(rs.getDate("fecha_alta").toLocalDate());
                perro.setFechaModificacion(rs.getDate("fecha_modificacion").toLocalDate());
                perro.setCifProtectora(rs.getString("cif"));

                perros.add(perro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return perros;
    }


}
