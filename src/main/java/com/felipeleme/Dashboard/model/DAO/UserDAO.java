package com.felipeleme.Dashboard.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import javax.persistence.Query;

import com.felipeleme.Dashboard.base.BaseDAO;
import com.felipeleme.Dashboard.model.entity.Empreendimento;
import com.felipeleme.Dashboard.model.entity.Usuario;

@Repository
public class UserDAO extends BaseDAO<Usuario, Long> {
	@PersistenceContext
	private EntityManager manager;
	
	public List<Usuario> readByLogin(String username, String senha) {
		String jpql = new StringBuilder("SELECT U FROM Usuario U ")
				.append("WHERE U.username = ?1 AND U.senha = ?2")
				.toString();
		return createQuery(jpql, username, senha);
	}

	public void updateUsuario(Usuario usuario) {
		Query query = manager.createNativeQuery("UPDATE usuario "
				+ "SET foto = ? WHERE id = 1")
				.setParameter(1, usuario.getFoto());
				
		query.executeUpdate();
	}

	public void updateAdminsitrador(Usuario usuario) {
		Query query = manager.createNativeQuery("UPDATE usuario SET email=?, nome=?, foto=? WHERE id = ?")
				.setParameter(1, usuario.getEmail())
				.setParameter(2, usuario.getNome())
				.setParameter(3, usuario.getFoto())
				.setParameter(4, usuario.getId());
				query.executeUpdate();		
	}

	public List<Usuario> readByUsername(String usuarioCriador) {
	    String jpql = new StringBuilder("SELECT U FROM Usuario U ")
			.append("WHERE U.username = ?1")
			.toString();
	return createQuery(jpql, usuarioCriador);
	}
}
