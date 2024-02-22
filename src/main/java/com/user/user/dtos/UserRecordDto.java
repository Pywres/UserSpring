package com.user.user.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String name,@NotBlank String username,@NotBlank String email,@NotBlank String document, @NotBlank String password) {

}
