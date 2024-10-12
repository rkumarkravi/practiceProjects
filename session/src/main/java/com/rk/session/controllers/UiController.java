package com.rk.session.controllers;

import com.rk.session.dtos.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class UiController {
    public static Map<String, String> users = Map.of("user1", "123456USER1");

    @GetMapping("/")
    public String init(Model model, HttpSession session, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        System.out.println("SESSIONID: " + session.getId());
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        System.out.println("LOGOUT: " + session.getId());
        session.removeAttribute("ID");
        session.invalidate();
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDto user, HttpSession httpSession, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        System.out.println(user);
        System.out.println("LOGIN: " + httpSession.getId());
        if ("user".equals(user.getUsername()) && "password".equals(user.getPassword())) {
            //System.out.println("before: " + httpSession.getId());
            HttpSession session = httpServletRequest.getSession();
            //System.out.println("after: " + session.getId());
            session.setAttribute("loginTime", System.currentTimeMillis());
            session.setAttribute("ID", users.get("user1"));
            session.setAttribute("username", "Ravi");
            session.setMaxInactiveInterval(10 * 60);
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setDomain("http://localhost:8080");
            cookie.setPath("/sessiontest");
            cookie.setHttpOnly(true);

            return "redirect:/main"; // Redirect to dashboard on successful login
        }
        return "index";
    }

    @GetMapping("/test")
    public String test(Model model, HttpSession session, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        System.out.println("TEST: " + session.getId());
        model.addAttribute("greet", "Test Success!");
        return "test";

    }

    @GetMapping("/main")
    public String main(Model model, HttpSession session, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        System.out.println("MAIN: " + session.getId());
        model.addAttribute("greet", "Hello " + session.getAttribute("username") + "!");
        return "main";

    }
}
