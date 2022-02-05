package br.com.fiap.shiftweb5.controller;

import br.com.fiap.shiftweb5.model.*;
import br.com.fiap.shiftweb5.model.filters.mixin.CategoriaMixin;
import br.com.fiap.shiftweb5.model.filters.mixin.LojaMixin;
import br.com.fiap.shiftweb5.model.filters.mixin.MarcaMixin;
import br.com.fiap.shiftweb5.repository.ProdutoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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


    //O método ProdutoRepository.FindAll retorna a lista de produtos,
    // porém o JPA/Hibernate efetuar uma query por cada linha da tabela de produtos.
    //O que precisamos fazer para resolver esse problema, ou seja,
    // a consulta toda acontecer em apenas uma query?

//    @GetMapping
//    public ResponseEntity<JsonNode> findAll() throws JsonProcessingException {
//        List<ProdutoModel> lista = produtoRepository.findAll() ;
//
//        ObjectMapper mapper = new ObjectMapper()
//                .addMixIn(ProdutoModel.class, LojaMixin.class)
//                .addMixIn(CategoriaModel.class, CategoriaMixin.class)
//                .addMixIn(MarcaModel.class, MarcaMixin.class);
//
//        return ResponseEntity.ok( mapper.readTree( mapper.writeValueAsString(lista) ) );
//    }

//    @GetMapping
//    public ResponseEntity<List<ProdutoModel>> findAll() {
//        List<ProdutoModel> lista = produtoRepository.findAll() ;
//
//        return ResponseEntity.ok(lista);
//    }

        @GetMapping
    public ResponseEntity<JsonNode> findAll() throws JsonProcessingException {
        List<ProdutoModel> lista = produtoRepository.findAll() ;

        ObjectMapper mapper = new ObjectMapper()
                .addMixIn(ProdutoModel.class, LojaMixin.class);
        return ResponseEntity.ok( mapper.readTree( mapper.writeValueAsString(lista) ) );
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


    @GetMapping("/novos")
    public ResponseEntity<JsonNode> findAllOrderByDataLancamentoDesc() throws JsonProcessingException{
        List<ProdutoModel> lista = produtoRepository.findAll( Sort.by(Sort.Direction.DESC, "dataLancamento") );
        ObjectMapper mapper = new ObjectMapper()
                .addMixIn(MarcaModel.class, MarcaMixin.class)
                .addMixIn(CategoriaModel.class, CategoriaMixin.class);
        return ResponseEntity.ok( mapper.readTree(  mapper.writeValueAsString(lista) ) );
    }

    @GetMapping("/precoDesc")
    public ResponseEntity<JsonNode> findAllOrderByPrecoDesc() throws JsonProcessingException{
        List<ProdutoModel> lista = produtoRepository.findAll( Sort.by(Sort.Direction.DESC, "preco") );
        ObjectMapper mapper = new ObjectMapper()
                .addMixIn(MarcaModel.class, MarcaMixin.class)
                .addMixIn(CategoriaModel.class, CategoriaMixin.class);
        return ResponseEntity.ok( mapper.readTree(  mapper.writeValueAsString(lista) ) );
    }

    @GetMapping("/precoAsc")
    public ResponseEntity<JsonNode> findAllOrderByPrecoAsc() throws JsonProcessingException{
        List<ProdutoModel> lista = produtoRepository.findAll( Sort.by(Sort.Direction.ASC, "preco") );
        ObjectMapper mapper = new ObjectMapper()
                .addMixIn(MarcaModel.class, MarcaMixin.class);
        mapper.addMixIn(CategoriaModel.class, CategoriaMixin.class);
        return ResponseEntity.ok( mapper.readTree(  mapper.writeValueAsString(lista) ) );
    }

    @GetMapping("/nome")
    public ResponseEntity<JsonNode> findByNomeContains(@RequestParam("nome") String nome) throws JsonProcessingException{
        List<ProdutoModel> lista = produtoRepository.findByNomeContains( nome );
        ObjectMapper mapper = new ObjectMapper()
                .addMixIn(MarcaModel.class, MarcaMixin.class)
                .addMixIn(CategoriaModel.class, CategoriaMixin.class);

        return ResponseEntity.ok(mapper.readTree(  mapper.writeValueAsString(lista) ));
    }



    @GetMapping("/{id}")
    public ResponseEntity<JsonNode> findById(@PathVariable("id") Long id)
            throws JsonProcessingException {

        if ( produtoRepository.existsById(id) ) {
            ProdutoModel model = produtoRepository.findById(id).get();

            ObjectMapper mapper = new ObjectMapper()
                    .addMixIn(CategoriaModel.class, CategoriaMixin.class)
                    .addMixIn(MarcaModel.class, MarcaMixin.class);

            return ResponseEntity.ok( mapper.readTree( mapper.writeValueAsString(model) ) );
        } else {
            return ResponseEntity.notFound().build();
        }

    }


}
