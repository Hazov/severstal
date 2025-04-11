package ru.hazov.booksdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hazov.booksdemo.dto.auth.login.LoginRequest;
import ru.hazov.booksdemo.dto.auth.login.LoginResponse;
import ru.hazov.booksdemo.entity.Person;
import ru.hazov.booksdemo.service.auth.AuthService;
import ru.hazov.booksdemo.service.auth.TokenService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final TokenService tokenService;

    public AuthController(AuthService authService, TokenService tokenService) {
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest request) {
        Person currentPerson = authService.authenticate(request.getEmail(), request.getPassword());
        String jwtToken = tokenService.generateToken(currentPerson);
        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        response.setExpiresIn(tokenService.getExpirationTime());
        return ResponseEntity.ok(response);
    }
}
