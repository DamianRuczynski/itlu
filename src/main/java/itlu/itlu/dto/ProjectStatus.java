package itlu.itlu.dto;

public enum ProjectStatus {

    IN_PREPARATION(0, "In Preparation"),
    PREPARED(1, "Prepared"),
    SIGNED(2, "Signed"),
    ACTIVE(3, "Active"),
    FINISHED(4, "Finished"),
    TERMINATED(5, "Terminated"),
    CANCELED(6, "Canceled");

    private Integer value;
    private String description;

    ProjectStatus(Integer value, String description) {
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
