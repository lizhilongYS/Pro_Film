package cn.java68.controller.admin;


import cn.java68.entity.Category;
import cn.java68.entity.VideoCat;
import cn.java68.service.CategoryService;
import cn.java68.service.VideoCatService;
import cn.java68.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class VideoCatController {

    @Autowired
    private VideoCatService videoCatService;

    @Autowired
    private CategoryService categoryService;

    @Value("${catImageFilePath}")
    private String catImageFilePath;




    @PostMapping("/cat/save")
    public Map<String,Object> save(VideoCat videoCat,  @RequestParam("imageFile")MultipartFile file)throws Exception{
        if(!file.isEmpty()){
            String basePath = System.getProperty("user.dir");
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr()+suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(basePath+catImageFilePath+newFileName));
            videoCat.setImageName(newFileName);
        }

        System.out.println(videoCat.toString());
        Map<String,Object> result = new HashMap<>();

        videoCatService.save(videoCat);

        result.put("success",true);

        return result;
    }

    /**
     * 获取视频所有课程
     * @return
     */
    @GetMapping("/cat/show")
    public List<VideoCat> show(){

        List<VideoCat> result = videoCatService.selectAll();

        return result;
    }

    /**
     * 查找视频类目
     * @return
     */
    @GetMapping("/category/show")
    public List<Category> show2(){
        List<Category> result = categoryService.findAll();

        return  result;
    }

}
