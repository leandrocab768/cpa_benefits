package BenefitsService;

import java.util.List;

public class Data {
      private List<Benefits> benefits;

    public List<Benefits> getBenefits() {
        return benefits;
    }

    @Override
    public String toString() {
        return "Data{" +
                "benefits=" + benefits +
                '}';
    }
}
