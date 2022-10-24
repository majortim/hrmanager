package kr.co.hrmanager.auth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal(expression = " #this instanceof T(org.springframework.security.oauth2.jwt.Jwt) ? @userDetailsService.loadUserByUsername(#this.subject) : #this")
public @interface PrincipalUser {}
