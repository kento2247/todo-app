package com.todoClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class Home extends Window {
    JFrame frame;
    User user;

    Home(JFrame f, User user) {
        this.frame = f;
        this.user = user;
    }

    public void moveLogin() {
        frame.getContentPane().removeAll();
        Login login = new Login(frame);
        Component content = login.createComponents();
        frame.getContentPane().add(content, BorderLayout.CENTER);
        frame.getContentPane().revalidate();
    }

    class ButtonAction implements ActionListener {
        private String label_txt = "";

        ButtonAction(String txt) {
            this.label_txt = txt;
        }

        public void actionPerformed(ActionEvent e) {
            if (this.label_txt == "logout") {
                System.out.println("logout");
                moveLogin();
            } else if (this.label_txt == "submit") {
                System.out.println("submit");
            } else {
                System.out.println("undefined");
            }
        }

    }

    public Component createComponents() {
        int main_height = frame.getHeight();
        int main_width = frame.getWidth();
        JFrame new_frame = new JFrame();
        new_frame.setSize(main_width, main_height);
        new_frame.setLayout(new BorderLayout());
        JFrame left_frame = new JFrame();
        left_frame.setSize(main_width / 3, main_height);
        JFrame left_upper_frame = new JFrame();
        left_upper_frame.setSize(main_width / 3, main_height / 8);
        JFrame left_middle_frame = new JFrame();
        left_middle_frame.setSize(main_width / 3, main_height / 8 * 6);
        JFrame left_bottom_frame = new JFrame();
        left_bottom_frame.setSize(main_width / 3, main_height / 8);
        JFrame right_frame = new JFrame();
        right_frame.setSize(main_width / 3 * 2, main_height);
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        // 仮想タスクデータ
        Task t1 = new Task(0, "0", "title1", "body", "2020-01-01");
        Task t2 = new Task(1, "1", "title2", "body", "2020-01-01");
        Task t3 = new Task(2, "2", "title3", "body", "2020-01-01");
        Task t4 = new Task(3, "3", "title4", "body", "2020-01-01");
        Task t5 = new Task(4, "4", "title5", "body", "2020-01-01");
        Task t6 = new Task(5, "5", "title6", "body", "2020-01-01");
        Task[] tasks = new Task[] { t1, t2, t3, t4, t5, t6 };
        JPanel tasks_panel = new JPanel();
        tasks_panel.setLayout(new GridLayout(tasks.length * 5, 1));
        tasks_panel.setSize(main_width / 3, main_height / 8 * 6);
        for (int x = 0; x < 5; x++) {
            for (int i = 0; i < tasks.length; i++) {
                JButton task_button = new JButton(tasks[i].title);
                // task_button.setBorder(BorderFactory.createEmptyBorder(10, 3000, 10, 30));
                task_button.setHorizontalAlignment(JButton.CENTER);
                task_button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("task_button clicked");
                    }
                });
                tasks_panel.add(task_button);
            }
        }
        tasks_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tasks_panel.setBorder(blackBorder);
        JScrollPane scrollPane = new JScrollPane(tasks_panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(main_width / 3, main_height / 8 * 6));
        left_middle_frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel logout_panel = new JPanel();
        logout_panel.setSize(main_width / 3, main_height / 8);
        JButton logout = new JButton("Log out");
        ButtonAction logout_listener = new ButtonAction("logout");
        logout.addActionListener(logout_listener);
        logout.setHorizontalAlignment(JButton.CENTER);
        logout_panel.setLayout(new GridLayout(1, 1));
        logout_panel.setBorder(blackBorder);
        logout_panel.add(logout);
        logout_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        left_bottom_frame.getContentPane().add(logout_panel, BorderLayout.CENTER);

        JPanel sort_panel = new JPanel();
        sort_panel.setSize(main_width / 3, main_height / 8);
        sort_panel.setLayout(new GridLayout(1, 3));
        sort_panel.setBorder(blackBorder);
        JLabel sort_label = new JLabel("Sort by");
        sort_label.setHorizontalAlignment(JLabel.CENTER);
        JButton primary_button = new JButton("Primary");
        ButtonAction primary_listener = new ButtonAction("primary");
        primary_button.addActionListener(primary_listener);
        JButton due_button = new JButton("Due");
        ButtonAction due_listener = new ButtonAction("due");
        primary_button.addActionListener(primary_listener);
        due_button.addActionListener(due_listener);
        primary_button.setHorizontalAlignment(JButton.CENTER);
        due_button.setHorizontalAlignment(JButton.CENTER);
        sort_panel.add(sort_label);
        sort_panel.add(primary_button);
        sort_panel.add(due_button);
        sort_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        left_upper_frame.getContentPane().add(sort_panel, BorderLayout.CENTER);

        JLabel label = new JLabel("Hello world!");
        label.setHorizontalAlignment(JLabel.CENTER);
        right_frame.setLayout(new GridLayout(1, 1));
        right_frame.add(label);

        // JFrame top_frame = new JFrame();
        // top_frame.setSize(frame.getWidth(), main_height / 10);
        // top_frame.getContentPane().add(pane0, BorderLayout.CENTER);
        // top_frame.getContentPane().add(pane1, BorderLayout.WEST);

        left_frame.getContentPane().add(left_upper_frame.getContentPane(), BorderLayout.NORTH);
        left_frame.getContentPane().add(left_middle_frame.getContentPane(), BorderLayout.CENTER);
        left_frame.getContentPane().add(left_bottom_frame.getContentPane(), BorderLayout.SOUTH);
        new_frame.getContentPane().add(left_frame.getContentPane(), BorderLayout.WEST);
        new_frame.getContentPane().add(right_frame.getContentPane(), BorderLayout.EAST);
        return new_frame.getContentPane();
    }
}
