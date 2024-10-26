
import org.json.simple.JSONObject;
import org.rmj.appdriver.GProperty;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.agentfx.service.TokenRestAPI;

public class GET_authorized_approver {
    public static void main(String[] args) {
        String path;
        if(System.getProperty("os.name").toLowerCase().contains("win")){
            path = "D:/GGC_Java_Systems";
        } else{
            path = "/srv/GGC_Java_Systems";
        }
        
        System.setProperty("sys.default.path.config", path);
        
        GRider poGRider = new GRider("General");
        GProperty loProp = new GProperty("GhostRiderXP");

        if (!poGRider.loadEnv("General")) {
            System.err.println(poGRider.getErrMsg());
            System.exit(1);
        }
        
        if (!poGRider.logUser("General", "M001111122")) {
            System.err.println(poGRider.getErrMsg());
            System.exit(1);
        }     
        
        JSONObject loJSON = new JSONObject();
        loJSON.put("sRqstType", "PO");
        loJSON.put("nLevelxxx", 1);
        
        loJSON = TokenRestAPI.RequestAuthorized(poGRider, loJSON);
        System.out.println(loJSON.toJSONString());
    }
}
