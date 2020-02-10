package com.celestino.model.util;

import java.sql.*;

public class ConnectionConsultorio {

    public static Connection getConnection() throws SQLException {

        try {
            System.out.println("Conectando ao banco");
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CLINIC_DB?useTimezone=true&serverTimezone=UTC", "root", "root");
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
