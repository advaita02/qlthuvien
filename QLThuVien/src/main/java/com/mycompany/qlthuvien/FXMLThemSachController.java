/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qlthuvien;

import com.lth.bojo.Sach;
import com.lth.conf.Utils;
import com.lth.service.SachService;
import java.net.URL;
import java.sql.Date;

import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hunii
 */
public class FXMLThemSachController implements Initializable {
    @FXML private TextField txtMaSach;
    @FXML private TextField txtTenSach;
    @FXML private TextField txtTenTacGia;
    @FXML private DatePicker dpNamXuatBan;
    @FXML private TextField txtSoTrang;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void addSachHandler(ActionEvent event) {
        Date date = java.sql.Date.valueOf(this.dpNamXuatBan.getValue());
        
        Sach s = new Sach(this.txtTenSach.getText(), this.txtTenTacGia.getText(), date, 1, Integer.parseInt(this.txtSoTrang.getText()));
        
        SachService sv = new SachService();
        try {
            sv.addBook(s);
            Utils.getBox("Thêm sách thành công!", Alert.AlertType.INFORMATION).show();
        } catch (SQLException ex) {
            Utils.getBox("Thêm thất bại!", Alert.AlertType.WARNING).show();
        }
    }
}
