/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progra.proyecto2.accesoDatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Consulta {

    DbScore db = new DbScore();
    Connection con = db.getConnection();

    public String insertar(String nombre, String score) {

        try {
            CallableStatement cs = con.prepareCall("{call SP_In_Jugador(?,?)}");
            cs.setString(1, nombre);
            cs.setString(2, score);
            cs.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return nombre + "_" + score;
    }

    public String actualizar() {
        String selec = "select * from jugadores";
        Statement st;
        String retorno = "";
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(selec);

            while (rs.next()) {
                retorno += rs.getString(1) + "_" + rs.getString(2) + ";";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return retorno;
    }

}
