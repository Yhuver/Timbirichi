package ufps.poo2.business;

import java.awt.Color;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import ufps.poo2.business.basedatos.DAOFactory;
import ufps.poo2.business.basedatos.Derby.dto.JugadaDTO;

import ufps.poo2.business.basedatos.Derby.dto.JugadorDTO;
import ufps.poo2.business.basedatos.interfaz.IJugadaDAO;
import ufps.poo2.cliente.Cliente;
import ufps.poo2.gui.Frame;
import ufps.poo2.gui.PanelJuego;
import ufps.poo2.servidor.Servidor;

public class Negocio implements INegocio {
private DAOFactory factory;
    private JugadorDTO[] myJ;
    private DAOFactory myF;
    private Servidor myS;
    private Cliente myC;
    private int Tablero;
    private JugadorDTO myJugador;
    private ArrayList<JugadaDTO> jugadas;
    private Frame fr;
    private int id=1000;
    private boolean estado;
    private int turno;
    
    public Negocio(Frame panel) {
        myF = new DAOFactory();
Tablero=0;
        myJ = new JugadorDTO[4];
        jugadas=new ArrayList<>();
        this.fr=panel;
        estado=false;
        turno=0;
        factory= new DAOFactory();
    }

    @Override
    public String registrarJugador(String nombre, String ip) throws Exception {
        String cad = "";
if(myJ[0]==null&&myC==null){
    myS=new Servidor(this);
    myS.inciarServidor();
    myJugador=new JugadorDTO(nombre, generarColor(0), ip);
}
        if (buscarJugadorNombre(nombre)) {
            System.out.println("---");
            cad = "Este jugador ya se ha registrado";
            return cad;
        }

        for (int i = 0; i < myJ.length; i++) {
            if (myJ[i] == null) {
                System.out.println("holaaa");
                myJ[i] = new JugadorDTO(nombre, generarColor(i), ip);
                cad = "Tu registro fue exitoso";
if(myC!=null){
    añadirMyJugador();
}
                break;
            }
        }
        return cad;
    }

    private void añadirMyJugador(){
        for (int i = 0; i < myJ.length; i++) {
            if(myJ[i]!=null){
                if(myJ[i].getNombre().equalsIgnoreCase(myJugador.getNombre())){
                    myJugador=myJ[i];
                    id=i;
                }
            }
        }
    }
    private boolean buscarJugadorNombre(String nombre) {
        boolean exito = false;

        for (int i = 0; i < myJ.length; i++) {
            if (myJ[i] != null) {
                if (myJ[i].getNombre().equalsIgnoreCase(nombre)) {
                    exito = true;
                    break;
                }
            }
        }

        return exito;
    }
    
    

    private Color generarColor(int numeroJugador) throws Exception {

        int a = numeroJugador;
        Color[] myC = new Color[4];
        myC[0] = Color.BLUE;
        myC[1] = Color.RED;
        myC[2] = Color.GREEN;
        myC[3] = Color.yellow;

        return myC[a];
    }

    @Override
    public String mostrarJugadoresConectados() {
        String cad = "";

        for (int i = 0; i < myJ.length; i++) {
            if (myJ[i] != null) {
                cad += "Nombre: " + myJ[i].getNombre() + " IP: " + myJ[i].getIp() + "\n";
            }
        }

        return cad;
    }

    public String enviarJugador(String nombre,String ip){
        String cad="Jugador Enviado";
        myC.enviarJugador(nombre, ip);
        myJugador=new JugadorDTO(nombre, null, ip);
        return cad;
    }
    
    public int getTablero(){
        return Tablero;
    }
    public void setTablero(int tablero){
        this.Tablero=tablero;
    }

    @Override
    public void iniciarCliente(String ip) throws Exception {
        myC=new Cliente(ip, this);
       myC.iniciarCliente();
    }

    @Override
    public void enviarTablero(int tablero) throws Exception {
        myS.enviarTablero(tablero);
    }
    @Override
    public void enviarListaJugadores(){
        myS.enviarJugadores(myJ);
    }
    public void recibirCoordenada(int num,String coordenada1,String coordenada2,int label1,int label2){
        Color c=myJ[num].getColor();
        
        fr.getMyJ().pintarLabel(coordenada1,coordenada2, c,label1,label2);
        
        
        if(myS!=null){
        enviarCoordenadaClientes(num, coordenada1, coordenada2, label1, label2);
    }}
    private void añadirJugada(int num,String coordenada1,String coordenada2){
        JugadaDTO j=new JugadaDTO();
        j.setCoordenada(coordenada1+"-"+coordenada2);
        j.setNumero(turno+1);
        turno++;
        j.setMyJ(myJ[num]);
        IJugadaDAO emp = factory.obtenerJugada(false);
    try {
        boolean exito = emp.insertarJugada(j);
    } catch (Exception ex) {
        Logger.getLogger(Negocio.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public void enviarCoordenadaServidor(String coordenada1,String coordenada2,int label1,int label2){
        if(myS!=null){
        myS.enviarCoordenada(0, coordenada1,coordenada2,label1,label2);
        añadirJugada(0, coordenada1, coordenada2);
        }
        else{
            myC.enviarCoordenada(id, coordenada1,coordenada2,label1,label2);
            añadirJugada(id, coordenada1, coordenada2);
        }
    }
    public void enviarCoordenadaClientes(int num,String coordenada1,String coordenada2,int label1,int label2){
        myS.enviarCoordenada(num, coordenada1,coordenada2,label1,label2);
        
    }

    public JugadorDTO getMyJugador() {
        return myJugador;
    }
    
    public void generarTurno(){
        if(id==turno){
            fr.getMyJ().eventos();
        }
        else{
            fr.getMyJ().bloquearJuego();
        }
    }
    
    public void recibirChatServidor(int num,String nombre,String mensaje){
        
        
        fr.getMyJ().añadirMensaje(nombre, mensaje);
        if(myS!=null){
        myS.enviarChatCliente(num, nombre, mensaje);
    }}
    
    public void enviarChat(String nombre,String mensaje){
        if(myC!=null){
            myC.enviarChatCliente(id, nombre, mensaje);
        }
        else{
            myS.enviarChatCliente(id, nombre, mensaje);
        }
    }

    public Servidor getMyS() {
        return myS;
    }

    public void setMyS(Servidor myS) {
        this.myS = myS;
    }

    public JugadorDTO buscarJugadorPorColor(Color c){
        JugadorDTO j=null;
                for (int i = 0; i < myJ.length; i++) {
            if(myJ[i]!=null&&myJ[i].getColor()==c){
                j=myJ[i];
                break;
            }
        }
                return j;
    }
    
    public String mostrarPuntos(){
        String cad="Puntos"+"\n";
        for (int i = 0; i < myJ.length; i++) {
            if(myJ[i]!=null){
                cad+=myJ[i].getNombre()+"    Puntos: "+myJ[i].getPuntos()+"\n";
            }
        }
        return cad;
    }
    
    public String mostrarGanador(){
        String cad="";
        int a=0;
        for (int i = 0; i < myJ.length; i++) {
            if(myJ[i]!=null){
                a++;
            }
        }
        int gana=(((this.fr.getMyJ().getMyPanel().length*this.fr.getMyJ().getMyPanel().length)/a)+1);
        for (int i = 0; i < myJ.length; i++) {
            if(myJ[i]!=null&&myJ[i].getPuntos()>=gana){
                cad="El ganador es "+myJ[i].getNombre();
            }
        }
        return cad;
    }
}
