package com.felipeleme.Dashboard.model.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipeleme.Dashboard.model.DAO.LeituraDao;
import com.felipeleme.Dashboard.model.entity.Leitura;

@Service
@Transactional(readOnly = false)
public class LeituraService {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private LeituraDao dao;

    public void salvarLeitura(Leitura leitura) {
	dao.save(leitura);
    }

    public List<Leitura> findAllLeituras(Long id) {
	return dao.findByUserId(id);
    }

    public void editarLeitura(Leitura leitura) {
	Query query = manager.createNativeQuery(
			"UPDATE leitura SET total_paginas=?, paginas_lidas=?, nome=?  WHERE id=?")
		.setParameter(1, leitura.getTotalPaginas()).setParameter(2, leitura.getPaginasLidas())
		.setParameter(3, leitura.getNome()).setParameter(4, leitura.getId());

	query.executeUpdate();    }

}
