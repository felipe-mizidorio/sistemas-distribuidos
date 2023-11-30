package server.interfaces;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitInterface extends JDialog {

    private JPanel serverInitPanel;
    private JTextField portaField;
    private JButton OKButton;

    @Getter
    private String porta;

    public InitInterface(JFrame parent) {
        super(parent);
        setTitle("Start Server connection");
        setContentPane(serverInitPanel);
        setMinimumSize(new Dimension(300, 150));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                porta = portaField.getText();
                dispose();
            }
        });
        setVisible(true);
    }
}
