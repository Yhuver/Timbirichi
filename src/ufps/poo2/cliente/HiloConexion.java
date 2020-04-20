/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.cliente;

import javax.swing.JOptionPane;
import ufps.poo2.servidor.Servidor;

/**
 *
 * @author YHUVER
 */
public class HiloConexion extends Thread{
    private boolean estado;
    private Cliente myC;

    public HiloConexion(Cliente myC) {
        this.myC = myC;
        estado=true;
    }
    
    public void run(){
        try {
            while(estado){
            myC.recibirMensaje();
                
            }
        } catch (Exception e) {
        }
    }
}
