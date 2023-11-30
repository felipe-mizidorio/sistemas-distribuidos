package client.interfaces.response;

import json.Json;
import protocol.response.routes.FindNodesResponse;
import server.datatransferobject.node.NodeDTO;
import server.datatransferobject.user.UserDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FindNodesInterfaceResponse extends JDialog {
    private JPanel findNodesResponse;
    private JLabel idLabel;
    private JLabel nomeLabel;
    private JLabel xLabel;
    private JLabel yLabel;
    private JLabel avisoLabel;
    private JLabel acessivelLabel;
    private JButton OKButton;
    private JButton leftArrow;
    private JButton rightArrow;

    int i = 0;

    public FindNodesInterfaceResponse(JFrame parent, String json) {
        super(parent);
        setTitle("Admin Find Nodes Response");
        setContentPane(findNodesResponse);
        setMinimumSize(new Dimension(375, 275));
        setModal(true);
        setLocationRelativeTo(parent);
        List<NodeDTO> jsonResponse = Json.fromJson(json, FindNodesResponse.class).payload().getPdis();
        if(!jsonResponse.isEmpty()) {
            updateValues(jsonResponse.get(0));
        }
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
        setVisible(true);
    }

    public void updateValues(NodeDTO node) {
        idLabel.setText(String.valueOf(node.getId()));
        nomeLabel.setText(node.getNome());
        xLabel.setText(String.valueOf(node.getPosicao().x()));
        yLabel.setText(String.valueOf(node.getPosicao().y()));
        avisoLabel.setText(node.getAviso());
        acessivelLabel.setText(String.valueOf(node.getAcessivel()));
    }
}
