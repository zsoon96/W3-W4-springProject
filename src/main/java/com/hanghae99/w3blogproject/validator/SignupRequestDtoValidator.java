package com.hanghae99.w3blogproject.validator;

import com.hanghae99.w3blogproject.dto.SignupRequestDto;
import com.hanghae99.w3blogproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

@RequiredArgsConstructor
@Component
public class SignupRequestDtoValidator implements Validator {

    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignupRequestDto requestDto = (SignupRequestDto) o;


        if (userRepository.existsByUsername(requestDto.getUsername())) {
            errors.rejectValue("username", "invalid.username", new Object[]{requestDto.getUsername()}, "이미 사용중인 닉네임 입니다");
        }

        if (requestDto.getPassword().equals(requestDto.getUsername())) {
            errors.rejectValue("password", "wrong.value", "닉네임과 패스워드는 같을 수 없습니다.");
        }
        if (!requestDto.getPassword().equals(requestDto.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "wrong.value", "입력한 패스워드가 서로 일치하지 않습니다.");
        }
    }
}
