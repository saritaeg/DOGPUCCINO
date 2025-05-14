package org.example.proyecto.dao;

import org.mindrot.jbcrypt.BCrypt;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.*;
import java.time.LocalDate;

public class UsuarioDAO {

    public static boolean registrarUsuario(int idCliente, String contrasenia) throws SQLException {
        String contraseniaHasheada = BCrypt.hashpw(contrasenia, BCrypt.gensalt());
        System.out.println("Contraseña hasheada: " + contraseniaHasheada);

        String sql = """
        INSERT INTO Usuarios (ID, ID_Clientes, Contrasenia, Fecha_Alta, Fecha_Modificacion)
        VALUES (usuario_seq.NEXTVAL, ?, ?, ?, ?)
    """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            stmt.setString(2, contraseniaHasheada);

            Date hoy = Date.valueOf(LocalDate.now());
            stmt.setDate(3, hoy);
            stmt.setDate(4, hoy);

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean correoExiste(String correo) {
        String sql = "SELECT COUNT(*) FROM ( " +
                "SELECT correo_electronico FROM Clientes WHERE correo_electronico = ? " +
                "UNION " +
                "SELECT correo_electronico FROM Protectoras WHERE correo_electronico = ? " +
                ")";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            stmt.setString(2, correo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String obtenerContraseniaPorEmail(String email) throws SQLException {
        String sql = """
        SELECT u.Contrasenia
        FROM Usuarios u
        LEFT JOIN Clientes c ON u.ID_Clientes = c.ID
        LEFT JOIN Protectoras p ON u.CIF_Protectoras = p.CIF
        WHERE c.Correo_Electronico = ? OR p.Correo_Electronico = ?
    """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Contrasenia");
                }
            }
        }

        return null;
    }

    public static boolean registrarUsuarioParaProtectora(String cifProtectora, String contrasenia) throws SQLException {
        String contraseniaHasheada = BCrypt.hashpw(contrasenia, BCrypt.gensalt());

        String sql = """
        INSERT INTO Usuarios (ID, CIF_Protectoras, Contrasenia, Fecha_Alta, Fecha_Modificacion)
        VALUES (usuario_seq.NEXTVAL, ?, ?, ?, ?)
    """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cifProtectora);
            stmt.setString(2, contraseniaHasheada);

            Date hoy = Date.valueOf(LocalDate.now());
            stmt.setDate(3, hoy);
            stmt.setDate(4, hoy);

            return stmt.executeUpdate() > 0;
        }
    }

    public static boolean verificarContrasenia(String email, String contrasenia) throws SQLException {
        String sql = """
    SELECT Contrasenia 
    FROM Usuarios u
    LEFT JOIN Clientes c ON u.ID_Clientes = c.ID
    LEFT JOIN Protectoras p ON u.CIF_Protectoras = p.CIF
    WHERE c.Correo_Electronico = ? OR p.Correo_Electronico = ?
    """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String contraseniaHasheada = rs.getString("Contrasenia");
                System.out.println("Contraseña hasheada obtenida de la base de datos: " + contraseniaHasheada);
                return BCrypt.checkpw(contrasenia, contraseniaHasheada);
            }
        }
        return false;
    }





}
