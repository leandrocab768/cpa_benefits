package OffertManager;


import com.google.gson.Gson;

import static com.claro.sp.ta.db.DatabaseQuery.databaseQuery;

public class Packages {
    private String user, password, environment, country;

    public Packages(String user, String password, String environment, String country) {
        this.user = user;
        this.password = password;
        this.environment = environment;
        this.country = country;

    }

    /**
     * Obtener Packages vigente
     *
     * @param PKT_ID el identificador del package
     * @return Objeto de tipo Packages
     */
    public Packages getPackagesVigente(String PKT_ID) {
        String sql = "SELECT * \n" +
                "    FROM PACKAGES \n" +
                "   WHERE PKT_ID = ? ";
        Packages packages = new Packages();
        try {
            String result = databaseQuery()
                    .user(this.user)
                    .password(this.password)
                    .environment(this.environment)
                    .country(this.country)
                    .prod()
                    .select(sql)
                    //.log("\n\t[INFO] Obtener registro de la tabla Packages")
                    .addParameter(PKT_ID)
                    //.addParameter(pd o pf)
                    .execute();

            String cadena = result.substring(1, result.length() - 1);
            Gson gson = new Gson();
            packages = (Packages) gson.fromJson(cadena, Packages.class);


        } catch (Exception e) {
            System.out.println("[FALLO] Metodo getPackagesVigente clase Packages ");
        }
        return packages;
    }

    /**
     * Obtener Packages vigente
     *
     * @param PKT_ID   el identificador del package
     * @param PKT_TYPE tipo pack (PD,PF,PW)
     * @return Objeto de tipo Packages
     */
    public Packages getPackagesVigente(String PKT_ID, String PKT_TYPE) {
        String sql = "SELECT * \n" +
                "    FROM PACKAGES \n" +
                "   WHERE PKT_ID = ? \n" +
                " and PKT_TYPE = ? ";
        Packages packages = new Packages();
        try {
            String result = databaseQuery()
                    .user(this.user)
                    .password(this.password)
                    .environment(this.environment)
                    .country(this.country)
                    .prod()
                    .select(sql)
                    //.log("\n\t[INFO] Obtener registro de la tabla Packages")
                    .addParameter(PKT_ID)
                    .addParameter(PKT_TYPE)
                    .execute();

            String cadena = result.substring(1, result.length() - 1);
            Gson gson = new Gson();
            packages = (Packages) gson.fromJson(cadena, Packages.class);


        } catch (Exception e) {
            System.out.println("[FALLO] Metodo getPackagesVigente clase Packages ");
        }
        return packages;
    }

    public boolean actFchVigencia(String PKT_ID, String PKT_START_DATE, String PKT_END_DATE) {
        String startDate = "";
        String endDate = "";
        //Utilizaremos el flag para validar si se envia alguna fecha.
        boolean flgEjecucion = false;
        if (PKT_START_DATE.compareTo("") != 0 && PKT_START_DATE.compareTo("-") != 0) {
            startDate = "PS.PKT_START_DATE = to_date('" + PKT_START_DATE + "', 'dd-mm-yyyy hh24:mi:ss') ";
            flgEjecucion = true;
        }
        if (PKT_END_DATE.compareTo("") != 0 && PKT_END_DATE.compareTo("-") != 0) {
            endDate = "PS.PKT_END_DATE = to_date('" + PKT_END_DATE + "', 'dd-mm-yyyy hh24:mi:ss') ";
            flgEjecucion = true;
        }
        //Validamos el Flag
        if (flgEjecucion && PKT_ID.compareTo("") != 0 && PKT_ID.compareTo("-") != 0) {
            //Armamos la sentencia UPDATE según los campos enviados
            String sql = "UPDATE PACKAGES PS SET \n";
            if (startDate.compareTo("") != 0) {
                sql += startDate;
            }
            if (endDate.compareTo("") != 0 && startDate.compareTo("") != 0) {
                sql += " , " + endDate;
            } else {
                sql += endDate;
            }
            sql += "\n" + "where PS.PKT_ID = ?";
            //System.out.println(sql);
            String result = "";
            try {
                result = databaseQuery()
                        .user(this.user)
                        .password(this.password)
                        .environment(this.environment)
                        .country(this.country)
                        .prod()
                        .update(sql)
                        .addParameter(PKT_ID)
                        .execute();

            } catch (Exception e) {
                System.out.println("[FALLO] Metodo actFchVigencia clase Packages ");
                return false;
            }
            System.out.println("[INFO] Se realiza el UPDATE del metodo actFchVigencia clase Packages ");
            return true;
        }
        System.out.println("[INFO] No se realiza el UPDATE del metodo actFchVigencia clase Packages  ");
        return false;
    }

    public boolean insertPackages(String sqlInsert) {

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
            System.out.println("[FALLO] Metodo insertPackages clase Packages");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Packages pg = new Packages("ghct", "auto_333", "TEST", "AR");
        Packages p = pg.insertPackages("P6GB3","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","");//No hace nada
        System.out.println("P: "+p.toString());
    }

    public Packages insertPackages(String PKT_ID, String PKT_DESCRIPTION, String PKT_CURRENT_PRICE, String PKT_ADVANCE, String PKT_INC_ID, String PKT_CANCELED, String PKT_FRT_ID, String PKT_START_DATE, String PKT_END_DATE, String PKT_PRORRATION, String PKT_SYR_ID, String PKT_TYPE, String PKT_LENGTH, String PKT_DIGITAL_FLAG, String PKT_SMS_INFOR_REQ_FLAG, String PKT_AUDIOTEL_FLAG, String PKT_INC_ID_LD, String PKT_VALIDATION_FLAG, String PKT_CHECKED_BILLING_FLAG, String PKT_PREPAY_FLAG, String PKT_CLASSING, String PKT_SOURCE, String PKT_CHANGING_TYPE, String PKT_MIP_ID_PERMANENCY, String PKT_MIP_ID_NEW, String PKT_SECOND_ENT_FLAG, String PKT_SECOND_ENT_ID, String PKT_PFA_ID, String PKT_CATEGORY, String PKT_ISG_ID, String PKT_FTR_ISI, String PKT_LENGTH_DAYS, String PKT_PARAMETRO, String PKT_ENABLED_CLEARING, String PKT_CAN_CICLO, String PKT_BAJAR_FLAG, String PKT_WEB_DESCRIPTION, String PKT_VISIBILITY, String PKT_HELP, String PKT_WORK_AS_PREPAY, String PKT_CHARGE_ID, String PKT_EVENTUAL_CHARGE, String PKT_CYCLIC, String PKT_QUANTITY) {
        Packages packages = getPackagesVigente(PKT_ID);
        if (packages != null) {
            return packages;
        }

        String insert = "insert into packages (PKT_ID, PKT_DESCRIPTION, PKT_CURRENT_PRICE, PKT_ADVANCE, PKT_INC_ID, PKT_CANCELED, PKT_FRT_ID, PKT_START_DATE, PKT_END_DATE, PKT_PRORRATION, PKT_SYR_ID, PKT_TYPE, PKT_LENGTH, PKT_DIGITAL_FLAG, PKT_SMS_INFOR_REQ_FLAG, PKT_AUDIOTEL_FLAG, PKT_INC_ID_LD, PKT_VALIDATION_FLAG, PKT_CHECKED_BILLING_FLAG, PKT_PREPAY_FLAG, PKT_CLASSING, PKT_SOURCE, PKT_CHANGING_TYPE, PKT_MIP_ID_PERMANENCY, PKT_MIP_ID_NEW, PKT_SECOND_ENT_FLAG, PKT_SECOND_ENT_ID, PKT_PFA_ID, PKT_CATEGORY, PKT_ISG_ID, PKT_FTR_ISI, PKT_LENGTH_DAYS, PKT_PARAMETRO, PKT_ENABLED_CLEARING, PKT_CAN_CICLO, PKT_BAJAR_FLAG, PKT_WEB_DESCRIPTION, PKT_VISIBILITY, PKT_HELP, PKT_WORK_AS_PREPAY, PKT_CHARGE_ID, PKT_EVENTUAL_CHARGE, PKT_CYCLIC, PKT_QUANTITY)\n" +
                "values (" + PKT_ID + "," + PKT_DESCRIPTION + "," + PKT_CURRENT_PRICE + "," + PKT_ADVANCE + "," + PKT_INC_ID + "," + PKT_CANCELED + "," + PKT_FRT_ID + "," + "to_date(" + PKT_START_DATE + ", 'dd-mm-yyyy'), to_date(" + PKT_END_DATE + ", 'dd-mm-yyyy'), " + PKT_PRORRATION + "," + PKT_SYR_ID + "," + PKT_TYPE + "," + PKT_LENGTH + "," + PKT_DIGITAL_FLAG + "," + PKT_SMS_INFOR_REQ_FLAG + "," + PKT_AUDIOTEL_FLAG + "," + PKT_INC_ID_LD + "," + PKT_VALIDATION_FLAG + "," + PKT_CHECKED_BILLING_FLAG + "," + PKT_PREPAY_FLAG + "," + PKT_CLASSING + "," + PKT_SOURCE + "," + PKT_CHANGING_TYPE + "," + PKT_MIP_ID_PERMANENCY + "," + PKT_MIP_ID_NEW + "," + PKT_SECOND_ENT_FLAG + "," + PKT_SECOND_ENT_ID + "," + PKT_PFA_ID + "," + PKT_CATEGORY + "," + PKT_ISG_ID + "," + PKT_FTR_ISI + "," + PKT_LENGTH_DAYS + "," + PKT_PARAMETRO + "," + PKT_ENABLED_CLEARING + "," + PKT_CAN_CICLO + "," + PKT_BAJAR_FLAG + "," + PKT_WEB_DESCRIPTION + "," + PKT_VISIBILITY + "," + PKT_HELP + "," + PKT_WORK_AS_PREPAY + "," + PKT_CHARGE_ID + "," + PKT_EVENTUAL_CHARGE + "," + PKT_CYCLIC + "," + PKT_QUANTITY + ")";
        try {
            String result = databaseQuery()
                    .user(this.user)
                    .password(this.password)
                    .environment(this.environment)
                    .country(this.country)
                    .prod()
                    .insert(insert)
                    //.log("\n\t[INFO]Se elimina registro historico en la tabla prepay_interfaces")
                    //.addParameter(nim)
                    .execute();
        } catch (Exception e) {
            System.out.println("[FALLO] Metodo insertPackages clase Packages ");
            return null;
        }
        packages = getPackagesVigente(PKT_ID);
        return packages;
    }

    ///////////////
    private String PKT_ID;
    private String PKT_DESCRIPTION;
    private String PKT_CURRENT_PRICE;
    private String PKT_ADVANCE;
    private String PKT_INC_ID;
    private String PKT_CANCELED;
    private String PKT_FRT_ID;
    private String PKT_START_DATE;
    private String PKT_END_DATE;
    private String PKT_PRORRATION;
    private String PKT_SYR_ID;
    private String PKT_TYPE;
    private String PKT_LENGTH;
    private String PKT_DIGITAL_FLAG;
    private String PKT_SMS_INFOR_REQ_FLAG;
    private String PKT_AUDIOTEL_FLAG;
    private String PKT_INC_ID_LD;
    private String PKT_VALIDATION_FLAG;
    private String PKT_CHECKED_BILLING_FLAG;
    private String PKT_PREPAY_FLAG;
    private String PKT_CLASSING;
    private String PKT_SOURCE;
    private String PKT_CHANGING_TYPE;
    private String PKT_MIP_ID_PERMANENCY;
    private String PKT_MIP_ID_NEW;
    private String PKT_SECOND_ENT_FLAG;
    private String PKT_SECOND_ENT_ID;
    private String PKT_PFA_ID;
    private String PKT_CATEGORY;
    private String PKT_ISG_ID;
    private String PKT_FTR_ISI;
    private String PKT_LENGTH_DAYS;
    private String PKT_PARAMETRO;
    private String PKT_ENABLED_CLEARING;
    private String PKT_CAN_CICLO;
    private String PKT_BAJAR_FLAG;
    private String PKT_WEB_DESCRIPTION;
    private String PKT_VISIBILITY;
    private String PKT_HELP;
    private String PKT_WORK_AS_PREPAY;
    private String PKT_CHARGE_ID;
    private String PKT_EVENTUAL_CHARGE;
    private String PKT_CYCLIC;
    private String PKT_QUANTITY;


    public Packages() {

    }

    @Override
    public String toString() {
        return "Packages{" +
                "PKT_ID='" + PKT_ID + '\'' +
                ", PKT_DESCRIPTION='" + PKT_DESCRIPTION + '\'' +
                ", PKT_CURRENT_PRICE='" + PKT_CURRENT_PRICE + '\'' +
                ", PKT_ADVANCE='" + PKT_ADVANCE + '\'' +
                ", PKT_INC_ID='" + PKT_INC_ID + '\'' +
                ", PKT_CANCELED='" + PKT_CANCELED + '\'' +
                ", PKT_FRT_ID='" + PKT_FRT_ID + '\'' +
                ", PKT_START_DATE='" + PKT_START_DATE + '\'' +
                ", PKT_END_DATE='" + PKT_END_DATE + '\'' +
                ", PKT_PRORRATION='" + PKT_PRORRATION + '\'' +
                ", PKT_SYR_ID='" + PKT_SYR_ID + '\'' +
                ", PKT_TYPE='" + PKT_TYPE + '\'' +
                ", PKT_LENGTH='" + PKT_LENGTH + '\'' +
                ", PKT_DIGITAL_FLAG='" + PKT_DIGITAL_FLAG + '\'' +
                ", PKT_SMS_INFOR_REQ_FLAG='" + PKT_SMS_INFOR_REQ_FLAG + '\'' +
                ", PKT_AUDIOTEL_FLAG='" + PKT_AUDIOTEL_FLAG + '\'' +
                ", PKT_INC_ID_LD='" + PKT_INC_ID_LD + '\'' +
                ", PKT_VALIDATION_FLAG='" + PKT_VALIDATION_FLAG + '\'' +
                ", PKT_CHECKED_BILLING_FLAG='" + PKT_CHECKED_BILLING_FLAG + '\'' +
                ", PKT_PREPAY_FLAG='" + PKT_PREPAY_FLAG + '\'' +
                ", PKT_CLASSING='" + PKT_CLASSING + '\'' +
                ", PKT_SOURCE='" + PKT_SOURCE + '\'' +
                ", PKT_CHANGING_TYPE='" + PKT_CHANGING_TYPE + '\'' +
                ", PKT_MIP_ID_PERMANENCY='" + PKT_MIP_ID_PERMANENCY + '\'' +
                ", PKT_MIP_ID_NEW='" + PKT_MIP_ID_NEW + '\'' +
                ", PKT_SECOND_ENT_FLAG='" + PKT_SECOND_ENT_FLAG + '\'' +
                ", PKT_SECOND_ENT_ID='" + PKT_SECOND_ENT_ID + '\'' +
                ", PKT_PFA_ID='" + PKT_PFA_ID + '\'' +
                ", PKT_CATEGORY='" + PKT_CATEGORY + '\'' +
                ", PKT_ISG_ID='" + PKT_ISG_ID + '\'' +
                ", PKT_FTR_ISI='" + PKT_FTR_ISI + '\'' +
                ", PKT_LENGTH_DAYS='" + PKT_LENGTH_DAYS + '\'' +
                ", PKT_PARAMETRO='" + PKT_PARAMETRO + '\'' +
                ", PKT_ENABLED_CLEARING='" + PKT_ENABLED_CLEARING + '\'' +
                ", PKT_CAN_CICLO='" + PKT_CAN_CICLO + '\'' +
                ", PKT_BAJAR_FLAG='" + PKT_BAJAR_FLAG + '\'' +
                ", PKT_WEB_DESCRIPTION='" + PKT_WEB_DESCRIPTION + '\'' +
                ", PKT_VISIBILITY='" + PKT_VISIBILITY + '\'' +
                ", PKT_HELP='" + PKT_HELP + '\'' +
                ", PKT_WORK_AS_PREPAY='" + PKT_WORK_AS_PREPAY + '\'' +
                ", PKT_CHARGE_ID='" + PKT_CHARGE_ID + '\'' +
                ", PKT_EVENTUAL_CHARGE='" + PKT_EVENTUAL_CHARGE + '\'' +
                ", PKT_CYCLIC='" + PKT_CYCLIC + '\'' +
                ", PKT_QUANTITY='" + PKT_QUANTITY + '\'' +
                '}';
    }

    public String getPKT_ID() {
        return PKT_ID;
    }

    public String getPKT_DESCRIPTION() {
        return PKT_DESCRIPTION;
    }

    public String getPKT_CURRENT_PRICE() {
        return PKT_CURRENT_PRICE;
    }

    public String getPKT_ADVANCE() {
        return PKT_ADVANCE;
    }

    public String getPKT_INC_ID() {
        return PKT_INC_ID;
    }

    public String getPKT_CANCELED() {
        return PKT_CANCELED;
    }

    public String getPKT_FRT_ID() {
        return PKT_FRT_ID;
    }

    public String getPKT_START_DATE() {
        return PKT_START_DATE;
    }

    public String getPKT_END_DATE() {
        return PKT_END_DATE;
    }

    public String getPKT_PRORRATION() {
        return PKT_PRORRATION;
    }

    public String getPKT_SYR_ID() {
        return PKT_SYR_ID;
    }

    public String getPKT_TYPE() {
        return PKT_TYPE;
    }

    public String getPKT_LENGTH() {
        return PKT_LENGTH;
    }

    public String getPKT_DIGITAL_FLAG() {
        return PKT_DIGITAL_FLAG;
    }

    public String getPKT_SMS_INFOR_REQ_FLAG() {
        return PKT_SMS_INFOR_REQ_FLAG;
    }

    public String getPKT_AUDIOTEL_FLAG() {
        return PKT_AUDIOTEL_FLAG;
    }

    public String getPKT_INC_ID_LD() {
        return PKT_INC_ID_LD;
    }

    public String getPKT_VALIDATION_FLAG() {
        return PKT_VALIDATION_FLAG;
    }

    public String getPKT_CHECKED_BILLING_FLAG() {
        return PKT_CHECKED_BILLING_FLAG;
    }

    public String getPKT_PREPAY_FLAG() {
        return PKT_PREPAY_FLAG;
    }

    public String getPKT_CLASSING() {
        return PKT_CLASSING;
    }

    public String getPKT_SOURCE() {
        return PKT_SOURCE;
    }

    public String getPKT_CHANGING_TYPE() {
        return PKT_CHANGING_TYPE;
    }

    public String getPKT_MIP_ID_PERMANENCY() {
        return PKT_MIP_ID_PERMANENCY;
    }

    public String getPKT_MIP_ID_NEW() {
        return PKT_MIP_ID_NEW;
    }

    public String getPKT_SECOND_ENT_FLAG() {
        return PKT_SECOND_ENT_FLAG;
    }

    public String getPKT_SECOND_ENT_ID() {
        return PKT_SECOND_ENT_ID;
    }

    public String getPKT_PFA_ID() {
        return PKT_PFA_ID;
    }

    public String getPKT_CATEGORY() {
        return PKT_CATEGORY;
    }

    public String getPKT_ISG_ID() {
        return PKT_ISG_ID;
    }

    public String getPKT_FTR_ISI() {
        return PKT_FTR_ISI;
    }

    public String getPKT_LENGTH_DAYS() {
        return PKT_LENGTH_DAYS;
    }

    public String getPKT_PARAMETRO() {
        return PKT_PARAMETRO;
    }

    public String getPKT_ENABLED_CLEARING() {
        return PKT_ENABLED_CLEARING;
    }

    public String getPKT_CAN_CICLO() {
        return PKT_CAN_CICLO;
    }

    public String getPKT_BAJAR_FLAG() {
        return PKT_BAJAR_FLAG;
    }

    public String getPKT_WEB_DESCRIPTION() {
        return PKT_WEB_DESCRIPTION;
    }

    public String getPKT_VISIBILITY() {
        return PKT_VISIBILITY;
    }

    public String getPKT_HELP() {
        return PKT_HELP;
    }

    public String getPKT_WORK_AS_PREPAY() {
        return PKT_WORK_AS_PREPAY;
    }

    public String getPKT_CHARGE_ID() {
        return PKT_CHARGE_ID;
    }

    public String getPKT_EVENTUAL_CHARGE() {
        return PKT_EVENTUAL_CHARGE;
    }

    public String getPKT_CYCLIC() {
        return PKT_CYCLIC;
    }

    public String getPKT_QUANTITY() {
        return PKT_QUANTITY;
    }
}
