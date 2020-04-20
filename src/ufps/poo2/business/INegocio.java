package ufps.poo2.business;

import java.awt.Color;
import ufps.poo2.business.basedatos.Derby.dto.JugadorDTO;
import ufps.poo2.servidor.Servidor;



public interface INegocio {

	public String registrarJugador(String nombre, String ip)throws Exception;
        public String mostrarJugadoresConectados()throws Exception;
        public String enviarJugador(String nombre,String ip)throws Exception;
        public void iniciarCliente(String ip)throws Exception;
        public void enviarTablero(int tablero)throws Exception;
        public int getTablero()throws Exception;
        public void enviarListaJugadores()throws Exception;
       public void recibirCoordenada(int num,String coordenada1,String coordenada2,int label1,int label2)throws Exception;
       public void enviarCoordenadaServidor(String coordenada1,String coordenada2,int label1,int label2)throws Exception;
       public JugadorDTO getMyJugador() throws Exception;
        public void enviarChat(String nombre,String mensaje)throws Exception;
        public Servidor getMyS()throws Exception;
        public JugadorDTO buscarJugadorPorColor(Color c)throws Exception;
        public String mostrarPuntos()throws Exception;
        public String mostrarGanador()throws Exception;
        
}
