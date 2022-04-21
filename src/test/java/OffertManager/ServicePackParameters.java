package OffertManager;


import com.google.gson.Gson;

import static com.claro.sp.ta.db.DatabaseQuery.databaseQuery;

public class ServicePackParameters {
    private String user,password,environment,country;
    public ServicePackParameters(String user, String password,String environment, String country){
        this.user= user;
        this.password = password;
        this.environment = environment;
        this.country = country;
    }

    public  ServicePackParameters getServicePackParameters(String SEP_PKT_ID){
        ServicePackParameters servicePackParameters = null;
        String sql = "select *\n" +
                "from service_pack_parameters spp\n" +
                "where spp.sep_pkt_id = ? "+
                "and sysdate between spp.sep_start_date and nvl(spp.sep_end_date,sysdate+1)";
        try {
            String result = databaseQuery()
                    .user(this.user)
                    .password(this.password)
                    .environment(this.environment)
                    .country(this.country)
                    .ccard()
                    .select(sql)
                    //.log("\n\t[INFO] Obtener registro de la tabla service_pack_parameters")
                    .addParameter(SEP_PKT_ID)
                    .execute();
            String cadena = result.substring(1, result.length() - 1);
            Gson gson = new Gson();
            servicePackParameters = (ServicePackParameters) gson.fromJson(cadena, ServicePackParameters.class);

        } catch (Exception e) {
            System.out.println("[FALLO] Metodo getRegistro de la clase ServicePackParameters ");
        }
        return servicePackParameters;
    }

//    public static void main(String[] args) {
//        ServicePackParameters s = new ServicePackParameters("ghct","auto_333","TEST","AR");
//        s.getServicePackParameters("WHAT15");
//        System.out.println(s.getSEP_PKT_TECNOMEN());
//    }

    ////////////
    private String SEP_PKT_ID= "";
    private String SEP_UNITS_IN= "";
    private String SEP_RPL_ID_IN= "";
    private String SEP_RPL_ID_OUT= "";
    private String SEP_START_DATE= "";
    private String SEP_END_DATE= "";
    private String SEP_GSPR_ID= "";
    private String SEP_TYPE_TECNOMEN= "";
    private String SEP_PKT_TECNOMEN= "";
    private String SEP_RENEWAL_PKT= "";
    private String SEP_UNITS_COMBINED= "";
    private String SEP_TYPE_TECNOMEN_COMBINED= "";
    private String SEP_RENEWAL_PKT_COMBINED= "";
    private String SEP_PKT_TECNOMEN_COMBINED= "";
    private String SEP_VOP_DESTINATION= "";
    private String SEP_VOP_DESTINATION_TYPE= "";
    private String SEP_TUC_UNIT_ID= "";
    private String SEP_UNITS_REPURCHASE= "";
    private String SEP_SPG_ID= "";
    private String SEP_USE_STEALTH= "";
    private String SEP_FLAG_HABILITA_NOTIF= "";
    private String SEP_FLAG_SALDO_PACK= "";
    private String SEP_NPG_ID= "";
    private String SEP_SPG_ID_VOZ= "";
    private String SEP_GAP_MONTHS_QTY= "";
    private String SEP_LENGTH_DAYS= "";

    public String getSEP_PKT_ID() {
        return SEP_PKT_ID;
    }

    public String getSEP_UNITS_IN() {
        return SEP_UNITS_IN;
    }

    public String getSEP_RPL_ID_IN() {
        return SEP_RPL_ID_IN;
    }

    public String getSEP_RPL_ID_OUT() {
        return SEP_RPL_ID_OUT;
    }

    public String getSEP_START_DATE() {
        return SEP_START_DATE;
    }

    public String getSEP_END_DATE() {
        return SEP_END_DATE;
    }

    public String getSEP_GSPR_ID() {
        return SEP_GSPR_ID;
    }

    public String getSEP_TYPE_TECNOMEN() {
        return SEP_TYPE_TECNOMEN;
    }

    public String getSEP_PKT_TECNOMEN() {
        return SEP_PKT_TECNOMEN;
    }

    public String getSEP_RENEWAL_PKT() {
        return SEP_RENEWAL_PKT;
    }

    public String getSEP_UNITS_COMBINED() {
        return SEP_UNITS_COMBINED;
    }

    public String getSEP_TYPE_TECNOMEN_COMBINED() {
        return SEP_TYPE_TECNOMEN_COMBINED;
    }

    public String getSEP_RENEWAL_PKT_COMBINED() {
        return SEP_RENEWAL_PKT_COMBINED;
    }

    public String getSEP_PKT_TECNOMEN_COMBINED() {
        return SEP_PKT_TECNOMEN_COMBINED;
    }

    public String getSEP_VOP_DESTINATION() {
        return SEP_VOP_DESTINATION;
    }

    public String getSEP_VOP_DESTINATION_TYPE() {
        return SEP_VOP_DESTINATION_TYPE;
    }

    public String getSEP_TUC_UNIT_ID() {
        return SEP_TUC_UNIT_ID;
    }

    public String getSEP_UNITS_REPURCHASE() {
        return SEP_UNITS_REPURCHASE;
    }

    public String getSEP_SPG_ID() {
        return SEP_SPG_ID;
    }

    public String getSEP_USE_STEALTH() {
        return SEP_USE_STEALTH;
    }

    public String getSEP_FLAG_HABILITA_NOTIF() {
        return SEP_FLAG_HABILITA_NOTIF;
    }

    public String getSEP_FLAG_SALDO_PACK() {
        return SEP_FLAG_SALDO_PACK;
    }

    public String getSEP_NPG_ID() {
        return SEP_NPG_ID;
    }

    public String getSEP_SPG_ID_VOZ() {
        return SEP_SPG_ID_VOZ;
    }

    public String getSEP_GAP_MONTHS_QTY() {
        return SEP_GAP_MONTHS_QTY;
    }

    public String getSEP_LENGTH_DAYS() {
        return SEP_LENGTH_DAYS;
    }

    @Override
    public String toString() {
        return "ServicePackParameters{" +
                "SEP_PKT_ID='" + SEP_PKT_ID + '\'' +
                ", SEP_UNITS_IN='" + SEP_UNITS_IN + '\'' +
                ", SEP_RPL_ID_IN='" + SEP_RPL_ID_IN + '\'' +
                ", SEP_RPL_ID_OUT='" + SEP_RPL_ID_OUT + '\'' +
                ", SEP_START_DATE='" + SEP_START_DATE + '\'' +
                ", SEP_END_DATE='" + SEP_END_DATE + '\'' +
                ", SEP_GSPR_ID='" + SEP_GSPR_ID + '\'' +
                ", SEP_TYPE_TECNOMEN='" + SEP_TYPE_TECNOMEN + '\'' +
                ", SEP_PKT_TECNOMEN='" + SEP_PKT_TECNOMEN + '\'' +
                ", SEP_RENEWAL_PKT='" + SEP_RENEWAL_PKT + '\'' +
                ", SEP_UNITS_COMBINED='" + SEP_UNITS_COMBINED + '\'' +
                ", SEP_TYPE_TECNOMEN_COMBINED='" + SEP_TYPE_TECNOMEN_COMBINED + '\'' +
                ", SEP_RENEWAL_PKT_COMBINED='" + SEP_RENEWAL_PKT_COMBINED + '\'' +
                ", SEP_PKT_TECNOMEN_COMBINED='" + SEP_PKT_TECNOMEN_COMBINED + '\'' +
                ", SEP_VOP_DESTINATION='" + SEP_VOP_DESTINATION + '\'' +
                ", SEP_VOP_DESTINATION_TYPE='" + SEP_VOP_DESTINATION_TYPE + '\'' +
                ", SEP_TUC_UNIT_ID='" + SEP_TUC_UNIT_ID + '\'' +
                ", SEP_UNITS_REPURCHASE='" + SEP_UNITS_REPURCHASE + '\'' +
                ", SEP_SPG_ID='" + SEP_SPG_ID + '\'' +
                ", SEP_USE_STEALTH='" + SEP_USE_STEALTH + '\'' +
                ", SEP_FLAG_HABILITA_NOTIF='" + SEP_FLAG_HABILITA_NOTIF + '\'' +
                ", SEP_FLAG_SALDO_PACK='" + SEP_FLAG_SALDO_PACK + '\'' +
                ", SEP_NPG_ID='" + SEP_NPG_ID + '\'' +
                ", SEP_SPG_ID_VOZ='" + SEP_SPG_ID_VOZ + '\'' +
                ", SEP_GAP_MONTHS_QTY='" + SEP_GAP_MONTHS_QTY + '\'' +
                ", SEP_LENGTH_DAYS='" + SEP_LENGTH_DAYS + '\'' +
                '}';
    }
}
