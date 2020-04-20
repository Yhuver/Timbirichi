/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.business.basedatos.Derby.dto;

/**
 *
 * @author YHUVER
 */
public class JugadaDTO {
    
    private JugadorDTO myJ;
    private String coordenada;
    private int numero;

    public JugadaDTO() {
        numero=0;
    }

    public String getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(String cordenada) {
        this.coordenada = cordenada;
    }

    public JugadorDTO getMyJ() {
        return myJ;
    }

    public void setMyJ(JugadorDTO myJ) {
        this.myJ = myJ;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    
    
    
}
