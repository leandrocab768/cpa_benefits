package com.claro.sp;

import Services.Specification;
import Services.TestValidate;
import cucumber.api.java.es.Y;

public class ValidateSteps {


    @Y("entrada: (.*),(.*),(.*) la salida: (.*),(.*),(.*),(.*),(.*),(.*)")
    public void entradaValidationsLineaPayingLineaReceivingLaSalidaValidationExecuteCellularNumberBillNumberBusinessTypePlanIdReasonCategory(String validations,String lineaPaying,String lineaReceiving,String validation,String execute,String cellularNumber,String planId,String reason,String category) {
        Specification.bodyValidate(validations, lineaPaying, lineaReceiving, validation, execute, cellularNumber, planId, reason, category);
    }

    @Y("Se validan los datos de la respuesta")
    public void seValidanLosDatosDeLaRespuesta() {

        TestValidate.validarDatosValidateService();
    }


}
