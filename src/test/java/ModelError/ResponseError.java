package ModelError;

import java.util.List;

public class ResponseError{
	private Data data;
	private List<ErrorsItem> errors;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setErrors(List<ErrorsItem> errors){
		this.errors = errors;
	}

	public List<ErrorsItem> getErrors(){
		return errors;
	}
}