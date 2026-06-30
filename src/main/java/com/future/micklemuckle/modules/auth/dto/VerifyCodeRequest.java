package com.future.micklemuckle.modules.auth.dto;

import lombok.Getter;

@Getter
public class VerifyCodeRequest {
    private String email;
    private String code;
    private String purpose;
}
