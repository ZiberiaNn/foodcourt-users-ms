package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.enums.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
public class UserRequestDto {
    @Schema(example = "Example Name", required = true)
    private String name;
    @Schema(example = "Example LastName", required = true)
    private String lastName;
    @Schema(example = "123456789", required = true)
    private Integer  identityDocument;
    @Schema(example = "+573132222222", required = true)
    private String phone;
    @Schema(example = "2000-01-20T01:09:18.916Z", required = true)
    private Date birthDate;
    @Schema(example = "example@example.com", required = true)
    private String email;
    @Schema(example = "example", required = true)
    private String password;
    private Collection<RoleEnum> roles;
}