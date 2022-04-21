package ModelResponseValidate;

public class ValidateItem{
	private boolean execute;
	private String validation;

	public void setExecute(boolean execute){
		this.execute = execute;
	}

	public boolean isExecute(){
		return execute;
	}

	public void setValidation(String validation){
		this.validation = validation;
	}

	public String getValidation(){
		return validation;
	}
}
