package com.novatechzone.dentisthunt.util;

import com.novatechzone.dentisthunt.domain.user.User;
import com.novatechzone.dentisthunt.exception.ApplicationCustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final String SECRET = "JBX/b]MJ)x0M.}Ve6i(b+[pt,f(T53A8u+Z8Mwx/;UQc@V@&RFm%ubutt)4;x]7X";
    private static final Long EXPIRY_DURATION = 60 * 30L;

    public String generateAccessToken(User user) {

        long currentTimeMillis = System.currentTimeMillis();
        long expiryTime = currentTimeMillis + EXPIRY_DURATION * 1000;

        Date issuedAt = new Date(currentTimeMillis);
        Date expiryAt = new Date(expiryTime);

        Claims claims = Jwts.claims()
                .setIssuer("com.novatechzone.dentist-hunt")
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);

        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    public Claims verifyToken(String authorization) {
        try {
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authorization).getBody();
        } catch (Exception e) {
            throw new ApplicationCustomException(HttpStatus.UNAUTHORIZED, "ACCESS_DENIED", "Access Denied");
        }
    }
}
