package br.com.fiap.shiftweb5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.fiap.shiftweb5.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>  {
	
	
	public UsuarioModel findByEmailAndSenha(String email, String senha);
	
	
	//delete
	/*
	public void delete() {
	
		string sql = "DELETE FROM tabele WHERE id = ?";
		
		abrir conexao com o banco
		abrir statament
		executar(sql)
		pegar a reposta
		retorno
	
	}
	*/
	
	//insert
	
	//update
	
	//buscar
	/*
	public void buscarTodos() {
	
		string sql = "SELECT * FROM tabela ";
		
		abrir conexao com o banco
		abrir statament
		executar(sql)
		
		while ( proximo == true ) {
			monta uma lista
		}
		
		retorno lista;
	
	}
	*/
	
	
}
