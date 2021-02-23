package itlu.itlu.dto;

public enum MeetsStatus {

    PLANNED(0, "Planned"),
    FINISHED(1, "Finished"),
    CANCELED(2, "Canceled");

    private Integer value;
    private String description;

    MeetsStatus(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
