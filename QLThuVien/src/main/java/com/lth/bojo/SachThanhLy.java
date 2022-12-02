/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.bojo;

/**
 *
 * @author lth7p
 */
public class SachThanhLy {
    private int maSach_ThanhLy;
    private String tenSach;
    private String tacGia;
    private String soTrang;
    private int giaBan;

    public SachThanhLy() {
    }

    public SachThanhLy(int maSach_ThanhLy, String tenSach, String tacGia, String soTrang, int giaBan) {
        this.maSach_ThanhLy = maSach_ThanhLy;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.soTrang = soTrang;
        this.giaBan = giaBan;
    }
    
    

    /**
     * @return the maSach_ThanhLy
     */
    public int getMaSach_ThanhLy() {
        return maSach_ThanhLy;
    }

    /**
     * @param maSach_ThanhLy the maSach_ThanhLy to set
     */
    public void setMaSach_ThanhLy(int maSach_ThanhLy) {
        this.maSach_ThanhLy = maSach_ThanhLy;
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
     * @return the tacGia
     */
    public String getTacGia() {
        return tacGia;
    }

    /**
     * @param tacGia the tacGia to set
     */
    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    /**
     * @return the soTrang
     */
    public String getSoTrang() {
        return soTrang;
    }

    /**
     * @param soTrang the soTrang to set
     */
    public void setSoTrang(String soTrang) {
        this.soTrang = soTrang;
    }

    /**
     * @return the giaBan
     */
    public int getGiaBan() {
        return giaBan;
    }

    /**
     * @param giaBan the giaBan to set
     */
    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }
}
