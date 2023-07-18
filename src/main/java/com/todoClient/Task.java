package com.todoClient;

public class Task extends History {
    long id;
    String task_set_id;
    String title;
    String body;
    String due_date;

    Task(long id, String task_set_id, String title, String body, String due_date) {
        this.id = id;
        this.task_set_id = task_set_id;
        this.title = title;
        this.body = body;
        this.due_date = due_date;
    }
}