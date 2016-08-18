package com.hand.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hand.dao.FilmDao;
import com.hand.domain.Film;
import com.hand.service.FilmService;

@Service
public class FilmServiceImpl implements FilmService{

	@Resource
	private FilmDao filmDao;
	
	@Transactional
	public boolean addFilm(Film film){
		try {
			System.out.println("正在插入title值为"+film.getTitle()+"的film...");
			filmDao.insertFilm(film);	
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
}
