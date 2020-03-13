package com.felipeleme.Dashboard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipeleme.Dashboard.model.DAO.FrasesDAO;
import com.felipeleme.Dashboard.model.entity.Frase;
@Service
@Transactional(readOnly = false)
public class FrasesService {
    @Autowired
    private FrasesDAO dao;
    public List<Frase> findAllFrases() {
	return dao.findAll();
    }

}
