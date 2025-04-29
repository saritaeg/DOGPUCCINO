package org.example.proyecto.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBaseDatos {
    private static String URL = null;
    private static String USERNAME = null;
    private static String PASSWORD = null;

    private static ConexionBaseDatos instance;
    private ConexionBaseDatos() {
        Properties props = new Properties();

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
            if(is == null){
                throw new FileNotFoundException("config.properties no encontrado");
            }
            props.load(is);
            URL = props.getProperty("db.url");
            USERNAME = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static synchronized ConexionBaseDatos getInstance() {
        if (instance == null) {
            instance = new ConexionBaseDatos();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }
}
