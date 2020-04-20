package ufps.poo2.business.basedatos.interfaz;

import java.util.ArrayList;
import ufps.poo2.business.basedatos.Derby.dto.JugadaDTO;




public interface IJugadaDAO {

	public boolean insertarJugada(JugadaDTO myJ)throws Exception;
        public ArrayList<String> listarJugadas()throws Exception;
        public boolean closeConn() throws Exception ;
	
}
