package com.br.nupex.controleusuario.controle_usuario_api.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter // LOMBOK TA DANDO PROBLEMA POR ALGUMARAZAO MDS
@Setter // LOMBOK
@AllArgsConstructor // LOMBOK
@NoArgsConstructor // LOMBOK
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String name;
	private String email;
	private String password;

	// Construtor padrão
	public User() {
	}

	// Construtor com todos os parâmetros
	public User(String id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	// Getters e Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

// Lombok facilita o trabalho todo meu fi, ai o caba n precisa ta fazendo
// construtor nem geterzin nao papai. Tchamaaaa!!
