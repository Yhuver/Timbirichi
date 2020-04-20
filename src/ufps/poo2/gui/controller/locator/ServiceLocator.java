package ufps.poo2.gui.controller.locator;

import ufps.poo2.business.INegocio;
import ufps.poo2.business.Negocio;
import ufps.poo2.gui.Frame;

public class ServiceLocator {

	private static ServiceLocator instance;
    private INegocio negocio;

    private ServiceLocator(Frame f) {
        // Se obtiene por instanciacion directa.
        // Pero en otros casos podria incluir invocacion remota
        // o llamado a un servicio web.
       
    	negocio = new Negocio(f);
    }

    public static ServiceLocator getInstance(Frame fr) {
        if (instance == null) {
            
            instance = new ServiceLocator(fr);
            
        }
        return instance;
    }

    public INegocio getBusinessFacadeInstance() {
        return negocio;
    }
	
}
