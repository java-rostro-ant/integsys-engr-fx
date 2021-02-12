package org.rmj.integsys.engr.base;

import java.sql.ResultSet;
import org.rmj.appdriver.GRider;

public interface iDashboard{
    public void setGRider(GRider foGRider);
    public void setCallback(iCallBack foCallBack);
    public ResultSet getProjects();
    public ResultSet getPurchases();
    public ResultSet getReceiving();
    public ResultSet getReturnsxx();
    public ResultSet getOpenTransfer();
    public ResultSet getUnpostedTransfer();
    public ResultSet getProjectPieData(String fsProjCode);
}
