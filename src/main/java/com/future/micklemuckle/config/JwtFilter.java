package com.future.micklemuckle.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private static final Long DEMO_USER_ID = 1L;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            // fixMe - 토큰이 만료된 경우 else 로직 추가하기
            if (jwtProvider.isValid(token)) {
                setAuthentication(jwtProvider.getUserId(token));
            }
        } else if ("GET".equalsIgnoreCase(request.getMethod())) {
            setAuthentication(DEMO_USER_ID);
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(Long userId) {
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(userId, null, List.of());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
