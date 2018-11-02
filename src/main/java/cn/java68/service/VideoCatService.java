package cn.java68.service;

import cn.java68.entity.VideoCat;

import java.util.List;

public interface VideoCatService {

    VideoCat save(VideoCat cat);

    List<VideoCat> selectAll();

    List<VideoCat> selectAllByCategoryId(Integer id);


}
