package com.future.micklemuckle.modules.auth.service;

import com.future.micklemuckle.common.exception.BusinessException;
import com.future.micklemuckle.common.exception.ErrorCode;
import com.future.micklemuckle.config.JwtProvider;
import com.future.micklemuckle.modules.auth.dto.LoginRequest;
import com.future.micklemuckle.modules.auth.dto.LoginResponse;
import com.future.micklemuckle.modules.auth.dto.ResetPasswordRequest;
import com.future.micklemuckle.modules.auth.dto.SignupRequest;
import com.future.micklemuckle.modules.auth.entity.EmailVerification;
import com.future.micklemuckle.modules.auth.repository.EmailVerificationRepository;
import com.future.micklemuckle.modules.user.entity.User;
import com.future.micklemuckle.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final EmailVerificationRepository emailVerificationRepository;

    public void signup(SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException(ErrorCode.AUTH_ALREADY_EXISTS_EMAIL);
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .build();

        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException(ErrorCode.AUTH_USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.AUTH_INVALID_PASSWORD);
        }

        String token = jwtProvider.createToken(user.getId());
        return new LoginResponse(token);
    }

    public void resetPassword(ResetPasswordRequest request) {
        EmailVerification verification = emailVerificationRepository
                .findTopByEmailAndPurposeOrderByCreatedAtDesc(request.getEmail(), "PASSWORD_RESET")
                .orElseThrow(() -> new BusinessException(ErrorCode.AUTH_EMAIL_NOT_VERIFIED));

        if (!verification.isVerified()) {
            throw new BusinessException(ErrorCode.AUTH_EMAIL_NOT_VERIFIED);
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException(ErrorCode.AUTH_USER_NOT_FOUND));

        user.updatePassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
