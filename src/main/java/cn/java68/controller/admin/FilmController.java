package cn.java68.controller.admin;

import cn.java68.entity.Film;
import cn.java68.service.FilmService;
import cn.java68.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class FilmController {

    @Autowired
    private FilmService service;

    @Value("${imageFilePath}")
    private String imageFilePath;
    @PostMapping("/film/save")
    public Map<String ,Object> save(Film film, @RequestParam("imageFile")MultipartFile file, @RequestParam("catId") Integer catId) throws Exception {
        if(!file.isEmpty()){
            String basePath = System.getProperty("user.dir");
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr()+suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(basePath+imageFilePath+newFileName));
            film.setImageName(newFileName);
            System.out.println(newFileName);
        }


        film.setCatId(catId);
        Map<String,Object> result = new HashMap<>();

        service.save(film);

        result.put("success",true);
        System.out.println(film.getCatId());
        return result;
    }

    @PostMapping("/film/list")
    public Map<String,Object> list(Film film, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows",required = false)Integer rows)throws Exception{

        List<Film> list = service.list(film,page-1, rows);
        long total = service.getCount(film);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", list);
        resultMap.put("total", total);

        return resultMap;

    }

    @PostMapping("/film/delete")
    public Map<String,Object> delete(@RequestParam(value="ids")String ids)throws Exception {
        String[] idsStr = ids.split(",");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        for(int i=0;i<idsStr.length;i++){
            Integer filmId=Integer.parseInt(idsStr[i]);

                service.delete(filmId);

        }
        resultMap.put("success", true);

        return resultMap;
    }

    @PostMapping("/film/findById")
    public Film getFilmById(@RequestParam(value = "id")Integer id){
        Film result = service.findById(id);

        return result;
    }

}
