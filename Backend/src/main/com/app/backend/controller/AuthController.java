package com.app.backend.controller;

import com.app.backend.dto.loginRequest;
import com.app.backend.dto.loginResponse;
import com.app.backend.model.user;
import com.app.backend.repository.UserRepository;
import com.com.app.backend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.cotext.security.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/login", consumes = "application/json", produces =  "application/Json")
    public ResponseEntity<?> login (@RequestBody LoginRequest loginRequest){
        try{
            Authentication authentication = authenticationManager.authentication(
                new 
                UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()));
            SecurityContextHolder.getContext().
            setAuthentication(authentication);

            String jwt = tokenProvider.generateToken(authentication);
            User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            return ResponseEntity.ok(new loginResponse(jwt,user));
        }catch(Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"Credenciales invalidas\"}");
        }
    }


}
