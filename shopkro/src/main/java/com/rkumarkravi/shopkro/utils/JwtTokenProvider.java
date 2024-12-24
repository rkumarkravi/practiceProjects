package com.rkumarkravi.shopkro.utils;

import com.rkumarkravi.shopkro.services.BuyerService;
import com.rkumarkravi.shopkro.services.SellerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    private String secretKeyStr = "yourSecretKeyjjwt-api-0.12.6.jar:0.12.6";  // This should be kept secure
    private SecretKey secretKey;
    private long validityInMilliseconds = 3600000; // 1h validity

    @Autowired
    BuyerService buyerService;

    @Autowired
    SellerService sellerService;

    @PostConstruct
    protected void init() {
        secretKey = Keys.hmacShaKeyFor(secretKeyStr.getBytes());
    }

    // 1. Create JWT Token
    public String createToken(String username, String role) {
        Map<String,String> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("u",username);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(validity)
                .signWith(secretKey)
                .compact();
    }

    // 2. Get Authentication Object from Token
    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        UserDetails userDetails=null;
        if("BUYER".equals(getRole(token))) {
            userDetails = buyerService.loadUserByUsername(getUsername(token));
        }else{
            userDetails = sellerService.loadUserByUsername(getUsername(token));
        }
        System.out.println(userDetails.getUsername()+","+userDetails.getPassword());
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }

    // 3. Extract Username from Token
    public String getUsername(String token) {
        return (String) Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("u");
    }

    public String getRole(String token) {
        return (String) Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role");
    }

    // 4. Resolve Token from Request Header
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 5. Validate JWT Token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
