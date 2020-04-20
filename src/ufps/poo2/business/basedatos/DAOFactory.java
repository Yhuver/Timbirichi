package ufps.poo2.business.basedatos;

import ufps.poo2.business.basedatos.Derby.dao.DerbyJugadaDAO;
import ufps.poo2.business.basedatos.interfaz.IJugadaDAO;

public class DAOFactory {

	public IJugadaDAO obtenerJugada(boolean keepOpenConn){
		return new DerbyJugadaDAO(keepOpenConn);
	}
        
	
}
