package com.luis.vacants.dao;

import java.sql.Connection;

/**
 *
 * @author Luis
 */
public class DbConnection {

    Connection conn = null;

    public Connection getConnection() {
        return conn;
    }

    public void disconnect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
