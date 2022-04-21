package BenefitsService;



import Services.TestBenefits;
import com.google.gson.Gson;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CallBenefitsService{

    public CallBenefitsService(){}

    public static void callBenefitsService(){
        //Hacemos la llamada al servicio
        //Response response = postTestCaseContratoBenefits();//ACA PONEMOS LA LOGICA DEL LLMADO AL SERVICIO

        //Convertimo la repuesta en String()
        // String jsonResponse =response.getBody().print();

        //Una ves q complete la logica de la linea 14, comentamos la linea siguiente
        //String jsonResponse = MockBenefitService.getRespuetaServicio();

        //System.out.println("Repuesta del jsonResponse:\n"+jsonResponse);
        //ResponseBenefitsService responseServicioBeneficio = new Gson().fromJson(jsonResponse, ResponseBenefitsService.class);
        //System.out.println("responseServicioBeneficio: \n"+responseServicioBeneficio);
    }

    //public static void main(String[] args) {
       // callBenefitsService();
    //}
}
