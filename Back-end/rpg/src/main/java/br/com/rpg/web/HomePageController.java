package br.com.rpg.web;

import br.com.rpg.model.EntidadeMesa;
import br.com.rpg.model.EntidadePersonagem;
import br.com.rpg.model.EntidadeUsuario;
import br.com.rpg.service.ServicoMesa;
import br.com.rpg.service.ServicoPersonagem;
import br.com.rpg.service.ServicoUsuario;
import br.com.rpg.web.webClass.FormMesa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Controller("/")
@RequestMapping
@RequiredArgsConstructor
public class HomePageController {

  private final ServicoMesa mesaService;
  private final ServicoUsuario userService;
  private final ServicoPersonagem personService;

  @GetMapping()
  public String homepage() {
    return "homepage/homepage";
  }

  @GetMapping("/homepage")
  public String homepageToo() {
    return "homepage/homepage";
  }

  @PostMapping("/nova_mesa")
  public String criandoNovaMesa(Model model, @ModelAttribute("idMestre") FormMesa userId) {
    EntidadeUsuario user = userService.findById(userId.getIdJogador());
    String codigoAleatorio = criarCodigo();
    EntidadeMesa mesa = EntidadeMesa.builder()
            .mestre(user)
            .nome("Nova Mesa")
            .codigo(codigoAleatorio)
            .build();
    mesaService.criar(mesa);

    var mesas = user.getMesas();
    var personagens = user.getPersonagens();

    model.addAttribute("id", user.getId());
    model.addAttribute("nome", user.getNome());
    model.addAttribute("email", user.getEmail());
    model.addAttribute("mesas", mesas);
    model.addAttribute("personagens", personagens);
    return "mesas/mesa";

  }

  @PostMapping("/entrando_na_mesa")
  public String entrandoNaMesa(Model model, @ModelAttribute("codigoMesa") FormMesa formMesa) {
    var user = userService.findById(formMesa.getIdJogador());
    var mesa = mesaService.findBycodigo(formMesa.getCodigoMesa());
    var personagem = criaPersonagem(user, mesa);

    var personagensDoUsuario = user.getPersonagens();
    personagensDoUsuario.add(personagem);
    atualizaUsuario(user, personagensDoUsuario);

    var personagensDaMesa = mesa.getPersonagens();
    personagensDaMesa.add(personagem);
    atualizaMesa(mesa, personagensDaMesa);

    var mesas = user.getMesas();
    var personagens = user.getPersonagens();

    model.addAttribute("id", user.getId());
    model.addAttribute("nome", user.getNome());
    model.addAttribute("email", user.getEmail());
    model.addAttribute("mesas", mesas);
    model.addAttribute("personagens", personagens);
    return "mesas/mesa";
  }

  @PostMapping("/teste")
  public String teste(
      @RequestParam(name = "name", required = false, defaultValue = "world") String name,
      Model model) {
    model.addAttribute("name", name);
    return "teste/teste";
  }

  @GetMapping("/teste")
  public String form() {
    return "teste/Form";
  }

  private String criarCodigo() {
  int leftLimit = 48; // numeral '0'
  int rightLimit = 122; // letter 'z'
  int targetStringLength = 10;
  Random random = new Random();
  var generatedString = random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
  System.out.println(generatedString);
  return generatedString;
  }

  private EntidadePersonagem criaPersonagem(EntidadeUsuario user, EntidadeMesa mesa) {
    var personagem = EntidadePersonagem
            .builder()
            .nome("Novo Personagem")
            .ficha("Nova Ficha")
            .vida("100")
            .usuario(user)
            .mesa(mesa)
            .build();

    var personagemsalvo = personService.criar(personagem);
    return personagemsalvo;
  }

  private EntidadeUsuario atualizaUsuario(EntidadeUsuario user, List<EntidadePersonagem> personagensDoUsuario) {
    var usuarioAtualizado = EntidadeUsuario
            .builder()
            .personagens(personagensDoUsuario)
            .build();
    return userService.modificar(user.getId(), usuarioAtualizado);
  }

  private EntidadeMesa atualizaMesa(EntidadeMesa mesa, List<EntidadePersonagem> personagensDaMesa) {
    var mesaAtualizada = EntidadeMesa
            .builder()
            .personagens(personagensDaMesa)
            .build();
    return mesaService.modificar(mesa.getId(), mesaAtualizada);
  }

}
