/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.gui;

import ufps.poo2.gui.HiloLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author estudiante
 */
public class PanelJuego extends javax.swing.JPanel {

    /**
     * Creates new form PanelJuego
     */
    private Mypanel[][] myPanel;
    private Frame myF;
    private int num;
    private HiloPuntos h;
    private HiloGanador g;
    private int n=0;

    public PanelJuego(Frame b, int tablero) {
        initComponents();
//        this.PanelJuego.setBackground(Color.red);
        myF = b;
        num = tablero;
       

        h = new HiloPuntos(myF, Area);
        h.start();
        g = new HiloGanador(this);
        g.start();
         añadirJuego();
    }

    public void añadirJuego() {
        System.out.println(num);
        myPanel = new Mypanel[num][num];
        GridLayout a = new GridLayout(myPanel.length, myPanel.length);
        this.PanelJuego.setPreferredSize(new Dimension(500, 500));
        this.PanelJuego.setLayout(a);
        añadir();
        habilitar();
        HiloLabel hilo = new HiloLabel(this);
        hilo.start();

    }

    public void habilitar() {
        if (myF.getMyC().getMyS() != null) {
            eventos();
        }
    }

    public void añadir() {
        for (int i = 0; i < myPanel.length; i++) {
            for (int j = 0; j < myPanel.length; j++) {
                Mypanel myP = new Mypanel(i, j);
                myP.setBackground(Color.LIGHT_GRAY);

                if (i == 0) {
                    myP.getMyLabeV()[0].setPreferredSize(new Dimension(8, 8));

                }
                if (i == myPanel.length - 1) {
                    myP.getMyLabeV()[1].setPreferredSize(new Dimension(8, 8));
                }
                if (j == 0) {
                    myP.getMyLabeV()[3].setPreferredSize(new Dimension(8, 8));
                }
                if (j == myPanel.length - 1) {
                    myP.getMyLabeV()[2].setPreferredSize(new Dimension(8, 8));
                }
                this.PanelJuego.add(myP);
                this.myPanel[i][j] = myP;
            }
        }
    }

    public void eventos() {
        MyLabel[] aux;
        for (int i = 0; i < myPanel.length; i++) {
            for (int j = 0; j < myPanel.length; j++) {

                aux = this.myPanel[i][j].getMyLabeV();
                if (j + 1 < myPanel.length) {
                    MouseListener evento = new ManejadorEventos(myPanel[i][j + 1].getMyLabeV()[3], myPanel[i][j].getMyLabeV()[2], this, (i + "x" + (j + 1)), (i + "x" + j), 3, 2);
                    myPanel[i][j].getMyLabeV()[2].addMouseListener(evento);
                } else {
                    MouseListener evento = new ManejadorEventos(myPanel[i][j].getMyLabeV()[2], this, (i + "x" + j), 2);
                    myPanel[i][j].getMyLabeV()[2].addMouseListener(evento);
                }
                if (j > 0) {
                    MouseListener evento = new ManejadorEventos(myPanel[i][j - 1].getMyLabeV()[2], myPanel[i][j].getMyLabeV()[3], this, (i + "x" + (j - 1)), (i + "x" + j), 2, 3);
                    myPanel[i][j].getMyLabeV()[3].addMouseListener(evento);
                } else {
                    MouseListener evento = new ManejadorEventos(myPanel[i][j].getMyLabeV()[3], this, (i + "x" + j), 3);
                    myPanel[i][j].getMyLabeV()[3].addMouseListener(evento);
                }
                if (i + 1 < myPanel.length) {
                    MouseListener evento = new ManejadorEventos(myPanel[i + 1][j].getMyLabeV()[0], myPanel[i][j].getMyLabeV()[1], this, ((i + 1) + "x" + j), (i + "x" + j), 0, 1);
                    myPanel[i][j].getMyLabeV()[1].addMouseListener(evento);
                } else {
                    MouseListener evento = new ManejadorEventos(myPanel[i][j].getMyLabeV()[1], this, (i + "x" + j), 1);
                    myPanel[i][j].getMyLabeV()[1].addMouseListener(evento);
                }
                if (i > 0) {
                    MouseListener evento = new ManejadorEventos(myPanel[i - 1][j].getMyLabeV()[1], myPanel[i][j].getMyLabeV()[0], this, ((i - 1) + "x" + j), (i + "x" + j), 1, 0);
                    myPanel[i][j].getMyLabeV()[0].addMouseListener(evento);
                } else {
                    MouseListener evento = new ManejadorEventos(myPanel[i][j].getMyLabeV()[0], this, (i + "x" + j), 0);
                    myPanel[i][j].getMyLabeV()[0].addMouseListener(evento);
                }
            }

        }
    }

    public void bloquearJuego() {
        MyLabel[] aux;
        for (int i = 0; i < myPanel.length; i++) {
            for (int j = 0; j < myPanel.length; j++) {
                if (myPanel[i][j].getMyLabeV()[0].getMouseListeners()[0] != null && myPanel[i][j].getMyLabeV()[1].getMouseListeners()[0] != null
                        && myPanel[i][j].getMyLabeV()[2].getMouseListeners()[0] != null && myPanel[i][j].getMyLabeV()[3].getMouseListeners()[0] != null) {
                    myPanel[i][j].getMyLabeV()[0].removeMouseListener(myPanel[i][j].getMyLabeV()[0].getMouseListeners()[0]);
                }
                myPanel[i][j].getMyLabeV()[1].removeMouseListener(myPanel[i][j].getMyLabeV()[1].getMouseListeners()[0]);
                myPanel[i][j].getMyLabeV()[2].removeMouseListener(myPanel[i][j].getMyLabeV()[2].getMouseListeners()[0]);
                myPanel[i][j].getMyLabeV()[3].removeMouseListener(myPanel[i][j].getMyLabeV()[3].getMouseListeners()[0]);
            }

        }
    }

    public void pintarLabel(String coordenada1, String coordenada2, Color color, int label, int label2) {
        String[] vector1 = coordenada1.split("x");
        int fila1 = Integer.parseInt(vector1[0]);
        int columna1 = Integer.parseInt(vector1[1]);
        rellenar(color);
        myPanel[fila1][columna1].getMyLabeV()[label].setBackground(color);
        if (!coordenada2.equalsIgnoreCase("") && label2 < 1000) {
            String[] vector2 = coordenada2.split("x");
            int fila2 = Integer.parseInt(vector2[0]);
            int columna2 = Integer.parseInt(vector2[1]);
            myPanel[fila2][columna2].getMyLabeV()[label2].setBackground(color);
        }

        boolean cad = rellenar(color);

        if (!cad) {
            eventos();
        }

    }

    public boolean rellenar(Color c) {
        boolean cad = false;
        for (int i = 0; i < myPanel.length; i++) {
            for (int j = 0; j < myPanel.length; j++) {
                if (myPanel[i][j].getMyLabeV()[0].getBackground() != Color.black
                        && myPanel[i][j].getMyLabeV()[1].getBackground() != Color.black && myPanel[i][j].getMyLabeV()[2].getBackground() != Color.black && myPanel[i][j].getMyLabeV()[3].getBackground() != Color.black && myPanel[i][j].getBackground() != Color.black) {
                    if (myPanel[i][j].getBackground() == Color.LIGHT_GRAY && myPanel[i][j].getMyLabeV()[0].getBackground() != Color.WHITE
                            && myPanel[i][j].getMyLabeV()[1].getBackground() != Color.WHITE && myPanel[i][j].getMyLabeV()[2].getBackground() != Color.WHITE && myPanel[i][j].getMyLabeV()[3].getBackground() != Color.WHITE && myPanel[i][j].getBackground() != Color.WHITE) {
                        
                        if(c==Color.BLUE){
                            Color a=new Color(135, 193, 252);
                            myPanel[i][j].setBackground(a);
                            myF.getMyC().buscarJugadorPorColor(c).setPuntos(myF.getMyC().buscarJugadorPorColor(c).getPuntos() + 1);
                        cad = true;
                        }
                        else if(c==Color.red){
                            Color a=new Color(251, 134, 142);
                             myPanel[i][j].setBackground(a);
                           myF.getMyC().buscarJugadorPorColor(c).setPuntos(myF.getMyC().buscarJugadorPorColor(c).getPuntos() + 1);
                        cad = true; 
                        }
                        
                    }
                }

            }

        }
        return cad;
    }

    public Frame getMyF() {
        return myF;
    }

    public void setMyF(Frame myF) {
        this.myF = myF;
    }

    public Mypanel[][] getMyPanel() {
        return myPanel;
    }

    public void setMyPanel(Mypanel[][] myPanel) {
        this.myPanel = myPanel;
    }

    public void añadirMensaje(String nombre, String mensaje) {
        String nom = nombre;
        String men = mensaje;
        Calendar c = Calendar.getInstance();
        int hora = c.getTime().getHours();
        int minutos = c.getTime().getMinutes();
        if (!txtArea.getText().isEmpty()) {
            txtArea.setText(txtArea.getText() + "\n" + nombre + "(" + hora + ":" + minutos + ")>" + men);
            txtMensaje.setText("");
        } else {
            txtArea.setText(nombre + "(" + hora + ":" + minutos + ")>" + men);
            txtMensaje.setText("");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelJuego = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        txtMensaje = new javax.swing.JTextField();
        btnNombre = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Area = new javax.swing.JTextArea();

        setPreferredSize(new java.awt.Dimension(900, 600));

        javax.swing.GroupLayout PanelJuegoLayout = new javax.swing.GroupLayout(PanelJuego);
        PanelJuego.setLayout(PanelJuegoLayout);
        PanelJuegoLayout.setHorizontalGroup(
            PanelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
        );
        PanelJuegoLayout.setVerticalGroup(
            PanelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
        );

        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        btnNombre.setText("Enviar ");
        btnNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNombreActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        Area.setColumns(20);
        Area.setRows(5);
        jScrollPane2.setViewportView(Area);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                        .addComponent(txtMensaje)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnNombre)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardar)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PanelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNombre)
                            .addComponent(btnGuardar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNombreActionPerformed
        // TODO add your handling code here:
        String nombre = myF.getMyC().getMyJugador().getNombre();
        String mensaje = this.txtMensaje.getText();

        if (mensaje.isEmpty()) {
            JOptionPane.showInputDialog(null, "Por favor digite su mensaje");
        } else {
            this.añadirMensaje(nombre, mensaje);
            myF.getMyC().enviarChat(nombre, mensaje);
        }

    }//GEN-LAST:event_btnNombreActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        n++;
       
        String cad=this.txtArea.getText();
        
       Calendar c1 = Calendar.getInstance();
        System.out.println(c1.getTime());
        
        String sFichero = "ARCHIVOS//Historial n "+n+".txt";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(sFichero));
   bw.write(c1.getTime()+"\n"+cad);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(PanelJuego.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Area;
    private javax.swing.JPanel PanelJuego;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNombre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables
}
