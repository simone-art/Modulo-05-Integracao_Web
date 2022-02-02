package br.com.fiap.shiftweb5.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.shiftweb5.model.CategoriaModel;
import br.com.fiap.shiftweb5.model.UsuarioModel;
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
	
//	@CrossOrigin
//	@GetMapping()
//	//Este método só vai trazer os elementos que estiverem ativos
//	public ResponseEntity<List<CategoriaModel>> findAll(){
//		return ResponseEntity.ok( categoriaRepository.findByAtivo(true) );
//	}
	
	
//	@CrossOrigin
//	@GetMapping()
//	//Este método só vai trazer os elementos do nomeCategoria que contém as letras ne
//		public ResponseEntity<List<CategoriaModel>> findAll(){
//			return ResponseEntity.ok( categoriaRepository.findByNomeCategoriaContains("ne") );
//		}
	
//	@CrossOrigin
//	@GetMapping()
//	//Este método só vai exibir os nomeCategoria que tem a letra T como inicial
//		public ResponseEntity<List<CategoriaModel>> findAll(){
//			return ResponseEntity.ok( categoriaRepository.findByNomeCategoriaLike("T%") );
//		}
	
//	@CrossOrigin
//	@GetMapping()
//	//Este método só vai exibir todas as categorias que estão ativas
//		public ResponseEntity<List<CategoriaModel>> findAll(){
//		
//		return ResponseEntity.ok( categoriaRepository.findAll() );
//		}
	
	@CrossOrigin
	@GetMapping()
	//Este método só vai exibir todas as categorias que estão ativas
		public ResponseEntity<Long> findAll(){
		
		return ResponseEntity.ok( categoriaRepository.getTotalCategorias() );
		}
	
	
		
	
//	@GetMapping
//	public ResponseEntity<List<CategoriaModel>> findAll() {
//		
//		List<CategoriaModel> lista = categoriaRepository.findAll();
//		return ResponseEntity.ok(lista);
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<CategoriaModel> findiById(@PathVariable("id") Long idCategoria){
//		
//		if(categoriaRepository.existsById(idCategoria)) {
//			CategoriaModel categoriaModel = categoriaRepository.findById(idCategoria).get();
//			return ResponseEntity.ok(categoriaModel);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//		
//	}
//	
//	@SuppressWarnings("rawtypes")
//	@PostMapping
//	public ResponseEntity post(@RequestBody CategoriaModel categoriaModel) {
//		if(categoriaModel.getNomeCategoria() != null) {
//			categoriaRepository.save(categoriaModel);
//			
//			URI location = ServletUriComponentsBuilder
//					.fromCurrentRequest()
//					.path("/{id}")
//					.buildAndExpand(categoriaModel.getIdCategoria()).toUri();
//			return ResponseEntity.created(location).build();
//			
//		}else {
//			return ResponseEntity.badRequest()
//					.body("O nome da categoria não foi informado");
//		}
//	}
//	
//	//Para o putMapping funcionar tem que ser pasado o ID no body request lá no Postamn
//	
//	@PutMapping("/{id}")
//	public ResponseEntity put(@PathVariable("id") Long idCategoria, @RequestBody CategoriaModel categoriaModel) {
//		if(categoriaRepository.existsById(idCategoria)) {
//			categoriaRepository.save(categoriaModel);
//			categoriaModel.setIdCategoria(idCategoria);
//			return ResponseEntity.ok().build();
//		}else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//	
//	
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity delete(@PathVariable("id") Long idCategoria) {
//		if(categoriaRepository.existsById(idCategoria)) {
//			categoriaRepository.deleteById(idCategoria);
//			return ResponseEntity.ok().build();
//		} else {
//			return ResponseEntity.noContent().build();
//		}
//	}
//	
//	

	
	
	

}
