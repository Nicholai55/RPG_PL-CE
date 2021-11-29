package br.com.rpg.repository;

import br.com.rpg.model.EntidadeMesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RepositorioMesa extends JpaRepository<EntidadeMesa, Long> {

    @Query("SELECT u FROM EntidadeMesa u WHERE u.codigo = :codigo")
    EntidadeMesa findMesaByCodigo(@Param("codigo") String codigo);
}
