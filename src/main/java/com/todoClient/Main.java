package com.todoClient;

public class Main {
  public static void main(String[] args) {
    // Color color = new Color(0, 0, 0);
    // Gson gson = new Gson();
    // String a = gson.toJson(color);
    // Color c = Json.parse(Color.class, a);
    // a = Json.stringify(c);
    // System.out.println(a);
    // gson = gson.toJson("{\"r\":1,\"g\":20,\"b\":30}");
    // Create a new Gson instance
    // Gson gson = new Gson();

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

    Window w = new Window();
    w.init();
    w.login();
  }
}
