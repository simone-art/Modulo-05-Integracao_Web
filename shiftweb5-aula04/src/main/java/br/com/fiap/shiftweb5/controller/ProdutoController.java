package br.com.fiap.shiftweb5.controller;

import br.com.fiap.shiftweb5.model.CategoriaModel;
import br.com.fiap.shiftweb5.model.MarcaModel;
import br.com.fiap.shiftweb5.model.ProdutoModel;
import br.com.fiap.shiftweb5.model.UsuarioModel;
import br.com.fiap.shiftweb5.model.filters.mixin.CategoriaMixin;
import br.com.fiap.shiftweb5.model.filters.mixin.MarcaMixin;
import br.com.fiap.shiftweb5.repository.ProdutoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

//    @GetMapping()
//    public ResponseEntity<List<ProdutoModel>> findAll() {
//        List<ProdutoModel> lista = produtoRepository.findAll();
//        return ResponseEntity.ok(lista);
//    }

    @GetMapping
    public ResponseEntity<JsonNode> findAll() throws JsonProcessingException {
        List<ProdutoModel> lista = produtoRepository.findAll() ;

        ObjectMapper mapper = new ObjectMapper()
                .addMixIn(CategoriaModel.class, CategoriaMixin.class)
                .addMixIn(MarcaModel.class, MarcaMixin.class);

        return ResponseEntity.ok( mapper.readTree( mapper.writeValueAsString(lista) ) );
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<ProdutoModel> findById(@PathVariable("id") Long id){
//        if ( produtoRepository.existsById(id) ) {
//            ProdutoModel model = produtoRepository.findById(id).get();
//
//            return ResponseEntity.ok(model);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonNode> findById(@PathVariable("id") Long id) throws JsonProcessingException {
        if ( produtoRepository.existsById(id) ) {
            ProdutoModel model = produtoRepository.findById(id).get();

            // ObjectMapper mapper = new ObjectMapper, mapea a CategoriaModel e o que deve ser ignorado
            //CategoriaMixin elimina a recursividade
            ObjectMapper mapper = new ObjectMapper()
                    .addMixIn(CategoriaModel.class, CategoriaMixin.class)
                    .addMixIn(MarcaModel.class, MarcaMixin.class);

            return ResponseEntity.ok(mapper.readTree(mapper.writeValueAsString(model)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
