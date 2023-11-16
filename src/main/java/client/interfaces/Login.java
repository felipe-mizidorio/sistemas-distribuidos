package client.interfaces;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JDialog {
    private JPanel loginPanel;
    private JTextField emailField;
    private JTextField senhaField;
    private JButton OKButton;

    @Getter
    private String email;
    @Getter
    private String senha;

    public Login(JFrame parent) {
        super(parent);
        setTitle("Operation");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(500, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                email = emailField.getText();
                senha = senhaField.getText();
                dispose();
            }
        });
        setVisible(true);
    }
}
