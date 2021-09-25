package br.com.rpg.repository;

import br.com.rpg.model.EntidadePersonagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPersonagem extends JpaRepository<EntidadePersonagem, Long> {}
