package com.felipeleme.Dashboard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipeleme.Dashboard.model.DAO.EventoDAO;
import com.felipeleme.Dashboard.model.DAO.MemeDAO;
import com.felipeleme.Dashboard.model.entity.Evento;
import com.felipeleme.Dashboard.model.entity.Meme;

@Service
@Transactional(readOnly = false)
public class EventoService {
    @Autowired
    private EventoDAO dao;

    public void salvarEventos(Long id, Evento events) {
	    events.setUsuarioId(id);
	    dao.save(events);
	
    }

    public List<Evento> findAllEventos(Long id) {
	return dao.readByUserId(id);
    }

    public Object delete(Long id) {
	dao.delete(id);
	return id;
    }

}
