package br.com.fiap.shiftweb5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.shiftweb5.model.CategoriaModel;
import br.com.fiap.shiftweb5.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaModel> findById(@PathVariable("id") Long id){
		if ( categoriaRepository.existsById(id) ) {
			CategoriaModel model = categoriaRepository.findById(id).get();
			
			return ResponseEntity.ok(model);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@CrossOrigin
	@GetMapping()
	public ResponseEntity<Long> findAll(){
		return ResponseEntity.ok( categoriaRepository.getTotalCategorias() );
	}
	
}
