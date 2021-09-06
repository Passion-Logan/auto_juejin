package com.auto.cody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wql
 * @desc HomeController
 * @date 2021/9/6
 * @lastUpdateUser wql
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/6
 */
@Controller
@RequestMapping("home")
public class HomeController {

    @RequestMapping("page")
    public String page() {
        return "home/page";
    }

}
