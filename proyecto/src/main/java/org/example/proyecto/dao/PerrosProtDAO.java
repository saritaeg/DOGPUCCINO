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
            SELECT p.ID, p.Nombre, p.Fecha_Nacimiento, r.Tipo AS Raza, p.Sexo, p.Adoptado, p.CIF, p.Foto, p.Fecha_alta, p.Fecha_modificacion
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

                    p.setId(rs.getInt("ID"));
                    p.setNombre(rs.getString("Nombre"));

                    Date fechaSQL = rs.getDate("Fecha_Nacimiento");
                    if (fechaSQL != null) {
                        LocalDate fechaNacimiento = fechaSQL.toLocalDate();
                        p.setFechaNacimiento(fechaNacimiento.toString());
                    }

                    p.setRaza(rs.getString("Raza"));

                    try {
                        p.setSexo(Sexo.valueOf(rs.getString("Sexo")));
                    } catch (IllegalArgumentException e) {
                        p.setSexo(null);
                    }

                    try {
                        p.setAdoptado(Adoptado.valueOf(rs.getString("Adoptado")));
                    } catch (IllegalArgumentException e) {
                        p.setAdoptado(null);
                    }

                    p.setCifProtectora(rs.getString("CIF"));
                    p.setFoto(rs.getString("Foto"));

                    Date fechaAltaSQL = rs.getDate("Fecha_alta");
                    if (fechaAltaSQL != null) {
                        p.setFechaAlta(fechaAltaSQL.toLocalDate());
                    }

                    Date fechaModSQL = rs.getDate("Fecha_modificacion");
                    if (fechaModSQL != null) {
                        p.setFechaModificacion(fechaModSQL.toLocalDate());
                    }

                    lista.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}



