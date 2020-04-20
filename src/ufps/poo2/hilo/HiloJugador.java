/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.hilo;

import javax.swing.JTextArea;
import ufps.poo2.business.basedatos.Derby.dto.JugadorDTO;
import ufps.poo2.gui.controller.Controlador;

/**
 *
 * @author YHUVER
 */
public class HiloJugador extends Thread{
    JTextArea dato;
    Controlador myC;
    boolean exito;

    public HiloJugador(JTextArea dato,Controlador myC) {
        exito=true;
        this.dato=dato;
        this.myC=myC;
    }
    
    
    public void detener(){
        exito=false;
    }
    
    
    @Override
    public void run(){       
        try{
            while(exito){
                String cad=myC.mostrarJugadoresConectados();
                dato.setText(cad);
                
                Thread.sleep(3000);
            }
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
