package com.felipeleme.Dashboard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipeleme.Dashboard.model.DAO.EmpreendimentoDao;
import com.felipeleme.Dashboard.model.entity.Empreendimento;

@Service
@Transactional(readOnly = false)
public class EmpreendimentoService {
    
    @Autowired
    private EmpreendimentoDao dao;

    public void salvarEmpreendimento(Empreendimento empreendimento) {
	dao.save(empreendimento);
    }

    public List<Empreendimento> getEmpreendimentos(Long id) {
	return dao.readByUserId(id);
    }

    public Long getByNameAndUserId(String idEmpreendimento, Long idUsuario) {
	return dao.getByNameAndUserId(idEmpreendimento,idUsuario ).get(0).getId();
    }
}
