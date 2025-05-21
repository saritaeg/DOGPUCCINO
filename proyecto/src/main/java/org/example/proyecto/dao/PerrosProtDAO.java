package org.example.proyecto.dao;

import org.example.proyecto.modelo.Perro;
import org.example.proyecto.modelo.Perro.Adoptado;
import org.example.proyecto.modelo.Perro.Sexo;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PerrosProtDAO {

    public static List<Perro> obtenerPerrosProtectora(String cifProtectora) {
        List<Perro> lista = new ArrayList<>();
        String sql = """
            SELECT p.Nombre, p.Fecha_Nacimiento, r.Tipo AS Raza, p.Sexo, p.Adoptado, p.CIF
            FROM Perros p
            JOIN Razas r ON p.Raza = r.Tipo
            WHERE p.CIF = ?
        """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cifProtectora);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Perro p = new Perro();
                    p.setNombre(rs.getString("Nombre"));
                    Date fechaSQL = rs.getDate("Fecha_Nacimiento");
                    if (fechaSQL != null) {
                        LocalDate fechaNacimiento = fechaSQL.toLocalDate();
                        p.setFechaNacimiento(fechaNacimiento.toString());
                    }
                    p.setRaza(rs.getString("Raza"));
                    p.setSexo(Sexo.valueOf(rs.getString("Sexo")));
                    p.setAdoptado(Adoptado.valueOf(rs.getString("Adoptado")));
                    p.setCifProtectora(rs.getString("CIF"));

                    lista.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}


