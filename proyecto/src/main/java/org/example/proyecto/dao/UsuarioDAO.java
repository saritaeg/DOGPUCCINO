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
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            stmt.setString(2, contrasenia);


            Date hoy = Date.valueOf(LocalDate.now());
            stmt.setDate(3, hoy);
            stmt.setDate(4, hoy);

            return stmt.executeUpdate() > 0;
        }
    }
}
