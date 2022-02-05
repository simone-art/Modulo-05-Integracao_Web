package br.com.fiap.shiftweb5.controller;

import br.com.fiap.shiftweb5.model.*;
import br.com.fiap.shiftweb5.model.filters.mixin.CategoriaMixin;
import br.com.fiap.shiftweb5.model.filters.mixin.MarcaMixin;
import br.com.fiap.shiftweb5.repository.ProdutoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/marcas")
    public ResponseEntity<JsonNode> findByMarcas(@RequestParam("idMarca") Long[] idMarcas) throws JsonProcessingException {

        Set<MarcaModel> setMarcas = new HashSet<>();
        MarcaModel marcaModel;
        for( Long id: idMarcas ) {
            marcaModel = new MarcaModel();
            marcaModel.setIdMarca(id);
            setMarcas.add(marcaModel);
        }

        List<ProdutoModel> lista = produtoRepository.findByMarcaModelIn(setMarcas) ;

        ObjectMapper mapper = new ObjectMapper()
                .addMixIn(CategoriaModel.class, CategoriaMixin.class)
                .addMixIn(MarcaModel.class, MarcaMixin.class);

        return ResponseEntity.ok( mapper.readTree( mapper.writeValueAsString(lista) ) );
    }



}
