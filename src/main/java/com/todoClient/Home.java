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
                System.out.println("undefined" + " label=" + this.label_txt);
            }
        }

    }

    public Component createComponents() {
        int main_height = frame.getHeight();
        int main_width = frame.getWidth();
        JFrame new_frame = new JFrame();
        new_frame.setPreferredSize(new Dimension(main_width, main_height));
        new_frame.setLayout(new BorderLayout());
        JFrame left_frame = new JFrame();
        left_frame.setPreferredSize(new Dimension(main_width / 3, main_height));
        JFrame left_upper_frame = new JFrame();
        left_upper_frame.setPreferredSize(new Dimension(main_width / 3, main_height / 8));
        JFrame left_middle_frame = new JFrame();
        left_middle_frame.setPreferredSize(new Dimension(main_width / 3, main_height / 16 * 13));
        JFrame left_bottom_frame = new JFrame();
        left_bottom_frame.setPreferredSize(new Dimension(main_width / 3, main_height / 16));
        JFrame right_frame = new JFrame();
        right_frame.setPreferredSize(new Dimension(main_width / 3 * 2, main_height));
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        // 仮想タスクデータ
        Task t1 = new Task(0, user, "title", "body", 1, "20201010T1010");
        Task[] tasks = new Task[] { t1 };
        JPanel tasks_panel = new JPanel();
        int repeat = 20;
        tasks_panel.setLayout(new GridLayout(tasks.length * repeat, 1));
        for (int x = 0; x < repeat; x++) {
            for (int i = 0; i < tasks.length; i++) {
                JPanel task_panel = new JPanel();
                task_panel.setLayout(new GridLayout(3, 1));
                JButton task_button = new JButton(tasks[i].title);
                task_button.setHorizontalAlignment(JButton.CENTER);
                task_button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("task_button clicked");
                    }
                });
                JLabel due_label = new JLabel("due: " + DateFormatter.format(tasks[i].due_date));
                due_label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                JLabel priority_label = new JLabel("priority: " + tasks[i].priority);
                priority_label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
                task_panel.add(task_button);
                task_panel.add(due_label);
                task_panel.add(priority_label);
                task_panel.setBorder(blackBorder);
                tasks_panel.add(task_panel);
            }
        }
        tasks_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(tasks_panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(blackBorder);
        left_middle_frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton logout_button = new JButton("< Log Out");
        ButtonAction logout_listener = new ButtonAction("logout");
        logout_button.addActionListener(logout_listener);
        logout_button.setHorizontalAlignment(JButton.CENTER);
        JLabel nickname_label = new JLabel(user.nickname);
        nickname_label.setHorizontalAlignment(JLabel.CENTER);
        JPanel left_bottom_panel = new JPanel();
        left_bottom_panel.setLayout(new GridLayout(1, 2));
        left_bottom_panel.setBorder(blackBorder);
        left_bottom_panel.add(logout_button);
        left_bottom_panel.add(nickname_label);
        left_bottom_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        left_bottom_frame.getContentPane().add(left_bottom_panel, BorderLayout.CENTER);

        JPanel sort_panel = new JPanel();
        sort_panel.setLayout(new GridLayout(1, 3));
        sort_panel.setBorder(blackBorder);
        JLabel sort_label = new JLabel("Sort by: ");
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
        JPanel addTask_panel = new JPanel();
        addTask_panel.setLayout(new GridLayout(1, 1));
        JButton addTask_button = new JButton("+ Add Task");
        ButtonAction addTask_listener = new ButtonAction("addTask");
        addTask_button.addActionListener(addTask_listener);
        addTask_button.setHorizontalAlignment(JButton.CENTER);
        addTask_panel.setBorder(blackBorder);
        addTask_panel.add(addTask_button);
        addTask_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        left_upper_frame.setLayout(new GridLayout(2, 1));
        left_upper_frame.getContentPane().add(sort_panel, BorderLayout.NORTH);
        left_upper_frame.getContentPane().add(addTask_panel, BorderLayout.SOUTH);

        JLabel label = new JLabel("Hello world!");
        label.setHorizontalAlignment(JLabel.CENTER);
        right_frame.setLayout(new GridLayout(1, 1));
        right_frame.add(label);

        left_frame.getContentPane().add(left_upper_frame.getContentPane(), BorderLayout.NORTH);
        left_frame.getContentPane().add(left_middle_frame.getContentPane(), BorderLayout.CENTER);
        left_frame.getContentPane().add(left_bottom_frame.getContentPane(), BorderLayout.SOUTH);
        System.out.println("left_frame: " + left_frame.getContentPane().getSize());
        System.out.println("right_frame: " + right_frame.getContentPane().getSize());
        new_frame.getContentPane().add(left_frame.getContentPane(), BorderLayout.WEST);
        new_frame.getContentPane().add(right_frame.getContentPane(), BorderLayout.EAST);
        return new_frame.getContentPane();
    }
}
