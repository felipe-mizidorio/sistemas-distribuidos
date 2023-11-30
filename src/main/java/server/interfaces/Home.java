package server.interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Home extends JDialog {
    private JPanel serverHomePanel;
    private JButton updateButton;
    private JLabel portaField;
    public JLabel users;

    public Home(JFrame parent, int porta, List<String> usuarios) {
        super(parent);
        setTitle("Server HomePage");
        setContentPane(serverHomePanel);
        setMinimumSize(new Dimension(300, 150));
        setModal(true);
        setLocationRelativeTo(parent);
        portaField.setText(String.valueOf(porta));
        update(usuarios);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        setVisible(true);
    }

    public void update(List<String> usuarios) {
        StringBuilder result = new StringBuilder();
        result.append("<html>");
        for (String element : usuarios) {
            result.append("IP: ").append(element).append("<br>");
        }
        result.append("</html>");
        System.out.println(result);
        users.setText(result.toString());
    }
}
