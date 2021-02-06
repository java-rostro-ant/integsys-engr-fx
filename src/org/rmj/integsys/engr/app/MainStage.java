package org.rmj.integsys.engr.app;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.rmj.appdriver.GRider;
import org.rmj.integsys.engr.views.MDIMainController;


public class MainStage extends Application {
    public final static String pxeMainFormTitle = "Engineering Inventory Management System v1.0";
    public final static String pxeMainForm = "../views/MDIMain.fxml";
    public final static String pxeStageIcon = "org/rmj/integsys/engr/images/icon.png";
    public static GRider poGRider;
    
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(pxeMainForm));
        
        //get the controller of the main interface
        MDIMainController loControl = new MDIMainController();
        //set the GRider Application Driver to the controller
        loControl.setGRider(poGRider);
        
        //the controller class to the main interface
        fxmlLoader.setController(loControl);
        
        //load the main interface
        Parent parent = fxmlLoader.load();
        //set the main interface as the scene
        Scene scene = new Scene(parent);
        
        //get the screen size
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(pxeStageIcon));
        stage.setTitle(pxeMainFormTitle);
        
        //set stage as maximized but not full screen
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void setGRider(GRider foGRider){
        this.poGRider = foGRider;
    }
    
    /*Parameters*/
    public final static String pxeBrand = "Brand.fxml";
    public final static String pxeCategory = "Category.fxml";
    public final static String pxeCategory2 = "Category2.fxml";
    public final static String pxeCategory3 = "Category3.fxml";
    public final static String pxeCategory4 = "Category4.fxml";
    public final static String pxeColor = "Color.fxml";
    public final static String pxeCompany = "Company.fxml";
    public final static String pxeInventory = "Inventory.fxml";
    public final static String pxeInventoryLocation = "InventoryLocation.fxml";
    public final static String pxeInventoryType = "InventoryType.fxml";
    public final static String pxeMeasure = "Measure.fxml";
    public final static String pxeModel = "Model.fxml";
    public final static String pxeSupplier = "Supplier.fxml";
    public final static String pxeTerm = "Term.fxml";
    public final static String pxeStocks = "InvMaster.fxml";
    
    /*Transactions*/
    public final static String pxePurchaseOrder = "PurchaseOrder.fxml";
    public final static String pxePOReceiving = "POReceiving.fxml";
    public final static String pxePOReturn = "POReturn.fxml";
    public final static String pxeInvTransfer = "InvTransfer.fxml";
    public final static String pxeInvCount = "InvCount.fxml";
    public final static String pxeDailyProd = "DailyProduction.fxml";
    public final static String pxeInvTransPosting = "InvTransPosting.fxml";
    public final static String pxeInvWaste = "InvWaste.fxml";
    public final static String pxeInvWasteReg = "InvWasteReg.fxml";
    
    /*Register*/
    public final static String pxePOReceivingReg = "POReceivingReg.fxml";
    public final static String pxePOReturnReg = "POReturnReg.fxml";
    public final static String pxePurchaseOrderReg = "PurchaseOrderReg.fxml";
    public final static String pxeInvTransferReg = "InvTransferReg.fxml";
    public final static String pxeInvCountReg = "InvCountReg.fxml";
    public final static String pxeDailyProdReg = "DailyProductionReg.fxml";
}
