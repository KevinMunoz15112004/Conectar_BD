import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class form1 {
    private JTextField loginTXT;
    private JPasswordField passwordField1;
    private JButton OKButton;
    public JPanel mainPanel;

    // Variable para almacenar la referencia a la ventana
    private JFrame frame;

    public form1() {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/test";
                String username = "root";
                String password = "123456";

                // Verificar si la ventana ya está abierta
                if (frame == null || !frame.isVisible()) {
                    // Crear y mostrar la ventana solo si no está abierta
                    frame = new JFrame("Login");
                    frame.setContentPane(new form1().mainPanel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(600, 600);
                    frame.setPreferredSize(new Dimension(600, 600));
                    frame.pack();
                    frame.setVisible(true);
                }

                try {
                    String parametro = loginTXT.getText();  // Obtienes el parámetro de texto
                    Connection con = DriverManager.getConnection(url, username, password);
                    System.out.println("Se ha conectado con la base de datos");

                    // Usamos PreparedStatement para evitar la inyección SQL
                    String query = "SELECT * FROM usuario WHERE UsuarioID = ?";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, parametro);  // Asignamos el valor de 'parametro' al primer '?'

                    ResultSet rs = pst.executeQuery();  // Ejecutamos la consulta segura

                    // Iteramos a través de los resultados
                    while (rs.next()) {
                        System.out.println(rs.getString("UsuarioID"));
                        System.out.println(rs.getString("Nombre"));
                        System.out.println(rs.getString("Edad"));
                    }
                    con.close();
                } catch (SQLException e1) {
                    throw new RuntimeException(e1);
                }
            }
        });
    }
}
