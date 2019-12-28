package com.lyc.controller;

import com.lyc.service.CodeService;
import com.lyc.bean.Sys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Package: com.lyc.controller
 * @Author: lyc
 * @Date: 2019/10/4 20:17
 */
@Controller
@Slf4j
public class CodeController {

    @Resource
    private CodeService codeService;

    @Resource
    private Sys sys;

    /**
     * 代码生成器主页
     */
    @GetMapping("/")
    public String index(Model model)  {
        model.addAttribute("tabs",codeService.queryAlltable());
        model.addAttribute("sys",sys);
        return "index";
    }

    /**
     * 提交
     */
    @ResponseBody
    @PostMapping("/submit")
    public String submit(@ModelAttribute Sys sys) throws Exception {
        codeService.submit(sys);
        return "index";
    }

}
