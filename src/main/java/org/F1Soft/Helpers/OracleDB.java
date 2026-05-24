//package org.F1Soft.Helpers;
//
//import oracle.jdbc.OracleCallableStatement;
//import oracle.jdbc.OracleTypes;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// * @Author Subash Paudel
// * @Date 1/09/2020 10:25 PM
// * @Version 1.0
// */
//public class OracleDB {
//    private Connection con = null;
//    private Statement statement = null;
//    private ResultSet rs = null;
//
//    public Connection connectDB(String connString, String Username, String Password) {
//
//        try {
////step1 load the driver class
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//
////step2 create  the connection object
//            con = DriverManager.getConnection(connString, Username, Password);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return con;
//    }
//
//    public int closeDB() {
//        try {
//            if (con != null) {
//                con.close();
//            }
//            return 1;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//    public int closeResultSet() {
//        try {
//            if (rs != null) {
//                rs.close();
//            }
//            return 1;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//    public int closeStatement() {
//        try {
//            if (statement != null) {
//                statement.close();
//            }
//            return 1;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//    public ResultSet executeStatement(String Query) {
//        rs = null;
//        try {
//            statement = con.createStatement();
//
//            // execute query
//            rs = statement.executeQuery(Query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return rs;
//    }
//
//    public int getRowCount(ResultSet rs) throws SQLException {
//        int rowCount = 0;
//        while (rs.next()) {
//            // Process the row.
//            rowCount++;
//        }
//        try {
//            rs.close();
//        } catch (Exception ignore) {
//        }
//        return rowCount;
//    }
//
//    public ResultSet executeSP(Integer param1, Integer param2, Integer param3, String param4, Integer param5) {
//        CallableStatement cstmt = null;
//        ResultSet rs = null;
//        ArrayList<String[]> dsts = new ArrayList<String[]>();
//        try {
//            cstmt = con.prepareCall("{call SPName(?,?,?,?,?,?,?,?,?,?)}",
//                    ResultSet.TYPE_SCROLL_INSENSITIVE,
//                    ResultSet.CONCUR_READ_ONLY);
//            cstmt.setInt(1, param1);
//            cstmt.setInt(2, param2);
//            cstmt.setInt(3, param3);
//            cstmt.setString(4, param4);
//            cstmt.setInt(5, param5);
//            cstmt.setInt(6, 1);
//            cstmt.setString(7, "");
//            cstmt.setString(8, "");
//            cstmt.setString(9, "DATA");
//            cstmt.registerOutParameter(10, OracleTypes.CURSOR);
//
//            boolean results = cstmt.execute();
//            int rowsAffected = 0;
//
//            rs = (ResultSet) cstmt.getObject(10);
//            while (rs.next()) {
//                ResultSetMetaData rsmd = rs.getMetaData();
//                int noCol = rsmd.getColumnCount();
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(SqlDB.class.getName()).log(
//                    Level.SEVERE, null, ex);
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(SqlDB.class.getName()).log(
//                            Level.WARNING, null, ex);
//                }
//            }
//            if (cstmt != null) {
//                try {
//                    cstmt.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(SqlDB.class.getName()).log(
//                            Level.WARNING, null, ex);
//                }
//            }
//        }
//        return rs;
//    }
//}
