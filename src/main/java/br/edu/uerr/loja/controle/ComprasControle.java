package br.edu.uerr.loja.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.uerr.loja.modelo.Compras;

import br.edu.uerr.loja.repositorio.ComprasRepositorio;

import br.edu.uerr.loja.repositorio.FornecedorRepositorio;

import br.edu.uerr.loja.repositorio.ProdutoRepositorio;

@Controller
public class ComprasControle {

	
	@Autowired
	ComprasRepositorio comprasRepositorio;
	
	@Autowired
	FornecedorRepositorio fornecedorRepositorio;
	
	@Autowired
	ProdutoRepositorio produtoRepositorio;

	@GetMapping("/compras")
	public String abreCompras(Model modelo) {
		modelo.addAttribute("listaCompras", comprasRepositorio.findAll());
		return "compras";
	}

	@GetMapping("/cadastroCompras") 
	 public String novoCompras (Model model) {
		  Compras compras = new Compras(); 
		  model.addAttribute("listaFornecedor", fornecedorRepositorio.findAll());
		  
		  model.addAttribute("listaProdutos", produtoRepositorio.findAll());
		  
		  model.addAttribute("compras",compras);
		  
		  return "formCompras"; 
	 }
	  	
	 @PostMapping("/salvarCompras")
	 public String salvar(@ModelAttribute("compras") Compras compras, Model model) {
	  
		 comprasRepositorio.save(compras);
	  
		 model.addAttribute("listaCompras", comprasRepositorio.findAll()); 
		 return "redirect:compras"; 
	  
	 }
	 
	 @GetMapping("/editarCompras/{id}")
		public String editCompras(@PathVariable("id") Integer id, Model modelo) {
			
			Compras compras = comprasRepositorio.findById(id)
					.orElseThrow(()->new IllegalArgumentException("Esta compra não existe"+id));
			
			modelo.addAttribute("listaFornecedor", fornecedorRepositorio.findAll());
			  
			modelo.addAttribute("listaProdutos", produtoRepositorio.findAll());
			  
			modelo.addAttribute("compras", compras);
			return "formCompras";
		}
	 
	 @GetMapping("/deletarCompras/{id}") 
	 	public String delCompras(@PathVariable("id") Integer id, Model modelo) {
		  
		 	Compras compras = comprasRepositorio.findById(id) 
		 			.orElseThrow(()->new IllegalArgumentException("Esta compra não existe"+id));
		 	comprasRepositorio.delete(compras); 
		  
			modelo.addAttribute("listaCompras", comprasRepositorio.findAll());
			return "compras";
	 }
}
