package br.com.rpg.web;

import br.com.rpg.controllers.UserController;
import br.com.rpg.model.EntidadeUsuario;
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

}
