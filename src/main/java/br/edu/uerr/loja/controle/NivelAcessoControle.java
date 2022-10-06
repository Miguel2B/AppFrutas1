package br.edu.uerr.loja.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import br.edu.uerr.loja.modelo.NivelAcesso;
import br.edu.uerr.loja.repositorio.NivelAcessoRepositorio;

@Controller
public class NivelAcessoControle {

		@Autowired
		NivelAcessoRepositorio nivelAcessoRepositorio;

		@GetMapping("/nivelAcesso")
		public String abreNivelAcesso(Model modelo) {
			modelo.addAttribute("listaNivelAcesso", nivelAcessoRepositorio.findAll());
			return "nivelAcesso";
		}

		@GetMapping("/cadastroNivelAcesso") 
		 public String novoNivelAcesso (Model model) {
			  NivelAcesso nivelAcesso = new NivelAcesso(); 
			  model.addAttribute("nivelAcesso",nivelAcesso);
			  return "formNivelAcesso"; 
		 }
		  	
		 @PostMapping("/salvarNivelAcesso")
		 public String salvar(@ModelAttribute("nivelAcesso") NivelAcesso nivelAcesso, Model modelo) {
		  
			 nivelAcessoRepositorio.save(nivelAcesso);
		  
			 modelo.addAttribute("listaNivelAcesso", nivelAcessoRepositorio.findAll()); 
			 return "redirect:nivelAcesso"; 
		  
		 }
		 
		 @GetMapping("/editarNivelAcesso/{id}")
			public String editNivelAcesso(@PathVariable("id") Integer id, Model modelo) {
				
				NivelAcesso nivelAcesso = nivelAcessoRepositorio.findById(id)
						.orElseThrow(()->new IllegalArgumentException("Este nível de acesso não existe"+id));
				
				modelo.addAttribute("nivelAcesso", nivelAcesso);
				return "formNivelAcesso";
			}
		 
		@GetMapping("/deletarNivelAcesso/{id}") 
		public String delNivelAcesso(@PathVariable("id") Integer id, Model modelo) {
			  
			NivelAcesso nivelAcesso = nivelAcessoRepositorio.findById(id) 
				 .orElseThrow(()->new IllegalArgumentException("Este nível de acesso não existe"+id));
			nivelAcessoRepositorio.delete(nivelAcesso); 
			  
				modelo.addAttribute("listaNivelAcesso", nivelAcessoRepositorio.findAll());
				return "nivelAcesso";
			  }

}
