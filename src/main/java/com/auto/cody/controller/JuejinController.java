package com.auto.cody.controller;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 * @author wql
 * @desc JuejinController
 * @date 2021/9/6
 * @lastUpdateUser wql
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/6
 */
@RestController
@RequestMapping("rest")
public class JuejinController {

    @Value("${juejin.aid}")
    private String aid;

    @Value("${juejin.uuid}")
    private String uuid;

    @Value("${juejin.cookie}")
    private String cookie;

    public static final HashMap<String, Object> PARAM_MAP = new HashMap<>();
    /**
     * 签到
     */
    public static final String SIGN_IN = "https://api.juejin.cn/growth_api/v1/check_in";
    /**
     * 矿石总数
     */
    public static final String TOTAL_ORE = "https://api.juejin.cn/growth_api/v1/get_cur_point";
    /**
     * 抽奖
     */
    public static final String draw_url = "https://api.juejin.cn/growth_api/v1/lottery/draw";

    @PostConstruct
    public void init() {
        PARAM_MAP.put("aid", aid);
        PARAM_MAP.put("uuid", uuid);
    }

    @GetMapping("test")
    public String test() {
        return "test";
    }

    /**
     * 签到
     *
     * @return string
     */
    @GetMapping("signIn")
    public String signIn() {
        String body = HttpRequest.post(SIGN_IN)
                .header(Header.COOKIE, cookie)
                .form(PARAM_MAP)
                .execute().body();
        return body;
    }

    /**
     * @return
     */
    @PostMapping
    public String draw() {
        return "";
    }


}
