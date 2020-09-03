package br.com.forumCaellum.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.forumCaellum.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody LoginForm form){
		System.out.println(form.getEmail());
		System.out.println(form.getSenha());	
		return ResponseEntity.ok().build(); 
	}

}
