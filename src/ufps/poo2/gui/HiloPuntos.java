/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.gui;

import javax.swing.JTextArea;

/**
 *
 * @author YHUVER
 */
public class HiloPuntos extends Thread{
    private Frame f;
    private JTextArea t;

    public HiloPuntos(Frame f, JTextArea t) {
        this.f = f;
        this.t = t;
    }
    
    public void run(){
        while (true) {
            t.setText(f.getMyC().mostrarPuntos());
        }
    }
}
