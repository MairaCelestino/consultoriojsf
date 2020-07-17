package com.celestino.model.util;

import java.sql.*;

public class ConnectionClinic {

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    //"jdbc:mysql://localhost:3306/CLINIC_DB?useTimezone=true&serverTimezone=UTC", "root", "M@ira160491");
                    "jdbc:mysql://localhost:3306/CLINIC_DB?useTimezone=true&serverTimezone=UTC", "root", "root");
        } catch (ClassNotFoundException e) {
            System.out.println("Not connected!!");
            throw new SQLException(e.getMessage());
        }
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws ClinicException {
        close(conn, stmt, rs);
    }

    public static void closeConnection(Connection conn, Statement stmt) throws ClinicException {
        close(conn, stmt, null);
    }

    public void closeConnection(Connection conn) throws ClinicException {
        close(conn, null, null);
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) throws ClinicException {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            throw new ClinicException(e.getMessage());
        }
    }
}
