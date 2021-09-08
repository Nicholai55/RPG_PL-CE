package br.com.rpg.service;

import br.com.rpg.model.EntidadePersonagem;
import br.com.rpg.repository.RepositorioPersonagem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public EntidadePersonagem findById(Long id) throws Exception {
    return personRepository
        .findById(id)
        .orElseThrow(() -> new Exception("Personagem nao encontrado"));
  }

  public String deletar(Long id) throws Exception {
    EntidadePersonagem user = findById(id);
    personRepository.delete(user);
    return ("usuario deletado");
  }

  public EntidadePersonagem modificar(Long id, EntidadePersonagem personagemAlterado)
      throws Exception {
    EntidadePersonagem person = findById(id);
    person.setNome(personagemAlterado.getNome());
    person.setVida(personagemAlterado.getVida());
    person.setImagem(personagemAlterado.getImagem());
    person.setFicha(personagemAlterado.getFicha());
    person.setMesa(personagemAlterado.getMesa());
    person.setUsuario(personagemAlterado.getUsuario());
    return personRepository.save(person);
  }
}
