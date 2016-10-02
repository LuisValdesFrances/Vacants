package com.luis.vacants.dao;

import com.luis.vacants.model.DbInfo;
import com.luis.vacants.model.User;
import com.luis.vacants.model.Vacant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class UserDao {

    DbConnection conn;
    
    public UserDao(DbConnection conn) {
        this.conn = conn;
    }
    
    public User login(String email, String pass){
        String sql = "select * from " + DbInfo.USER_TABLE + " where "
                + DbInfo.USER_TABLE_EMAIL + " =? and " 
                + DbInfo.USER_TABLE_PASS + " =? and "
                + DbInfo.USER_TABLE_STATUS + " ='active' limit 1";
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            User user = new User();
            while(rs.next()){
                user.setEmail(rs.getString(DbInfo.USER_TABLE_EMAIL));
                user.setPass(rs.getString(DbInfo.USER_TABLE_PASS));
                user.setName(rs.getString(DbInfo.USER_TABLE_NAME));
                user.setProfile(rs.getString(DbInfo.USER_TABLE_PROFILE));
                user.setStatus(rs.getString(DbInfo.USER_TABLE_STATUS));
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(VacantDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
