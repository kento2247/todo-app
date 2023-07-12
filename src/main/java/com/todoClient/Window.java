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

    public void login() {
        frame.getContentPane().removeAll();
        Login login = new Login();
        Component content[] = login.createComponents();
        frame.getContentPane().add(content[0], BorderLayout.CENTER);
        frame.getContentPane().add(content[1], BorderLayout.SOUTH);
        frame.getContentPane().revalidate();
    }

    public void signin() {
        frame.getContentPane().removeAll();
        Signin s = new Signin(frame);
        Component content[] = s.createComponents();
        frame.getContentPane().add(content[0], BorderLayout.CENTER);
        // frame.getContentPane().add(content[1], BorderLayout.SOUTH);
        frame.getContentPane().revalidate();
    }

    public void signup() {
        frame.getContentPane().removeAll();
        Signup s = new Signup(frame);
        Component content[] = s.createComponents();
        frame.getContentPane().add(content[0], BorderLayout.CENTER);
        // frame.getContentPane().add(content[1], BorderLayout.SOUTH);
        frame.getContentPane().revalidate();
    }
}