package com.todoClient;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;

public class Window {
    JFrame frame;

    public void init() {
        this.frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 480);
        // frame.pack();
        frame.setVisible(true);
        // System.out.println("Window created in " + this.getClass().getName());
    }

    public void start_page() {
        frame.getContentPane().removeAll();
        Login login = new Login(frame);
        Component content = login.createComponents();
        frame.getContentPane().add(content, BorderLayout.CENTER);
        frame.getContentPane().revalidate();
    }

    public void home_page(User user) {
        frame.getContentPane().removeAll();
        Home home = new Home(frame, user);
        //
        Task[] tasks = Task.get_tasks_demmo(user);
        //
        Component content = home.create_main_component(tasks, -1);
        frame.getContentPane().add(content, BorderLayout.CENTER);
        frame.getContentPane().revalidate();
    }
}