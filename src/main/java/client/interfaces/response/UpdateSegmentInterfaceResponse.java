package client.interfaces.response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateSegmentInterfaceResponse extends JDialog {
    private JPanel updateSegmentResponse;
    private JButton OKButton;

    public UpdateSegmentInterfaceResponse(JFrame parent) {
        super(parent);
        setTitle("Update Segment Response");
        setContentPane(updateSegmentResponse);
        setMinimumSize(new Dimension(320, 100));
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
