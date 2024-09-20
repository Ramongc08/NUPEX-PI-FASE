package com.br.nupex.controleusuario.controle_usuario_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	
	@GetMapping
	public ResponseEntity<String> getUser(){
		return ResponseEntity.ok("Sucesso!");
	}
}
