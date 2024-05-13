package org.enriqueboronat.pruebahexagonal.config.security.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.enriqueboronat.pruebahexagonal.config.security.dtos.*;
import org.enriqueboronat.pruebahexagonal.config.security.models.RolEnum;
import org.enriqueboronat.pruebahexagonal.config.security.models.UserSecurity;
import org.enriqueboronat.pruebahexagonal.config.security.services.AuthenticationService;
import org.enriqueboronat.pruebahexagonal.config.security.services.UserSecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "auth", description = "auth Endpoints")
public class AuthController {

    private final AuthenticationService authenticationService;

    private final UserSecurityService userSecurityEntityService;

    private final AuthenticationManager authenticationManager;

    @Operation(summary = "SignUp")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Token",
            content = @Content(schema = @Schema(implementation = JwtAuthenticationResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "other error (validations errors)",
            content = @Content),
    })
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@Valid @RequestBody SignUpRequest signUpRequest){
        authenticationService.signUp(signUpRequest);

        LogInRequest loginRequest = new LogInRequest();
        loginRequest.setEmail(signUpRequest.getEmail());
        loginRequest.setPassword(signUpRequest.getPassword());

        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

    @Operation(summary = "Login")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Token",
            content = @Content(schema = @Schema(implementation = JwtAuthenticationResponse.class))),
        @ApiResponse(
            responseCode = "500",
            description = "other error (Bad Credentials)",
            content = @Content),
    })
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LogInRequest loginRequest){
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

    @Operation(summary = "Update password")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Password updated",
            content = @Content),
        @ApiResponse(
            responseCode = "500",
            description = "other error",
            content = @Content),
        @ApiResponse(
            responseCode = "403",
            description = "Access Denied",
            content = @Content),
    })
    @PreAuthorize("#id == authentication.principal.id")
    @PatchMapping("/{id}/updatePassword")
    public ResponseEntity<String> UpdatePassword(@PathVariable UUID id, @RequestBody PasswordDto passwordDto){
        UserSecurity user = userSecurityEntityService.findUserEntityById(id);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), passwordDto.getOldPassword()));
        return ResponseEntity.ok(authenticationService.updatePassword(passwordDto.getPassword(), id));
    }

    @Operation(summary = "Update rol")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Rol updated",
            content = @Content),
        @ApiResponse(
            responseCode = "500",
            description = "other error",
            content = @Content),
        @ApiResponse(
            responseCode = "403",
            description = "Access Denied",
            content = @Content),
    })

    @PreAuthorize("hasRole('ADMIN')") 
    @PatchMapping("/{id}/updateRol")
    public ResponseEntity<String> UpdatePassword(@PathVariable UUID id, @RequestBody RolDto rol){
        RolEnum role= RolEnum.valueOf(rol.getRol());
        return ResponseEntity.ok(userSecurityEntityService.updateRole(id, role));
    }

}
