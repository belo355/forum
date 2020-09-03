package br.com.forumCaellum.config.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.forumCaellum.model.Usuario;
import io.jsonwebtoken.Claims;
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

	public boolean isValidoToken(String token) { 
		try {
			Jwts.parser().setSigningKey(System.getenv("JWT_SECRET")).parseClaimsJws(token);
			return true; 
		} catch (Exception e) {
			return false; 
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(System.getenv("JWT_SECRET")).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject()); 
	}	
}
