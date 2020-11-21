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
import javax.swing.JPanel;

public class Principal implements ActionListener {

    JFrame jf;
    PanelInicio sp = null;
    JuegoPanel juegoPanel = null;
    PanelInicio startPanel = null;
    JMenu jm1;
    JMenuItem jmt1;
    JMenuBar jmb;

    public Principal() {

        jf = new JFrame("Tank War");

        jmb = new JMenuBar();
        jm1 = new JMenu("Juego");
        jmt1 = new JMenuItem("Nuevo juego");
        jmt1.setActionCommand("Nuevo");//vamos a utilizarlo para hacer la diferentes funciones del menu
        jmt1.addActionListener(this);

        jm1.add(jmt1);
        jm1.addSeparator();
        jmb.add(jm1);
        jf.setJMenuBar(jmb);

        //crear Panel de inicio
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

            juegoPanel = new JuegoPanel("New");

            Thread juegoPanelThread = new Thread(juegoPanel);
            juegoPanelThread.start();

            jf.remove(startPanel);
            jf.add(juegoPanel);
            jf.addKeyListener(juegoPanel);

            jf.setVisible(true);

        }

    }

}
