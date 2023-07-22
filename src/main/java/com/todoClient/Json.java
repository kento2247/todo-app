package com.todoClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Json {
    public static <T> T parse(Class<T> class_name, String json_str) {
        Gson gson = new Gson();
        T parsed_class = gson.fromJson(json_str, class_name);
        return parsed_class;
    }

    public static String stringify(Object obj) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        return gson.toJson(obj);
    }

    public static String stringify(Object[] obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}