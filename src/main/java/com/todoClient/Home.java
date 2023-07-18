package com.todoClient;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends Window {
    JFrame frame;

    Home(JFrame f) {
        this.frame = f;
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
        JFrame left_frame = new JFrame();
        left_frame.setSize(main_width / 3, main_height);
        JFrame left_upper_frame = new JFrame();
        left_upper_frame.setSize(main_width / 3, main_height / 10 * 9);
        JFrame left_bottom_frame = new JFrame();
        left_bottom_frame.setSize(main_width / 3, main_height / 10);
        JFrame right_frame = new JFrame();
        right_frame.setSize(main_width / 3 * 2, main_height);

        // 仮想タスクデータ
        Task t1 = new Task(0, "0", "title1", "body", "2020-01-01");
        Task t2 = new Task(1, "1", "title2", "body", "2020-01-01");
        Task t3 = new Task(2, "2", "title3", "body", "2020-01-01");
        Task[] tasks = new Task[] { t1, t2, t3 };
        //

        JPanel tasks_panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2); // ボックスの周囲の余白を設定

        for (int x = 0; x < 10; x++) {
            for (int i = 0; i < tasks.length; i++) {
                gbc.gridx = 0;
                gbc.gridy = i;
                JLabel task_label = new JLabel(tasks[i].title);
                tasks_panel.add(task_label, gbc);
                gbc.gridx = 1;
            }
        }

        // 入力ボックスの高さを調整
        tasks_panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        tasks_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // JFrame scroll_frame = new JFrame();
        // JScrollPane scrollPane = new JScrollPane(tasks_panel);
        // scroll_frame.add(scrollPane);
        // left_upper_frame.getContentPane().add(scroll_frame, BorderLayout.CENTER);
        left_upper_frame.getContentPane().add(tasks_panel, BorderLayout.CENTER);

        JPanel logout_panel = new JPanel();
        JButton logout = new JButton("Log out");
        ButtonAction logout_listener = new ButtonAction("logout");
        logout.addActionListener(logout_listener);
        logout.setHorizontalAlignment(JButton.CENTER);
        // logout.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        logout_panel.setLayout(new GridLayout(1, 1));
        logout_panel.add(logout);
        left_bottom_frame.getContentPane().add(logout_panel, BorderLayout.CENTER);

        JLabel label = new JLabel("Hello world!");
        label.setHorizontalAlignment(JLabel.CENTER);
        right_frame.setLayout(new GridLayout(1, 1));
        right_frame.add(label);

        // JFrame top_frame = new JFrame();
        // top_frame.setSize(frame.getWidth(), main_height / 10);
        // top_frame.getContentPane().add(pane0, BorderLayout.CENTER);
        // top_frame.getContentPane().add(pane1, BorderLayout.WEST);

        left_frame.getContentPane().add(left_upper_frame.getContentPane(), BorderLayout.NORTH);
        left_frame.getContentPane().add(left_bottom_frame.getContentPane(), BorderLayout.SOUTH);
        new_frame.getContentPane().add(left_frame.getContentPane(), BorderLayout.WEST);
        new_frame.getContentPane().add(right_frame.getContentPane(), BorderLayout.EAST);
        return new_frame.getContentPane();
    }
}
