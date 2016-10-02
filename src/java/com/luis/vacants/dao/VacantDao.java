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
            ps.setString(1, vacant.getTitle());
            ps.setString(2, format.format(vacant.getDate()));
            ps.setString(3, vacant.getDescription());
            ps.setString(4, vacant.getDetail());
            ps.setInt(5, vacant.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VacantDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Vacant> getAll(){
        String sql = "select * from " + DbInfo.VACANT_TABLE + " order by "
                + DbInfo.VACANT_TABLE_ID + " desc";
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Vacant> list = new LinkedList<>();
            Vacant vacant;
            while(rs.next()){
                vacant = new Vacant(rs.getInt(DbInfo.VACANT_TABLE_ID));
                vacant.setDate(rs.getDate(DbInfo.VACANT_TABLE_DATE));
                vacant.setTitle(rs.getString(DbInfo.VACANT_TABLE_TITLE));
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
    
    public List<Vacant> getLasts(){
        String sql = "select * from " + DbInfo.VACANT_TABLE + " order by "
                + DbInfo.VACANT_TABLE_ID + " desc limit 3";
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Vacant> list = new LinkedList<>();
            Vacant vacant;
            while(rs.next()){
                vacant = new Vacant(rs.getInt(DbInfo.VACANT_TABLE_ID));
                vacant.setDate(rs.getDate(DbInfo.VACANT_TABLE_DATE));
                vacant.setTitle(rs.getString(DbInfo.VACANT_TABLE_TITLE));
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
    
    public Vacant getById(int idVacant){
        String sql = "select * from " + DbInfo.VACANT_TABLE + " where "
                + DbInfo.VACANT_TABLE_ID + " =? limit 1";
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setInt(1, idVacant);
            ResultSet rs = ps.executeQuery();
            Vacant vacant = new Vacant(0);
            while(rs.next()){
                vacant.setId(rs.getInt(DbInfo.VACANT_TABLE_ID));
                vacant.setDate(rs.getDate(DbInfo.VACANT_TABLE_DATE));
                vacant.setTitle(rs.getString(DbInfo.VACANT_TABLE_TITLE));
                vacant.setDescription(rs.getString(DbInfo.VACANT_TABLE_DESCRIPTION));
                vacant.setDetail(rs.getString(DbInfo.VACANT_TABLE_DETAIL));
            }
            return vacant;
        } catch (SQLException ex) {
            Logger.getLogger(VacantDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Vacant> getByQuery(String query){
        String sql = "select * from " + DbInfo.VACANT_TABLE + 
                " where (" + DbInfo.VACANT_TABLE_DESCRIPTION + " like ? or " + DbInfo.VACANT_TABLE_TITLE + " like ?) "
                + "order by " + DbInfo.VACANT_TABLE_ID + " desc";
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            ResultSet rs = ps.executeQuery();
            List<Vacant> list = new LinkedList<>();
            Vacant vacant;
            while(rs.next()){
                vacant = new Vacant(rs.getInt(DbInfo.VACANT_TABLE_ID));
                vacant.setDate(rs.getDate(DbInfo.VACANT_TABLE_DATE));
                vacant.setTitle(rs.getString(DbInfo.VACANT_TABLE_TITLE));
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
    
    public int deleteById(int idVacant){
        try {
            String sql = "delete from " + DbInfo.VACANT_TABLE + " where "
                    + DbInfo.VACANT_TABLE_ID + " =?";
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setInt(1, idVacant);
            int rows = ps.executeUpdate();
            return rows;
        } 
        catch (SQLException ex) {
            Logger.getLogger(VacantDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    
}
