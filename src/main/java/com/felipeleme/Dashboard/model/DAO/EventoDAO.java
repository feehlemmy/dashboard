package com.felipeleme.Dashboard.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.felipeleme.Dashboard.base.BaseDAO;
import com.felipeleme.Dashboard.model.entity.Evento;
import com.felipeleme.Dashboard.model.entity.Ideia;
import com.felipeleme.Dashboard.model.entity.Meme;
import com.felipeleme.Dashboard.model.entity.Tarefa;

@Repository
public class EventoDAO extends BaseDAO<Evento, Long> {
    @PersistenceContext
    private EntityManager manager;

    public List<Evento> readByUserId(Long id) {
	String jpql = new StringBuilder("SELECT E FROM Evento E ").append("WHERE E.usuarioId = ?1").toString();
	return createQuery(jpql, id);
    }
    

}
