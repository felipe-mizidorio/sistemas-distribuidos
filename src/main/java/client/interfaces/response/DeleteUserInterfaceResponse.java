package client.interfaces.response;

import json.Json;
import protocol.response.DeleteUserResponse;
import protocol.response.ErrorResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteUserInterfaceResponse extends JDialog{
    private JPanel deleteUserResponse;
    private JLabel mensagem;
    private JButton OKButton;

    public DeleteUserInterfaceResponse(JFrame parent, String json) {
        super(parent);
        setTitle("Delete User Response");
        setContentPane(deleteUserResponse);
        setMinimumSize(new Dimension(
                Json.fromJson(json, DeleteUserResponse.class).payload().getMensagem().length()*10, 100
                )
        );
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mensagem.setText(Json.fromJson(json, DeleteUserResponse.class).payload().getMensagem());
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
