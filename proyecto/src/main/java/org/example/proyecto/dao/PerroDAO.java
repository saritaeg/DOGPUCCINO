package org.example.proyecto.dao;

import org.example.proyecto.modelo.Perro;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerroDAO {

    public static void actualizarPerro(Perro perro) {
        String sql = """
            UPDATE Perros
            SET Nombre = ?, Fecha_Nacimiento = TO_DATE(?, 'YYYY-MM-DD'), Sexo = ?, Raza = ?, Foto = ?, Fecha_modificacion = SYSDATE
            WHERE ID = ?
        """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, perro.getNombre());
            pstmt.setString(2, perro.getFechaNacimiento());
            pstmt.setString(3, perro.getSexo().toString());
            pstmt.setString(4, perro.getRaza());
            pstmt.setString(5, perro.getFoto());
            pstmt.setInt(6, perro.getId());

            int filas = pstmt.executeUpdate();
            if (filas == 0) {
                System.err.println("No se actualizó el perro. ID no encontrado: " + perro.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> obtenerRazas() {
        List<String> listaRazas = new ArrayList<>();
        String sql = "SELECT Tipo FROM Razas";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                listaRazas.add(rs.getString("Tipo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaRazas;
    }

    public static List<String> obtenerPatologias() {
        List<String> listaPatologias = new ArrayList<>();
        String sql = "SELECT Nombre FROM Patologias";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                listaPatologias.add(rs.getString("Nombre"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPatologias;
    }
}
