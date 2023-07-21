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