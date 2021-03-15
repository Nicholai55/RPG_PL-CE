package cesusc.rpg.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cesusc.rpg.usuario.EntidadeUsuario;
import cesusc.rpg.usuario.ServicoUsuario;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final ServicoUsuario userService;
	
	@GetMapping
	public List<EntidadeUsuario> lista() {
		return userService.lista();
	}
	
	@GetMapping("/{id}")
	public EntidadeUsuario procurar(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntidadeUsuario criar(@RequestBody EntidadeUsuario user) {
		return userService.criar(user);
	}
}
