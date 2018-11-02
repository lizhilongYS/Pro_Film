package cn.java68.repository;


import cn.java68.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 电影Repository接口
 * @author Administrator
 *
 */
public interface FilmRepository extends JpaRepository<Film, Integer> {
//JpaSpecificationExecutor<Film>
	@Query("select f FROM Film f  WHERE f.catId=?1")
	List<Film> findListByCatId(Integer catName);
}
