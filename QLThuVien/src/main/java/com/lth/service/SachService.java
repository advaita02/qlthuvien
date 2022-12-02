/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.service;

import com.lth.bojo.Sach;
import com.lth.conf.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author hunii
 */
public class SachService {
    public void addBook(Sach s) throws SQLException {
        try(Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO sach(MaSach, TenSach, TenTacGia, NamXuatBan, TinhTrang, SoTrang) values(?, ?, ?, ?, ?, ?)");
            stm.setInt(1, s.getMaSach());
            stm.setString(2, s.getTenSach());
            stm.setString(3, s.getTenTacGia());
            stm.setDate(4, s.getNamXB());
            stm.setInt(5, s.getTinhTrang());
            stm.setInt(6, s.getSoTrang());
            
            stm.executeUpdate();
            conn.commit();
        }
    }
}
