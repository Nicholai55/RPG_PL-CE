package br.com.rpg.controllers;

import br.com.rpg.service.ServicoBasicTeste;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/teste")
@RequiredArgsConstructor
public class ControllerTeste {

  private final ServicoBasicTeste basicService;

  @GetMapping("/all")
  public List<Tuple> listarMesas() {
    return basicService.listarvarioselementos_comerro(); // com falha
  }

  @GetMapping("/one")
  public List<String> listarUmaInformacao() {
    return basicService.listar();
  }

  @GetMapping("/1/{id}")
  public List<Long> busca1(@PathVariable Long id) {
    return basicService.buscarIdMesasUserMestra(id);
  }

  @GetMapping("/2/{id}")
  public List<Long> busca2(@PathVariable Long id) {
    return basicService.buscarIdMesasPersonagem(id);
  }

  @GetMapping("/3/{id}")
  public List<String> busca3(@PathVariable Long id) {
    return basicService.teste3(id);

  }

  @GetMapping("/4/{id}")
  public List<String> busca4(@PathVariable Long id) {

    return basicService.teste4(id);
  }

  @GetMapping("/5/{id}")
  public List<String> busca5(@PathVariable Long id) {

    return basicService.teste5();
  }
}
