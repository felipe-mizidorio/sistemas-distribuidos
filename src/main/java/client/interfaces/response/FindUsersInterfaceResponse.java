package client.interfaces.response;

import json.Json;
import protocol.response.FindUsersResponse;
import server.datatransferobject.user.UserDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FindUsersInterfaceResponse extends JDialog {
    private JPanel findUsersResponse;
    private JButton OKButton;
    private JLabel registroField;
    private JLabel nomeField;
    private JLabel emailField;
    private JLabel tipoField;
    private JButton rightArrow;
    private JButton leftArrow;

    int i = 0;

    public FindUsersInterfaceResponse(JFrame parent, String json) {
        super(parent);
        setTitle("Admin Find Nodes Response");
        setContentPane(findUsersResponse);
        setMinimumSize(new Dimension(375, 275));
        setModal(true);
        setLocationRelativeTo(parent);
        List<UserDTO> jsonResponse = Json.fromJson(json, FindUsersResponse.class).payload().getUsuarios();
        updateValues(jsonResponse.get(0));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        rightArrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i++;
                if(i >= jsonResponse.size()) {
                    i = 0;
                }
                updateValues(jsonResponse.get(i));
            }
        });
        leftArrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i--;
                if(i < 0) {
                    i = jsonResponse.size() - 1;
                }
                updateValues(jsonResponse.get(i));
            }
        });
        setVisible(true);
    }

    public void updateValues(UserDTO user) {
        registroField.setText(String.valueOf(user.getRegistro()));
        nomeField.setText(String.valueOf(user.getNome()));
        emailField.setText(String.valueOf(user.getEmail()));
        tipoField.setText(String.valueOf(user.getTipo()));
    }
}
