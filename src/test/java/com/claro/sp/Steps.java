package com.claro.sp;

import com.claro.sp.model.Properties;
import com.claro.sp.model.ResponseOfferService;
import com.claro.sp.ta.db.util.exception.IncorrectParametersException;

import com.sp.ta.tecno.model.GetSubscriberResponse;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.sql.Types;
import java.util.Locale;

import static com.claro.sp.ta.db.DatabasePackage.databasePackage;
import static com.claro.sp.ta.db.DatabaseQuery.databaseQuery;
import static com.sp.ta.tecno.GetSubscriber.getSubscriber;

public class Steps {

    String               subId      = null;
    String               channel    = null;
    ResponseOfferService response   = null;
    String               billNumber = null;

    String  negocio       = null;
    String  categoria     = null;
    boolean ofertaVigente = false;



    @After
    public void doAfter(Scenario scenario){
        System.out.println("--------------------------------------------------------------");
        System.out.println(scenario.getName() + " Status - " + scenario.getStatus());
        System.out.println("--------------------------------------------------------------");
    }

    @Before
    public void doBefore(Scenario scenario){
        //verifyServiceUpPost();
        System.out.println("--------------------------------------------------------------");
        System.out.println("Starting - " + scenario.getName());
        System.out.println("--------------------------------------------------------------");


    }



   /* @Dado("^(.*) tiene oferta vigente para el segmento$")
    public void setOfertaVigente(String oferta) {
        if(oferta.equals("si")){
            ofertaVigente = true;
        }else if (oferta.equals("no")){
            ofertaVigente = false;
        }
    }*/

    private void executeQuery() throws Exception {
        String json = databaseQuery()
                .user(Properties.userCcard)
                .password(Properties.passwordCcard)
                .environment(Properties.environment)
                .country(Properties.country)
                .ccard()
                .select("select * from prepay_parameters where ppa_name = ? and ppa_value = ?")
                .countEqual(1) //VALIDA SI LA QUERY TRAJO UN SOLO REGISTRO, DE NO SER ASI, FALLA LA PRUEBA
                .log("Consulta prepay_parameters") //SE IMPRIME POR CONSOLA EL RESULTADO DE LA QUERY, CON EL TITULO QUE SE LE PASE POR PARAMETRO
                .errorValidationMessage("Mensaje configurable de error") //MENSAJE QUE SE IMPRIME AL FALLAR LA VALIDACION countEqual
                .addParameter("PPOPER_OPERATION_REASONS")
                .addParameter("NSCTID")
                .execute();

        System.out.println("JSON: " + json);
    }

    private void executeQuery2() throws Exception {
        databaseQuery()
                .user(Properties.userProd)
                .password(Properties.passwordProd)
                .environment(Properties.environment)
                .country(Properties.country)
                .prod()
                .select("select clu_cellular_number, clu_status, clu_esn, clu_acc_id\n" +
                        "from cellulars\n" +
                        "where clu_cellular_number='3512093638'")
                .log("Consulta parameters")

                .mostrarTabla();
    }

    private void executePackage () throws Exception {
        //EJECUCION DE UNA FUNCION DENTRO DE UN PAQUETE A MODO DE EJEMPLO
        String json = databasePackage()
                .user(Properties.userCcard)
                .password(Properties.passwordCcard)
                .environment(Properties.environment)
                .country(Properties.country)
                .ccard()
                .function("PP_PRUEBA_DAMIAN.PRUEBA")
                .addInParameter("damian",Types.VARCHAR) //PARAMETRO DE ENTRADA
                .addOutParameter("texto",Types.VARCHAR) //PARAMETRO DE SALIDA
                .addOutParameter("numero",Types.NUMERIC)
                .hasReturnParameter() //INDICA QUE LA FUNCION TIENE UN RETURN CODE
                .validateExitCode(0) //VALIDA SI EL RETURN CODE ES IGUAL AL VALOR PASADO POR PARAMETRO
                .compareParameter("numero","999") //COMPARA UNO DE LOS PARAMETROS DE SALIDA CON EL VALOR PASADO POR PARAMETRO
                .execute();

        System.out.println("JSON PKG: " + json);
    }

    private void executeApi () throws IncorrectParametersException {
        GetSubscriberResponse subscriber = getSubscriber()
                .user(Properties.userCcard)
                .password(Properties.passwordCcard)
                .environment(Properties.environment)
                .country(Properties.country)
                .subId("5493516338847").execute();
        System.out.println("\n{"+"\n\tsubid: 5493516338847"+"\n\taccountstatus: "+subscriber.getAccountstatus()+"\n}");
    }


    private void executeRunTx() throws Exception {
        //Método para procesar Transacciones
        String p = billNumber.substring(billNumber.length() - 2, billNumber.length() - 0);
        //Cambia el idioma de la sesion para procesar la transaccion
        Locale loc = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);

        databasePackage()
                .user(Properties.userProd)
                .password(Properties.passwordProd)
                .environment(Properties.environment)
                .country(Properties.country)
                .prod()
                .function("STL.PA_PPAY_INTERFACES_GSM.MAIN")
                .addInParameter(p, Types.VARCHAR)
                .hasReturnParameter()
                .validateExitCode(0)
                .execute();
        Locale.setDefault(loc);
        System.out.println("\n\t[INFO] --> Se procesa la Tx");
    }

   /* @Cuando("consulto los beneficios asociados a los montos de recarga ingresandoH:(.*),(.*),(.*),(.*),(.*),(.*)")
    public void consultoLosBeneficiosAsociadosALosMontosDeRecargaIngresandoHAmountsBusinessTypePlanIdReasonPktTypeRechargeDate(int amounts, String businessType, String planId, String reason, String pktType, String rechargeDate){
        TestContrato.setUp();
        TestContrato.PostTestCaseContrato(amounts,businessType,planId,reason,pktType,rechargeDate);
    }*/
}

