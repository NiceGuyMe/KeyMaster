package com.project.paswordapi.Controller.Response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class CreatePasswordResponses {
    private String label;
    private String password;
}
