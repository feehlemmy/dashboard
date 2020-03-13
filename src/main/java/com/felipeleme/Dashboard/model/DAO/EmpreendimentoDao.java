package com.felipeleme.Dashboard.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.felipeleme.Dashboard.base.BaseDAO;
import com.felipeleme.Dashboard.model.entity.Empreendimento;
import com.felipeleme.Dashboard.model.entity.Usuario;

@Repository
public class EmpreendimentoDao extends BaseDAO<Empreendimento, Long> {
    @PersistenceContext
	private EntityManager manager;

    public List<Empreendimento> readByUserId(Long id) {
		String jpql = new StringBuilder("SELECT E FROM Empreendimento E ")
				.append("WHERE E.usuarioId = ?1")
				.toString();
		return createQuery(jpql, id);
	}

    public List<Empreendimento> getByNameAndUserId(String idEmpreendimento, Long idUsuario) {
	String jpql = new StringBuilder("SELECT E FROM Empreendimento E ")
		.append("WHERE E.usuarioId = ?1 and E.nome = ?2")
		.toString();
	return createQuery(jpql, idUsuario, idEmpreendimento );
    }	
    }

