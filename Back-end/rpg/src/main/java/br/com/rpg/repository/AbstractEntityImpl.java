package br.com.rpg.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntityImpl<I> implements IEntity<I> {

  @QueryType(PropertyType.DATETIME)
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Column(name = "AUD_DH_ALTERACAO", updatable = false)
  private LocalDateTime updateIn = LocalDateTime.now();

  public LocalDateTime getUpdateIn() {
    return updateIn;
  }

  @Override
  public void onSave(String user) {}
}
