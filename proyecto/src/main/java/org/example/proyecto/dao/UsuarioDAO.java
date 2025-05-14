package org.example.proyecto.dao;

import org.example.proyecto.modelo.HashUtils;
import org.example.proyecto.utils.ConexionBaseDatos;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.time.LocalDate;

public class UsuarioDAO {
    // Método para registrar un usuario con la contraseña hasheada usando BCrypt
    public static boolean registrarUsuario(int idCliente, String contrasenia) throws SQLException {
        // Hashear la contraseña antes de insertarla
        String contraseniaHasheada = BCrypt.hashpw(contrasenia, BCrypt.gensalt());

        // Imprimir la contraseña hasheada para verificar
        System.out.println("Contraseña hasheada: " + contraseniaHasheada);  // Esto imprimirá el hash generado

        String sql = """
        INSERT INTO Usuarios (ID, ID_Clientes, Contrasenia, Fecha_Alta, Fecha_Modificacion)
        VALUES (usuario_seq.NEXTVAL, ?, ?, ?, ?)
    """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            stmt.setString(2, contraseniaHasheada);  // Insertamos la contraseña hasheada

            Date hoy = Date.valueOf(LocalDate.now());
            stmt.setDate(3, hoy);
            stmt.setDate(4, hoy);

            return stmt.executeUpdate() > 0; // Retorna true si se insertó correctamente
        }
    }
    public static boolean correoExiste(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Usuarios WHERE correo = ?";  // Asegúrate de que 'correo' sea el nombre correcto de la columna en tu base de datos.

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;  // Si el conteo es mayor que 0, el correo existe
            }
        }

        return false;  // Si no se encuentra ningún registro, el correo no existe
    }
    public static String obtenerContraseniaPorEmail(String email) throws SQLException {
        String sql = "SELECT Contrasenia FROM Usuarios WHERE correo = ?";  // Asegúrate de que 'correo' y 'Contrasenia' sean los nombres correctos de las columnas en tu base de datos.

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("Contrasenia");  // Devuelve la contraseña hasheada almacenada en la base de datos
            }
        }

        return null;  // Si no se encuentra la contraseña, devolver null
    }
    // Método para registrar un usuario para una protectora con la contraseña hasheada usando BCrypt
    public static boolean registrarUsuarioParaProtectora(String cifProtectora, String contrasenia) throws SQLException {
        // Hashear la contraseña antes de insertarla usando BCrypt
        String contraseniaHasheada = BCrypt.hashpw(contrasenia, BCrypt.gensalt());

        String sql = """
        INSERT INTO Usuarios (ID, CIF_Protectoras, Contrasenia, Fecha_Alta, Fecha_Modificacion)
        VALUES (usuario_seq.NEXTVAL, ?, ?, ?, ?)
    """;  // La consulta SQL para insertar el usuario de la protectora

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cifProtectora);
            stmt.setString(2, contraseniaHasheada);  // Insertamos la contraseña hasheada usando BCrypt

            Date hoy = Date.valueOf(LocalDate.now());
            stmt.setDate(3, hoy);
            stmt.setDate(4, hoy);

            return stmt.executeUpdate() > 0; // Retorna true si se insertó correctamente
        }
    }

    public static boolean verificarContrasenia(int idUsuario, String contrasenia) throws SQLException {
        String sql = "SELECT Contrasenia FROM Usuarios WHERE ID = ?";  // Obtiene la contraseña hasheada desde la base de datos

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            var rs = stmt.executeQuery();
            if (rs.next()) {
                String contraseniaHasheada = rs.getString("Contrasenia");

                // Verifica si la contraseña introducida coincide con la hasheada usando BCrypt
                return BCrypt.checkpw(contrasenia, contraseniaHasheada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Retorna false si no se pudo verificar la contraseña
    }

}
