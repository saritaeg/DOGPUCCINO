
package org.example.proyecto.dao;

import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.*;
import java.time.LocalDate;

public class RegClienteDAO {

    public static int registrarCliente(
            String nombre, String apellido1, String apellido2, Date fechaNacimiento,
            String telefono, String calle, String ciudad, String correo
    ) throws SQLException {

        String sql = """
            INSERT INTO Clientes (
                ID, Nombre, Apellido1, Apellido2, Fecha_Nacimiento, Telefono,
                Calle, Ciudad, Correo_Electronico, Fecha_Alta, Fecha_Modificacion
            ) VALUES (
                cliente_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
            )
        """;

        String sqlID = "SELECT cliente_seq.CURRVAL FROM dual";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             PreparedStatement stmtId = conn.prepareStatement(sqlID)) {

            stmt.setString(1, nombre);
            stmt.setString(2, apellido1);
            stmt.setString(3, apellido2);
            stmt.setDate(4, fechaNacimiento);
            stmt.setString(5, telefono);
            stmt.setString(6, calle);
            stmt.setString(7, ciudad);
            stmt.setString(8, correo);

            Date hoy = Date.valueOf(LocalDate.now());
            stmt.setDate(9, hoy);
            stmt.setDate(10, hoy);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                try (ResultSet rs = stmtId.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
            return -1;
        }
    }
}
