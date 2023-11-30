package client.interfaces.request;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDeleteNodeInterfaceRequest extends JDialog{
    private JPanel deleteNodeRequest;
    private JTextField idField;
    private JButton OKButton;

    @Getter
    private Long id;

    public AdminDeleteNodeInterfaceRequest(JFrame parent) {
        super(parent);
        setTitle("Admin Delete Node Request");
        setContentPane(deleteNodeRequest);
        setMinimumSize(new Dimension(400, 200));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = Long.parseLong(verify(idField.getText()));
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
