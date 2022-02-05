package br.com.fiap.shiftweb5.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SHIFT5_LOJA")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="idLoja")
public class LojaModel {

    private Long idLoja;
    private String nomeLoja;
    private List<ProdutoModel> produtos;

    public LojaModel() {
    }

    public LojaModel(Long idLoja, String nomeLoja) {
        super();
        this.idLoja = idLoja;
        this.nomeLoja = nomeLoja;
    }

    @Id
    @Column(name = "ID_LOJA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOJA_SEQ")
    @SequenceGenerator(name = "LOJA_SEQ", initialValue = 1, allocationSize = 1)
    public Long getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(Long idLoja) {
        this.idLoja = idLoja;
    }

    @Column(name = "NOME_LOJA")
    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    @ManyToMany(mappedBy = "lojas")
    public List<ProdutoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoModel> produtos) {
        this.produtos = produtos;
    }
}
