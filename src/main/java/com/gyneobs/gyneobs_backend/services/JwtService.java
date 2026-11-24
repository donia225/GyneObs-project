package com.gyneobs.gyneobs_backend.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gyneobs.gyneobs_backend.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;




@Service
public class JwtService {

    private final String secretKey;
    private final long jwtExpiration;

    public JwtService(@Value("${security.jwt.secret-key}") String secretKey,
                      @Value("${security.jwt.expiration-time}") long jwtExpiration) {
        this.secretKey = secretKey;
        this.jwtExpiration = jwtExpiration;
    }
   
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(User user) {
        return generateToken(new HashMap<>(), user);
    }

    public String generateToken(Map<String, Object> extraClaims, User user) {
        return buildToken(extraClaims, user, jwtExpiration);
    }
    
    public long getExpirationTime() {
        return jwtExpiration;
    }
//   génération du token 
    private String buildToken(Map<String, Object> extraClaims, User user, long jwtExpiration) {
        return io.jsonwebtoken.Jwts.builder()
                // extractClaims: ajout des données supp
                .setClaims(extraClaims)
                 //  subject : définit le username comme sujet de token
                .setSubject(user.getUsername())
                 // issuedAt : la date d'emission
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // expiration :  la durée de validité du token
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                // Signe le token avec HMAC-SHA256 en utilisant une clé secréte via getSignInKey
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secretKey)

                // compacte en une chaine finale : l'entéte, payload et signature
                .compact();
    }

    // Validation du token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new java.util.Date());
    }   
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    // claims: infos contenus dans le JWT 
    // extractAllClaims permet de récupérer tous les claims aprés vérification du token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build().parseClaimsJws(token)
                .getBody();
    }
    // cette méthode prépare la clé secréte pour HMAC
    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }





}
