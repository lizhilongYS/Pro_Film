package cn.java68.controller;

import cn.java68.entity.VideoCat;
import cn.java68.service.VideoCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/videoCat")
public class CategoryController {

    @Autowired
    private VideoCatService catService;

    @RequestMapping("/showList/{categoryId}")
    public ModelAndView showList(@PathVariable("categoryId")Integer categoryId){

        List<VideoCat> result = catService.selectAllByCategoryId(categoryId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list",result);
        modelAndView.setViewName("showList");

        return modelAndView;
    }
}
