package ModelResponse;

import java.util.List;

public class Data{
	private List<BenefitsItem> benefits;

	public void setBenefits(List<BenefitsItem> benefits){
		this.benefits = benefits;
	}

	public List<BenefitsItem> getBenefits(){
		return benefits;
	}
}