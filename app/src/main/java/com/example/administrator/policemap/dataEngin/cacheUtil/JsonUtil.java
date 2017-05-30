package com.example.administrator.policemap.dataEngin.cacheUtil;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
public class JsonUtil {
    public static Gson gson= new Gson();

    public static <T> T jsonToObject(String json,Class<T> c){
        if (!isGoodJson(json))return null;
        return gson.fromJson(json,c);
    }
    public static String objectToJson(Object param){
        if (param==null)return null;
        return gson.toJson(param);
    }

    public static boolean isGoodJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }
}
