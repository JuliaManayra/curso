package com.juliamanayra.curso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.juliamanayra.curso.domain.Categoria;
import com.juliamanayra.curso.repositories.CategoriaRepository;
import com.juliamanayra.curso.services.exception.DataIntegrityExeption;
import com.juliamanayra.curso.services.exception.ObjectNotFoundExeption;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoundExeption("Objeto não encontrado! Id: "+ id + "Tipo: "+ Categoria.class.getName());
		}
		return obj;
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		}catch(DataIntegrityViolationException e){
			throw new DataIntegrityExeption("Não é possivel excluir uma categoria que possui produtos");
		}
	}
	
	public List<Categoria>  findAll() {
//		List<Categoria> lista = repo.findAll();
//		if(lista==null) {
//			throw new ObjectNotFoundExeption("Objeto não encontrado! Id: "+ id + "Tipo: "+ Categoria.class.getName());
//		}
		return repo.findAll();
	}
	
	
}
