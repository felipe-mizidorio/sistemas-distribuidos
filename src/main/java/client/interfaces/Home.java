package client.interfaces;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JDialog{
    private JPanel panel1;
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

    @Getter
    private String operation;

    public Home(JFrame parent) {
        super(parent);
        setTitle("Operation");
        setContentPane(panel1);
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
        setVisible(true);
    }
}
