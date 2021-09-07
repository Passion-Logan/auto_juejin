package com.auto.cody.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.auto.cody.enums.CommonInterface;
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

    @PostConstruct
    public void init() {
        PARAM_MAP.put("aid", aid);
        PARAM_MAP.put("uuid", uuid);
    }

    @RequestMapping("page")
    public String page(Model model) {
        // 签到天数
        String days = CommonUtil.executeUrl(CommonInterface.SIGN_IN_DAYS, cookie, PARAM_MAP);
        JSONObject dayReq = JSONUtil.parseObj(days);
        CommonUtil.isErr(dayReq);
        JSONObject dayInfo = JSONUtil.parseObj(dayReq.get("data"));
        model.addAttribute("cont_count", dayInfo.get("cont_count"));
        model.addAttribute("sum_count", dayInfo.get("sum_count"));

        // 矿石总数
        String ore = CommonUtil.executeUrl(CommonInterface.TOTAL_ORE, cookie, PARAM_MAP);
        JSONObject oreReq = JSONUtil.parseObj(ore);
        CommonUtil.isErr(oreReq);
        model.addAttribute("ore_count", oreReq.get("data"));

        // 奖品池信息
        String pool = HttpUtil.get(CommonInterface.PRIZE_POOL, PARAM_MAP);
        JSONObject poolReq = JSONUtil.parseObj(pool);
        CommonUtil.isErr(poolReq);
        model.addAttribute("data", poolReq.get("data"));

        return "home/page";
    }

}
