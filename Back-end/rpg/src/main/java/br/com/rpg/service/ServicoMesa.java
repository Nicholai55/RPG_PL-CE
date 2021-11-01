package br.com.rpg.service;

import br.com.rpg.model.EntidadeMesa;
import br.com.rpg.model.Page;
import br.com.rpg.model.Pageable;
import br.com.rpg.model.QEntidadeMesa;
import br.com.rpg.repository.BasicRepository;
import br.com.rpg.repository.RepositorioMesa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ServicoMesa {

  private final RepositorioMesa mesaRepository;
  private final BasicRepository basicRepository;

  public List<String> findById2(Long id) {
    return basicRepository
        .query(EntidadeMesa.class)
        .select(QEntidadeMesa.entidadeMesa.nome)
        .where(QEntidadeMesa.entidadeMesa.mestre.id.eq(id))
        .fetch();
  }

  public Page<EntidadeMesa> findAll(int offset, int limit) {
    return basicRepository.findAll(EntidadeMesa.class, Pageable.of(offset, limit));
  }

  public List<EntidadeMesa> listarTodos() {
    return mesaRepository.findAll();
  }

  public EntidadeMesa criar(EntidadeMesa mesa) {
    return mesaRepository.save(mesa);
  }

  public EntidadeMesa findById(Long id) throws RuntimeException {
    return mesaRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Mesa nao encontrada"));
  }

  public String deletar(Long id) throws RuntimeException {
    EntidadeMesa mesa = findById(id);
    mesaRepository.delete(mesa);
    return "mesa deletada";
  }

  public EntidadeMesa modificar(Long id, EntidadeMesa mesaAlterada) throws RuntimeException {
    EntidadeMesa mesa = findById(id);
    if (Objects.nonNull(mesaAlterada.getNome())) {
      mesa.setNome(mesaAlterada.getNome());
    }
    if (Objects.nonNull(mesaAlterada.getMestre())) {
      mesa.setMestre(mesaAlterada.getMestre());
    }
    if (!ObjectUtils.isEmpty(mesaAlterada.getPersonagens())) {
      mesa.setPersonagens(mesaAlterada.getPersonagens());
    }
    return mesaRepository.save(mesa);
  }
}
