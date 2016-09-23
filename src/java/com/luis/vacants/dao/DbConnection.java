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
}
