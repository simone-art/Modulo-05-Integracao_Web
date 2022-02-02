package br.com.fiap.shiftweb5.controller;

import br.com.fiap.shiftweb5.model.CategoriaModel;
import br.com.fiap.shiftweb5.model.ProdutoModel;
import br.com.fiap.shiftweb5.model.UsuarioModel;
import br.com.fiap.shiftweb5.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping()
    public ResponseEntity<List<ProdutoModel>> findAll() {
        List<ProdutoModel> lista = produtoRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> findById(@PathVariable("id") Long id){
        if ( produtoRepository.existsById(id) ) {
            ProdutoModel model = produtoRepository.findById(id).get();

            return ResponseEntity.ok(model);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
