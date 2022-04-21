package OffertManager;

import java.util.ArrayList;
import java.util.List;

public class Benefits {
    private Float amount;
    private String pktId;
    private String pktIdTecno;
    private String pktType;
    private String description;
    private String type;
    private String reason;
    private String planId;
    private String cobeId;
    private List<Features> listFeatures;

    public void addFeaturesList(Features features) {
        listFeatures.add(features);
    }

    public Benefits(Float amount, String pktId, String pktIdTecno, String pktType, String description, String type, String reason, String planId, String cobeId) {
        this.amount = amount;
        this.pktId = pktId;
        this.pktIdTecno = pktIdTecno;
        this.pktType = pktType;
        this.description = description;
        this.type = type;
        this.reason = reason;
        this.planId = planId;
        this.cobeId = cobeId;
        listFeatures = new ArrayList<Features>();
    }

    public Benefits(){
    }

    public boolean comparePktId(String pktId){
        return this.pktId.equals(pktId) ;
    }

    public boolean comparePktIdTecno(String pktIdTecno){
        return  this.pktIdTecno.equals(pktIdTecno);
    }

    public  boolean compareDescription(String description){
        return this.description.equals(description);
    }


    ///////

    public Float getAmount() {
        return amount;
    }

    public String getPktId() {
        return pktId;
    }

    public String getPktIdTecno() {
        return pktIdTecno;
    }

    public String getPktType() {
        return pktType;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getReason() {
        return reason;
    }

    public String getPlanId() {
        return planId;
    }

    public String getCobeId() {
        return cobeId;
    }

    public List<Features> getListFeatures() {
        return listFeatures;
    }

    @Override
    public String toString() {
        return "Benefits{" +
                "amount='" + amount + '\'' +
                ", pktId='" + pktId + '\'' +
                ", pktIdTecno='" + pktIdTecno + '\'' +
                ", pktType='" + pktType + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", reason='" + reason + '\'' +
                ", planId='" + planId + '\'' +
                ", cobeId='" + cobeId + '\'' +
                ", listFeatures=" + listFeatures +
                '}';
    }
}
