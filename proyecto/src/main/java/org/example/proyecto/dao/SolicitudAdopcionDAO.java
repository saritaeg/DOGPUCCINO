package org.example.proyecto.dao;

import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SolicitudAdopcionDAO {
    public static boolean registrarSolicitudAdopcion(int idCliente, int idPerro) {
        String sql = """
            INSERT INTO Solicitud_adopcion (Cliente_ID, Perro_ID, Fecha_alta, Fecha_modificacion, Estado)
            VALUES (?, ?, SYSDATE, SYSDATE, 'Pendiente')
        """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            stmt.setInt(2, idPerro);

            int filasInsertadas = stmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
