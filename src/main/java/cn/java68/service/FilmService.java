package cn.java68.service;

import cn.java68.entity.Film;

import java.io.File;
import java.util.List;

public interface FilmService {

   //保存视频
   public Film save(Film film);

   //分页查询视频信息
   public List<Film> list(Film film, Integer page, Integer pageSize);

   //获取总记录条数
   public Long getCount(Film film);

    void delete(Integer filmId);

    Film findById(Integer id);

    List findAllByCatName(Integer catName);
}
