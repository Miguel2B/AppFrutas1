package br.edu.uerr.loja.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.uerr.loja.modelo.Cliente;

import br.edu.uerr.loja.repositorio.ClienteRepositorio;


@Controller
public class ClienteControle {

	@Autowired
	ClienteRepositorio clienteRepositorio;
	
	
	 @GetMapping("/cliente")
	 public String abreCliente(Model model) {
		 model.addAttribute("listaClientes", clienteRepositorio.findAll());
		 return "cliente";
	 } 
	 
	 @GetMapping("/cadastroCliente") 
	 public String novo(Model model) {
		  Cliente cliente = new Cliente(); 
		  model.addAttribute("cliente",cliente);
		  return "formCliente";
	 }
	 
	 @GetMapping("/cadastroCliente/PF")
	 public String novoCliente1(Model model) {
		 Cliente cliente = new Cliente();
		 model.addAttribute("cliente", cliente);
		 return "formClientePF";
	 }
	 
	 @GetMapping("/cadastroCliente/PJ")
	 public String novoCliente(Model model) {
		 Cliente cliente = new Cliente();
		 model.addAttribute("cliente", cliente);
		 return "formCliente/PJ";
	 }
	 
	 @PostMapping("/salvarCliente")
	 public String salvar(@ModelAttribute("cliente") Cliente cliente, Model modelo) {
	  
		 clienteRepositorio.save(cliente);
	  
		 modelo.addAttribute("listaClientes", clienteRepositorio.findAll()); 
		 return "redirect:cliente"; 
	 }
}
	 
	/** @GetMapping("/editarCliente/PF/{id}")
	/	public String editCliente(@PathVariable("id") Integer id, Model modelo) {
			
			Cliente cliente = clienteRepositorio.findById(id)
					.orElseThrow(()->new IllegalArgumentException("Este cliente não existe"+id));
			
			modelo.addAttribute("cliente", cliente);
			return "formEmpresa";
	*/
