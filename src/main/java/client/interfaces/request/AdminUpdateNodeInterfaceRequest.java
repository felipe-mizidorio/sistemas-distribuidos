package client.interfaces.request;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUpdateNodeInterfaceRequest extends JDialog {
    private JPanel updateNodeRequest;
    private JTextField nomeField;
    private JTextField avisoField;
    private JButton OKButton;
    private JTextField idField;
    private JRadioButton trueRadioButton;
    private JRadioButton falseRadioButton;

    @Getter
    private Long id;

    @Getter
    private String nome;

    @Getter
    private String aviso;

    @Getter
    private Boolean acessivel;

    public AdminUpdateNodeInterfaceRequest(JFrame parent) {
        super(parent);
        setTitle("Admin Update Node Request");
        setContentPane(updateNodeRequest);
        setMinimumSize(new Dimension(400, 200));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(verify(idField.getText()) == null) {
                    id = null;
                } else {
                    id = Long.parseLong(idField.getText());
                }
                nome = verify(nomeField.getText());
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
