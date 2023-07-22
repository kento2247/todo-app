package com.todoClient;

import java.util.Date;

public class Task extends History {
    long id;
    User user;
    String title;
    String body;
    int priority;
    Date due_date;
    boolean _completed;
    boolean _archived_on_completion;
    long[] shared_users;

    Task(long id, User user, String title, String body, int priority, Date due_date) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.body = body;
        this.priority = priority;
        this.due_date = due_date;
        this._completed = false;
        this._archived_on_completion = false;
        this.shared_users = new long[] {};
    }

    public static Task[] get_tasks(String access_token) {
        String endpoint = "/tasks";
        OpenAPI_client c = new OpenAPI_client();
        c.set_access_token(access_token);
        String response = c.get(endpoint);
        System.out.println(response);
        Task[] tasks = Json.parse(Task[].class, response);
        for (int i = 0; i < tasks.length; i++) {
            tasks[i].due_date = DateFormatter.format(DateFormatter.format(tasks[i].due_date, -9));
            tasks[i].created_at = DateFormatter.format(DateFormatter.format(tasks[i].created_at, -9));
            tasks[i].updated_at = DateFormatter.format(DateFormatter.format(tasks[i].updated_at, -9));
        }
        // System.out.println(tasks[0]);
        return tasks;
    }

    public static Task put_task(String access_token, Task taskDTO, long id) {
        System.out.println(taskDTO.updated_at);
        taskDTO.edit();
        System.out.println(taskDTO.updated_at);
        System.out.println("put_task");
        String endpoint = "/tasks/" + id;
        OpenAPI_client c = new OpenAPI_client();
        c.set_access_token(access_token);
        String response = c.put(endpoint, Json.stringify(taskDTO));
        System.out.println(response);
        Task response_obj = Json.parse(Task.class, response);
        response_obj.due_date = DateFormatter.format(DateFormatter.format(response_obj.due_date, -9));
        response_obj.created_at = DateFormatter.format(DateFormatter.format(response_obj.created_at, -9));
        response_obj.updated_at = DateFormatter.format(DateFormatter.format(response_obj.updated_at, -9));
        return response_obj;
    }

    public static Task[] get_tasks_demmo(User user) {
        // 仮想タスクデータ
        int repeat = 20;
        Task[] tasks = new Task[repeat];
        for (int i = 0; i < repeat; i++) {
            Task t1 = new Task(i, user, "title", "body", 1, DateFormatter.format("2021/01/01 10:10"));
            tasks[i] = t1;
        }
        return tasks;
    }

    public static Task find_target_task(Task[] tasks, long id) {
        if (tasks.length == 0) {
            System.out.println("tasks is empty");
            return null;
        }
        if (id == -1) {
            return tasks[0];
        }
        for (Task task : tasks) {
            if (task.id == id) {
                return task;
            }
        }
        return null;
    }

    public static int find_target_task_indexNum(Task[] tasks, long id) {
        if (tasks.length == 0) {
            System.out.println("tasks is empty");
            return -1;
        }
        if (id == -1) {
            return 0;
        }
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i].id == id) {
                return i;
            }
        }
        return -1;
    }

}