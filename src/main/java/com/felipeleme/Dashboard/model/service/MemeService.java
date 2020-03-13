package com.felipeleme.Dashboard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.felipeleme.Dashboard.model.DAO.MemeDAO;
import com.felipeleme.Dashboard.model.entity.Meme;

@Service
@Transactional(readOnly = false)
public class MemeService {
    @Autowired
    private MemeDAO dao;
    
    public void salvarMeme(Meme meme) {
	dao.save(meme);
    }

    public List<Meme> findAllMemes(Long id) {
	return dao.readByUserId(id);
    }

    public void deleteByUser(Long id) {
	dao.deleteByUserId(id);
    }

}
