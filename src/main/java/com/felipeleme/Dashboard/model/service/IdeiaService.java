package com.felipeleme.Dashboard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipeleme.Dashboard.model.DAO.IdeiaDao;
import com.felipeleme.Dashboard.model.DAO.TarefaDao;
import com.felipeleme.Dashboard.model.entity.Ideia;
import com.felipeleme.Dashboard.model.entity.Tarefa;

@Service
@Transactional(readOnly = false)
public class IdeiaService {
    @Autowired
    private IdeiaDao dao;
    
    public void salvarIdeia(Ideia ideia) {
	dao.save(ideia);
    }

    public List<Ideia> findAllIdeias(Long id) {
	return dao.readByUserId(id);
    }

    public void updateIdeia(Long id, Boolean status) {
	dao.updateStatus(id, status);
    }

}
