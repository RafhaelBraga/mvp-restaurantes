package com.mvp.demo.configs.security.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.mvp.demo.configs.security.service.JwtUserDetailsService;
import com.mvp.demo.configs.security.util.JwtTokenUtil;

import io.jsonwebtoken.Claims;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			
            String jwt = jwtTokenUtil.getJwt(request)!=null ? jwtTokenUtil.getJwt(request).replace("Bearer ", "") : null;            
            DecodedJWT decodedJwt = jwt!=null ? jwtTokenUtil.verifyToken(jwt): null;
            
            if (jwt != null && decodedJwt!=null) {
            	Claims claims = jwtTokenUtil.decodeJwtToken(jwt);
                JSONObject obj = new JSONObject(Objects.requireNonNull(claims));
                
                String email = obj.getString("email");
                String senha = obj.getString("senha");
                UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(email, senha);
                UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Não foi possível fornecer autenticação para o usuário -> Mensagem: {}", e);
        }

        filterChain.doFilter(request, response);
		
	}
	
}