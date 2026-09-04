package com.aaronlifeapp.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class AuthController {
    private final SettingsRepository settingsRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(SettingsRepository settingsRepository, PasswordEncoder passwordEncoder) {
        this.settingsRepository = settingsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        Settings settings = settingsRepository.findAll().get(0);

        if (!passwordEncoder.matches(request.getPin(), settings.getPinHash())) {
            throw new RuntimeException("Invalid PIN");
        }

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken("user", null, List.of());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        new HttpSessionSecurityContextRepository().saveContext(context, httpRequest, httpResponse);

        return "Logged in";
    }

    public static class LoginRequest {
        private String pin;
        public String getPin() { return pin; }
        public void setPin(String pin) { this.pin = pin; }
    }
}