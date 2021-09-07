package com.auto.cody.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.auto.cody.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.HashMap;

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

    @Value("${juejin.aid}")
    private String aid;

    @Value("${juejin.uuid}")
    private String uuid;

    @Value("${juejin.cookie}")
    private String cookie;

    public static final HashMap<String, Object> PARAM_MAP = new HashMap<>();
    /**
     * 签到天数
     */
    public static final String SIGN_IN_DAYS = "https://api.juejin.cn/growth_api/v1/get_counts";
    /**
     * 矿石总数
     */
    public static final String TOTAL_ORE = "https://api.juejin.cn/growth_api/v1/get_cur_point";
    /**
     * 奖品池信息
     */
    public static final String PRIZE_POOL = "https://api.juejin.cn/growth_api/v1/lottery_config/get";

    @PostConstruct
    public void init() {
        PARAM_MAP.put("aid", aid);
        PARAM_MAP.put("uuid", uuid);
    }

    @RequestMapping("page")
    public String page(Model model) {

        // 签到天数
        String days = CommonUtil.executeUrl(SIGN_IN_DAYS, cookie, PARAM_MAP);
        JSONObject dayReq = JSONUtil.parseObj(days);
        CommonUtil.isErr(dayReq);
        JSONObject dayInfo = JSONUtil.parseObj(dayReq.get("data"));
        model.addAttribute("cont_count", dayInfo.get("cont_count"));
        model.addAttribute("sum_count", dayInfo.get("sum_count"));
        // 矿石总数
        String ore = CommonUtil.executeUrl(TOTAL_ORE, cookie, PARAM_MAP);
        JSONObject oreReq = JSONUtil.parseObj(ore);
        CommonUtil.isErr(oreReq);
        model.addAttribute("ore_count", oreReq.get("data"));
        // 奖品池信息
        String pool = HttpUtil.get(PRIZE_POOL, PARAM_MAP);
        JSONObject poolReq = JSONUtil.parseObj(pool);
        CommonUtil.isErr(poolReq);
        model.addAttribute("data", poolReq.get("data"));

        return "home/page";
    }

}
