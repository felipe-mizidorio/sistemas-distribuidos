package client.interfaces.response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateUserInterfaceResponse extends JDialog {
    private JPanel updateUser;
    private JButton OKButton;

    public UpdateUserInterfaceResponse(JFrame parent) {
        super(parent);
        setTitle("Update User Response");
        setContentPane(updateUser);
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
