package OffertManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.claro.sp.ta.db.DatabaseQuery.databaseQuery;

public class AdditionalBenefits {
    private String user, password, environment, country;

    public AdditionalBenefits(String user, String password, String environment, String country) {
        this.user = user;
        this.password = password;
        this.environment = environment;
        this.country = country;
    }

    public AdditionalBenefits() {
    }

    public AdditionalBenefits getAdditionalBenefitsVigente(String amount, String reason, String business) {
        String sql = "select *\n" +
                "from additional_benefits ab\n" +
                "where ab.adbe_amount = ?\n" +
                "and ab.adbe_channel = ?\n" +
                "and ab.adbe_business = ?\n" +
                "and sysdate between ab.adbe_start_date and nvl(ab.adbe_end_date,sysdate+1) ";
        AdditionalBenefits additionalBenefits = new AdditionalBenefits();
        try {
            String result = databaseQuery()
                    .user(this.user)
                    .password(this.password)
                    .environment(this.environment)
                    .country(this.country)
                    .prod()
                    .select(sql)
                    //.log("\n\t[INFO] Obtener registro de la tabla Packages")
                    .addParameter(amount)
                    .addParameter(reason)
                    .addParameter(business)
                    .execute();

            String cadena = result.substring(1, result.length() - 1);
            Gson gson = new Gson();
            additionalBenefits = (AdditionalBenefits) gson.fromJson(cadena, AdditionalBenefits.class);


        } catch (Exception e) {
            System.out.println("[FALLO] Metodo getAdditionalBenefitsVigente clase AdditionalBenefits ");
        }
        return additionalBenefits;
    }

    //500 o 500,600,700 -- '500','600','700'
    public List<AdditionalBenefits> listAdditionalBenefits(String amount, String reason, String business) {
        List<AdditionalBenefits> listAdditionalBenefits = null;//

        String sql = "select *\n" +
                "from additional_benefits ab\n" +
                "where ab.adbe_channel = ? \n" +
                "and sysdate between ab.adbe_start_date and nvl(ab.adbe_end_date,sysdate)";
        if (amount.compareTo("-") != 0) {
            String auxAmount = formatoLista(amount);
            sql += "and ab.adbe_amount in (" + auxAmount + ") ";
        }
        if (business.compareTo("-") != 0) {
            String auxbusiness = formatoLista(business);
            sql += "and ab.adbe_business in (" + auxbusiness + ")";
        }
        //System.out.println("SQL: " + sql);
        try {

            String result = databaseQuery()
                    .user(this.user)
                    .password(this.password)
                    .environment(this.environment)
                    .country(this.country)
                    .prod()
                    .select(sql)
                    .addParameter(reason)
                    .execute();

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<AdditionalBenefits>>() {
            }.getType();
            listAdditionalBenefits = (List<AdditionalBenefits>) gson.fromJson(result, listType);

        } catch (Exception e) {
            System.out.println("[FALLO] Metodo getComboBenefitsVigentes clase ComboBenefits ");
            e.printStackTrace();
        }
        return listAdditionalBenefits;
    }

    private static String formatoLista(String amount) {
        String[] vectorAmount = amount.split(",");
        String retorno = "";
        for (String auxAmount : vectorAmount) {
            if (retorno.equals("")) {
                retorno += "'" + auxAmount + "'";
            } else {
                retorno += ",'" + auxAmount + "'";
            }

        }
        return retorno;
    }

    public boolean actFchVigencia(String ADBE_AMOUNT, String ADBE_CHANNEL, String ADBE_START_DATE, String ADBE_END_DATE) {
        String startDate = "";
        String endDate = "";
        //Utilizaremos el flag para validar si se envia alguna fecha.
        boolean flgEjecucion = false;
        if (ADBE_START_DATE.compareTo("") != 0 && ADBE_START_DATE.compareTo("-") != 0) {
            startDate = "PS.ADBE_START_DATE = to_date('" + ADBE_START_DATE + "', 'dd-mm-yyyy hh24:mi:ss') ";
            flgEjecucion = true;
        }
        if (ADBE_END_DATE.compareTo("") != 0 && ADBE_END_DATE.compareTo("-") != 0) {
            endDate = "PS.ADBE_END_DATE = to_date('" + ADBE_END_DATE + "', 'dd-mm-yyyy hh24:mi:ss') ";
            flgEjecucion = true;
        }
        //Validamos el Flag y los parametros de busqueda.
        if (flgEjecucion &&
                ADBE_AMOUNT.compareTo("") != 0 &&
                ADBE_AMOUNT.compareTo("-") != 0 &&
                ADBE_CHANNEL.compareTo("") != 0 &&
                ADBE_CHANNEL.compareTo("-") != 0) {
            //Armamos la sentencia UPDATE según los campos enviados
            String sql = "UPDATE ADDITIONAL_BENEFITS PS SET \n";
            if (startDate.compareTo("") != 0) {
                sql += startDate;
            }
            if (endDate.compareTo("") != 0 && startDate.compareTo("") != 0) {
                sql += " , " + endDate;
            } else {
                sql += endDate;
            }
            sql += "\n" + "where PS.ADBE_AMOUNT = ? and PS.ADBE_CHANNEL = ? ";
            System.out.println(sql);
            String result = "";
            try {
                result = databaseQuery()
                        .user(this.user)
                        .password(this.password)
                        .environment(this.environment)
                        .country(this.country)
                        .prod()
                        .update(sql)
                        .addParameter(ADBE_AMOUNT)
                        .addParameter(ADBE_CHANNEL)
                        .execute();

            } catch (Exception e) {
                System.out.println("[FALLO] Metodo actFchVigencia clase AdditionalBenefits ");
                return false;
            }
            System.out.println("[INFO] Se realiza el UPDATE del metodo actFchVigencia clase AdditionalBenefits ");
            return true;
        }
        System.out.println("[INFO] No se realiza el UPDATE del metodo actFchVigencia clase AdditionalBenefits  ");
        return false;
    }


    public boolean insertAdditionalBenefits(Float ADBE_AMOUNT, String ADBE_CHANNEL, String ADBE_BUSINESS, String ADBE_PKT_ID, String ADBE_START_DATE, String ADBE_END_DATE, String ADBE_DESCRIPTION, String ADBE_BLACKLIST, String ADBE_COBE_ID) {
        String insert = "insert into additional_benefits (ADBE_AMOUNT, ADBE_CHANNEL, ADBE_BUSINESS, ADBE_PKT_ID, ADBE_START_DATE, ADBE_END_DATE, ADBE_DESCRIPTION, ADBE_BLACKLIST, ADBE_COBE_ID)\n" +
                "values (" + ADBE_AMOUNT + "," + ADBE_CHANNEL + "," + ADBE_BUSINESS + "," + ADBE_PKT_ID + ", to_date(" + ADBE_START_DATE + ", 'dd-mm-yyyy hh24:mi:ss'), to_date(" + ADBE_END_DATE + ", 'dd-mm-yyyy')," + ADBE_DESCRIPTION + "," + ADBE_BLACKLIST + "," + ADBE_COBE_ID + ")";
        try {
            String result = databaseQuery()
                    .user(this.user)
                    .password(this.password)
                    .environment(this.environment)
                    .country(this.country)
                    .prod()
                    .insert(insert)
                    .execute();
        } catch (Exception e) {
            System.out.println("[FALLO] Metodo insertAdditionalBenefits clase AdditionalBenefits  ");
            return false;
        }
        return true;
    }

    public boolean insertAdditionalBenefits(String sqlInsert) {
           try {
            String result = databaseQuery()
                    .user(this.user)
                    .password(this.password)
                    .environment(this.environment)
                    .country(this.country)
                    .prod()
                    .insert(sqlInsert)
                    .execute();
        } catch (Exception e) {
            System.out.println("[FALLO] Metodo insertAdditionalBenefits clase AdditionalBenefits  ");
            return false;
        }
        return true;
    }

    public boolean deletAdditionalBenefits(String ADBE_AMOUNT,String ADBE_CHANNEL,String ADBE_BUSINESS){
        String delete = "delete Additional_Benefits ab\n" +
                "where ab.adbe_amount = ? \n" +
                "and ab.adbe_channel = ? \n" +
                "and ab.adbe_business = ?";
        try {
            String result = databaseQuery()
                    .user(this.user)
                    .password(this.password)
                    .environment(this.environment)
                    .country(this.country)
                    .prod()
                    .delete(delete)
                    .addParameter(ADBE_AMOUNT)
                    .addParameter(ADBE_CHANNEL)
                    .addParameter(ADBE_BUSINESS)
                    .execute();
        } catch (Exception e) {
            System.out.println("[FALLO] Metodo deletAdditionalBenefits clase AdditionalBenefits  ");
            return false;
        }
        return true;
    }
    //    public static void main(String[] args) {
//        List<AdditionalBenefits> listAdditionalBenefits = new AdditionalBenefits("ghct","auto_333","TEST","AR").listAdditionalBenefits("-", "REVPDC", "PP");
//        for (AdditionalBenefits a : listAdditionalBenefits){
//            System.out.println(a.toString());
//        }
//
//
//    }

    ////////////////////////
    private Float ADBE_AMOUNT;
    private String ADBE_CHANNEL;
    private String ADBE_BUSINESS;
    private String ADBE_PKT_ID;
    private String ADBE_START_DATE;
    private String ADBE_END_DATE;
    private String ADBE_DESCRIPTION;
    private String ADBE_BLACKLIST;
    private String ADBE_COBE_ID;

    public Float getADBE_AMOUNT() {
        return ADBE_AMOUNT;
    }

    public void setADBE_AMOUNT(Float ADBE_AMOUNT) {
        this.ADBE_AMOUNT = ADBE_AMOUNT;
    }

    public String getADBE_CHANNEL() {
        return ADBE_CHANNEL;
    }

    public void setADBE_CHANNEL(String ADBE_CHANNEL) {
        this.ADBE_CHANNEL = ADBE_CHANNEL;
    }

    public String getADBE_BUSINESS() {
        return ADBE_BUSINESS;
    }

    public void setADBE_BUSINESS(String ADBE_BUSINESS) {
        this.ADBE_BUSINESS = ADBE_BUSINESS;
    }

    public String getADBE_PKT_ID() {
        return ADBE_PKT_ID;
    }

    public void setADBE_PKT_ID(String ADBE_PKT_ID) {
        this.ADBE_PKT_ID = ADBE_PKT_ID;
    }

    public String getADBE_START_DATE() {
        return ADBE_START_DATE;
    }

    public void setADBE_START_DATE(String ADBE_START_DATE) {
        this.ADBE_START_DATE = ADBE_START_DATE;
    }

    public String getADBE_END_DATE() {
        return ADBE_END_DATE;
    }

    public void setADBE_END_DATE(String ADBE_END_DATE) {
        this.ADBE_END_DATE = ADBE_END_DATE;
    }

    public String getADBE_DESCRIPTION() {
        return ADBE_DESCRIPTION;
    }

    public void setADBE_DESCRIPTION(String ADBE_DESCRIPTION) {
        this.ADBE_DESCRIPTION = ADBE_DESCRIPTION;
    }

    public String getADBE_BLACKLIST() {
        return ADBE_BLACKLIST;
    }

    public void setADBE_BLACKLIST(String ADBE_BLACKLIST) {
        this.ADBE_BLACKLIST = ADBE_BLACKLIST;
    }

    public String getADBE_COBE_ID() {
        return ADBE_COBE_ID;
    }

    public void setADBE_COBE_ID(String ADBE_COBE_ID) {
        this.ADBE_COBE_ID = ADBE_COBE_ID;
    }

    @Override
    public String toString() {
        return "AdditionalBenefits{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", environment='" + environment + '\'' +
                ", country='" + country + '\'' +
                ", ADBE_AMOUNT='" + ADBE_AMOUNT + '\'' +
                ", ADBE_CHANNEL='" + ADBE_CHANNEL + '\'' +
                ", ADBE_BUSINESS='" + ADBE_BUSINESS + '\'' +
                ", ADBE_PKT_ID='" + ADBE_PKT_ID + '\'' +
                ", ADBE_START_DATE='" + ADBE_START_DATE + '\'' +
                ", ADBE_END_DATE='" + ADBE_END_DATE + '\'' +
                ", ADBE_DESCRIPTION='" + ADBE_DESCRIPTION + '\'' +
                ", ADBE_BLACKLIST='" + ADBE_BLACKLIST + '\'' +
                ", ADBE_COBE_ID='" + ADBE_COBE_ID + '\'' +
                '}';
    }
}
