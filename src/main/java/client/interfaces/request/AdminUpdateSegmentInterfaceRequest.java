package client.interfaces.request;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUpdateSegmentInterfaceRequest extends JDialog {
    private JPanel updateSegmentRequest;
    private JTextField pdiInicialField;
    private JTextField pdiFinalField;
    private JTextField avisoField;
    private JButton OKButton;
    private JRadioButton trueRadioButton;
    private JRadioButton falseRadioButton;

    @Getter
    private Long pdiInicial;

    @Getter
    private Long pdiFinal;

    @Getter
    private String aviso;

    @Getter
    private Boolean acessivel;

    public AdminUpdateSegmentInterfaceRequest(JFrame parent) {
        super(parent);
        setTitle("Admin Update Segment Request");
        setContentPane(updateSegmentRequest);
        setMinimumSize(new Dimension(400, 200));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(verify(pdiInicialField.getText()) == null) {
                    pdiInicial = null;
                } else {
                    pdiInicial = Long.parseLong(pdiInicialField.getText());
                }
                if(verify(pdiFinalField.getText()) == null) {
                    pdiFinal = null;
                } else {
                    pdiFinal = Long.parseLong(pdiFinalField.getText());
                }
                aviso = avisoField.getText();
                acessivel = trueRadioButton.isSelected();
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
