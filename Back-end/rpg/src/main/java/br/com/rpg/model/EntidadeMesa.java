package br.com.rpg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mesas")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class EntidadeMesa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String nome;

  @OneToOne
  @JoinColumn(name = "usuariosId")
  @JsonIgnore
  private EntidadeUsuario mestre;

  @OneToMany private List<EntidadePersonagem> personagens;

  @Column
  private String codigo;

}

// @JsonIgnore
