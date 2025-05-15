package org.example.proyecto.dao;

import org.example.proyecto.modelo.Perro;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PerrosCliDAO {

    public List<Perro> obtenerPerrosCli() {
        List<Perro> perros = new ArrayList<>();
        String sql = """
        SELECT p.ID, p.Nombre, p.Fecha_Nacimiento, p.Sexo, p.Adoptado, p.Raza, p.Foto,
               pr.Nombre AS nombre_protectora,
               pa.Nombre AS nombre_patologia
        FROM Perros p
        JOIN Protectoras pr ON p.CIF = pr.CIF
        LEFT JOIN Perros_Patologias pp ON p.ID = pp.ID_Perros
        LEFT JOIN Patologias pa ON pp.ID_Patologia = pa.ID
    """;

        try (Connection connection = ConexionBaseDatos.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Perro perro = new Perro(
                        resultSet.getInt("ID"),
                        resultSet.getString("Nombre"),
                        resultSet.getDate("Fecha_Nacimiento").toString(),
                        Perro.Adoptado.valueOf(resultSet.getString("Adoptado")),
                        Perro.Sexo.valueOf(resultSet.getString("Sexo")),
                        resultSet.getString("Raza"),
                        resultSet.getString("Foto"),
                        resultSet.getDate("Fecha_alta").toLocalDate(),
                        resultSet.getDate("Fecha_modificacion").toLocalDate()

                );

                // Si quieres guardar la patología en el modelo, añade un campo en la clase Perro:
                // perro.setPatologia(resultSet.getString("nombre_patologia"));

                perros.add(perro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return perros;
    }

}
