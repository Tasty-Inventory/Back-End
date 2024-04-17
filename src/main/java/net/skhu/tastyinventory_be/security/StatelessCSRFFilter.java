package net.skhu.tastyinventory_be.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.model.SecurityException;
import net.skhu.tastyinventory_be.util.CookieUtils;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

import static net.skhu.tastyinventory_be.util.ServletErrorResponse.setErrorResponse;

@Slf4j
public class StatelessCSRFFilter extends OncePerRequestFilter {
    public static final String CSRF_TOKEN = "CSRF-TOKEN";
    public static final String X_CSRF_TOKEN = "X-CSRF-TOKEN";
    private final RequestMatcher requireCsrfProtectionMatcher = new DefaultRequiresCsrfMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (requireCsrfProtectionMatcher.matches(request)) {
            try {
                String csrfToken = Optional.ofNullable(request.getHeader(X_CSRF_TOKEN))
                        .orElseThrow(() -> new SecurityException(ErrorCode.CSRF_HEADER_TOKEN_NOT_FOUND_EXCEPTION, ErrorCode.CSRF_HEADER_TOKEN_NOT_FOUND_EXCEPTION.getMessage()));

                Cookie csrfCookie = CookieUtils.getCookie(request, CSRF_TOKEN)
                        .orElseThrow(() -> new SecurityException(ErrorCode.CSRF_COOKIE_TOKEN_NOT_FOUND_EXCEPTION, ErrorCode.CSRF_COOKIE_TOKEN_NOT_FOUND_EXCEPTION.getMessage()));

                if (!csrfToken.equals(csrfCookie.getValue())) {
                    throw new SecurityException(ErrorCode.CSRF_TOKEN_INVALID_EXCEPTION, ErrorCode.CSRF_TOKEN_INVALID_EXCEPTION.getMessage());
                }
            } catch (SecurityException e) {
                log.error("CSRF 토큰이 일치하지 않습니다: {}", e.getMessage(), e);
                setErrorResponse(response, e.getErrorCode());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    public static final class DefaultRequiresCsrfMatcher implements RequestMatcher {
        private final Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

        @Override
        public boolean matches(HttpServletRequest request) {
            return !allowedMethods.matcher(request.getMethod()).matches();
        }
    }
}
