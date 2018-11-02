package cn.java68.controller;

import cn.java68.entity.VideoCat;
import cn.java68.service.VideoCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 根路径以及其他请求处理
 * @author Administrator
 *
 */
@Controller
public class IndexController {

	@Autowired
	private VideoCatService videoCatService;

	/**
	 * 网页根目录请求
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView root(){
		List<VideoCat> list = videoCatService.selectAll();
		List<VideoCat> result = new ArrayList<>();
		for (int i=0 ; i<2 ; i++){
			result.add(list.get(i));
		}
		ModelAndView mav=new ModelAndView();
		mav.addObject("list", result);

		mav.setViewName("index");
		return mav;
	}
	
	/**
	 * 登录请求
	 * @return
	 */
	@RequestMapping("/login")
	public String login(){
		return "/login";
	}
	
	/**
	 * 进入后台管理请求
	 * @return
	 */
	@RequestMapping("/admin")
	public String toAdmin(){
		return "/admin/main";
	}
	
	/**
	 * 关于本站
	 * @return
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe(){
		ModelAndView mav=new ModelAndView();
		mav.addObject("title", "关于本站");
		mav.addObject("mainPage", "common/aboutMe");
		mav.addObject("mainPageKey", "#a");
		mav.setViewName("index");
		return mav;
	}
	//未实现页面

	@RequestMapping("/static/no")
	public String noPage(){ return "/unrealized"; };

	@RequestMapping("/static/play")
	public String play(){return "play"; };

	@RequestMapping("/static/showList")
	public String showList(){ return "showList";};

	@RequestMapping("/addFilm")
	public String addFilm(){return "addFilm";};
}
