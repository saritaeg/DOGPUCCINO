package org.example.proyecto.dao;

import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PerfilPerroDAO {
    public static String obtenerPatologiasPerro(int idPerro) {
        StringBuilder sb = new StringBuilder();

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
            String sql = """
            SELECT p.Nombre, pp.Descripcion
            FROM Patologias p
            JOIN Perros_Patologias pp ON p.ID = pp.ID_Patologia
            WHERE pp.ID_Perros = ?
        """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPerro);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("Nombre");
                String descripcion = rs.getString("Descripcion");
                sb.append("• ").append(nombre);
                if (descripcion != null && !descripcion.isBlank()) {
                    sb.append(" - ").append(descripcion);
                }
                sb.append("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            sb.append("Error al cargar patologías.");
        }

        return sb.length() > 0 ? sb.toString() : "Ninguna";
    }

}
