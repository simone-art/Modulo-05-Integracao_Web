package br.com.fiap.shiftweb5.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="SHIFT5_PRODUTO")
public class ProdutoModel {

    @Id
    @Column(name="ID_PRODUTO")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "PRODUTO_SEQ")
    @SequenceGenerator( name = "PRODUTO_SEQ", initialValue = 1, allocationSize = 1)
    private Long idProduto;

    @Column(name="NOME_PRODUTO")
    private String nomeProduto;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SKU")
    private String sku;

    @Column(name = "FOTO")
    private String foto;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "PRECO")
    private float preco;

    @Column(name = "CARACTERISTICAS")
    private String caracteristicas;

    @Column(name = "DATA_LANCAMENTO")
    private Date dataLancamento;

    //(fetch = FetchType.EAGER), como vai ser carregado o categoriaModel
    //nullable = false atrela o produto a categoria. sem ele Produto nao pode existir
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ID_CATEGORIA", nullable = false)
    private CategoriaModel categoriaModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ID_MARCA", nullable = false)
    private MarcaModel marcaModel;

    public ProdutoModel() {
    }

    public ProdutoModel(Long idProduto, String nomeProduto, String nome, String sku, String foto, String descricao, float preco, String caracteristicas, Date dataLancamento) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.nome = nome;
        this.sku = sku;
        this.foto = foto;
        this.descricao = descricao;
        this.preco = preco;
        this.caracteristicas = caracteristicas;
        this.dataLancamento = dataLancamento;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
