package OffertManager;


import com.google.gson.Gson;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.claro.sp.ta.db.DatabaseQuery.databaseQuery;

public class ComboBenefits {
    private String COBE_ID;
    private String COBE_PKT_ID;
    private String COBE_START_DATE;
    private String COBE_END_DATE;

    private String user, password, environment, country;

    public ComboBenefits(String user, String password, String environment, String country) {
        this.user = user;
        this.password = password;
        this.environment = environment;
        this.country = country;
    }

    public List<ComboBenefits> getComboBenefitsVigentes(String COBE_ID) {
        List<ComboBenefits> listComboBenefits = null;// new ArrayList<ComboBenefits>();
        String sql = "select *\n" +
                "from combo_benefits cb\n" +
                "where cb.cobe_id = ?\n" +
                "and sysdate between cb.cobe_start_date and nvl(cb.cobe_end_date,sysdate+1) ";
        try {

            String result = databaseQuery()
                    .user(this.user)
                    .password(this.password)
                    .environment(this.environment)
                    .country(this.country)
                    .prod()
                    .select(sql)
                    .addParameter(COBE_ID)
                    .execute();

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<ComboBenefits>>() {
            }.getType();
            listComboBenefits = (List<ComboBenefits>) gson.fromJson(result, listType);

        } catch (Exception e) {
            System.out.println("[FALLO] Metodo getComboBenefitsVigentes clase ComboBenefits ");
            e.printStackTrace();
        }
        return listComboBenefits;
    }

    public boolean actFchVigencia(String COBE_ID, String COBE_START_DATE, String COBE_END_DATE) {
        String startDate = "";
        String endDate = "";
        //Utilizaremos el flag para validar si se envia alguna fecha.
        boolean flgEjecucion = false;
        if (COBE_START_DATE.compareTo("") != 0 && COBE_START_DATE.compareTo("-") != 0) {
            startDate = "PS.COBE_START_DATE = to_date('" + COBE_START_DATE + "', 'dd-mm-yyyy hh24:mi:ss') ";
            flgEjecucion = true;
        }
        if (COBE_END_DATE.compareTo("") != 0 && COBE_END_DATE.compareTo("-") != 0) {
            endDate = "PS.COBE_END_DATE = to_date('" + COBE_END_DATE + "', 'dd-mm-yyyy hh24:mi:ss') ";
            flgEjecucion = true;
        }
        //Validamos el Flag
        if (flgEjecucion && COBE_ID.compareTo("") != 0 && COBE_ID.compareTo("-") != 0) {
            //Armamos la sentencia UPDATE según los campos enviados
            String sql = "UPDATE COMBO_BENEFITS PS SET \n";
            if (startDate.compareTo("") != 0) {
                sql += startDate;
            }
            if (endDate.compareTo("") != 0 && startDate.compareTo("") != 0) {
                sql += " , " + endDate;
            } else {
                sql += endDate;
            }
            sql += "\n" + "where PS.COBE_ID = ?";
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
                        .addParameter(COBE_ID)
                        .execute();

            } catch (Exception e) {
                System.out.println("[FALLO] Metodo actFchVigencia clase ComboBenefits ");
                return false;
            }
            System.out.println("[INFO] Se realiza el UPDATE del metodo actFchVigencia clase ComboBenefits ");
            return true;
        }
        System.out.println("[INFO] No se realiza el UPDATE del metodo actFchVigencia clase ComboBenefits  ");
        return false;
    }

    public boolean actFchVigencia(String COBE_ID, String COBE_PKT_ID, String COBE_START_DATE, String COBE_END_DATE) {
        String startDate = "";
        String endDate = "";
        //Utilizaremos el flag para validar si se envia alguna fecha.
        boolean flgEjecucion = false;
        if (COBE_START_DATE.compareTo("") != 0 && COBE_START_DATE.compareTo("-") != 0) {
            startDate = "PS.COBE_START_DATE = to_date('" + COBE_START_DATE + "', 'dd-mm-yyyy hh24:mi:ss') ";
            flgEjecucion = true;
        }
        if (COBE_END_DATE.compareTo("") != 0 && COBE_END_DATE.compareTo("-") != 0) {
            endDate = "PS.COBE_END_DATE = to_date('" + COBE_END_DATE + "', 'dd-mm-yyyy hh24:mi:ss') ";
            flgEjecucion = true;
        }
        //Validamos el Flag
        if (flgEjecucion && COBE_ID.compareTo("") != 0 && COBE_ID.compareTo("-") != 0 && COBE_PKT_ID.compareTo("") != 0 && COBE_PKT_ID.compareTo("-") != 0) {
            //Armamos la sentencia UPDATE según los campos enviados
            String sql = "UPDATE COMBO_BENEFITS PS SET \n";
            if (startDate.compareTo("") != 0) {
                sql += startDate;
            }
            if (endDate.compareTo("") != 0 && startDate.compareTo("") != 0) {
                sql += " , " + endDate;
            } else {
                sql += endDate;
            }
            sql += "\n" + "where PS.COBE_ID = ? and where PS.COBE_PKT_ID = ?";
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
                        .addParameter(COBE_ID)
                        .addParameter(COBE_PKT_ID)
                        .execute();

            } catch (Exception e) {
                System.out.println("[FALLO] Metodo actFchVigencia clase ComboBenefits ");
                return false;
            }
            System.out.println("[INFO] Se realiza el UPDATE del metodo actFchVigencia clase ComboBenefits ");
            return true;
        }
        System.out.println("[INFO] No se realiza el UPDATE del metodo actFchVigencia clase ComboBenefits  ");
        return false;
    }

    public boolean insertComboBenefits(String COBE_ID, String COBE_PKT_ID, String COBE_START_DATE, String COBE_END_DATE) {
        String insert = "insert into combo_benefits (COBE_ID, COBE_PKT_ID, COBE_START_DATE, COBE_END_DATE)\n" +
                "values (" + COBE_ID + "," + COBE_PKT_ID + ", to_date(" + COBE_START_DATE + ", 'dd-mm-yyyy'), to_date(" + COBE_END_DATE + ", 'dd-mm-yyyy'))";
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
            System.out.println("[FALLO] Metodo insertComboBenefits clase ComboBenefits ");
            return false;
        }
        return true;
    }

    public boolean insertComboBenefits(String sqlInsert) {

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
            System.out.println("[FALLO] Metodo insertComboBenefits clase ComboBenefits ");
            return false;
        }
        return true;
    }

    public boolean deletComboBenefits(String COBE_ID) {
        String delete = "delete combo_benefits cb where cb.cobe_id = ?";
        try {
            String result = databaseQuery()
                    .user(this.user)
                    .password(this.password)
                    .environment(this.environment)
                    .country(this.country)
                    .prod()
                    .delete(delete)
                    .addParameter(String.valueOf(COBE_ID))
                    .execute();
        } catch (Exception e) {
            System.out.println("[FALLO] Metodo deletComboBenefits clase ComboBenefits  ");
            return false;
        }
        return true;
    }

    //   public static void main(String[] args) {
//        ComboBenefits comboBenefits = new ComboBenefits("ghct","auto_333","TEST","AR");
//      comboBenefits.actFchVigencia("CMB004","","","1/4/2024 22:12");
//   }
    ////////GET AND SET
    public String getCOBE_ID() {
        return COBE_ID;
    }

    public void setCOBE_ID(String COBE_ID) {
        this.COBE_ID = COBE_ID;
    }

    public String getCOBE_PKT_ID() {
        return COBE_PKT_ID;
    }

    public void setCOBE_PKT_ID(String COBE_PKT_ID) {
        this.COBE_PKT_ID = COBE_PKT_ID;
    }

    public String getCOBE_START_DATE() {
        return COBE_START_DATE;
    }

    public void setCOBE_START_DATE(String COBE_START_DATE) {
        this.COBE_START_DATE = COBE_START_DATE;
    }

    public String getCOBE_END_DATE() {
        return COBE_END_DATE;
    }

    public void setCOBE_END_DATE(String COBE_END_DATE) {
        this.COBE_END_DATE = COBE_END_DATE;
    }
}
