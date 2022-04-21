package ValidateManager;

import com.claro.sp.model.Properties;

public class Prepay_Parameters {

    private String user;
    private String password;
    private String environment;
    private String country;

    public Prepay_Parameters() {
        this.user = Properties.userProd;
        this.password = Properties.passwordProd;
        this.environment = Properties.environment;
        this.country = Properties.country;
    }

}
