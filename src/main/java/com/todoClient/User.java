package com.todoClient;

public class User extends History {
    String email;
    String nickname;
    String password;

    User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public int signup() {
        System.out.println("signup");
        System.out.println("email: " + this.email);
        System.out.println("nickname: " + this.nickname);
        System.out.println("password: " + this.password);
        String endpoint = "/users/register";
        String json_str = Json.stringify(this);
        OpenAPI_client c = new OpenAPI_client();
        String response = c.post(endpoint, json_str);
        System.out.println(response);
        return 1;
    }

    public int signin() {
        System.out.println("signin");
        System.out.println("email: " + this.email);
        System.out.println("password: " + this.password);
        String endpoint = "/users/login";
        String json_str = Json.stringify(this);
        OpenAPI_client c = new OpenAPI_client();
        String response = c.post(endpoint, json_str);
        if (response.equals("")) {
            System.out.println("login failed");
            return 0;
        } else {
            System.out.println("login success");
            System.out.println("Access token: " + response);
            return 1;
        }
    }
}
