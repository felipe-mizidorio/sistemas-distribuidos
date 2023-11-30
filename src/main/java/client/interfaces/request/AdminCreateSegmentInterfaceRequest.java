package client.interfaces.request;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminCreateSegmentInterfaceRequest extends JDialog {
    private JPanel createSegmentRequest;
    private JTextField pdiInicialField;
    private JTextField pdiFinalField;
    private JTextField avisoField;
    private JTextField acessivelField;
    private JButton OKButton;

    @Getter
    private Long pdiInicial;

    @Getter
    private Long pdiFinal;

    @Getter
    private String aviso;

    @Getter
    private Boolean acessivel;

    public AdminCreateSegmentInterfaceRequest(JFrame parent) {
        super(parent);
        setTitle("Admin Create Segment Request");
        setContentPane(createSegmentRequest);
        setMinimumSize(new Dimension(400, 200));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pdiInicial = Long.parseLong(verify(pdiInicialField.getText()));
                pdiFinal = Long.parseLong(verify(pdiFinalField.getText()));
                aviso = verify(avisoField.getText());
                acessivel = Boolean.parseBoolean(verify(acessivelField.getText()));
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
