#language: es

Característica: Beneficios asociados al monto
  Como cliente quiero obtener los beneficios asociados a los montos de recargas.


  Esquema del escenario: Validacion de respuesta de status del servicio SP-RECHARGE-BENEFIT-SERVICE
    Dado que esta  configurada la especificacion de solicitud con los headers:<headerName1>,<headerValue1>,<headerName2>,<headerValue2>,<headerName3>,<headerValue3>
    Y la estructura de entrada:<amounts>,<businessType>,<planId>,<reason>,<pktType>, salida:<amount>,<pktId>,<pktIdTecno>,<pktTypeOut>,<description>,<type>,<reasonOut>,<planIdOut>,<cobeId>
    Cuando ejecuto la solicitud
    Entonces Se obtiene el <statusCode>


    @PSP-8039
    Ejemplos:
      | statusCode | amounts                                   | businessType | planId | reason | pktType | amount | pktId | pktIdTecno | pktTypeOut | description | type | reasonOut | planIdOut | cobeId | headerName1 | headerValue1   | headerName2 | headerValue2   | headerName3  | headerValue3     |
      | 400        | 800                                       | PP           | PPP18  | REVPDC | PD      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId |             |                | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 400        | 800                                       | PP           | PPP18  | REVPDC | PW      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId |             | pruebaError    | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 404        | 700                                       | CR           | PPP18  | REVPDC | PF      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | pruebaError  | pruebaError      |
      | 200        | 50 100 200  300  400  500 600 700 800 900 | PP           | PPP18  | REVPDC | PD      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 50 100 200  300  400  500 600 700 800 900 | PP           | PPP18  | REVPDC | PW      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 50 100 200  300  400  500 600 700 800 900 | PP           | PPP18  | REVPDC | PF      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 50 100 200  300  400                      | CR           | PPP18  | REVPDC | PD      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 50 100 200  300  400                      | CR           | PPP18  | REVPDC | PW      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 50 100 200  300  400                      | CR           | PPP18  | REVPDC | PF      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 50 100 200                                | PP           | PPP18  | REVPDC | PD      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 50 100 200                                | PP           | PPP18  | REVPDC | PW      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 50 100 200                                | PP           | PPP18  | REVPDC | PF      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 50 100 200                                | CR           | PPP18  | REVPDC | PD      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 50                                        | CR           | PPP18  | REVPDC | PW      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 100                                       | CR           | PPP18  | REVPDC | PF      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 200                                       | PP           | PPP18  | REVPDC | PD      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 300                                       | PP           | PPP18  | REVPDC | PW      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 400                                       | PP           | PPP18  | REVPDC | PF      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 500                                       | CR           | PPP18  | REVPDC | PD      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 600                                       | CR           | PPP18  | REVPDC | PW      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |
      | 200        | 700                                       | CR           | PPP18  | REVPDC | PF      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |


  Esquema del escenario: Validacion mensaje de error, respuesta del servicio SP-RECHARGE-BENEFIT-SERVICE
    Dado que esta  configurada la especificacion de solicitud con los headers:<headerName1>,<headerValue1>,<headerName2>,<headerValue2>,<headerName3>,<headerValue3>
    Y la estructura de entrada:<amounts>,<businessType>,<planId>,<reason>,<pktType>, salida:<amount>,<pktId>,<pktIdTecno>,<pktTypeOut>,<description>,<type>,<reasonOut>,<planIdOut>,<cobeId>
    Cuando ejecuto la solicitud
    Entonces Se obtiene el <statusCode>
    Y Se verifica el mensaje de salida <mensaje>

    @PSP-8041
    Ejemplos:
      | statusCode | amounts | businessType | planId | reason | pktType | amount | pktId | pktIdTecno | pktTypeOut | description | type | reasonOut | planIdOut | cobeId | headerName1 | headerValue1   | headerName2 | headerValue2   | headerName3  | headerValue3     | mensaje                                               |
      | 200        | 800     | CR           | PPP18  | REVPDC | PD      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | pruebaError | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json | Error el campo Session-Id es nulo o vacio             |
      | 200        | 800     | CR           | PPP18  | REVPDC | PD      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | pruebaError | Channel Prueba | Content-Type | application/json | Error el campo Channel-Id es nulo o vacio             |
      | 200        | 800     | CR           | PPP18  | REVPDC | PD      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | pruebaError | Session Prueba | pruebaError | Channel Prueba | Content-Type | application/json | Error el campo Session-Id, Channel-Id es nulo o vacio |
      | 404        | 700     | CR           | PPP18  | REVPDC | PF      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | pruebaError  | pruebaError      | Not Found                                             |


  Esquema del escenario: Validacion exitosa de la estructura response, del servicio SP-RECHARGE-BENEFIT-SERVICE
    Dado que esta  configurada la especificacion de solicitud con los headers:<headerName1>,<headerValue1>,<headerName2>,<headerValue2>,<headerName3>,<headerValue3>
    Y la estructura de entrada:<amounts>,<businessType>,<planId>,<reason>,<pktType>, salida:<amount>,<pktId>,<pktIdTecno>,<pktTypeOut>,<description>,<type>,<reasonOut>,<planIdOut>,<cobeId>
    Cuando ejecuto la solicitud
    Entonces Se obtiene el <statusCode>
    Y Se valida la estructura de la respuesta

    @PSP-8050
    Ejemplos:
      | statusCode | amounts | businessType | planId | reason | pktType | amount | pktId | pktIdTecno | pktTypeOut | description | type | reasonOut | planIdOut | cobeId | headerName1 | headerValue1   | headerName2 | headerValue2   | headerName3  | headerValue3     |
      | 200        | 10      | PP           | PPP18  | REVPDC | PD      | amount | pktId | pktIdTecno | pktType    | description | type | reason    |           | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |


  Esquema del escenario: Validacion exitosa del contenido del response, del servicio SP-RECHARGE-BENEFIT-SERVICE
    Dado que esta  configurada la especificacion de solicitud con los headers:<headerName1>,<headerValue1>,<headerName2>,<headerValue2>,<headerName3>,<headerValue3>
    Y la estructura de entrada:<amounts>,<businessType>,<planId>,<reason>,<pktType>, salida:<amount>,<pktId>,<pktIdTecno>,<pktTypeOut>,<description>,<type>,<reasonOut>,<planIdOut>,<cobeId>
    Cuando ejecuto la solicitud
    Entonces Se obtiene el <statusCode>
    Y Se valida la respuesta obtenida:<amounts>,<reason>,<businessType>,<pktType>

    @PSP-8051
    Ejemplos:
      | statusCode | amounts | businessType | planId | reason | pktType | amount | pktId | pktIdTecno | pktTypeOut | description | type | reasonOut | planIdOut | cobeId | headerName1 | headerValue1   | headerName2 | headerValue2   | headerName3  | headerValue3     |
      | 200        | 800     | PP           | PPP18  | REVPDC | PD      | amount | pktId | pktIdTecno | pktType    | description | type | reason    |           | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |


  Esquema del escenario: Validacion de vigencias para Paquetes y Beneficios, del servicio SP-RECHARGE-BENEFIT-SERVICE
    Dado que esta  configurada la especificacion de solicitud con los headers:<headerName1>,<headerValue1>,<headerName2>,<headerValue2>,<headerName3>,<headerValue3>
    Y Existe el combo <beneficio>
    Y la vigencia de Packequetes otorgados como beneficios Pack 1 <paquete> con <paqueteFechaInicio>,<paqueteFechaFin>
    Y la vigencia de Monto con beneficios <amounts>,<reason> con <montoBeneficioFechaInicio>,<montoBeneficioFechaFin>
    Y la vigencia de Beneficios <beneficio>,<paquete> con <beneficioFechaInicio>,<beneficioFechaFin>
    Y la estructura de entrada:<amounts>,<businessType>,<planId>,<reason>,<pktType>, salida:<amount>,<pktId>,<pktIdTecno>,<pktTypeOut>,<description>,<type>,<reasonOut>,<planIdOut>,<cobeId>
    Cuando ejecuto la solicitud
    Entonces Se obtiene el <statusCode>
    Y Se valida la respuesta obtenida:<amounts>,<reason>,<businessType>,<pktType>


    @PSP-8191
    Ejemplos:
      | statusCode | paquete | beneficio  | paqueteFechaInicio | paqueteFechaFin    | montoBeneficioFechaInicio | montoBeneficioFechaFin | beneficioFechaInicio | beneficioFechaFin  | amounts | businessType | planId | reason | pktType | amount | pktId | pktIdTecno | pktTypeOut | description | type | reasonOut | planIdOut | cobeId | headerName1 | headerValue1   | headerName2 | headerValue2   | headerName3  | headerValue3     |
      | 200        | P1GB1D  | CMB_TEST01 | 8/4/2022 00:00:00  | 21/4/2999 23:59:59 | 8/4/2022 00:00:00         | 13/4/2999 23:59:59     | 8/4/2022 00:00:00    | 13/4/2999 23:59:59 | 10      | PP           | PPP18  | REVPDC | PD      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |


  Esquema del escenario: Validacion de vigencias de Paquetes asociados a Beneficios, del servicio SP-RECHARGE-BENEFIT-SERVICE
    Dado que esta  configurada la especificacion de solicitud con los headers:<headerName1>,<headerValue1>,<headerName2>,<headerValue2>,<headerName3>,<headerValue3>
    Y Realizo la la insercion de los datos de prueba: <paquete1>,<paquete2>,<beneficio1>,<beneficio2>,<amounts>,<reason>
    Y Vigencias de Packequetes otorgados como beneficios Pack 1 <paquete1> con <paqueteFechaInicio1>,<paqueteFechaFin1> y Pack 2 <paquete2> con <paqueteFechaInicio2>,<paqueteFechaFin2>
    Y la vigencia de Monto con beneficios <amounts>,<reason> con <montoBeneficioFechaInicio>,<montoBeneficioFechaFin>
    Y Vigencias de Beneficios asociados a los Paquetes <beneficio1>,<paquete1> con <beneficioFechaInicio>,<beneficioFechaFin> y <beneficio2>,<paquete2> con <beneficioFechaInicio2>,<beneficioFechaFin2>
    Y la estructura de entrada:<amounts>,<businessType>,<planId>,<reason>,<pktType>, salida:<amount>,<pktId>,<pktIdTecno>,<pktTypeOut>,<description>,<type>,<reasonOut>,<planIdOut>,<cobeId>
    Cuando ejecuto la solicitud
    Entonces Se obtiene el <statusCode>
    Y Se valida la respuesta obtenida:<amounts>,<reason>,<businessType>,<pktType>


    @PSP-8192
    Ejemplos:
      | statusCode | paquete1 | paquete2 | beneficio1 | beneficio2 | paqueteFechaInicio1 | paqueteFechaFin1 | paqueteFechaInicio2 | paqueteFechaFin2 | montoBeneficioFechaInicio | montoBeneficioFechaFin | beneficioFechaInicio | beneficioFechaFin  | beneficioFechaInicio2 | beneficioFechaFin2 | amounts | businessType | planId | reason | pktType | amount | pktId | pktIdTecno | pktTypeOut | description | type | reasonOut | planIdOut | cobeId | headerName1 | headerValue1   | headerName2 | headerValue2   | headerName3  | headerValue3     |
      | 200        | P2GB15   |          | CMB001     |            | 8/4/2022 00:00:00   |                  | 13/4/2022 23:59:59  |                  | 8/4/2022 00:00:00         | 13/4/2022 23:59:59     | 8/4/2022 00:00:00    | 13/4/2022 23:59:59 |                       |                    | 10      | PP           | PPP18  | REVPDC | PD      | amount | pktId | pktIdTecno | pktType    | description | type | reason    | planId    | cobeId | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |






    #-------------------------------------------------------------- FEATURES ASOCIADOS A LOS PAQUETES -------------------------------------------------------------------------------------------------------------------------------#


#COMENTARIO DE PRUEBA EN BITBUCKET
  Esquema del escenario: Validacion de respuesta de status para obtener los feature asociados a los paquetes del servicio SP-RECHARGE-BENEFIT-SERVICE
    Dado que esta  configurada la especificacion de solicitud con los headers:<headerName1>,<headerValue1>,<headerName2>,<headerValue2>,<headerName3>,<headerValue3>
    Y la entrada:<pktId> salida:<id>,<type>,<description>,<webDescription>
    Cuando ejecuto la solicitud
    Entonces Se obtiene el <statusCode>


    @PSP-8193
    Ejemplos:
      | statusCode | pktId  | id | type | description | webDescription | headerName1 | headerValue1   | headerName2 | headerValue2   | headerName3  | headerValue3     |
      | 200        | P2GB15 | id | type | description | webDescription | Session-Id  | Session Prueba | Channel-Id  | Channel Prueba | Content-Type | application/json |