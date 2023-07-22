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
import java.util.Date;

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
    JCheckBox complete_checkbox;
    JTextField taskDetail_title_label;
    JTextField taskDetail_due_label;
    JTextField taskDetail_body_label;
    JTextField taskDetail_priority_label;
    JTextField taskDetail_users_label;
    JTextField taskDetail_createdat_label;
    JTextField taskDetail_updatedat_label;
    JCheckBox taskDetail_archivedoncompletion_checkbox;
    Task[] tasks = new Task[] {};
    long target_task_id = -1;

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
        Task[] new_tasks = Task.get_tasks(user.getAccessToken());
        if (new_tasks.length == 0) {
            TaskDTO new_task = new TaskDTO("example", "this is an example", 0, new Date(), false, true, new Date(),
                    new Date(),
                    new long[] {});
            new_tasks = new Task[] { TaskDTO.create_task(user.getAccessToken(), new_task) };
        }
        frame.getContentPane().removeAll();
        Component content = this.create_main_component(new_tasks, target_task_id);
        frame.getContentPane().add(content, BorderLayout.CENTER);
        frame.getContentPane().revalidate();
    }

    public void reload_home_page(Task[] tasks) {
        frame.getContentPane().removeAll();
        Component content = this.create_main_component(tasks, target_task_id);
        frame.getContentPane().add(content, BorderLayout.CENTER);
        frame.getContentPane().revalidate();
    }

    public void put_task() {
        String new_title = taskDetail_title_label.getText();
        String new_body = taskDetail_body_label.getText();
        String new_due = taskDetail_due_label.getText();
        String new_priority = taskDetail_priority_label.getText();
        String new_users_csv = taskDetail_users_label.getText();
        String new_created_at = taskDetail_createdat_label.getText();
        String new_updated_at = taskDetail_updatedat_label.getText();
        Boolean new_archived_on_completion = taskDetail_archivedoncompletion_checkbox.isSelected();
        System.out.println("new_title is " + new_title);
        int task_index = Task.find_target_task_indexNum(tasks, target_task_id);
        tasks[task_index].title = new_title;
        tasks[task_index].body = new_body;
        tasks[task_index]._archived_on_completion = new_archived_on_completion;
        try {
            tasks[task_index].due_date = DateFormatter.format(new_due);
            tasks[task_index].priority = Integer.parseInt(new_priority);
            long[] new_user_ids = User.split_csv_remove_myself(new_users_csv, user.get_id());
            tasks[task_index].shared_users = new_user_ids;
            System.out.println("new_id is " + User.get_user_id_csv(new_user_ids));
            tasks[task_index].created_at = DateFormatter.format(new_created_at);
            tasks[task_index].updated_at = DateFormatter.format(new_updated_at);
            if (tasks[task_index].due_date == null || tasks[task_index].shared_users == null) {
                System.out.println("due_date or shared_users is null");
                editable = true;
            } else {
                editable = false;
                // reload_home_page();
            }

            System.out.println(tasks[task_index].due_date);
            System.out.println(tasks[task_index].created_at);
            Task response = Task.put_task(user.getAccessToken(), tasks[task_index], tasks[task_index].id);
            System.out.println("response=" + response);
        } catch (Exception e2) {
            editable = true;
            System.out.println("parse error: " + e2);
        }
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
                System.out.println(new Date());
                TaskDTO new_task = new TaskDTO("", "", 0, new Date(), false, true, new Date(),
                        new Date(),
                        new long[] {});
                int tasks_length = tasks.length;
                Task[] new_tasks = new Task[tasks_length + 1];
                for (int i = 0; i < tasks_length; i++) {
                    new_tasks[i] = tasks[i];
                }
                new_tasks[tasks_length] = TaskDTO.create_task(user.getAccessToken(), new_task);
                tasks = new_tasks;
                editable = true;
                target_task_id = new_tasks[tasks_length].id;
                reload_home_page(tasks);
            } else if (this.label_txt == "edit") {
                System.out.println("edit");
                complete_checkbox.setEnabled(false);
                long[] sharing_list = Task.find_target_task(tasks, target_task_id).shared_users;
                for (int i = 0; i < sharing_list.length; i++) {
                    if (sharing_list[i] == user.get_id()) {
                        editable = false;
                        return;
                    }
                }

                editable = true;
                reload_home_page();
            } else if (this.label_txt == "save") {
                put_task();
                editable = false;
                complete_checkbox.setEnabled(true);
                if (Task.find_target_task(tasks, target_task_id)._completed == true
                        && Task.find_target_task(tasks, target_task_id)._archived_on_completion == false) {
                    target_task_id = -1;
                }
                reload_home_page();
            } else {
                System.out.println("undefined" + " label=" + this.label_txt);
            }
        }
    }

    class Task_ButtonAction implements ActionListener {
        private Task task;

        Task_ButtonAction(Task task) {
            this.task = task;
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println("task_button clicked" + " task_id=" + task.id);
            target_task_id = task.id;
            reload_home_page();
        }
    }

    private Component create_leftSide_component() {
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
            task_button.addActionListener(new Task_ButtonAction(tasks[i]));
            if (tasks[i]._completed) {
                task_button.setForeground(Color.LIGHT_GRAY);
                System.out.println("completed");
            } else if (tasks[i].shared_users.length > 0) {
                task_button.setForeground(Color.GREEN);
                System.out.println("shared");
            } else if (tasks[i].due_date.before(new Date())) {
                task_button.setForeground(Color.RED);
                System.out.println("deadline miss");
            } else {
                task_button.setForeground(Color.BLACK);
                System.out.println("normal");
            }
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
        // JLabel nickname_label = new JLabel(user.nickname);
        JLabel nickname_label = new JLabel("");
        nickname_label.setHorizontalAlignment(JLabel.CENTER);
        JPanel left_bottom_panel = new JPanel();
        left_bottom_panel.setPreferredSize(new Dimension(main_width / 3, main_height / 16));
        left_bottom_panel.setLayout(new GridLayout(1, 2));
        left_bottom_panel.setBorder(blackBorder);
        left_bottom_panel.add(logout_button);
        left_bottom_panel.add(nickname_label);
        left_bottom_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        left_bottom_frame.getContentPane().add(left_bottom_panel, BorderLayout.CENTER);

        JPanel info_panel = new JPanel();
        info_panel.setLayout(new GridLayout(1, 2));
        info_panel.setBorder(blackBorder);
        info_panel.setPreferredSize(new Dimension(main_width / 3, main_height / 16));
        JLabel id_label = new JLabel("id=" + user.get_id());
        id_label.setHorizontalAlignment(JLabel.CENTER);
        JLabel name_label = new JLabel(user.nickname);
        id_label.setHorizontalAlignment(JLabel.CENTER);

        info_panel.add(id_label);
        info_panel.add(name_label);
        info_panel.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        left_upper_frame.getContentPane().add(info_panel, BorderLayout.NORTH);
        left_upper_frame.getContentPane().add(addTask_panel, BorderLayout.SOUTH);
        left_frame.getContentPane().add(left_upper_frame.getContentPane(), BorderLayout.NORTH);
        left_frame.getContentPane().add(left_middle_frame.getContentPane(), BorderLayout.CENTER);
        left_frame.getContentPane().add(left_bottom_frame.getContentPane(), BorderLayout.SOUTH);

        return left_frame.getContentPane();
    }

    private Component create_rightSide_component() {
        Task task = Task.find_target_task(tasks, target_task_id);
        int main_height = frame.getHeight();
        int main_width = frame.getWidth();
        JFrame right_frame = new JFrame();
        right_frame.setPreferredSize(new Dimension(main_width / 3 * 2, main_height));

        Component taskDetail_panel = create_taskDetail_component();
        JScrollPane taskDetail_scroll = new JScrollPane(taskDetail_panel); // スクロール可能にする
        taskDetail_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        taskDetail_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        taskDetail_scroll
                .setPreferredSize(new Dimension(main_width / 3 * 2, main_height));
        taskDetail_scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        right_frame.add(taskDetail_scroll);
        return right_frame.getContentPane();
    }

    private Component create_taskDetail_component() {
        Task task = Task.find_target_task(tasks, target_task_id);
        int taskDetail_panel_height = frame.getHeight();
        int taskDetail_panel_width = frame.getWidth() / 3 * 2 - scrollBar_width;
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        JPanel return_panel = new JPanel();
        return_panel
                .setPreferredSize(new Dimension(taskDetail_panel_width, taskDetail_panel_height));
        return_panel.setLayout(new GridLayout(9, 1));

        return_panel.add(create_taskDetail_component_top());
        return_panel.add(create_taskDetail_component_labelRow("Body: ", task.body));
        return_panel.add(create_taskDetail_component_labelRow("Due: ", DateFormatter.format(task.due_date)));
        return_panel.add(create_taskDetail_component_labelRow("Priority: ", Integer.toString(task.priority)));
        String user_id_csv = User.get_user_id_csv(task.shared_users);
        return_panel.add(create_taskDetail_component_labelRow("Users: ", user_id_csv));
        return_panel.add(create_taskDetail_component_labelRow("Created at: ", DateFormatter.format(task.created_at)));
        return_panel.add(create_taskDetail_component_labelRow("Updated at: ", DateFormatter.format(task.updated_at)));
        return_panel.add(create_taskDetail_component_labelRow("Archive on complete: ",
                Boolean.toString(task._archived_on_completion)));
        return_panel.add(create_taskDetail_component_labelRow("Id: ", Long.toString(task.id)));

        JPanel taskDetail_body_panel = new JPanel();
        taskDetail_body_panel.setBorder(blackBorder);

        return_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return return_panel;
    }

    private Component create_taskDetail_component_top() {
        Task task = Task.find_target_task(tasks, target_task_id);
        String title = task.title;
        JFrame return_frame = new JFrame();
        // int taskDetail_panel_height = frame.getHeight();
        int taskDetail_panel_width = frame.getWidth() / 3 * 2 - scrollBar_width;
        int width_ratio = 5;

        JPanel action_panel = new JPanel(new GridLayout(2, 1));
        action_panel.setPreferredSize(new Dimension(taskDetail_panel_width / width_ratio, 50));
        action_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JPanel checkbox_panel = new JPanel(new GridLayout(1, 3));
        checkbox_panel.setPreferredSize(new Dimension(taskDetail_panel_width / width_ratio, 50));
        complete_checkbox = new JCheckBox("");
        complete_checkbox.setSelected(task._completed); // チェックボックスの初期状態を設定。trueでチェックあり、falseでチェックなし
        complete_checkbox.addActionListener(e -> {
            boolean selected = complete_checkbox.isSelected();
            int task_index = Task.find_target_task_indexNum(tasks, target_task_id);
            tasks[task_index]._completed = selected;
            if (selected) {
                if (Task.find_target_task(tasks, target_task_id)._completed == true
                        && Task.find_target_task(tasks, target_task_id)._archived_on_completion == false) {
                    target_task_id = -1;
                }
            }
            editable = true;
            reload_home_page(tasks);
            put_task();
            editable = false;
            reload_home_page();
            System.out.println("selected: " + selected);
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
            taskDetail_title_label = new JTextField(title);
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
            if (title == "Body: ") {
                taskDetail_body_label = new JTextField(content);
                taskDetail_body_label.setHorizontalAlignment(JTextField.CENTER);
                return_panel.add(taskDetail_body_label);
            } else if (title == "Due: ") {
                taskDetail_due_label = new JTextField(content);
                taskDetail_due_label.setHorizontalAlignment(JTextField.CENTER);
                return_panel.add(taskDetail_due_label);
            } else if (title == "Priority: ") {
                taskDetail_priority_label = new JTextField(content);
                taskDetail_priority_label.setHorizontalAlignment(JTextField.CENTER);
                return_panel.add(taskDetail_priority_label);
            } else if (title == "Users: ") {
                taskDetail_users_label = new JTextField(content);
                taskDetail_users_label.setHorizontalAlignment(JTextField.CENTER);
                return_panel.add(taskDetail_users_label);
            } else if (title == "Archive on complete: ") {
                JPanel center_checkbox_panel = new JPanel();
                center_checkbox_panel.setLayout(new GridLayout(1, 3));
                taskDetail_archivedoncompletion_checkbox = new JCheckBox();
                taskDetail_archivedoncompletion_checkbox.setSelected(Boolean.parseBoolean(content));
                center_checkbox_panel.add(Box.createVerticalGlue());
                center_checkbox_panel.add(taskDetail_archivedoncompletion_checkbox);
                center_checkbox_panel.add(Box.createVerticalGlue());
                return_panel.add(center_checkbox_panel);
            } else {
                JLabel taskDetail_content_label = new JLabel(content);
                taskDetail_content_label.setHorizontalAlignment(JLabel.CENTER);
                return_panel.add(taskDetail_content_label);
            }
        } else

        {
            JLabel taskDetail_content_label = new JLabel(content);
            taskDetail_content_label.setHorizontalAlignment(JLabel.CENTER);
            return_panel.add(taskDetail_content_label);
        }
        return_panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return return_panel;
    }

    public Component create_main_component(Task[] tasks, long target_task_id) {
        this.tasks = tasks;
        this.target_task_id = target_task_id;
        int main_height = frame.getHeight();
        int main_width = frame.getWidth();
        JFrame new_frame = new JFrame();
        new_frame.setPreferredSize(new Dimension(main_width, main_height));
        new_frame.setLayout(new BorderLayout());
        new_frame.getContentPane().add(create_leftSide_component(), BorderLayout.WEST);
        new_frame.getContentPane().add(create_rightSide_component(), BorderLayout.EAST);
        return new_frame.getContentPane();
    }
}
