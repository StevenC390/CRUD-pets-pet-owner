/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Conexion;
import Vistas.Pet_Owner;

/**
 *
 * @author Steven
 */
public class Pets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion inst_conn = new Conexion();
        Pet_Owner inst_frame = new Pet_Owner();
        inst_conn.getConnection();
        inst_frame.setVisible(true);
    }
    
}
