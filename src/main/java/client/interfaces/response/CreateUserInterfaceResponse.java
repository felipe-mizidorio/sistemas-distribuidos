package client.interfaces.response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUserInterfaceResponse extends JDialog {
    private JPanel createUserResponse;
    private JButton OKButton;

    public CreateUserInterfaceResponse(JFrame parent) {
        super(parent);
        setTitle("Create User Response");
        setContentPane(createUserResponse);
        setMinimumSize(new Dimension(250, 100));
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
