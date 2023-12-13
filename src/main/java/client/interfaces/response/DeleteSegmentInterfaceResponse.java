package client.interfaces.response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteSegmentInterfaceResponse extends JDialog {
    private JPanel deleteSegmentResponse;
    private JButton OKButton;

    public DeleteSegmentInterfaceResponse(JFrame parent) {
        super(parent);
        setTitle("Delete Segment Response");
        setContentPane(deleteSegmentResponse);
        setMinimumSize(new Dimension(300, 100));
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
