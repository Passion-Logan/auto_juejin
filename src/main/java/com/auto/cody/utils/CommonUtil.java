package com.auto.cody.utils;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;

import java.util.HashMap;

/**
 * @author wql
 * @desc JsonUtil
 * @date 2021/9/6
 * @lastUpdateUser wql
 * @lastUpdateDesc
 * @lastUpdateTime 2021/9/6
 */
public class CommonUtil {

    public static void isErr(JSONObject req) {
        int errNo = (int) req.get("err_no");
        if (errNo != 0) {
            throw new RuntimeException(req.get("err_msg").toString());
        }
    }

    public static String executeUrl(String url, String cookie, HashMap<String, Object> paramMap) {
        return HttpRequest.get(url)
                .header(Header.COOKIE, cookie)
                .form(paramMap)
                .execute().body();
    }

}
