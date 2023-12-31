package com.project.paswordapi.Service;

import com.project.paswordapi.Entity.UserEntity;
import com.project.paswordapi.Exception.DatabaseAccessException;
import com.project.paswordapi.Exception.UserNotFoundException;
import com.project.paswordapi.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity getUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void createUser(List<UserEntity> userEntity) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        for (UserEntity user : userEntity) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.saveAll(userEntity);
    }

    public void deleteUser(UUID userID) throws UserNotFoundException, DatabaseAccessException {
        try {
            Optional<UserEntity> optionalUser = userRepository.findById(userID);
            if (optionalUser.isPresent()) {
                userRepository.deleteById(userID);
            } else {
                throw new UserNotFoundException("User with ID " + userID + " does not exist.");
            }
        } catch (IllegalArgumentException | DataAccessException e) {
            throw new DatabaseAccessException("Error while deleting user with ID " + userID);
        }
    }

}
