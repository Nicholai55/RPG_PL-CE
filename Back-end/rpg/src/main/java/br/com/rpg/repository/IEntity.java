package br.com.rpg.repository;

import java.io.Serializable;

public interface IEntity<I> extends Serializable, IIdentity<I> {
  void onSave(String var1);

  default boolean isNew() {
    return this.getId() == null;
  }
}
