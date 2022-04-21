package com.claro.sp.model;

public class ResponseOfferService {
    String responseCode;
    String responseMessage;
    String client;
    String offerServices;

    public ResponseOfferService(String responseCode, String responseMessage, String client, String offerServices) {
        this.responseCode    = responseCode;
        this.responseMessage = responseMessage;
        this.client          = client;
        this.offerServices   = offerServices;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getOfferServices() {
        return offerServices;
    }

    public void setOfferServices(String offerServices) {
        this.offerServices = offerServices;
    }
}
