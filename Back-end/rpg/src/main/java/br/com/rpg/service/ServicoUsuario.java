package br.com.rpg.service;

import br.com.rpg.model.EntidadeUsuario;
import br.com.rpg.repository.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

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

  public EntidadeUsuario findById(Long id) throws RuntimeException {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));
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

  public String deletar(Long id) throws RuntimeException {
    EntidadeUsuario user = findById(id);
    userRepository.delete(user);
    return ("usuario deletado");
  }

  public EntidadeUsuario modificar(Long id, EntidadeUsuario userAlterado) throws RuntimeException {
    EntidadeUsuario user = findById(id);
    if (Objects.nonNull(userAlterado.getNome())) {
      user.setNome(userAlterado.getNome());
    }
    if (Objects.nonNull(userAlterado.getEmail())) {
      user.setEmail(userAlterado.getEmail());
    }
    if (Objects.nonNull(userAlterado.getSenha())) {
      user.setSenha(userAlterado.getSenha());
    }
    if (!ObjectUtils.isEmpty(userAlterado.getMesas())) {
      user.setMesas(userAlterado.getMesas());
    }
    if (!ObjectUtils.isEmpty(userAlterado.getPersonagens())) {
      user.setPersonagens(userAlterado.getPersonagens());
    }

    return userRepository.save(user);
  }
}
