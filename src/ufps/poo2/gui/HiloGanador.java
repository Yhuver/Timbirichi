/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.gui;

import javax.swing.JOptionPane;

/**
 *
 * @author YHUVER
 */
public class HiloGanador extends Thread{
    private PanelJuego p;

    public HiloGanador(PanelJuego p) {
        this.p = p;
    }

    public void run(){
        while(true){
            String cad=p.getMyF().getMyC().mostrarGanador();
            if(!cad.equalsIgnoreCase("")){
                p.bloquearJuego();
                JOptionPane.showMessageDialog(null,cad);
                break;
            }
        }
    }
    
}
