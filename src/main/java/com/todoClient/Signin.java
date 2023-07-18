package com.todoClient;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Signin extends Window {
    JFrame frame;

    Signin(JFrame f) {
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
        private JTextField emailField;
        private JPasswordField passwordField;

        ButtonAction(JTextField emailField, JPasswordField passwordField, String txt) {
            this.label_txt = txt;
            this.passwordField = passwordField;
            this.emailField = emailField;
        }

        public void actionPerformed(ActionEvent e) {
            if (this.label_txt == "back") {
                System.out.println("back to login");
                moveLogin();
            } else if (this.label_txt == "submit") {
                System.out.println("submit");
                String email = this.emailField.getText();
                String password = String.valueOf(this.passwordField.getPassword());
                int return_flag = 0;
                if (email.equals("")) {
                    emailField.setBackground(java.awt.Color.pink);
                    return_flag = 1;
                    System.out.println("email is empty");
                } else {
                    emailField.setBackground(java.awt.Color.white);
                }
                if (password.equals("")) {
                    passwordField.setBackground(java.awt.Color.pink);
                    return_flag = 1;
                    System.out.println("password is empty");
                } else {
                    passwordField.setBackground(java.awt.Color.white);
                }
                if (return_flag == 1) {
                    return;
                }
                User user = new User("", email, password, "");
                user.signin();
                // moveLogin();
            } else {
                System.out.println("undefined");
            }
        }

    }

    public Component createComponents() {
        JFrame new_frame = new JFrame();

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 0));
        JLabel emailLabel = new JLabel("Email Address:");
        JTextField emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        // 入力ボックスの高さを調整
        Font font = new Font("Arial", Font.PLAIN, 16); // フォントサイズを16に設定
        emailField.setFont(font);
        passwordField.setFont(font);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JFrame scroll_frame = new JFrame();
        JScrollPane scrollPane = new JScrollPane(inputPanel);
        scroll_frame.add(scrollPane);

        JPanel pane_submit = new JPanel();
        pane_submit.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        pane_submit.setLayout(new GridLayout(1, 1));
        JButton submit = new JButton("SIGN IN");
        ButtonAction submit_listener = new ButtonAction(emailField, passwordField, "submit");
        submit.addActionListener(submit_listener);
        pane_submit.add(submit);

        JPanel pane0 = new JPanel();
        JLabel label = new JLabel("SIGN IN");
        label.setHorizontalAlignment(JLabel.CENTER);
        pane0.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        pane0.setLayout(new GridLayout(1, 1));
        pane0.add(label);

        JPanel pane1 = new JPanel();
        JButton back = new JButton("< BACK");
        ButtonAction back_listener = new ButtonAction(emailField, passwordField, "back");
        back.addActionListener(back_listener);
        back.setHorizontalAlignment(JButton.CENTER);
        pane1.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        pane1.setLayout(new GridLayout(1, 1));
        pane1.add(back);

        JFrame top_frame = new JFrame("");
        int main_height = frame.getHeight();
        top_frame.setSize(frame.getWidth(), main_height / 10);
        top_frame.getContentPane().add(pane0, BorderLayout.CENTER);
        top_frame.getContentPane().add(pane1, BorderLayout.WEST);

        new_frame.getContentPane().add(top_frame.getContentPane(), BorderLayout.NORTH);
        new_frame.getContentPane().add(scroll_frame.getContentPane(), BorderLayout.CENTER);
        new_frame.getContentPane().add(pane_submit, BorderLayout.SOUTH);

        return new_frame.getContentPane();
    }
}
