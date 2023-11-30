package client.interfaces.response;

import json.Json;
import protocol.response.ErrorResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorInterfaceResponse extends JDialog {
    private JPanel errorResponsePanel;
    private JButton OKButton;
    private JLabel code;
    private JLabel mensagem;

    public ErrorInterfaceResponse(JFrame parent, String json) {
        super(parent);
        setTitle("Error Response");
        setContentPane(errorResponsePanel);
        setMinimumSize(new Dimension(400, 200));
        setModal(true);
        setLocationRelativeTo(parent);
        code.setText(String.valueOf(Json.fromJson(json, ErrorResponse.class).payload().getCode()));
        mensagem.setText(Json.fromJson(json, ErrorResponse.class).payload().getMensagem());
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
