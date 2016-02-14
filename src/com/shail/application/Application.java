package com.shail.application;

import javax.swing.SwingUtilities;

import com.shail.controller.Controller;
import com.shail.model.Model;
import com.shail.view.View;

public class Application {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        runApp();
      }

    });
  }

  public static void runApp() {
    Model model = new Model();
    View view = new View();

    Controller controller = new Controller(view, model);

    view.setLoginListener(controller);
  }

}
