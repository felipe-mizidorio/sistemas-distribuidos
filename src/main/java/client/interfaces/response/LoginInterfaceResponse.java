package client.interfaces.response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LoginInterfaceResponse extends JDialog {
    private JPanel loginResponse;
    private JButton OKButton;

    public LoginInterfaceResponse(JFrame parent) {
        super(parent);
        setTitle("Login Response");
        setContentPane(loginResponse);
        setMinimumSize(new Dimension(270, 100));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
