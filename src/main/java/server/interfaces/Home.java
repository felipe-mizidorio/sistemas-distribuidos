package server.interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Home extends JFrame {
    private JPanel serverHomePanel;
    private JLabel portaField;
    private JTable usersTable;

    public Home() {
        setTitle("Server HomePage");
        setContentPane(serverHomePanel);
        setMinimumSize(new Dimension(300, 150));
        setLocationRelativeTo(null);
        createTable(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void setPortaField(Integer porta) {
        portaField.setText(porta.toString());
    }

    private void createTable(String [][] data) {
        usersTable.setModel(new DefaultTableModel(
                data,
                new String[] {"IP"}
        ));
    }

    public void updateUserTable(List<String> usuarios) {
        String [][] data = new String[usuarios.size()][1];
        for(int i = 0; i < usuarios.size(); i++) {
            data[i][0] = usuarios.get(i);
        }
        createTable(data);
    }
}
