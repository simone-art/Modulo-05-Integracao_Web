package br.com.fiap.shiftweb5.model;

import javax.persistence.*;

@Entity
@Table(name="SHIFT5_Marca")
public class MarcaModel {

    @Id
    @Column(name = "ID_MARCA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MARCA_SEQ")
    @SequenceGenerator(name = "MARCA_SEQ", initialValue = 1, allocationSize = 1)
    private Long idMarca;

    @Column(name = "NOME_MARCA", length = 50, nullable = false)
    private String nomeMarca;

    public MarcaModel() {
    }

    public MarcaModel(Long idMarca, String nomeMarca) {
        this.idMarca = idMarca;
        this.nomeMarca = nomeMarca;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }


}
