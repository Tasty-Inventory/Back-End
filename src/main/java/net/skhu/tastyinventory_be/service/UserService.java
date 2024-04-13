package net.skhu.tastyinventory_be.service;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.controller.user.dto.SignUpRequestDto;
import net.skhu.tastyinventory_be.domain.user.User;
import net.skhu.tastyinventory_be.domain.user.UserRepository;
import net.skhu.tastyinventory_be.domain.user.UserType;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.model.DuplicateUserException;
import net.skhu.tastyinventory_be.exception.model.RegisteredUserException;
import net.skhu.tastyinventory_be.oauth2.OAuth2Token;
import net.skhu.tastyinventory_be.oauth2.domain.OAuth2Account;
import net.skhu.tastyinventory_be.oauth2.domain.OAuth2AccountDto;
import net.skhu.tastyinventory_be.oauth2.domain.OAuth2AccountRepository;
import net.skhu.tastyinventory_be.oauth2.userinfo.OAuth2UserInfo;
import net.skhu.tastyinventory_be.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;
    private final OAuth2AccountRepository oAuth2AccountRepository;
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

    @Transactional(readOnly = true)
    public Optional<OAuth2AccountDto> getOAuth2Account(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent() || optionalUser.get().getSocial() == null) {
            return Optional.empty();
        }
        return Optional.of(optionalUser.get().getSocial().toDto());
    }

    public UserDetails loginOAuth2User(String provider, OAuth2Token oAuth2Token, OAuth2UserInfo userInfo) {
        Optional<OAuth2Account> optOAuth2Account = oAuth2AccountRepository.findByProviderAndProviderId(provider, userInfo.getId());
        User user = null;

        if (optOAuth2Account.isPresent()) {
            OAuth2Account oAuth2Account = optOAuth2Account.get();
            user = oAuth2Account.getUser();
            oAuth2Account.updateToken(oAuth2Token.getToken(), oAuth2Token.getRefreshToken(), oAuth2Token.getExpiredAt());
        } else {
            OAuth2Account newAccount = OAuth2Account.builder()
                    .provider(provider)
                    .providerId(userInfo.getId())
                    .token(oAuth2Token.getToken())
                    .refreshToken(oAuth2Token.getRefreshToken())
                    .tokenExpiredAt(oAuth2Token.getExpiredAt()).build();
            oAuth2AccountRepository.save(newAccount);

            if (userInfo.getEmail() != null) {
                user = userRepository.findByEmail(userInfo.getEmail())
                        .orElse(User.builder()
                                .username(provider + "_" + userInfo.getId())
                                .name(userInfo.getName())
                                .email(userInfo.getEmail())
                                .type(UserType.OAUTH)
                                .build()
                        );
            } else {
                user = User.builder()
                        .username(provider + "_" + userInfo.getId())
                        .name(userInfo.getName())
                        .email(userInfo.getName() + "@kakao.com")
                        .type(UserType.OAUTH)
                        .build();
            }

            if (user.getId() == null) {
                userRepository.save(user);
            }

            user.linkSocial(newAccount);
        }

        return UserDetailsImpl.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .type(user.getType())
                .authorities(user.getAuthorities()).build();
    }

    public UserDetails linkOAuth2Account(String username, String provider, OAuth2Token oAuth2Token, OAuth2UserInfo userInfo) {
        User user = checkRegisteredUser(username);

        OAuth2Account oAuth2Account = OAuth2Account.builder()
                .provider(provider)
                .provider(userInfo.getId())
                .token(oAuth2Token.getToken())
                .refreshToken(oAuth2Token.getRefreshToken())
                .tokenExpiredAt(oAuth2Token.getExpiredAt())
                .build();
        oAuth2AccountRepository.save(oAuth2Account);

        user.linkSocial(oAuth2Account);

        return UserDetailsImpl.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .type(user.getType())
                .authorities(user.getAuthorities()).build();
    }

    public OAuth2AccountDto unlinkOAuth2Account(String username) {
        User user = checkRegisteredUser(username);

        OAuth2Account oAuth2Account = user.getSocial();
        OAuth2AccountDto oAuth2AccountDto = oAuth2Account.toDto();
        user.unlinkSocial();
        oAuth2AccountRepository.delete(oAuth2Account);

        return oAuth2AccountDto;
    }

    private void checkDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateUserException(ErrorCode.DUPLICATE_EMAIL_EXCEPTION, ErrorCode.DUPLICATE_EMAIL_EXCEPTION.getMessage());
        }
    }

    private User checkRegisteredUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RegisteredUserException(ErrorCode.NOT_FOUND_USER_EXCEPTION, ErrorCode.NOT_FOUND_USER_EXCEPTION.getMessage()));
    }

}
