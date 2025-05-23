package org.example.proyecto.dao;

import org.example.proyecto.modelo.Clientes;
import org.example.proyecto.modelo.ResultadoLogin;
import org.example.proyecto.modelo.Usuario;
import org.example.proyecto.utils.Contraseña;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class UsuarioDAO {

    public static boolean registrarUsuario(int idCliente, String contrasenia) throws SQLException {
        String contraseniaHasheada = Contraseña.encriptarContrasenia(contrasenia);

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

    public static boolean registrarUsuarioParaProtectora(String cifProtectora, String contrasenia) throws SQLException {
        String contraseniaHasheada = Contraseña.encriptarContrasenia(contrasenia);

        String sql =
                """
        INSERT INTO Usuarios (ID, CIF_Protectoras, Contrasenia, Fecha_Alta,
                Fecha_Modificacion)
        VALUES (usuario_seq.
                NEXTVAL, ?, ?, ?, ?)
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

    public static boolean correoExiste(String correo) throws SQLException {
        String sql = """
            SELECT 1 FROM Clientes
                WHERE
                Correo_Electronico = ?
            UNION
            SELECT 1 FROM Protectoras WHERE Correo_Electronico = ?
        """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            stmt.setString(2, correo);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }


    }

    public static Usuario obtenerUsuarioPorCorreo(String correo) {
        String sql = """
        SELECT u.ID, u.Contrasenia, u.Rol, u.Fecha_Alta, u.Fecha_Modificacion,
               u.ID_Clientes, u.CIF_Protectoras
        FROM Usuarios u
        LEFT JOIN Clientes c ON u.ID_Clientes = c.ID
        LEFT JOIN Protectoras p ON u.CIF_Protectoras = p.CIF
        WHERE c.Correo_Electronico = ? OR p.Correo_Electronico = ?
    """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            stmt.setString(2, correo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("ID");
                    String contrasenia = rs.getString("Contrasenia");
                    String rolStr = rs.getString("Rol");
                    Usuario.Rol rol = Usuario.Rol.valueOf(rolStr.toUpperCase());
                    LocalDate fechaAlta = rs.getDate("Fecha_Alta").toLocalDate();
                    LocalDate fechaMod = rs.getDate("Fecha_Modificacion").toLocalDate();
                    Integer idCliente = rs.getObject("ID_Clientes") != null ? rs.getInt("ID_Clientes") : null;
                    String cif = rs.getString("CIF_Protectoras") != null ? rs.getString("CIF_Protectoras") : null;

                    return new Usuario(id, contrasenia, rol, fechaAlta, fechaMod, idCliente, cif);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Usuario obtenerClientePorCorreo(String correo) {
        String sql = """
        SELECT u.ID, u.Contrasenia, u.Rol, u.Fecha_Alta AS Fecha_Alta_Usuario,
               u.Fecha_Modificacion AS Fecha_Mod_Usuario,
               u.ID_Clientes, u.CIF_Protectoras,
               c.ID AS Cliente_ID, c.Nombre, c.Apellido1, c.Apellido2, c.Correo_Electronico,
               c.Telefono, c.Ciudad, c.Calle, c.Fecha_Nacimiento, c.Fecha_Alta AS Fecha_Alta_Cliente,
               c.Fecha_Modificacion AS Fecha_Mod_Cliente
        FROM Usuarios u
        LEFT JOIN Clientes c ON u.ID_Clientes = c.ID
        LEFT JOIN Protectoras p ON u.CIF_Protectoras = p.CIF
        WHERE c.Correo_Electronico = ? OR p.Correo_Electronico = ?
    """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            stmt.setString(2, correo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("ID");
                    String contrasenia = rs.getString("Contrasenia");
                    Usuario.Rol rol = Usuario.Rol.valueOf(rs.getString("Rol").toUpperCase());
                    LocalDate fechaAltaUsuario = rs.getDate("Fecha_Alta_Usuario").toLocalDate();
                    LocalDate fechaModUsuario = rs.getDate("Fecha_Mod_Usuario").toLocalDate();
                    Integer idCliente = rs.getObject("ID_Clientes") != null ? rs.getInt("ID_Clientes") : null;
                    String cif = rs.getString("CIF_Protectoras");

                    Clientes cliente = null;
                    if (idCliente != null) {
                        cliente = new Clientes(
                                rs.getInt("Cliente_ID"),
                                rs.getString("Nombre"),
                                rs.getString("Apellido1"),
                                rs.getString("Apellido2"),
                                rs.getString("Correo_Electronico"),
                                rs.getString("Telefono"),
                                rs.getString("Ciudad"),
                                rs.getString("Calle"),
                                rs.getDate("Fecha_Nacimiento").toLocalDate(),
                                rs.getDate("Fecha_Alta_Cliente").toLocalDate(),
                                rs.getDate("Fecha_Mod_Cliente").toLocalDate()
                        );
                    }

                    return new Usuario(id, contrasenia, rol, fechaAltaUsuario, fechaModUsuario, idCliente, cif, cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }





}







