package br.com.rpg.repository;

import br.com.rpg.model.EntidadeMesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioMesa extends JpaRepository<EntidadeMesa, Long> {}
