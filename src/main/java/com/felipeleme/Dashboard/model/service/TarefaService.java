package com.felipeleme.Dashboard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipeleme.Dashboard.model.DAO.TarefaDao;
import com.felipeleme.Dashboard.model.entity.Tarefa;

@Service
@Transactional(readOnly = false)
public class TarefaService {
    @Autowired
    private TarefaDao dao;
    
    public void salvarTarefa(Tarefa tarefa) {
	dao.save(tarefa);
    }

    public List<Tarefa> findAllTarefas(Long id) {
	return dao.readByUserId(id);
    }

    public void updateTarefa(Long id, Boolean status) {
	dao.updateStatus(id, status);
    }

}
