package client.interfaces.response;

import json.Json;
import protocol.response.LogoutResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutInterfaceResponse extends JDialog {
    private JPanel logout;
    private JButton OKButton;
    private JLabel mensagem;

    public LogoutInterfaceResponse(JFrame parent, String json) {
        super(parent);
        setTitle("Logout Response");
        setContentPane(logout);
        setMinimumSize(new Dimension(250, 100));
        setModal(true);
        setLocationRelativeTo(parent);
        mensagem.setText(Json.fromJson(json, LogoutResponse.class).payload().getMensagem());
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
