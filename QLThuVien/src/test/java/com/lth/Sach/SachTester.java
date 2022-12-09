/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.Sach;
import com.lth.bojo.Sach;
import com.lth.service.SachService;
import com.lth.conf.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author hunii
 */
public class SachTester {
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
    public void testAddBook1() throws SQLException, ParseException {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String d = "2022/9/9";
        LocalDate lcDate = LocalDate.parse(d);
        java.sql.Date date = java.sql.Date.valueOf(lcDate);
//        String d = "2022/9/9";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//        java.util.Date date = sdf.parse(d);
//        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Sach s = new Sach(30, "Football", "Messi", date, 1, 235, 923123);
        Assertions.assertEquals(true, addBook(s));
    }
    @Test
    public void testAddBook2() throws SQLException, ParseException {
        String d = "2001/2/9";
        LocalDate lcDate = LocalDate.parse(d);
        java.sql.Date date = java.sql.Date.valueOf(lcDate);
        Sach s = new Sach(98, "Hich Tuong Si", "Quoc Tuan", date, 1, 280, 90000000);
        Assertions.assertEquals(true, addBook(s));
    }
    @Test
    public void testAddBook3() throws SQLException, ParseException {
        String d = "2001/2/30";
        LocalDate lcDate = LocalDate.parse(d);
        java.sql.Date date = java.sql.Date.valueOf(lcDate);
        Sach s = new Sach(50, "Mat biec", "NNA", date, 1, 200, 9000);
        Assertions.assertEquals(true, addBook(s));
    }
    
    @Test
    public void testDeleteBook() throws SQLException {
        Assertions.assertEquals(true, deleteBook(-1));
    }
    @Test
    public void testDeleteBook1() throws SQLException {
        Assertions.assertEquals(true, deleteBook(19));
    }
    @Test
    public void testDeleteBook2() throws SQLException {
        Assertions.assertEquals(true, deleteBook(100000000));
    }
    
    @Test
    public void testListSach() throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM sach");

        while (rs.next()){
            String name = rs.getString("TenSach");
            System.out.println(name);
        }
    }
    
    @Test
    public void testListCoTheMuon() throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from sach where TinhTrang = 1");

        while (rs.next()){
            String name = rs.getString("TenSach");
            System.out.println(name);
        }
    }
    
    public boolean addBook(Sach s) throws SQLException {
        try(Connection conn1 = JdbcUtils.getConn()) {
            PreparedStatement stm = conn1.prepareStatement("INSERT INTO sach(MaSach, TenSach, TenTacGia, NamXuatBan, TinhTrang, SoTrang, GiaBia) values(?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, s.getMaSach());
            stm.setString(2, s.getTenSach());
            stm.setString(3, s.getTenTacGia());
            stm.setDate(4, s.getNamXB());
            stm.setInt(5, s.getTinhTrang());
            stm.setInt(6, s.getSoTrang());
            stm.setInt(7, s.getGiaBia());
            stm.executeUpdate();
            return true;
        }
        catch(Exception ex) {
            return false;
        }
    }
    public boolean deleteBook(int id) throws SQLException {
        try(Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("DELETE FROM sachthanhly where MaSachTL = ?");
            stm.setInt(1, id);

            stm.executeUpdate();
            
            PreparedStatement stm2 = conn.prepareStatement("DELETE FROM muontra where MaSach = ?");
            stm2.setInt(1, id);
            
            stm2.executeUpdate();

            PreparedStatement stm1 = conn.prepareStatement("DELETE FROM sach where MaSach = ?");
            stm1.setInt(1, id);

            stm1.executeUpdate();
            
            return true;
        } catch(Exception ex) {
            return false;
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
