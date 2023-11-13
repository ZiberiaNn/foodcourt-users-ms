package com.pragma.powerup.domain.model.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN("ADMIN"),
    OWNER("OWNER"),
    EMPLOYEE("EMPLOYEE"),
    CLIENT("CLIENT");
    private final String name;
    RoleEnum(String name) {
        this.name = name;
    }
}