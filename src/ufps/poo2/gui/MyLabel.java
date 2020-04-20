/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author diego
 */
public class MyLabel extends  JLabel{
    
    private Mypanel contenedor;
    private boolean exito=true;
    

    public MyLabel() {
        this.setOpaque(true);
        
Dimension a = new Dimension(30, 15);
this.setPreferredSize(a);
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public boolean isExito() {
        return exito;
    }

    
    
    
}
