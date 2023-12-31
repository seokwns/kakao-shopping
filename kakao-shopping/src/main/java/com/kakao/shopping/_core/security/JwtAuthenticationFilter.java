package com.kakao.shopping._core.security;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kakao.shopping.domain.UserAccount;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");

        if (token == null) {
            chain.doFilter(request, response);
            return;
        }

        try {
            DecodedJWT decodedJWT = JwtTokenProvider.verify(token);
            Long userId = decodedJWT.getClaim("id").asLong();
            String roles = decodedJWT.getClaim("role").asString();
            logger.debug("request user id: " + userId);

            UserAccount user = UserAccount.builder().id(userId).roles(roles).build();
            CustomUserDetails userDetails = new CustomUserDetails(user);
            Authentication authenticationManager = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    userDetails.getPassword(),
                    userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationManager);
            logger.debug("인증 객체 생성");
        }
        catch (SignatureVerificationException exception) {
            logger.error("토큰 검증 실패");
        }
        catch (TokenExpiredException exception) {
            logger.error("토큰 만료됨");
        }
        finally {
            chain.doFilter(request, response);
        }
    }
}
