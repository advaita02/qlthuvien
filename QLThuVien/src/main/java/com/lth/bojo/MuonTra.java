/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.bojo;

import java.sql.Date;

/**
 *
 * @author lth7p
 */
public class MuonTra {
    private int maSach_MuonTra;
    private Date ngayMuonDate;
    private String hoTenNguoiMuon;
    private int soCCCD;
    private char soDTNguoiMuon;
    private int nVLap;

    public MuonTra() {
    }

    public MuonTra(int maSach_MuonTra, Date ngayMuonDate, String hoTenNguoiMuon, int soCCCD, char soDTNguoiMuon, int nVLap) {
        this.maSach_MuonTra = maSach_MuonTra;
        this.ngayMuonDate = ngayMuonDate;
        this.hoTenNguoiMuon = hoTenNguoiMuon;
        this.soCCCD = soCCCD;
        this.soDTNguoiMuon = soDTNguoiMuon;
        this.nVLap = nVLap;
    }
    
    

    /**
     * @return the maSach_MuonTra
     */
    public int getMaSach_MuonTra() {
        return maSach_MuonTra;
    }

    /**
     * @param maSach_MuonTra the maSach_MuonTra to set
     */
    public void setMaSach_MuonTra(int maSach_MuonTra) {
        this.maSach_MuonTra = maSach_MuonTra;
    }

    /**
     * @return the ngayMuonDate
     */
    public Date getNgayMuonDate() {
        return ngayMuonDate;
    }

    /**
     * @param ngayMuonDate the ngayMuonDate to set
     */
    public void setNgayMuonDate(Date ngayMuonDate) {
        this.ngayMuonDate = ngayMuonDate;
    }

    /**
     * @return the hoTenNguoiMuon
     */
    public String getHoTenNguoiMuon() {
        return hoTenNguoiMuon;
    }

    /**
     * @param hoTenNguoiMuon the hoTenNguoiMuon to set
     */
    public void setHoTenNguoiMuon(String hoTenNguoiMuon) {
        this.hoTenNguoiMuon = hoTenNguoiMuon;
    }

    /**
     * @return the soCCCD
     */
    public int getSoCCCD() {
        return soCCCD;
    }

    /**
     * @param soCCCD the soCCCD to set
     */
    public void setSoCCCD(int soCCCD) {
        this.soCCCD = soCCCD;
    }

    /**
     * @return the soDTNguoiMuon
     */
    public char getSoDTNguoiMuon() {
        return soDTNguoiMuon;
    }

    /**
     * @param soDTNguoiMuon the soDTNguoiMuon to set
     */
    public void setSoDTNguoiMuon(char soDTNguoiMuon) {
        this.soDTNguoiMuon = soDTNguoiMuon;
    }

    /**
     * @return the nVLap
     */
    public int getnVLap() {
        return nVLap;
    }

    /**
     * @param nVLap the nVLap to set
     */
    public void setnVLap(int nVLap) {
        this.nVLap = nVLap;
    }
}
