package OffertManager;

import Services.TestBenefits;
import com.claro.sp.model.Properties;

import java.util.ArrayList;
import java.util.List;

public class OfferBenefitsManager {

    private String user, password, environment, country;
    private AdditionalBenefits additionalBenefits;
    private Packages packages;
    private ServicePackParameters servicePackParameters;
    private ComboBenefits comboBenefits;
    private FeaturePackages featurePackages;
    private Features features;

    ///Lista de Datos

    private List<Benefits> listBenefits;


    public OfferBenefitsManager(String user, String password, String environment, String country) {

        this.user = user;
        this.password = password;
        this.environment = environment;
        this.country = country;
        this.listBenefits = new ArrayList<Benefits>();

        this.additionalBenefits = new AdditionalBenefits(user, password, environment, country);
        this.servicePackParameters = new ServicePackParameters(user, password, environment, country);
        this.comboBenefits = new ComboBenefits(user, password, environment, country);
        this.packages = new Packages(user, password, environment, country);
        this.featurePackages = new FeaturePackages(user, password, environment, country);
        this.features = new Features(user, password, environment, country);
    }

    public OfferBenefitsManager() {

        this.user = Properties.userCcard;
        this.password = Properties.passwordCcard;
        this.environment = Properties.environment;
        this.country = Properties.country;
        this.listBenefits = new ArrayList<Benefits>();

        this.additionalBenefits = new AdditionalBenefits(user, password, environment, country);
        this.servicePackParameters = new ServicePackParameters(user, password, environment, country);
        this.comboBenefits = new ComboBenefits(user, password, environment, country);
        this.packages = new Packages(user, password, environment, country);
        this.featurePackages = new FeaturePackages(user, password, environment, country);
        this.features = new Features(user, password, environment, country);
    }

    public List<Benefits> getListBenefits(String amount, String reason, String business, String pktType) {
        List<Benefits> listBenefits = new ArrayList<Benefits>();
        List<AdditionalBenefits> listAdditionalBenefits = this.additionalBenefits.listAdditionalBenefits(amount, reason, business);
        for (AdditionalBenefits auxAdditionalBenefits : listAdditionalBenefits) {
            Float auxAmount = auxAdditionalBenefits.getADBE_AMOUNT();
            //Preguntamos si tiene beneficio, si es null no tiene beneficios asociados.
            if (auxAdditionalBenefits.getADBE_COBE_ID() != null) {
                List<ComboBenefits> listComboBenefits = comboBenefits.getComboBenefitsVigentes(auxAdditionalBenefits.getADBE_COBE_ID());
                //Buscamos en la tabla de combo_Benefits los beneficios y sus datos
                for (ComboBenefits comboBenefts : listComboBenefits) {
                    //Buscamos los datos del Package
                    Packages auxPackages = packages.getPackagesVigente(comboBenefts.getCOBE_PKT_ID());
                    if (auxPackages.getPKT_TYPE().equalsIgnoreCase(pktType) || pktType.equals("-")) {

                        //Fitro de tipo de paquete
                        String pktId = auxPackages.getPKT_ID();
                        ServicePackParameters auxServicePackParameters = servicePackParameters.getServicePackParameters(pktId);
                        String pktIdTecno = "-1";
                        if (auxServicePackParameters != null) {
                            pktIdTecno = auxServicePackParameters.getSEP_PKT_TECNOMEN();
                        }
                        String pktType2 = auxPackages.getPKT_TYPE();
                        String description = auxPackages.getPKT_WEB_DESCRIPTION();
                        String type = defineType(pktType2);
                        String planId = auxAdditionalBenefits.getADBE_BLACKLIST();
                        String cobeId = auxAdditionalBenefits.getADBE_COBE_ID();

                        //Creamos el objeto de la clase Benefits
                        Benefits benefits = new Benefits(auxAmount, pktId, pktIdTecno, pktType2, description, type, reason, planId, cobeId);

                        //Agregamos los Feature
                        for (FeaturePackages auxFeaturePackages : featurePackages.getFeaturePackages(pktId)) {
                            Features auxFeatures = features.getFeatures(auxFeaturePackages.getPKS_FTR_ID());
                            benefits.addFeaturesList(auxFeatures);
                        }

                        //Agregamos un beneficio a la lista
                        listBenefits.add(benefits);

                    }
                }

            }
        }
        ///Lista AdditionaBenefit
        return listBenefits;
    }

    public void casuisticaConfigCMBTEST01() {
        //Registar AdditionalBenefits
        System.out.println("[INFO] Cargando Configuracion Beneficio");
        boolean flag = false;
        String insertAdditionalBenefits = "insert into additional_benefits (ADBE_AMOUNT, ADBE_CHANNEL, ADBE_BUSINESS, ADBE_PKT_ID, ADBE_START_DATE, ADBE_END_DATE, ADBE_DESCRIPTION, ADBE_BLACKLIST, ADBE_COBE_ID)\n" +
                "values (10.0000, 'REVPDC', 'PP', null, to_date('14-12-2021 10:37:59', 'dd-mm-yyyy hh24:mi:ss'), to_date('31-12-3999', 'dd-mm-yyyy'), 'DESCRIPCION DE PRUEBA', null, 'CMB_TEST01')";

        this.additionalBenefits.deletAdditionalBenefits("10", "REVPDC", "PP");
        flag = this.additionalBenefits.insertAdditionalBenefits(insertAdditionalBenefits);

        String insertComboBenefits = "insert into combo_benefits (COBE_ID, COBE_PKT_ID, COBE_START_DATE, COBE_END_DATE)\n" +
                "values ('CMB_TEST01', 'P1GB1D', to_date('11-04-2022', 'dd-mm-yyyy'), to_date('13-04-2099', 'dd-mm-yyyy'))";
        this.comboBenefits.deletComboBenefits("CMB_TEST01");
        flag = this.comboBenefits.insertComboBenefits(insertComboBenefits);

        String insertPackages = "insert into packages (PKT_ID, PKT_DESCRIPTION, PKT_CURRENT_PRICE, PKT_ADVANCE, PKT_INC_ID, PKT_CANCELED, PKT_FRT_ID, PKT_START_DATE, PKT_END_DATE, PKT_PRORRATION, PKT_SYR_ID, PKT_TYPE, PKT_LENGTH, PKT_DIGITAL_FLAG, PKT_SMS_INFOR_REQ_FLAG, PKT_AUDIOTEL_FLAG, PKT_INC_ID_LD, PKT_VALIDATION_FLAG, PKT_CHECKED_BILLING_FLAG, PKT_PREPAY_FLAG, PKT_CLASSING, PKT_SOURCE, PKT_CHANGING_TYPE, PKT_MIP_ID_PERMANENCY, PKT_MIP_ID_NEW, PKT_SECOND_ENT_FLAG, PKT_SECOND_ENT_ID, PKT_PFA_ID, PKT_CATEGORY, PKT_ISG_ID, PKT_FTR_ISI, PKT_LENGTH_DAYS, PKT_PARAMETRO, PKT_ENABLED_CLEARING, PKT_CAN_CICLO, PKT_BAJAR_FLAG, PKT_WEB_DESCRIPTION, PKT_VISIBILITY, PKT_HELP, PKT_WORK_AS_PREPAY, PKT_CHARGE_ID, PKT_EVENTUAL_CHARGE, PKT_CYCLIC, PKT_QUANTITY)\n" +
                "values ('P1GB1D', 'Pack Internet PP 1GB x 1 día', 63.3600, 'N', 'P1GBA', 'N', null, to_date('24-01-2017', 'dd-mm-yyyy'), to_date('31-12-3999', 'dd-mm-yyyy'), 'N', null, 'PD', null, null, null, null, null, null, 'N', null, null, null, 'PACKDATOS', 'N', null, 'N', null, '94', 'M', null, 'NNNN', 1.00000000, null, 'N', 90, null, '1GB por 1 día', 'N', null, 'Y', null, null, 'N', '1024')\n";

        //Validamos q el pack no este cargado, si no esta cargado devuelve null
        if (this.packages.getPackagesVigente("P1GB1D") == null) {
            this.packages.insertPackages(insertPackages);
        } else {
            System.out.println("[INFO] El packages P1GB1D ya se encuentra cargado");
        }

        if (flag) {
            System.out.println("|monto|rezon|tipoNegocio|idCombo|estadoVigente|idCombo|idPK|estadoVigente|idPK|featureAsociado|estadoVigente\n" +
                    "10|REVPDC|PP|CMB_TEST01|VIGENTE|CMB_TEST01|P1GB1D|VIGENTE|P1GB1D|SITiene|VIGENTE");
        } else {
            System.out.println("[WARNING] Fallo la carga de la Configuracion Beneficio");
        }

    }

    public void casuisticaConfigCMBTEST02() {
        //Registar AdditionalBenefits
        System.out.println("[INFO] Cargando Configuracion Beneficio");
        boolean flag = false;
        //Insertamos registro con la promocion de $20
        String insertAdditionalBenefits = "insert into Additional_Benefits (ADBE_AMOUNT, ADBE_CHANNEL, ADBE_BUSINESS, ADBE_PKT_ID, ADBE_START_DATE, ADBE_END_DATE, ADBE_DESCRIPTION, ADBE_BLACKLIST, ADBE_COBE_ID)\n" +
                "values (20.0000, 'REVPDC', 'PP', null, to_date('14-12-2020 10:37:59', 'dd-mm-yyyy hh24:mi:ss'), to_date('11-04-2022', 'dd-mm-yyyy'), 'DESCRIPCION DE PRUEBA', null, 'CMB_TEST02')";
        this.additionalBenefits.deletAdditionalBenefits("20", "REVPDC", "PP");
        flag = this.additionalBenefits.insertAdditionalBenefits(insertAdditionalBenefits);

        //ARegistramos los beneficios a otorgar, pero antes eliminamos los registros historicos.
        this.comboBenefits.deletComboBenefits("CMB_TEST02");

        String insertComboBenefits1 = "insert into combo_benefits (COBE_ID, COBE_PKT_ID, COBE_START_DATE, COBE_END_DATE)\n" +
                "values ('CMB_TEST02', 'P1GB1D', to_date('11-04-2022', 'dd-mm-yyyy'), to_date('13-04-2099', 'dd-mm-yyyy'))";
        flag = this.comboBenefits.insertComboBenefits(insertComboBenefits1);


        String insertPackages = "insert into packages (PKT_ID, PKT_DESCRIPTION, PKT_CURRENT_PRICE, PKT_ADVANCE, PKT_INC_ID, PKT_CANCELED, PKT_FRT_ID, PKT_START_DATE, PKT_END_DATE, PKT_PRORRATION, PKT_SYR_ID, PKT_TYPE, PKT_LENGTH, PKT_DIGITAL_FLAG, PKT_SMS_INFOR_REQ_FLAG, PKT_AUDIOTEL_FLAG, PKT_INC_ID_LD, PKT_VALIDATION_FLAG, PKT_CHECKED_BILLING_FLAG, PKT_PREPAY_FLAG, PKT_CLASSING, PKT_SOURCE, PKT_CHANGING_TYPE, PKT_MIP_ID_PERMANENCY, PKT_MIP_ID_NEW, PKT_SECOND_ENT_FLAG, PKT_SECOND_ENT_ID, PKT_PFA_ID, PKT_CATEGORY, PKT_ISG_ID, PKT_FTR_ISI, PKT_LENGTH_DAYS, PKT_PARAMETRO, PKT_ENABLED_CLEARING, PKT_CAN_CICLO, PKT_BAJAR_FLAG, PKT_WEB_DESCRIPTION, PKT_VISIBILITY, PKT_HELP, PKT_WORK_AS_PREPAY, PKT_CHARGE_ID, PKT_EVENTUAL_CHARGE, PKT_CYCLIC, PKT_QUANTITY)\n" +
                "values ('P1GB1D', 'Pack Internet PP 1GB x 1 día', 63.3600, 'N', 'P1GBA', 'N', null, to_date('24-01-2017', 'dd-mm-yyyy'), to_date('31-12-3999', 'dd-mm-yyyy'), 'N', null, 'PD', null, null, null, null, null, null, 'N', null, null, null, 'PACKDATOS', 'N', null, 'N', null, '94', 'M', null, 'NNNN', 1.00000000, null, 'N', 90, null, '1GB por 1 día', 'N', null, 'Y', null, null, 'N', '1024');\n";
        if (this.packages.getPackagesVigente("P1GB1D") == null) {
            this.packages.insertPackages(insertPackages);
        } else {
            System.out.println("[INFO] El packages P1GB1D ya se encuentra cargado");
        }


        if (flag) {
            System.out.println("|monto|rezon|tipoNegocio|idCombo|estadoVigente|idCombo|idPK|estadoVigente|idPK|featureAsociado|estadoVigente\n" +
                    "|20|REVPDC|PP|CMB_TEST02|NOVIGENTE|CMB_TEST02|P1GB1D|VIGENTE|P1GB1D|SITiene|VIGENTE|");
        } else {
            System.out.println("[WARNING] Fallo la carga de la Configuracion Beneficio");
        }

    }

    public void casuisticaConfigCMBTEST03() {
        //Registar AdditionalBenefits
        System.out.println("[INFO] Cargando Configuracion Beneficio");
        boolean flag = false;
        //Insertamos registro con la promocion de $30
        String insertAdditionalBenefits = "insert into Additional_Benefits (ADBE_AMOUNT, ADBE_CHANNEL, ADBE_BUSINESS, ADBE_PKT_ID, ADBE_START_DATE, ADBE_END_DATE, ADBE_DESCRIPTION, ADBE_BLACKLIST, ADBE_COBE_ID)\n" +
                "values (30.0000, 'REVPDC', 'PP', null, to_date('14-12-2020 10:37:59', 'dd-mm-yyyy hh24:mi:ss'), to_date('11-04-2029', 'dd-mm-yyyy'), 'DESCRIPCION DE PRUEBA', null, 'CMB_TEST03')";
        this.additionalBenefits.deletAdditionalBenefits("30", "REVPDC", "PP");
        flag = this.additionalBenefits.insertAdditionalBenefits(insertAdditionalBenefits);

        //ARegistramos los beneficios a otorgar, pero antes eliminamos los registros historicos.
        this.comboBenefits.deletComboBenefits("CMB_TEST03");

        String insertComboBenefits1 = "insert into combo_benefits (COBE_ID, COBE_PKT_ID, COBE_START_DATE, COBE_END_DATE)\n" +
                "values ('CMB_TEST03', 'P1GB1D', to_date('11-04-2022', 'dd-mm-yyyy'), to_date('13-04-2022', 'dd-mm-yyyy'))";
        flag = this.comboBenefits.insertComboBenefits(insertComboBenefits1);


        String insertPackages = "insert into packages (PKT_ID, PKT_DESCRIPTION, PKT_CURRENT_PRICE, PKT_ADVANCE, PKT_INC_ID, PKT_CANCELED, PKT_FRT_ID, PKT_START_DATE, PKT_END_DATE, PKT_PRORRATION, PKT_SYR_ID, PKT_TYPE, PKT_LENGTH, PKT_DIGITAL_FLAG, PKT_SMS_INFOR_REQ_FLAG, PKT_AUDIOTEL_FLAG, PKT_INC_ID_LD, PKT_VALIDATION_FLAG, PKT_CHECKED_BILLING_FLAG, PKT_PREPAY_FLAG, PKT_CLASSING, PKT_SOURCE, PKT_CHANGING_TYPE, PKT_MIP_ID_PERMANENCY, PKT_MIP_ID_NEW, PKT_SECOND_ENT_FLAG, PKT_SECOND_ENT_ID, PKT_PFA_ID, PKT_CATEGORY, PKT_ISG_ID, PKT_FTR_ISI, PKT_LENGTH_DAYS, PKT_PARAMETRO, PKT_ENABLED_CLEARING, PKT_CAN_CICLO, PKT_BAJAR_FLAG, PKT_WEB_DESCRIPTION, PKT_VISIBILITY, PKT_HELP, PKT_WORK_AS_PREPAY, PKT_CHARGE_ID, PKT_EVENTUAL_CHARGE, PKT_CYCLIC, PKT_QUANTITY)\n" +
                "values ('P1GB1D', 'Pack Internet PP 1GB x 1 día', 63.3600, 'N', 'P1GBA', 'N', null, to_date('24-01-2017', 'dd-mm-yyyy'), to_date('31-12-3999', 'dd-mm-yyyy'), 'N', null, 'PD', null, null, null, null, null, null, 'N', null, null, null, 'PACKDATOS', 'N', null, 'N', null, '94', 'M', null, 'NNNN', 1.00000000, null, 'N', 90, null, '1GB por 1 día', 'N', null, 'Y', null, null, 'N', '1024');\n";

        if (this.packages.getPackagesVigente("P1GB1D") == null) {
            this.packages.insertPackages(insertPackages);
        } else {
            System.out.println("[INFO] El packages P1GB1D ya se encuentra cargado");
        }

        if (flag) {
            System.out.println("|monto|rezon|tipoNegocio|idCombo|estadoVigente|idCombo|idPK|estadoVigente|idPK|featureAsociado|estadoVigente\n" +
                    "30|REVPDC|PP|CMB_TEST03|VIGENTE|CMB_TEST03|P1GB1D|NOVIGENTE|P1GB1D|SITiene|VIGENTE");
        } else {
            System.out.println("[WARNING] Fallo la carga de la Configuracion Beneficio");
        }

    }

    public void casuisticaConfigCMBTEST04() {
        //Registar AdditionalBenefits
        System.out.println("[INFO] Cargando Configuracion Beneficio");
        boolean flag = false;
        //Insertamos registro con la promocion de $40
        String insertAdditionalBenefits = "insert into Additional_Benefits (ADBE_AMOUNT, ADBE_CHANNEL, ADBE_BUSINESS, ADBE_PKT_ID, ADBE_START_DATE, ADBE_END_DATE, ADBE_DESCRIPTION, ADBE_BLACKLIST, ADBE_COBE_ID)\n" +
                "values (40.0000, 'REVPDC', 'PP', null, to_date('14-12-2020 10:37:59', 'dd-mm-yyyy hh24:mi:ss'), to_date('11-04-2029', 'dd-mm-yyyy'), 'DESCRIPCION DE PRUEBA', null, 'CMB_TEST04')";
        this.additionalBenefits.deletAdditionalBenefits("40", "REVPDC", "PP");
        flag = this.additionalBenefits.insertAdditionalBenefits(insertAdditionalBenefits);

        //ARegistramos los beneficios a otorgar, pero antes eliminamos los registros historicos.
        this.comboBenefits.deletComboBenefits("CMB_TEST04");

        String insertComboBenefits1 = "insert into combo_benefits (COBE_ID, COBE_PKT_ID, COBE_START_DATE, COBE_END_DATE)\n" +
                "values ('CMB_TEST04', 'T6GB3D', to_date('11-04-2022', 'dd-mm-yyyy'), to_date('13-04-2099', 'dd-mm-yyyy'))";
        flag = this.comboBenefits.insertComboBenefits(insertComboBenefits1);


        String insertPackages = "insert into packages (PKT_ID, PKT_DESCRIPTION, PKT_CURRENT_PRICE, PKT_ADVANCE, PKT_INC_ID, PKT_CANCELED, PKT_FRT_ID, PKT_START_DATE, PKT_END_DATE, PKT_PRORRATION, PKT_SYR_ID, PKT_TYPE, PKT_LENGTH, PKT_DIGITAL_FLAG, PKT_SMS_INFOR_REQ_FLAG, PKT_AUDIOTEL_FLAG, PKT_INC_ID_LD, PKT_VALIDATION_FLAG, PKT_CHECKED_BILLING_FLAG, PKT_PREPAY_FLAG, PKT_CLASSING, PKT_SOURCE, PKT_CHANGING_TYPE, PKT_MIP_ID_PERMANENCY, PKT_MIP_ID_NEW, PKT_SECOND_ENT_FLAG, PKT_SECOND_ENT_ID, PKT_PFA_ID, PKT_CATEGORY, PKT_ISG_ID, PKT_FTR_ISI, PKT_LENGTH_DAYS, PKT_PARAMETRO, PKT_ENABLED_CLEARING, PKT_CAN_CICLO, PKT_BAJAR_FLAG, PKT_WEB_DESCRIPTION, PKT_VISIBILITY, PKT_HELP, PKT_WORK_AS_PREPAY, PKT_CHARGE_ID, PKT_EVENTUAL_CHARGE, PKT_CYCLIC, PKT_QUANTITY)\n" +
                "values ('T6GB3D', 'Pack Internet PP 6GB por 3 días', 269.2856, 'N', 'PP63A', 'N', null, to_date('12-06-2019', 'dd-mm-yyyy'), to_date('19-04-2022', 'dd-mm-yyyy'), 'N', null, 'PD', null, null, null, null, null, null, 'N', null, null, null, 'PACKDATOS', 'N', null, 'N', null, '94', 'M', null, 'NNNN', 3.00000000, null, 'N', 90, null, '6GB por 3 días', null, null, 'Y', null, null, 'N', '6144')";

        if (this.packages.getPackagesVigente("T6GB3D") == null) {
            this.packages.insertPackages(insertPackages);
        } else {
            System.out.println("[INFO] El packages T6GB3D ya se encuentra cargado");
        }
        if (flag) {
            System.out.println("|monto|rezon|tipoNegocio|idCombo|estadoVigente|idCombo|idPK|estadoVigente|idPK|featureAsociado|estadoVigente\n" +
                    "40|REVPDC|PP|CMB_TEST04|VIGENTE|CMB_TEST04|T6GB3D|VIGENTE|T6GB3D|SITiene|NOVIGENTE");
        } else {
            System.out.println("[WARNING] Fallo la carga de la Configuracion Beneficio");
        }

    }

    public void casuisticaConfigCMBTEST05() {
        //Registar AdditionalBenefits
        System.out.println("[INFO] Cargando Configuracion Beneficio");
        boolean flag = false;
        //Insertamos registro con la promocion de $40
        String insertAdditionalBenefits = "insert into Additional_Benefits (ADBE_AMOUNT, ADBE_CHANNEL, ADBE_BUSINESS, ADBE_PKT_ID, ADBE_START_DATE, ADBE_END_DATE, ADBE_DESCRIPTION, ADBE_BLACKLIST, ADBE_COBE_ID)\n" +
                "values (60.0000, 'REVPDC', 'PP', null, to_date('14-12-2020 10:37:59', 'dd-mm-yyyy hh24:mi:ss'), to_date('11-04-2029', 'dd-mm-yyyy'), 'DESCRIPCION DE PRUEBA', null, 'CMB_TEST05')";
        this.additionalBenefits.deletAdditionalBenefits("60", "REVPDC", "PP");
        flag = this.additionalBenefits.insertAdditionalBenefits(insertAdditionalBenefits);

        //ARegistramos los beneficios a otorgar, pero antes eliminamos los registros historicos.
        this.comboBenefits.deletComboBenefits("CMB_TEST05");

        String insertComboBenefits1 = "insert into combo_benefits (COBE_ID, COBE_PKT_ID, COBE_START_DATE, COBE_END_DATE)\n" +
                "values ('CMB_TEST05', 'P1GB1D', to_date('10-04-2022', 'dd-mm-yyyy'), to_date('10-04-2099', 'dd-mm-yyyy'))";
        String insertComboBenefits2 = "insert into combo_benefits (COBE_ID, COBE_PKT_ID, COBE_START_DATE, COBE_END_DATE)\n" +
                "values ('CMB_TEST05', 'P2GB15', to_date('10-03-2022', 'dd-mm-yyyy'), to_date('11-04-2022', 'dd-mm-yyyy'))";

        flag = this.comboBenefits.insertComboBenefits(insertComboBenefits1);
        flag = this.comboBenefits.insertComboBenefits(insertComboBenefits2);


        String insertPackages1 = "insert into packages (PKT_ID, PKT_DESCRIPTION, PKT_CURRENT_PRICE, PKT_ADVANCE, PKT_INC_ID, PKT_CANCELED, PKT_FRT_ID, PKT_START_DATE, PKT_END_DATE, PKT_PRORRATION, PKT_SYR_ID, PKT_TYPE, PKT_LENGTH, PKT_DIGITAL_FLAG, PKT_SMS_INFOR_REQ_FLAG, PKT_AUDIOTEL_FLAG, PKT_INC_ID_LD, PKT_VALIDATION_FLAG, PKT_CHECKED_BILLING_FLAG, PKT_PREPAY_FLAG, PKT_CLASSING, PKT_SOURCE, PKT_CHANGING_TYPE, PKT_MIP_ID_PERMANENCY, PKT_MIP_ID_NEW, PKT_SECOND_ENT_FLAG, PKT_SECOND_ENT_ID, PKT_PFA_ID, PKT_CATEGORY, PKT_ISG_ID, PKT_FTR_ISI, PKT_LENGTH_DAYS, PKT_PARAMETRO, PKT_ENABLED_CLEARING, PKT_CAN_CICLO, PKT_BAJAR_FLAG, PKT_WEB_DESCRIPTION, PKT_VISIBILITY, PKT_HELP, PKT_WORK_AS_PREPAY, PKT_CHARGE_ID, PKT_EVENTUAL_CHARGE, PKT_CYCLIC, PKT_QUANTITY)\n" +
                "values ('P1GB1D', 'Pack Internet PP 1GB x 1 día', 63.3600, 'N', 'P1GBA', 'N', null, to_date('24-01-2017', 'dd-mm-yyyy'), to_date('31-12-3999', 'dd-mm-yyyy'), 'N', null, 'PD', null, null, null, null, null, null, 'N', null, null, null, 'PACKDATOS', 'N', null, 'N', null, '94', 'M', null, 'NNNN', 1.00000000, null, 'N', 90, null, '1GB por 1 día', 'N', null, 'Y', null, null, 'N', '1024')";

        String insertPackages2 = "insert into packages (PKT_ID, PKT_DESCRIPTION, PKT_CURRENT_PRICE, PKT_ADVANCE, PKT_INC_ID, PKT_CANCELED, PKT_FRT_ID, PKT_START_DATE, PKT_END_DATE, PKT_PRORRATION, PKT_SYR_ID, PKT_TYPE, PKT_LENGTH, PKT_DIGITAL_FLAG, PKT_SMS_INFOR_REQ_FLAG, PKT_AUDIOTEL_FLAG, PKT_INC_ID_LD, PKT_VALIDATION_FLAG, PKT_CHECKED_BILLING_FLAG, PKT_PREPAY_FLAG, PKT_CLASSING, PKT_SOURCE, PKT_CHANGING_TYPE, PKT_MIP_ID_PERMANENCY, PKT_MIP_ID_NEW, PKT_SECOND_ENT_FLAG, PKT_SECOND_ENT_ID, PKT_PFA_ID, PKT_CATEGORY, PKT_ISG_ID, PKT_FTR_ISI, PKT_LENGTH_DAYS, PKT_PARAMETRO, PKT_ENABLED_CLEARING, PKT_CAN_CICLO, PKT_BAJAR_FLAG, PKT_WEB_DESCRIPTION, PKT_VISIBILITY, PKT_HELP, PKT_WORK_AS_PREPAY, PKT_CHARGE_ID, PKT_EVENTUAL_CHARGE, PKT_CYCLIC, PKT_QUANTITY)\n" +
                "values ('P2GB15', 'Pack Internet PP 2 GB x 15 días - Full', 340.5671, 'N', 'P2GBA', 'N', null, to_date('01-12-2020', 'dd-mm-yyyy'), to_date('31-12-3999', 'dd-mm-yyyy'), 'N', null, 'PD', null, null, null, null, null, null, 'N', null, null, null, 'PACKDATOS', 'N', null, 'N', null, '94', 'M', null, 'NNNN', 15.00000000, null, 'N', 90, null, '2GB por 15 días', 'W', null, 'Y', null, null, 'N', '2048')";


        if (this.packages.getPackagesVigente("P1GB1D") == null) {
            this.packages.insertPackages(insertPackages1);
        } else {
            System.out.println("[INFO] El packages P1GB1D ya se encuentra cargado");
        }

        if (this.packages.getPackagesVigente("P2GB15") == null) {
            this.packages.insertPackages(insertPackages2);
        } else {
            System.out.println("[INFO] El packages P2GB15 ya se encuentra cargado");
        }


        if (flag) {
            System.out.println("|monto|rezon|tipoNegocio|idCombo|estadoVigente|idCombo|idPK|estadoVigente|idPK|featureAsociado|estadoVigente\n" +
                    "60|REVPDC|PP|CMB_TEST05|VIGENTE|CMB_TEST05|P1GB1D|VIGENTE||P1GB1D|SITiene|VIGENTE\n" +
                    "60|REVPDC|PP|CMB_TEST05|VIGENTE|CMB_TEST05|P2GB15|NOVIGENTE|P2GB15|SITiene|VIGENTE");
        } else {
            System.out.println("[WARNING] Fallo la carga de la Configuracion Beneficio");
        }

    }

    public void casuisticaConfigCMBTEST06() {
        //Registar AdditionalBenefits
        System.out.println("[INFO] Cargando Configuracion Beneficio");
        boolean flag = false;
        //Insertamos registro con la promocion de $40
        String insertAdditionalBenefits = "insert into Additional_Benefits (ADBE_AMOUNT, ADBE_CHANNEL, ADBE_BUSINESS, ADBE_PKT_ID, ADBE_START_DATE, ADBE_END_DATE, ADBE_DESCRIPTION, ADBE_BLACKLIST, ADBE_COBE_ID)\n" +
                "values (70.0000, 'REVPDC', 'PP', null, to_date('14-12-2020 10:37:59', 'dd-mm-yyyy hh24:mi:ss'), to_date('11-04-2029', 'dd-mm-yyyy'), 'DESCRIPCION DE PRUEBA', null, 'CMB_TEST06')";
        this.additionalBenefits.deletAdditionalBenefits("70", "REVPDC", "PP");
        flag = this.additionalBenefits.insertAdditionalBenefits(insertAdditionalBenefits);

        //ARegistramos los beneficios a otorgar, pero antes eliminamos los registros historicos.
        this.comboBenefits.deletComboBenefits("CMB_TEST06");

        String insertComboBenefits1 = "insert into combo_benefits (COBE_ID, COBE_PKT_ID, COBE_START_DATE, COBE_END_DATE)\n" +
                "values ('CMB_TEST06', 'P1GB1D', to_date('10-04-2022', 'dd-mm-yyyy'), to_date('10-04-2099', 'dd-mm-yyyy'))";
        String insertComboBenefits2 = "insert into combo_benefits (COBE_ID, COBE_PKT_ID, COBE_START_DATE, COBE_END_DATE)\n" +
                "values ('CMB_TEST06', 'T6GB3D', to_date('10-03-2022', 'dd-mm-yyyy'), to_date('10-04-2099', 'dd-mm-yyyy'))";

        flag = this.comboBenefits.insertComboBenefits(insertComboBenefits1);
        flag = this.comboBenefits.insertComboBenefits(insertComboBenefits2);


        String insertPackages1 = "insert into packages (PKT_ID, PKT_DESCRIPTION, PKT_CURRENT_PRICE, PKT_ADVANCE, PKT_INC_ID, PKT_CANCELED, PKT_FRT_ID, PKT_START_DATE, PKT_END_DATE, PKT_PRORRATION, PKT_SYR_ID, PKT_TYPE, PKT_LENGTH, PKT_DIGITAL_FLAG, PKT_SMS_INFOR_REQ_FLAG, PKT_AUDIOTEL_FLAG, PKT_INC_ID_LD, PKT_VALIDATION_FLAG, PKT_CHECKED_BILLING_FLAG, PKT_PREPAY_FLAG, PKT_CLASSING, PKT_SOURCE, PKT_CHANGING_TYPE, PKT_MIP_ID_PERMANENCY, PKT_MIP_ID_NEW, PKT_SECOND_ENT_FLAG, PKT_SECOND_ENT_ID, PKT_PFA_ID, PKT_CATEGORY, PKT_ISG_ID, PKT_FTR_ISI, PKT_LENGTH_DAYS, PKT_PARAMETRO, PKT_ENABLED_CLEARING, PKT_CAN_CICLO, PKT_BAJAR_FLAG, PKT_WEB_DESCRIPTION, PKT_VISIBILITY, PKT_HELP, PKT_WORK_AS_PREPAY, PKT_CHARGE_ID, PKT_EVENTUAL_CHARGE, PKT_CYCLIC, PKT_QUANTITY)\n" +
                "values ('P1GB1D', 'Pack Internet PP 1GB x 1 día', 63.3600, 'N', 'P1GBA', 'N', null, to_date('24-01-2017', 'dd-mm-yyyy'), to_date('31-12-3999', 'dd-mm-yyyy'), 'N', null, 'PD', null, null, null, null, null, null, 'N', null, null, null, 'PACKDATOS', 'N', null, 'N', null, '94', 'M', null, 'NNNN', 1.00000000, null, 'N', 90, null, '1GB por 1 día', 'N', null, 'Y', null, null, 'N', '1024')";

        String insertPackages2 = "insert into packages (PKT_ID, PKT_DESCRIPTION, PKT_CURRENT_PRICE, PKT_ADVANCE, PKT_INC_ID, PKT_CANCELED, PKT_FRT_ID, PKT_START_DATE, PKT_END_DATE, PKT_PRORRATION, PKT_SYR_ID, PKT_TYPE, PKT_LENGTH, PKT_DIGITAL_FLAG, PKT_SMS_INFOR_REQ_FLAG, PKT_AUDIOTEL_FLAG, PKT_INC_ID_LD, PKT_VALIDATION_FLAG, PKT_CHECKED_BILLING_FLAG, PKT_PREPAY_FLAG, PKT_CLASSING, PKT_SOURCE, PKT_CHANGING_TYPE, PKT_MIP_ID_PERMANENCY, PKT_MIP_ID_NEW, PKT_SECOND_ENT_FLAG, PKT_SECOND_ENT_ID, PKT_PFA_ID, PKT_CATEGORY, PKT_ISG_ID, PKT_FTR_ISI, PKT_LENGTH_DAYS, PKT_PARAMETRO, PKT_ENABLED_CLEARING, PKT_CAN_CICLO, PKT_BAJAR_FLAG, PKT_WEB_DESCRIPTION, PKT_VISIBILITY, PKT_HELP, PKT_WORK_AS_PREPAY, PKT_CHARGE_ID, PKT_EVENTUAL_CHARGE, PKT_CYCLIC, PKT_QUANTITY)\n" +
                "values ('T6GB3D', 'Pack Internet PP 6GB por 3 días', 269.2856, 'N', 'PP63A', 'N', null, to_date('12-06-2019', 'dd-mm-yyyy'), to_date('31-12-3999', 'dd-mm-yyyy'), 'N', null, 'PD', null, null, null, null, null, null, 'N', null, null, null, 'PACKDATOS', 'N', null, 'N', null, '94', 'M', null, 'NNNN', 3.00000000, null, 'N', 90, null, '6GB por 3 días', null, null, 'Y', null, null, 'N', '6144')";

        if (this.packages.getPackagesVigente("P1GB1D") == null) {
            this.packages.insertPackages(insertPackages1);
        } else {
            System.out.println("[INFO] El packages P1GB1D ya se encuentra cargado");
        }

        if (this.packages.getPackagesVigente("T6GB3D") == null) {
            this.packages.insertPackages(insertPackages2);
        } else {
            System.out.println("[INFO] El packages T6GB3D ya se encuentra cargado");
        }

        if (flag) {
            System.out.println("|monto|rezon|tipoNegocio|idCombo|estadoVigente|idCombo|idPK|estadoVigente|idPK|featureAsociado|estadoVigente\n" +
                    "70|REVPDC|PP|CMB_TEST06|VIGENTE|CMB_TEST06|P1GB1D|VIGENTE|P1GB1D|SITiene|VIGENTE\n" +
                    "70|REVPDC|PP|CMB_TEST06|VIGENTE|CMB_TEST06|T6GB3D|VIGENTE|T6GB3D|SITiene|NOVIGENTE");
        } else {
            System.out.println("[WARNING] Fallo la carga de la Configuracion Beneficio");
        }

    }

    public void casuisticaConfigCMBTEST07() {
        //Registar AdditionalBenefits
        System.out.println("[INFO] Cargando Configuracion Beneficio");
        boolean flag = false;
        //Insertamos registro con la promocion de $40
        String insertAdditionalBenefits = "insert into Additional_Benefits (ADBE_AMOUNT, ADBE_CHANNEL, ADBE_BUSINESS, ADBE_PKT_ID, ADBE_START_DATE, ADBE_END_DATE, ADBE_DESCRIPTION, ADBE_BLACKLIST, ADBE_COBE_ID)\n" +
                "values (80.0000, 'REVPDC', 'PP', null, to_date('14-12-2020 10:37:59', 'dd-mm-yyyy hh24:mi:ss'), to_date('11-04-2029', 'dd-mm-yyyy'), 'DESCRIPCION DE PRUEBA', null, 'CMB_TEST07')";
        this.additionalBenefits.deletAdditionalBenefits("80", "REVPDC", "PP");
        flag = this.additionalBenefits.insertAdditionalBenefits(insertAdditionalBenefits);

        //ARegistramos los beneficios a otorgar, pero antes eliminamos los registros historicos.
        this.comboBenefits.deletComboBenefits("CMB_TEST07");

        String insertComboBenefits1 = "insert into combo_benefits (COBE_ID, COBE_PKT_ID, COBE_START_DATE, COBE_END_DATE)\n" +
                "values ('CMB_TEST07', 'P1GB1D', to_date('10-04-2022', 'dd-mm-yyyy'), to_date('11-04-2022', 'dd-mm-yyyy'))";

        String insertComboBenefits2 = "insert into combo_benefits (COBE_ID, COBE_PKT_ID, COBE_START_DATE, COBE_END_DATE)\n" +
                "values ('CMB_TEST07', 'T6GB3D', to_date('10-03-2022', 'dd-mm-yyyy'), to_date('10-04-2099', 'dd-mm-yyyy'))";

        flag = this.comboBenefits.insertComboBenefits(insertComboBenefits1);
        flag = this.comboBenefits.insertComboBenefits(insertComboBenefits2);


        String insertPackages1 = "insert into packages (PKT_ID, PKT_DESCRIPTION, PKT_CURRENT_PRICE, PKT_ADVANCE, PKT_INC_ID, PKT_CANCELED, PKT_FRT_ID, PKT_START_DATE, PKT_END_DATE, PKT_PRORRATION, PKT_SYR_ID, PKT_TYPE, PKT_LENGTH, PKT_DIGITAL_FLAG, PKT_SMS_INFOR_REQ_FLAG, PKT_AUDIOTEL_FLAG, PKT_INC_ID_LD, PKT_VALIDATION_FLAG, PKT_CHECKED_BILLING_FLAG, PKT_PREPAY_FLAG, PKT_CLASSING, PKT_SOURCE, PKT_CHANGING_TYPE, PKT_MIP_ID_PERMANENCY, PKT_MIP_ID_NEW, PKT_SECOND_ENT_FLAG, PKT_SECOND_ENT_ID, PKT_PFA_ID, PKT_CATEGORY, PKT_ISG_ID, PKT_FTR_ISI, PKT_LENGTH_DAYS, PKT_PARAMETRO, PKT_ENABLED_CLEARING, PKT_CAN_CICLO, PKT_BAJAR_FLAG, PKT_WEB_DESCRIPTION, PKT_VISIBILITY, PKT_HELP, PKT_WORK_AS_PREPAY, PKT_CHARGE_ID, PKT_EVENTUAL_CHARGE, PKT_CYCLIC, PKT_QUANTITY)\n" +
                "values ('P1GB1D', 'Pack Internet PP 1GB x 1 día', 63.3600, 'N', 'P1GBA', 'N', null, to_date('24-01-2017', 'dd-mm-yyyy'), to_date('31-12-3999', 'dd-mm-yyyy'), 'N', null, 'PD', null, null, null, null, null, null, 'N', null, null, null, 'PACKDATOS', 'N', null, 'N', null, '94', 'M', null, 'NNNN', 1.00000000, null, 'N', 90, null, '1GB por 1 día', 'N', null, 'Y', null, null, 'N', '1024')";

        String insertPackages2 = "insert into packages (PKT_ID, PKT_DESCRIPTION, PKT_CURRENT_PRICE, PKT_ADVANCE, PKT_INC_ID, PKT_CANCELED, PKT_FRT_ID, PKT_START_DATE, PKT_END_DATE, PKT_PRORRATION, PKT_SYR_ID, PKT_TYPE, PKT_LENGTH, PKT_DIGITAL_FLAG, PKT_SMS_INFOR_REQ_FLAG, PKT_AUDIOTEL_FLAG, PKT_INC_ID_LD, PKT_VALIDATION_FLAG, PKT_CHECKED_BILLING_FLAG, PKT_PREPAY_FLAG, PKT_CLASSING, PKT_SOURCE, PKT_CHANGING_TYPE, PKT_MIP_ID_PERMANENCY, PKT_MIP_ID_NEW, PKT_SECOND_ENT_FLAG, PKT_SECOND_ENT_ID, PKT_PFA_ID, PKT_CATEGORY, PKT_ISG_ID, PKT_FTR_ISI, PKT_LENGTH_DAYS, PKT_PARAMETRO, PKT_ENABLED_CLEARING, PKT_CAN_CICLO, PKT_BAJAR_FLAG, PKT_WEB_DESCRIPTION, PKT_VISIBILITY, PKT_HELP, PKT_WORK_AS_PREPAY, PKT_CHARGE_ID, PKT_EVENTUAL_CHARGE, PKT_CYCLIC, PKT_QUANTITY)\n" +
                "values ('T6GB3D', 'Pack Internet PP 6GB por 3 días', 269.2856, 'N', 'PP63A', 'N', null, to_date('12-06-2019', 'dd-mm-yyyy'), to_date('31-12-3999', 'dd-mm-yyyy'), 'N', null, 'PD', null, null, null, null, null, null, 'N', null, null, null, 'PACKDATOS', 'N', null, 'N', null, '94', 'M', null, 'NNNN', 3.00000000, null, 'N', 90, null, '6GB por 3 días', null, null, 'Y', null, null, 'N', '6144')";

        if (this.packages.getPackagesVigente("P1GB1D") == null) {
            this.packages.insertPackages(insertPackages1);
        } else {
            System.out.println("[INFO] El packages P1GB1D ya se encuentra cargado");
        }

        if (this.packages.getPackagesVigente("T6GB3D") == null) {
            this.packages.insertPackages(insertPackages2);
        } else {
            System.out.println("[INFO] El packages T6GB3D ya se encuentra cargado");
        }

        if (flag) {
            System.out.println("|monto|rezon|tipoNegocio|idCombo|estadoVigente|idCombo|idPK|estadoVigente|idPK|featureAsociado|estadoVigente\n" +
                    "80|REVPDC|PP|CMB_TEST07|VIGENTE|CMB_TEST07|P1GB1D|NOVIGENTE|P1GB1D|SITiene|VIGENTE\n" +
                    "80|REVPDC|PP|CMB_TEST07|VIGENTE|CMB_TEST07|T6GB3D|VIGENTE||T6GB3D|SITiene|NOVIGENTE");
        } else {
            System.out.println("[WARNING] Fallo la carga de la Configuracion Beneficio");
        }

    }

    public static void main(String[] args) {

        OfferBenefitsManager offerBenefitsManager = new OfferBenefitsManager("ghct", "auto_333", "TEST", "AR");
        offerBenefitsManager.casuisticaConfigCMBTEST01();
    }

    private String defineType(String pktType) {
        if (pktType.compareTo("PD") == 0) {
            return "DATA";
        } else if (pktType.compareTo("PF") == 0) {
            return "SERVICE";
        } else if (pktType.compareTo("PW") == 0) {
            return "WHATSAPP";
        } else {
            return "NoDefinition";
        }

    }


}
