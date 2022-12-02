/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lth.bojo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author lth7p
 */
public class NhanVien {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int maNV;
    private String hoTenNV;
    private String tenDN;
    private String matKhau;

    public NhanVien() {
    }

    public NhanVien(int maNV, String hoTenNV, String tenDN, String matKhau) {
        this.maNV = count.incrementAndGet();
        this.hoTenNV = hoTenNV;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
    }
    
    

    /**
     * @return the maNV
     */
    public int getMaNV() {
        return maNV;
    }

    /**
     * @param maNV the maNV to set
     */
    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    /**
     * @return the hoTenNV
     */
    public String getHoTenNV() {
        return hoTenNV;
    }

    /**
     * @param hoTenNV the hoTenNV to set
     */
    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    /**
     * @return the tenDN
     */
    public String getTenDN() {
        return tenDN;
    }

    /**
     * @param tenDN the tenDN to set
     */
    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    /**
     * @return the matKhau
     */
    public String getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
}
