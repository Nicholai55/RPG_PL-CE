package projetoRpgWeb.usuario.testando;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projetoRpgWeb.usuario.EntidadeUsuario;


@Repository
public interface RepositorioUsuario extends JpaRepository<EntidadeUsuario, Long> {


}
