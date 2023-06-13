package com.project.paswordapi.Controller;

import com.project.paswordapi.Controller.Response.CreateUserResponse;
import com.project.paswordapi.Exception.UserNotFoundException;
import com.project.paswordapi.Service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody CreateUserResponse userResponse) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userResponse.getUsername(), userResponse.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userResponse.getUsername());
        } else {
            throw new UserNotFoundException("invalid user request");
        }
    }
}
