package client.interfaces.request;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateUserInterfaceRequest extends JDialog {
    private JPanel updateUser;
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField senhaField;
    private JButton OKButton;

    @Getter
    private String nome;
    @Getter
    private String email;
    @Getter
    private String senha;

    public UpdateUserInterfaceRequest(JFrame parent) {
        super(parent);
        setTitle("Update User Request");
        setContentPane(updateUser);
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
