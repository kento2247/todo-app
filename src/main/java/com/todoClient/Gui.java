package com.todoClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui {
    private JLabel label;
    String labelPre = "";

    class numButtonAction implements ActionListener {
        private String num = "";

        numButtonAction(int num) {
            this.num = String.valueOf(num);
        }

        public void actionPerformed(ActionEvent e) {
            labelPre += this.num;
            label.setText(labelPre);
        }
    }

    class calcButtonAction implements ActionListener {
        private String type = "";

        calcButtonAction(String type) {
            this.type = type;
        }

        public void actionPerformed(ActionEvent e) {
            labelPre += this.type;
            label.setText(labelPre);
        }
    }

    class showButtonAction implements ActionListener {
        private String type = "";

        showButtonAction(String type) {
            this.type = type;
        }

        public void actionPerformed(ActionEvent e) {
            if (this.type == "=") {
                labelPre = "0" + labelPre;
                String[] strs = labelPre.split("[\\+\\-\\*\\/]");
                String[] flags = labelPre.split("[0-9]+");
                int f_pointer = 0;
                int result = Integer.parseInt(strs[0]);
                for (int i = 1; i < strs.length; i++) {
                    int num = Integer.parseInt(strs[i]);
                    if (flags[i].equals("+")) {
                        result += num;
                    } else if (flags[i].equals("-")) {
                        result -= num;
                    } else if (flags[i].equals("*")) {
                        result *= num;
                    } else if (flags[i].equals("/")) {
                        result /= num;
                    }
                    f_pointer++;
                }
                label.setText(labelPre.substring(1) + "=" + String.valueOf(result));
                labelPre = "";
            } else {
                label.setText("0");
                labelPre = "";
            }
        }
    }

    public Component[] createComponents() {
        label = new JLabel("");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton button0 = new JButton("0");
        JButton button_plus = new JButton("+");
        JButton button_minus = new JButton("-");
        JButton button_times = new JButton("*");
        JButton button_div = new JButton("/");
        JButton button_equal = new JButton("=");
        JButton button_clear = new JButton("C");

        numButtonAction buttonListener1 = new numButtonAction(1);
        numButtonAction buttonListener2 = new numButtonAction(2);
        numButtonAction buttonListener3 = new numButtonAction(3);
        numButtonAction buttonListener4 = new numButtonAction(4);
        numButtonAction buttonListener5 = new numButtonAction(5);
        numButtonAction buttonListener6 = new numButtonAction(6);
        numButtonAction buttonListener7 = new numButtonAction(7);
        numButtonAction buttonListener8 = new numButtonAction(8);
        numButtonAction buttonListener9 = new numButtonAction(9);
        numButtonAction buttonListener0 = new numButtonAction(0);
        calcButtonAction buttonListener_plus = new calcButtonAction("+");
        calcButtonAction buttonListener_minus = new calcButtonAction("-");
        calcButtonAction buttonListener_times = new calcButtonAction("*");
        calcButtonAction buttonListener_div = new calcButtonAction("/");
        showButtonAction buttonListener_equal = new showButtonAction("=");
        showButtonAction buttonListener_clear = new showButtonAction("C");
        button1.addActionListener(buttonListener1);
        button2.addActionListener(buttonListener2);
        button3.addActionListener(buttonListener3);
        button4.addActionListener(buttonListener4);
        button5.addActionListener(buttonListener5);
        button6.addActionListener(buttonListener6);
        button7.addActionListener(buttonListener7);
        button8.addActionListener(buttonListener8);
        button9.addActionListener(buttonListener9);
        button0.addActionListener(buttonListener0);
        button_plus.addActionListener(buttonListener_plus);
        button_minus.addActionListener(buttonListener_minus);
        button_times.addActionListener(buttonListener_times);
        button_div.addActionListener(buttonListener_div);
        button_equal.addActionListener(buttonListener_equal);
        button_clear.addActionListener(buttonListener_clear);

        JPanel pane0 = new JPanel();
        pane0.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        pane0.setLayout(new GridLayout(1, 1));
        pane0.add(label);

        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        pane.setLayout(new GridLayout(5, 4));

        pane.add(button1);
        pane.add(button2);
        pane.add(button3);
        pane.add(button_times);

        pane.add(button4);
        pane.add(button5);
        pane.add(button6);
        pane.add(button_div);

        pane.add(button7);
        pane.add(button8);
        pane.add(button9);
        pane.add(button_minus);

        pane.add(button0);
        pane.add(button_clear);
        pane.add(button_equal);
        pane.add(button_plus);

        Component[] components = { pane0, pane };
        return components;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("login");
        Gui app = new Gui();
        Component[] components = app.createComponents();
        Component contents0 = components[0];
        Component contents1 = components[1];
        frame.getContentPane().add(contents0, BorderLayout.NORTH);
        frame.getContentPane().add(contents1, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}