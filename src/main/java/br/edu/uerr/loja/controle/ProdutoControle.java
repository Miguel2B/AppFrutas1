package br.edu.uerr.loja.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
	
import br.edu.uerr.loja.modelo.Produto;
import br.edu.uerr.loja.repositorio.EmpresaRepositorio;
import br.edu.uerr.loja.repositorio.FornecedorRepositorio;
import br.edu.uerr.loja.repositorio.ProdutoRepositorio;

@Controller
public class ProdutoControle {

	@Autowired
	ProdutoRepositorio produtoRepositorio;
	
	@Autowired
	EmpresaRepositorio empresaRepositorio;
	
	@Autowired
	FornecedorRepositorio fornecedorRepositorio;
	
	//listar
	@GetMapping("/produto")
	public String abreProduto (Model model) {
		model.addAttribute("listaProdutos", produtoRepositorio.findAll());
		return "produto";
	}
	//form
	@GetMapping("/cadastroProduto")
	public String novoProduto (Model model) {
		Produto produto = new Produto();
		 model.addAttribute("listaEmpresas", empresaRepositorio.findAll());
		 
		 model.addAttribute("listaFornecedores", fornecedorRepositorio.findAll()); 
		
		model.addAttribute("produto", produto);
		return "formProduto";
	}
	
	@PostMapping("/salvarProduto")
		public String salvar(@ModelAttribute("produto") Produto produto, Model model) {
		
		produtoRepositorio.save(produto);
		
		model.addAttribute("listaProdutos", produtoRepositorio.findAll());
		return "redirect:produto";
	}
	
	@GetMapping("/editarProduto/{id}")
	public String editProduto(@PathVariable("id") Integer id, Model model) {
		
		Produto produto = produtoRepositorio.findById(id)
				.orElseThrow(()->new IllegalArgumentException("Este produto não existe"+id));
		
		model.addAttribute("listaEmpresas", empresaRepositorio.findAll());
		 
		model.addAttribute("listaFornecedores", fornecedorRepositorio.findAll()); 
		
		model.addAttribute("produto", produto);
		return "formProduto";
	}
	
	@GetMapping("/deletarProduto/{id}")
	public String delProduto(@PathVariable("id") Integer id, Model model) {
		
		Produto produto= produtoRepositorio.findById(id)
				.orElseThrow(()->new IllegalArgumentException("Este prodkuto não existe"+id));
		produtoRepositorio.delete(produto);
		
		model.addAttribute("listaProdutos", produtoRepositorio.findAll());
		return "produto";
	}
}
