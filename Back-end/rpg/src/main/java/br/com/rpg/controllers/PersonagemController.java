package br.com.rpg.controllers;

import br.com.rpg.model.EntidadePersonagem;
import br.com.rpg.service.ServicoPersonagem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/person")
@RequiredArgsConstructor
public class PersonagemController {

  private final ServicoPersonagem personService;

  @GetMapping()
  public List<EntidadePersonagem> list() {
    return personService.listar();
  }

  @GetMapping("/{id}")
  public EntidadePersonagem procurar(@PathVariable Long id) throws RuntimeException {
    return personService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EntidadePersonagem criar(@RequestBody EntidadePersonagem user) {
    return personService.criar(user);
  }

  @PutMapping("/{id}")
  public EntidadePersonagem modificar(@PathVariable Long id, @RequestBody EntidadePersonagem user)
      throws RuntimeException {
    return personService.modificar(id, user);
  }

  @DeleteMapping("/{id}")
  public String deletar(@PathVariable Long id) throws RuntimeException {
    return personService.deletar(id);
  }
}
