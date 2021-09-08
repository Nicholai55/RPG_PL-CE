package br.com.rpg.service;

import br.com.rpg.model.EntidadeMesa;
import br.com.rpg.model.Page;
import br.com.rpg.model.Pageable;
import br.com.rpg.model.QEntidadeMesa;
import br.com.rpg.repository.BasicRepository;
import br.com.rpg.repository.RepositorioMesa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public EntidadeMesa findById(Long id) throws Exception {
    return mesaRepository.findById(id).orElseThrow(() -> new Exception("Mesa nao encontrada"));
  }

  public String deletar(Long id) throws Exception {
    EntidadeMesa mesa = findById(id);
    mesaRepository.delete(mesa);
    return "mesa deletada";
  }

  public EntidadeMesa modificar(Long id, EntidadeMesa mesaAlterada) throws Exception {
    EntidadeMesa mesa = findById(id);
    mesa.setNome(mesaAlterada.getNome());
    mesa.setMestre(mesaAlterada.getMestre());
    mesa.setPersonagens(mesaAlterada.getPersonagens());
    return mesaRepository.save(mesa);
  }
}
/*

  }
*/
