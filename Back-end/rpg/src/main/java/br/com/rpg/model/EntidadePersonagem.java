package br.com.rpg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "personagens")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class EntidadePersonagem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String nome;
  @Column private String vida;
  @Column private String imagem;
  @Column private String ficha;

  @OneToOne
  @JoinColumn(name = "mesasId")
  @JsonIgnore
  private EntidadeMesa mesa;

  @ManyToOne
  @JoinColumn(name = "usuariosId")
  @JsonIgnore
  private EntidadeUsuario usuario;
}
