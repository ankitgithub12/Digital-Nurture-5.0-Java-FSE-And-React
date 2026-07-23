package com.cognizant.springlearn.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@RestController
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        LOGGER.info("START: authenticate");
        
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            LOGGER.warn("Missing or invalid Authorization header");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Missing or invalid Authorization header"));
        }

        try {
            String base64Credentials = authHeader.substring(6).trim();
            byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(decodedBytes, StandardCharsets.UTF_8);
            
            String[] values = credentials.split(":", 2);
            if (values.length != 2) {
                LOGGER.warn("Invalid credentials format in Authorization header");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Invalid credentials format"));
            }
            
            String username = values[0];
            String password = values[1];
            
            LOGGER.debug("Decoded credentials - Username: {}", username);
            
            // For this hands-on exercise, we accept the credentials and generate a token.
            // If needed, basic validation like checking username/password are not empty can be performed.
            if (username.isEmpty() || password.isEmpty()) {
                LOGGER.warn("Empty username or password provided");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Username and password cannot be empty"));
            }
            
            // Generate JWT token
            String token = generateJwtToken(username);
            
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            
            LOGGER.info("END: authenticate - JWT generated successfully");
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            LOGGER.error("Base64 decoding failed for credentials", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Invalid Base64 encoding for credentials"));
        } catch (Exception e) {
            LOGGER.error("Error during authentication process", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Authentication error"));
        }
    }

    private String generateJwtToken(String username) {
        // Token expires in 20 minutes (matches the 20-minute validity in the prompt's example: iat and exp difference = 1200 seconds)
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 20 * 60 * 1000);
        
        // Using HMAC256 with a secret key
        Algorithm algorithm = Algorithm.HMAC256("supersecretkey");
        
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }
}
