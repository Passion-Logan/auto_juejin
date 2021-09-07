package com.auto.cody.enums;

/**
 * @author wql
 * @desc CommonInterface
 * @date 2021/9/7
 * @lastUpdateUser wql
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/7
 */
public class CommonInterface {

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
    /**
     * 签到
     */
    public static final String SIGN_IN = "https://api.juejin.cn/growth_api/v1/check_in";
    /**
     * 抽奖
     */
    public static final String DRAW_URL = "https://api.juejin.cn/growth_api/v1/lottery/draw";

}
