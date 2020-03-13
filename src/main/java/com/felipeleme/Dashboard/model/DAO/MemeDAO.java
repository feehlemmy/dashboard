package com.felipeleme.Dashboard.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.felipeleme.Dashboard.base.BaseDAO;
import com.felipeleme.Dashboard.model.entity.Ideia;
import com.felipeleme.Dashboard.model.entity.Meme;
import com.felipeleme.Dashboard.model.entity.Tarefa;

@Repository
public class MemeDAO extends BaseDAO<Meme, Long> {
    @PersistenceContext
    private EntityManager manager;

    public List<Meme> readByUserId(Long id) {
	String jpql = new StringBuilder("SELECT M FROM Meme M ").append("WHERE M.usuarioId = ?1").toString();
	return createQuery(jpql, id);
    }

    public void deleteByUserId(Long id) {
	Query query = manager.createNativeQuery("DELETE FROM meme WHERE usuario_id = ?;")
		.setParameter(1, id);
	query.executeUpdate();		
    }
}
