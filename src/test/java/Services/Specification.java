package Services;


import ModelError.Error404NotFound;
import ModelError.ResponseError;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class Specification {

    private static Date fecha = new Date();
    private static java.text.SimpleDateFormat Fecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static String date = Fecha.format(fecha);


    public static void header(String h1, String v1, String h2, String v2, String h3, String v3) {

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        Map<String, String> hdrs = new HashMap<String, String>();
        hdrs.put(h1, v1);
        hdrs.put(h2, v2);
        hdrs.put(h3, v3);

        RestAssured.requestSpecification = (RequestSpecification) new RequestSpecBuilder()
                .addHeaders(hdrs)
                .build();
    }


    public static void bodyBenefits(String amounts, String businessType, String planId, String reason, String pktType, String amount, String pktId, String pktIdTecno, String pktTypeOut, String description, String type, String reasonOut, String planIdOut, String cobeId) {
        String bodyaux = "{\"query\":\"query {\\r\\n    benefits(\\r\\n        input:{         \\r\\n            amounts: [10]#MONTOS DE RECARGA\\r\\n            businessType:\\\"PP\\\"#NEGOCIO DE LA LINEA            \\r\\n            planId:\\\"PPP18\\\"#PLAN DE LA LINEA VERIFICACION LISTA NEGRA\\r\\n            reason:\\\"REVPDC\\\"#RAZON -> CHANNEL\\r\\n            #pktType: \\\"PD\\\"#TIPO PAQUETE\\r\\n            rechargeDate: \\\"2022-04-20T22:23:31\\\"\\r\\n        }) \\r\\n    {        \\r\\n        amount,\\r\\n        pktId,\\r\\n        pktIdTecno,        \\r\\n        pktType,\\r\\n        description,\\r\\n        type,\\r\\n        reason,\\r\\n        planIdBlackList,\\r\\n        cobeId,\\r\\n        feature{\\r\\n            id,\\r\\n            type,\\r\\n            group,\\r\\n            description,\\r\\n            webDescription,\\r\\n            volume,\\r\\n            unit\\r\\n        }\\r\\n    }\\r\\n}\",\"variables\":{}}";
        String input = "{\"query\":\"query {\\r\\n    benefits(\\r\\n        input:{         \\r\\n            amounts: [" + amounts + "]\\r\\n            businessType:\\\"" + businessType + "\\\"           \\r\\n            planId:\\\"" + planId + "\\\"\\r\\n            reason:\\\"" + reason + "\\\"\\r\\n            pktType: \\\"" + pktType + "\\\"\\r\\n            rechargeDate: \\\"" + date + "\\\"\\r\\n        })";
        String output = " \\r\\n    {        \\r\\n        " + amount + ",\\r\\n        " + pktId + ",\\r\\n        " + pktIdTecno + ",        \\r\\n        " + pktTypeOut + ",\\r\\n        " + description + ",\\r\\n        " + type + ",\\r\\n        " + reasonOut + ",\\r\\n        " + planIdOut + ",\\r\\n        " + cobeId + ",\\r\\n        feature{\\r\\n            id,\\r\\n            type,\\r\\n            \\r\\n            description,\\r\\n            webDescription\\r\\n        }\\r\\n    }\\r\\n}\",\"variables\":{}}";

        RestAssured.requestSpecification = (RequestSpecification) new RequestSpecBuilder()
                .setBody(bodyaux)
                .setBaseUri("https://sp-recharge-benefit-service-recharge-ar.apps.osen02.claro.amx")
                .setBasePath("/graphql")
                .build();
    }

    public static void bodyValidate(String validations, String lineaPaying, String lineaReceiving, String validation, String execute,String cellularNumber, String planId, String reason, String category) {
        String bodyAux = "{\"query\":\"query {\\r\\n    validate(\\r\\n        sequential: false,\\r\\n        input:{  \\r\\n            validations: [BUSINESS]    \\r\\n            linePaying: {\\r\\n                cellularNumber: \\\"23232323\\\"      \\r\\n            }            \\r\\n            lineReceiving: {\\r\\n                cellularNumber: \\\"3515572975\\\"      \\r\\n            }\\r\\n        }) \\r\\n    {\\r\\n        validate {\\r\\n            validation\\r\\n            execute\\r\\n        }\\r\\n        lineReceiving {\\r\\n            cellularNumber,\\r\\n            planId,\\r\\n            reason,\\r\\n            category\\r\\n        }\\r\\n    }\\r\\n}\",\"variables\":{}}";
        String input = "{\"query\":\"query{\\r\\n    validate(\\r\\n        sequential:false\\r\\n        input:{\\r\\n            validations: [" + validations + "]\\r\\n            linePaying: {\\r\\n                cellularNumber: \\\"" + lineaPaying + "\\\"\\r\\n            }\\r\\n            lineReceiving: {\\r\\n                cellularNumber: \\\"" + lineaReceiving + "\\\"\\r\\n            }\\r\\n        })";
        String output = "\\r\\n    {\\r\\n        validate{\\r\\n            " + validation + "\\r\\n            " + execute + "\\r\\n        }\\r\\n        lineReceiving {\\r\\n            "+cellularNumber+",            " + planId + ",\\r\\n            " + reason + ",\\r\\n            " + category + "\\r\\n        }\\r\\n    }\\r\\n}\",\"variables\":{}}";

        RestAssured.requestSpecification = (RequestSpecification) new RequestSpecBuilder()
                //.setBody(input + output)
                .setBody(bodyAux)
                .setBaseUri("https://sp-recharge-validate-service-recharge-ar-desa.apps.osen02.claro.amx")
                .setBasePath("/graphql")
                .build();
    }


    public static void bodyFeature_Benefits(String pktId, String id, String type, String description, String webDescription) {
        String input = "{\"query\":\"query {\\r\\n    features(\\r\\n        input:{         \\r\\n            pktId: \\\""+pktId+"\\\"\\r\\n        })";
        String output = "\\r\\n    {\\r\\n        "+id+",\\r\\n        "+type+",\\r\\n        "+description+",\\r\\n        "+webDescription+"\\r\\n    }\\r\\n}\",\"variables\":{}}";

        RestAssured.requestSpecification = (RequestSpecification) new RequestSpecBuilder()
                .setBody(input + output)
                .setBaseUri("https://sp-recharge-benefit-service-recharge-ar-desa.apps.osen02.claro.amx")
                .setBasePath("/graphql")
                .build();
    }


    public static Response post() {
        Response resp = given()
                .relaxedHTTPSValidation()
                .post()
                .then()
                .extract()
                .response();

        return (Response) resp;

    }


    public static void validarStatusCode(int status) {
        Response resp = post();
        resp.then().statusCode(status);

    }


    public static void validarMensaje(String mensaje) {
        Response resp = post();
        String jsonResponse = resp.then().extract().body().asString();
        ResponseError response = new Gson().fromJson(jsonResponse, ResponseError.class);
        Error404NotFound response404 = new Gson().fromJson(jsonResponse, Error404NotFound.class);
        List<ModelError.ErrorsItem> error = response.getErrors();

       if (mensaje.equals("Not Found")) {
          Assert.assertEquals(response404.getError(), mensaje);
        } else {
            for (ModelError.ErrorsItem e : error) {
               // Assert.assertEquals(e.getMessage(), mensaje);
                System.out.println(e.getMessage());
            }
        }

    }


}
