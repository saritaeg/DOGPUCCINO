package org.example.proyecto.dao;

import org.example.proyecto.modelo.Cita;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CitasDAO {
    public static boolean insertarCita(Cita cita) {
        String sql = "INSERT INTO Reservan (Cliente_ID, Perro_ID, Donacion, Hora, Fecha_cita, Estado, Fecha_alta, Fecha_modificacion) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            System.out.println("Insertando cita: Cliente_ID=" + cita.getClienteId() + ", Perro_ID=" + cita.getPerroId()
                    + ", Donacion=" + cita.getDonacion() + ", Hora=" + cita.getHora() + ", Fecha_cita=" + cita.getFechaCita()
                    + ", Estado=" + cita.getEstado());

            stmt.setInt(1, cita.getClienteId());
            stmt.setInt(2, cita.getPerroId());
            stmt.setString(3, cita.getDonacion());
            stmt.setInt(4, cita.getHora());
            stmt.setDate(5, Date.valueOf(cita.getFechaCita()));
            stmt.setString(6, cita.getEstado());
            stmt.setDate(7, Date.valueOf(cita.getFechaAlta()));
            stmt.setDate(8, Date.valueOf(cita.getFechaModificacion()));

            int filas = stmt.executeUpdate();
            System.out.println("Filas afectadas: " + filas);
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
