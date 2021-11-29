package br.com.rpg.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class HomePageController {

    @GetMapping()
    public String homepage() {
        return "homepage/homepage";
    }

    @GetMapping("/homepage")
    public String homepageToo() {
        return "homepage/homepage";
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

}
