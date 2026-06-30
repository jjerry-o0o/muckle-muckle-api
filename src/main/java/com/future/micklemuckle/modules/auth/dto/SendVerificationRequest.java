package com.future.micklemuckle.modules.auth.dto;

import lombok.Getter;

@Getter
public class SendVerificationRequest {
    private String email;
    private String purpose;
}
