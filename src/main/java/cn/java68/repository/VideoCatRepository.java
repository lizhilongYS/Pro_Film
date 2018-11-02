package cn.java68.repository;

import cn.java68.entity.VideoCat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoCatRepository extends JpaRepository<VideoCat,String> {

    List<VideoCat> findAllByCategoryId(Integer categoryId);
}
