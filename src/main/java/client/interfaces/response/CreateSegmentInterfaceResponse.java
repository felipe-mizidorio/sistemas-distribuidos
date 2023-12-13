package client.interfaces.response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateSegmentInterfaceResponse extends JDialog {
    private JPanel createSegmentResponse;
    private JButton OKButton;

    public CreateSegmentInterfaceResponse(JFrame parent) {
        super(parent);
        setTitle("Create Segment Response");
        setContentPane(createSegmentResponse);
        setMinimumSize(new Dimension(280, 100));
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
