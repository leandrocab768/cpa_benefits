#language: es

Característica: SP-RECHARGE-OFFERS


  Esquema del escenario: SP-RECHARGE-OFFERS
    Dado que esta  configurada la especificacion de solicitud con los headers:<headerName1>,<headerValue1>,<headerName2>,<headerValue2>,<headerName3>,<headerValue3>
    Y los datos: <payingBillNumber>,<receivingBillNumber>,<rechargeOfferType>,<paymentMethod>

    Ejemplos:

















































  Esquema del escenario: Respuesta exitosa con beneficios disponibles para recarga entre dos lineas <lineaPaying> <lineaReceiving> a traves del PDC con el servicio SP-RECHARGE-OFFERS
    Dado que tengo dos lineas <lineaPaying>,<lineaReceiving> habilitadas para operar
    Y Habiendo beneficios disponibles para las recargas por PDC para <rason>
    Cuando el PDC solicita informacion sobre montos y beneficios para una linea <lineaPaying> que desea realizar una recarga a otra linea <lineaReceiving> a traves del PDC
    Entonces el servicio retorna los montos de recarga a tarjeta y beneficios disponibles para <lineaReceiving>

    Ejemplos:
      | lineaPaying | lineaPaying | rason |


  Esquema del escenario: Respuesta exitosa sin beneficios disponibles para recarga de una linea <lineaPaying> a traves del PDC con el servicio SP-RECHARGE-OFFERS
    Dado una linea <lineaPaying> habilitada para operar
    Y No hay beneficios disponibles para las recargas por PDC para <rason>
    Cuando el PDC solicita informacion sobre montos y beneficios para una linea <lineaPaying> que desea realizarse una recarga a traves del PDC
    Entonces el servicio retorna los montos de recarga a tarjeta disponibles

    Ejemplos:
      | lineaPaying | rason |


  Esquema del escenario:Respuesta exitosa sin beneficios disponibles para recarga entre dos lineas <lineaPaying> <lineaReceiving> a traves del PDC con el servicio SP-RECHARGE-OFFERS
    Dado que tengo dos lineas <lineaPaying>,<lineaReceiving> habilitadas para operar
    Y No hay beneficios disponibles para las recargas por PDC para <rason>
    Cuando el PDC solicita informacion sobre montos y beneficios para una linea <lineaPaying> que desea realizar una recarga a otra linea <lineaReceiving> a traves del PDC
    Entonces el servicio retorna los montos de recarga a tarjeta disponibles

    Ejemplos:
      | lineaPaying | lineaPaying | rason |