package client.interfaces.request;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFindUserInterfaceRequest extends JDialog {

    private JPanel adminFindUserRequest;
    private JTextField registroField;
    private JButton OKButton;

    @Getter
    private Long registro;

    public AdminFindUserInterfaceRequest(JFrame parent) {
        super(parent);
        setTitle("Admin Find User Request");
        setContentPane(adminFindUserRequest);
        setMinimumSize(new Dimension(400, 200));
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
