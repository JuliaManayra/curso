package com.juliamanayra.curso.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.juliamanayra.curso.domain.enums.TipoCliente;
import com.juliamanayra.curso.dto.ClienteNewDTO;
import com.juliamanayra.curso.resources.exceptions.FieldMessage;
import com.juliamanayra.curso.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		if(objDto.getTipo()==null) {
			list.add(new FieldMessage("tipo","Tipo nao pode ser nulo"));
		}else {
			if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCpf(objDto.getCpfOuCnpj())) {
				list.add(new FieldMessage("cpfOuCnpj","CPF é invalido"));

			}
			if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCnpj(objDto.getCpfOuCnpj())) {
				list.add(new FieldMessage("cpfOuCnpj","CNPJ é invalido"));

			}
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
