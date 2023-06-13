package com.project.paswordapi.Controller.Mapper;

import com.project.paswordapi.Controller.Response.CreatePasswordResponses;
import com.project.paswordapi.Controller.Response.PasswordResponse;
import com.project.paswordapi.Entity.PasswordEntity;
import com.project.paswordapi.Entity.UserEntity;
import com.project.paswordapi.Exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@AllArgsConstructor
public class PasswordMapper {

    public PasswordResponse toRest(PasswordEntity domain) {
        return PasswordResponse.builder()
                .label(domain.getLabel())
                .password(domain.getPassword())
                .UserId(domain.getUserEntity().getId())
                .build();
    }

    public PasswordEntity toDomainPassword(Optional<UserEntity> user, CreatePasswordResponses toCreate) {
        if(user.isPresent()){
            return PasswordEntity.builder()
                    .label(toCreate.getLabel())
                    .password(toCreate.getPassword())
                    .userEntity(user.get())
                    .build();
        }
       else {
           throw new UserNotFoundException("invalid user");
        }
    }

}
