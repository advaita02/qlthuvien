/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.service;

import com.lth.bojo.MuonTra;
import com.lth.bojo.Sach;
import com.lth.bojo.SachThanhLy;
import com.lth.conf.JdbcUtils;
import java.sql.Connection;
import java.sql.Date;
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
public class MuonTraService {
//    public List<MuonTra> getMuonTra(String kw) throws SQLException {
//        List<MuonTra> muontras = new ArrayList<>();
//        try (Connection conn = JdbcUtils.getConn()) {
//            String sql = "Select * from muontra";
//            if (kw != null && !kw.isEmpty()) {
//                sql += " WHERE TenSach like concat('%', ?, '%')";
//            }
//            PreparedStatement stm = conn.prepareStatement(sql);
//            if (kw != null && !kw.isEmpty()) {
//                stm.setString(1, kw);
//            }
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                MuonTra e = new MuonTra(rs.getInt("maSach_MuonTra"),
//                        rs.getDate("ngayMuonDate"),
//                        rs.getString("hoTenNguoiMuon"),
//                        rs.getInt("soCCCD"),
//                        rs.getString("soDTNguoiMuon"),
//                        rs.getString("nVLap")
//                );
//                muontras.add(e);
//            }
//        }
//        return muontras;
//    }

    public List<MuonTra> getSachDangMuon(String kw) throws SQLException {
        List<MuonTra> muons = new ArrayList<>();
        try ( Connection conn = JdbcUtils.getConn()) {
            String sql = "Select * from muontra";
//             if(kw != null && !kw.isEmpty()) {
//                 sql += " WHERE NVLap like concat('%', ?, '%')";
//             }
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                MuonTra s = new MuonTra(rs.getInt("MaSach"), rs.getDate("NgayMuon"),
                        rs.getString("HoTenNguoiMuon"), rs.getInt("SoCCCD"),
                        rs.getString("SDTNguoiMuon"), rs.getInt("NVLap")
                );
                muons.add(s);
            }
        }
        return muons;
    }

    public void addMuonTra(MuonTra m) throws SQLException {
        try ( Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO muontra(MaSach, NgayMuon, HoTenNguoiMuon, SoCCCD, SDTNguoiMuon, NVLap) values(?, ?, ?, ?, ?, ?)");
            stm.setInt(1, m.getMaSach_MuonTra());
            stm.setDate(2, m.getNgayMuonDate());
            stm.setString(3, m.getHoTenNguoiMuon());
            stm.setInt(4, m.getSoCCCD());
            stm.setString(5, m.getSoDTNguoiMuon());
            stm.setInt(6, m.getnVLap());

            stm.executeUpdate();
            
        }
    }

    public boolean checkMaSach(int id) throws SQLException {
        try ( Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM sach where TinhTrang = 1");

            while (rs.next()) {
                if (id == (rs.getInt("MaSach"))) {
                    PreparedStatement stm1 = conn.prepareStatement("update sach set sach.TinhTrang = 0 where sach.MaSach = ?");
                    stm1.setInt(1, id);
                    stm1.executeUpdate();

                    return true;
                }
            }
            
        }
        return false;
    }

    public boolean checkSachMuon(int id) throws SQLException {
        try ( Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM muontra");

            while (rs.next()) {
                if (id == (rs.getInt("MaSach"))) {
                    PreparedStatement stm2 = conn.prepareStatement("update sach set sach.TinhTrang = 1 where sach.MaSach = ?");
                    stm2.setInt(1, id);
                    stm2.executeUpdate();

                    PreparedStatement stm3 = conn.prepareStatement("DELETE from muontra where MaSach = ?");
                    stm3.setInt(1, id);
                    stm3.executeUpdate();

                    return true;
                }
            }
            conn.commit();
        }
        return false;
    }
    public boolean kiemTraTreHan(int id) throws SQLException {
        try ( Connection conn = JdbcUtils.getConn()) {
//            PreparedStatement stm1 = conn.prepareStatement("select MaSach from muontra \n" +
//"where NgayMuon <= date_sub(now(), interval 15 day)");
//            stm1.setInt(1, id);
            Statement stm1 = conn.createStatement();
            ResultSet rs = stm1.executeQuery("select * from muontra where NgayMuon = date_sub(now(), interval 15 day)");
            while (rs.next()) {
                if (rs.getInt("MaSach")==id) {
                    return true;
                }
            }
        }
        return false;
    }

}
