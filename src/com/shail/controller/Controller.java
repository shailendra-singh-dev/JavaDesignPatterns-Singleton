package com.shail.controller;

import javax.security.auth.spi.LoginModule;

import com.shail.model.Model;
import com.shail.view.LoginFormEvent;
import com.shail.view.View;
import com.shail.view.View.LoginListener;

public class Controller implements  LoginListener {
  private View view;
  private Model model;

  public Controller(View view, Model model) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void loginPerformed(LoginFormEvent event) {
    System.out.println("Login event received: " + event.getName() + "; " + event.getPassword());

  }


}
