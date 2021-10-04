package br.com.rpg.web.webClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class FormMesa {

    private Long idMesa;
    private Long idPersonagem;
    private Papel papel;
}
