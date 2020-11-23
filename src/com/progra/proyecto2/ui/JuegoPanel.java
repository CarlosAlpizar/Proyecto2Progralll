/*
 En el panel de abajo quieren que pongamos los puntajes del jugador, vidad, tanques eliminados etc
para hacer los movimientos los podemos generar con spritee o manualmente
 */
package com.progra.proyecto2.ui;

import com.progra.proyecto2.entidades.Bala;
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
import java.util.LinkedList;
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

    LinkedList<Enemigo> enemigos = null;
    ColisionDetection colision = null;

    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    BufferedImage obstaculo = null;
    BufferedImage borde1 = null;
    BufferedImage borde2 = null;
    BufferedImage borde3 = null;
    BufferedImage borde4 = null;
    BufferedImage bala = null;

    //entorno
    private Image mapa = null;

    Jugador jugador = null;
    private SpriteSheet spriteSheet;

    Enemigo enemigo = null;
    Enemigo enemigo2 = null;
    Enemigo enemigo3 = null;
    Enemigo enemigo4 = null;
    Bala shoot = null;

    ColisionDetection colisionDetectionObs = null;
    ColisionDetection colisionDetection1 = null;
    ColisionDetection colisionDetection2 = null;
    ColisionDetection colisionDetection3 = null;
    ColisionDetection colisionDetection4 = null;

    BufferedImage currentPlayerSprite;
    BufferedImage currentEnemigoSprite;
    private boolean obstacle = false;

    public JuegoPanel(String flag) {

        try {
            ImageLoader imgLoader = new ImageLoader();

            image1 = imgLoader.getImage(Panel.class.getResource("/com/progra/proyecto2/resources/explore1.jpg"));
            image2 = imgLoader.getImage(Panel.class.getResource("/com/progra/proyecto2/resources/explore2.jpg"));
            image3 = imgLoader.getImage(Panel.class.getResource("/com/progra/proyecto2/resources/explore3.jpg"));

            borde1 = ImageIO.read(Panel.class.getResource("/com/progra/proyecto2/resources/borde1.png"));
            borde2 = ImageIO.read(Panel.class.getResource("/com/progra/proyecto2/resources/borde1.png"));
            borde3 = ImageIO.read(Panel.class.getResource("/com/progra/proyecto2/resources/borde2.png"));
            borde4 = ImageIO.read(Panel.class.getResource("/com/progra/proyecto2/resources/borde2.png"));
            bala = ImageIO.read(Panel.class.getResource("/com/progra/proyecto2/resources/bala.png"));

            BufferedImage sheet = ImageIO.read(Panel.class.getResource("/com/progra/proyecto2/resources/tanks.png"));
            spriteSheet = new SpriteSheetBuilder()
                    .withSheet(sheet)
                    .withColumns(8)
                    .withRows(8)
                    .withSpriteCount(64).
                    build();

            jugador = new Jugador(400, 300);
            colisionDetectionObs = new ColisionDetection();

            colisionDetection1 = new ColisionDetection();
            colisionDetection2 = new ColisionDetection();
            colisionDetection3 = new ColisionDetection();
            colisionDetection4 = new ColisionDetection();

            this.setImage("/com/progra/proyecto2/resources/mapa.jpg");

            shoot = new Bala(100, 100, "Norte");
            Thread hilob = new Thread(shoot);
            hilob.start();

            enemigo = new Enemigo(210, 200, spriteSheet);
            Thread hilo = new Thread(enemigo);
            hilo.start();

        } catch (IOException ex) {
            Logger.getLogger(JuegoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        enemigos = new LinkedList<>();
        colision = new ColisionDetection();

        enemigos.add(new Enemigo(70, 70, spriteSheet));
        enemigos.add(new Enemigo(80, 100, spriteSheet));
        enemigos.add(new Enemigo(100, 400, spriteSheet));
        enemigos.add(new Enemigo(300, 300, spriteSheet));
        enemigos.add(new Enemigo(400, 400, spriteSheet));
        enemigos.add(new Enemigo(50, 50, spriteSheet));
        enemigos.add(new Enemigo(400, 30, spriteSheet));
        enemigos.add(new Enemigo(245, 140, spriteSheet));
        enemigos.add(new Enemigo(280, 350, spriteSheet));
        enemigos.add(new Enemigo(420, 250, spriteSheet));

        for (Enemigo em : enemigos) {
            Thread hilo = new Thread(em);
            hilo.start();
        }

    }

    public void iniciarEnemigos() {

        for (Enemigo em : enemigos) {
            Thread hilo = new Thread(em);
            hilo.start();
        }
    }

    public void paintEnemy(Graphics g, Enemigo em) {

        this.dibujarEnemigo(g, em.getX(), em.getY(), em.getDirection(), new int[]{10, 8, 1, 3}, em);
    }

    public void paint(Graphics g) {
        super.paint(g);
        this.dibujarBala(g, shoot.getBulletX(), shoot.getBulletY());
        this.dibujarTanqueJugadorSprite(g, jugador.getX(), jugador.getY(), jugador.getDirection(), new int[]{17, 24, 26, 19});

        for (Enemigo em : enemigos) {
            paintEnemy(g, em);
            g.setColor(Color.ORANGE);
            g.drawRect(0, 0, 580, 535);
        }

        Rectangle imaborde1 = colisionDetection1.crearRectangle(borde1, 0, 0);
        colisionDetection1.setImagen1Bounds(imaborde1);
        g.setColor(null);
        g.drawRect(imaborde1.x, imaborde1.y, imaborde1.width, imaborde1.height);
        Rectangle imaSpr = colisionDetection1.crearRectangle(currentPlayerSprite, jugador.getX(), jugador.getY());
        colisionDetection1.setImagen2Bounds(imaSpr);
        g.setColor(null);
        g.drawRect(imaSpr.x, imaSpr.y, imaSpr.width, imaSpr.height);
        Rectangle imaborde2 = colisionDetection2.crearRectangle(borde2, 0, 530);
        colisionDetection2.setImagen1Bounds(imaborde2);
        g.setColor(null);
        g.drawRect(imaborde2.x, imaborde2.y, imaborde2.width, imaborde2.height);
        Rectangle imaSpr2 = colisionDetection2.crearRectangle(currentPlayerSprite, jugador.getX(), jugador.getY());
        colisionDetection2.setImagen2Bounds(imaSpr2);
        g.setColor(null);
        g.drawRect(imaSpr2.x, imaSpr2.y, imaSpr2.width, imaSpr2.height);

        Rectangle imaborde3 = colisionDetection3.crearRectangle(borde3, 0, 0);
        colisionDetection3.setImagen1Bounds(imaborde3);
        g.setColor(null);
        g.drawRect(imaborde3.x, imaborde3.y, imaborde3.width, imaborde3.height);
        Rectangle imaSpr3 = colisionDetection3.crearRectangle(currentPlayerSprite, jugador.getX(), jugador.getY());
        colisionDetection3.setImagen2Bounds(imaSpr3);
        g.setColor(null);
        g.drawRect(imaSpr3.x, imaSpr3.y, imaSpr3.width, imaSpr3.height);
        Rectangle imaborde4 = colisionDetection4.crearRectangle(borde4, 575, 0);
        colisionDetection4.setImagen1Bounds(imaborde4);
        g.setColor(null);
        g.drawRect(imaborde4.x, imaborde4.y, imaborde4.width, imaborde4.height);

        Rectangle imaSpr4 = colisionDetection4.crearRectangle(currentPlayerSprite, jugador.getX(), jugador.getY());
        colisionDetection4.setImagen2Bounds(imaSpr4);
        g.setColor(null);
        g.drawRect(imaSpr4.x, imaSpr4.y, imaSpr4.width, imaSpr4.height);

        if (colisionDetection1.detecCollision() != null) {
            obstacle = true;

        } else if (colisionDetection2.detecCollision() != null) {
            obstacle = true;

        } else if (colisionDetection3.detecCollision() != null) {
            obstacle = true;

        } else if (colisionDetection4.detecCollision() != null) {
            obstacle = true;

        } else {
            obstacle = false;

        }

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

    public void dibujarBala(Graphics g, int x, int y) {
        g.drawImage(bala, x, y, this);
    }

    public void dibujarBorde1(Graphics g, int x, int y) {
        g.drawImage(borde1, x, y, this);
    }

    public void dibujarBorde2(Graphics g, int x, int y) {
        g.drawImage(borde2, x, y, this);
    }

    public void dibujarBorde3(Graphics g, int x, int y) {
        g.drawImage(borde3, x, y, this);
    }

    public void dibujarBorde4(Graphics g, int x, int y) {
        g.drawImage(borde4, x, y, this);
    }

    public void dibujarObstaculo(Graphics g, int x, int y) {
        g.drawImage(obstaculo, x, y, this);
    }

    public void dibujarEnemigo(Graphics g, int x, int y, String direction, int[] pos, Enemigo em) {
        BufferedImage sprite = null;
        boolean obstacle2 = false;
        switch (direction) {
            case "Norte":
                if (obstacle2 == false) {
                    sprite = spriteSheet.getSprite(pos[0]);
                    g.drawImage(sprite, x, y, this);
                } else {
                    em.setDirection("Sur");
                    em.setY(y = y - 15);
                    sprite = spriteSheet.getSprite(pos[2]);
                    g.drawImage(sprite, x, y, this);

                }
                break;

            case "Oeste":
                if (obstacle2 == false) {
                    sprite = spriteSheet.getSprite(pos[1]);
                    g.drawImage(sprite, x, y, this);
                } else {
                    em.setDirection("Este");
                    em.setX(x = x + 15);
                    sprite = spriteSheet.getSprite(pos[3]);
                    g.drawImage(sprite, x, y, this);

                }
                break;

            case "Sur":
                if (obstacle2 == false) {
                    sprite = spriteSheet.getSprite(pos[2]);
                    g.drawImage(sprite, x, y, this);
                } else {
                    em.setDirection("Norte");
                    em.setY(y = y + 15);
                    sprite = spriteSheet.getSprite(pos[1]);
                    g.drawImage(sprite, x, y, this);

                }
                break;

            case "Este":
                if (obstacle2 == false) {
                    sprite = spriteSheet.getSprite(pos[3]);
                    g.drawImage(sprite, x, y, this);
                } else {
                    em.setDirection("Oeste");
                    em.setX(x = x - 15);
                    sprite = spriteSheet.getSprite(pos[1]);
                    g.drawImage(sprite, x, y, this);
                }
                break;
        }

        em.getReg().x = x;
        em.getReg().y = y;
        em.getReg().width = 32;
        em.getReg().width = 32;

        g.setColor(Color.GREEN);
        g.drawRect(em.getReg().x, em.getReg().y, em.getReg().width, em.getReg().height);

    }

    @Override
    public void keyPressed(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_UP) {
            if (obstacle == false) {
                jugador.setDirection("Norte");
                jugador.moveUp();
            } else if (jugador.getDirection().equals("Norte")) {
                jugador.moveDown();
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
        if (k.getKeyCode() == KeyEvent.VK_SPACE) {
            jugador.shoot(jugador.getX(), jugador.getY(), this.jugador.getDirection());
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
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
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
