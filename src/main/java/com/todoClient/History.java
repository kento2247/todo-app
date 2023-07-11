package com.todoClient;

import java.util.Date;

public class History {
    Date created_at;
    Date updated_at;

    History() {
        created_at = new Date();
        updated_at = new Date();
    }

    void edit() {
        updated_at = new Date();
    }
}
