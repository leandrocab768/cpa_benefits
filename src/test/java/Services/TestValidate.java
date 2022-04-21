package Services;

import ModelResponseValidate.ResponseValidate;
import ModelResponseValidate.ValidateItem;
import com.google.gson.Gson;
import io.restassured.response.Response;

import java.util.List;

public class TestValidate extends Specification{
    public static void validarDatosValidateService(){
        Response response = post();
        String jsonResponse = response.getBody().asString();
        ResponseValidate responseServicioValidate = new Gson().fromJson(jsonResponse, ResponseValidate.class);

        List<ValidateItem> listValidate = responseServicioValidate.getData().getValidate().getValidatelist();

    }
}
