package org.rmj.integsys.engr.base;

import java.sql.ResultSet;
import java.util.TimerTask;
import org.rmj.appdriver.GRider;

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
                            "  sProjCode" +
                            ", sProjDesc" +
                        " FROM Project" +
                        " WHERE cRecdStat = '1'" +
                        " ORDER BY sProjDesc";
        
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
                        " WHERE cTranStat = 1" +
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
                        " ORDER BY a.dTransact, a.sTransNox";
        
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
