package net.skhu.tastyinventory_be.service;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.controller.user.dto.SignUpRequestDto;
import net.skhu.tastyinventory_be.domain.user.User;
import net.skhu.tastyinventory_be.domain.user.UserRepository;
import net.skhu.tastyinventory_be.domain.user.UserType;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.model.DuplicateUserException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(SignUpRequestDto signUpRequestDto) {
        checkDuplicateEmail(signUpRequestDto.getEmail());

        User user = User.builder()
                .username(signUpRequestDto.getEmail())
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getEmail())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .type(UserType.DEFAULT)
                .build();

        userRepository.save(user);
    }

    private void checkDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateUserException(ErrorCode.DUPLICATE_EMAIL_EXCEPTION, ErrorCode.DUPLICATE_EMAIL_EXCEPTION.getMessage());
        }
    }
}
