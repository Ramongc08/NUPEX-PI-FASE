package com.br.nupex.controleusuario.controle_usuario_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.nupex.controleusuario.controle_usuario_api.domain.user.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	Optional<User> findByEmail(String email);
}
