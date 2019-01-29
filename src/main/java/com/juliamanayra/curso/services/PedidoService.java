package com.juliamanayra.curso.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juliamanayra.curso.domain.ItemPedido;
import com.juliamanayra.curso.domain.PagamentoComBoleto;
import com.juliamanayra.curso.domain.Pedido;
import com.juliamanayra.curso.domain.enums.EstadoPagamento;
import com.juliamanayra.curso.repositories.ItemPedidoRepository;
import com.juliamanayra.curso.repositories.PagamentoRepository;
import com.juliamanayra.curso.repositories.PedidoRepository;
import com.juliamanayra.curso.services.exception.ObjectNotFoundExeption;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);
		if(obj==null) {
			throw new ObjectNotFoundExeption("Objeto não encontrado! Id: "+ id + "Tipo: "+ Pedido.class.getName());
		}
		return obj;
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto,obj.getInstante());
		}
		repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.save(obj.getItens());
		return obj;
	}
	
}
