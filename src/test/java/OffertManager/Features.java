package OffertManager;


import com.google.gson.Gson;

import static com.claro.sp.ta.db.DatabaseQuery.databaseQuery;

public class Features {
    private String user, password, environment, country;

    public Features(String user, String password, String environment, String country) {
        this.user = user;
        this.password = password;
        this.environment = environment;
        this.country = country;
    }

    public Features() {
    }

    public Features getFeatures(String FTR_ID) {
        String sql = "select *\n" +
                "       from features f\n" +
                "       where f.ftr_id = ?";
        Features features = new Features();
        try {
            String result = databaseQuery()
                    .user(this.user)
                    .password(this.password)
                    .environment(this.environment)
                    .country(this.country)
                    .prod()
                    .select(sql)
                    .addParameter(FTR_ID)
                    .execute();

            String cadena = result.substring(1, result.length() - 1);
            Gson gson = new Gson();
            features = (Features) gson.fromJson(cadena, Features.class);
        } catch (Exception e) {
            System.out.println("[FALLO] Metodo getFeatures clase Features ");
        }
        return features;
    }

//    public static void main(String[] args) {
//        Features features = new Features("ghct", "auto_333", "TEST", "AR");
//        System.out.println(features.getFeatures("2514").toString());
//    }

    ///Variable, get
    private String FTR_ID;
    private String FTR_DESCRIPTION;
    private String FTR_SWITCH_FLAG;
    private String FTR_TECHNOLOGY;
    private String FTR_AVAILABILITY;
    private String FTR_VOICE_FLAG;
    private String FTR_LIMIT_LICENSES;
    private String FTR_USED_LICENSES;
    private String FTR_NEEDS_SPECIAL_PRODUCT;
    private String FTR_INDEX_PROFILE;
    private String FTR_SPEED_QOSP;
    private String FTR_FEP_ID;
    private String FTR_SCP_PACKAGE_ID;
    private String FTR_SPEED;
    private String FTR_TYPE;
    private String FTR_WEB_DESCRIPTION;
    private String FTR_VISIBILITY;
    private String FTR_HELP;
    private String FTR_BOUQUET;
    private String FTR_DEACTIVATE;
    private String FTR_SEND_NE;

    public String getFTR_ID() {
        return FTR_ID;
    }

    public String getFTR_DESCRIPTION() {
        return FTR_DESCRIPTION;
    }

    public String getFTR_SWITCH_FLAG() {
        return FTR_SWITCH_FLAG;
    }

    public String getFTR_TECHNOLOGY() {
        return FTR_TECHNOLOGY;
    }

    public String getFTR_AVAILABILITY() {
        return FTR_AVAILABILITY;
    }

    public String getFTR_VOICE_FLAG() {
        return FTR_VOICE_FLAG;
    }

    public String getFTR_LIMIT_LICENSES() {
        return FTR_LIMIT_LICENSES;
    }

    public String getFTR_USED_LICENSES() {
        return FTR_USED_LICENSES;
    }

    public String getFTR_NEEDS_SPECIAL_PRODUCT() {
        return FTR_NEEDS_SPECIAL_PRODUCT;
    }

    public String getFTR_INDEX_PROFILE() {
        return FTR_INDEX_PROFILE;
    }

    public String getFTR_SPEED_QOSP() {
        return FTR_SPEED_QOSP;
    }

    public String getFTR_FEP_ID() {
        return FTR_FEP_ID;
    }

    public String getFTR_SCP_PACKAGE_ID() {
        return FTR_SCP_PACKAGE_ID;
    }

    public String getFTR_SPEED() {
        return FTR_SPEED;
    }

    public String getFTR_TYPE() {
        return FTR_TYPE;
    }

    public String getFTR_WEB_DESCRIPTION() {
        return FTR_WEB_DESCRIPTION;
    }

    public String getFTR_VISIBILITY() {
        return FTR_VISIBILITY;
    }

    public String getFTR_HELP() {
        return FTR_HELP;
    }

    public String getFTR_BOUQUET() {
        return FTR_BOUQUET;
    }

    public String getFTR_DEACTIVATE() {
        return FTR_DEACTIVATE;
    }

    public String getFTR_SEND_NE() {
        return FTR_SEND_NE;
    }

    @Override
    public String toString() {
        return "Features{" +
                "FTR_ID='" + FTR_ID + '\'' +
                ", FTR_DESCRIPTION='" + FTR_DESCRIPTION + '\'' +
                ", FTR_SWITCH_FLAG='" + FTR_SWITCH_FLAG + '\'' +
                ", FTR_TECHNOLOGY='" + FTR_TECHNOLOGY + '\'' +
                ", FTR_AVAILABILITY='" + FTR_AVAILABILITY + '\'' +
                ", FTR_VOICE_FLAG='" + FTR_VOICE_FLAG + '\'' +
                ", FTR_LIMIT_LICENSES='" + FTR_LIMIT_LICENSES + '\'' +
                ", FTR_USED_LICENSES='" + FTR_USED_LICENSES + '\'' +
                ", FTR_NEEDS_SPECIAL_PRODUCT='" + FTR_NEEDS_SPECIAL_PRODUCT + '\'' +
                ", FTR_INDEX_PROFILE='" + FTR_INDEX_PROFILE + '\'' +
                ", FTR_SPEED_QOSP='" + FTR_SPEED_QOSP + '\'' +
                ", FTR_FEP_ID='" + FTR_FEP_ID + '\'' +
                ", FTR_SCP_PACKAGE_ID='" + FTR_SCP_PACKAGE_ID + '\'' +
                ", FTR_SPEED='" + FTR_SPEED + '\'' +
                ", FTR_TYPE='" + FTR_TYPE + '\'' +
                ", FTR_WEB_DESCRIPTION='" + FTR_WEB_DESCRIPTION + '\'' +
                ", FTR_VISIBILITY='" + FTR_VISIBILITY + '\'' +
                ", FTR_HELP='" + FTR_HELP + '\'' +
                ", FTR_BOUQUET='" + FTR_BOUQUET + '\'' +
                ", FTR_DEACTIVATE='" + FTR_DEACTIVATE + '\'' +
                ", FTR_SEND_NE='" + FTR_SEND_NE + '\'' +
                '}';
    }
}
