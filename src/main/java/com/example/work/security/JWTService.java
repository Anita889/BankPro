package com.example.work.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


/************************
 * Service class  for JWT
 ************************/

@Service
@Slf4j
public class JWTService {
    private final static String
            SECRET_KEY="404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";


    //extract username from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //extract one claim from token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //generate  token from userDetails(human object)
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    //generate  token from extraClaims and userDetails(human object)  (full method)
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*24*1000))
                .signWith(Keys.hmacShaKeyFor(getSignInKey()))
                .compact();
    }

    //get bytes of signKey
    private byte[] getSignInKey() {
       return Decoders.BASE64.decode(SECRET_KEY);
    }

    //check token validations
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    //check token expiration
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //get date of token extract expiration
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


//get all claims of extractAllClaims from token
    private Claims extractAllClaims(String token) {
        return
                     Jwts.parser()
                         .verifyWith(Keys.hmacShaKeyFor(getSignInKey()))
                         .build()
                         .parseSignedClaims(token)
                         .getPayload();
    }

}
