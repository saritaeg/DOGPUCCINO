package org.example.proyecto.dao;

import org.example.proyecto.modelo.Protectoras;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditarProtectoraDAO {

    public static Protectoras obtenerProtectoraPorEmail(String correo) {
        Protectoras protectora = null;
        String sql = "SELECT CIF, Nombre, Telefono, Correo_Electronico, Calle, Ciudad, Redes_Sociales, Fecha_Modificacion, Fecha_Alta " +
                "FROM Protectoras WHERE Correo_Electronico = ?";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                protectora = new Protectoras(
                        rs.getString("CIF"),
                        rs.getString("Nombre"),
                        rs.getString("Telefono"),
                        rs.getString("Correo_Electronico"),
                        rs.getString("Calle"),
                        rs.getString("Ciudad"),
                        rs.getString("Redes_Sociales"),
                        rs.getObject("Fecha_Modificacion", java.time.LocalDate.class),
                        rs.getObject("Fecha_Alta", java.time.LocalDate.class)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return protectora;
    }

    public static boolean actualizarProtectora(Protectoras protectora, String correoOriginal) {
        String sql = "UPDATE Protectoras SET CIF = ?, Nombre = ?, Telefono = ?, Correo_Electronico = ?, Calle = ?, Ciudad = ?, Redes_Sociales = ?, Fecha_Modificacion = ? " +
                "WHERE Correo_Electronico = ?";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, protectora.getCIF());
            ps.setString(2, protectora.getNombre());
            ps.setString(3, protectora.getTelefono());
            ps.setString(4, protectora.getCorreoElectronico());
            ps.setString(5, protectora.getCalle());
            ps.setString(6, protectora.getCiudad());
            ps.setString(7, protectora.getRedesSociales());
            ps.setObject(8, protectora.getFechaModificacion());
            ps.setString(9, correoOriginal);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


