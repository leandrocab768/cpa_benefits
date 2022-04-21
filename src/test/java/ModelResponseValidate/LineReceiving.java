package ModelResponseValidate;

public class LineReceiving{
	private Object reason;
	private String cellularNumber;
	private String planId;
	private String billNumber;
	private String businessType;
	private String category;

	public void setReason(Object reason){
		this.reason = reason;
	}

	public Object getReason(){
		return reason;
	}

	public void setCellularNumber(String cellularNumber){
		this.cellularNumber = cellularNumber;
	}

	public String getCellularNumber(){
		return cellularNumber;
	}

	public void setPlanId(String planId){
		this.planId = planId;
	}

	public String getPlanId(){
		return planId;
	}

	public void setBillNumber(String billNumber){
		this.billNumber = billNumber;
	}

	public String getBillNumber(){
		return billNumber;
	}

	public void setBusinessType(String businessType){
		this.businessType = businessType;
	}

	public String getBusinessType(){
		return businessType;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}
}
