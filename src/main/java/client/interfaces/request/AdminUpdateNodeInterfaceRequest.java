package client.interfaces.request;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUpdateNodeInterfaceRequest extends JDialog {
    private JPanel updateNodeRequest;
    private JTextField nomeField;
    private JTextField xField;
    private JTextField yField;
    private JTextField avisoField;
    private JTextField acessivelField;
    private JButton OKButton;
    private JTextField idField;

    @Getter
    private Long id;

    @Getter
    private String nome;

    @Getter
    private Integer coordenadaX;

    @Getter
    private Integer coordenadaY;

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
                id = Long.parseLong(verify(idField.getText()));
                nome = verify(nomeField.getText());
                coordenadaX = Integer.parseInt(verify(xField.getText()));
                coordenadaY = Integer.parseInt(verify(yField.getText()));
                aviso = avisoField.getText();
                acessivel = Boolean.valueOf(verify(acessivelField.getText()));
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
