package com.todoClient;

import java.util.Date;

public class History {
    Date make_time;
    Date last_edit_time;

    History() {
        make_time = new Date();
        last_edit_time = new Date();
    }
}
