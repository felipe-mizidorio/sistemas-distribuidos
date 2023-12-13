package client.interfaces.request;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminCreateNodeInterfaceRequest extends JDialog {
    private JPanel createNodeRequest;
    private JTextField nomeField;
    private JTextField xField;
    private JTextField yField;
    private JTextField avisoField;
    private JButton OKButton;
    private JRadioButton trueRadioButtonAcessivel;
    private JRadioButton falseRadioButtonAcessivel;

    @Getter
    private String nome;

    @Getter
    private Double coordenadaX;

    @Getter
    private Double coordenadaY;

    @Getter
    private String aviso;

    @Getter
    private Boolean acessivel;

    public AdminCreateNodeInterfaceRequest(JFrame parent) {
        super(parent);
        setTitle("Admin Create Node Request");
        setContentPane(createNodeRequest);
        setMinimumSize(new Dimension(400, 275));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nome = verify(nomeField.getText());
                if(verify(xField.getText()) == null) {
                    coordenadaX = null;
                } else {
                    coordenadaX = Double.parseDouble(xField.getText());
                }
                if(verify(yField.getText()) == null) {
                    coordenadaY = null;
                } else {
                    coordenadaY = Double.parseDouble(yField.getText());
                }
                aviso = avisoField.getText();
                acessivel = trueRadioButtonAcessivel.isSelected();
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
