package com.roger.department.pojo;

import io.swagger.v3.oas.annotations.media.Schema;

public class Department {

    @Schema(description = "The id of the department", example = "1")
    private Integer id;

    @Schema(description = "The name of the department", example = "software")
    private String name;

    @Schema(description = "The description of the department", example = "this is a software department")
    private String description;

    public Department() {
    }

    public Department(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
