package seedu.address.model.person.medical;

public class MedicalHistory {

    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String medicalHistoryDescription;
    public final Integer index;
    private String medicalItem;
    private String medicalPeriod;
    private Integer quantity;


    /**
     *
     * @param medicalHistoryDescription
     * @param index
     */
    public MedicalHistory(String medicalHistoryDescription, Integer index) {
        this.medicalHistoryDescription = medicalHistoryDescription;
        this.index = index;
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


    public Integer getIndex() {
        return index;
    }

}
