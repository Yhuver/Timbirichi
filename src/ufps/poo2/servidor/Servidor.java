/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.servidor;

import com.sun.corba.se.impl.io.IIOPOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import ufps.poo2.business.Negocio;
import ufps.poo2.business.basedatos.Derby.dto.JugadorDTO;

/**
 *
 * @author YHUVER
 */
public class Servidor {
    private final int puerto=5555;
    private Socket[] myC;
    private ServerSocket myS;
    private DataOutputStream salida;
    private Negocio myN;
    private HiloConexion[] myH;
    private HiloConector hilo;

    public Servidor(Negocio myN) {
        this.myN = myN;
        this.myH=new HiloConexion[3];
        myC=new Socket[3];
    }
    
    public void inciarServidor(){
        hilo=new HiloConector(this);
        hilo.start();
    }
    
    public void cargandoServidor() {
        System.out.println("escuchando...");
        try {
            myS = new ServerSocket(5555);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void esperandoCliente(){
        try {
            for (int i = 0; i < myC.length; i++) {
                if(myC[i]==null){
            
             myC[i] = myS.accept();
             this.crearHilo(myC[i]);
            
            System.out.println("Servidor Aceptado");
            
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void crearHilo(Socket myC){
        for (int i = 0; i < myH.length; i++) {
            if(myH[i]==null){
                myH[i]= new HiloConexion(myC, this, i+1);
                myH[i].start();
                break;
            }
        }
    }
    
    public void recibirMensajes(Socket myC,int identificador){
        try {
            DataInputStream entrada=new DataInputStream(myC.getInputStream());
            String cad=entrada.readUTF();
            if(cad.equalsIgnoreCase("Registrar Jugador")){
                
                String nombre=entrada.readUTF();
                String ip=entrada.readUTF();
                myN.registrarJugador(nombre, ip);
            }
            else if(cad.equalsIgnoreCase("Enviando Coordenada")){
                int num=entrada.readInt();
                    String coordenada1=entrada.readUTF();
                    String coordenada2=entrada.readUTF();
                    int label=entrada.readInt();
                    int labe2=entrada.readInt();
                    myN.recibirCoordenada(num, coordenada1, coordenada2, label, labe2);
                    System.out.println("recibiendo coordenada de: "+identificador);
            }
            else if(cad.equalsIgnoreCase("Enviando chat")){
                int num=entrada.readInt();
                    String nombre=entrada.readUTF();
                    String mensaje=entrada.readUTF();
                    myN.recibirChatServidor(num, nombre, mensaje);
                }
        } catch (Exception e) {
        }
    }
    
    public void enviarTablero(int tablero){
        try {
            for(int i=0;i<this.myH.length;i++){
                if(myH[i]!=null){
            salida=new DataOutputStream(myH[i].getMyC().getOutputStream());
            salida.writeUTF("Enviar Tablero");
            salida.writeInt(tablero);
        } }}catch (Exception e) {
        }
    }
    
    public void enviarJugadores(JugadorDTO[] myJ){
        try {
             for(int i=0;i<this.myH.length;i++){
                if(myH[i]!=null){
            salida=new DataOutputStream(myH[i].getMyC().getOutputStream());
            salida.writeUTF("Enviando Jugador");
                    
                    for (int j = 0; j < myJ.length; j++) {
                        
                        if(myJ[j]!=null){
                            
                            salida.writeBoolean(true);
                            salida.writeUTF(myJ[j].getNombre());
                            salida.writeUTF(myJ[j].getIp());
                        }
                        else{
                           salida.writeBoolean(false);
                           break;
                        }
                    }
            
            
        } }
        } catch (Exception e) {
        }
    }
    
    public void enviarCoordenada(int num,String coordenada1,String coordenada2,int label1,int label2){
        try {
            for (int i = 0; i < myH.length; i++) {
                if(myH[i]!=null&&myH[i].getIdentificador()!=num){
                    salida=new DataOutputStream(myH[i].getMyC().getOutputStream());
                    salida.writeUTF("Enviando Coordenada");
                    salida.writeInt(num);
                    salida.writeUTF(coordenada1);
                    salida.writeUTF(coordenada2);
                    salida.writeInt(label1);
                    salida.writeInt(label2);
                }
            }
        } catch (Exception e) {
        }
    }
    
    public void enviarChatCliente(int num,String nombre,String mensaje){
        try {
            for (int i = 0; i < myH.length; i++) {
                if(myH[i]!=null&&myH[i].getIdentificador()!=num){
                    salida=new DataOutputStream(myH[i].getMyC().getOutputStream());
                    salida.writeUTF("Enviando chat");
                    salida.writeUTF(nombre);
                    salida.writeUTF(mensaje);
                }
            }
        } catch (Exception e) {
        }
    }
    
}
