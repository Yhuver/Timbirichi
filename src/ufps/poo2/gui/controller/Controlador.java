package ufps.poo2.gui.controller;

import java.awt.Color;
import java.util.List;

import ufps.poo2.business.INegocio;
import ufps.poo2.business.basedatos.Derby.dto.JugadorDTO;
import ufps.poo2.gui.Frame;
import ufps.poo2.gui.controller.locator.ServiceLocator;
import ufps.poo2.servidor.Servidor;

public class Controlador {

	private ServiceLocator sLocator;
        
    
    public Controlador(Frame fr){
        sLocator = ServiceLocator.getInstance(fr);
    }
	
    public String registrarJugador(String nombre,String ip) throws Exception{
    	INegocio myN = sLocator.getBusinessFacadeInstance();
        String cad = "";
        try {
            cad = myN.registrarJugador(nombre, ip);
                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return cad;
    }
    
    public String mostrarJugadoresConectados() throws Exception{
        String cad="";
        INegocio myN = sLocator.getBusinessFacadeInstance();
        try {
            cad = myN.mostrarJugadoresConectados();
                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cad;
    }
    
    public String enviarJugadores(String nombre,String ip){
        String cad="";
        INegocio myN = sLocator.getBusinessFacadeInstance();
        try {
            cad=myN.enviarJugador(nombre, ip);
            System.out.println("controlador");
        } catch (Exception e) {
        }
        return cad;
    }
    
    
    public void iniciarCliente(String ip){
        try {
            INegocio myN = sLocator.getBusinessFacadeInstance();
            myN.iniciarCliente(ip);
        } catch (Exception e) {
        }
    }
    
    public int getTablero(){
        int cad=0;
        try {
            INegocio myN = sLocator.getBusinessFacadeInstance();
            
            cad=myN.getTablero();
        } catch (Exception e) {
        }
        return cad;
    }
    public void enviarTablero(int num){
        try {
            INegocio myN = sLocator.getBusinessFacadeInstance();
            
            myN.enviarTablero(num);
        } catch (Exception e) {
        }
    }
    public void enviarListaJugadores(){
        try {
            INegocio myN = sLocator.getBusinessFacadeInstance();
            myN.enviarListaJugadores();
        } catch (Exception e) {
        }
    }
  
    
    public void enviarCoordenadaServidor(String coordenada1,String coordenada2,int label1,int label2){
        
        try {
            INegocio myN = sLocator.getBusinessFacadeInstance();
            myN.enviarCoordenadaServidor(coordenada1,coordenada2,label1,label2);
        } catch (Exception e) {
        }
    }

    public JugadorDTO getMyJugador() {
        JugadorDTO j=null;
        try {
            INegocio myN = sLocator.getBusinessFacadeInstance();
             j=myN.getMyJugador();
        } catch (Exception e) {
        }
        return j;
    }
   public void enviarChat(String nombre,String mensaje){
       try {
           INegocio myN = sLocator.getBusinessFacadeInstance();
             myN.enviarChat(nombre, mensaje);
       } catch (Exception e) {
       }
   }
   public Servidor getMyS(){
       Servidor a =null;
       try {
           INegocio myN = sLocator.getBusinessFacadeInstance();
             a=myN.getMyS();
       } catch (Exception e) {
       }
       return a;
   }
       public JugadorDTO buscarJugadorPorColor(Color c){
            JugadorDTO a =null;
       try {
           INegocio myN = sLocator.getBusinessFacadeInstance();
             a=myN.buscarJugadorPorColor(c);
       } catch (Exception e) {
       }
       return a;
       }
       
       public String mostrarPuntos(){
           String cad="";
           try {
           INegocio myN = sLocator.getBusinessFacadeInstance();
             cad=myN.mostrarPuntos();
       } catch (Exception e) {
          
       }
            return cad;
        }
       public String mostrarGanador(){
           String cad="";
           try {
           INegocio myN = sLocator.getBusinessFacadeInstance();
             cad=myN.mostrarGanador();
       } catch (Exception e) {
          
       }
            return cad;
       }
}
