package client.interfaces.request;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminCreateUserInterfaceRequest extends JDialog {
    private JPanel adminCreateUserRequest;
    private JTextField nomeField;
    private JButton OKButton;
    private JTextField emailField;
    private JTextField senhaField;

    @Getter
    private String nome;
    @Getter
    private String email;
    @Getter
    private String senha;

    public AdminCreateUserInterfaceRequest(JFrame parent) {
        super(parent);
        setTitle("Admin Create User Request");
        setContentPane(adminCreateUserRequest);
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
