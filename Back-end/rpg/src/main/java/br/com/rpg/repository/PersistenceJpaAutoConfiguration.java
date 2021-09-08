package br.com.rpg.repository;

import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class PersistenceJpaAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public JPQLTemplates jpqlTemplates(EntityManager em) {
    return JPAProvider.getTemplates(em);
  }

  @Bean
  @ConditionalOnMissingBean
  public PathBuilderFactory pathBuilderFactory() {
    return new PathBuilderFactory();
  }
}
