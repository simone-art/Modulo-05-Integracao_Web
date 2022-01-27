package br.com.fiap.shiftweb5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.shiftweb5.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
	
	//O repository tem métodos para fazer o insert, delete, update e buscar dados
	
	public UsuarioModel findByEmailAndSenha(String email, String senha);
	
	public UsuarioModel findByEmail(String email);

}
