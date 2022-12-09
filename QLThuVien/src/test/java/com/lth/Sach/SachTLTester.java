/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.Sach;

import com.lth.bojo.SachThanhLy;
import com.lth.conf.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


/**
 *
 * @author hunii
 */
public class SachTLTester {
    private static Connection conn;

    @BeforeAll
    public static void beforeAll() throws SQLException{
        conn = JdbcUtils.getConn();
    }
    @AfterAll
    public static void afterAll() throws SQLException {
        if (conn!=null) {
            conn.close();
        }
    }
    @Test
    public void testAddThanhLy() throws SQLException {
        Assertions.assertEquals(true, addThanhLy());
    }
    @Test
    public void testShowDSThanhLy() throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from sachthanhly");

        while (rs.next()){
            String name = rs.getString("TenSach");
            System.out.println(name);
        }
    }
    
    public boolean addThanhLy () throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            String sql = "INSERT INTO sachthanhly(MaSachTL, TenSach, TacGia, SoTrang, GiaBan)\n" +
"SELECT MaSach, TenSach , TenTacGia, SoTrang, GiaBia * 0.5  FROM sach\n" +
"WHERE MaSach not in (select MaSachTL from sachthanhly) and NamXuatBan <= date_sub(now(), interval 10 year)";
            stm.executeUpdate(sql);
            
            return true;
        } catch(Exception ex) {
            return false;
        }   
    }
}
