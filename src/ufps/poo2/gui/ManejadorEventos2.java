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
 * @author YHUVER
 */
public class ManejadorEventos2 implements MouseListener{
    private MyLabel myL;
    private MyLabel myL2;
    private PanelJuego myJ;

    public ManejadorEventos2(MyLabel myL, PanelJuego myJ) {
        this.myL = myL;
        this.myJ = myJ;
    }

    public ManejadorEventos2(MyLabel myL1, MyLabel myL2,PanelJuego myJ) {
        this.myL = myL1;
        this.myL2 = myL2;
        this.myJ=myJ;
    }
public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (myL.getBackground()==Color.BLUE) {

            if (this.myL2 != null) {
                this.myL.setBackground(Color.WHITE);
                this.myL2.setBackground(Color.WHITE);
                myL.setExito(false);
                myL2.setExito(false);
            } else {

                this.myL.setBackground(Color.WHITE);
                myL.setExito(false);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if (myL.getBackground()==Color.blue) {

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
        if (myL.getBackground()==Color.BLUE) {

            if (this.myL2 != null) {
                this.myL.setBackground(Color.WHITE);
                this.myL2.setBackground(Color.WHITE);
            } else {
                this.myL.setBackground(Color.WHITE);
            }
        }
    }}