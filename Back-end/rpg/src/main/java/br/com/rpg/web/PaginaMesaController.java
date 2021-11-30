package br.com.rpg.web;

import br.com.rpg.controllers.UserController;
import br.com.rpg.model.EntidadeMesa;
import br.com.rpg.model.EntidadePersonagem;
import br.com.rpg.model.EntidadeUsuario;
import br.com.rpg.service.ServicoBasicTeste;
import br.com.rpg.service.ServicoMesa;
import br.com.rpg.service.ServicoPersonagem;
import br.com.rpg.service.ServicoUsuario;
import br.com.rpg.web.webClass.FormMesa;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class PaginaMesaController {

    private final ServicoBasicTeste basicService;
    private final ServicoMesa mesaService;
    private final ServicoUsuario userService;
    private final ServicoPersonagem personService;

    @Autowired
    private HttpServletRequest request;

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
    public String entrandoNaMesa(Model model) { // Model model, @ModelAttribute("codigoMesa") String codigo,@ModelAttribute("userId") String id
        var codigoMesa = request.getHeader("codigoMesa");
        var userId = request.getHeader("userId");
        var user = userService.findById(Long.valueOf(userId));
        var mesa = mesaService.findBycodigo(codigoMesa);
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
