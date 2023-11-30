package client.interfaces.request;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUpdateUserInterfaceRequest extends JDialog {

    private JPanel adminUpdateUserRequest;
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField senhaField;
    private JTextField tipoField;
    private JButton OKButton;
    private JTextField registroField;

    @Getter
    private Long registro;
    @Getter
    private String nome;
    @Getter
    private String email;
    @Getter
    private String senha;
    @Getter
    private Boolean tipo;

    public AdminUpdateUserInterfaceRequest(JFrame parent) {
        super(parent);
        setTitle("Admin Update User Request");
        setContentPane(adminUpdateUserRequest);
        setMinimumSize(new Dimension(400, 275));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(verify(registroField.getText()) == null) {
                    registro = null;
                } else {
                    registro = Long.parseLong(registroField.getText());
                }
                nome = verify(nomeField.getText());
                email = verify(emailField.getText());
                senha = verify(senhaField.getText());
                if(verify(tipoField.getText()) == null) {
                    tipo = null;
                } else {
                    tipo = Boolean.parseBoolean(tipoField.getText());
                }
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
