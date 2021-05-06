package projetoRpgWeb.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import projetoRpgWeb.excessoes.ExcessaoUserNaoEncontrado;
import projetoRpgWeb.usuario.EntidadeUsuario;
import projetoRpgWeb.usuario.UserDao;

@Transactional
@Controller("/usuarios")
public class UserController {

	@Autowired
	@Qualifier("JpaUserDao")
	private UserDao dao;

	@GetMapping
	public List<EntidadeUsuario> list() {
		return dao.lista();
	}

	@GetMapping("/{id}")
	public EntidadeUsuario procurar(@PathVariable Long id) throws ExcessaoUserNaoEncontrado {
		return dao.buscaPorId(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String create(@RequestBody EntidadeUsuario user) {
		dao.adiciona(user);
		return "Usuario Criado";
	}

	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) throws ExcessaoUserNaoEncontrado {
		EntidadeUsuario user = dao.buscaPorId(id);
		dao.remove(user);
		return "Usuario Removido";
	}

}