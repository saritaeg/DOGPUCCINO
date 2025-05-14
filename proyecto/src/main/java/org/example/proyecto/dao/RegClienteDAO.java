
package org.example.proyecto.dao;

import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.*;
import java.time.LocalDate;

public class RegClienteDAO {

    public static int registrarCliente(
            String nombre, String apellido1, String apellido2, Date fechaNacimiento,
            String telefono, String calle, String ciudad, String correo_electronico
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
             PreparedStatement prueba = conn.prepareStatement(sql);
             PreparedStatement pruebaID = conn.prepareStatement(sqlID)) {

            prueba.setString(1, nombre);
            prueba.setString(2, apellido1);
            prueba.setString(3, apellido2);
            prueba.setDate(4, fechaNacimiento);
            prueba.setString(5, telefono);
            prueba.setString(6, calle);
            prueba.setString(7, ciudad);
            prueba.setString(8, correo_electronico);

            Date hoy = Date.valueOf(LocalDate.now());
            prueba.setDate(9, hoy);
            prueba.setDate(10, hoy);

            int rows = prueba.executeUpdate();

            if (rows > 0) {
                try (ResultSet rs = pruebaID.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
            return -1;
        }
    }
}
