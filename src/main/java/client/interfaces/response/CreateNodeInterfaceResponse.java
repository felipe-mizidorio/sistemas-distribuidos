package client.interfaces.response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNodeInterfaceResponse extends JDialog {
    private JPanel createNodeResponse;
    private JButton OKButton;

    public CreateNodeInterfaceResponse(JFrame parent) {
        super(parent);
        setTitle("Create Node Response");
        setContentPane(createNodeResponse);
        setMinimumSize(new Dimension(380, 100));
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
