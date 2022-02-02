package br.com.fiap.shiftweb5.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.shiftweb5.model.UsuarioModel;
import br.com.fiap.shiftweb5.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
//http://localhost:8080/usuario
//Request Method
@CrossOrigin("*")
public class UsuarioController {


	@Autowired
	UsuarioRepository usuarioRepository;
	//UsuarioRepository usuarioRepository = new UsuarioRepository();
	

	
	
	@GetMapping("/idade/{idade}")
	public ResponseEntity<UsuarioModel> findByIdade(@PathVariable("idade") Long id) {
			return ResponseEntity.noContent().build();
	}
	
	
	
	
	@GetMapping("/{email}/{senha}")
	public ResponseEntity<UsuarioModel> findByEmailAndSenha(
		@PathVariable("email")	String paramEmail, 
		@PathVariable("senha")	String paramSenha) {
		
		UsuarioModel usuarioModel = 
				usuarioRepository.findByEmailAndSenha(paramEmail, paramSenha);
		
		if ( usuarioModel == null ) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(usuarioModel);
		}
		
	}
	

	
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> findAll() {
		List<UsuarioModel> lista = usuarioRepository.findAll() ;
		return ResponseEntity.ok(lista);
	}

	
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> findById(@PathVariable("id") Long id) {
		
		if ( usuarioRepository.existsById(id) ) {
			UsuarioModel model = usuarioRepository.findById(id).get();
			return ResponseEntity.ok(model);
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping
	@CrossOrigin("*")
	public ResponseEntity post(@RequestBody UsuarioModel usuarioModel ) {
		System.out.println(usuarioModel);
		
		if ( usuarioModel.getIdade() != null ) {
			
			usuarioRepository.save(usuarioModel);
		
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
	public ResponseEntity put(@PathVariable("id") Long codigoUsuario,  
							  @RequestBody UsuarioModel usuarioModel) {
		
		if ( usuarioRepository.existsById(codigoUsuario) ) {
			usuarioModel.setIdUsuario(codigoUsuario);
			usuarioRepository.save(usuarioModel);
			return ResponseEntity.ok().build();
		} else { 
			return ResponseEntity.notFound().build();
		}
		
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id ) {
		
		if ( usuarioRepository.existsById(id) ) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else { 
			return ResponseEntity.notFound().build();
		}
		
	}

	
}
