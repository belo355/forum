package br.com.forumCaellum.config.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.forumCaellum.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	public String generateToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal(); 
		Date hoje = new Date(); 
		Date dataExpiração = new Date(hoje.getTime()+ Long.parseLong(System.getenv("JWT_EXPIRATION"))); 
		
		return Jwts.builder()
				.setIssuer("Api forum alura")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiração)
				.signWith(SignatureAlgorithm.HS256, System.getenv("JWT_SECRET"))
				.compact();
	}
	
}
