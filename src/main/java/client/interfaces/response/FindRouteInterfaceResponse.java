package client.interfaces.response;

import json.Json;
import protocol.response.routes.FindRouteResponse;
import server.datatransferobject.route.RouteDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class FindRouteInterfaceResponse extends JDialog {
    private JPanel findRouteInterfaceResponse;
    private JTable routesTable;
    private JButton OKButton;

    public FindRouteInterfaceResponse(JFrame parent, String jsonResponse) {
        super(parent);
        setTitle("Find Route Response");
        setContentPane(findRouteInterfaceResponse);
        setMinimumSize(new Dimension(800, 300));
        setModal(true);
        setLocationRelativeTo(parent);
        updateTable(Json.fromJson(jsonResponse, FindRouteResponse.class).payload().getComandos());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void createTable(String [][] data) {
        routesTable.setModel(new DefaultTableModel(
                data,
                new String[] {"Ponto Inicial do Segmento", "Ponto final do segmento", "Distância (m)", "Direcão", "Observacão"}
        ));
    }

    public void updateTable(List<RouteDTO> rotas) {
        String [][] data = new String[rotas.size()][5];
        for(int i = 0; i < rotas.size(); i++) {
            data[i][0] = rotas.get(i).getNome_inicio();
            data[i][1] = rotas.get(i).getNome_final();
            data[i][2] = String.valueOf(rotas.get(i).getDistancia());
            data[i][3] = rotas.get(i).getDirecao();
            data[i][4] = rotas.get(i).getAviso();
        }
        createTable(data);
    }
}
