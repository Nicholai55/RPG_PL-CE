package br.com.rpg.web;

import br.com.rpg.controllers.MesaController;
import br.com.rpg.controllers.PersonagemController;
import br.com.rpg.model.EntidadeMesa;
import br.com.rpg.service.ServicoMesa;
import br.com.rpg.service.ServicoPersonagem;
import br.com.rpg.web.webClass.FormMesa;
import br.com.rpg.web.webClass.Papel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

  private final ServicoMesa mesaService;
  private final ServicoPersonagem personService;

  @GetMapping("/game_mestre")
  public String mesaGame() {
    return "game/game_mestre";
  }

  @GetMapping("/game_player")
  public String mesaGame2() {
    return "game/game_player";
  }

  @PostMapping
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

    return "game/game";
  }

}
