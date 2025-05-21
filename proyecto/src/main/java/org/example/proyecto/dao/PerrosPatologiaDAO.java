package org.example.proyecto.dao;

import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerrosPatologiaDAO {

    // Obtiene la patología y descripción de un perro por su ID
    public static String[] obtenerPatologiaYDescripcionPorPerro(int idPerro) {
        String sql = """
            SELECT pa.Nombre, pp.Descripcion
            FROM Perros_Patologias pp
            JOIN Patologias pa ON pa.ID = pp.ID_Patologia
            WHERE pp.ID_Perros = ?
        """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPerro);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new String[]{rs.getString("Nombre"), rs.getString("Descripcion")};
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new String[]{null, null}; // No tiene patología registrada
    }

    // Actualiza o inserta la patología y descripción para un perro dado
    public static void actualizarPatologiaDescripcion(int idPerro, String nombrePatologia, String descripcion) {
        String sqlBuscarIdPatologia = "SELECT ID FROM Patologias WHERE Nombre = ?";
        String sqlActualizar = "UPDATE Perros_Patologias SET ID_Patologia = ?, Descripcion = ? WHERE ID_Perros = ?";
        String sqlInsertar = "INSERT INTO Perros_Patologias (ID_Perros, ID_Patologia, Descripcion, Fecha_alta) VALUES (?, ?, ?, SYSDATE)";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            int idPatologia = -1;
            try (PreparedStatement pstmt = conn.prepareStatement(sqlBuscarIdPatologia)) {
                pstmt.setString(1, nombrePatologia);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        idPatologia = rs.getInt("ID");
                    } else {
                        System.err.println("Patología no encontrada: " + nombrePatologia);
                        return;
                    }
                }
            }

            // Intentar actualizar
            try (PreparedStatement pstmt = conn.prepareStatement(sqlActualizar)) {
                pstmt.setInt(1, idPatologia);
                pstmt.setString(2, descripcion);
                pstmt.setInt(3, idPerro);
                int filasActualizadas = pstmt.executeUpdate();

                if (filasActualizadas == 0) {
                    // No existía registro, insertamos nuevo
                    try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsertar)) {
                        pstmtInsert.setInt(1, idPerro);
                        pstmtInsert.setInt(2, idPatologia);
                        pstmtInsert.setString(3, descripcion);
                        pstmtInsert.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtiene todas las patologías para llenar el ComboBox
    public static List<String> obtenerTodasPatologias() {
        List<String> patologias = new ArrayList<>();
        String sql = "SELECT Nombre FROM Patologias ORDER BY Nombre";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                patologias.add(rs.getString("Nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patologias;
    }
}



