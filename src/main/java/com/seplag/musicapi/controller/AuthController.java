package com.seplag.musicapi.controller;

import com.seplag.musicapi.dto.AuthRequestDTO;
import com.seplag.musicapi.dto.AuthResponseDTO;
import com.seplag.musicapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO authRequestDTO) {
        String token = jwtService.gerarToken(authRequestDTO.username());
        return new AuthResponseDTO(token);
    }
}

