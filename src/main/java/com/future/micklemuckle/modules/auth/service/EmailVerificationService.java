package com.future.micklemuckle.modules.auth.service;

import com.future.micklemuckle.common.exception.BusinessException;
import com.future.micklemuckle.common.exception.ErrorCode;
import com.future.micklemuckle.modules.auth.dto.SendVerificationRequest;
import com.future.micklemuckle.modules.auth.dto.VerifyCodeRequest;
import com.future.micklemuckle.modules.auth.entity.EmailVerification;
import com.future.micklemuckle.modules.auth.repository.EmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationRepository emailVerificationRepository;
    private final JavaMailSender mailSender;

    public void sendCode(SendVerificationRequest request) {
        String code = generateCode();

        emailVerificationRepository.save(EmailVerification.builder()
                .email(request.getEmail())
                .code(code)
                .purpose(request.getPurpose())
                .build());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getEmail());
        message.setSubject("[MickleMuckle] 이메일 인증코드");
        message.setText("인증코드: " + code + "\n\n5분 이내에 입력해주세요.");
        mailSender.send(message);
    }

    public void verifyCode(VerifyCodeRequest request) {
        EmailVerification verification = emailVerificationRepository
                .findTopByEmailAndPurposeOrderByCreatedAtDesc(request.getEmail(), request.getPurpose())
                .orElseThrow(() -> new BusinessException(ErrorCode.AUTH_INVALID_CODE));

        if (verification.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new BusinessException(ErrorCode.AUTH_CODE_EXPIRED);
        }

        if (!verification.getCode().equals(request.getCode())) {
            throw new BusinessException(ErrorCode.AUTH_INVALID_CODE);
        }

        verification.verify();
        emailVerificationRepository.save(verification);
    }

    private String generateCode() {
        return String.format("%06d", new Random().nextInt(1000000));
    }
}
