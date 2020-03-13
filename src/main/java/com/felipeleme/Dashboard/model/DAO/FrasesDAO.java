package com.felipeleme.Dashboard.model.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.felipeleme.Dashboard.base.BaseDAO;
import com.felipeleme.Dashboard.model.entity.Frase;


@Repository
public class FrasesDAO extends BaseDAO<Frase, Long>  {
    @PersistenceContext
    private EntityManager manager;


}
