package cn.java68.controller;

import cn.java68.entity.Film;
import cn.java68.entity.VideoCat;
import cn.java68.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmDataController {

    @Autowired
    private FilmService filmService;


    @RequestMapping(value = "/getList/{catName}")
    public ModelAndView getList(@PathVariable("catName")Integer catName){

        System.out.println(catName);
        List<Film> result = filmService.findAllByCatName(catName);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list1",result);
        modelAndView.setViewName("play");
        System.out.println(result.size());
        return modelAndView;

    }


}
