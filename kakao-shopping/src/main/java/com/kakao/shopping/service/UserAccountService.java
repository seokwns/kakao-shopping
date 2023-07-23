package com.kakao.shopping.service;

import com.kakao.shopping._core.errors.exception.PasswordMismatchException;
import com.kakao.shopping._core.security.JwtTokenProvider;
import com.kakao.shopping.domain.UserAccount;
import com.kakao.shopping.dto.user.UserLoginRequest;
import com.kakao.shopping.dto.user.UserRegisterRequest;
import com.kakao.shopping.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.InvalidPropertiesFormatException;

@RequiredArgsConstructor
@Service
public class UserAccountService implements UserDetailsService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return User.builder()
                .username(userAccount.getEmail())
                .password(userAccount.getPassword())
                .roles(userAccount.getRoles())
                .build();
    }

    public void regist(UserRegisterRequest request) throws InvalidPropertiesFormatException, DuplicateKeyException {
        checkEmailFormat(request.email());
        checkNameFormat(request.name());
        checkPasswordFormat(request.password());

        try {
            userAccountRepository.save(
                    UserAccount.builder()
                            .name(request.name())
                            .email(request.email())
                            .password(request.password())
                            .birthdate(request.birthdate())
                            .build()
            );
        }
        catch (DataIntegrityViolationException error) {
            throw new DuplicateKeyException("중복된 email 입니다.");
        }
    }

    public String login(UserLoginRequest request) throws PasswordMismatchException {
        UserAccount userAccount = userAccountRepository.findByEmail(request.email()).orElseThrow(
                () -> new UsernameNotFoundException("등록되지 않은 이메일 입니다.")
        );

        if (!passwordEncoder.matches(request.password(), userAccount.getPassword())) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }

        return JwtTokenProvider.create(userAccount);
    }

    private void checkEmailFormat(String email) throws InvalidPropertiesFormatException {
        if (email == null || email.length() < 1 || email.length() > 100) {
            throw new InvalidPropertiesFormatException("이메일의 길이는 1 이상 100 이하만 가능합니다.");
        }

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.matches(regex)) {
            throw new InvalidPropertiesFormatException("이메일 형식이 잘못되었습니다.");
        }
    }

    private void checkNameFormat(String name) throws InvalidPropertiesFormatException {
        if (name.length() > 45) {
            throw new InvalidPropertiesFormatException("이름의 길이는 45자 이하만 가능합니다.");
        }
    }

    private void checkPasswordFormat(String password) throws InvalidPropertiesFormatException {
        if (password == null || password.length() < 8 || password.length() > 256) {
            throw new InvalidPropertiesFormatException("비밀번호의 길이는 8 이상 256 이하만 가능합니다.");
        }

        String regex = "^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$";
        if (!password.matches(regex)) {
            throw new InvalidPropertiesFormatException("비밀번호는 1개 이상의 영문자, 1개 이상의 숫자, 1개 이상의 특수문자를 포함해야 합니다.");
        }
    }
}