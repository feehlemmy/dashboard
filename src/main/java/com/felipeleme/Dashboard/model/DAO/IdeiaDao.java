package com.felipeleme.Dashboard.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.felipeleme.Dashboard.base.BaseDAO;
import com.felipeleme.Dashboard.model.entity.Ideia;
import com.felipeleme.Dashboard.model.entity.Tarefa;

@Repository
public class IdeiaDao extends BaseDAO<Ideia, Long> {
    @PersistenceContext
    private EntityManager manager;

    public List<Ideia> readByUserId(Long id) {
	String jpql = new StringBuilder("SELECT I FROM Ideia I ").append("WHERE I.usuarioId = ?1").toString();
	return createQuery(jpql, id);
    }

    public void updateStatus(Long id, Boolean status) {
	Query query = manager.createNativeQuery("UPDATE ideia " + "SET utilizada = ? " + "WHERE id = ?")
		.setParameter(1, status).setParameter(2, id);
	query.executeUpdate();	
    }

}
