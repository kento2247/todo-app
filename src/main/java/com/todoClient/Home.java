package com.todoClient;

import java.awt.BorderLayout;
import java.awt.Component;
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
        private JTextField emailField;
        private JPasswordField passwordField;

        ButtonAction(JTextField emailField, JPasswordField passwordField, String txt) {
            this.label_txt = txt;
            this.passwordField = passwordField;
            this.emailField = emailField;
        }

        public void actionPerformed(ActionEvent e) {
            if (this.label_txt == "logout") {
                System.out.println("logout");
                moveLogin();
            } else if (this.label_txt == "submit") {
                System.out.println("submit");
                // String email = this.emailField.getText();
                // String password = String.valueOf(this.passwordField.getPassword());
                // int return_flag = 0;
                // if (email.equals("")) {
                // emailField.setBackground(java.awt.Color.pink);
                // return_flag = 1;
                // System.out.println("email is empty");
                // } else {
                // emailField.setBackground(java.awt.Color.white);
                // }
                // if (password.equals("")) {
                // passwordField.setBackground(java.awt.Color.pink);
                // return_flag = 1;
                // System.out.println("password is empty");
                // } else {
                // passwordField.setBackground(java.awt.Color.white);
                // }
                // if (return_flag == 1) {
                // return;
                // }
                // User user = new User("", email, password, "");
                // moveLogin();
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
        JFrame left_bottom_frame = new JFrame();
        left_bottom_frame.setSize(main_width / 3, main_height / 10);
        JFrame right_frame = new JFrame();
        right_frame.setSize(main_width / 3 * 2, main_height);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 10, 0); // 入力ボックスの周囲の余白を設定
        JLabel emailLabel = new JLabel("Email Address:");
        JTextField emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        // 入力ボックスの高さを調整
        Font font = new Font("Arial", Font.PLAIN, 16); // フォントサイズを16に設定
        emailField.setFont(font);
        passwordField.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(emailField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(passwordField, gbc);
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
        top_frame.setSize(frame.getWidth(), main_height / 10);
        top_frame.getContentPane().add(pane0, BorderLayout.CENTER);
        top_frame.getContentPane().add(pane1, BorderLayout.WEST);

        new_frame.getContentPane().add(top_frame.getContentPane(), BorderLayout.NORTH);
        new_frame.getContentPane().add(scroll_frame.getContentPane(), BorderLayout.CENTER);
        new_frame.getContentPane().add(pane_submit, BorderLayout.SOUTH);

        return new_frame.getContentPane();
    }
}
