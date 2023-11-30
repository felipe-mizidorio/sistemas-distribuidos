package client.interfaces.request;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUserInterfaceRequest extends JDialog {
    private JPanel createUserRequest;
    private JTextField senhaField;
    private JTextField emailField;
    private JTextField nomeField;
    private JButton OKButton;

    @Getter
    private String nome;
    @Getter
    private String email;
    @Getter
    private String senha;

    public CreateUserInterfaceRequest(JFrame parent) {
        super(parent);
        setContentPane(createUserRequest);
        setTitle("Create User Request");
        setMinimumSize(new Dimension(400, 200));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nome = verify(nomeField.getText());
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
