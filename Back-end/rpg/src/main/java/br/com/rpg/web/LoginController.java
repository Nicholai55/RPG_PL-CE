package br.com.rpg.web;

import br.com.rpg.controllers.UserController;
import br.com.rpg.model.EntidadeUsuario;
import br.com.rpg.service.ServicoBasicTeste;
import br.com.rpg.service.ServicoMesa;
import br.com.rpg.service.ServicoUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class LoginController {

  private final ServicoUsuario userService;
  private final ServicoMesa mesaService;
  private final ServicoBasicTeste basicService;

  @GetMapping("/login")
  public String login(Model model) {
    return "login/signup";
  }

  @PostMapping("/criar")
  public String criarUsuario(@ModelAttribute("user") EntidadeUsuario user) {
    UserController uc = new UserController(userService);
    uc.criar(user);
    return "login/signup";
  }

  @PostMapping("/homepage_principal")
  public String validaLogin(Model model, @ModelAttribute("user") EntidadeUsuario user) {
    UserController uc = new UserController(userService);

    EntidadeUsuario userValido = uc.validar(user.getNome(), user.getSenha());
    if (userValido.getId() != null) {
      System.out.println(userValido.getNome() + userValido.getSenha() + userValido.getEmail());
      EntidadeUsuario usuario = new EntidadeUsuario();
      try {
        usuario = uc.procurar(userValido.getId());
      } catch (Exception e) {
        e.printStackTrace();
      }
      // buscar as mesas de acordo com um usuario
      var mesas = basicService.buscarIdMesasUserMestra(usuario.getId());
      var mesas2 = usuario.getMesas();

      // buscar as mesas dos personagens do usuario
      var personagens = basicService.buscarIdMesasPersonagem(usuario.getId());
      var personagens2 = usuario.getPersonagens();

      model.addAttribute("id", usuario.getId());
      model.addAttribute("nome", usuario.getNome());
      model.addAttribute("email", usuario.getEmail());
      model.addAttribute("mesas", mesas2);
      model.addAttribute("personagens", personagens2);
      return "mesas/mesa";
    } else {
      return "login/signup";
    }
  }
}
