package com.future.micklemuckle.rest;

import com.future.micklemuckle.modules.auth.dto.*;
import com.future.micklemuckle.modules.auth.service.AuthService;
import com.future.micklemuckle.modules.auth.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final EmailVerificationService emailVerificationService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/email/send")
    public ResponseEntity<Void> sendVerification(@RequestBody SendVerificationRequest request) {
        emailVerificationService.sendCode(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/email/verify")
    public ResponseEntity<Void> verifyCode(@RequestBody VerifyCodeRequest request) {
        emailVerificationService.verifyCode(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/password/reset")
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request);
        return ResponseEntity.ok().build();
    }
}
