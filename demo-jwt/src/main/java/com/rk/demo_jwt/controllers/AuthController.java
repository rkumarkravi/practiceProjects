package com.rk.demo_jwt.controllers;

import com.rk.demo_jwt.services.MyUserDetailsService;
import com.rk.demo_jwt.utils.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    String signup(@RequestBody Map<String, String> reqBody) {
        log.debug("{},Request Body: {}", this.getClass().getSimpleName(), reqBody);
        try {
            userDetailsService.addMoreUsers(reqBody);
            return "Registration Successful!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody Map<String, String> reqBody) {
        log.debug("{},Request Body: {}", this.getClass().getSimpleName(), reqBody);
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(reqBody.get("u"),reqBody.get("p"));

            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            if(authentication.isAuthenticated()) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                var token = jwtTokenProvider.createToken(userDetails.getUsername(), "USER");

                Map<String, String> map = new HashMap<>();
                map.put("at", token);
                map.put("rs", "S");
                map.put("rd", "Welcome " + reqBody.get("u"));
                return ResponseEntity.ok(map);
            }else{
                return new ResponseEntity<String>("Invalid Credentials!", HttpStatus.UNAUTHORIZED);
            }
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
