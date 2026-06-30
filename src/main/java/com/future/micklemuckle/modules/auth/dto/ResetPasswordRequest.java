package com.future.micklemuckle.modules.auth.dto;

import lombok.Getter;

@Getter
public class ResetPasswordRequest {
    private String email;
    private String newPassword;
}
