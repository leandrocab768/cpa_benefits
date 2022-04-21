package OffertManager;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.claro.sp.ta.db.DatabaseQuery.databaseQuery;

public class FeaturePackages {
    private String PKS_FTR_ID;
    private String PKS_PKT_ID;
    private String user, password, environment, country;

    public FeaturePackages(String user, String password, String environment, String country) {
        this.user = user;
        this.password = password;
        this.environment = environment;
        this.country = country;
    }

    public List<FeaturePackages> getFeaturePackages(String PKS_PKT_ID) {
        List<FeaturePackages> listFeaturePackages = new ArrayList<FeaturePackages>();
        String sql = "select *\n" +
                "       from feature_packages s\n" +
                "       where s.pks_pkt_id = ?";
        try {

            String result = databaseQuery()
                    .user(this.user)
                    .password(this.password)
                    .environment(this.environment)
                    .country(this.country)
                    .prod()
                    .select(sql)
                    .addParameter(PKS_PKT_ID)
                    .execute();

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<FeaturePackages>>() {
            }.getType();
            listFeaturePackages = (List<FeaturePackages>) gson.fromJson(result, listType);

        } catch (Exception e) {
            System.out.println("[FALLO] Metodo getFeaturePackages clase FeaturePackages ");
            e.printStackTrace();
        }
        return listFeaturePackages;
    }

    public String getPKS_FTR_ID() {
        return PKS_FTR_ID;
    }

    public String getPKS_PKT_ID() {
        return PKS_PKT_ID;
    }
}
