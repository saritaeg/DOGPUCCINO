package org.example.proyecto.dao;

import org.example.proyecto.modelo.ResultadoLogin;
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




}







