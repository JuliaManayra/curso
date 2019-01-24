package com.juliamanayra.curso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliamanayra.curso.domain.Cliente;
import com.juliamanayra.curso.repositories.ClienteRepository;
import com.juliamanayra.curso.services.exception.ObjectNotFoundExeption;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Cliente obj = repo.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoundExeption("Objeto não encontrado! Id: "+ id + "Tipo: "+ Cliente.class.getName());
		}
		return obj;
	}
}
