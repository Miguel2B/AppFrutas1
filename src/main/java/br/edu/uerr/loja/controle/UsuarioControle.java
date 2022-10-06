package br.edu.uerr.loja.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import br.edu.uerr.loja.modelo.Usuario;
import br.edu.uerr.loja.repositorio.EmpresaRepositorio;
import br.edu.uerr.loja.repositorio.NivelAcessoRepositorio;
import br.edu.uerr.loja.repositorio.UsuarioRepositorio;

@Controller
public class UsuarioControle {

	
	@Autowired
	UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	EmpresaRepositorio empresaRepositorio;
	
	@Autowired
	NivelAcessoRepositorio nivelAcessoRepositorio;

	@GetMapping("/usuario")
	public String abreUsuario(Model modelo) {
		modelo.addAttribute("listaUsuario", usuarioRepositorio.findAll());
		return "usuario";
	}

	@GetMapping("/cadastroUsuario") 
	 public String novoUsuario (Model model) {
		  Usuario usuario = new Usuario(); 
		  model.addAttribute("listaEmpresas", empresaRepositorio.findAll());
		  
		  model.addAttribute("listaNivelAcesso", nivelAcessoRepositorio.findAll());
		  
		  model.addAttribute("usuario",usuario);
		  
		  return "formUsuario"; 
	 }
	  	
	 @PostMapping("/salvarUsuario")
	 public String salvar(@ModelAttribute("usuario") Usuario usuario, Model model) {
	  
		 usuarioRepositorio.save(usuario);
	  
		 model.addAttribute("listaUsuario", usuarioRepositorio.findAll()); 
		 return "redirect:usuario"; 
	  
	 }
	 
	 @GetMapping("/editarUsuario/{id}")
		public String editUsuario(@PathVariable("id") Integer id, Model modelo) {
			
			Usuario usuario = usuarioRepositorio.findById(id)
					.orElseThrow(()->new IllegalArgumentException("Este usuário não existe"+id));
			
			modelo.addAttribute("listaNivelAcesso", nivelAcessoRepositorio.findAll());
			  
			modelo.addAttribute("listaEmpresas", empresaRepositorio.findAll());
			
			modelo.addAttribute("usuario", usuario);
			return "formUsuario";
		}
	 
	 @GetMapping("/deletarUsuario/{id}") 
	 	public String delUsuario(@PathVariable("id") Integer id, Model modelo) {
		  
		 	Usuario usuario = usuarioRepositorio.findById(id) 
		 			.orElseThrow(()->new IllegalArgumentException("Este usuário não existe"+id));
		 	usuarioRepositorio.delete(usuario); 
		  
			modelo.addAttribute("listaUsuario", usuarioRepositorio.findAll());
			return "usuario";
	 }
}
