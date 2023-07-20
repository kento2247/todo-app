package com.todoClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Http {
    private String url;
    private String accessToken = "";

    Http(String url) {
        this.url = url;
    }

    private HttpResponse<String> send(String method_str, String param_str, String body_str)
            throws Exception {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(Redirect.NORMAL)
                .build();
        HttpRequest request;
        if (!this.accessToken.equals("")) {
            request = HttpRequest.newBuilder(URI.create(this.url + param_str))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + this.accessToken)
                    .method(method_str, HttpRequest.BodyPublishers.ofString(body_str))
                    .build();
        } else {
            request = HttpRequest.newBuilder(URI.create(this.url + param_str))
                    .header("Content-Type", "application/json")
                    .method(method_str, HttpRequest.BodyPublishers.ofString(body_str))
                    .build();
        }
        HttpResponse<String> response = client.send(
                request, BodyHandlers.ofString());
        // System.out.println(response.statusCode());
        return response;
    }

    public String get(String endpoint_path) {
        try {
            HttpResponse<String> response = this.send("GET", endpoint_path, "");
            return response.body();
        } catch (Exception e) {
            return e.toString();
        }
    }

    public String post(String param_str, String body_str) {
        try {
            HttpResponse<String> response = this.send("POST", param_str, body_str);
            return response.body();
        } catch (Exception e) {
            return e.toString();
        }
    }

    public String put(String param_str, String body_str) {
        try {
            HttpResponse<String> response = this.send("PUT", param_str, body_str);
            if (response.statusCode() == 200)
                return response.body();
            else
                return "";
        } catch (Exception e) {
            return e.toString();
        }
    }

    public String delete(String param_str, String body_str) {
        try {
            HttpResponse<String> response = this.send("DELETE", param_str, body_str);
            return response.body();
        } catch (Exception e) {
            return e.toString();
        }
    }

    public void set_access_token(String accessToken) {
        this.accessToken = accessToken;
    }
}
