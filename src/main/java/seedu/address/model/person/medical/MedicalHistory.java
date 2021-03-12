package seedu.address.model.person.medical;

public class MedicalHistory {

    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String medicalHistoryDescription;
    private String medicalItem;
    private String medicalPeriod;
    private Integer quantity;

    public MedicalHistory(String medicalHistoryDescription) {
        this.medicalHistoryDescription = medicalHistoryDescription;
    }

    public String getMedicalHistoryDescription() {
        return medicalHistoryDescription;
    }

    public static boolean isValidMedicalHistoryDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    public String getMedicalItem() {
        return medicalItem;
    }

    public void setMedicalItem(String medicalItem) {
        this.medicalItem = medicalItem;
    }

    public String getMedicalPeriod() {
        return medicalPeriod;
    }

    public void setMedicalPeriod(String medicalPeriod) {
        this.medicalPeriod = medicalPeriod;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
