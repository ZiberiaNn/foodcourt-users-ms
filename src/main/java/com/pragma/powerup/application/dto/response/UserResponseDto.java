package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.domain.model.RoleModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;
@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String lastName;
    private Integer  identityDocument;
    private String phone;
    private Date birthDate;
    private String email;
    private String password;
    private Collection<RoleModel> roles;
}
