package com.logistica.pdv.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewUserDTO {
    @NotBlank(message = "Username field is empty")
    private String username;

    @NotBlank(message = "Password field is empty")
    private String password;
}
