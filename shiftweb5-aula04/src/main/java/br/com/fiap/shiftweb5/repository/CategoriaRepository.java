package br.com.fiap.shiftweb5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap.shiftweb5.model.CategoriaModel;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {

	@Override
	@Query(value=" SELECT c FROM CategoriaModel c WHERE ativo = 1 ")
	public List<CategoriaModel> findAll();
	
	
	@Query(value="SELECT COUNT(ATIVO) QUANTIDADE FROM shift5_categoria WHERE ATIVO = 1", 
			nativeQuery = true)
	public long getTotalCategorias();
	
	
	public List<CategoriaModel> findByAtivo(Boolean ativo);
	
	public List<CategoriaModel> findByNomeCategoriaContains(String parteNome);
	
	public List<CategoriaModel> findByNomeCategoriaLike(String parteNome);
	
}
