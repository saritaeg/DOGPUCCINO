package org.example.proyecto.dao;

import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class RegClienteDAO {

    public static boolean registrarCliente(
            String nombre, String apellido1, String apellido2, Date fechaNacimiento,
            String telefono, String calle, String ciudad, String correo
    ) throws SQLException {

        String sql = """
            INSERT INTO Cliente (
                ID, Nombre, Apellido1, Apellido2, Fecha_Nacimiento, Telefono,
                Calle, Ciudad, Correo_Electronico, Fecha_Alta, Fecha_Modificacion
            ) VALUES (
                cliente_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
            )
        """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, apellido1);
            stmt.setString(3, apellido2);
            stmt.setDate(4, fechaNacimiento);
            stmt.setString(5, telefono);
            stmt.setString(6, calle);
            stmt.setString(7, ciudad);
            stmt.setString(8, correo);

            Date hoy = Date.valueOf(LocalDate.now());
            stmt.setDate(9, hoy);  // Fecha_Alta
            stmt.setDate(10, hoy); // Fecha_Modificacion

            return stmt.executeUpdate() > 0;
        }
    }
}

