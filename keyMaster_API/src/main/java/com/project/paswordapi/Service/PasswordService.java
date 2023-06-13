package com.project.paswordapi.Service;

import com.project.paswordapi.Entity.PasswordEntity;
import com.project.paswordapi.Repository.PasswordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PasswordService {

    private final PasswordRepository passwordRepository;
    private final JwtService jwtService;

    public String generatePassword(int length) {
        final String PASSWORD_ALLOW_BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(PASSWORD_ALLOW_BASE.length());
            password.append(PASSWORD_ALLOW_BASE.charAt(index));
        }
        return password.toString();
    }

    public boolean isStrongPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (hasUpperCase && hasLowerCase && hasDigit) {
                break;
            }
        }
        if (!hasUpperCase || !hasLowerCase || !hasDigit) {
            return false;
        }

        String specialCharacters = "!@#$%^&*()-+";
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (specialCharacters.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }


    public PasswordEntity savePassword(PasswordEntity passwordEntity) {
        return passwordRepository.save(passwordEntity);
    }

    public List<PasswordEntity> showAllPassword(String token) {
        String userName = jwtService.extractUsername(token);
        return passwordRepository.findByUserEntity_Username(userName);
    }

    public void deletePassword(UUID passwordID) {
        passwordRepository.deleteById(passwordID);
    }
}
