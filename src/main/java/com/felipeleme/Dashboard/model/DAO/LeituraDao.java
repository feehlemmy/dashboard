package com.felipeleme.Dashboard.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.felipeleme.Dashboard.base.BaseDAO;
import com.felipeleme.Dashboard.model.entity.Leitura;
@Repository
public class LeituraDao extends BaseDAO<Leitura, Long>  {
    @PersistenceContext
    private EntityManager manager;

    public List<Leitura> findByUserId(Long id) {
	String jpql = new StringBuilder("SELECT L FROM Leitura L ").append("WHERE L.usuarioId = ?1").toString();
	return createQuery(jpql, id);
    }

    public void updateLeitura(Leitura leitura) {
	// TODO Auto-generated method stub
	
    }
}
