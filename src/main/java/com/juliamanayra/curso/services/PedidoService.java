package com.juliamanayra.curso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliamanayra.curso.domain.Pedido;
import com.juliamanayra.curso.repositories.PedidoRepository;
import com.juliamanayra.curso.services.exception.ObjectNotFoundExeption;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoundExeption("Objeto não encontrado! Id: "+ id + "Tipo: "+ Pedido.class.getName());
		}
		return obj;
	}
}
