package Services;


import BenefitsService.Benefits;
import ModelError.ResponseError;
import OffertManager.*;
import com.claro.sp.model.Properties;
import io.restassured.response.Response;

import java.lang.Object;
import java.util.*;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import BenefitsService.*;
import org.junit.Assert;


public class TestBenefits extends Specification {


    public static void validateResponseEstruturaDeSalida() throws JsonMappingException, JsonProcessingException {

        Response resp = post();

        String jsonResponse = resp.then().extract().body().asString();
        String json22 = "{\n" +
                "                \"amount\": 800.0,\n" +
                "                \"pktId\": \"P2GB15\",\n" +
                "                \"pktIdTecno\": 9999,\n" +
                "                \"pktType\": \"PD\",\n" +
                "                \"description\": \"2GB por 15 días\",\n" +
                "                \"reason\": \"REVPDC\",\n" +
                "                \"planId\": null,\n" +
                "                \"cobeId\": \"CMB_TEST22\"\n" +
                "            }";

        ObjectMapper objectMapper2 = new ObjectMapper();
        // Deserializing into a Map
        Map<String, String> parsedJsonObject2 = objectMapper2.readValue(jsonResponse,
                new TypeReference<Map<String, String>>() {
                });


        // Get all keys
        Set<String> allKeys2 = parsedJsonObject2.keySet();


        String jsonObject = "{\n" +
                "                \"amount\": 800.0,\n" +
                "                \"pktId\": \"P2GB15\",\n" +
                "                \"pktIdTecno\": 9999,\n" +
                "                \"pktType\": \"PD\",\n" +
                "                \"description\": \"2GB por 15 días\",\n" +
                "                \"type\": \"DATA\",\n" +
                "                \"reason\": \"REVPDC\",\n" +
                "                \"planId\": null,\n" +
                "                \"cobeId\": \"CMB_TEST22\"\n" +
                "            }";
        ObjectMapper objectMapper = new ObjectMapper();
        // Deserializing into a Map
        Map<String, String> parsedJsonObject = objectMapper.readValue(jsonObject,
                new TypeReference<Map<String, String>>() {
                });


        // Get all keys
        Set<String> allKeys = parsedJsonObject.keySet();
        System.out.println("All keys are : ");
        allKeys.stream().forEach(k -> System.out.println(k));


        System.out.println(allKeys);
        System.out.println(allKeys2);
        System.out.println(jsonResponse);


        //Assert.assertEquals(allKeys,allKeys2);
    }


    public static void allKeysArray() throws JsonProcessingException {
        Response response = post();
        String jsonResponse = response.getBody().asString();
        ResponseBenefitsService responseServicioBeneficio = new Gson().fromJson(jsonResponse, ResponseBenefitsService.class);

        /*String jsonObject = "{\n" +
                "                \"amount\": 500.0,\n" +
                "                \"pktId\": \"P4GB30\",\n" +
                "                \"pktIdTecno\": 835,\n" +
                "                \"pktType\": \"PD\",\n" +
                "                \"description\": \"4GB por 30 días\",\n" +
                "                \"type\": \"DATA\",\n" +
                "                \"reason\": \"REVPDC\",\n" +
                "                \"planId\": \"#PPPBU#\",\n" +
                "                \"cobeId\": \"CMB001\",\n" +
                "                \"feature\": [\n" +
                "                    {\n" +
                "                        \"id\": \"54\",\n" +
                "                        \"type\": \"SERVICE\",\n" +
                "                        \"calification\": \"COMPLETAR CUSTOMER\",\n" +
                "                        \"description\": \"Packs asoc a NOKIA\",\n" +
                "                        \"webDescription\": \"Packs asoc a NOKIA\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"id\": \"2457\",\n" +
                "                        \"type\": \"SERVICE\",\n" +
                "                        \"calification\": \"COMPLETAR CUSTOMER\",\n" +
                "                        \"description\": \"RRSS 500 MB\",\n" +
                "                        \"webDescription\": \"Redes Sociales 500 MB\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"id\": \"2458\",\n" +
                "                        \"type\": \"SERVICE\",\n" +
                "                        \"calification\": \"COMPLETAR CUSTOMER\",\n" +
                "                        \"description\": \"FF 500MB\",\n" +
                "                        \"webDescription\": \"Free Fire 500 MB\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"id\": \"2496\",\n" +
                "                        \"type\": \"SERVICE\",\n" +
                "                        \"calification\": \"COMPLETAR CUSTOMER\",\n" +
                "                        \"description\": \"AMAZON_PVME30\",\n" +
                "                        \"webDescription\": \"Amazon Prime Mobile Edition 30 das\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }";*/


        ObjectMapper objectMapper = new ObjectMapper();
        // Deserializar en Map
        Map<String, Object> parsedJsonObject = objectMapper.readValue(jsonResponse, new TypeReference<Map<String, Object>>() {
        });
        // Obtener todas las claves
        Set<String> allKeys = parsedJsonObject.keySet();
        // Iterar claves
        allKeys.stream().forEach(key -> {
            Object value = parsedJsonObject.get(key);
            // Si el valor es String o ArrayList o Integer o Double
            if (value instanceof String || value instanceof ArrayList || value instanceof Integer || value instanceof Double)
                System.out.println(key);
                // Si el valor es otro objeto JSON que será LinkedHashMap.
            else if (value instanceof LinkedHashMap<?, ?>) {
                @SuppressWarnings("unchecked")
                Set<String> allKeysOfNestedJsonObject = ((LinkedHashMap<String, ?>) value).keySet();
                allKeysOfNestedJsonObject.stream().forEach(k -> System.out.println(k));
            }
        });

        // System.out.println(jsonResponse);
    }


    public static void validarDatosBenefitsService(String amount, String reason, String business, String pktType) {
        Response response = post();
        String jsonResponse = response.getBody().asString();
        //String jsonResponseDB = ;
        OfferBenefitsManager benefitsDbManager = new OfferBenefitsManager();


        OfferBenefitsManager responsebenefitsDb2 = new Gson().fromJson(String.valueOf(jsonResponse), OfferBenefitsManager.class);
        ResponseBenefitsService responseServicioBeneficio = new Gson().fromJson(jsonResponse, ResponseBenefitsService.class);

        List<Benefits> benefServicio = responseServicioBeneficio.getData().getBenefits();
        List<OffertManager.Benefits> benefDb = responsebenefitsDb2.getListBenefits(amount, reason, business, pktType);


        for (OffertManager.Benefits db : benefDb) {
            for (Benefits sv : benefServicio) {
                if (sv.getPktId().equalsIgnoreCase(db.getPktId())) {
                    //Assert.assertEquals(db.getAmount(),sv.getAmount());
                    Assert.assertEquals(db.getPktId(), sv.getPktId());
                    Assert.assertEquals(db.getPktIdTecno(), sv.getPktIdTecno());
                    Assert.assertEquals(db.getPktType(), sv.getPktType());
                    Assert.assertEquals(db.getDescription(), sv.getDescription());
                    //Assert.assertEquals(db.getType(), sv.getType());
                    Assert.assertEquals(db.getReason(), sv.getReason());
                    Assert.assertEquals(db.getPlanId(), sv.getPlanId());
                    Assert.assertEquals(db.getCobeId(), sv.getCobeId());


                }

                for (Features fdb : db.getListFeatures()) {
                    for (Feature fsv : sv.getFeature()) {
                        if (fsv.getId().equalsIgnoreCase(fdb.getFTR_ID())) {

                            Assert.assertEquals(fdb.getFTR_ID(), fsv.getId());
                            //Assert.assertEquals(fdb.getFTR_TYPE(),fsv.getType());
                            // Assert.assertEquals(fdb.get,fsv.getCalification());
                            Assert.assertEquals(fdb.getFTR_DESCRIPTION(), fsv.getDescription());
                            Assert.assertEquals(fdb.getFTR_WEB_DESCRIPTION(), fsv.getWebDescription());

                            /*System.out.println(fdb.getFTR_ID() +          " \tDB --------> SV " + fsv.getId());
                            System.out.println(fdb.getFTR_TYPE() + " \tDB --------> SV " + fsv.getType());
                            System.out.println(fdb.getFTR_DESCRIPTION() + " \tDB --------> SV " + fsv.getDescription());
                            System.out.println(fdb.getFTR_WEB_DESCRIPTION() + " \tDB --------> SV " + fsv.getWebDescription());*/

                            System.out.println(fdb.getFTR_ID() + "  DB<---------->SV  " + fsv.getId() + "\n" +
                                    fdb.getFTR_TYPE() + "  DB<---------->SV  " + fsv.getType() + "\n" +
                                    fdb.getFTR_DESCRIPTION() + "  DB<---------->SV  " + fsv.getDescription() + "\n" +
                                    fdb.getFTR_WEB_DESCRIPTION() + "  DB<---------->SV  " + fsv.getWebDescription() + "\n\n");

                        }
                    }
                }


            }

        }
    }

    public static void addVigenciaPackage(String PKT_ID, String PKT_START_DATE, String PKT_END_DATE) {
        Packages pkt = new Packages(Properties.userCcard, Properties.passwordCcard, Properties.environment, Properties.country);
        pkt.actFchVigencia(PKT_ID, PKT_START_DATE, PKT_END_DATE);

    }

    public static void addVigenciaADBE(String ADBE_AMOUNT, String ADBE_CHANNEL, String ADBE_START_DATE, String
            ADBE_END_DATE) {
        AdditionalBenefits adbe = new AdditionalBenefits(Properties.userCcard, Properties.passwordCcard, Properties.environment, Properties.country);
        adbe.actFchVigencia(ADBE_AMOUNT, ADBE_CHANNEL, ADBE_START_DATE, ADBE_END_DATE);

    }

    public static void addVigenciaCombo(String COBE_ID, String COBE_PKT_ID, String COBE_START_DATE, String
            COBE_END_DATE) {
        ComboBenefits cobe = new ComboBenefits(Properties.userCcard, Properties.passwordCcard, Properties.environment, Properties.country);
        cobe.actFchVigencia(COBE_ID, COBE_PKT_ID, COBE_START_DATE, COBE_END_DATE);

    }

   public static void combo(String c){


   }
}