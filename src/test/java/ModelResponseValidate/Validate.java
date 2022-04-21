package ModelResponseValidate;

import java.util.List;

public class Validate{
	private LineReceiving lineReceiving;
	private List<ValidateItem> validate;

	public void setLineReceiving(LineReceiving lineReceiving){
		this.lineReceiving = lineReceiving;
	}

	public LineReceiving getLineReceiving(){
		return lineReceiving;
	}

	public void setValidate(List<ValidateItem> validate){
		this.validate = validate;
	}

	public List<ValidateItem> getValidatelist(){
		return validate;
	}
}