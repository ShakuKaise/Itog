package com.example.SpringAppV3.controller;

import com.example.SpringAppV3.model.LoginDto;
import com.example.SpringAppV3.model.UserRegistrationDto;
import com.example.SpringAppV3.service.UserDetailsService;
import com.example.SpringAppV3.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public UserController(UserService userService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    // Показать страницу входа
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login"; // Имя шаблона страницы входа
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDto") LoginDto loginDto, BindingResult result,
                        RedirectAttributes redirectAttributes, HttpSession session) {
        if (result.hasErrors()) {
            return "login"; // Вернуть на страницу входа в случае ошибок
        }

        try {
            // Создаем объект для аутентификации с введенным email и паролем
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

            // Аутентифицируем пользователя
            Authentication authentication = authenticationManager.authenticate(authToken);

            // Устанавливаем аутентификацию в SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Сохраняем пользователя в сессии
            session.setAttribute("user", authentication.getPrincipal());

            return "redirect:/home"; // Перенаправление на домашнюю страницу после успешного входа
        } catch (AuthenticationException e) {
            // Обработка ошибок аутентификации
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid email or password");
            return "redirect:login"; // Вернуться на страницу входа с ошибкой
        }
    }

    // Показать страницу регистрации
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegistrationDto", new UserRegistrationDto());
        return "register"; // Make sure this view exists
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "register"; // Return to registration page on errors
        }

        try {
            userService.registerUser(userRegistrationDto.getEmail(), userRegistrationDto.getPassword(),
                    userRegistrationDto.getFirstName(), userRegistrationDto.getLastName());
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful. You can now log in.");
            return "redirect:/auth/login"; // Redirect to login page after successful registration
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/auth/register"; // Вернуться на страницу регистрации с ошибкой
        }
    }

    // Выход пользователя
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate(); // Удаляем сессию пользователя
        redirectAttributes.addFlashAttribute("successMessage", "You have been logged out successfully.");
        return "redirect:login"; // Перенаправление на страницу входа
    }
}