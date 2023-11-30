package client.interfaces.response;

import json.Json;
import protocol.response.routes.FindSegmentsResponse;
import server.datatransferobject.segment.SegmentDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FindSegmentsInterfaceResponse extends JDialog {
    private JPanel findSegmentsResponse;
    private JButton OKButton;
    private JButton leftArrow;
    private JButton rightArrow;
    private JLabel pdiInicialLabel;
    private JLabel pdiFinalLabel;
    private JLabel distanciaLabel;
    private JLabel avisoLabel;
    private JLabel acessivelLabel;

    int i = 0;

    public FindSegmentsInterfaceResponse(JFrame parent, String json) {
        super(parent);
        setTitle("Admin Find Segments Response");
        setContentPane(findSegmentsResponse);
        setMinimumSize(new Dimension(375, 275));
        setModal(true);
        setLocationRelativeTo(parent);
        List<SegmentDTO> jsonResponse = Json.fromJson(json, FindSegmentsResponse.class).payload().getSegmentos();
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

    public void updateValues(SegmentDTO segment) {
        pdiInicialLabel.setText(String.valueOf(segment.getPdiInicial()));
        pdiFinalLabel.setText(String.valueOf(segment.getPdiFinal()));
        distanciaLabel.setText(String.valueOf(segment.getDistancia()));
        avisoLabel.setText(segment.getAviso());
        acessivelLabel.setText(String.valueOf(segment.getAcessivel()));
    }
}
