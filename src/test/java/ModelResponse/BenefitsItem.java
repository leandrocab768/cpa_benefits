package ModelResponse;

import java.util.List;

public class BenefitsItem{
	private String cobeId;
	private int pktIdTecno;
	private String reason;
	private double amount;
	private List<FeatureItem> feature;
	private String pktId;
	private String pktType;
	private String description;
	private String planId;
	private String type;

	public void setCobeId(String cobeId){
		this.cobeId = cobeId;
	}

	public String getCobeId(){
		return cobeId;
	}

	public void setPktIdTecno(int pktIdTecno){
		this.pktIdTecno = pktIdTecno;
	}

	public int getPktIdTecno(){
		return pktIdTecno;
	}

	public void setReason(String reason){
		this.reason = reason;
	}

	public String getReason(){
		return reason;
	}

	public void setAmount(double amount){
		this.amount = amount;
	}

	public double getAmount(){
		return amount;
	}

	public void setFeature(List<FeatureItem> feature){
		this.feature = feature;
	}

	public List<FeatureItem> getFeature(){
		return feature;
	}

	public void setPktId(String pktId){
		this.pktId = pktId;
	}

	public String getPktId(){
		return pktId;
	}

	public void setPktType(String pktType){
		this.pktType = pktType;
	}

	public String getPktType(){
		return pktType;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setPlanId(String planId){
		this.planId = planId;
	}

	public String getPlanId(){
		return planId;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}
}