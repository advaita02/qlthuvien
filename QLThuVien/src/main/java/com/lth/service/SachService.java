/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.service;

import com.lth.bojo.Sach;
import com.lth.conf.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hunii
 */
public class SachService {
    public void addBook(Sach s) throws SQLException {
        try(Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO sach(MaSach, TenSach, TenTacGia, NamXuatBan, TinhTrang, SoTrang, GiaBia) values(?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, s.getMaSach());
            stm.setString(2, s.getTenSach());
            stm.setString(3, s.getTenTacGia());
            stm.setDate(4, s.getNamXB());
            stm.setInt(5, s.getTinhTrang());
            stm.setInt(6, s.getSoTrang());
            stm.setInt(7, s.getGiaBia());
            stm.executeUpdate();
            conn.commit();
        }
    }
    public void deleteBook(int id) throws SQLException {
//        try(Connection conn = JdbcUtils.getConn()) {
//            PreparedStatement stm = conn.prepareStatement("DELETE FROM sachthanhly where MaSachTL = ?");
//            stm.setInt(1, id);
//            
//            stm.executeUpdate();
//            conn.commit();
//        }
        try(Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("DELETE FROM sachthanhly where MaSachTL = ?");
            stm.setInt(1, id);

            stm.executeUpdate();

            PreparedStatement stm1 = conn.prepareStatement("DELETE FROM sach where MaSach = ?");
            stm1.setInt(1, id);

            stm1.executeUpdate();
            conn.commit();
        }
    }
    
    
    
    public List<Sach> getBook(String kw) throws SQLException {
         List<Sach> sachs = new ArrayList<>();
         try (Connection conn = JdbcUtils.getConn()) {
             String sql = "Select * from sach";
             if(kw != null && !kw.isEmpty()) {
                 sql += " WHERE TenSach like concat('%', ?, '%')";
             }
            PreparedStatement stm = conn.prepareStatement(sql);
            if(kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Sach s = new Sach(rs.getInt("MaSach"), rs.getString("TenSach"), 
                        rs.getString("TenTacGia"), rs.getDate("NamXuatBan"), 
                        rs.getInt("TinhTrang"), rs.getInt("SoTrang"),
                        rs.getInt("GiaBia")
                );
                sachs.add(s);
            }
         }
        return sachs;
    }
    
    public List<Sach> getSachCoTheMuon(String kw) throws SQLException {
         List<Sach> sachs = new ArrayList<>();
         try (Connection conn = JdbcUtils.getConn()) {
             String sql = "Select * from sach where TinhTrang = 1";
             if(kw != null && !kw.isEmpty()) {
                 sql += " WHERE TenSach like concat('%', ?, '%')";
             }
            PreparedStatement stm = conn.prepareStatement(sql);
            if(kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Sach s = new Sach(rs.getInt("MaSach"), rs.getString("TenSach"), 
                        rs.getString("TenTacGia"), rs.getDate("NamXuatBan"), 
                        rs.getInt("TinhTrang"), rs.getInt("SoTrang"),
                        rs.getInt("GiaBia")
                );
                sachs.add(s);
            }
         }
        return sachs;
    }
}
