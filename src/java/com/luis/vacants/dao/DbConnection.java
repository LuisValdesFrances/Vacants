package com.luis.vacants.dao;

import com.luis.vacants.model.DbInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class DbConnection {

    private Connection conn = null;
    
    public DbConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DbInfo.URL + DbInfo.DATABASE, DbInfo.USER, DbInfo.PASSWORD);
            if(conn != null){
                System.out.println("Conexion a la base de datos: " + conn.toString() + " OK");
            }else{
                System.out.println("No de puede conectar a la base de datos");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error: jdbc.Driver no encontrado [" + ex.getMessage() + "]");
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error: Conexion a la base de datos [" + ex.getMessage() + "]");
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
