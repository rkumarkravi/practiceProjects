package com.rkumarkravi.shopkro.controllers;

import com.rkumarkravi.shopkro.dtos.request.LoginRequest;
import com.rkumarkravi.shopkro.dtos.response.AuthBuyerDto;
import com.rkumarkravi.shopkro.dtos.response.LoginResponse;
import com.rkumarkravi.shopkro.entities.Buyer;
import com.rkumarkravi.shopkro.entities.Seller;
import com.rkumarkravi.shopkro.repositories.BuyerRepository;
import com.rkumarkravi.shopkro.repositories.SellerRepository;
import com.rkumarkravi.shopkro.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.rkumarkravi.shopkro.utils.Utils.MODEL_MAPPER;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtUtil;

    @PostMapping("/register/seller")
    public ResponseEntity<?> registerUser(@Validated @RequestBody Seller user) {
        // Check if email already exists
        if (sellerRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already in use!");
        }

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        sellerRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/register/buyer")
    public ResponseEntity<?> registerUser(@Validated @RequestBody Buyer user) {
        // Check if email already exists
        if (buyerRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already in use!");
        }

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        buyerRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    /**
     * Login and get a JWT token
     * @param loginRequest LoginRequest object containing email and password
     * @return JWT token or error message
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        var password="";
        var id=0L;
        Object userInfo=null;
        if("BUYER".equals(loginRequest.getType())) {
            // Find user by email
            Optional<Buyer> userOptional = buyerRepository.findByEmailIgnoreCase(loginRequest.getEmail());
            if (userOptional.isEmpty()) {
                return ResponseEntity.badRequest().body("Invalid email or password!");
            }

            Buyer user = userOptional.get();
            password = user.getPassword();
            id= user.getId();
            userInfo= MODEL_MAPPER.map(user, AuthBuyerDto.class);
        }else {
            Optional<Seller> userOptional = sellerRepository.findByEmailIgnoreCase(loginRequest.getEmail());
            if (userOptional.isEmpty()) {
                return ResponseEntity.badRequest().body("Invalid email or password!");
            }

            Seller user = userOptional.get();
            password = user.getPassword();
            id= user.getId();
            userInfo=user;
        }
        // Check if password matches
        /*if (!passwordEncoder.matches(loginRequest.getPassword(), password)) {
            return ResponseEntity.badRequest().body("Invalid email or password!");
        }*/


        if(!loginRequest.getPassword().equals(password)){
            return ResponseEntity.badRequest().body("Invalid email or password!");
        }

        // Generate JWT token
        String token = jwtUtil.createToken(String.valueOf(id),loginRequest.getType());
        return ResponseEntity.ok(new LoginResponse(token,userInfo,loginRequest.getType()));
    }
}
