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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author hunii
 */
public class FXMLMainController implements Initializable {
    @FXML private TextField txtMaSach;
    @FXML private TextField txtTenSach;
    @FXML private TextField txtTenTacGia;
    @FXML private DatePicker dpNamXuatBan;
    @FXML private TextField txtSoTrang;
    @FXML private TableView<Sach> tbSach;
    @FXML private TableView<Sach> tbThanhLy;
    @FXML private TextField txtKeyWord;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loadTableView();
        try {
            this.loadTableViewDate(null);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtKeyWord.textProperty().addListener((evt) -> {
            try {
               this.loadTableViewDate(this.txtKeyWord.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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
    public void deleteSachHandler(ActionEvent event) {
        SachService sv = new SachService();
        try {
            sv.deleteBook(Integer.parseInt(this.txtMaSach.getText()));
            Utils.getBox("Xóa sách thành công!", Alert.AlertType.INFORMATION).show();
        } catch (SQLException ex) {
            Utils.getBox("Xóa thất bại!", Alert.AlertType.WARNING).show();
        }
    }
    
    private void loadTableView() {
        TableColumn colMaSach = new TableColumn("Mã sách");
        TableColumn colTenSach = new TableColumn("Tên sách");
        TableColumn colTenTG = new TableColumn("Tên tác giả");
        TableColumn colNamXB = new TableColumn("Năm xuất bản");
        TableColumn colTinhTrang = new TableColumn("Tình trạng");
        TableColumn colSoTrang = new TableColumn("Số trang");
        TableColumn colGiaBia = new TableColumn("Giá bìa");
        
        colMaSach.setCellValueFactory(new PropertyValueFactory("maSach"));
        colTenSach.setCellValueFactory(new PropertyValueFactory("tenSach"));
        colTenTG.setCellValueFactory(new PropertyValueFactory("tenTacGia"));
        colNamXB.setCellValueFactory(new PropertyValueFactory("tinhTrang"));
        colTinhTrang.setCellValueFactory(new PropertyValueFactory("maSach"));
        colSoTrang.setCellValueFactory(new PropertyValueFactory("soTrang"));
        colGiaBia.setCellValueFactory(new PropertyValueFactory("giaBia"));

        colMaSach.setPrefWidth(25 + 35);
        colTenSach.setPrefWidth(100 + 20);
        colTenTG.setPrefWidth(75 + 20);
        colNamXB.setPrefWidth(100 + 20);
        colTinhTrang.setPrefWidth(25 + 35);
        colSoTrang.setPrefWidth(25 + 35);
        colGiaBia.setPrefWidth(50 + 20);
        
        this.tbThanhLy.getColumns().addAll(colMaSach, colTenSach, colTenTG, colNamXB, colTinhTrang, colSoTrang, colGiaBia);
        this.tbSach.getColumns().addAll(colMaSach, colTenSach, colTenTG, colNamXB, colTinhTrang, colSoTrang, colGiaBia);
    }
    
    private void loadTableViewDate(String kw) throws SQLException {
        SachService s = new SachService();
        this.tbSach.setItems(FXCollections.observableList(s.getBook(kw)));
    }
    
}
