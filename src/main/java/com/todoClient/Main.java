package com.todoClient;

public class Main {
  public static void main(String[] args) {
    // User user = new User("nickname", "email", "password", "status");
    // User[] user_json = new User[] { user, user };
    // String a = Json.stringify(user_json);
    // System.out.println(a);
    // User[] b = Json.parse(User[].class, a);

    // // Convert a JSON string to an object
    // String json = "{\"name\":\"John\",\"age\":30}";
    // MyObject obj = gson.fromJson(json, MyObject.class);

    // // Display the object's properties
    // System.out.println("Name: " + obj.name);
    // System.out.println("Age: " + obj.age);

    // // Now let's create a GUI that displays these properties
    // SwingUtilities.invokeLater(
    // () -> {
    // JFrame frame = new JFrame("Hello, Swing!");
    // frame.setSize(300, 200);
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.add(new JLabel("Name: " + obj.name + ", Age: " + obj.age));
    // frame.setVisible(true);
    // });
    // }

    // OpenAPI_client c = new OpenAPI_client();
    // System.out.println(c.get("/hello"));

    // String str_date = DateFormatter.format(new Date());
    // System.out.println(str_date);
    // System.out.println(DateFormatter.format(str_date));
    // System.out.println(str_date.length());

    Window w = new Window();
    w.init();
    w.start_page();

    // User user = new User("hello", "email", "password");
    // w.home_page(user);
  }
}
