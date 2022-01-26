package br.com.fiap.shiftweb5.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SHIFT5_USUARIO")
public class UsuarioModel {
	
	@Id
	@Column(name="ID_USUARIO")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
    @SequenceGenerator( name="USUARIO_SEQ", initialValue = 1, allocationSize = 1)
	//AllocationSize é especie de um contador que define de quanto em quanto voce quer contar
	private Long idUsuario;
	
	@Column(name="CPF")
    private String cpf;
	
	@Column(name="NOME")
    private String nome;
    
	@Column(name="EMAIL")
    private String email;
    
	@Column(name="SENHA")
    private String senha;
	 
    @Column(name="IDADE")
    private Long idade;
    
    @Column(name="FOTO")
    private String foto;
    
    

	public UsuarioModel() {
		super();
	}
	
	

	public UsuarioModel(String cpf, String email) {
		super();
		this.cpf = cpf;
		this.email = email;
	}



	public UsuarioModel(Long idUsuario, String cpf, String nome, String email, String senha, Long idade, String foto) {
		super();
		this.idUsuario = idUsuario;
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.idade = idade;
		this.foto = foto;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getIdade() {
		return idade;
	}

	public void setIdade(Long idade) {
		this.idade = idade;
	}
	
	
	


	
	public String getFoto() {
		return foto;
	}



	public void setFoto(String foto) {
		this.foto = foto;
	}


	//Serve para ver todos os campos com os dados respectivos

	@Override
	public String toString() {
		return "UsuarioModel [idUsuario=" + idUsuario + ", cpf=" + cpf + ", nome=" + nome + ", email=" + email
				+ ", senha=" + senha + ", idade=" + idade + "]";
	}
	
	
    
    

}
