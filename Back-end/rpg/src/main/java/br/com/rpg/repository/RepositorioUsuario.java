package br.com.rpg.repository;

import br.com.rpg.model.EntidadeUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends JpaRepository<EntidadeUsuario, Long> {}
