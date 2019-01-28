package com.juliamanayra.curso.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.juliamanayra.curso.domain.Cliente;

public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Preenchimento obrigatorio")
	@Length(min=5, max=120, message="O tamnho deve ter entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")	
	private String email;
	

//	private String cpfOuCnpj;
	
	
	public ClienteDTO() {
		
	}

	public ClienteDTO(Cliente cliente) {
		this.id= cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
//		this.cpfOuCnpj = cliente.getCpfOuCnpj();
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

//	public String getCpfOuCnpj() {
//		return cpfOuCnpj;
//	}

//	public void setCpfOuCnpj(String cpfOuCnpj) {
//		this.cpfOuCnpj = cpfOuCnpj;
//	}
	
	
}
