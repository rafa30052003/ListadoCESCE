package com.rioma.listacesce.conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    private static Connect _newInstace;
    private static Connection con;

    /**
     * Método para establecer la conexión a la base de datos SQL Server.
     * Este método se encarga de cargar el controlador JDBC, establecer la conexión y manejar posibles errores.
     */
    private Connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String server = "RIO-WFC02SQL01";
            String database = "P856_DATA";
            String username = "appjava";
            String password = "fuS3jSNZ";
            String connectionString = "jdbc:sqlserver://" + server + ";databaseName=" + database;
            con = DriverManager.getConnection(connectionString, username, password);
            System.out.println("Conexión exitosa.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión: " + e.getMessage());
        }
    }

    public static Connection getConnect() {
        if(_newInstace == null) {
            _newInstace = new Connect();

        }
        return con;
    }


}