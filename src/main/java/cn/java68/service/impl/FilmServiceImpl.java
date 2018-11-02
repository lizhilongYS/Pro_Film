package cn.java68.service.impl;

import cn.java68.entity.Film;
import cn.java68.repository.FilmRepository;
import cn.java68.service.FilmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository repository;


    @Override
    public Film save(Film film) {

       Film result = repository.save(film);

        return result;
    }

    @Override
    public List<Film> list(Film film, Integer page, Integer pageSize) {
        Pageable pageable = new PageRequest(page, pageSize);
        Page<Film> page1 = repository.findAll(pageable);

        return page1.getContent();
    }

    @Override
    public Long getCount(Film film) {
        Long count = repository.count();
        return count;
    }

    @Override
    public void delete(Integer filmId) {
        repository.deleteById(filmId);
    }

    @Override
    public Film findById(Integer id) {
       Optional<Film> result =  repository.findById(id);
       Film film = result.get();
       return film;
    }

    @Override
    public List findAllByCatName(Integer catName) {


       List<Film> result =  repository.findListByCatId(catName);
//       List<Film> list = new ArrayList<>();
//        for (Film film: result) {
//
//            if (catName.equals(film.getCatId())){
//                list.add(film);
//            }
//        }

       return result;
    }
}
