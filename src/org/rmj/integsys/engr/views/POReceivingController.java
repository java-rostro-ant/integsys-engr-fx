package org.rmj.integsys.engr.views;

import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyBooleanPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.F3;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.json.simple.JSONObject;
import org.rmj.appdriver.constants.EditMode;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agent.MsgBox;
import org.rmj.appdriver.agentfx.CommonUtils;
import org.rmj.appdriver.agentfx.callback.IFXML;
import org.rmj.engr.inventory.base.Inventory;
import org.rmj.cas.parameter.agent.XMInventoryType;
import org.rmj.cas.parameter.agent.XMTerm;
import org.rmj.appdriver.agentfx.callback.IMasterDetail;
import org.rmj.cas.parameter.agent.XMProject;
import org.rmj.engr.purchasing.agent.XMPOReceiving;

public class POReceivingController implements Initializable, IFXML {
    @FXML private AnchorPane anchorField;
    @FXML private TextField txtField01;
    @FXML private TextField txtField03;
    @FXML private TextField txtField02;
    @FXML private TextField txtField05;
    @FXML private TextField txtField06;
    @FXML private TextField txtField07;
    @FXML private TextField txtField08;
    @FXML private TextField txtField20;
    @FXML private TextArea txtField16;
    @FXML private Label Label09;
    @FXML private TextField txtDetail03;
    @FXML private TextField txtDetail80;
    @FXML private TextField txtDetail04;
    @FXML private TableView table;
    @FXML private TextField txtField10;
    @FXML private TextField txtField11;
    @FXML private TextField txtField15;
    @FXML private TextField txtField12;
    @FXML private TextField txtField13;
    @FXML private TextField txtField14;
    @FXML private Button btnNew;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;
    @FXML private Button btnSearch;
    @FXML private Button btnConfirm;
    @FXML private Button btnDel;
    @FXML private TextField txtDetail05;
    @FXML private ComboBox Combo06;
    @FXML private TextField txtDetail08;
    @FXML private TextField txtDetail09;
    @FXML private TextField txtDetail07;
    @FXML private Button btnBrowse;
    @FXML private ImageView imgTranStat;
    @FXML private TextField txtField50;
    @FXML private TextField txtField51;
    @FXML private Button btnPrint;
    @FXML private Button btnVoid;
    @FXML private TextField txtField17;
    @FXML private ComboBox Combo21;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*Initialize class*/
        poTrans = new XMPOReceiving(poGRider, poGRider.getBranchCode(), false);
        poTrans.setTranStat(10);
        poTrans.setCallBack(poCallBack);
                
        /*Set action event handler for the buttons*/
        btnCancel.setOnAction(this::cmdButton_Click);
        btnSearch.setOnAction(this::cmdButton_Click);
        btnSave.setOnAction(this::cmdButton_Click);
        btnDel.setOnAction(this::cmdButton_Click);
        btnNew.setOnAction(this::cmdButton_Click);
        btnConfirm.setOnAction(this::cmdButton_Click);
        btnVoid.setOnAction(this::cmdButton_Click);
        btnBrowse.setOnAction(this::cmdButton_Click);
        btnPrint.setOnAction(this::cmdButton_Click);
        
        /*Add listener to text fields*/
        txtField02.focusedProperty().addListener(txtField_Focus);
        txtField03.focusedProperty().addListener(txtField_Focus);
        txtField05.focusedProperty().addListener(txtField_Focus);
        txtField06.focusedProperty().addListener(txtField_Focus);
        txtField07.focusedProperty().addListener(txtField_Focus);
        txtField08.focusedProperty().addListener(txtField_Focus);
        txtField10.focusedProperty().addListener(txtField_Focus);
        txtField11.focusedProperty().addListener(txtField_Focus);
        txtField12.focusedProperty().addListener(txtField_Focus);
        txtField13.focusedProperty().addListener(txtField_Focus);
        txtField14.focusedProperty().addListener(txtField_Focus);
        txtField15.focusedProperty().addListener(txtField_Focus);
        txtField20.focusedProperty().addListener(txtField_Focus);
        txtField50.focusedProperty().addListener(txtField_Focus);
        txtField51.focusedProperty().addListener(txtField_Focus);
        txtField16.focusedProperty().addListener(txtArea_Focus);
        
        txtDetail03.focusedProperty().addListener(txtDetail_Focus);
        txtDetail04.focusedProperty().addListener(txtDetail_Focus);
        txtDetail05.focusedProperty().addListener(txtDetail_Focus);
        txtDetail07.focusedProperty().addListener(txtDetail_Focus);
        txtDetail08.focusedProperty().addListener(txtDetail_Focus);
        txtDetail09.focusedProperty().addListener(txtDetail_Focus);
        txtDetail80.focusedProperty().addListener(txtDetail_Focus);
                
        /*Add keypress event for field with search*/
        txtField02.setOnKeyPressed(this::txtField_KeyPressed);
        txtField03.setOnKeyPressed(this::txtField_KeyPressed);
        txtField05.setOnKeyPressed(this::txtField_KeyPressed);
        txtField06.setOnKeyPressed(this::txtField_KeyPressed);
        txtField07.setOnKeyPressed(this::txtField_KeyPressed);
        txtField08.setOnKeyPressed(this::txtField_KeyPressed);
        txtField10.setOnKeyPressed(this::txtField_KeyPressed);
        txtField11.setOnKeyPressed(this::txtField_KeyPressed);
        txtField12.setOnKeyPressed(this::txtField_KeyPressed);
        txtField13.setOnKeyPressed(this::txtField_KeyPressed);
        txtField14.setOnKeyPressed(this::txtField_KeyPressed);
        txtField15.setOnKeyPressed(this::txtField_KeyPressed);
        txtField17.setOnKeyPressed(this::txtField_KeyPressed);
        txtField20.setOnKeyPressed(this::txtField_KeyPressed);
        txtField50.setOnKeyPressed(this::txtField_KeyPressed);
        txtField51.setOnKeyPressed(this::txtField_KeyPressed);
        txtField16.setOnKeyPressed(this::txtFieldArea_KeyPressed);
        
        txtDetail03.setOnKeyPressed(this::txtDetail_KeyPressed);
        txtDetail04.setOnKeyPressed(this::txtDetail_KeyPressed);
        txtDetail05.setOnKeyPressed(this::txtDetail_KeyPressed);
        txtDetail07.setOnKeyPressed(this::txtDetail_KeyPressed);
        txtDetail08.setOnKeyPressed(this::txtDetail_KeyPressed);
        txtDetail09.setOnKeyPressed(this::txtDetail_KeyPressed);
        txtDetail80.setOnKeyPressed(this::txtDetail_KeyPressed);
        
        Combo06.setOnKeyPressed(this::ComboBox_KeyPressed);
        Combo06.focusedProperty().addListener(Combo_Focus);
        Combo06.setItems(cUnitType);
        Combo06.getSelectionModel().select(1);       
        
        Combo21.setItems(cTranStat);
        Combo21.getSelectionModel().select(0);
        Combo21.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                pnEditMode = EditMode.UNKNOWN;
        
                clearFields();
                initGrid();
                initButton(pnEditMode);
                txtField51.requestFocus();
            }    
        });
        
        pnEditMode = EditMode.UNKNOWN;
        
        clearFields();
        initGrid();
        initButton(pnEditMode);
        
        pbLoaded = true;
    } 

    @FXML
    private void table_Clicked(MouseEvent event) { 
        pnRow = table.getSelectionModel().getSelectedIndex();
        
        setDetailInfo(); 
        txtDetail03.requestFocus();
        txtDetail03.selectAll();
    }
    
    private void setDetailInfo(){       
        String lsStockIDx = (String) poTrans.getDetail(pnRow, "sStockIDx");
        if (pnRow >= 0){                   
            txtDetail03.setText((String) poTrans.getDetail(pnRow, 3));
            
            Inventory loInventory; 
            
            if (!lsStockIDx.equals("")){    
                loInventory = poTrans.GetInventory(lsStockIDx, true, false);
                psBarCodex = (String) loInventory.getMaster("sBarCodex");
                psDescript = (String) loInventory.getMaster("sDescript");
            } else {
                psBarCodex = (String) poTrans.getDetail(pnRow, 100);
                psDescript = (String) poTrans.getDetail(pnRow, 101);
            }
            
            txtDetail04.setText(psBarCodex);
            txtDetail80.setText(psDescript);
            
            lsStockIDx = (String) poTrans.getDetail(pnRow, "sReplacID");
            if (!lsStockIDx.equals("")){
                loInventory = poTrans.GetInventory(lsStockIDx, true, false);
                psBarCodex = (String) loInventory.getMaster("sBarCodex");
                psDescript = (String) loInventory.getMaster("sDescript");
                txtDetail05.setText(psBarCodex);
            } else
                txtDetail05.setText("");
                        
            txtDetail07.setText(String.valueOf(poTrans.getDetail(pnRow, "nQuantity")));
            txtDetail08.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getDetail(pnRow, 8).toString()), "0.00"));
            txtDetail09.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getDetail(pnRow, 9).toString()), "0.00"));
            
            Combo06.getSelectionModel().select(Integer.parseInt((String) poTrans.getDetail(pnRow, 6)));
        } else{
            txtDetail03.setText("");
            txtDetail04.setText("");
            txtDetail05.setText("");
            txtDetail07.setText("0");
            txtDetail08.setText("0.00");
            txtDetail09.setText("0.00");
            txtDetail80.setText("");   
        }
    }
    
    private void initButton(int fnValue){
        boolean lbShow = (fnValue == EditMode.ADDNEW || fnValue == EditMode.UPDATE);
        
        btnCancel.setVisible(lbShow);
        btnSearch.setVisible(lbShow);
        btnSave.setVisible(lbShow);
        btnDel.setVisible(lbShow);
        
        txtField50.setDisable(lbShow);
        txtField51.setDisable(lbShow);
                
        btnBrowse.setVisible(!lbShow);
        btnNew.setVisible(!lbShow);
        btnPrint.setVisible(!lbShow);
        btnVoid.setVisible(!lbShow);
        btnConfirm.setVisible(false);
        
        txtField01.setDisable(!lbShow);
        txtField02.setDisable(!lbShow);
        txtField03.setDisable(!lbShow);
        txtField05.setDisable(!lbShow);
        txtField06.setDisable(!lbShow);
        txtField07.setDisable(!lbShow);
        txtField08.setDisable(!lbShow);
        txtField10.setDisable(!lbShow);
        txtField11.setDisable(!lbShow);
        txtField12.setDisable(!lbShow);
        txtField13.setDisable(!lbShow);
        txtField14.setDisable(!lbShow);
        txtField15.setDisable(!lbShow);
        txtField17.setDisable(!lbShow);
        txtField16.setDisable(!lbShow);
        txtField20.setDisable(!lbShow);
        txtDetail03.setDisable(!lbShow);
        txtDetail04.setDisable(!lbShow);
        txtDetail05.setDisable(!lbShow);
        txtDetail07.setDisable(!lbShow);
        txtDetail08.setDisable(!lbShow);
        txtDetail09.setDisable(!lbShow);
        txtDetail80.setDisable(!lbShow);
        
        Combo06.setDisable(true); //!lbShow
        
        if (lbShow)
            txtField17.requestFocus();
        else
            txtField50.requestFocus();
    }
    
    private void initGrid(){
        
        TableColumn index01 = new TableColumn("No.");
        TableColumn index02 = new TableColumn("Order No.");
        TableColumn index03 = new TableColumn("Bar Code");
        TableColumn index04 = new TableColumn("Description");
        TableColumn index05 = new TableColumn("Superseded");
        TableColumn index06 = new TableColumn("Unit Type");
        TableColumn index07 = new TableColumn("Qty");
        TableColumn index08 = new TableColumn("Unit Price");
        TableColumn index09 = new TableColumn("Freight");
        
        index01.setPrefWidth(30); index01.setStyle("-fx-alignment: CENTER;");
        index02.setPrefWidth(110);
        index03.setPrefWidth(110);
        index04.setPrefWidth(195);
        index05.setPrefWidth(110);
        index06.setPrefWidth(80);
        index07.setPrefWidth(40); index07.setStyle("-fx-alignment: CENTER;");
        index08.setPrefWidth(80); index08.setStyle("-fx-alignment: CENTER-RIGHT;");
        index09.setPrefWidth(80); index09.setStyle("-fx-alignment: CENTER-RIGHT;");
        
        index01.setSortable(false); index01.setResizable(false);
        index02.setSortable(false); index02.setResizable(false);
        index03.setSortable(false); index03.setResizable(false);
        index04.setSortable(false); index04.setResizable(false);
        index05.setSortable(false); index05.setResizable(false);
        index06.setSortable(false); index06.setResizable(false);
        index07.setSortable(false); index07.setResizable(false);
        index08.setSortable(false); index08.setResizable(false);
        index09.setSortable(false); index09.setResizable(false);

        table.getColumns().clear();        
        table.getColumns().add(index01);
        table.getColumns().add(index02);
        table.getColumns().add(index03);
        table.getColumns().add(index04);
        table.getColumns().add(index05);
        table.getColumns().add(index06);
        table.getColumns().add(index07);
        table.getColumns().add(index08);
        table.getColumns().add(index09);
        
        index01.setCellValueFactory(new PropertyValueFactory<org.rmj.integsys.engr.views.TableModel,String>("index01"));
        index02.setCellValueFactory(new PropertyValueFactory<org.rmj.integsys.engr.views.TableModel,String>("index02"));
        index03.setCellValueFactory(new PropertyValueFactory<org.rmj.integsys.engr.views.TableModel,String>("index03"));
        index04.setCellValueFactory(new PropertyValueFactory<org.rmj.integsys.engr.views.TableModel,String>("index04"));
        index05.setCellValueFactory(new PropertyValueFactory<org.rmj.integsys.engr.views.TableModel,String>("index05"));
        index06.setCellValueFactory(new PropertyValueFactory<org.rmj.integsys.engr.views.TableModel,String>("index06"));
        index07.setCellValueFactory(new PropertyValueFactory<org.rmj.integsys.engr.views.TableModel,String>("index07"));
        index08.setCellValueFactory(new PropertyValueFactory<org.rmj.integsys.engr.views.TableModel,String>("index08"));
        index09.setCellValueFactory(new PropertyValueFactory<org.rmj.integsys.engr.views.TableModel,String>("index09"));
        
        /*making column's position uninterchangebale*/
        table.widthProperty().addListener(new ChangeListener<Number>() {  
            public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
            {
                TableHeaderRow header = (TableHeaderRow) table.lookup("TableHeaderRow");
                header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        header.setReordering(false);
                            }
                        });
                    }
                });
         /*Set data source to table*/
        table.setItems(data);
    }
    
    private void cmdButton_Click(ActionEvent event) {
        String lsButton = ((Button)event.getSource()).getId();
        
        switch (lsButton){
            case "btnNew":
                if (poTrans.newRecord()) {
                    clearFields(); 
                    loadRecord();
                    txtField50.setText("");
                        
                    pnEditMode = poTrans.getEditMode();
                } else
                    MsgBox.showOk(poTrans.getErrMsg() + " " + poTrans.getMessage());
                break;
            case "btnPrint":
                if(!psOldRec.equals("")){
                    if (poTrans.printRecord()){
                        clearFields();
                        initGrid();
                        pnEditMode = EditMode.UNKNOWN;
                    } else
                        MsgBox.showOk(poTrans.getErrMsg() + " " + poTrans.getMessage());
                }else 
                    MsgBox.showOk("Please select a record to print!");                            
                
                break;
            case "btnVoid":
                if (!psOldRec.equals("")){                    
                    if (poTrans.cancelRecord(psOldRec)){
                        MsgBox.showOk("Transaction CANCELLED successfully.");                            
                        initGrid();
                        clearFields();
                        pnEditMode = EditMode.UNKNOWN;
                    } else 
                        MsgBox.showOk(poTrans.getErrMsg() + " " + poTrans.getMessage());
                } else MsgBox.showOk("Please select a record to cancel!"); 
                                       
                break;
            case "btnConfirm": 
                if (!psOldRec.equals("")){                    
                    if (poTrans.postRecord(psOldRec)){
                        MsgBox.showOk("Transaction CONFIRMED successfully.");
                        clearFields();
                        initGrid();
                        pnEditMode = EditMode.UNKNOWN;
                        initButton(pnEditMode);
                    } else
                        MsgBox.showOk(poTrans.getErrMsg() + " " + poTrans.getMessage());
                } else MsgBox.showOk("Please select a record to confirm!");
                                    
                break;                
            case "btnCancel": 
                if(MsgBox.showYesNo("Do you want to disregard changes?") == MsgBox.RESP_YES_OK){
                    clearFields();
                    pnEditMode = EditMode.UNKNOWN;
                    break;
                } else
                    return;
            case "btnSearch": return;
            case "btnSave": 
                if (poTrans.saveRecord()){
                    MsgBox.showOk("Transaction saved successfuly.");                    
                    
                    //re open and print the record
                    if (poTrans.openRecord((String) poTrans.getMaster("sTransNox"))){
                        loadRecord(); 
                        psOldRec = (String) poTrans.getMaster("sTransNox");
                        
                        pnEditMode = poTrans.getEditMode();
                    } else {
                        clearFields();
                        initGrid();
                        pnEditMode = EditMode.UNKNOWN;
                    }
                    
                    initButton(pnEditMode);
                    break;
                } else {
                    MsgBox.showOk(poTrans.getErrMsg() + " " + poTrans.getMessage());
                    return;      
                }   
            case "btnBrowse":
                poTrans.setTranStat(Combo21.getSelectionModel().getSelectedIndex());
                if(poTrans.BrowseRecord(txtField50.getText(), true)==true){
                    loadRecord(); 
                    pnEditMode = poTrans.getEditMode();
                    break;
                }else {
                    clearFields();
                    pnEditMode = EditMode.UNKNOWN;
                }
                    
                return;    
            case "btnDel":  
               int lnIndex = table.getSelectionModel().getFocusedIndex();    
                if (table.getSelectionModel().getSelectedItem() == null){
                    MsgBox.showOk("Please select item to remove!");                    
                    break;
                }
                
                if(MsgBox.showYesNo("Do you want to remove this item?") == MsgBox.RESP_YES_OK){
                    poTrans.deleteDetail(lnIndex);
                    loadDetail();
                }     
                break;
            default:
                MsgBox.showOk("Button with name " + lsButton + " not registered.");                    
                return;
        }
        
        initButton(pnEditMode);
    }
    
    private void loadRecord(){
        txtField01.setText((String) poTrans.getMaster(1));
        
        txtField03.setText(CommonUtils.xsDateMedium((Date) poTrans.getMaster("dTransact")));
        txtField06.setText((String) poTrans.getMaster(6));
        txtField50.setText((String) poTrans.getMaster(6));
        psReferNox = txtField50.getText();
        txtField07.setText(CommonUtils.xsDateMedium((Date) poTrans.getMaster("dRefernce")));
        txtField10.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster(10).toString()), "0.00"));
        txtField11.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster(11).toString()), "#,##0.00"));
        txtField12.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster(12).toString()), "0.00"));
        txtField13.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster(13).toString()), "#,##0.00"));
        txtField14.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster(14).toString()), "#,##0.00"));
        txtField15.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster(15).toString()), "#,##0.00"));
        txtField16.setText((String) poTrans.getMaster(16));
        
        XMProject loProject = poTrans.GetProject((String)poTrans.getMaster(2), true);
        if (loProject != null) txtField02.setText((String) loProject.getMaster("sProjDesc"));

        XMInventoryType loInv = poTrans.GetInventoryType((String)poTrans.getMaster(20), true);
        if (loInv != null) txtField20.setText((String) loInv.getMaster("sDescript"));
        
        JSONObject loSupplier = poTrans.GetSupplier((String)poTrans.getMaster(5), true);
        if (loSupplier != null) {
            txtField05.setText((String) loSupplier.get("sClientNm"));
            txtField51.setText((String) loSupplier.get("sClientNm"));
        }

        XMTerm loTerm = poTrans.GetTerm((String)poTrans.getMaster(8), true);
        if (loTerm != null) txtField08.setText((String) loTerm.getMaster("sDescript"));

        setTranStat((String) poTrans.getMaster("cTranStat"));
        Label09.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster(9).toString()) + Double.valueOf(poTrans.getMaster(11).toString()), "#,##0.00"));
        
        pnRow = 0;
        pnOldRow = 0;
        loadDetail();
        
        psOldRec = txtField01.getText();
    }
    
    private void clearFields(){
        txtField01.setText("");
        txtField02.setText("");
        txtField03.setText("");
        txtField05.setText("");
        txtField06.setText("");
        txtField07.setText("");
        txtField08.setText("");
        txtField10.setText("0.00");
        txtField11.setText("0.00");
        txtField12.setText("0.00");
        txtField13.setText("0.00");
        txtField14.setText("0.00");
        txtField15.setText("0.00");
        txtField15.setText("0.00");
        txtField16.setText("");
        txtField17.setText("");
        txtField20.setText("");
        txtField50.setText("");
        txtField51.setText("");
        
        txtDetail03.setText("");
        txtDetail04.setText("");
        txtDetail05.setText("");
        txtDetail07.setText("0");
        txtDetail08.setText("0.00");
        txtDetail09.setText("0.00");
        txtDetail80.setText("");
        
        Label09.setText("0.00");
        Combo06.getSelectionModel().select(1);
        
        pnRow = 51;
        pnOldRow = -1;
        pnIndex = -1;
        pdIndex = -1;
        setTranStat("-1");
        
        psOldRec = "";
        psReferNox = "";

        psOrderNox = "";
        
        data.clear();
    }
    
    private void txtField_KeyPressed(KeyEvent event){
        TextField txtField = (TextField)event.getSource();
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        String lsValue = txtField.getText();
        if (event.getCode() == F3){
            switch (lnIndex){
                case 2: /*sBranchCd*/
                case 5: /*sSupplier*/
                case 8: /*sTermCode*/
                case 17: /*sSourceNo*/
                case 20: /*sInvTypCd*/
                case 29: /*sDeptIDxx*/    
                    if (poTrans.SearchMaster(lnIndex, lsValue, false))
                        CommonUtils.SetNextFocus(txtField);
                    return;
                case 50: /*ReferNox*/
                    poTrans.setTranStat(Combo21.getSelectionModel().getSelectedIndex());
                    if(poTrans.BrowseRecord(lsValue, true)==true){
                        loadRecord();
                        pnEditMode = poTrans.getEditMode();
                    } else {
                        clearFields();
                        pnEditMode = EditMode.UNKNOWN; break;
                    }
                            
                    return;
                case 51: /*Supplier*/
                    poTrans.setTranStat(Combo21.getSelectionModel().getSelectedIndex());
                    if(poTrans.BrowseRecord(lsValue, false)== true){
                        loadRecord(); 
                        pnEditMode = poTrans.getEditMode();
                    } else {
                        clearFields();
                        pnEditMode = EditMode.UNKNOWN;
                    }
            }
        } 
        
        switch (event.getCode()){
        case ENTER:
        case DOWN:
            CommonUtils.SetNextFocus(txtField);
            break;
        case UP:
            CommonUtils.SetPreviousFocus(txtField);
        }
    }
    
    private void ComboBox_KeyPressed(KeyEvent event){
        ComboBox cmbBox = (ComboBox)event.getSource();
        
        switch (event.getCode()){
        case ENTER:
        case DOWN:
            CommonUtils.SetNextFocus(cmbBox);
            break;
        case UP:
            CommonUtils.SetPreviousFocus(cmbBox);
        }
    }
    
    private void txtFieldArea_KeyPressed(KeyEvent event){
        if (event.getCode() == ENTER || event.getCode() == DOWN){ 
            event.consume();
            CommonUtils.SetNextFocus((TextArea)event.getSource());
        }else if(event.getCode()== UP ){
            event.consume();
            CommonUtils.SetPreviousFocus((TextArea)event.getSource());
        }
    }
    
    private void txtDetail_KeyPressed(KeyEvent event){
        TextField txtDetail = (TextField) event.getSource();
        int lnIndex = Integer.parseInt(txtDetail.getId().substring(9, 11));
        String lsValue = txtDetail.getText();
        JSONObject loJSON;
        
        if (event.getCode() == F3){                    
            if (lsValue.isEmpty()) lsValue = "%";
            
            switch (lnIndex){
                case 3:                    
                    loJSON = poTrans.SearchDetail(pnRow, 3, lsValue, false, false);                  
                    if (loJSON != null) txtDetail03.setText((String) loJSON.get("sTransNox"));
                    break;
                case 4:
                    loJSON = poTrans.SearchDetail(pnRow, 4, lsValue, false, false);
                    if (loJSON != null){
                        psBarCodex = (String) loJSON.get("sBarCodex");
                        psDescript = (String) loJSON.get("sDescript");
                        txtDetail04.setText(psBarCodex);
                        txtDetail80.setText(psDescript);
                        loadDetail();
                    }
                    break;
                case 80:
                    loJSON = poTrans.SearchDetail(pnRow, 4, lsValue, true, false);
                    if (loJSON != null){
                        psBarCodex = (String) loJSON.get("sBarCodex");
                        psDescript = (String) loJSON.get("sDescript");
                        txtDetail04.setText(psBarCodex);
                        txtDetail80.setText(psDescript);
                        loadDetail();
                    }
                    break;
                case 5:
                    loJSON = poTrans.SearchDetail(pnRow, 5, lsValue, false, false);
                    if (loJSON != null){
                        psBarCodex = (String) loJSON.get("sBarCodex");
                        txtDetail05.setText(psBarCodex);
                        loadDetail();
                    }
                    break;
            }
        }
        
        switch (event.getCode()){
        case ENTER:
        case DOWN:
            CommonUtils.SetNextFocus(txtDetail);
            break;
        case UP:
            CommonUtils.SetPreviousFocus(txtDetail);
        }
    }
    
    private void deleteDetail(){
        if (pnOldRow == -1) return;
        if (poTrans.deleteDetail(pnOldRow)){
            pnRow = poTrans.getDetailCount() - 1;
            pnOldRow = pnRow;
            
            loadDetail();
            setDetailInfo();
        }
    }  
    
    private void loadDetail(){
        int lnCtr;
        int lnRow = poTrans.getDetailCount();
        
        data.clear();
        /*ADD THE DETAIL*/
        
        Inventory loInventory;
        String lsOldCode = "";
        for(lnCtr = 0; lnCtr <= lnRow -1; lnCtr++){           
            if (!"".equals((String) poTrans.getDetail(lnCtr, "sStockIDx"))) {
                loInventory = poTrans.GetInventory((String) poTrans.getDetail(lnCtr, "sStockIDx"), true, false);
                psBarCodex = (String) loInventory.getMaster("sBarCodex");
                psDescript = (String) loInventory.getMaster("sDescript");
                
                if (!"".equals((String) poTrans.getDetail(lnCtr, "sReplacID"))){
                    loInventory = poTrans.GetInventory((String) poTrans.getDetail(lnCtr, "sReplacID"), true, false);
                    lsOldCode = (String) loInventory.getMaster("sBarCodex");
                }
                    
                data.add(new TableModel(String.valueOf(lnCtr + 1), 
                            (String) poTrans.getDetail(lnCtr, "sOrderNox"),
                            psBarCodex, 
                            psDescript,
                            lsOldCode,
                            cUnitType.get(Integer.parseInt((String) poTrans.getDetail(lnCtr, "cUnitType"))),
                            String.valueOf(poTrans.getDetail(lnCtr, "nQuantity")),
                            CommonUtils.NumberFormat(Double.valueOf(poTrans.getDetail(lnCtr, "nUnitPrce").toString()), "#,##0.00"),
                            CommonUtils.NumberFormat(Double.valueOf(poTrans.getDetail(lnCtr, "nFreightx").toString()), "#,##0.00"),
                            ""));
            } else {
                data.add(new TableModel(String.valueOf(lnCtr + 1), 
                            "",
                            (String) poTrans.getDetail(lnCtr, 100), 
                            (String) poTrans.getDetail(lnCtr, 101),
                            "",
                            cUnitType.get(Integer.parseInt((String) poTrans.getDetail(lnCtr, "cUnitType"))),
                            String.valueOf(poTrans.getDetail(lnCtr, "nQuantity")),
                            CommonUtils.NumberFormat(Double.valueOf(poTrans.getDetail(lnCtr, "nUnitPrce").toString()), "#,##0.00"),
                            CommonUtils.NumberFormat(Double.valueOf(poTrans.getDetail(lnCtr, "nFreightx").toString()), "#,##0.00"),
                            ""));
            }
        }

        /*FOCUS ON FIRST ROW*/
        if (!data.isEmpty()){
            table.getSelectionModel().select(pnRow);
            table.getFocusModel().focus(pnRow); 
            pnRow = table.getSelectionModel().getSelectedIndex();           
            
            setDetailInfo();
        }
        
        Label09.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster(9).toString()) + Double.valueOf(poTrans.getMaster(11).toString()), "#,##0.00"));
        txtField11.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster(11).toString()), "#,##0.00"));
    }    
    
    @Override
    public void setGRider(GRider foGRider){this.poGRider = foGRider;}
    
    private final String pxeModuleName = "POReceivingController";
    private static GRider poGRider;
    private XMPOReceiving poTrans;
    
    private int pnEditMode = -1;
    private boolean pbLoaded = false;
    
    private final String pxeDateFormat = "yyyy-MM-dd";
    private final String pxeDateDefault = java.time.LocalDate.now().toString();
    
    private TableModel model;
    private ObservableList<TableModel> data = FXCollections.observableArrayList();
    ObservableList<String> cUnitType = FXCollections.observableArrayList("Demo", "Regular", "Repo");
    ObservableList<String> cDivision = FXCollections.observableArrayList("Motorcycle", "Mobile Phone", "Hotel", "General");
    
    private int pnIndex = -1;
    private int pdIndex = -1;
    private int pnRow = -1;
    private int pnOldRow = -1;
    
    private String psOldRec = "";
    private String psReferNox = "";
    
    private String psOrderNox = "";
    
    private String psBarCodex;
    private String psDescript;
    
    ObservableList<String> cTranStat = FXCollections.observableArrayList("Open", "Closed", "Posted", "Cancelled", "Void");
    
    final ChangeListener<? super Boolean> Combo_Focus = (o,ov,nv)->{
        if (!pbLoaded) return;
        
        ComboBox loField = (ComboBox)((ReadOnlyBooleanPropertyBase)o).getBean();
        
        if(!nv){ /*Lost Focus*/
            switch (loField.getId()){
                case "Combo06":
                    String sUnitType = (String) loField.getValue();
                    if(!cUnitType.contains(sUnitType)){
                        Combo06.getSelectionModel().select(1);
                    }
                    Combo06.getSelectionModel().getSelectedIndex();
                    poTrans.setDetail(pnRow, "cUnitType", String.valueOf(loField.getSelectionModel().getSelectedIndex()));
                    loadDetail();
                    break;
            }       
        }         
    };
    
    final ChangeListener<? super Boolean> txtArea_Focus = (o,ov,nv)->{
        if (!pbLoaded) return;
        
        TextArea txtField = (TextArea)((ReadOnlyBooleanPropertyBase)o).getBean();
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        String lsValue = txtField.getText();
        
        if (lsValue == null) return;
        
        if(!nv){ /*Lost Focus*/            
            switch (lnIndex){
                case 16: /*sRemarksx*/
                    if (lsValue.length() > 256) lsValue = lsValue.substring(0, 256);
                    
                    poTrans.setMaster(lnIndex, CommonUtils.TitleCase(lsValue));
                    txtField.setText((String)poTrans.getMaster(lnIndex));
            }
            pnIndex = lnIndex;
        }else{ 
            pnIndex = -1;
            txtField.selectAll();
        }
    };
    
    final ChangeListener<? super Boolean> txtDetail_Focus = (o,ov,nv)->{
        if (!pbLoaded) return;
        
        TextField txtDetail = (TextField)((ReadOnlyBooleanPropertyBase)o).getBean();
        int lnIndex = Integer.parseInt(txtDetail.getId().substring(9, 11));
        String lsValue = txtDetail.getText();
        
        if (pnRow < 0) return;
        if (lsValue == null) return;
        
        if(!nv){ /*Lost Focus*/     
            switch (lnIndex){
                case 3: /*Order No*/
                case 5: /*Replaced ID*/
                    break;
                case 4: /*Barcode*/
                    if (poTrans.getDetail(pnRow, "sStockIDx").equals("")){
                        poTrans.setDetail(pnRow, 100, txtDetail04.getText());
                    }
                    break;
                case 80: /*Description*/
                    //send the barcode and descript to class if it has no stock id
                    if (poTrans.getDetail(pnRow, "sStockIDx").equals("")){                        
                        if (txtDetail80.getText().equals("")){
                            MsgBox.showOk("Description must have a value if stock is not existing.");
                            return;
                        }
                        poTrans.setDetail(pnRow, 101, txtDetail80.getText());
                    }
                    break;
                case 7: /*Quantity*/
                    int lnValue = 0;
                    try {
                        /*this must be numeric*/
                        lnValue = Integer.parseInt(lsValue);
                    } catch (Exception e) {
                        MsgBox.showOk("Please input numbers only.");                            
                        txtDetail.requestFocus(); 
                    }
                    poTrans.setDetail(pnRow, lnIndex, lnValue);
                    break;
                case 8: /*nUnitPrce*/
                case 9: /*nFreightx*/
                    double x = 0.00;
                    try {
                        /*this must be numeric*/
                        x = Double.parseDouble(lsValue);
                    } catch (Exception e) {
                        MsgBox.showOk("Please input numbers only.");       
                        txtDetail.requestFocus(); 
                    }
                    poTrans.setDetail(pnRow, lnIndex, x);
                    break;
                default:
                    break;
            }
            pnOldRow = table.getSelectionModel().getSelectedIndex();
            pdIndex= lnIndex;
        } else {
            pdIndex = -1 ;
            txtDetail.selectAll();
        }
    };
    
    final ChangeListener<? super Boolean> txtField_Focus = (o,ov,nv)->{
        if (!pbLoaded) return;
        
        TextField txtField = (TextField)((ReadOnlyBooleanPropertyBase)o).getBean();
        int lnIndex = Integer.parseInt(txtField.getId().substring(8, 10));
        String lsValue = txtField.getText();
        
        if (lsValue == null) return;
            
        if(!nv){ /*Lost Focus*/           
            switch (lnIndex){
                case 2: /*sBranchCd*/
                case 5: /*sSupplier*/
                case 29: /*sDeptIDxx*/
                    return;
                case 6: /*sReferNox*/
                    if(txtField.getText().equals("")){
                       txtField.setText("");
                       poTrans.setMaster(lnIndex, "");
                    }
                    
                    if (txtField.getText().length() > 8){
                        MsgBox.showOk("Max characters for `Reference No.` exceeds the limit");       
                        txtField.requestFocus();
                        return;
                    }
                    poTrans.setMaster(lnIndex, txtField.getText());
                    return;
                case 8: /*sTermCode*/
                case 20: /*sInvTypCd*/
                    return;
                case 3: /*dTransact*/
                case 7: /*dRefernce*/
                    if (CommonUtils.isDate(txtField.getText(), pxeDateFormat)){
                        poTrans.setMaster(lnIndex, CommonUtils.toDate(txtField.getText()));
                    } else{
                        MsgBox.showOk("Invalid date entry. \n\nDate format must be yyyy-MM-dd (e.g. 1991-07-07)");       
                        poTrans.setMaster(lnIndex, CommonUtils.toDate(pxeDateDefault));
                    }
                    return;
                case 10: /*nVATRatex*/
                case 11: /*nTWithHld*/
                    break;
                case 12: /*nDiscount*/
                    double x = 0.00;
                    try {
                        /*this must be numeric*/
                        x = Double.parseDouble(lsValue);
                    } catch (NumberFormatException e) {
                        x = 0.00;
                        txtField.setText("0.00");
                    }
                    poTrans.setMaster(lnIndex, Double.parseDouble(txtField.getText()));
                    break;

                case 13: /*nAddDiscx*/
                case 14: /*nAmtPaidx*/
                case 15: /*nFreightx*/
                    double y = 0.00;
                    try {
                        /*this must be numeric*/
                        y = Double.parseDouble(lsValue);
                    } catch (NumberFormatException e) {
                        y = 0.00;
                        txtField.setText("0.00");
                        
                    }
                    poTrans.setMaster(lnIndex, Double.parseDouble(txtField.getText()));
                    break;

                case 50:
                case 51:
                    return;
                default:
                    MsgBox.showOk("Text field with name " + txtField.getId() + " not registered.");
            }
            pnIndex = lnIndex;
        } else{
            switch (lnIndex){
                case 3: /*dTransact*/
                case 7: /*dRefernce*/
                    try{
                        txtField.setText(CommonUtils.xsDateShort(lsValue));
                    }catch(ParseException e){
                        MsgBox.showOk(e.getMessage());
                    }
                    txtField.selectAll();
                    break;
            }
            pnIndex = lnIndex;
            txtField.selectAll();
        }
        
    };
    
    IMasterDetail poCallBack = new IMasterDetail() {
        @Override
        public void MasterRetreive(int fnIndex) {
            getMaster(fnIndex);
        }

        @Override
        public void DetailRetreive(int fnIndex) {
            switch(fnIndex){
                case 7:
                    txtDetail07.setText(String.valueOf(poTrans.getDetail(pnRow,7)));
                    loadDetail();
                        
                    poTrans.addDetail(); //pass psOrderNox here
                    
                    pnRow = poTrans.getDetailCount() - 1;

                    loadDetail();
                    if (txtDetail04.getText().isEmpty()){
                        txtDetail04.requestFocus();
                        txtDetail04.selectAll();
                    } else{
                        txtDetail03.requestFocus();
                        txtDetail03.selectAll();
                    }
                    break;
                case 8:
                    txtDetail08.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getDetail(pnRow, fnIndex).toString()), "0.00"));
                    loadDetail();
                    break;
                case 9:
                    txtDetail09.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getDetail(pnRow, fnIndex).toString()), "0.00"));
                    loadDetail();
                    break;
            }
        }
    };
    
    private void getMaster(int fnIndex){
        switch(fnIndex){
        case 2:
            XMProject loProject = poTrans.GetProject((String)poTrans.getMaster(fnIndex), true);
            if (loProject != null) txtField02.setText((String) loProject.getMaster("sProjDesc"));
            break;
        case 5:
            JSONObject loSupplier = poTrans.GetSupplier((String)poTrans.getMaster(fnIndex), true);
            if (loSupplier != null) txtField05.setText((String) loSupplier.get("sClientNm"));
            break;
        case 6:
            txtField06.setText((String)poTrans.getMaster(fnIndex));
            break;
        case 8:
            XMTerm loTerm = poTrans.GetTerm((String)poTrans.getMaster(fnIndex), true);
            if (loTerm != null) txtField08.setText((String) loTerm.getMaster("sDescript"));
            break;
        case 20:
            XMInventoryType loInv = poTrans.GetInventoryType((String)poTrans.getMaster(fnIndex), true);
            if (loInv != null) txtField20.setText((String) loInv.getMaster("sDescript"));
            break;
        case 3:
            txtField03.setText(CommonUtils.xsDateMedium((Date)poTrans.getMaster(fnIndex)));
            break;
        case 7:
            txtField07.setText(CommonUtils.xsDateMedium((Date)poTrans.getMaster(fnIndex)));
            break;
        case 12:
            txtField12.setText(CommonUtils.NumberFormat((Double)poTrans.getMaster(fnIndex), "0.00"));
            loadDetail();
            break;
        case 13:
            txtField13.setText(CommonUtils.NumberFormat((Double)poTrans.getMaster(fnIndex), "0.00"));
            loadDetail();
            break;
        case 14:
            txtField14.setText(CommonUtils.NumberFormat((Double)poTrans.getMaster(fnIndex), "0.00"));
            loadDetail();
            break;    
       case 15:
            txtField15.setText(CommonUtils.NumberFormat((Double)poTrans.getMaster(fnIndex), "0.00"));
            loadDetail();
            break;
        case 9:
            Label09.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster(9).toString()), "#,##0.00"));      
            break;
        case 17:
            txtField17.setText((String) poTrans.getMaster("sSourceNo"));
            
            //load project code
            XMProject instance1 = poTrans.GetProject((String) poTrans.getMaster("sBranchCd"), true);
            if (instance1 != null) txtField02.setText((String) instance1.getMaster("sProjDesc"));
            
            JSONObject instance2 = poTrans.GetSupplier((String)poTrans.getMaster(fnIndex), true);
            if (instance2 != null) txtField05.setText((String) instance2.get("sClientNm"));
            
            XMTerm instance3 = poTrans.GetTerm((String)poTrans.getMaster(fnIndex), true);
            if (instance3 != null) txtField08.setText((String) instance3.getMaster("sDescript"));
            
            XMInventoryType instance4 = poTrans.GetInventoryType((String) poTrans.getMaster("sInvTypCd"), true);
            if (instance4!= null) txtField20.setText((String) instance4.getMaster("sDescript"));
                        
            pnRow = poTrans.getDetailCount() - 1;
            loadDetail();
            setDetailInfo();
            
            Label09.setText(CommonUtils.NumberFormat(Double.valueOf(poTrans.getMaster(9).toString()) + Double.valueOf(poTrans.getMaster(11).toString()), "#,##0.00"));
            break;
        }
    }
    
    private void setTranStat(String fsValue){
        switch (fsValue){
            case "0":
                imgTranStat.setImage(new Image("org/rmj/integsys/engr/images/open.png")); break;
            case "1":
                imgTranStat.setImage(new Image("org/rmj/integsys/engr/images/closed.png")); break;
            case "2":
                imgTranStat.setImage(new Image("org/rmj/integsys/engr/images/posted.png")); break;
            case "3":
                imgTranStat.setImage(new Image("org/rmj/integsys/engr/images/cancelled.png")); break;
            case "4":
                imgTranStat.setImage(new Image("org/rmj/integsys/engr/images/void.png")); break;
            default:
                imgTranStat.setImage(new Image("org/rmj/integsys/engr/images/unknown.png"));
        }    
    }
}