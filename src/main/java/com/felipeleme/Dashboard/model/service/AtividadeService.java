package com.felipeleme.Dashboard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipeleme.Dashboard.model.DAO.AtividadeDao;
import com.felipeleme.Dashboard.model.entity.Atividade;
@Service
@Transactional(readOnly = false)
public class AtividadeService {

    @Autowired
private AtividadeDao dao;
    
    public void salvarAtividade(Atividade atividade) {
	dao.save(atividade);
    }

    public List<Atividade> findAllAtividades(Long id) {
	return dao.readByUserId(id);
    }

    public void editarAtividade(Atividade atividade) {
	dao.updateAtividade(atividade);
    }

}
