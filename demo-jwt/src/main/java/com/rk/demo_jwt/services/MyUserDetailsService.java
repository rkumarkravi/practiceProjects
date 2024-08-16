package com.rk.demo_jwt.services;

import com.rk.demo_jwt.models.DemoUser;
import com.rk.demo_jwt.models.mongo.WeatherObsMongoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    private static final List<DemoUser> userList = new ArrayList<>();

    @Override
    public DemoUser loadUserByUsername(String username) throws UsernameNotFoundException {
        StopWatch stopWatch=new StopWatch("loadUserByUsername");
        stopWatch.start();
        //        log.info("username is:: {}",username);
        try{
        var users = userList.stream()
                .filter(x -> x.getUsername().equals(username))
                .toList();
        if (!users.isEmpty()) {
            log.info("users size:{}, data:{},{}",users.size(),users.get(0).getUsername(),users.get(0).getPassword());
            return users.get(0);
        } else {
            throw new UsernameNotFoundException("Invalid Credentials!");
        }}finally {
            stopWatch.stop();
            log.info(stopWatch.prettyPrint(TimeUnit.MILLISECONDS));
        }
    }

    public DemoUser addMoreUsers(Map<String, String> map) throws Exception {
        var user = new DemoUser(map.get("u"), passwordEncoder.encode(map.get("p")));
        if (userList.stream().noneMatch(x -> x.getUsername().equals(map.get("u")))) {
            userList.add(user);
            return user;
        }

        throw new Exception("User already exists!");

    }


}
