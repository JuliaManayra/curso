package com.juliamanayra.curso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliamanayra.curso.domain.Categoria;
import com.juliamanayra.curso.repositories.CategoriaRepository;
import com.juliamanayra.curso.services.exception.ObjectNotFoundExeption;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoundExeption("Objeto não encontrado! Id: "+ id + "Tipo: "+ Categoria.class.getName());
		}
		return obj;
	}
}
