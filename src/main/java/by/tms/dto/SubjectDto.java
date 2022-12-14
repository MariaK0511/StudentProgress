package by.tms.dto;

import javax.validation.constraints.NotEmpty;

public class SubjectDto {


    public SubjectDto() {
    }
    @NotEmpty(message = "Name should not be empty")
    private String name;
    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
