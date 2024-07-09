package com.roger.department.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Schema(description = "The id of the department", example = "1")
    private Integer id;

    @Schema(description = "The name of the department", example = "software")
    private String name;

    @Schema(description = "The description of the department", example = "this is a software department")
    private String description;
}
