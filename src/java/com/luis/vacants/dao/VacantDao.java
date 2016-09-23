package com.luis.vacants.dao;

import com.luis.vacants.model.DbInfo;
import com.luis.vacants.model.Vacant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lvaldes
 */
public class VacantDao {
    
    DbConnection conn;

    public VacantDao(DbConnection conn) {
        this.conn = conn;
    }
    
    public boolean insert(Vacant vacant){
        String sql = "insert into "+ DbInfo.VACANT_TABLE + " values (?,?,?,?,?)";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setInt(1, vacant.getId());
            ps.setString(2, format.format(vacant.getDate()));
            ps.setString(3, vacant.getName());
            ps.setString(4, vacant.getDescription());
            ps.setString(1, vacant.getDetail());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VacantDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Vacant> getLasts(){
        String sql = "select * from " + DbInfo.VACANT_TABLE + " ordey by " 
                + DbInfo.VACANT_TABLE_ID + " desc limit 3";
        PreparedStatement ps;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Vacant> list = new LinkedList<>();
            Vacant vacant;
            while(rs.next()){
                vacant = new Vacant(rs.getInt(DbInfo.VACANT_TABLE_ID));
                vacant.setDate(rs.getDate(DbInfo.VACANT_TABLE_DATE));
                vacant.setName(rs.getString(DbInfo.VACANT_TABLE_NAME));
                vacant.setDescription(rs.getString(DbInfo.VACANT_TABLE_DESCRIPTION));
                vacant.setDetail(rs.getString(DbInfo.VACANT_TABLE_DETAIL));
                list.add(vacant);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(VacantDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
        
    }
}
