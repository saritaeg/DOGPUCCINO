package org.example.proyecto.dao;
import org.example.proyecto.modelo.Perro;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PerroDAO {
    public void insertarPerro(Perro perro) {
        String sql = "INSERT INTO perros (nombre, fecha_nacimiento, sexo, raza, patologia, descripcion_patologia) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, perro.getNombre());
            stmt.setString(2, perro.getFechaNacimiento());
            stmt.setString(3, perro.getSexo());
            stmt.setString(4, perro.getRaza());
            stmt.setString(5, perro.getPatologia());
            stmt.setString(6, perro.getDescripcionPatologia());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Perro> obtenerTodos() {
        List<Perro> perros = new ArrayList<>();
        String sql = "SELECT * FROM perros";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Perro perro = new Perro();
                perro.setId(rs.getInt("id"));
                perro.setNombre(rs.getString("nombre"));
                perro.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                perro.setSexo(rs.getString("sexo"));
                perro.setRaza(rs.getString("raza"));
                perro.setPatologia(rs.getString("patologia"));
                perro.setDescripcionPatologia(rs.getString("descripcion_patologia"));
                perros.add(perro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return perros;
    }

}
