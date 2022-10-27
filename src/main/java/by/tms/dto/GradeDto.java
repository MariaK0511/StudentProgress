package by.tms.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GradeDto {
    private long id;
    @NotNull
    private long grade;
}
