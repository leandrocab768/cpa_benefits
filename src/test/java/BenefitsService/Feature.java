package BenefitsService;

public class Feature {
    private String id,type,calification,description,webDescription;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getCalification() {
        return calification;
    }

    public String getDescription() {
        return description;
    }

    public String getWebDescription() {
        return webDescription;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", calification='" + calification + '\'' +
                ", description='" + description + '\'' +
                ", webDescription='" + webDescription + '\'' +
                '}';
    }
}
