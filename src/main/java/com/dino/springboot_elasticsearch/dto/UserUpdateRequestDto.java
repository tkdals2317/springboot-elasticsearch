package com.dino.springboot_elasticsearch.dto;

public class UserUpdateRequestDto {
    private String name;
    private Long age;
    private Boolean isActive;

    public String getName() {
        return name;
    }

    public Long getAge() {
        return age;
    }

    public Boolean getIsActive() {
        return isActive;
    }
}
