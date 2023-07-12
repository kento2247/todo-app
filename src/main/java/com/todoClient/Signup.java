package com.todoClient;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Signup extends Window {
    JFrame frame;

    Signup(JFrame f) {
        this.frame = f;
    }

    class ButtonAction implements ActionListener {
        private String label_txt = "";

        ButtonAction(String txt) {
            this.label_txt = txt;
        }

        public void actionPerformed(ActionEvent e) {
            if (this.label_txt == "signin") {
                System.out.println("signin");
            } else if (this.label_txt == "signup") {
                System.out.println("signup");
            }
        }
    }

    public Component[] createComponents() {
        JPanel pane0 = new JPanel();
        JLabel label = new JLabel("SIGN UP");
        label.setHorizontalAlignment(JLabel.CENTER);
        // label.setPreferredSize(new Dimension(300, 100));
        pane0.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        pane0.setLayout(new GridLayout(1, 1));
        pane0.add(label);

        JPanel pane1 = new JPanel();
        pane1.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        pane1.setLayout(new GridLayout(1, 2));
        JButton signin = new JButton("SIGN IN");
        JButton signup = new JButton("SIGN UP");
        ButtonAction signin_listener = new ButtonAction("signin");
        ButtonAction signup_listener = new ButtonAction("signup");
        signin.addActionListener(signin_listener);
        signup.addActionListener(signup_listener);
        pane1.add(signin);
        pane1.add(signup);

        Component[] components = { pane0, pane1 };
        return components;
    }
}
