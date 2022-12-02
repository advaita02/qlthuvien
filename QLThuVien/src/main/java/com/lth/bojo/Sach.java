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
public class Sach {
    private int maSach;
    private String tenSach;
    private String tenTacGia;
    private Date NamXB;
    private int tinhTrang;
    private int soTrang; 

    public Sach() {
    }

    public Sach(String tenSach, String tenTacGia, Date NamXB, int tinhTrang, int soTrang) {
//        this.maSach = count.incrementAndGet();
//        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tenTacGia = tenTacGia;
        this.NamXB = NamXB;
        this.tinhTrang = tinhTrang;
        this.soTrang = soTrang;
    }
    
    

    /**
     * @return the maSach
     */
    public int getMaSach() {
        return maSach;
    }

    /**
     * @param maSach the maSach to set
     */
    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    /**
     * @return the tenSach
     */
    public String getTenSach() {
        return tenSach;
    }

    /**
     * @param tenSach the tenSach to set
     */
    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    /**
     * @return the tenTacGia
     */
    public String getTenTacGia() {
        return tenTacGia;
    }

    /**
     * @param tenTacGia the tenTacGia to set
     */
    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    /**
     * @return the NamXB
     */
    public Date getNamXB() {
        return NamXB;
    }

    /**
     * @param NamXB the NamXB to set
     */
    public void setNamXB(Date NamXB) {
        this.NamXB = NamXB;
    }

    /**
     * @return the tinhTrang
     */
    public int getTinhTrang() {
        return tinhTrang;
    }

    /**
     * @param tinhTrang the tinhTrang to set
     */
    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    /**
     * @return the soTrang
     */
    public int getSoTrang() {
        return soTrang;
    }

    /**
     * @param soTrang the soTrang to set
     */
    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }
    
}
