package client.interfaces.request;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDeleteUserInterfaceRequest extends JDialog {

    private JPanel adminDeleteUserRequest;
    private JTextField registroField;
    private JButton OKButton;

    @Getter
    private Long registro;

    public AdminDeleteUserInterfaceRequest(JFrame parent) {
        super(parent);
        setTitle("Admin Delete User Request");
        setContentPane(adminDeleteUserRequest);
        setMinimumSize(new Dimension(400, 200));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registro = Long.parseLong(verify(registroField.getText()));
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
