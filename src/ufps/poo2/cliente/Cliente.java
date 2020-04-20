/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import ufps.poo2.business.Negocio;


/**
 *
 * @author YHUVER
 */
public class Cliente {

    private final int puerto = 5555;
    private String ip;
    private Socket myC;
    private DataOutputStream salida;
    private DataInputStream entrada;
    private Negocio myN;
    private HiloConexion myH;

    public Cliente(String ip, Negocio myN) {
        this.ip = ip;
        this.myN = myN;
        myH = new HiloConexion(this);

    }

    public void iniciarCliente() {
        try {
            myC = new Socket(ip, puerto);
            entrada = new DataInputStream(myC.getInputStream());
            salida = new DataOutputStream(myC.getOutputStream());
            System.out.println("Cliente inicializado");
        } catch (Exception e) {
            System.out.println("Error al iniciar cliente: " + e);
        }
    }

    public void recibirMensaje() {
        try {
            if (myC.isConnected() && entrada != null) {
                String cad = entrada.readUTF();
                if (cad.equalsIgnoreCase("Enviar Tablero")) {

                    int tablero = entrada.readInt();
                    myN.setTablero(tablero);
                } else if (cad.equalsIgnoreCase("Enviando Jugador")) {

                    boolean estado = entrada.readBoolean();
String nombre="";
String ip="";
                    while (estado) {
                        
                        nombre = entrada.readUTF();
                        ip = entrada.readUTF();
                        myN.registrarJugador(nombre, ip);
                        estado = entrada.readBoolean();
                    }
                }
                else if(cad.equalsIgnoreCase("Enviando Coordenada")){
                    
                    int num=entrada.readInt();
                    String coordenada1=entrada.readUTF();
                    String coordenada2=entrada.readUTF();
                    int label1=entrada.readInt();
                    int label2=entrada.readInt();
                    myN.recibirCoordenada(num,coordenada1,coordenada2,label1,label2);
                }
                else if(cad.equalsIgnoreCase("Enviando chat")){
                    String nombre=entrada.readUTF();
                    String mensaje=entrada.readUTF();
                    myN.recibirChatServidor(0,nombre, mensaje);
                }
            }

        } catch (Exception e) {
            System.out.println("Error recibiendo mensaje del cliente: " + e);
            
        }
    }

    public void enviarJugador(String nombre, String ip) {
        try {
            salida.writeUTF("Registrar Jugador");
            salida.writeUTF(nombre);
            salida.writeUTF(ip);
            myH.start();
        } catch (Exception e) {
        }
    }
    
    public void enviarCoordenada(int num,String coordenada1,String coordenada2,int label1,int label2){
        try {
            
                System.out.println("un cliente envia coordenada");
                    salida=new DataOutputStream(myC.getOutputStream());
                    salida.writeUTF("Enviando Coordenada");
                    salida.writeInt(num);
                    salida.writeUTF(coordenada1);
                    salida.writeUTF(coordenada2);
                    salida.writeInt(label1);
                    salida.writeInt(label2);
                }
            
         catch (Exception e) {
        }
    }
    public void enviarChatCliente(int num,String nombre,String mensaje){
        try {
            
                    salida=new DataOutputStream(myC.getOutputStream());
                    salida.writeUTF("Enviando chat");
                    salida.writeInt(num);
                    salida.writeUTF(nombre);
                    salida.writeUTF(mensaje);
                
        } catch (Exception e) {
        }
    }
}
