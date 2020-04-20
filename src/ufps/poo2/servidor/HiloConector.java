/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.servidor;

/**
 *
 * @author YHUVER
 */
public class HiloConector extends Thread{
    
    private Servidor myS;
    private boolean estado;

    public HiloConector(Servidor myS) {
        this.myS = myS;
        estado=true;
    }
    
    public void run(){
        try {
            myS.cargandoServidor();
            while(estado){
                myS.esperandoCliente();
            }
        } catch (Exception e) {
        }
    }
}
