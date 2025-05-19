package org.example.proyecto.dao;

import org.example.proyecto.modelo.Perro;
import org.example.proyecto.modelo.Protectoras;
import org.example.proyecto.utils.ConexionBaseDatos;
import org.example.proyecto.utils.Contraseña;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EditarProtectoraDAO {
    public static Protectoras obtenerProtectoraPorEmail(String email) {
        Protectoras protectora = null;
        String sql = """
        SELECT CIF, Nombre, Telefono, Correo_Electronico, Calle, Ciudad, Redes_Sociales, Fecha_Modificacion, Fecha_Alta
        FROM Protectoras
        WHERE Correo_Electronico = ?
    """;
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
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

    public static boolean actualizarProtectora(Protectoras protectora, String emailOriginal) {
        String sql = "UPDATE Protectoras SET Nombre=?, Telefono=?, Calle=?, Ciudad=?, Redes_Sociales=?, Fecha_Modificacion=?, Correo_Electronico=? WHERE Correo_Electronico=?";
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, protectora.getNombre());
            ps.setString(2, protectora.getTelefono());
            ps.setString(3, protectora.getCalle());
            ps.setString(4, protectora.getCiudad());
            ps.setString(5, protectora.getRedesSociales());
            ps.setObject(6, protectora.getFechaModificacion());
            ps.setString(7, protectora.getCorreoElectronico());
            ps.setString(8, emailOriginal);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean actualizarContrasenaPorCIF(String cifProtectora, String nuevaContrasena) {
        String sql = """
    
                UPDATE Usuarios
    SET Contrasenia = ?, Fecha_Modificacion = ?
    WHERE CIF_PROTECTORAS = ?
    """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String hashedPassword = Contraseña.encriptarContrasenia(nuevaContrasena);

            System.out.println("Actualizando contraseña para CIF: " + cifProtectora);
            System.out.println("Contraseña (hashed): " + hashedPassword);

            ps.setString(1, hashedPassword);
            ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.setString(3, cifProtectora);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String obtenerCifProtectoraPorCorreo(String correo) {
        String sql = "SELECT CIF FROM Protectoras WHERE Correo_Electronico = ?";
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("CIF");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
