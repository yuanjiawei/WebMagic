package org.java.controller;

import org.java.service.WebMagicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ShowController {
    @Autowired
    private WebMagicService service;
    @Autowired
    private WebMagicontroller con;
    /**
     * 在页面进行显示爬取的内容
     * @return
     */
    @ResponseBody
    @RequestMapping("/init")
    public List<Map> show(){
        return service.show();
    }


    /**
     * 用于页面跳转
     */
    @RequestMapping("/{html}")
    public String html(@PathVariable("html") String html) {
        return html;
    }


    /**
     * 添加
     */
    @RequestMapping("/add")
    public String add(){
        service.add(con.ret());
        return "/index";
    }
}
