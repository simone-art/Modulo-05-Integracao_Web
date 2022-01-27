package br.com.fiap.shiftweb5.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.fiap.shiftweb5.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	//UsuarioRepository usuarioRepositiry = new UsuarioRepository();
	
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> findAll() {
		
		List<UsuarioModel> lista = usuarioRepository.findAll();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/idade/{idade}")
	public ResponseEntity<UsuarioModel> findByIdade(@PathVariable("idade") Long id) {
			return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> findById(@PathVariable("id") Long idUsuario) {
		
		if ( usuarioRepository.existsById(idUsuario) ) {
			UsuarioModel usuarioModel = usuarioRepository.findById(idUsuario).get();
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
		
		UsuarioModel usuarioModel = usuarioRepository.findByEmailAndSenha(paramEmail, paramSenha);
		if(usuarioModel == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(usuarioModel);
		}
		
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping
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
	public ResponseEntity put(@PathVariable("id") Long idUsuario,  
							  @RequestBody UsuarioModel usuarioModel) {
		
		
		if (usuarioRepository.existsById(idUsuario)) {
			usuarioRepository.save(usuarioModel);
			//usuarioModel.setIdUsuario(idUsuario); linha de código necessária para o put funcionar corretamente
			usuarioModel.setIdUsuario(idUsuario);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long idUsuario ) {
		
		if (usuarioRepository.existsById(idUsuario)) {
			usuarioRepository.deleteById(idUsuario);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
}
