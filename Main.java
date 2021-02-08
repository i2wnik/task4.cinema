package com.company;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception e) {
            System.out.println(e);
        }

        JFrame frame = new JFrame("Cinema"); //
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout());
        frame.setMinimumSize(new Dimension(1200, 800));
        frame.setBackground(Color.WHITE);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        panel1.setLayout(null);
        panel2.setLayout(null);

        JLabel loginLabel = new JLabel("Login: ");
        JLabel passwordLabel = new JLabel("Password: ");
        loginLabel.setBounds(10, 200, 200, 50);
        loginLabel.setFont(new Font("Candara", Font.BOLD, 20));
        passwordLabel.setBounds(10, 300, 200, 50);
        passwordLabel.setFont(new Font("Candara", Font.BOLD, 20));

        JLabel image = new javax.swing.JLabel();
        image.setIcon(new javax.swing.ImageIcon("C:\\Users\\user\\IdeaProjects\\Cinema\\src\\com\\company\\FILM.KZ.jpg"));
        image.setBounds(305, 195,350, 350);

        JTextField loginText = new JTextField(50);
        loginText.setFont(new Font("Candara", Font.BOLD, 17));
        loginText.setBounds(10, 250, 400, 30);
        JPasswordField passwordText = new JPasswordField(50);
        passwordText.setFont(new Font("Candara", Font.PLAIN, 20));
        passwordText.setBounds(10, 350, 400, 30);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 400, 195, 30);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con = null;
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cinema?useUnicode=true&serverTimezone=UTC", "root", "mazlm44o");
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                Statement statement = null;
                try {
                    statement = con.createStatement();
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                ResultSet result = null;
                try {
                    result = statement.executeQuery("SELECT id_e FROM employees WHERE login = \'" + loginText.getText() + "\'");
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                try {
                    if (!result.next()) {
                        JOptionPane.showMessageDialog(null, "Такой пользователь не найден.");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Такой пользователь есть!");
                    }
                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        JButton registrationButton = new JButton("Registration");
        registrationButton.setBounds(215, 400, 195, 30);
        registrationButton.addActionListener(new regButtonActionListener());

        panel1.add(image);
        panel2.add(loginLabel);
        panel2.add(passwordLabel);
        panel2.add(loginText);
        panel2.add(passwordText);
        panel2.add(loginButton);
        panel2.add(registrationButton);

        frame.add(panel1);
        frame.add(panel2);
        frame.setVisible(true);
    }
}
