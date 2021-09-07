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
    public static final String DRAW_URL = "https://api.juejin.cn/growth_api/v1/lottery/draw";

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
        return HttpRequest.post(SIGN_IN)
                .header(Header.COOKIE, cookie)
                .form(PARAM_MAP)
                .execute().body();
    }

    /**
     * 抽奖
     *
     * @return String
     */
    @PostMapping("draw")
    public String draw() {
        return HttpRequest.post(DRAW_URL)
                .header(Header.COOKIE, cookie)
                .form(PARAM_MAP)
                .execute().body();
    }

    /**
     * 重置矿石
     *
     * @return String
     */
    @GetMapping("getOre")
    public String getOre() {
        return HttpRequest.get(TOTAL_ORE)
                .header(Header.COOKIE, cookie)
                .form(PARAM_MAP)
                .execute().body();
    }

    class datas {
        private String lottery_id;
        private String lottery_name;

        public datas(String lottery_id, String lottery_name) {
            this.lottery_id = lottery_id;
            this.lottery_name = lottery_name;
        }

        public String getLottery_id() {
            return lottery_id;
        }

        public void setLottery_id(String lottery_id) {
            this.lottery_id = lottery_id;
        }

        public String getLottery_name() {
            return lottery_name;
        }

        public void setLottery_name(String lottery_name) {
            this.lottery_name = lottery_name;
        }
    }

    class result {
        private int err_no;
        private String err_msg;
        private datas data;

        public result(int err_no, String err_msg, datas data) {
            this.err_no = err_no;
            this.err_msg = err_msg;
            this.data = data;
        }

        public int getErr_no() {
            return err_no;
        }

        public void setErr_no(int err_no) {
            this.err_no = err_no;
        }

        public String getErr_msg() {
            return err_msg;
        }

        public void setErr_msg(String err_msg) {
            this.err_msg = err_msg;
        }

        public datas getData() {
            return data;
        }

        public void setData(datas data) {
            this.data = data;
        }
    }

}
