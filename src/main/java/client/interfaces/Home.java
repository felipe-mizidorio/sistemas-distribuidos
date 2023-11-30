package client.interfaces;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JDialog{
    private JPanel clientHomePanel;
    private JButton LOGINButton;
    private JButton LOGOUTButton;
    private JButton ADMIN_BUSCAR_USUARIOSButton;
    private JButton ADMIN_BUSCAR_USUARIOButton;
    private JButton ADMIN_ATUALIZAR_USUARIOButton;
    private JButton ADMIN_DELETAR_USUARIOButton;
    private JButton BUSCAR_USUARIOButton;
    private JButton CADASTRAR_USUARIOButton;
    private JButton ATUALIZAR_USUARIOButton;
    private JButton DELETAR_USUARIOButton;
    private JButton BUSCAR_PDISButton;
    private JButton CADASTRAR_PDIButton;
    private JButton ATUALIZAR_PDIButton;
    private JButton DELETAR_PDIButton;
    private JButton BUSCAR_SEGMENTOSButton;
    private JButton CADASTRAR_SEGMENTOButton;
    private JButton ATUALIZAR_SEGMENTOButton;
    private JButton DELETAR_SEGMENTOButton;

    @Getter
    private String operation;

    public Home(JFrame parent) {
        super(parent);
        setTitle("Client HomePage");
        setContentPane(clientHomePanel);
        setMinimumSize(new Dimension(500, 500));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "LOGIN";
                dispose();
            }
        });
        LOGOUTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "LOGOUT";
                dispose();
            }
        });
        ADMIN_BUSCAR_USUARIOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "ADMIN_BUSCAR_USUARIOS";
                dispose();
            }
        });
        ADMIN_BUSCAR_USUARIOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "ADMIN_BUSCAR_USUARIO";
                dispose();
            }
        });
        ADMIN_ATUALIZAR_USUARIOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "ADMIN_ATUALIZAR_USUARIO";
                dispose();
            }
        });
        ADMIN_DELETAR_USUARIOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "ADMIN_DELETAR_USUARIO";
                dispose();
            }
        });
        BUSCAR_USUARIOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "BUSCAR_USUARIO";
                dispose();
            }
        });
        CADASTRAR_USUARIOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "CADASTRAR_USUARIO";
                dispose();
            }
        });
        ATUALIZAR_USUARIOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "ATUALIZAR_USUARIO";
                dispose();
            }
        });
        DELETAR_USUARIOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "DELETAR_USUARIO";
                dispose();
            }
        });
        BUSCAR_PDISButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "BUSCAR_PDIS";
                dispose();
            }
        });
        CADASTRAR_PDIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "CADASTRAR_PDI";
                dispose();
            }
        });
        ATUALIZAR_PDIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "ATUALIZAR_PDI";
                dispose();
            }
        });
        DELETAR_PDIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "DELETAR_PDI";
                dispose();
            }
        });
        BUSCAR_SEGMENTOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "BUSCAR_SEGMENTOS";
                dispose();
            }
        });
        CADASTRAR_SEGMENTOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "CADASTRAR_SEGMENTO";
                dispose();
            }
        });
        ATUALIZAR_SEGMENTOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "ATUALIZAR_SEGMENTO";
                dispose();
            }
        });
        DELETAR_SEGMENTOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operation = "DELETAR_SEGMENTO";
                dispose();
            }
        });
        setVisible(true);
    }
}
