package com.todoClient;

import java.util.Date;

public class Task extends History {
    long id;
    User user;
    String title;
    String body;
    int priority;
    Date due_date;
    boolean is_completed;
    boolean is_archived_on_completion;
    User[] shared_users;

    Task(long id, User user, String title, String body, int priority, Date due_date) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.body = body;
        this.priority = priority;
        this.due_date = due_date;
        this.is_completed = false;
        this.is_archived_on_completion = false;
        this.shared_users = new User[] {};
    }

    public static Task[] get_tasks(String access_token) {
        String endpoint = "/tasks";
        OpenAPI_client c = new OpenAPI_client();
        c.set_access_token(access_token);
        String response = c.get(endpoint);
        System.out.println(response);
        Task[] tasks = Json.parse(Task[].class, response);
        return tasks;
    }
}