package org.example.proyecto.dao;

import org.example.proyecto.modelo.Perro;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PerrosCliDAO {

    public static List<Perro> obtenerPerrosCliente(String email) {
        List<Perro> lista = new ArrayList<>();
        String sql = """
        SELECT p.Nombre, p.Fecha_Nacimiento, r.Tipo AS Raza, p.foto
        FROM Perros p
        JOIN Razas r ON p.Raza = r.Tipo
        WHERE p.Adoptado = 'N'
    """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Perro p = new Perro();
                p.setNombre(rs.getString("Nombre"));
                p.setFechaNacimiento(rs.getInt("Edad"));
                p.setRaza(rs.getString("Raza"));
                p.setFoto(rs.getString("Ruta_Imagen"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


}
