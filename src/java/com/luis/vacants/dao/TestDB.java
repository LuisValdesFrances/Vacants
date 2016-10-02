package com.luis.vacants.dao;

import com.luis.vacants.model.User;

/**
 *
 * @author Luis
 */
public class TestDB {
    public static void main(String[] args){
        System.out.println("Test conexion a la base de datos init...");
        DbConnection conn = new DbConnection();
        System.out.println("Test userDao...");
        UserDao ud = new UserDao((conn));
        User user = ud.login("triskel67@hotmail.com", "a");
        System.out.println(user);
        conn.disconnect();
    }
}
