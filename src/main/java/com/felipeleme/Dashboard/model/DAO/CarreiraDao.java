package com.felipeleme.Dashboard.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.felipeleme.Dashboard.base.BaseDAO;
import com.felipeleme.Dashboard.model.entity.Carreira;

@Repository
public class CarreiraDao extends BaseDAO<Carreira, Long> {
    @PersistenceContext
    private EntityManager manager;

    public List<Carreira> readByUserId(Long id) {
	String jpql = new StringBuilder("SELECT C FROM Carreira C ").append("WHERE C.usuarioId = ?1").toString();
	return createQuery(jpql, id);
    }

    public List<Carreira> getByNameAndUserId(String idCarreira, Long id) {
	String jpql = new StringBuilder("SELECT C FROM Carreira C ")
		.append("WHERE C.usuarioId = ?1 and C.nome = ?2")
		.toString();
	return createQuery(jpql, id, idCarreira );
    }
}
