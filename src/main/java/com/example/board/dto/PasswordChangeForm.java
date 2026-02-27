package com.example.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeForm {

    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
