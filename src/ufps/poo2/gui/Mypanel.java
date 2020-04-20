/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author diego
 */
public class Mypanel extends JPanel{
 
   private MyLabel arriba;
   private MyLabel abajo;
   private MyLabel derecha;
   private MyLabel izquierda;
   private MyLabel centro;
   private int x;
   private int y;
  private MyLabel[] myLabeV;
    public Mypanel(int x,int y){
        Dimension a = new Dimension(70, 70);
        this.setPreferredSize(a);
        this.setLayout(new java.awt.BorderLayout());
        myLabeV=new MyLabel[5];
        
        arriba=new MyLabel();
        abajo=new MyLabel();
        derecha=new MyLabel();
        izquierda=new MyLabel();
        centro=new MyLabel();
        arriba.setBackground(Color.white);
        abajo.setBackground(Color.WHITE);
        derecha.setBackground(Color.WHITE);
        izquierda.setBackground(Color.WHITE);
        
        myLabeV[0]=arriba;
        myLabeV[1]=abajo;
        myLabeV[2]=derecha;
        myLabeV[3]=izquierda;
        myLabeV[4]=centro;
        
        this.arriba.setName("arriba");
        this.abajo.setName("abajo");
        this.derecha.setName("derecha");
        this.izquierda.setName("izquierda");
        this.arriba.setPreferredSize(new Dimension(4, 4));
        this.abajo.setPreferredSize(new Dimension(4, 4));
        this.derecha.setPreferredSize(new Dimension(4, 4));
        this.izquierda.setPreferredSize(new Dimension(4, 4));
//        this.arriba.setText("-");
//        this.abajo.setText("-");
//        this.derecha.setText("|");
//        this.izquierda.setText("|");
        this.add(arriba,java.awt.BorderLayout.NORTH);
        this.add(abajo,java.awt.BorderLayout.SOUTH);
        this.add(derecha,java.awt.BorderLayout.EAST);
        this.add(izquierda,java.awt.BorderLayout.WEST);
        this.add(centro,java.awt.BorderLayout.CENTER);
        this.centro.setOpaque(false);
        this.x=x;
        this.y=y;
   
    }

    public MyLabel[] getMyLabeV() {
        return myLabeV;
    }

    
    
    
}
