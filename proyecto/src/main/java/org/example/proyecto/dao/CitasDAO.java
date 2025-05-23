package org.example.proyecto.dao;

import org.example.proyecto.modelo.Cita;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitasDAO {

    public static boolean insertarCita(Cita cita) {
        String sql = "INSERT INTO Reservan (Cliente_ID, Perro_ID, Donacion, Hora, Fecha_cita, Estado, Fecha_alta, Fecha_modificacion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cita.getClienteId());
            stmt.setInt(2, cita.getPerroId());
            stmt.setString(3, cita.getDonacion());
            stmt.setInt(4, cita.getHora());
            stmt.setDate(5, Date.valueOf(cita.getFechaCita()));
            stmt.setString(6, cita.getEstado());
            stmt.setDate(7, Date.valueOf(cita.getFechaAlta()));
            stmt.setDate(8, Date.valueOf(cita.getFechaModificacion()));

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Cita> obtenerCitasPorProtectora(String cifProtectora) {
        List<Cita> citas = new ArrayList<>();

        String sql = "SELECT r.Cliente_ID, r.Perro_ID, r.Donacion, r.Hora, r.Fecha_cita, r.Estado, " +
                "r.Fecha_alta, r.Fecha_modificacion " +
                "FROM Reservan r " +
                "JOIN Perros p ON r.Perro_ID = p.ID " +
                "WHERE p.CIF = ?";

        try (Connection con = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, cifProtectora);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cita cita = new Cita();
                    cita.setClienteId(rs.getInt("Cliente_ID"));
                    cita.setPerroId(rs.getInt("Perro_ID"));
                    cita.setDonacion(rs.getString("Donacion"));
                    cita.setHora(rs.getInt("Hora"));
                    cita.setFechaCita(rs.getDate("Fecha_cita").toLocalDate());
                    cita.setEstado(rs.getString("Estado"));

                    Date fechaAlta = rs.getDate("Fecha_alta");
                    Date fechaMod = rs.getDate("Fecha_modificacion");

                    cita.setFechaAlta(fechaAlta != null ? fechaAlta.toLocalDate() : null);
                    cita.setFechaModificacion(fechaMod != null ? fechaMod.toLocalDate() : null);

                    citas.add(cita);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return citas;
    }
}
