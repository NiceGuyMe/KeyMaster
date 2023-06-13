package com.project.paswordapi.Controller;

import com.project.paswordapi.Controller.Mapper.PasswordMapper;
import com.project.paswordapi.Controller.Response.CreatePasswordResponses;
import com.project.paswordapi.Controller.Response.PasswordResponse;
import com.project.paswordapi.Entity.PasswordEntity;
import com.project.paswordapi.Entity.UserEntity;
import com.project.paswordapi.Repository.UserRepository;
import com.project.paswordapi.Service.JwtService;
import com.project.paswordapi.Service.PasswordService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@SuppressWarnings("unused")
public class PasswordController {
    private final PasswordService passwordService;

    private final PasswordMapper passwordMapper;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    @GetMapping("/generate")
    public String createPassword(@RequestParam(value = "password_size") Integer passwordSize) {
        return passwordService.generatePassword(passwordSize);
    }

    @GetMapping("/isStrong")
    public boolean isStrong(@RequestBody String password) {
        return passwordService.isStrongPassword(password);
    }

    @GetMapping("/myPassword")
    public List<PasswordResponse> showAllPassword(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        List<PasswordEntity> passwordEntities = passwordService.showAllPassword(token);
        return passwordEntities.stream()
                .map(passwordMapper::toRest)
                .toList();
    }


    @PostMapping("/save")
    public PasswordResponse savePassword(HttpServletRequest request,@RequestBody CreatePasswordResponses createPasswordResponses) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String userName = jwtService.extractUsername(token);
        Optional<UserEntity> user = userRepository.findByUsername(userName);
        PasswordEntity password = passwordService.savePassword(passwordMapper.toDomainPassword(user,createPasswordResponses));
        return passwordMapper.toRest(password);
    }


    @DeleteMapping("/delete")
    public void deletePassword(@RequestParam UUID passwordID) {
        passwordService.deletePassword(passwordID);
    }
}
