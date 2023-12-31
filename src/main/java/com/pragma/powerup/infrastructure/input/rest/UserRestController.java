package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserLoginDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.AuthTokenResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;
    @PostMapping("/auth")
    public ResponseEntity<AuthTokenResponseDto> generateToken(@RequestBody UserLoginDto userLoginDto) throws AuthenticationException {
        return ResponseEntity.ok(userHandler.authenticateUser(userLoginDto));
    }
    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @PreAuthorize("hasAnyRole(" +
            "T(com.pragma.powerup.domain.model.enums.RoleEnum).ADMIN.toString()," +
            "T(com.pragma.powerup.domain.model.enums.RoleEnum).OWNER.toString()," +
            "T(com.pragma.powerup.domain.model.enums.RoleEnum).CLIENT.toString()" +
            ")")
    @PostMapping("/")
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userHandler.saveUser(userRequestDto), HttpStatus.CREATED);
    }
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userHandler.getAllUsers());
    }
    @Operation(summary = "Get one users by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@Schema(example = "1")
                                                             @PathVariable Long userId) {
        return ResponseEntity.ok(userHandler.getUserById(userId));
    }
    @Operation(summary = "Get one users by user identity number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/by-identity-number/{identityNumber}")
    public ResponseEntity<UserResponseDto> getUserByIdentityNumber(@Schema(example = "123456789")
                                                   @PathVariable Integer identityNumber) {
        return ResponseEntity.ok(userHandler.getUserByIdentityNumber(identityNumber));
    }
    @Operation(summary = "Get one users by user email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/by-email/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@Schema(example = "example@example.com")
                                                                   @PathVariable String email) {
        return ResponseEntity.ok(userHandler.getUserByEmail(email));
    }
}