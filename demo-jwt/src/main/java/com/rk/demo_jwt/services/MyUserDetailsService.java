package com.rk.demo_jwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    private static List<User> userList = new ArrayList<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var users = userList.stream()
                .filter(x -> x.getUsername().equals(username))
                .toList();
        if (!users.isEmpty()) {
            return users.get(0);
        } else {
            throw new UsernameNotFoundException("Invalid Credentials!");
        }
    }

    public User addMoreUsers(Map<String, String> map) throws Exception {
        var user = new User(map.get("u"), passwordEncoder.encode(map.get("p")), Collections.emptyList());
        if (userList.stream().noneMatch(x -> x.getUsername().equals(map.get("u")))) {
            userList.add(user);
            return user;
        }

        throw new Exception("User already exists!");

    }
}
