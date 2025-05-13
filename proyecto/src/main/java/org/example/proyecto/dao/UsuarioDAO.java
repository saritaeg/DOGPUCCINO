package org.example.proyecto.dao;

import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class UsuarioDAO {
    public static boolean registrarUsuario(int idCliente, String contrasenia) throws SQLException {
        String sql = """
            INSERT INTO Usuarios (ID, ID_Clientes, Contrasenia, Fecha_Alta, Fecha_Modificacion)
            VALUES (usuario_seq.NEXTVAL, ?, ?, ?, ?)
        """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement prueba = conn.prepareStatement(sql)) {

            prueba.setInt(1, idCliente);
            prueba.setString(2, contrasenia);


            Date hoy = Date.valueOf(LocalDate.now());
            prueba.setDate(3, hoy);
            prueba.setDate(4, hoy);

            return prueba.executeUpdate() > 0;
        }
    }
    public static boolean registrarUsuarioParaProtectora(String cifProtectora, String contrasenia) throws SQLException {
        String sql = """
        INSERT INTO Usuarios (ID, CIF_Protectoras, Contrasenia, Fecha_Alta, Fecha_Modificacion)
        VALUES (usuario_seq.NEXTVAL, ?, ?, ?, ?)
    """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cifProtectora);
            stmt.setString(2, contrasenia);

            Date hoy = Date.valueOf(LocalDate.now());
            stmt.setDate(3, hoy);
            stmt.setDate(4, hoy);

            return stmt.executeUpdate() > 0;
        }
    }

}
