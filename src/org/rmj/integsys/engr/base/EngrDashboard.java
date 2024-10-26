package org.rmj.integsys.engr.base;

import java.sql.ResultSet;
import java.util.TimerTask;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.SQLUtil;

/**
 *
 * @author mac 2021.02.11
 */
public class EngrDashboard extends TimerTask implements iDashboard{  
    public EngrDashboard(){
    }
    
    @Override
    public void run() {
        poCallBack.MasterRetreive("xProjects");
        poCallBack.MasterRetreive("xPurchase");
        poCallBack.MasterRetreive("xReceivex");
        poCallBack.MasterRetreive("xReturnsx");
        poCallBack.MasterRetreive("xOpnTrnsf");
        poCallBack.MasterRetreive("xUnpTrnsf");
    }
    
    @Override
    public ResultSet getProjects(){
        String lsSQL = "SELECT" +
                            "  a.sProjCode" +
                            ", a.sProjDesc" + 
                            ", IFNULL(SUM(b.nQtyOnHnd * c.nUnitPrce), 0.00) xValuexxx" +
                        " FROM Project a" +
                            " LEFT JOIN Inv_Master b" +
                                " ON a.sProjCode = b.sBranchCd" +
                            " LEFT JOIN Inventory c" +
                                " ON b.sStockIDx = c.sStockIDx" +
                        " WHERE a.cRecdStat = '1'" + 
                            " AND a.sProjCode = " + SQLUtil.toSQL(poGRider.getBranchCode()) +
                        " UNION" +
                        " (SELECT" + 
                            "  a.sProjCode" +
                            ", a.sProjDesc" + 
                            ", IFNULL(SUM(b.nQtyOnHnd * c.nUnitPrce), 0.00) xValuexxx" +
                        " FROM Project a" +
                            " LEFT JOIN Inv_Master b" +
                                " ON a.sProjCode = b.sBranchCd" +
                            " LEFT JOIN Inventory c" +
                                " ON b.sStockIDx = c.sStockIDx" +
                        " WHERE a.cRecdStat = '1'" + 
                            " AND a.sProjCode <> " + SQLUtil.toSQL(poGRider.getBranchCode()) +
                        " GROUP BY a.sProjCode)";
        
        return poGRider.executeQuery(lsSQL);
    }
    
    @Override
    public ResultSet getPurchases() {
        String lsSQL = "SELECT" +
                            "  a.sTransNox" +
                            ", a.dTransact" +
                            ", b.sProjDesc" +
                            ", a.sReferNox" +
                        " FROM PO_Master a" +
                            " LEFT JOIN Project b" +
                                " ON a.sBranchCd = b.sProjCode" +
                        " WHERE cTranStat < 1" +
                            " AND LEFT(a.sTransNox, 4) LIKE " + SQLUtil.toSQL(poGRider.getBranchCode()+ "%") +
                        " ORDER BY a.dTransact, a.sTransNox";
        
        return poGRider.executeQuery(lsSQL);
    }

    @Override
    public ResultSet getReceiving() {
        String lsSQL = "SELECT" +
                            "  a.sTransNox" +
                            ", a.dTransact" +
                            ", b.sProjDesc" +
                            ", a.sReferNox" +
                        " FROM PO_Receiving_Master a" +
                            " LEFT JOIN Project b" +
                                " ON a.sBranchCd = b.sProjCode" +
                        " WHERE cTranStat < 1" +
                            " AND LEFT(a.sTransNox, 4) LIKE " + SQLUtil.toSQL(poGRider.getBranchCode()+ "%") +
                        " ORDER BY a.dTransact, a.sTransNox";
        
        return poGRider.executeQuery(lsSQL);
    }

    @Override
    public ResultSet getReturnsxx() {
        String lsSQL = "SELECT" +
                            "  a.sTransNox" +
                            ", a.dTransact" +
                            ", b.sProjDesc" +
                        " FROM PO_Return_Master a" +
                            " LEFT JOIN Project b" +
                                " ON a.sBranchCd = b.sProjCode" +
                        " WHERE cTranStat < 1" +
                            " AND LEFT(a.sTransNox, 4) LIKE " + SQLUtil.toSQL(poGRider.getBranchCode()+ "%") +
                        " ORDER BY a.dTransact, a.sTransNox";
        
        return poGRider.executeQuery(lsSQL);
    }
    
    @Override
    public ResultSet getOpenTransfer() {
        String lsSQL = "SELECT" +
                            "  a.sTransNox" +
                            ", a.dTransact" +
                            ", b.sProjDesc xSourcexx" +
                            ", c.sProjDesc xDestinat" +
                        " FROM Inv_Transfer_Master a" +
                            " LEFT JOIN Project b" +
                                " ON a.sBranchCd = b.sProjCode" +
                            " LEFT JOIN Project c" +
                                " ON a.sDestinat = c.sProjCode" +
                        " WHERE cTranStat = 0" +
                            " AND LEFT(a.sTransNox, 4) LIKE " + SQLUtil.toSQL(poGRider.getBranchCode()+ "%") +
                        " ORDER BY a.dTransact, a.sTransNox";
        
        return poGRider.executeQuery(lsSQL);
    }

    @Override
    public ResultSet getUnpostedTransfer() {
        String lsSQL = "SELECT" +
                            "  a.sTransNox" +
                            ", a.dTransact" +
                            ", b.sProjDesc xSourcexx" +
                            ", c.sProjDesc xDestinat" +
                        " FROM Inv_Transfer_Master a" +
                            " LEFT JOIN Project b" +
                                " ON a.sBranchCd = b.sProjCode" +
                            " LEFT JOIN Project c" +
                                " ON a.sDestinat = c.sProjCode" +
                        " WHERE cTranStat = 1" +
                            " AND LEFT(a.sTransNox, 4) LIKE " + SQLUtil.toSQL(poGRider.getBranchCode()+ "%") +
                        " ORDER BY a.dTransact, a.sTransNox";
        
        return poGRider.executeQuery(lsSQL);
    }
    
    @Override
    public ResultSet getProjectPieData(String fsProjCode) {
        String lsSQL = "SELECT" +
                            "  b.sInvTypCd" +
                            ", IFNULL(SUM(a.nQtyOnHnd * b.nUnitPrce), 0.00) xValuexxx" +
                        " FROM Inv_Master a" +
                            ", Inventory b" +
                        " WHERE a.sStockIDx = b.sStockIDx" +
                            " AND a.sBranchCd = " + SQLUtil.toSQL(fsProjCode) +
                        " GROUP BY b.sInvTypCd";
        
        return poGRider.executeQuery(lsSQL);
    }
    
    @Override
    public void setGRider(GRider foGRider) {
        poGRider = foGRider;
    }

    @Override
    public void setCallback(iCallBack foCallBack) {
        poCallBack = foCallBack;
    }
    
    GRider poGRider;
    iCallBack poCallBack;
}
