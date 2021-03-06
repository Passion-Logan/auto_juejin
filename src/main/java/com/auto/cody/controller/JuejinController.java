package com.auto.cody.controller;

import com.auto.cody.enums.CommonInterface;
import com.auto.cody.utils.CommonUtil;
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

    @PostConstruct
    public void init() {
        PARAM_MAP.put("aid", aid);
        PARAM_MAP.put("uuid", uuid);
    }

    /**
     * 签到
     *
     * @return string
     */
    @GetMapping("signIn")
    public String signIn() {
        return CommonUtil.executePostUrl(CommonInterface.SIGN_IN, cookie, PARAM_MAP);
    }

    /**
     * 抽奖
     *
     * @return String
     */
    @PostMapping("draw")
    public String draw() {
        return CommonUtil.executePostUrl(CommonInterface.DRAW_URL, cookie, PARAM_MAP);
    }

    /**
     * 重置矿石
     *
     * @return String
     */
    @GetMapping("getOre")
    public String getOre() {
        return CommonUtil.executeGetUrl(CommonInterface.TOTAL_ORE, cookie, PARAM_MAP);
    }

}
