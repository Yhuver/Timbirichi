/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.business.basedatos.Derby.dto;

import java.awt.Color;

/**
 *
 * @author YHUVER
 */
public class JugadorDTO {
    
    private String nombre;
    private int puntos;
    private Color color;
    private String ip;

    public JugadorDTO(String nombre,Color color,String ip) {
        this.nombre = nombre;
        this.color=color;
        puntos=0;
        this.ip=ip;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public JugadorDTO(String nombre, int puntos, Color color, String ip) {
        this.nombre = nombre;
        this.puntos = puntos;
        this.color = color;
        this.ip = ip;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
}
