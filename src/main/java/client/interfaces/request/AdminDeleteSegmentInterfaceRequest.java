package client.interfaces.request;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDeleteSegmentInterfaceRequest extends JDialog {
    private JPanel deleteSegmentRequest;
    private JTextField pdiInicialField;
    private JTextField pdiFinalField;
    private JButton OKButton;

    @Getter
    private Long pdiInicial;

    @Getter
    private Long pdiFinal;

    public AdminDeleteSegmentInterfaceRequest(JFrame parent) {
        super(parent);
        setTitle("Admin Delete Segment Request");
        setContentPane(deleteSegmentRequest);
        setMinimumSize(new Dimension(400, 200));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pdiInicial = Long.parseLong(verify(pdiInicialField.getText()));
                pdiFinal = Long.parseLong(verify(pdiFinalField.getText()));
                dispose();
            }
        });
        setVisible(true);
    }

    private String verify(String field) {
        if(field.isBlank() || field.isEmpty()) {
            field = null;
        }
        return field;
    }
}
