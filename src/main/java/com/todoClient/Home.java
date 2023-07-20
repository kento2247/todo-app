package com.todoClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Home extends Window {
    JFrame frame;
    User user;
    int scrollBar_width = 20;
    boolean editable = false;

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

    public void reload_home_page() {
        frame.getContentPane().removeAll();
        Component content = this.create_main_component();
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
            } else if (this.label_txt == "addTask") {
                System.out.println("addTask");
                System.out.println("user.getAccessToken()=" + user.getAccessToken());
                Task.get_tasks(user.getAccessToken());
            } else if (this.label_txt == "edit") {
                System.out.println("edit");
                editable = true;
                reload_home_page();
            } else if (this.label_txt == "save") {
                editable = false;
                Component rightside_components = frame.getContentPane().getComponent(1);
                System.out.println("rightside_components=" + rightside_components);
                reload_home_page();
            } else {
                System.out.println("undefined" + " label=" + this.label_txt);
            }
        }
    }

    private Component create_detail_top_component(String title) {
        JFrame return_frame = new JFrame();
        int taskDetail_panel_height = frame.getHeight();
        int taskDetail_panel_width = frame.getWidth() / 3 * 2 - scrollBar_width;
        int width_ratio = 5;

        JPanel action_panel = new JPanel(new GridLayout(2, 1));
        action_panel.setPreferredSize(new Dimension(taskDetail_panel_width / width_ratio, 50));
        action_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JPanel checkbox_panel = new JPanel(new GridLayout(1, 3));
        checkbox_panel.setPreferredSize(new Dimension(taskDetail_panel_width / width_ratio, 50));
        JCheckBox complete_checkbox = new JCheckBox("");
        complete_checkbox.setSelected(true); // チェックボックスの初期状態を設定。trueでチェックあり、falseでチェックなし
        complete_checkbox.addActionListener(e -> {
            boolean selected = complete_checkbox.isSelected();
            System.out.println("チェックボックスが選択されたかどうか: " + selected);
        });
        checkbox_panel.add(Box.createVerticalGlue());
        checkbox_panel.add(complete_checkbox);
        checkbox_panel.add(Box.createVerticalGlue());
        action_panel.add(checkbox_panel);

        if (editable) {
            JButton save_button = new JButton("save");
            save_button.addActionListener(new ButtonAction("save"));
            save_button.setSize(20, 20);
            action_panel.add(save_button);
        } else {
            ImageIcon edit_icon = new ImageIcon("src/main/java/com/todoClient/img/icon_edit.png");
            Image edit_image = edit_icon.getImage();
            Image scaled_edit_image = edit_image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            edit_icon = new ImageIcon(scaled_edit_image);
            JButton edit_button = new JButton(edit_icon);
            edit_button.addActionListener(new ButtonAction("edit"));
            edit_button.setSize(20, 20);
            action_panel.add(edit_button);
        }
        action_panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel taskDetail_title_panel = new JPanel(new BorderLayout());
        taskDetail_title_panel
                .setPreferredSize(new Dimension(taskDetail_panel_width / width_ratio * (width_ratio - 1), 50));
        if (editable) {
            JTextField taskDetail_title_label = new JTextField(title);
            taskDetail_title_label.setHorizontalAlignment(JTextField.CENTER);
            taskDetail_title_label.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
            taskDetail_title_panel.add(taskDetail_title_label);
        } else {
            JLabel taskDetail_title_label = new JLabel(title);
            taskDetail_title_label.setHorizontalAlignment(JLabel.CENTER);
            taskDetail_title_label.setFont(new java.awt.Font("Dialog", Font.BOLD, 30));
            taskDetail_title_panel.add(taskDetail_title_label);
        }

        return_frame.add(action_panel, BorderLayout.WEST);
        return_frame.add(taskDetail_title_panel, BorderLayout.CENTER);

        return return_frame.getContentPane();
    }

    private Component create_taskDetail_component_labelRow(String title, String content) {
        // int taskDetail_panel_width = frame.getWidth() / 3 * 2 - scrollBar_width;
        // int taskDetail_panel_height = frame.getHeight();
        JPanel return_panel = new JPanel();
        return_panel.setLayout(new GridLayout(1, 2));
        JLabel taskDetail_title_label = new JLabel(title);
        taskDetail_title_label.setHorizontalAlignment(JLabel.CENTER);
        return_panel.add(taskDetail_title_label);
        if (editable) {
            JTextField taskDetail_content_label = new JTextField(content);
            taskDetail_content_label.setHorizontalAlignment(JTextField.CENTER);
            return_panel.add(taskDetail_content_label);
        } else {
            JLabel taskDetail_content_label = new JLabel(content);
            taskDetail_content_label.setHorizontalAlignment(JLabel.CENTER);
            return_panel.add(taskDetail_content_label);
        }
        return_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return return_panel;
    }

    private Component create_taskDetail_component(Task task) {
        int taskDetail_panel_height = frame.getHeight();
        int taskDetail_panel_width = frame.getWidth() / 3 * 2 - scrollBar_width;
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        JPanel return_panel = new JPanel();
        return_panel
                .setPreferredSize(new Dimension(taskDetail_panel_width, taskDetail_panel_height));
        return_panel.setLayout(new GridLayout(6, 1));

        return_panel.add(create_detail_top_component(task.title));
        return_panel.add(create_taskDetail_component_labelRow("Body: ", task.body));
        return_panel.add(create_taskDetail_component_labelRow("Due: ", DateFormatter.format(task.due_date)));
        return_panel.add(create_taskDetail_component_labelRow("Priority: ", Integer.toString(task.priority)));
        String users_name = "";
        for (int i = 0; i < task.user.length; i++) {
            users_name += task.user[i].nickname;
            if (i != task.user.length - 1) {
                users_name += ", ";
            }
        }
        return_panel.add(create_taskDetail_component_labelRow("Users: ", users_name));
        return_panel.add(create_taskDetail_component_labelRow("Id: ", Long.toString(task.id)));

        JPanel taskDetail_body_panel = new JPanel();
        taskDetail_body_panel.setBorder(blackBorder);

        return_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return return_panel;
    }

    private Component create_leftSide_component(Task[] tasks) {
        int main_height = frame.getHeight();
        int main_width = frame.getWidth();
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        JFrame left_frame = new JFrame();
        left_frame.setPreferredSize(new Dimension(main_width / 3, main_height));
        JFrame left_upper_frame = new JFrame();
        left_upper_frame.setPreferredSize(new Dimension(main_width / 3, main_height / 8));
        JFrame left_middle_frame = new JFrame();
        left_middle_frame.setPreferredSize(new Dimension(main_width / 3, main_height / 16 * 13));
        JFrame left_bottom_frame = new JFrame();
        left_bottom_frame.setPreferredSize(new Dimension(main_width / 3, main_height / 16));

        JPanel tasks_panel = new JPanel();
        tasks_panel
                .setPreferredSize(new Dimension(main_width / 3 - scrollBar_width, main_height * 3 / 16 * tasks.length));
        tasks_panel.setLayout(new GridLayout(tasks.length, 1));
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
        tasks_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(tasks_panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(blackBorder);
        scrollPane.setPreferredSize(new Dimension(main_width / 3, main_height / 16 * 13));
        left_middle_frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton logout_button = new JButton("< Log Out");
        ButtonAction logout_listener = new ButtonAction("logout");
        logout_button.addActionListener(logout_listener);
        logout_button.setHorizontalAlignment(JButton.CENTER);
        JLabel nickname_label = new JLabel(user.nickname);
        nickname_label.setHorizontalAlignment(JLabel.CENTER);
        JPanel left_bottom_panel = new JPanel();
        left_bottom_panel.setPreferredSize(new Dimension(main_width / 3, main_height / 16));
        left_bottom_panel.setLayout(new GridLayout(1, 2));
        left_bottom_panel.setBorder(blackBorder);
        left_bottom_panel.add(logout_button);
        left_bottom_panel.add(nickname_label);
        left_bottom_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        left_bottom_frame.getContentPane().add(left_bottom_panel, BorderLayout.CENTER);

        JPanel sort_panel = new JPanel();
        sort_panel.setLayout(new GridLayout(1, 3));
        sort_panel.setBorder(blackBorder);
        sort_panel.setPreferredSize(new Dimension(main_width / 3, main_height / 16));
        JLabel sort_label = new JLabel("Sort by: ");
        sort_label.setHorizontalAlignment(JLabel.CENTER);
        JButton primary_button = new JButton("Pri");
        ButtonAction primary_listener = new ButtonAction("priority");
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
        addTask_panel.setPreferredSize(new Dimension(main_width / 3, main_height / 16));
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
        left_frame.getContentPane().add(left_upper_frame.getContentPane(), BorderLayout.NORTH);
        left_frame.getContentPane().add(left_middle_frame.getContentPane(), BorderLayout.CENTER);
        left_frame.getContentPane().add(left_bottom_frame.getContentPane(), BorderLayout.SOUTH);

        return left_frame.getContentPane();
    }

    private Component create_rightSide_component(Task task) {
        int main_height = frame.getHeight();
        int main_width = frame.getWidth();
        JFrame right_frame = new JFrame();
        right_frame.setPreferredSize(new Dimension(main_width / 3 * 2, main_height));

        Component taskDetail_panel = create_taskDetail_component(task);
        JScrollPane taskDetail_scroll = new JScrollPane(taskDetail_panel); // スクロール可能にする
        taskDetail_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        taskDetail_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        taskDetail_scroll
                .setPreferredSize(new Dimension(main_width / 3 * 2, main_height));
        taskDetail_scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        right_frame.add(taskDetail_scroll);
        return right_frame.getContentPane();
    }

    public Component create_main_component() {
        int main_height = frame.getHeight();
        int main_width = frame.getWidth();
        JFrame new_frame = new JFrame();
        new_frame.setPreferredSize(new Dimension(main_width, main_height));
        new_frame.setLayout(new BorderLayout());

        // 仮想タスクデータ
        int repeat = 20;
        User[] users = new User[] { user };
        Task t1 = new Task(0, users, "title", "body", 1, "20201010T1010");
        Task[] tasks = new Task[repeat];
        for (int i = 0; i < repeat; i++) {
            tasks[i] = t1;
        }

        new_frame.getContentPane().add(create_leftSide_component(tasks), BorderLayout.WEST);
        new_frame.getContentPane().add(create_rightSide_component(t1), BorderLayout.EAST);
        return new_frame.getContentPane();
    }
}
