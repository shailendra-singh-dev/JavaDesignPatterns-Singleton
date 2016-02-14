package com.shail.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.shail.model.DatabaseManager;
import com.shail.model.Model;

public class View extends JFrame implements ActionListener {

  private JButton okButton;
  private JTextField nameField;
  private JPasswordField passField;

  private LoginListener loginListener;

  public interface LoginListener {
    public void loginPerformed(LoginFormEvent event);
  }

  public View() {
    super("MVC Demo");

    nameField = new JTextField(10);
    passField = new JPasswordField(10);
    okButton = new JButton("OK");

    setLayout(new GridBagLayout());

    GridBagConstraints gc = new GridBagConstraints();
    gc.anchor = GridBagConstraints.LAST_LINE_END;
    gc.gridx = 1;
    gc.gridy = 1;
    gc.weightx = 1;
    gc.weighty = 1;
    gc.insets = new Insets(100, 0, 0, 10);
    gc.fill = GridBagConstraints.NONE;

    add(new JLabel("Name: "), gc);

    gc.anchor = GridBagConstraints.LAST_LINE_START;
    gc.gridx = 2;
    gc.gridy = 1;
    gc.weightx = 1;
    gc.weighty = 1;
    gc.insets = new Insets(100, 0, 0, 0);
    gc.fill = GridBagConstraints.NONE;

    add(nameField, gc);

    gc.anchor = GridBagConstraints.LINE_END;
    gc.gridx = 1;
    gc.gridy = 2;
    gc.weightx = 1;
    gc.weighty = 1;
    gc.insets = new Insets(0, 0, 0, 10);
    gc.fill = GridBagConstraints.NONE;

    add(new JLabel("Password: "), gc);

    gc.anchor = GridBagConstraints.LINE_START;
    gc.gridx = 2;
    gc.gridy = 2;
    gc.weightx = 1;
    gc.weighty = 1;
    gc.insets = new Insets(0, 0, 0, 0);
    gc.fill = GridBagConstraints.NONE;

    add(passField, gc);

    gc.anchor = GridBagConstraints.FIRST_LINE_START;
    gc.gridx = 2;
    gc.gridy = 3;
    gc.weightx = 1;
    gc.weighty = 100;
    gc.fill = GridBagConstraints.NONE;

    add(okButton, gc);

    okButton.addActionListener(this);

    setSize(600, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowOpened(WindowEvent e) {
        super.windowOpened(e);
        DatabaseManager.getDatabaseManager().establishConnection();
      }

      @Override
      public void windowClosing(WindowEvent e) {
        super.windowClosed(e);
        DatabaseManager.getDatabaseManager().closeConnection();
      }

    });
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    String password = new String(passField.getPassword());
    String name = nameField.getText();

    fireLoginEvent(new LoginFormEvent(name, password));
  }

  public void setLoginListener(LoginListener loginListener) {
    this.loginListener = loginListener;
  }

  public void fireLoginEvent(LoginFormEvent event) {
    if (loginListener != null) {
      loginListener.loginPerformed(event);
    }
  }

}
