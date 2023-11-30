package client.interfaces.request;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInterfaceRequest extends JDialog {
    private JPanel login;
    private JTextField emailField;
    private JTextField senhaField;
    private JButton OKButton;

    @Getter
    private String email;
    @Getter
    private String senha;

    public LoginInterfaceRequest(JFrame parent) {
        super(parent);
        setTitle("Login Request");
        setContentPane(login);
        setMinimumSize(new Dimension(400, 200));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                email = verify(emailField.getText());
                senha = verify(senhaField.getText());
                dispose();
            }
        });
        setVisible(true);
    }

    private String verify(String field) {
        if(field.isBlank() || field.isEmpty()) {
            field = null;
        }
        return field;
    }
}
