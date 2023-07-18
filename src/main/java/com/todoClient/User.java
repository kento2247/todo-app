package com.todoClient;

public class User extends History {
    String email;
    String nickname;
    String password;
    String status;

    User(String nickname, String email, String password, String status) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.status = status;
    }

    public int signup() {
        System.out.println("signup");
        System.out.println("email: " + this.email);
        System.out.println("nickname: " + this.nickname);
        System.out.println("password: " + this.password);
        return 0;
    }

    public int signin() {
        System.out.println("signup");
        System.out.println("email: " + this.email);
        System.out.println("nickname: " + this.nickname);
        System.out.println("password: " + this.password);
        return 0;
    }
}
