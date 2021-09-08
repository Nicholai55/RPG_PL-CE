package br.com.rpg.repository;

import br.com.rpg.model.Page;
import br.com.rpg.model.Pageable;
import br.com.rpg.model.Sort;
import com.mysema.commons.lang.CloseableIterator;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.QBean;
import com.querydsl.core.types.dsl.ComparablePath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@Transactional
public class BasicRepository {

  @PersistenceContext EntityManager em;

  JPAQueryFactory jpaQueryFactory;

  @Autowired PathBuilderFactory pathBuilderFactory;

  @PostConstruct
  public void init() {
    jpaQueryFactory = new JPAQueryFactory(this.em);
  }

  @Deprecated
  public <T> List<T> findAll(EntityPath<T> entityClass, final Predicate... where) {
    return (List<T>) query(entityClass).where(where).fetch();
  }

  public <T> List<T> findAll(
      Class<T> entityClass, OrderSpecifier<?> specifier, final Predicate... where) {
    return (List<T>)
        query(pathBuilderFactory.create(entityClass)).where(where).orderBy(specifier).fetch();
  }

  public <T> List<T> findAll(Class<T> entityClass, final Predicate... where) {
    return (List<T>) query(pathBuilderFactory.create(entityClass)).where(where).fetch();
  }

  public <T> CloseableIterator<T> iterator(
      Class<T> entityClass, Sort sort, final Predicate... where) {
    JPAQuery<?> query = query(pathBuilderFactory.create(entityClass));
    if (Objects.nonNull(sort)) {
      applySorting(query, sort);
    }
    return (CloseableIterator<T>) query.where(where).iterate();
  }

  public <T> CloseableIterator<T> iterator(Class<T> entityClass, final Predicate... where) {
    return iterator(entityClass, null, where);
  }

  public <T> T find(Class<T> entityClass, Serializable id) {
    return em.find(entityClass, id);
  }

  public <T> Page<T> findAll(
      final Class<T> entityClass,
      final Pageable pageable,
      QBean<?> select,
      final Predicate... where) {
    JPAQuery<?> query = query(pathBuilderFactory.create(entityClass)).where(where);
    int offset = pageable.getOffset();
    int limit = pageable.getLimit();

    long total = query.fetchCount();
    query.offset(offset);
    if (Objects.nonNull(select)) {
      query.select(select);
    }
    query.limit(limit);

    applySorting(query, pageable.getSort());

    List<T> content = (List<T>) query.fetch();

    return new Page<>(content, pageable, total);
  }

  public <T> Page<T> findAll(
      final Class<T> entityClass, final Pageable pageable, final Predicate... where) {
    return (Page<T>) findAll(entityClass, pageable, null, where);
  }

  public <T> Page<T> findAll(final Class<T> entityClass, final Pageable pageable) {
    JPAQuery<?> query = query(pathBuilderFactory.create(entityClass));
    int offset = pageable.getOffset();
    int limit = pageable.getLimit();

    long total = query.fetchCount();
    query.offset(offset);
    query.limit(limit);
    applySorting(query, pageable.getSort());

    List<T> content = (List<T>) query.fetch();

    return new Page<>(content, pageable, total);
  }

  public <T> JPAQuery<T> applySorting(JPAQuery<T> query, Sort sort) {
    Optional.ofNullable(sort).ifPresent(s -> s.forEach(query::orderBy));
    return query;
  }

  @Deprecated
  public <T> List<T> findAll(
      EntityPath<T> entityClass, int limit, int offset, final Predicate... where) {
    return (List<T>) query(entityClass).where(where).offset(offset).limit(limit).fetch();
  }

  @Deprecated
  public <T> Optional<T> findOne(EntityPath<T> entityClass, final Predicate... where) {
    return (Optional<T>) Optional.ofNullable(query(entityClass).where(where).fetchOne());
  }

  public <T> Optional<T> findFirst(Class<T> entityClass, final Predicate... where) {
    return Optional.ofNullable(query(entityClass).where(where).fetchFirst());
  }

  public <T> Optional<T> findOne(Class<T> entityClass, final Predicate... where) {
    return Optional.ofNullable(query(entityClass).where(where).fetchOne());
  }

  public <T> JPAQuery<T> query(Class<T> entityClass) {
    return (JPAQuery<T>) query(pathBuilderFactory.create(entityClass));
  }

  @Deprecated
  public JPAQuery<?> query() {
    return jpaQueryFactory.query();
  }

  @Deprecated
  public JPAQuery<?> query(EntityPath<?> entityClass) {
    return jpaQueryFactory.from(entityClass);
  }

  public <T extends AbstractEntityImpl> List<T> saveAll(Collection<T> entities) {
    return entities.stream().map(this::save).collect(Collectors.toList());
  }

  public <T extends AbstractEntityImpl> T save(T entity) {
    return save(entity, false);
  }

  public <T extends AbstractEntityImpl> T save(T entity, boolean skipAudity) {
    if (!entity.isNew()) {
      this.em.merge(entity);
    } else {
      this.em.persist(entity);
    }
    this.em.flush();
    return entity;
  }

  @Deprecated
  public <T> T findByUUID(EntityPath<T> entityClass, ComparablePath<UUID> entityId, UUID id) {
    return (T) query(entityClass).where(entityId.eq(id)).fetchOne();
  }

  @Deprecated
  public boolean exists(EntityPath<?> entityClass, final Predicate... where) {
    return query(entityClass).where(where).fetchCount() > 0;
  }

  public boolean exists(Class<?> entityClass, final Predicate... where) {
    return query(pathBuilderFactory.create(entityClass)).where(where).fetchCount() > 0;
  }

  public boolean notExists(Class<?> entityClass, final Predicate... where) {
    return query(pathBuilderFactory.create(entityClass)).where(where).fetchCount() == 0;
  }

  @Deprecated
  public long delete(EntityPath<?> entityClass, final Predicate... where) {
    return new JPAQueryFactory(this.em).delete(entityClass).where(where).execute();
  }

  public long delete(Class<?> entityClass, final Predicate... where) {
    return new JPAQueryFactory(this.em)
        .delete(pathBuilderFactory.create(entityClass))
        .where(where)
        .execute();
  }

  public long count(Class<?> entityClass, final Predicate... where) {
    return query(pathBuilderFactory.create(entityClass)).where(where).fetchCount();
  }

  public <T> long update(Class<T> entityClass, Consumer<JPAUpdateClause> consumer) {
    PathBuilder<T> entityPath = pathBuilderFactory.create(entityClass);
    JPAUpdateClause clause = new JPAUpdateClause(em, entityPath);
    consumer.accept(clause);
    return clause.execute();
  }
}
