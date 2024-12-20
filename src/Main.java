import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Main{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new form1().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setPreferredSize(new Dimension(600, 600));
        frame.pack();
        frame.setVisible(true);
    }
}

