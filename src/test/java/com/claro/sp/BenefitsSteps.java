package com.claro.sp;

import Services.Specification;
import Services.TestBenefits;
import com.fasterxml.jackson.core.JsonProcessingException;
import cucumber.api.java.es.Y;



public class BenefitsSteps {


    @Y("la estructura de entrada:(.*),(.*),(.*),(.*),(.*), salida:(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*)")
    public void laEstructuraDeEntradaAmountsBusinessTypePlanIdReasonPktType(String amounts, String businessType, String planId, String reason, String pktType, String amount, String pktId, String pktIdTecno, String pktTypeOut, String description, String type, String reasonOut, String planIdOut, String cobeId) {

        Specification.bodyBenefits(amounts, businessType, planId, reason, pktType, amount, pktId, pktIdTecno, pktTypeOut, description, type, reasonOut, planIdOut, cobeId);

    }


    @Y("Se valida la estructura de la respuesta")
    public void seValidaLaRespuestaObtenida() throws JsonProcessingException {

        TestBenefits.allKeysArray();
    }


    @Y("Se valida la respuesta obtenida:(.*),(.*),(.*),(.*)")
    public void seValidaLaRespuestaObtenidaConLosDatosCorrespondienteEnLaDB(String amount, String reason, String business,String pktType){
        TestBenefits.validarDatosBenefitsService(amount, reason, business, pktType);
    }



    @Y("la vigencia de Packequetes otorgados como beneficios Pack 1 (.*) con (.*),(.*)")
    public void laVigenciaDelBenefitConStartDateEndDate(String PKT_ID, String PKT_START_DATE, String PKT_END_DATE) {
        TestBenefits.addVigenciaPackage(PKT_ID, PKT_START_DATE, PKT_END_DATE);
    }


    @Y("la vigencia de Monto con beneficios (.*),(.*) con (.*),(.*)")
    public void laVigenciaDeADBEAmountsReasonConADBE_START_DATEADBE_END_DATE(String ADBE_AMOUNT, String ADBE_CHANNEL, String ADBE_START_DATE, String ADBE_END_DATE) {
        TestBenefits.addVigenciaADBE(ADBE_AMOUNT, ADBE_CHANNEL, ADBE_START_DATE, ADBE_END_DATE);
    }

    @Y("la vigencia de Beneficios (.*),(.*) con (.*),(.*)")
    public void laVigenciaDeCombosCOBE_IDCOBE_PKT_IDConCOBE_START_DATECOBE_END_DATE(String COBE_ID, String COBE_PKT_ID,String COBE_START_DATE, String COBE_END_DATE) {
        TestBenefits.addVigenciaCombo(COBE_ID, COBE_PKT_ID, COBE_START_DATE, COBE_END_DATE);
    }

    @Y("Vigencias de Packequetes otorgados como beneficios Pack 1 (.*) con (.*),(.*) y Pack 2 (.*) con (.*),(.*)")
    public void vigenciasDePackequetesOtorgadosComoBeneficiosPackPKT_IDConPKT_START_DATEPKT_END_DATEYPackPKT_IDConPKT_START_DATEPKT_END_DATE() {

    }

    @Y("Vigencias de Beneficios asociados a los Paquetes (.*),(.*) con (.*),(.*) y (.*),(.*) con (.*),(.*)")
    public void vigenciasDeBeneficiosAsociadosALosPaquetesCOBE_IDPKT_IDConCOBE_START_DATECOBE_END_DATEYCOBE_IDPKT_IDConCOBE_START_DATECOBE_END_DATE() {
    }

    @Y("Realizo la la insercion de los datos de prueba: (.*),(.*),(.*),(.*)")
    public void realizoLaLaInsercionDeLosDatosDePruebaPKT_IDCOBE_IDAmountsReason() {

    }

    @Y("la entrada:(.*) salida:(.*),(.*),(.*),(.*)")
    public void laEstructuraDeEntradaPktIdSalidaIdTypeDescriptionWebDescription(String pktId, String id, String type, String description, String webDescription) {
        TestBenefits.bodyFeature_Benefits(pktId, id, type, description, webDescription);
    }

    @Y("Existe el combo (.*)")
    public void existeElComboBeneficio(String combo) {

    }
}
