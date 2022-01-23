package br.com.fiap.shiftweb5.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.shiftweb5.model.UsuarioModel;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
//	@GetMapping
//	public ResponseEntity<List<UsuarioModel>> findAll() {
//		
//		List<UsuarioModel> lista = new ArrayList<>();
//		lista.add( new UsuarioModel("191", "fmoreni1@gmail.com.br") );
//		lista.add( new UsuarioModel("192", "fmoreni2@gmail.com.br") );
//		lista.add( new UsuarioModel("193", "fmoreni3@gmail.com.br") );
//		
//		return ResponseEntity.ok(lista);
//	}
//	
//	@GetMapping("/idade/{idade}")
//	public ResponseEntity<UsuarioModel> findByIdade(@PathVariable("idade") Long id) {
//			return ResponseEntity.noContent().build();
//	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> findById(@PathVariable("id") Long idUsuario) {
		
		if ( idUsuario == 1 ) {
			UsuarioModel usuarioModel = new UsuarioModel("191", "fmoreni1@gmail.com.br");
			return ResponseEntity.ok(usuarioModel);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/{email}/{senha}")
	public ResponseEntity<UsuarioModel> findByEmailAndSenha(
		@PathVariable("email")	String paramEmail, 
		@PathVariable("senha")	String paramSenha) {
		
		System.out.println( paramEmail + paramSenha );
		
		UsuarioModel usuarioModel = new UsuarioModel("191", "fmoreni1@gmail.com.br");
		return ResponseEntity.ok(usuarioModel);
		
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping
	public ResponseEntity post(@RequestBody UsuarioModel usuarioModel ) {
		System.out.println(usuarioModel);
		
		if ( usuarioModel.getIdade() != null ) {
		
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(usuarioModel.getIdUsuario()).toUri();
			
			return ResponseEntity.created(location).build();
			
		} else {
			
			return ResponseEntity.badRequest()
					.body("Idade não informada");
			
		}
		
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") Long idUsuario,  
							  @RequestBody UsuarioModel usuarioModel) {
		
		System.out.println(usuarioModel);
		
		if (idUsuario != 1) {
			// Erro usuário não existe
			return ResponseEntity.notFound().build();
		} else if ( usuarioModel.getIdade() != null ) {
			// Sucesso
			return ResponseEntity.ok().build();
		} else {
			// Erro o Json está incorreto
			return ResponseEntity.badRequest()
					.body("Idade não informada");
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id ) {
		
		if (id != 1) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
}
