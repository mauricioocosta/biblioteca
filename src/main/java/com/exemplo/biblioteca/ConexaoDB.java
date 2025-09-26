package main.java.com.exemplo.biblioteca;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca_db"; 
    private static final String USER = "root"; // altere para seu usuário
    private static final String PASSWORD = "root"; // altere para sua senha

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}