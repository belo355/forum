package br.com.forumCaellum.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.forumCaellum.config.security.TokenService;
import br.com.forumCaellum.controller.dto.TokenDto;
import br.com.forumCaellum.controller.form.LoginForm;


/**
 * Class for manager operations Authentication.
 *
 * @author Edilson Belo
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager; 
	
	@Autowired
	private TokenService tokenService; 
	
	@PostMapping
	public ResponseEntity<TokenDto> authenticationAndGeneratedTokenAcess(@RequestBody LoginForm form) throws AuthenticationException{
		UsernamePasswordAuthenticationToken dataAccess = form.converter();
		Authentication authentication = authManager.authenticate(dataAccess);
		String token = tokenService.generateToken(authentication);
		return ResponseEntity.ok(new TokenDto(token, "Bearer"));
	}

}
