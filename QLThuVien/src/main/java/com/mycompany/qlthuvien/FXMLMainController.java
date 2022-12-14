 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.qlthuvien;

import com.lth.bojo.MuonTra;
import com.lth.bojo.Sach;
import com.lth.bojo.SachThanhLy;
import com.lth.conf.Utils;
import com.lth.service.MuonTraService;
import com.lth.service.SachService;
import com.lth.service.SachTLService;
import static java.lang.String.format;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
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
    @FXML private TableView<MuonTra> tbSachDangMuon;
    @FXML private TextField txtMaSach;
    @FXML private TextField txtTenSach;
    @FXML private TextField txtTenTacGia;
    @FXML private DatePicker dpNamXuatBan;
    @FXML private TextField txtSoTrang;
    @FXML private TextField txtGiaBia;
    @FXML private TableView<Sach> tbSach;
    @FXML private TableView<SachThanhLy> tbThanhLy;
    @FXML private TableView<Sach> tbSachMuon;
    @FXML private TextField txtKeyWord;
    // muon sach
    @FXML private TextField txtMaSachMuon;
    @FXML private DatePicker dpNgayMuon;
    @FXML private TextField txtHTNguoiMuon;
    @FXML private TextField txtSoCCCD;
    @FXML private TextField txtSDT;
    @FXML private TextField txtNVLap;
    @FXML private TextField txtMaSachTra;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loadTableView();
        this.loadTableViewTL();
        this.loadTableViewMuon();
        this.loadTableTra();
        try {
            this.loadTableViewDate(null);
            this.loadTableViewTL(null);
            this.loadTableSachCoTheMuon(null);
            this.loadTableDangMuon(null);
//            this.loadTableViewTra(null);
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
    
    public void muonSach(ActionEvent event) throws SQLException {
        LocalDate local = this.dpNgayMuon.getValue();
        DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String d = local.format(Formatter);
        Date date = java.sql.Date.valueOf(local.format(Formatter));
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        MuonTra m = new MuonTra(Integer.parseInt(this.txtMaSachMuon.getText()), date, this.txtHTNguoiMuon.getText(), Integer.parseInt(this.txtSoCCCD.getText()), this.txtSDT.getText(), Integer.parseInt(this.txtNVLap.getText()));
        MuonTraService msv = new MuonTraService();
        if (msv.checkMaSach(Integer.parseInt(this.txtMaSachMuon.getText())) == true) {
            try {
                msv.addMuonTra(m);
                Utils.getBox("M?????n s??ch th??nh c??ng!", Alert.AlertType.INFORMATION).show();
            } catch (SQLException ex) {
                Utils.getBox("M?????n th???t b???i!", Alert.AlertType.WARNING).show();
            }
        }
        else {
            Utils.getBox("M?? s??ch kh??ng t???n t???i", Alert.AlertType.WARNING).show();
        }
    }
    public void addSachHandler(ActionEvent event) {
        Date date = java.sql.Date.valueOf(this.dpNamXuatBan.getValue());
        
//        Sach s = new Sach(this.txtTenSach.getText(), this.txtTenTacGia.getText(), date, 1, Integer.parseInt(this.txtSoTrang.getText()), Integer.parseInt(this.txtGiaBia.getText()));
        Sach s = new Sach(this.txtTenSach.getText(), this.txtTenTacGia.getText(), date, 1, Integer.parseInt(this.txtSoTrang.getText()), Integer.parseInt(this.txtGiaBia.getText()));
        SachService sv = new SachService();
        try {
            sv.addBook(s);
            Utils.getBox("Th??m s??ch th??nh c??ng!", Alert.AlertType.INFORMATION).show();
        } catch (SQLException ex) {
            Utils.getBox("Th??m th???t b???i!", Alert.AlertType.WARNING).show();
        }
    }
    public void deleteSachHandler(ActionEvent event) {
        SachService sv = new SachService();
        try {
            sv.deleteBook(Integer.parseInt(this.txtMaSach.getText()));
            Utils.getBox("X??a s??ch th??nh c??ng!", Alert.AlertType.INFORMATION).show();
        } catch (SQLException ex) {
            Utils.getBox("X??a th???t b???i!", Alert.AlertType.WARNING).show();
        }
    }
    public void addSachTLHandler(ActionEvent event) {
        SachTLService stl = new SachTLService();
        try {
            stl.addThanhLy();
            Utils.getBox("???? th??m s??ch >= 10 n??m v??o thanh l??!", Alert.AlertType.INFORMATION).show();
        } catch (SQLException ex) {
            Utils.getBox("Th??m s??ch th???t b???i!", Alert.AlertType.WARNING).show();
        }
    }
    public void traSach(ActionEvent event) throws SQLException{
        MuonTraService mt = new MuonTraService();
        if (mt.checkSachMuon(Integer.parseInt(this.txtMaSachTra.getText())) == true) {
            Utils.getBox("Tr? s??ch th??nh c??ng!", Alert.AlertType.INFORMATION).show();
            if(mt.kiemTraTreHan(Integer.parseInt(this.txtMaSachTra.getText()))== true){
                Utils.getBox("Tra sach tre han!", Alert.AlertType.WARNING).show();
            }
        }
        else
            Utils.getBox("Tra? s??ch th???t b???i!", Alert.AlertType.WARNING).show();

    }
    
    private void loadTableView() {
        TableColumn colMaSach = new TableColumn("M?? s??ch");
        TableColumn colTenSach = new TableColumn("T??n s??ch");
        TableColumn colTenTG = new TableColumn("T??n t??c gi???");
        TableColumn colNamXB = new TableColumn("N??m xu???t b???n");
        TableColumn colTinhTrang = new TableColumn("T??nh tr???ng");
        TableColumn colSoTrang = new TableColumn("S??? trang");
        TableColumn colGiaBia = new TableColumn("Gi?? b??a");
        
        colMaSach.setCellValueFactory(new PropertyValueFactory("maSach"));
        colTenSach.setCellValueFactory(new PropertyValueFactory("tenSach"));
        colTenTG.setCellValueFactory(new PropertyValueFactory("tenTacGia"));
        colNamXB.setCellValueFactory(new PropertyValueFactory("NamXB"));
        colTinhTrang.setCellValueFactory(new PropertyValueFactory("tinhTrang"));
        colSoTrang.setCellValueFactory(new PropertyValueFactory("soTrang"));
        colGiaBia.setCellValueFactory(new PropertyValueFactory("giaBia"));

        colMaSach.setPrefWidth(25 + 35);
        colTenSach.setPrefWidth(100 + 20);
        colTenTG.setPrefWidth(75 + 20);
        colNamXB.setPrefWidth(100 + 20);
        colTinhTrang.setPrefWidth(25 + 35);
        colSoTrang.setPrefWidth(25 + 35);
        colGiaBia.setPrefWidth(50 + 20);
        
        this.tbSach.getColumns().addAll(colMaSach, colTenSach, colTenTG, colNamXB, colTinhTrang, colSoTrang, colGiaBia);
    }
    private void loadTableViewTL() {
        TableColumn colMaSach = new TableColumn("M?? s??ch");
        TableColumn colTenSach = new TableColumn("T??n s??ch");
        TableColumn colTenTG = new TableColumn("T??n t??c gi???");
        TableColumn colSoTrang = new TableColumn("S??? trang");
        TableColumn colGiaBan = new TableColumn("Gi?? b??n");

        colMaSach.setCellValueFactory(new PropertyValueFactory("maSach_ThanhLy"));
        colTenSach.setCellValueFactory(new PropertyValueFactory("tenSach"));
        colTenTG.setCellValueFactory(new PropertyValueFactory("tacGia"));
        colSoTrang.setCellValueFactory(new PropertyValueFactory("soTrang"));
        colGiaBan.setCellValueFactory(new PropertyValueFactory("giaBan"));

        colMaSach.setPrefWidth(25 + 35);
        colTenSach.setPrefWidth(100 + 20);
        colTenTG.setPrefWidth(75 + 20);
        colSoTrang.setPrefWidth(25 + 35);
        colGiaBan.setPrefWidth(50 + 20);

        this.tbThanhLy.getColumns().addAll(colMaSach, colTenSach, colTenTG, colSoTrang, colGiaBan);
    }
    private void loadTableTra() {
        TableColumn colMaSach = new TableColumn("M?? s??ch");
        TableColumn colNgayMuon = new TableColumn("Ng??y m?????n");
        TableColumn colHoTen = new TableColumn("T??n ng?????i m?????n");
        TableColumn colCCCD = new TableColumn("S??? CCCD");
        TableColumn colSDT = new TableColumn("S??? ??i???n tho???i");
        TableColumn colNV = new TableColumn("Nh??n vi??n l???p");
        
        colMaSach.setCellValueFactory(new PropertyValueFactory("maSach_MuonTra"));
        colNgayMuon.setCellValueFactory(new PropertyValueFactory("ngayMuonDate"));
        colHoTen.setCellValueFactory(new PropertyValueFactory("hoTenNguoiMuon"));
        colCCCD.setCellValueFactory(new PropertyValueFactory("soCCCD"));
        colSDT.setCellValueFactory(new PropertyValueFactory("soDTNguoiMuon"));
        colNV.setCellValueFactory(new PropertyValueFactory("nVLap"));

        colMaSach.setPrefWidth(25 + 35);
        colNgayMuon.setPrefWidth(100 + 20);
        colHoTen.setPrefWidth(75 + 20);
        colCCCD.setPrefWidth(25 + 35);
        colSDT.setPrefWidth(50 + 20);
        colNV.setPrefWidth(75 + 20);

        this.tbSachDangMuon.getColumns().addAll(colMaSach, colNgayMuon, colHoTen, colCCCD, colSDT, colNV);
    }
    private void loadTableViewMuon() {
        TableColumn colMaSach = new TableColumn("M?? s??ch");
        TableColumn colTenSach = new TableColumn("T??n s??ch");
        TableColumn colTenTG = new TableColumn("T??n t??c gi???");
        TableColumn colNamXB = new TableColumn("N??m xu???t b???n");
        TableColumn colSoTrang = new TableColumn("S??? trang");
        TableColumn colGiaBia = new TableColumn("Gi?? b??a");
        
        colMaSach.setCellValueFactory(new PropertyValueFactory("maSach"));
        colTenSach.setCellValueFactory(new PropertyValueFactory("tenSach"));
        colTenTG.setCellValueFactory(new PropertyValueFactory("tenTacGia"));
        colNamXB.setCellValueFactory(new PropertyValueFactory("NamXB"));
        colSoTrang.setCellValueFactory(new PropertyValueFactory("soTrang"));
        colGiaBia.setCellValueFactory(new PropertyValueFactory("giaBia"));

        colMaSach.setPrefWidth(25 + 35);
        colTenSach.setPrefWidth(100 + 20);
        colTenTG.setPrefWidth(75 + 20);
        colNamXB.setPrefWidth(100 + 20);
        colSoTrang.setPrefWidth(25 + 35);
        colGiaBia.setPrefWidth(50 + 20);
        
        this.tbSachMuon.getColumns().addAll(colMaSach, colTenSach, colTenTG, colNamXB, colSoTrang, colGiaBia);
    }
    
    private void loadTableViewDate(String kw) throws SQLException {
        SachService s = new SachService();
        this.tbSach.setItems(FXCollections.observableList(s.getBook(kw)));
    }
    private void loadTableSachCoTheMuon(String kw) throws SQLException {
        SachService s = new SachService();
        this.tbSachMuon.setItems(FXCollections.observableList(s.getSachCoTheMuon(kw)));
    }
    private void loadTableViewTL(String kw) throws SQLException {
        SachTLService stl = new SachTLService();
        this.tbThanhLy.setItems(FXCollections.observableList(stl.getThanhLy(kw)));
    }
//    private void loadTableViewTra(String kw) throws SQLException {
//        MuonTraService mt = new MuonTraService();
//        this.tbSachTra.setItems(FXCollections.observableList(mt.getMuonTra(kw)));
//    }
    private void loadTableDangMuon(String kw) throws SQLException {
        MuonTraService mt = new MuonTraService();
        this.tbSachDangMuon.setItems(FXCollections.observableArrayList(mt.getSachDangMuon(kw)));
    }
}
