/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.servidor;

import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author YHUVER
 */
public class HiloConexion extends Thread{
    
    private Socket myC;
    private boolean estado;
    private Servidor myS;
    private int identificador;

    public HiloConexion(Socket myC, Servidor myS,int identificador) {
        this.myC = myC;
        this.myS = myS;
        this.identificador=identificador;
        estado=true;
    }
    
    public void run(){
        try {
            while (estado) {    
                System.out.println("esperando hilo"+identificador);
                myS.recibirMensajes(myC, identificador);
                if(!myC.isClosed()){
                   JOptionPane.showMessageDialog(null, "Ganaste la partida");
                   estado=false;
                }
            }
        } catch (Exception e) {
        }
    }

    public Socket getMyC() {
        return myC;
    }

    public void setMyC(Socket myC) {
        this.myC = myC;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    
    
}
