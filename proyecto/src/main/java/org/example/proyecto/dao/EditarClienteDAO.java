package org.example.proyecto.dao;

import org.example.proyecto.modelo.Clientes;
import org.example.proyecto.utils.ConexionBaseDatos;
import org.example.proyecto.utils.Contraseña;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditarClienteDAO {
    public static Clientes obtenerClientePorEmail(String email) {
        Clientes cliente = null;
        String sql = "SELECT Nombre, Apellido1, Apellido2, Fecha_Nacimiento, Telefono, Calle, Ciudad, Correo_Electronico" +
                " FROM Clientes WHERE Correo_Electronico = ?";
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente = new Clientes();
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setApellido1(rs.getString("Apellido1"));
                cliente.setApellido2(rs.getString("Apellido2"));
                cliente.setFechaNacimiento(rs.getObject("Fecha_Nacimiento", java.time.LocalDate.class));
                cliente.setTelefono(rs.getString("Telefono"));
                cliente.setCalle(rs.getString("Calle"));
                cliente.setProvincia(rs.getString("Ciudad"));
                cliente.setEmail(rs.getString("Correo_Electronico"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public static boolean actualizarCliente(Clientes cliente, String emailOriginal) {
        String sql = "UPDATE Clientes SET Nombre=?, Apellido1=?, Apellido2=?, Fecha_Nacimiento=?, Ciudad=?, Calle=?, Telefono=?, Correo_Electronico=? WHERE Correo_Electronico=?";
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido1());
            ps.setString(3, cliente.getApellido2());
            ps.setObject(4, cliente.getFechaNacimiento());
            ps.setString(5, cliente.getProvincia());
            ps.setString(6, cliente.getCalle());
            ps.setString(7, cliente.getTelefono());
            ps.setString(8, cliente.getEmail());
            ps.setString(9, emailOriginal);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean actualizarContrasenaPorId(int idCliente, String nuevaContrasena) {
        String sql = """
        UPDATE Usuarios
        SET Contrasenia = ?, Fecha_Modificacion = ?
        WHERE ID_Clientes = ?
    """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String hashedPassword = Contraseña.encriptarContrasenia(nuevaContrasena);

            System.out.println("Actualizando contraseña para ID: " + idCliente);
            System.out.println("Contraseña (hashed): " + hashedPassword);

            ps.setString(1, hashedPassword);
            ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.setInt(3, idCliente);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Integer obtenerIdClientePorCorreo(String correo) {
        String sql = "SELECT ID FROM Clientes WHERE Correo_Electronico = ?";
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
