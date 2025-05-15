package org.example.proyecto.dao;

import org.mindrot.jbcrypt.BCrypt;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class UsuarioDAO {

    public static boolean registrarUsuario(int idCliente, String contrasenia) throws SQLException {
        String contraseniaHasheada = BCrypt.hashpw(contrasenia.trim(), BCrypt.gensalt());

        System.out.println("🔐 Hash generado para guardar: " + contraseniaHasheada);

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

    public static boolean correoExiste(String correo) throws SQLException {
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
        }
        return false;
    }

    public static boolean registrarUsuarioParaProtectora(String cifProtectora, String contrasenia) throws SQLException {
        String contraseniaHasheada = BCrypt.hashpw(contrasenia.trim(), BCrypt.gensalt());

        System.out.println("🔐 Hash generado para guardar: " + contraseniaHasheada);

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

    public static boolean verificarContrasenia(String email, String passwordIngresada) {
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
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashAlmacenado = rs.getString("Contrasenia").trim();
                System.out.println("Hash almacenado: " + hashAlmacenado);
                System.out.println("Password ingresada: " + passwordIngresada);
                boolean match = BCrypt.checkpw(passwordIngresada, hashAlmacenado);
                System.out.println("¿Contraseña correcta? " + match);
                return match;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }



}







