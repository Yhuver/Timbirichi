/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author diego
 */
public class ManejadorEventos implements MouseListener {

    private MyLabel myL;
    private MyLabel myL2;
    private PanelJuego myJ;
    private String coordenada1="";
    private String coordenada2="";
    private int label1;
    private int label2;

    public ManejadorEventos(MyLabel myL, PanelJuego myJ,String coordenada,int label) {
        this.myL = myL;
        this.myJ = myJ;
        coordenada1=coordenada;
        label1=label;
    }

    public ManejadorEventos(MyLabel myL1, MyLabel myL2,PanelJuego myJ,String coordenada1,
            String coordenada2,int label1,int label2) {
        this.myL = myL1;
        this.myL2 = myL2;
        this.myJ=myJ;
        this.coordenada1=coordenada1;
        this.coordenada2=coordenada2;
        this.label1=label1;
        this.label2=label2;
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        Color c=myJ.getMyF().getMyC().getMyJugador().getColor();
        if (myL.getBackground()==Color.BLACK) {

            if (this.myL2 != null) {
                
                this.myL.setBackground(c);
                this.myL2.setBackground(c);
                myL.setExito(false);
                myL2.setExito(false);
                myJ.getMyF().getMyC().enviarCoordenadaServidor(coordenada1, coordenada2, label1, label2);
                boolean cad=myJ.rellenar(c);
                if(!cad){
                    this.myJ.bloquearJuego();
                }
                
            } else {

                this.myL.setBackground(c);
                myL.setExito(false);
                myJ.getMyF().getMyC().enviarCoordenadaServidor(coordenada1, "", label1, 1000);
                boolean cad=myJ.rellenar(c);
                if(!cad){
                    this.myJ.bloquearJuego();
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if (myL.getBackground()==Color.WHITE) {

            if (this.myL2 != null) {

                this.myL.setBackground(Color.black);
                this.myL2.setBackground(Color.black);

            } else {
                this.myL.setBackground(Color.black);

            }
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (myL.getBackground()==Color.BLACK) {

            if (this.myL2 != null) {
                this.myL.setBackground(Color.WHITE);
                this.myL2.setBackground(Color.WHITE);
            } else {
                this.myL.setBackground(Color.WHITE);
            }
        }
    }
    
    

}
