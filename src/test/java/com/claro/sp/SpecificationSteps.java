package com.claro.sp;

import Services.Specification;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;

public class SpecificationSteps {

    @Dado("que esta  configurada la especificacion de solicitud con los headers:(.*),(.*),(.*),(.*),(.*),(.*)")
    public void queEstaConfiguradaLaEspecificacionDeSolicitudConHeaderNameHeaderValueHeaderNameHeaderValueHeaderNameHeaderValue(String h1, String v1, String h2, String v2, String h3, String v3) {

        Specification.header(h1,v1,h2,v2,h3,v3);
    }


    @Cuando("ejecuto la solicitud")
    public void ejecutoLaSolicitudIngresandoAmountsBusinessTypePlanIdReasonPktType(){

        Specification.post();
    }

    @Entonces("Se obtiene el (.*)")
    public void seObtieneElStatusCode(int statusCode) {

        Specification.validarStatusCode(statusCode);
    }


    @Y("Se verifica el mensaje de salida (.*)")
    public void seVerificaElMensajeDeSalidaMensaje(String mensaje) {

        Specification.validarMensaje(mensaje);
    }
}
