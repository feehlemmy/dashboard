package com.felipeleme.Dashboard.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.felipeleme.Dashboard.base.BaseDAO;
import com.felipeleme.Dashboard.model.entity.Atividade;

@Repository
public class AtividadeDao extends BaseDAO<Atividade, Long> {
    @PersistenceContext
    private EntityManager manager;

    public List<Atividade> readByUserId(Long id) {
	String jpql = new StringBuilder("SELECT A FROM Atividade A ").append("WHERE A.usuarioId = ?1").toString();
	return createQuery(jpql, id);
    }

    public void updateAtividade(Atividade atividade) {
	Query query = manager
		.createNativeQuery(
			"UPDATE atividades SET carga_horaria=?, carga_horaria_cumprida=?, nome=?  WHERE id=?")
		.setParameter(1, atividade.getCargaHoraria()).setParameter(2, atividade.getCargaHorariaCumprida())
		.setParameter(3, atividade.getNome()).setParameter(4, atividade.getId());

	query.executeUpdate();
    }
}
