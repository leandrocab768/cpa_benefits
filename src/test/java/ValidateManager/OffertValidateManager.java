package ValidateManager;

import OffertManager.Benefits;
import com.claro.sp.model.Properties;

import java.util.ArrayList;
import java.util.List;


public class OffertValidateManager {


    private String user, password, environment, country;
    private ValidateDB validateDb;

    public OffertValidateManager() {

        this.user = Properties.userProd;
        this.password = Properties.passwordProd;
        this.environment = Properties.environment;
        this.country = Properties.country;
        this.validateDb = new ValidateDB();

    }


    public List<ValidateDB> getlistValidateDb (){
        List<ValidateDB> listValidateDb = new ArrayList<ValidateDB>();







        return listValidateDb;
    }
}
