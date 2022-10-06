package br.edu.uerr.loja.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.uerr.loja.modelo.Vendas;
import br.edu.uerr.loja.repositorio.ClienteRepositorio;
import br.edu.uerr.loja.repositorio.ProdutoRepositorio;
import br.edu.uerr.loja.repositorio.VendasRepositorio;

@Controller
public class VendasControle {

	
	@Autowired
	VendasRepositorio vendasRepositorio;
	
	@Autowired
	ClienteRepositorio clienteRepositorio;
	
	@Autowired
	ProdutoRepositorio produtoRepositorio;

	@GetMapping("/vendas")
	public String abreVendas(Model modelo) {
		modelo.addAttribute("listaVendas", vendasRepositorio.findAll());
		return "vendas";
	}

	@GetMapping("/cadastroVendas") 
	 public String novoVendas (Model model) {
		  Vendas vendas = new Vendas(); 
		  model.addAttribute("listaClientes", clienteRepositorio.findAll());
		  
		  model.addAttribute("listaProdutos", produtoRepositorio.findAll());
		  
		  model.addAttribute("vendas",vendas);
		  
		  return "formVendas"; 
	 }
	  	
	 @PostMapping("/salvarVendas")
	 public String salvar(@ModelAttribute("vendas") Vendas vendas, Model model) {
	  
		 vendasRepositorio.save(vendas);
	  
		 model.addAttribute("listaVendas", vendasRepositorio.findAll()); 
		 return "redirect:vendas"; 
	  
	 }
	 
	 @GetMapping("/editarVendas/{id}")
		public String editVendas(@PathVariable("id") Integer id, Model modelo) {
			
			Vendas vendas= vendasRepositorio.findById(id)
					.orElseThrow(()->new IllegalArgumentException("Esta venda não existe"+id));
			
			modelo.addAttribute("listaClientes", clienteRepositorio.findAll());
			  
			  modelo.addAttribute("listaProdutos", produtoRepositorio.findAll());
			  
			modelo.addAttribute("vendas", vendas);
			return "formVendas";
		}
	 
	 @GetMapping("/deletarVendas/{id}") 
	 	public String delVendas(@PathVariable("id") Integer id, Model modelo) {
		  
		 	Vendas vendas = vendasRepositorio.findById(id) 
		 			.orElseThrow(()->new IllegalArgumentException("Esta venda não existe"+id));
		 	vendasRepositorio.delete(vendas); 
		  
			modelo.addAttribute("listaVendas", vendasRepositorio.findAll());
			return "vendas";
}
}
