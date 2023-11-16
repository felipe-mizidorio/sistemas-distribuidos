package client;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Operation extends JDialog{
    private JPanel panel1;
    private JTextField textField1;
    private JButton button1;

    @Getter
    private String text;

    public Operation(JFrame parent) {
        super(parent);
        setTitle("Operation");
        setContentPane(panel1);
        setMinimumSize(new Dimension(300, 150));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text = textField1.getText();
                dispose();
            }
        });
        setVisible(true);
    }
}
