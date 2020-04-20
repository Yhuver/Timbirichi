/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preba;

/**
 *
 * @author YHUVER
 */
public class Hilo extends Thread{
    String cad;
    String frase;
    public Hilo(String a,String fr){
        cad=a;
        frase=fr;
    }
    
    public void run(){
        try {
            while(true){
                cad=frase;
            }
        } catch (Exception e) {
        }
    }
}
