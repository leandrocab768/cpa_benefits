package ModelError;

public class Extensions{
	private String exceptionType;
	private String code;
	private String message;
	private String classification;

	public void setExceptionType(String exceptionType){
		this.exceptionType = exceptionType;
	}

	public String getExceptionType(){
		return exceptionType;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setClassification(String classification){
		this.classification = classification;
	}

	public String getClassification(){
		return classification;
	}
}
