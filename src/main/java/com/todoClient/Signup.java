package com.todoClient;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Signup extends Window {
    JFrame frame;

    Signup(JFrame f) {
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
        private JTextField usernameField;
        private JTextField emailField;
        private JPasswordField passwordField;
        private JPasswordField confirm_passwordField;

        ButtonAction(JTextField usernameField, JTextField emailField, JPasswordField passwordField,
                JPasswordField confirm_passwordField, String txt) {
            this.label_txt = txt;
            this.usernameField = usernameField;
            this.passwordField = passwordField;
            this.emailField = emailField;
            this.confirm_passwordField = confirm_passwordField;
        }

        public void actionPerformed(ActionEvent e) {
            if (this.label_txt == "back") {
                System.out.println("back to login");
                moveLogin();
            } else if (this.label_txt == "submit") {
                System.out.println("submit");
                String username = this.usernameField.getText();
                String email = this.emailField.getText();
                String password = String.valueOf(this.passwordField.getPassword());
                String confirm_password = String.valueOf(this.confirm_passwordField.getPassword());
                int return_flag = 0;
                if (username.equals("")) {
                    usernameField.setBackground(java.awt.Color.pink);
                    return_flag = 1;
                    System.out.println("username is empty");
                } else {
                    usernameField.setBackground(java.awt.Color.white);
                }
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
                if (confirm_password.equals("")) {
                    confirm_passwordField.setBackground(java.awt.Color.pink);
                    return_flag = 1;
                    System.out.println("confirm password is empty");
                } else {
                    confirm_passwordField.setBackground(java.awt.Color.white);
                }
                if (!password.equals(confirm_password)) {
                    passwordField.setBackground(java.awt.Color.pink);
                    confirm_passwordField.setBackground(java.awt.Color.pink);
                    return_flag = 1;
                    System.out.println("password and confirm password are not same");
                } else {
                    if (!password.equals("") && !confirm_password.equals("")) {
                        passwordField.setBackground(java.awt.Color.white);
                        confirm_passwordField.setBackground(java.awt.Color.white);
                    }
                }
                if (return_flag == 1) {
                    return;
                }
                User user = new User(username, email, password);
                user.signup();
                moveLogin();
            } else {
                System.out.println("undefined");
            }
        }

    }

    public Component createComponents() {
        JFrame new_frame = new JFrame();

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 10, 0); // 入力ボックスの周囲の余白を設定
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email Address:");
        JTextField emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField(20);
        // 入力ボックスの高さを調整
        Font font = new Font("Arial", Font.PLAIN, 16); // フォントサイズを16に設定
        usernameField.setFont(font);
        emailField.setFont(font);
        passwordField.setFont(font);
        confirmPasswordField.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(emailField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(confirmPasswordField, gbc);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JFrame scroll_frame = new JFrame();
        JScrollPane scrollPane = new JScrollPane(inputPanel);
        scroll_frame.add(scrollPane);

        JPanel pane_submit = new JPanel();
        pane_submit.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        pane_submit.setLayout(new GridLayout(1, 1));
        JButton submit = new JButton("SIGN UP");
        submit.setPreferredSize(new Dimension(100, 50));
        submit.setFont(new Font("Dialog", Font.PLAIN, 20));
        ButtonAction submit_listener = new ButtonAction(usernameField, emailField, passwordField, confirmPasswordField,
                "submit");
        submit.addActionListener(submit_listener);
        pane_submit.add(submit);

        JPanel pane0 = new JPanel();
        JLabel label = new JLabel("SIGN UP PAGE");
        label.setFont(new Font("Dialog", Font.PLAIN, 30));
        label.setHorizontalAlignment(JLabel.CENTER);
        pane0.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        pane0.setLayout(new GridLayout(1, 1));
        pane0.add(label);

        JPanel pane1 = new JPanel();
        JButton back = new JButton("< BACK");
        ButtonAction back_listener = new ButtonAction(usernameField, emailField, passwordField, confirmPasswordField,
                "back");
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
