package br.com.rpg.service;

import br.com.rpg.model.EntidadePersonagem;
import br.com.rpg.repository.RepositorioPersonagem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ServicoPersonagem {

  private final RepositorioPersonagem personRepository;

  public List<EntidadePersonagem> listar() {
    return personRepository.findAll();
  }

  public EntidadePersonagem criar(EntidadePersonagem user) {
    return personRepository.save(user);
  }

  public EntidadePersonagem findById(Long id) throws RuntimeException {
    return personRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Personagem nao encontrado"));
  }

  public String deletar(Long id) throws RuntimeException {
    EntidadePersonagem user = findById(id);
    personRepository.delete(user);
    return ("usuario deletado");
  }

  public EntidadePersonagem modificar(Long id, EntidadePersonagem personagemAlterado)
      throws RuntimeException {
    EntidadePersonagem person = findById(id);
    if (Objects.nonNull(personagemAlterado.getNome())) {
      person.setNome(personagemAlterado.getNome());
    }
    if (Objects.nonNull(personagemAlterado.getVida())) {
      person.setVida(personagemAlterado.getVida());
    }
    if (Objects.nonNull(personagemAlterado.getImagem())) {
      person.setImagem(personagemAlterado.getImagem());
    }
    if (Objects.nonNull(personagemAlterado.getFicha())) {
      person.setFicha(personagemAlterado.getFicha());
    }
    if (!ObjectUtils.isEmpty(personagemAlterado.getMesa())) {
      person.setMesa(personagemAlterado.getMesa());
    }
    if (!ObjectUtils.isEmpty(personagemAlterado.getUsuario())) {
      person.setUsuario(personagemAlterado.getUsuario());
    }
    return personRepository.save(person);
  }
}
