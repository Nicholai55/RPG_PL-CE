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

  @GetMapping
  public String mesaGame() {
    return "game/game";
  }

  @PostMapping
  public String mesaMestre(@ModelAttribute("mesa") FormMesa form, Model model) throws Exception {

    if (form.getPapel() == Papel.MESTRE) {
      var mc = new MesaController(mesaService);
      var mesaEscolhida = mc.procurar(form.getIdMesa());
      model.addAttribute("mesa",mesaEscolhida);
      return "game/game";
    }

    if (form.getPapel() == Papel.JOGADOR) {
      var mc = new MesaController(mesaService);
      var mesaEscolhida = mc.procurar(form.getIdMesa());
      var pc = new PersonagemController(personService);
      var personagem = pc.procurar(form.getIdPersonagem());
      model.addAttribute("mesa",mesaEscolhida);
      model.addAttribute("personagem",personagem);
      return "game/game";
    }

    return "game/game";
  }

}
