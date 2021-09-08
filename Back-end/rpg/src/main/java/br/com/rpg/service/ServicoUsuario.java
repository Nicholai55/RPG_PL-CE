package br.com.rpg.service;

import br.com.rpg.model.EntidadeUsuario;
import br.com.rpg.repository.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoUsuario {

  private final RepositorioUsuario userRepository;

  public List<EntidadeUsuario> listar() {
    return userRepository.findAll();
  }

  public EntidadeUsuario criar(EntidadeUsuario user) {
    return userRepository.save(user);
  }

  public EntidadeUsuario findById(Long id) throws Exception {
    return userRepository.findById(id).orElseThrow(() -> new Exception("Cliente nao encontrado"));
  }

  public EntidadeUsuario validar(EntidadeUsuario user) {

    EntidadeUsuario usuarioCorreto = new EntidadeUsuario();
    List<EntidadeUsuario> lista = userRepository.findAll();

    for (EntidadeUsuario usuario : lista) {
      if (usuario.getNome().equals(user.getNome())) {
        if (usuario.getSenha().equals(user.getSenha())) {
          usuarioCorreto.setId(usuario.getId());
          usuarioCorreto.setNome(usuario.getNome());
          usuarioCorreto.setSenha(usuario.getSenha());
          usuarioCorreto.setEmail(usuario.getEmail());
          usuarioCorreto.setMesas(usuario.getMesas());
          usuarioCorreto.setPersonagens(usuario.getPersonagens());
          return usuarioCorreto;
        }
      }
    }
    return user;
  }

  public String deletar(Long id) throws Exception {
    EntidadeUsuario user = findById(id);
    userRepository.delete(user);
    return ("usuario deletado");
  }

  public EntidadeUsuario modificar(Long id, EntidadeUsuario userAlterado) throws Exception {
    EntidadeUsuario user = findById(id);
    user.setNome(userAlterado.getNome());
    user.setEmail(userAlterado.getEmail());
    user.setSenha(userAlterado.getSenha());
    user.setMesas(userAlterado.getMesas());
    user.setPersonagens(userAlterado.getPersonagens());
    return userRepository.save(user);
  }
}
