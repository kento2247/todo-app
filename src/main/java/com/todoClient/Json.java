package com.todoClient;

import com.google.gson.Gson;

public class Json {
    public static <T> T parse(Class<T> class_name, String json_str) {
        Gson gson = new Gson();
        return gson.fromJson(json_str, class_name);
    }

    public static String stringify(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static String stringify(Object[] obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}