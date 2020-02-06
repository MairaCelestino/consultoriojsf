package com.celestino.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionConsultorio {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("jdbc:mysql://localhost/consultorio_bd");
            System.out.println("Conectando ao banco");
            return DriverManager.getConnection("com.mysql.jdbc.Driver", "root", "root");

        } catch (ClassNotFoundException e) {
            System.out.println("Não conectado");
            throw new SQLException(e.getMessage());
        }
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws ConsultorioException {
        close(conn, stmt, rs);
    }

    public static void closeConnection(Connection conn, Statement stmt) throws ConsultorioException {
        close(conn, stmt, null);
    }

    public void closeConnection(Connection conn) throws ConsultorioException {
        close(conn, null, null);
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) throws ConsultorioException {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            throw new ConsultorioException(e.getMessage());
        }
    }
}
