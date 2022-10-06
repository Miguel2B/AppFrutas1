package br.edu.uerr.loja.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.uerr.loja.modelo.Fornecedor;
import br.edu.uerr.loja.repositorio.FornecedorRepositorio;

@Controller
public class FornecedorControle {
	
	@Autowired
	FornecedorRepositorio fornecedorRepositorio;
	
	@GetMapping("/fornecedor")
	public String abreFornecedor (Model model) {
		model.addAttribute("listaFornecedores", fornecedorRepositorio.findAll());
		return "fornecedor";
	}
	
	@GetMapping("/cadastroFornecedor")
	public String novoFornecedor (Model model) {
		Fornecedor fornecedor = new Fornecedor();
		model.addAttribute("fornecedor", fornecedor);
		return "formFornecedor";
	}

	
	@PostMapping("/salvarFornecedor")
		public String salvar(@ModelAttribute("fornecedor") Fornecedor fornecedor, Model model) {
			
		fornecedorRepositorio.save(fornecedor);
			
		model.addAttribute("listaFornecedores", fornecedorRepositorio.findAll());
		return "redirect:fornecedor";
	}
	
	@GetMapping("/editarFornecedor/{id}")
	public String editFornecedor(@PathVariable("id") Integer id, Model model) {
		
		Fornecedor fornecedor = fornecedorRepositorio.findById(id)
				.orElseThrow(()->new IllegalArgumentException("Este fornecedor não existe"+id));
		
		model.addAttribute("fornecedor", fornecedor);
		return "formFornecedor";
	}
	
	@GetMapping("/deletarFornecedor/{id}")
	public String delFornecedor(@PathVariable("id") Integer id, Model model) {
		
		Fornecedor fornecedor = fornecedorRepositorio.findById(id)
				.orElseThrow(()->new IllegalArgumentException("Este fornecedor não existe"+id));
		fornecedorRepositorio.delete(fornecedor);
		
		model.addAttribute("listaFornecedores", fornecedorRepositorio.findAll());
		return "fornecedor";
	}
		
}
