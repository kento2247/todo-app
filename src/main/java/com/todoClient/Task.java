package com.todoClient;

public class Task extends History {
    long id;
    User user;
    String title;
    String body;
    int priority;
    String due_date;
    boolean is_completed;
    boolean is_archived_on_completion;

    Task(long id, User user, String title, String body, int priority, String due_date) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.body = body;
        this.priority = priority;
        this.due_date = due_date;
        this.is_completed = false;
        this.is_archived_on_completion = false;
    }

    // public Task[] get_tasks(String access_token) {
    // String endpoint = "/tasks";
    // OpenAPI_client c = new OpenAPI_client();
    // String response = c.post(endpoint, json_str, "");
    // if (response.equals("")) {
    // System.out.println("login failed");
    // return 0;
    // } else {
    // System.out.println("login success");
    // System.out.println("Access token: " + response);
    // this.access_token = response;
    // return 1;
    // }
    // }
}