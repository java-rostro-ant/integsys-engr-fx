package org.rmj.integsys.engr.views;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.SQLUtil;
import org.rmj.integsys.engr.base.iCallBack;
import org.rmj.integsys.engr.base.iDashboard;
import org.rmj.integsys.engr.base.iDashboardTrans;

/**
 * FXML Controller class
 *
 * @author mac
 */
public class DashboardController implements Initializable, iDashboardTrans {   
    @FXML
    private TableView tblDetail;
    @FXML
    private TableColumn index01;
    @FXML
    private TableColumn index02;
    @FXML
    private TableColumn index03;
    @FXML
    private TableColumn index04;
    @FXML
    private TableView tblPO;
    @FXML
    private TableColumn PO01;
    @FXML
    private TableColumn PO02;
    @FXML
    private TableColumn PO03;
    @FXML
    private TableColumn PO04;
    @FXML
    private TableColumn PO05;
    @FXML
    private TableView tblRec;
    @FXML
    private TableColumn Rec01;
    @FXML
    private TableColumn Rec02;
    @FXML
    private TableColumn Rec03;
    @FXML
    private TableColumn Rec04;
    @FXML
    private TableColumn Rec05;
    @FXML
    private TableView tblRet;
    @FXML
    private TableColumn Ret01;
    @FXML
    private TableColumn Ret02;
    @FXML
    private TableColumn Ret03;
    @FXML
    private TableColumn Ret04;
    @FXML
    private TableView tblTrnfr;
    @FXML
    private TableColumn Trnfr01;
    @FXML
    private TableColumn Trnfr02;
    @FXML
    private TableColumn Trnfr03;
    @FXML
    private TableColumn Trnfr04;
    @FXML
    private TableColumn Trnfr05;
    @FXML
    private TableView tblUnposted;
    @FXML
    private TableColumn Unposted01;
    @FXML
    private TableColumn Unposted02;
    @FXML
    private TableColumn Unposted03;
    @FXML
    private TableColumn Unposted04;
    @FXML
    private TableColumn Unposted05;
    @FXML
    private Tab tabPO;
    @FXML
    private Tab tabReceiving;
    @FXML
    private Tab tabReturns;
    @FXML
    private Tab tabOpenTransfer;
    @FXML
    private Tab tabUnpostedTransfer;
    @FXML
    private PieChart pie01;

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        poTrans.setGRider(poGRider);
        poTrans.setCallback(poCallBack);
        
        initGrid();
        loadProjects();
        loadPurchases();
        loadReceiving();
        loadReturns();
        loadOpenTransfer();
        loadUnpostedTransfer();
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("MRO", 50),
                new PieChart.Data("RwMt", 25),
                new PieChart.Data("FsGd", 25));
        pie01.setData(pieChartData);
        pie01.setTitle("Imported Fruits");
    }    

    @Override
    public void setGRider(GRider foGRider) {
        poGRider = foGRider;
    }
    
    @Override
    public void setClass(iDashboard foValue) {
        poTrans = foValue;
    }
    
    @FXML
    private void tblDetail_Click(MouseEvent event) {
        int lnRow  = tblDetail.getSelectionModel().getSelectedIndex();   
    }
         
    private void loadProjects(){
        ResultSet loRS = poTrans.getProjects();
        
        int lnCtr = 0;
        
        data.clear();
        
        try {
            while (loRS.next()){
                lnCtr ++;
                data.add(new TableModel(String.valueOf(lnCtr),
                        loRS.getString("sProjCode"),
                        loRS.getString("sProjDesc"),
                        "0.00",
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""));    
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadPurchases(){
        ResultSet loRS = poTrans.getPurchases();
        
        int lnCtr = 0;
        
        po.clear();
        
        try {
            while (loRS.next()){
                lnCtr ++;
                po.add(new TableModel(String.valueOf(lnCtr),
                        loRS.getString("sTransNox"),
                        SQLUtil.dateFormat(loRS.getDate("dTransact"), SQLUtil.FORMAT_SHORT_DATE),
                        loRS.getString("sProjDesc"),
                        loRS.getString("sReferNox"),
                        "",
                        "",
                        "",
                        "",
                        ""));    
            }
            
            if (lnCtr > 0)
                tabPO.setText("Unconfirmed PO (" + lnCtr + ")");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadReceiving(){
        ResultSet loRS = poTrans.getReceiving();
        
        int lnCtr = 0;
        
        rec.clear();
        
        try {
            while (loRS.next()){
                lnCtr ++;
                rec.add(new TableModel(String.valueOf(lnCtr),
                        loRS.getString("sTransNox"),
                        SQLUtil.dateFormat(loRS.getDate("dTransact"), SQLUtil.FORMAT_SHORT_DATE),
                        loRS.getString("sProjDesc"),
                        loRS.getString("sReferNox"),
                        "",
                        "",
                        "",
                        "",
                        ""));    
            }
            
            if (lnCtr > 0)      
                tabReceiving.setText("Unconfirmed PO Receiving (" + lnCtr + ")");     
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadReturns(){
        ResultSet loRS = poTrans.getReturnsxx();
        
        int lnCtr = 0;
        
        ret.clear();
        
        try {
            while (loRS.next()){
                lnCtr ++;
                ret.add(new TableModel(String.valueOf(lnCtr),
                        loRS.getString("sTransNox"),
                        SQLUtil.dateFormat(loRS.getDate("dTransact"), SQLUtil.FORMAT_SHORT_DATE),
                        loRS.getString("sProjDesc"),
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""));    
            }
            
            if (lnCtr > 0)          
                tabReturns.setText("Unconfirmed PO Returns (" + lnCtr + ")");         
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadOpenTransfer(){
        ResultSet loRS = poTrans.getOpenTransfer();
        
        int lnCtr = 0;
        
        opntrsf.clear();
        
        try {
            while (loRS.next()){
                lnCtr ++;
                opntrsf.add(new TableModel(String.valueOf(lnCtr),
                        loRS.getString("sTransNox"),
                        SQLUtil.dateFormat(loRS.getDate("dTransact"), SQLUtil.FORMAT_SHORT_DATE),
                        loRS.getString("xSourcexx"),
                        loRS.getString("xDestinat"),
                        "",
                        "",
                        "",
                        "",
                        ""));    
            }
            
            if (lnCtr > 0)          
                tabOpenTransfer.setText("Unconfirmed Transfer (" + lnCtr + ")"); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadUnpostedTransfer(){
        ResultSet loRS = poTrans.getUnpostedTransfer();
        
        int lnCtr = 0;
        
        unptrsf.clear();
        
        try {
            while (loRS.next()){
                lnCtr ++;
                unptrsf.add(new TableModel(String.valueOf(lnCtr),
                        loRS.getString("sTransNox"),
                        SQLUtil.dateFormat(loRS.getDate("dTransact"), SQLUtil.FORMAT_SHORT_DATE),
                        loRS.getString("xSourcexx"),
                        loRS.getString("xDestinat"),
                        "",
                        "",
                        "",
                        "",
                        ""));    
            }
            
            if (lnCtr > 0)          
                tabUnpostedTransfer.setText("Unposted Transfer (" + lnCtr + ")"); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void initGrid() {
        index01.setStyle("-fx-alignment: CENTER;");
        index02.setStyle("-fx-alignment: CENTER-LEFT;");
        index03.setStyle("-fx-alignment: CENTER-LEFT;");
        index04.setStyle("-fx-alignment: CENTER-RIGHT;");
        
        index01.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index01"));
        index02.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index02"));
        index03.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index03"));        
        index04.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index04"));        
        
        PO01.setStyle("-fx-alignment: CENTER;");
        PO02.setStyle("-fx-alignment: CENTER-LEFT;");
        PO03.setStyle("-fx-alignment: CENTER-LEFT;");
        PO04.setStyle("-fx-alignment: CENTER-LEFT;");
        PO05.setStyle("-fx-alignment: CENTER-LEFT;");
        
        PO01.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index01"));
        PO02.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index02"));
        PO03.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index03"));        
        PO04.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index04"));        
        PO05.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index05"));    
        
        Rec01.setStyle("-fx-alignment: CENTER;");
        Rec02.setStyle("-fx-alignment: CENTER-LEFT;");
        Rec03.setStyle("-fx-alignment: CENTER-LEFT;");
        Rec04.setStyle("-fx-alignment: CENTER-LEFT;");
        Rec05.setStyle("-fx-alignment: CENTER-LEFT;");
        
        Rec01.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index01"));
        Rec02.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index02"));
        Rec03.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index03"));        
        Rec04.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index04"));        
        Rec05.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index05"));   
        
        Ret01.setStyle("-fx-alignment: CENTER;");
        Ret02.setStyle("-fx-alignment: CENTER-LEFT;");
        Ret03.setStyle("-fx-alignment: CENTER-LEFT;");
        Ret04.setStyle("-fx-alignment: CENTER-LEFT;");        
        
        Ret01.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index01"));
        Ret02.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index02"));
        Ret03.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index03"));        
        Ret04.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index04"));    
        
        Trnfr01.setStyle("-fx-alignment: CENTER;");
        Trnfr02.setStyle("-fx-alignment: CENTER-LEFT;");
        Trnfr03.setStyle("-fx-alignment: CENTER-LEFT;");
        Trnfr04.setStyle("-fx-alignment: CENTER-LEFT;");        
        Trnfr05.setStyle("-fx-alignment: CENTER-LEFT;");        
        
        Trnfr01.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index01"));
        Trnfr02.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index02"));
        Trnfr03.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index03"));        
        Trnfr04.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index04"));  
        Trnfr05.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index05")); 
        
        Unposted01.setStyle("-fx-alignment: CENTER;");
        Unposted02.setStyle("-fx-alignment: CENTER-LEFT;");
        Unposted03.setStyle("-fx-alignment: CENTER-LEFT;");
        Unposted04.setStyle("-fx-alignment: CENTER-LEFT;");        
        Unposted05.setStyle("-fx-alignment: CENTER-LEFT;");        
        
        Unposted01.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index01"));
        Unposted02.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index02"));
        Unposted03.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index03"));        
        Unposted04.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index04"));  
        Unposted05.setCellValueFactory(new PropertyValueFactory<TableModel,String>("index05")); 
        
        /*Assigning data to table*/
        tblDetail.setItems(data);
        tblPO.setItems(po);
        tblRec.setItems(rec);
        tblRet.setItems(ret);
        tblTrnfr.setItems(opntrsf);
        tblUnposted.setItems(unptrsf);
        
        tabPO.setText("Unconfirmed PO");
        tabReceiving.setText("Unconfirmed PO Receiving");
        tabReturns.setText("Unconfirmed PO Returns");
        tabOpenTransfer.setText("Unconfirmed Transfer");
        tabUnpostedTransfer.setText("Unposted Transfer");
    }
    
    iCallBack poCallBack = new iCallBack() {
        @Override
        public void MasterRetreive(String fsIndex) {
            switch (fsIndex){
                case "xProjects":
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            loadProjects();
                        }
                    });
                    break;    
                case "xPurchase":
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            loadPurchases();
                        }
                    });
                    break;
                case "xReceivex":
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            loadReceiving();
                        }
                    });
                    break;
                case "xReturnsx":
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            loadReturns();
                        }
                    });
                    break;
                case "xOpnTrnsf":
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            loadOpenTransfer();
                        }
                    });
                    break;
                case "xUnpTrnsf":
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            loadUnpostedTransfer();
                        }
                    });
                    break;
            }
        }
    };
    
    GRider poGRider;
    iDashboard poTrans;
    ResultSet poData;
    
    private ObservableList<TableModel> data = FXCollections.observableArrayList();
    private ObservableList<TableModel> po = FXCollections.observableArrayList();
    private ObservableList<TableModel> rec = FXCollections.observableArrayList();
    private ObservableList<TableModel> ret = FXCollections.observableArrayList();
    private ObservableList<TableModel> opntrsf = FXCollections.observableArrayList();
    private ObservableList<TableModel> unptrsf = FXCollections.observableArrayList();
}
