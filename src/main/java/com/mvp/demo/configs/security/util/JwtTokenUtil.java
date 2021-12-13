package com.mvp.demo.configs.security.util;

import java.io.Serializable;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.pdf.codec.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Component
public class JwtTokenUtil implements Serializable{

	private static final long serialVersionUID = -6563638730430701989L;

	public static final long JWT_TOKEN_VALIDITY = 30 * 60 * 60;

	@Value("${jwt.public-key}")
	private String publicKey;
	
	@Value("${jwt.private-key}")
	private String privateKey;		
	
	public String createToken(UserDetails userDetails) throws NoSuchAlgorithmException, InvalidKeySpecException {
		try {
			RSAPublicKey rsaPublicKey= generatePublicKey();
			RSAPrivateKey rsaPrivateKey = generatePrivateKey();
		    Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
		    String token = JWT.create()
		        .withIssuer("auth0")
		        .withClaim("email", userDetails.getUsername())
		        .withClaim("senha", userDetails.getPassword())
				.withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
		        .sign(algorithm);
		    return token;
		} catch (JWTCreationException exception){
		    exception.printStackTrace();
		}
		return null;
	}
	
	public DecodedJWT verifyToken(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {			
		try {
			RSAPublicKey rsaPublicKey= generatePublicKey();
			RSAPrivateKey rsaPrivateKey = generatePrivateKey();
			
		    Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("auth0")
		        .build();
		    DecodedJWT jwt = verifier.verify(token);
		    return jwt;
		} catch (JWTVerificationException exception){
		    System.out.println("Falha ao verificar token");
		}
		return null;
	}
	
    public Claims decodeJwtToken(String token) {
        try {
            RSAPublicKey publicKey = generatePublicKey();
            return Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();
        } catch (JWTVerificationException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public String getJwt(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }	
	
    private RSAPublicKey generatePublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        publicKey = publicKey.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");
        byte[] encodedPublicKey = Base64.decode(publicKey);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(encodedPublicKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) kf.generatePublic(spec);
    }
    
    private RSAPrivateKey generatePrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        privateKey = privateKey.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
        byte[] encodedPrivateKey = Base64.decode(privateKey);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(encodedPrivateKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) kf.generatePrivate(spec);
    }
}
