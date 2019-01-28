package com.juliamanayra.curso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.juliamanayra.curso.domain.Cliente;
import com.juliamanayra.curso.dto.ClienteDTO;
import com.juliamanayra.curso.repositories.ClienteRepository;
import com.juliamanayra.curso.services.exception.DataIntegrityExeption;
import com.juliamanayra.curso.services.exception.ObjectNotFoundExeption;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoundExeption("Objeto não encontrado! Id: "+ id + "Tipo: "+ Cliente.class.getName());
		}
		return obj;
	}
//	public Cliente insert(Cliente obj) {
//		obj.setId(null);
//		return repo.save(obj);
//	}
//	
	public Cliente fromDTO(ClienteDTO obj) {
		return new Cliente(obj.getId(),obj.getNome(),obj.getEmail(),null,null);
	}
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}
	
	private void  updateData(Cliente newObj,Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		}catch(DataIntegrityViolationException e){
			throw new DataIntegrityExeption("Não é possivel excluir pois há entidades relacionadas");
		}
	}
	
	public List<Cliente>  findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest =  new PageRequest(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
}
