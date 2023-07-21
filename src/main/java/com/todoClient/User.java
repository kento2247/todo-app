package com.todoClient;

public class User extends History {
    String email;
    String nickname;
    String password;
    private String access_token;
    private long id;

    User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.id = -1;
    }

    public int signup() {
        System.out.println("signup");
        // System.out.println("email: " + this.email);
        // System.out.println("nickname: " + this.nickname);
        // System.out.println("password: " + this.password);
        String endpoint = "/users/register";
        String json_str = Json.stringify(this);
        OpenAPI_client c = new OpenAPI_client();
        String response = c.post(endpoint, json_str);
        System.out.println(response);
        return 1;
    }

    public int signin() {
        System.out.println("signin");
        // System.out.println("email: " + this.email);
        // System.out.println("password: " + this.password);
        String endpoint = "/users/login";
        String json_str = Json.stringify(this);
        OpenAPI_client c = new OpenAPI_client();

        String response = c.post(endpoint, json_str);
        if (response.equals("")) {
            System.out.println("login failed");
            return 0;
        } else {
            System.out.println("login success");
            // System.out.println("Access token: " + response);
            this.access_token = response;
            return 1;
        }
    }

    public String getAccessToken() {
        return this.access_token;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }

    public long get_id() {
        return this.id;
    }

    public static String get_user_id_csv(long[] users) {
        String return_str = "";
        if (users.length == 0) {
            return return_str;
        }
        for (int i = 0; i < users.length; i++) {
            return_str += Long.toString(users[i]);
            if (i != users.length - 1) {
                return_str += ",";
            }
        }
        return return_str;
    }

    public static User[] get_users() {
        String endpoint = "/users";
        OpenAPI_client c = new OpenAPI_client();
        String response = c.get(endpoint);
        return Json.parse(User[].class, response);
    }

    public static long[] split_csv(String user_id_csv) {
        if (user_id_csv.equals("")) {
            return new long[] {};
        }
        String[] user_id_array = user_id_csv.split(",");
        long[] users = new long[user_id_array.length];
        for (int i = 0; i < user_id_array.length; i++) {
            users[i] = Long.parseLong(user_id_array[i]);
        }
        return users;
    }
}
