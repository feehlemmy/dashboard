package com.felipeleme.Dashboard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipeleme.Dashboard.model.DAO.CarreiraDao;
import com.felipeleme.Dashboard.model.entity.Carreira;

@Service
@Transactional(readOnly = false)
public class CarreiraService {
    @Autowired
    private CarreiraDao dao;
    public void salvarCarreira(Carreira carreira) {
	dao.save(carreira);

    }
    public List<Carreira> getCarreiras(Long id) {
   	return dao.readByUserId(id);
       }
    public Long getByNameAndUserId(String idCarreira, Long id) {
	return dao.getByNameAndUserId(idCarreira,id ).get(0).getId();
    }
}
