package cn.java68.service.impl;

import cn.java68.entity.VideoCat;
import cn.java68.repository.VideoCatRepository;
import cn.java68.service.VideoCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoCatServiceImpl implements VideoCatService {

    @Autowired
    private VideoCatRepository repository;

    @Override
    public VideoCat save(VideoCat cat) {

        VideoCat result = repository.save(cat);
        return result;
    }

    @Override
    public List<VideoCat> selectAll() {

        List<VideoCat> list = repository.findAll();

        return list;
    }

    @Override
    public List<VideoCat> selectAllByCategoryId(Integer categoryId) {
        List<VideoCat> result = repository.findAllByCategoryId(categoryId);
        return result;
    }
}
