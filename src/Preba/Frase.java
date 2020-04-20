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
public class Frase {
    String frase;

    public Frase() {
        frase="";
    }
    
    public void insertar(String cad){
        frase=cad+"\n";
    }
    
    public void imprimir(){
        String cad="";
        Hilo a=new Hilo(cad,frase);
        a.start();
        System.out.println(cad);
        
    }
    public static void main(String[] args) {
        Frase a=new Frase();
        
        a.insertar("hola mita");
        
        a.imprimir();
        a.insertar("que mas");
    }
    
}
