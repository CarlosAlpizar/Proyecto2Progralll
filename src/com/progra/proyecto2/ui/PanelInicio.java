/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progra.proyecto2.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Dario
 */
//the start panel is to start the game
class PanelInicio extends JPanel implements Runnable {

    int time = 0;
    private Image fondo = null;

    public void paint(Graphics g) {
        super.paint(g);
        this.setImage("/com/progra/proyecto2/resources/inicial.jpg");
        g.setColor(Color.black);
        //g.fillRect(0, 0, 600, 400);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            time++;
            this.repaint();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
    }

    public void setImage(String image) {
        if (image != null) {
            fondo = new ImageIcon(getClass().getResource(image)).getImage();
        }
    }

}
