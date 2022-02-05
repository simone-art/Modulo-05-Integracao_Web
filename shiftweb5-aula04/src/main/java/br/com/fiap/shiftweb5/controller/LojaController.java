package br.com.fiap.shiftweb5.controller;

import br.com.fiap.shiftweb5.model.CategoriaModel;
import br.com.fiap.shiftweb5.model.LojaModel;
import br.com.fiap.shiftweb5.model.MarcaModel;
import br.com.fiap.shiftweb5.model.ProdutoModel;
import br.com.fiap.shiftweb5.model.filters.mixin.CategoriaMixin;
import br.com.fiap.shiftweb5.model.filters.mixin.LojaMixin;
import br.com.fiap.shiftweb5.model.filters.mixin.MarcaMixin;
import br.com.fiap.shiftweb5.repository.LojaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loja")
public class LojaController {

    @Autowired
    public LojaRepository lojaRepository;
//
//    @CrossOrigin
//    @GetMapping("/{id}")
//    public ResponseEntity<LojaModel> findById(@PathVariable("id") Long id){
//        if ( lojaRepository.existsById(id) ) {
//            LojaModel model = lojaRepository.findById(id).get();
//
//            return ResponseEntity.ok(model);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<JsonNode> findById(@PathVariable("id") Long id)
            throws JsonProcessingException {

        if (lojaRepository.existsById(id)) {
            LojaModel model = lojaRepository.findById(id).get();

            ObjectMapper mapper = new ObjectMapper()
                    .addMixIn(ProdutoModel.class, LojaMixin.class)
                    .addMixIn(CategoriaModel.class, CategoriaMixin.class)
                    .addMixIn(MarcaModel.class, MarcaMixin.class);

            return ResponseEntity.ok(mapper.readTree(mapper.writeValueAsString(model)));
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
