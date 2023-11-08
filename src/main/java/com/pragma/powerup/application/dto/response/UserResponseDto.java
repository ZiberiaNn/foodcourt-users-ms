package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.domain.model.RoleModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;
@Getter
@Setter
public class UserResponseDto {
    private Long id;
    @Schema(example = "Example Name", required = true)
    private String name;
    @Schema(example = "Example LastName")
    private String lastName;
    @Schema(example = "123456789")
    private Integer  identityDocument;
    @Schema(example = "+573132222222")
    private String phone;
    @Schema(example = "2000-01-20T01:09:18.916Z")
    private Date birthDate;
    @Schema(example = "example@example.com")
    private String email;
    @Schema(example = "[\n" +
            "        {\n" +
            "            \"id\": 0,\n" +
            "            \"name\": \"EXAMPLE\"\n" +
            "        }\n" +
            "    ]")
    private Collection<RoleModel> roles;
}
