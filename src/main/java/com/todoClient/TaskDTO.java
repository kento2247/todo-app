package com.todoClient;

import java.util.Date;

public class TaskDTO {
    String title;
    String body;
    int priority;
    Date due_date;
    boolean _completed;
    boolean _archived_on_completion;
    Date created_at;
    Date updated_at;
    long[] shared_users;

    TaskDTO(String title, String body, int priority, Date due_date, boolean is_completed, boolean is_archived_on_completion,  Date created_at,  Date updated_at,  long[] shared_users) {
        this.title = title;
        this.body = body;
        this.priority = priority;
        this.due_date = due_date;
        this._completed = is_completed;
        this._archived_on_completion = is_archived_on_completion;
        this.created_at = new Date();
        this.updated_at = new Date();
        this.shared_users = shared_users;
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

}
