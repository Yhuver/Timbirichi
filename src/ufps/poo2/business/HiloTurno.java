/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.business;

/**
 *
 * @author YHUVER
 */
public class HiloTurno extends Thread{
    private Negocio myN;
    private boolean estado;

    public HiloTurno(Negocio myN) {
        this.myN = myN;
        estado=true;
    }
    
    public void run(){
            try {
                while (estado) {                    
                    myN.generarTurno();
                }
            } catch (Exception e) {
            }
}
    
    

    
    
    
    
}
