package com.todoClient;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.google.gson.Gson;

// A class to represent an example object with two properties
class MyObject {
  String name;
  int age;

  MyObject(String name, int age) {
    this.name = name;
    this.age = age;
  }
}

public class Main {
  public static void main(String[] args) {
    // Create a new Gson instance
    Gson gson = new Gson();

    // Convert a JSON string to an object
    String json = "{\"name\":\"John\",\"age\":30}";
    MyObject obj = gson.fromJson(json, MyObject.class);

    // Display the object's properties
    System.out.println("Name: " + obj.name);
    System.out.println("Age: " + obj.age);

    // Now let's create a GUI that displays these properties
    SwingUtilities.invokeLater(
        () -> {
          JFrame frame = new JFrame("Hello, Swing!");
          frame.setSize(300, 200);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.add(new JLabel("Name: " + obj.name + ", Age: " + obj.age));
          frame.setVisible(true);
        });
  }
}
