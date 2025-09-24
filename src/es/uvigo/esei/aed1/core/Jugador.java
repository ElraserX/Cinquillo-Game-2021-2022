/*
 * Representa a un jugador, identificado por el nombre y sus cartas de la mano
 * Estructura mano: se utilizará un TAD adecuado
 * Funcionalidad: Añadir carta a la mano, convertir a String el objeto Jugador (toString)
 */

package es.uvigo.esei.aed1.core;

import java.util.ArrayList;

public class Jugador{
    // Atributos.
    private final String nombre;
    private ArrayList<Carta> mano;

    // Constructor.
    public Jugador(String nombre){
        this.nombre = nombre;
        mano = new ArrayList<>();
    }

    // Getters.
    public ArrayList<Carta> getMano(){
        return mano;
    }

    public String getNombre() {
        return nombre;
    }
    
    // Setters.
    public void setMano(ArrayList<Carta> mano){
        this.mano=mano;
    }
    
    // * MÉTODOS DEL JUGADOR *//
    
    // Devuelve la carta que se quiere jugar.
    public Carta jugarCartaMesa(int numero, String palo){
        for(Carta carta : mano){
            if(carta.getNumero()==numero&&carta.getPalo().equals(palo)){
                return carta;
            }
        }
        return null;
    }
    
    // Comprobamos si el numero que se quiere jugar está en la mano.
    public boolean comprobarNumeroCartaMano(int num){
        for(Carta carta : mano){
            if (carta.getNumero()==num){
                return true;
            }    
        }
        return false;
    }
    
    // Comprobamos si el palo que se quiere jugar está en la mano.
    public boolean comprobarPaloCartaMano(String palo){
        for(Carta carta : mano){
            if (carta.getPalo().equals(palo)){
                return true;
            }    
        }
        return false;
    }

    // Método toString que devuelve una cadena que representa un objeto Jugador en formato de texto con sus respectivos nombre y mano.
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("Jugador: ");
        sb.append(nombre).append(".\n");
        
        for (int i=0; i<mano.size(); i++){
            sb.append(mano.get(i)).append("\n");
        }

        return sb.toString();
    }
}