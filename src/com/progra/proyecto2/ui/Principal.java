package com.progra.proyecto2.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Principal implements ActionListener {

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    JFrame jf;
    PanelInicio sp = null;
    JuegoPanel juegoPanel = new JuegoPanel("New");
    PanelInicio startPanel = null;
    Ranking ranking = new Ranking("Ranking");
    Controles controles = new Controles();
    JMenu jm1;
    JMenuItem jmt1;
    JMenuItem jmt2;
    JMenuItem jmt3;
    JMenuBar jmb;
    private String user;

    public Principal() {

        jf = new JFrame("Tank War");

        jmb = new JMenuBar();

        jm1 = new JMenu("Juego");
        jmt1 = new JMenuItem("Nuevo juego");
        jmt2 = new JMenuItem("Ranking");
        jmt3 = new JMenuItem("Controles");

        jmt1.setActionCommand("Nuevo");
        jmt2.setActionCommand("Ranking");
        jmt3.setActionCommand("Controles");

        jmt1.addActionListener(this);
        jmt2.addActionListener(this);
        jmt3.addActionListener(this);

        jm1.add(jmt1);
        jm1.add(jmt2);
        jm1.add(jmt3);
        jm1.addSeparator();

        jmb.add(jm1);

        jf.setJMenuBar(jmb);

        startPanel = new PanelInicio();
        Thread startPanelThread = new Thread(startPanel);

        startPanelThread.start();

        jf.add(startPanel);

        jf.setLocation(600, 300);
        jf.setResizable(false);
        jf.setSize(600, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Principal();
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        // TODO Auto-generated method stub

        if (a.getActionCommand().equals("Nuevo")) {
            user = JOptionPane.showInputDialog("Digite el nombre del jugador");
            ranking.setNombre(user);

            Thread juegoPanelThread = new Thread(juegoPanel);
            juegoPanelThread.start();

            jf.remove(startPanel);
            jf.remove(ranking);
            jf.remove(controles);
            jf.add(juegoPanel);
            jf.addKeyListener(juegoPanel);
            jf.setVisible(true);

        } else if (a.getActionCommand().equals("Ranking")) {

            Thread rankingpanel = new Thread(ranking);
            rankingpanel.start();

            jf.remove(startPanel);
            jf.remove(juegoPanel);
            jf.remove(controles);
            jf.add(ranking);
            jf.addKeyListener(ranking);
            ranking.GenerarTabla();
            jf.setVisible(true);

        } else if (a.getActionCommand().equals("Controles")) {

            Thread controlpanel = new Thread(controles);
            controlpanel.start();

            jf.remove(startPanel);
            jf.remove(juegoPanel);
            jf.remove(ranking);

            jf.add(controles);
            jf.addKeyListener(controles);
            jf.setVisible(true);

        }

    }

}
