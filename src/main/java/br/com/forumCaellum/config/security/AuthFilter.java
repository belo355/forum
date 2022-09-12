package br.com.forumCaellum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.forumCaellum.model.Usuario;
import br.com.forumCaellum.repository.UsuarioRepository;

public class AuthFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;
	
	public AuthFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService; 
		this.usuarioRepository = usuarioRepository; 
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = getTokenRequest(request); 		
		boolean isValid = tokenService.isValidoToken(token);
		if (isValid) {
			autenticaCliente(token); 
		}
		filterChain.doFilter(request, response);
	}

	private void autenticaCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token); 
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	private String getTokenRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null; 
		}
		return token.substring(7, token.length());
	}
}
