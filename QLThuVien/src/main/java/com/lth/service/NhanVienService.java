/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.service;

import com.lth.bojo.NhanVien;
import com.lth.conf.JdbcUtils;
import com.lth.conf.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

/**
 *
 * @author lth7p
 */
public class NhanVienService {
    public void themNhanVien(NhanVien nv) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()){
            PreparedStatement stm1= conn.prepareStatement("INSERT INTO nhanvien(MaNV, HoTenNV, TenDN, MatKhau) VALUES(?, ?, ?, ?)");
            stm1.setInt(1, nv.getMaNV());
            stm1.setString(2, nv.getHoTenNV());
            stm1.setString(3, nv.getTenDN());
            stm1.setString(4, nv.getMatKhau());
            
            stm1.executeUpdate();
        }
    }
    public boolean kiemtraDangNhap(String user,String pwd) throws SQLException{            
        try (Connection conn = JdbcUtils.getConn()){
            Statement stm= conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien");
            
            while(rs.next()){
                if(user.equals(rs.getString("TenDN"))==true&&pwd.equals(rs.getString("MatKhau"))==true){
                    return true;
                }
            }
        }
        return false;
    }
}
