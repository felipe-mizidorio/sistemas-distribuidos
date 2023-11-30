package client.interfaces.response;

import json.Json;
import protocol.response.FindUserResponse;
import server.datatransferobject.user.UserDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindUserInterfaceResponse extends JDialog{

    private JPanel findUserResponse;
    private JButton OKButton;
    private JLabel registroField;
    private JLabel nomeField;
    private JLabel emailField;
    private JLabel tipoField;

    public FindUserInterfaceResponse(JFrame parent, String json) {
        super(parent);
        setTitle("Find User Response");
        setContentPane(findUserResponse);
        setMinimumSize(new Dimension(250, 100));
        setModal(true);
        setLocationRelativeTo(parent);
        UserDTO jsonResponse = Json.fromJson(json, FindUserResponse.class).payload();
        registroField.setText(String.valueOf(jsonResponse.getRegistro()));
        nomeField.setText(String.valueOf(jsonResponse.getNome()));
        emailField.setText(String.valueOf(jsonResponse.getEmail()));
        tipoField.setText(String.valueOf(jsonResponse.getTipo()));
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
