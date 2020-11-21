/*
 En el panel de abajo quieren que pongamos los puntajes del jugador, vidad, tanques eliminados etc
para hacer los movimientos los podemos generar con spritee o manualmente
 */
package com.progra.proyecto2.ui;

import com.progra.proyecto2.entidades.Enemigo;
import com.progra.proyecto2.entidades.Jugador;
import com.progra.utils.ColisionDetection;
import com.progra.utils.ImageLoader;
import com.progra.utils.SpriteSheet;
import com.progra.utils.SpriteSheetBuilder;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Dario
 */
public class JuegoPanel extends JPanel implements KeyListener, Runnable {

    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    BufferedImage obstaculo = null;
    BufferedImage borde1 = null;
    BufferedImage borde2 = null;
    BufferedImage borde3 = null;
    BufferedImage borde4 = null;

    //entorno
    private Image mapa = null;

    /*  Image image4 = null;
    Image image5 = null;
    Image image6 = null;
    Image image7 = null;
    Image image8 = null;*/
    // entorno
    Jugador jugador = null;
    private SpriteSheet spriteSheet;

    Enemigo enemigo = null;

    ColisionDetection colisionDetectionObs = null;
    ColisionDetection colisionDetection1 = null;
    ColisionDetection colisionDetection2 = null;
    ColisionDetection colisionDetection3 = null;
    ColisionDetection colisionDetection4 = null;
    BufferedImage currentPlayerSprite;
    private boolean obstacle = false;

    public JuegoPanel(String flag) {

        try {
            ImageLoader imgLoader = new ImageLoader();

            image1 = imgLoader.getImage(Panel.class.getResource("/com/progra/proyecto2/resources/explore1.jpg"));
            image2 = imgLoader.getImage(Panel.class.getResource("/com/progra/proyecto2/resources/explore2.jpg"));
            image3 = imgLoader.getImage(Panel.class.getResource("/com/progra/proyecto2/resources/explore3.jpg"));
            obstaculo = ImageIO.read(Panel.class.getResource("/com/progra/proyecto2/resources/pruebafinal.png"));
            borde1 = ImageIO.read(Panel.class.getResource("/com/progra/proyecto2/resources/borde1.png"));
            borde2 = ImageIO.read(Panel.class.getResource("/com/progra/proyecto2/resources/borde1.png"));
            borde3 = ImageIO.read(Panel.class.getResource("/com/progra/proyecto2/resources/borde2.png"));
            borde4 = ImageIO.read(Panel.class.getResource("/com/progra/proyecto2/resources/borde2.png"));

//estas nos permiten usar los tanques del sprite
            BufferedImage sheet = ImageIO.read(Panel.class.getResource("/com/progra/proyecto2/resources/tanks.png"));
            spriteSheet = new SpriteSheetBuilder()
                    .withSheet(sheet)//con estos datos se escoge que imagen se quiere usar segun su coordenada en la matriz
                    .withColumns(8)
                    .withRows(8)
                    //  .withSpriteSize(40, 34)
                    .withSpriteCount(64).
                    build();
            jugador = new Jugador(400, 300);
            colisionDetectionObs = new ColisionDetection();
            colisionDetection1 = new ColisionDetection();
            colisionDetection2 = new ColisionDetection();
            colisionDetection3 = new ColisionDetection();
            colisionDetection4 = new ColisionDetection();

            this.setImage("/com/progra/proyecto2/resources/mapa.jpg");

            enemigo = new Enemigo(10, 500, spriteSheet);
            Thread hilo = new Thread(enemigo);
            hilo.start();

        } catch (IOException ex) {
            Logger.getLogger(JuegoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.blue);
        int cont = 0;
        this.dibujarTanqueJugadorSprite(g, jugador.getX(), jugador.getY(), jugador.getDirection(), new int[]{17, 24, 26, 19});
        this.dibujarObstaculo(g, 250, 200);
        this.dibujarentorno(g, 0, 0);

        //pinta el tanque naranja
        BufferedImage sprite = spriteSheet.getSprite(1);
        int x = (getWidth() - sprite.getWidth()) / 2;
        int y = (getHeight() - sprite.getHeight()) / 2;
        g.drawImage(sprite, x, y, this);

        Rectangle ima1 = colisionDetectionObs.crearRectangle(obstaculo, 250, 200);
        colisionDetectionObs.setImagen1Bounds(ima1);
        g.setColor(Color.red);
        g.drawRect(ima1.x, ima1.y, ima1.width, ima1.height);

        Rectangle ima2 = colisionDetectionObs.crearRectangle(currentPlayerSprite, jugador.getX(), jugador.getY());
        colisionDetectionObs.setImagen2Bounds(ima2);
        g.setColor(Color.red);
        g.drawRect(ima2.x, ima2.y, ima2.width, ima2.height);
////////////////////

        Rectangle imaborde1 = colisionDetection1.crearRectangle(borde1, 0, 0);
        colisionDetection1.setImagen1Bounds(imaborde1);
        g.setColor(Color.red);
        g.drawRect(imaborde1.x, imaborde1.y, imaborde1.width, imaborde1.height);

        Rectangle imaSpr = colisionDetection1.crearRectangle(currentPlayerSprite, jugador.getX(), jugador.getY());
        colisionDetection1.setImagen2Bounds(imaSpr);
        g.setColor(Color.red);
        g.drawRect(imaSpr.x, imaSpr.y, imaSpr.width, imaSpr.height);

        //////////////////////////////////////////////////////////
        Rectangle imaborde2 = colisionDetection2.crearRectangle(borde2, 0, 530);
        colisionDetection2.setImagen1Bounds(imaborde2);
        g.setColor(Color.red);
        g.drawRect(imaborde2.x, imaborde2.y, imaborde2.width, imaborde2.height);

        Rectangle imaSpr2 = colisionDetection2.crearRectangle(currentPlayerSprite, jugador.getX(), jugador.getY());
        colisionDetection2.setImagen2Bounds(imaSpr2);
        g.setColor(Color.red);
        g.drawRect(imaSpr2.x, imaSpr2.y, imaSpr2.width, imaSpr2.height);

        ///////////////////////////
        Rectangle imaborde3 = colisionDetection3.crearRectangle(borde3, 0, 0);
        colisionDetection3.setImagen1Bounds(imaborde3);
        g.setColor(Color.red);
        g.drawRect(imaborde3.x, imaborde3.y, imaborde3.width, imaborde3.height);

        Rectangle imaSpr3 = colisionDetection3.crearRectangle(currentPlayerSprite, jugador.getX(), jugador.getY());
        colisionDetection3.setImagen2Bounds(imaSpr3);
        g.setColor(Color.red);
        g.drawRect(imaSpr3.x, imaSpr3.y, imaSpr3.width, imaSpr3.height);

        Rectangle imaborde4 = colisionDetection4.crearRectangle(borde4, 575, 0);
        colisionDetection4.setImagen1Bounds(imaborde4);
        g.setColor(Color.red);
        g.drawRect(imaborde4.x, imaborde4.y, imaborde4.width, imaborde4.height);

        Rectangle imaSpr4 = colisionDetection4.crearRectangle(currentPlayerSprite, jugador.getX(), jugador.getY());
        colisionDetection4.setImagen2Bounds(imaSpr4);
        g.setColor(Color.red);
        g.drawRect(imaSpr4.x, imaSpr4.y, imaSpr4.width, imaSpr4.height);
        ///////////////////////////////////////////////
        if (colisionDetectionObs.detecCollision() != null) {
            obstacle = true;
            System.out.println("choco!!");
        } else if (colisionDetection1.detecCollision() != null) {
            obstacle = true;
            System.out.println("choco!!");
        } else if (colisionDetection2.detecCollision() != null) {
            obstacle = true;
            System.out.println("choco!!");
        } else if (colisionDetection3.detecCollision() != null) {
            obstacle = true;
            System.out.println("choco!!");
        } else if (colisionDetection4.detecCollision() != null) {
            obstacle = true;
            System.out.println("choco!!");
        } else {
            obstacle = false;
        }

        /////////////////////////////////////////////////////////////
        enemigo.pintar(g);
    }

    public void dibujarTanqueJugadorSprite(Graphics g, int x, int y, String direction, int[] pos) {
        BufferedImage sprite = null;

        switch (direction) {
            case "Norte":
                sprite = spriteSheet.getSprite(pos[0]);
                g.drawImage(sprite, x, y, this);
                break;
            case "Oeste":
                sprite = spriteSheet.getSprite(pos[1]);
                g.drawImage(sprite, x, y, this);
                break;
            case "Sur":
                sprite = spriteSheet.getSprite(pos[2]);
                g.drawImage(sprite, x, y, this);
                break;
            case "Este":
                sprite = spriteSheet.getSprite(pos[3]);
                g.drawImage(sprite, x, y, this);
                break;
        }
        currentPlayerSprite = sprite;
    }

    public void dibujarentorno(Graphics g, int x, int y) {

        /*g.drawImage(image4, x, y, this);
        g.drawImage(image4, x + 10, y + 10, this);
        g.drawImage(image4, x + -80, y + 25, this);
        g.drawImage(image4, x + 50, y + 45, this);

        g.drawImage(image5, x + 350, y - 80, this);
        g.drawImage(image6, x + 400, y - 70, this);

        g.drawImage(image8, x + 150, y + 10, this);
        g.drawImage(image8, x + 180, y + 75, this);
        g.drawImage(image8, x + 100, y + 150, this);*/
    }

    public void dibujarObstaculo(Graphics g, int x, int y) {

        g.drawImage(obstaculo, x, y, this);
    }

    //permite manipular el control de jugador
    @Override
    public void keyPressed(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_UP) {
            if (obstacle == false) {
                jugador.setDirection("Norte");
                jugador.moveUp();
            } else if (jugador.getDirection().equals("Norte")) {
                jugador.moveDown();
                // jugador.setDirection("Sur");

            }
        }
        if (k.getKeyCode() == KeyEvent.VK_LEFT) {
            if (obstacle == false) {
                jugador.setDirection("Oeste");
                jugador.moveLeft();
            } else if (jugador.getDirection().equals("Oeste")) {
                jugador.moveRight();
            }
        }
        if (k.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (obstacle == false) {
                jugador.setDirection("Este");
                jugador.moveRight();
            } else if (jugador.getDirection().equals("Este")) {
                jugador.moveLeft();
            }
        }
        if (k.getKeyCode() == KeyEvent.VK_DOWN) {
            if (obstacle == false) {
                jugador.setDirection("Sur");
                jugador.moveDown();
            } else if (jugador.getDirection().equals("Sur")) {
                jugador.moveUp();
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            this.repaint();
//			}
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(mapa, 0, 0, getWidth(), getHeight(), null);
    }

    public void setImage(String image) {
        if (image != null) {
            mapa = new ImageIcon(getClass().getResource(image)).getImage();
        }
    }

}