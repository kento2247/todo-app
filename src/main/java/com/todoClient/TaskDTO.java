package com.todoClient;

import java.util.Date;

public class TaskDTO {
    String title;
    String body;
    int priority;
    Date due_date;
    boolean is_completed;
    boolean is_archived_on_completion;
    User[] shared_users;

    TaskDTO(String title, String body, int priority, Date due_date) {
        this.title = title;
        this.body = body;
        this.priority = priority;
        this.due_date = due_date;
        this.is_completed = false;
        this.is_archived_on_completion = false;
        this.shared_users = new User[]{};
    }

    public static Task create_task (String access_token, Task taskDTO) {
        String endpoint = "/tasks";
        OpenAPI_client c = new OpenAPI_client();
        c.set_access_token(access_token);
        String response = c.post(endpoint, Json.stringify(taskDTO));
        System.out.println(response);
        Task task = Json.parse(Task.class, response);
        return task;
    }

    public static Task put_task (String access_token, Task taskDTO, long id) {
        String endpoint = "/tasks/" + id;
        OpenAPI_client c = new OpenAPI_client();
        c.set_access_token(access_token);
        String response = c.put(endpoint, Json.stringify(taskDTO));
        System.out.println(response);
        Task task = Json.parse(Task.class, response);
        return task;
    }
}
