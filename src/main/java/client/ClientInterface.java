package client;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

public class ClientInterface extends JDialog {
    private JPanel panel1;
    private JButton acceptButton;
    private JTextField ipField;
    private JTextField portaField;

    @Getter
    private String ip;
    @Getter
    private String porta;

    public ClientInterface(JFrame parent) {
        super(parent);
        setTitle("Start Server connection");
        setContentPane(panel1);
        setMinimumSize(new Dimension(300, 150));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ip = ipField.getText();
                porta = portaField.getText();
                dispose();
            }
        });
        setVisible(true);
    }
}
