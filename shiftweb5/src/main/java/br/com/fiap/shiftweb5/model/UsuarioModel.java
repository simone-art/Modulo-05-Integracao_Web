package br.com.fiap.shiftweb5.model;

public class UsuarioModel {
	
	private Long idUsuario;
	
    private String cpf;
    
    private String nome;
    
    private String email;
    
    private String senha;
    
    private Long idade;
    
    

	public UsuarioModel() {
		super();
	}
	
	

	public UsuarioModel(String cpf, String email) {
		super();
		this.cpf = cpf;
		this.email = email;
	}



	public UsuarioModel(Long idUsuario, String cpf, String nome, String email, String senha, Long idade) {
		super();
		this.idUsuario = idUsuario;
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.idade = idade;
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


	//Serve

	@Override
	public String toString() {
		return "UsuarioModel [idUsuario=" + idUsuario + ", cpf=" + cpf + ", nome=" + nome + ", email=" + email
				+ ", senha=" + senha + ", idade=" + idade + "]";
	}
	
	
    
    

}
