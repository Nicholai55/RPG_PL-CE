package br.com.rpg.web;

import br.com.rpg.controllers.MesaController;
import br.com.rpg.controllers.PersonagemController;
import br.com.rpg.model.EntidadeMesa;
import br.com.rpg.model.EntidadePersonagem;
import br.com.rpg.service.ServicoMesa;
import br.com.rpg.service.ServicoPersonagem;
import br.com.rpg.web.webClass.FormMesa;
import br.com.rpg.web.webClass.Papel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@Controller
@RequestMapping
@RequiredArgsConstructor
public class GameController {

  private final ServicoMesa mesaService;
  private final ServicoPersonagem personService;

  @PostMapping("/game")
  public String mesaMestre(@ModelAttribute("mesa") FormMesa form, Model model) throws RuntimeException {

    if (form.getPapel() == Papel.MESTRE) {
      var mesaEscolhida = mesaService.findById(form.getIdMesa());
      model.addAttribute("mesa",mesaEscolhida);
      return "game/game_mestre";
    }

    if (form.getPapel() == Papel.JOGADOR) {
      var mesaEscolhida = mesaService.findById(form.getIdMesa());
      var personagem = personService.findById(form.getIdPersonagem());
      model.addAttribute("mesa",mesaEscolhida);
      model.addAttribute("personagem",personagem);
      return "game/game_player";
    }

    return "game/game_player";
  }

  @PostMapping("/alterar_nome_mesa")
  public String alterarNomeMesa (Model model, @ModelAttribute("nomeMesa")EntidadeMesa novosDados) {
    if (Objects.nonNull(novosDados.getNome())) {
      if (!novosDados.getNome().isEmpty()) {
        mesaService.modificar(novosDados.getId(),novosDados);
      }
    }
    var mesa = mesaService.findById(novosDados.getId());
    model.addAttribute("mesa", mesa);
    return "game/game_mestre";
  }

  @PostMapping("/alterar_nome_personagem")
  public String alterarNomePersonagem(Model model, @ModelAttribute("nomePersonagem")EntidadePersonagem novosDados) {
    if (Objects.nonNull(novosDados.getNome())) {
      if (!novosDados.getNome().isEmpty()) {
        personService.modificar(novosDados.getId(), novosDados);
      }
    }
    var personagem = personService.findById(novosDados.getId());
    var mesa = mesaService.findById(personagem.getMesa().getId());
    model.addAttribute("mesa",mesa);
    model.addAttribute("personagem",personagem);
    return "game/game_player";
  }



}
