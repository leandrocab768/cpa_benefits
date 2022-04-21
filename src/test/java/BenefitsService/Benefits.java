package BenefitsService;

import Services.TestBenefits;

import java.util.List;

public class Benefits  {
    private String amount,pktId,pktIdTecno,pktType,description,type,reason,planId,cobeId;
    private   List<Feature> feature;

    public Benefits(String amount, String pktId, String pktIdTecno,String pktType,String description,String type,String reason,String planId,String cobeId, List<Feature> feature){
      this.amount = amount;
      this.pktId = pktId;
      this.pktIdTecno = pktIdTecno;
      this.pktType = pktType;
      this.description = description;
      this.type = type;
      this.reason = reason;
      this.planId = planId;
      this.cobeId = cobeId;
      this.feature = feature;
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
                ", benefits=" + feature +
                '}';
    }

    public String getAmount() {
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

    public List<Feature> getFeature() {
        return feature;
    }
}
