package br.com.rpg.controllers;

import br.com.rpg.model.EntidadeUsuario;
import br.com.rpg.service.ServicoUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/user")
@RequiredArgsConstructor
public class UserController {

  private final ServicoUsuario userService;

  @GetMapping()
  public List<EntidadeUsuario> list() {
    return userService.listar();
  }

  @GetMapping("/{id}")
  public EntidadeUsuario procurar(@PathVariable Long id) throws Exception {
    return userService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EntidadeUsuario criar(@RequestBody EntidadeUsuario user) {
    return userService.criar(user);
  }

  @PostMapping("/validar")
  public EntidadeUsuario validar(@RequestBody String nome, @RequestBody String senha) {
    EntidadeUsuario user = new EntidadeUsuario(null, nome, null, senha, null, null);
    EntidadeUsuario userValidado = userService.validar(user);
    if (userValidado.getId() != null) {
      return userValidado;
    } else {
      return user;
    }
  }

  @PutMapping("/{id}")
  public EntidadeUsuario modificar(@PathVariable Long id, @RequestBody EntidadeUsuario user)
      throws Exception {
    return userService.modificar(id, user);
  }

  @DeleteMapping("/{id}")
  public String deletar(@PathVariable Long id) throws Exception {
    return userService.deletar(id);
  }
}
