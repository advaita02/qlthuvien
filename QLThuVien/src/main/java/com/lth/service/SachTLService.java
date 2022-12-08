/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.service;

import com.lth.bojo.SachThanhLy;
import com.lth.conf.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hunii
 */
public class SachTLService {
    public void get10Nam() throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            String query = "";

        }
    }
    
    public List<SachThanhLy> getThanhLy(String kw) throws SQLException {
         List<SachThanhLy> thanhlys= new ArrayList<>();
         try (Connection conn = JdbcUtils.getConn()) {
             String sql = "Select * from sachthanhly";
             if(kw != null && !kw.isEmpty()) {
                 sql += " WHERE TenSach like concat('%', ?, '%')";
             }
            PreparedStatement stm = conn.prepareStatement(sql);
            if(kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                SachThanhLy e = new SachThanhLy(rs.getInt("MaSachTL"), 
                        rs.getString("TenSach"), 
                        rs.getString("TacGia"),
                        rs.getInt("SoTrang"),
                        rs.getInt("GiaBan")
                );
                thanhlys.add(e);
            }
         }
        return thanhlys;
    }
}
