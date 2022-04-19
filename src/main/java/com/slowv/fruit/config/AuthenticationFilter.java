package com.slowv.fruit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slowv.fruit.constant.AppConstants;
import com.slowv.fruit.domain.Account;
import com.slowv.fruit.repository.AccountRepository;
import com.slowv.fruit.util.JwtUtils;
import com.slowv.fruit.web.errors.ApiError;
import com.slowv.fruit.web.errors.AuthenticationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private final ApplicationProperties properties;
    private final AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (shouldNotFilter(request)) {
            filterChain.doFilter(request, response);
        } else {
            final var authentication = getAuthentication(request, response);
            if (Objects.nonNull(authentication)) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } else {
                SecurityContextHolder.clearContext();
                responseFailCredential(response, HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
            }
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return !path.startsWith("/admin");
    }

    private void responseFailCredential(HttpServletResponse response, int code, String message) throws IOException {
        response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(code);
        new ObjectMapper().writeValue(response.getOutputStream(), new ApiError(message, code));
        System.out.println(response);
        response.flushBuffer();
    }

    private Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final var authorization = request.getHeader("Authorization");

        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            final var token = authorization.substring(7);
            try {
                JwtUtils.validateJwtToken(token, properties.getJwtSecret());
            } catch (Exception ex) {
                throw new AuthenticationException("Invalid token");
            }
            final var userUuid = JwtUtils.getUserUuidFromJwtToken(token, properties.getJwtSecret());
            final var account = accountRepository.findByUuid(userUuid).orElseThrow(EntityNotFoundException::new);
            this.pushCustomerAuthToSession(request, account);
            final var authentication = new UsernamePasswordAuthenticationToken(account, null, buildAuthorities(account));
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            return authentication;
        }

        return null;
    }

    private void pushCustomerAuthToSession(HttpServletRequest request, Account account) {
        request.getSession().setAttribute(AppConstants.ACCOUNT_AUTH, account);
    }

    private List<GrantedAuthority> buildAuthorities(Account account) {
        return account.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }
}
