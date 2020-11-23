package com.progra.proyecto2.accesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbScore {

    public Connection getConnection() {
        Connection coneccion = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            coneccion = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectoll", "root", "root");
            return coneccion;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

}
