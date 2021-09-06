package com.auto.cody.controller;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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
@RestController("rest")
public class JuejinController {

    @Value("${juejin.aid}")
    private String aid;

    @Value("${juejin.uuid}")
    private String uuid;

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
    public static final String COOKIE = "_ga=GA1.2.1803990248.1619417154; n_mh=X7_5R6zFWw8R8v6LqZJzQ7zqatYJi2Bp6llmZi8aO6Y; _tea_utm_cache_2608={%22utm_source%22:%22infinitynewtab.com%22}; MONITOR_WEB_ID=180b49a9-0b64-46e5-90dc-546449a1e766; _gid=GA1.2.568040586.1630889715; passport_csrf_token_default=2a5116ce03cb656b582153d7f5840591; passport_csrf_token=2a5116ce03cb656b582153d7f5840591; sid_guard=d17ab0611677ec5a9a2b0e4b21819458%7C1630893652%7C5184000%7CFri%2C+05-Nov-2021+02%3A00%3A52+GMT; uid_tt=54e38f794a2a7eb0234ec2210afe0cf6; uid_tt_ss=54e38f794a2a7eb0234ec2210afe0cf6; sid_tt=d17ab0611677ec5a9a2b0e4b21819458; sessionid=d17ab0611677ec5a9a2b0e4b21819458; sessionid_ss=d17ab0611677ec5a9a2b0e4b21819458; sid_ucp_v1=1.0.0-KDdmNTQ1YmUzMTMzZjFmYzNjODNiNzIxNjAzMWRkNGVhNGU5M2I5NmUKFwie7cDA_fXpARDU7NWJBhiwFDgCQO8HGgJsZiIgZDE3YWIwNjExNjc3ZWM1YTlhMmIwZTRiMjE4MTk0NTg; ssid_ucp_v1=1.0.0-KDdmNTQ1YmUzMTMzZjFmYzNjODNiNzIxNjAzMWRkNGVhNGU5M2I5NmUKFwie7cDA_fXpARDU7NWJBhiwFDgCQO8HGgJsZiIgZDE3YWIwNjExNjc3ZWM1YTlhMmIwZTRiMjE4MTk0NTg";

    @PostConstruct
    public void init() {
        /*String aid = "2608";
        String uuid = "6956000234107389447";*/
        PARAM_MAP.put("aid", aid);
        PARAM_MAP.put("uuid", uuid);
    }

    /**
     * 签到天数
     *
     * @return string
     */
    @GetMapping("signInDays")
    public String signInDays() {
        return HttpRequest.get(SIGN_IN_DAYS)
                .header(Header.COOKIE, COOKIE)
                .form(PARAM_MAP)
                .execute().body();
    }


}
