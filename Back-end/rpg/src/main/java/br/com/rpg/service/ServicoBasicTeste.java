package br.com.rpg.service;

import br.com.rpg.model.EntidadeMesa;
import br.com.rpg.model.EntidadePersonagem;
import br.com.rpg.model.QEntidadeMesa;
import br.com.rpg.model.QEntidadePersonagem;
import br.com.rpg.repository.BasicRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoBasicTeste {

  private final BasicRepository basicRepository;

  public List<Tuple> listarvarioselementos_comerro() {
    var retorno =
        basicRepository
            .query(EntidadePersonagem.class)
            .select(
                QEntidadePersonagem.entidadePersonagem.nome,
                QEntidadePersonagem.entidadePersonagem.vida,
                QEntidadePersonagem.entidadePersonagem.mesa.id,
                QEntidadePersonagem.entidadePersonagem.mesa.mestre.nome)
            // .where(QEntidadePersonagem.entidadePersonagem.usuario.id.eq(1L))
            .fetch();
    System.out.println(retorno.stream().toList());
    return retorno;
  }

  public List<String> listar() {
    return basicRepository
        .query(EntidadePersonagem.class)
        .select(QEntidadePersonagem.entidadePersonagem.mesa.nome)
        .where(QEntidadePersonagem.entidadePersonagem.mesa.id.eq(1L))
        .fetch();
  }

  public List<Long> buscarIdMesasUserMestra(Long id) {
    return basicRepository
        .query(EntidadeMesa.class)
        .select(QEntidadeMesa.entidadeMesa.id)
        .where(QEntidadeMesa.entidadeMesa.mestre.id.eq(id))
        .fetch();
  }

  public List<Long> buscarIdMesasPersonagem(Long id) {
    return basicRepository
        .query(EntidadePersonagem.class)
        .select(QEntidadePersonagem.entidadePersonagem.mesa.id)
        .where(QEntidadePersonagem.entidadePersonagem.usuario.id.eq(id))
        .fetch();
  }

  public List<String> teste3(Long id) {
    var query =
        basicRepository
            .query(EntidadeMesa.class)
            .select(QEntidadeMesa.entidadeMesa.id, QEntidadeMesa.entidadeMesa.nome)
            .where(QEntidadeMesa.entidadeMesa.mestre.id.eq(id))
            .fetch();
    var tup = query.get(0);
    List<String> lista = new ArrayList<>();
    lista.add(tup.get(0, Long.class).toString());
    lista.add(tup.get(1, String.class));

    return lista.stream().toList();
  }

  public List<String> teste4(Long id) {
    var query =
        basicRepository
            .query(EntidadePersonagem.class)
            .select(
                QEntidadePersonagem.entidadePersonagem.mesa.id,
                QEntidadePersonagem.entidadePersonagem.mesa.nome)
            .where(QEntidadePersonagem.entidadePersonagem.usuario.id.eq(id))
            .fetch();
    var tup = query.get(0);
    List<String> lista = new ArrayList<>();
    lista.add(tup.get(0, Long.class).toString());
    lista.add(tup.get(1, String.class));

    return lista.stream().toList();
  }

  public List<String> teste5() {
    var query =
        basicRepository
            .query(EntidadeMesa.class)
            .select(QEntidadeMesa.entidadeMesa)
            .where(QEntidadeMesa.entidadeMesa.mestre.id.eq(1L))
            .fetch();

    System.out.println(query);

    return null;
  }

  //    System.out.println(query);
  //    var tup = query.get(0);
  //    System.out.println(tup);
  //    var teste1 = tup.toString();
  //    System.out.println(teste1);
  //    var teste2 = tup.get(0,Long.class);
  //    System.out.println(teste2);
  //    var teste3 = tup.get(1,String.class);
  //    System.out.println(teste3);
  //    var teste4 = tup.get(2,String.class);
  //    System.out.println(teste4);
  //    List<String> lista = new ArrayList<>();
  //    lista.add(teste2.toString());
  //    lista.add(teste3);
  //    lista.add(teste4);
  //    System.out.println(lista.stream().toList());

}
