package it.uniroma2.bd.Model.DAO;


import it.uniroma2.bd.Model.Domain.Role;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.DriverManager;


public class ConnectionSingleton {

    public static ConnectionSingleton instance;
    private Connection connection;


    private ConnectionSingleton() {
        try {
            InputStream input = new FileInputStream("resources/db.properties");
            Properties properties = new Properties();
            properties.load(input);

            String URL = properties.getProperty("CONNECTION_URL");
            String Username = properties.getProperty("LOGIN_USER");
            String Password = properties.getProperty("LOGIN_PASSWORD");
            
            
            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            }catch(ClassNotFoundException e) {
            	throw new RuntimeException(e);
            }
            this.connection = DriverManager.getConnection(URL, Username, Password);

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


    }


    public static ConnectionSingleton getInstance() {
        if (instance == null) {
            instance = new ConnectionSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void changeRole(Role role) {
        try {
            connection.close();

            InputStream input = new FileInputStream("resources/db.properties");
            Properties properties = new Properties();
            properties.load(input);
            
            
            String URL = properties.getProperty("CONNECTION_URL");
            String Username = properties.getProperty(role.name() + "_USER");
            String Password = properties.getProperty(role.name() + "_PASSWORD");
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, Username, Password);
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}