package com.future.micklemuckle.modules.auth.dto;

import lombok.Getter;

@Getter
public class SignupRequest {
    private String email;
    private String password;
    private String nickname;
}
