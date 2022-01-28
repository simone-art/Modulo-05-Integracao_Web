package br.com.fiap.shiftweb5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fiap.shiftweb5.model.UsuarioModel;
import br.com.fiap.shiftweb5.repository.UsuarioRepository;

@Service
public class ValidarIdadeUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public String validarIdadeUsuario(UsuarioModel usuarioModel) {
		if(usuarioModel.getIdade() >= 18) {
			return "Você está autorizado para ingresar no portal";	
		}else {
			return "Você não está autorizado para ingresar no portal";
		}
	}
	
	

}
