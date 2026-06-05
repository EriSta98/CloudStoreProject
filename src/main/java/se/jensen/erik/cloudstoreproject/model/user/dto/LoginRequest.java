package se.jensen.erik.cloudstoreproject.model.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @Email
        @NotBlank
        String mail,

        @NotBlank
        String password
) {
}
