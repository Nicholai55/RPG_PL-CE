package br.com.rpg.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

// @NoArgsConstructor
@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class EntidadeUsuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome")
  private String nome;

  @Column(name = "email")
  private String email;

  @Column(name = "senha")
  private String senha;

  @OneToMany(
      mappedBy = "mestre",
      cascade = {CascadeType.ALL})
  private List<EntidadeMesa> mesas;

  @OneToMany(
      mappedBy = "usuario",
      cascade = {CascadeType.ALL})
  private List<EntidadePersonagem> personagens;
}
