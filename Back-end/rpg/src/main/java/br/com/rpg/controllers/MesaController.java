package br.com.rpg.controllers;

import br.com.rpg.model.EntidadeMesa;
import br.com.rpg.service.ServicoMesa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/mesas")
@RequiredArgsConstructor
public class MesaController {

  private final ServicoMesa mesaService;

  @GetMapping
  public List<EntidadeMesa> list() {
    return mesaService.listarTodos();
  }

  @GetMapping("/{id}")
  public EntidadeMesa procurar(@PathVariable Long id) throws RuntimeException {
    return mesaService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EntidadeMesa criar(@RequestBody EntidadeMesa mesa) {
    return mesaService.criar(mesa);
  }

  @PutMapping("/{id}")
  public EntidadeMesa modificar(@PathVariable Long id, @RequestBody EntidadeMesa mesa)
      throws RuntimeException {
    return mesaService.modificar(id, mesa);
  }

  @DeleteMapping("/{id}")
  public String deletar(@PathVariable Long id) throws RuntimeException {
    return mesaService.deletar(id);
  }
}
