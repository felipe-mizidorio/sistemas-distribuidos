package client.interfaces.response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateNodeInterfaceResponse extends JDialog {
    private JPanel updateNodeResponse;
    private JButton OKButton;

    public UpdateNodeInterfaceResponse(JFrame parent) {
        super(parent);
        setTitle("Update Node Response");
        setContentPane(updateNodeResponse);
        setMinimumSize(new Dimension(420, 100));
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
