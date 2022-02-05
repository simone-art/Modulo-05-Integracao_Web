package br.com.fiap.shiftweb5.model.filters.mixin;

import br.com.fiap.shiftweb5.model.CategoriaModel;
import br.com.fiap.shiftweb5.model.LojaModel;
import br.com.fiap.shiftweb5.model.ProdutoModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public interface LojaMixin {

    @JsonIgnore
//    List<ProdutoModel> getProdutos();
    List<LojaModel> getLojas();
    //List<CategoriaModel> getCategorias();
}
