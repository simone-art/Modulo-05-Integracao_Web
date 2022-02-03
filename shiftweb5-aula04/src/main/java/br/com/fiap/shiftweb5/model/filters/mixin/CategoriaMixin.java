package br.com.fiap.shiftweb5.model.filters.mixin;

import br.com.fiap.shiftweb5.model.ProdutoModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public interface CategoriaMixin {

    @JsonIgnore
    List<ProdutoModel> getProdutos();
}
