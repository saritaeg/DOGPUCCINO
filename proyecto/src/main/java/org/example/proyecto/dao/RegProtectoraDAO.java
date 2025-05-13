package org.example.proyecto.dao;

import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class RegProtectoraDAO {

    public static boolean registrarProtectora(
            String cif, String nombre, String calle, String ciudad,
            String correo, String telefono, String redes
    ) throws SQLException {

        String sql = """
            INSERT INTO Protectoras (
                CIF, Nombre, Calle, Ciudad, Correo_Electronico, Telefono, Redes_Sociales,
                Fecha_Alta, Fecha_Modificacion
            ) VALUES (
                ?, ?, ?, ?, ?, ?, ?, ?, ?
            )
        """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement protectora = conn.prepareStatement(sql)) {

            Date hoy = Date.valueOf(LocalDate.now());

            protectora.setString(1, cif);
            protectora.setString(2, nombre);
            protectora.setString(3, calle);
            protectora.setString(4, ciudad);
            protectora.setString(5, correo);
            protectora.setString(6, telefono);
            protectora.setString(7, redes);
            protectora.setDate(8, hoy);
            protectora.setDate(9, hoy);


            int rows = protectora.executeUpdate();
            return rows > 0;
        }
    }
}
