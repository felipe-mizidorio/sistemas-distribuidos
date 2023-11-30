package client.interfaces.response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteNodeInterfaceResponse extends JDialog {
    private JPanel deleteNodeResponse;
    private JButton OKButton;

    public DeleteNodeInterfaceResponse(JFrame parent) {
        super(parent);
        setTitle("Delete Node Response");
        setContentPane(deleteNodeResponse);
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
