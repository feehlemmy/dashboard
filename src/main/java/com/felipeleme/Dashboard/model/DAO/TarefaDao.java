package com.felipeleme.Dashboard.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.felipeleme.Dashboard.base.BaseDAO;
import com.felipeleme.Dashboard.model.entity.Tarefa;

@Repository
public class TarefaDao extends BaseDAO<Tarefa, Long> {
    @PersistenceContext
    private EntityManager manager;

    public List<Tarefa> readByUserId(Long id) {
	String jpql = new StringBuilder("SELECT T FROM Tarefa T ").append("WHERE T.usuarioId = ?1").toString();
	return createQuery(jpql, id);
    }

    public void updateStatus(Long id, Boolean status) {

	Query query = manager.createNativeQuery("UPDATE tarefa " + "SET concluida = ? " + "WHERE id = ?")
		.setParameter(1, status).setParameter(2, id);
	query.executeUpdate();
    }
}
