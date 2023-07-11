package com.todoClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Http {
    private String url;

    Http(String url) {
        this.url = url;
    }

    private String send(String method_str, String param_str, String body_str) throws Exception {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(Redirect.NORMAL)
                .build();
        HttpRequest request = HttpRequest.newBuilder(URI.create(this.url + param_str))
                .method(method_str, HttpRequest.BodyPublishers.ofString(body_str))
                .build();
        HttpResponse<String> response = client.send(
                request, BodyHandlers.ofString());
        System.out.println(request.method());
        return response.body();
    }

    public String get(String param_str) {
        try {
            return this.send("GET", param_str, "");
        } catch (Exception e) {
            return e.toString();
        }
    }

    public String post(String param_str, String body_str) throws Exception {
        try {
            return this.send("POST", param_str, body_str);
        } catch (Exception e) {
            return e.toString();
        }
    }

    public String put(String param_str, String body_str) throws Exception {
        try {
            return this.send("PUT", param_str, body_str);
        } catch (Exception e) {
            return e.toString();
        }
    }

    public String delete(String param_str, String body_str) throws Exception {
        try {
            return this.send("DELETE", param_str, body_str);
        } catch (Exception e) {
            return e.toString();
        }
    }
}
