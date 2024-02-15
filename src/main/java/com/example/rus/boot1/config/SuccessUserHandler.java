package com.example.rus.boot1.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                System.out.println("Admin successfully logged in."); // Добавлено отладочное сообщение
                response.sendRedirect("/admin");
                return;
            } else if (authority.getAuthority().equals("ROLE_USER")) {
                System.out.println("User successfully logged in."); // Добавлено отладочное сообщение
                response.sendRedirect("/user");
                return;
            }
        }
        System.out.println("Unknown user role."); // Добавлено отладочное сообщение
        response.sendRedirect("/"); // В случае неопределенной роли перенаправляем на главную страницу
    }
}
