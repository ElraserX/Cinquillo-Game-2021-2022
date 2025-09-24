/*
* Representa la Mesa de juego. 
* Estructura: se utilizará un TAD adecuado. Piensa que hay 4 palos y de cada palo se pueden colocar cartas.
* Por cualquiera de los dos extremos (un array con 4 dobles colas parece lo más adecuado). La DobleCola se comentó en clase de teoría.
* Funcionalidad: Saber si es posible colocar una carta en la mesa, colocar una carta en la mesa, mostrar la mesa.
*/

package es.uvigo.esei.aed1.core;

import java.util.ArrayDeque;
import java.util.Deque;

public class Mesa{
    // Atributos.
    private final Deque<Carta>[] palos;
    
    // Constructor.
    public Mesa(){
        palos = new ArrayDeque[4];
        for(int i=0; i<palos.length; i++){
            palos[i] = new ArrayDeque<>();
        }
    }
    
    // "Getters".
    private int getIndicePalo(String palo){
        switch(palo){
            case "Oros":
                return 0;
            case "Copas":
                return 1;
            case "Espadas":
                return 2;
            case "Bastos":
                return 3;
            default:
                return -1; // Esto nunca debería pasar.
        }
    }
    
    private String getPalo(int indice){
        switch(indice){
            case 0:
                return "Oros";
            case 1:
                return "Copas";
            case 2:
                return "Espadas";
            case 3:
                return "Bastos";
            default:
                return ""; // Esto nunca debería pasar.
        }
    }
    
    // * MÉTODOS DE LA MESA *//
    
    // REVISAR.
    public boolean esPosibleColocar(Carta carta){
        int indicePalo=getIndicePalo(carta.getPalo());
        
        if(indicePalo==-1){
            return false; // El palo no existe en la mesa. Nunca debería pasar.
        }

        Deque<Carta> palo = palos[indicePalo];
        if(palo.isEmpty()){
            return carta.getNumero()==5; // Si la lista está vacía, solo se puede colocar un 5.
        }

        Carta ultimaCarta = palo.getLast();
        // Si la carta a colocar es la siguiente a la última o es la carta inmediatamente inferior, devuelve true, en caso contrario devuelve false.
        return ((carta.getNumero()==ultimaCarta.getNumero()+1)||(carta.getNumero()==ultimaCarta.getNumero()-1));
    }

    public void colocarCartaFinal(Carta carta){
        int indicePalo=getIndicePalo(carta.getPalo());
        palos[indicePalo].addLast(carta);
    }
    
    public void colocarCartaPrincipio(Carta carta){
        int indicePalo=getIndicePalo(carta.getPalo());
        palos[indicePalo].addFirst(carta);
    }
    
    // Método toString que devuelve una cadena que representa la Mesa en formato de texto.
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < 4; i++) {
            String palo = getPalo(i);
            sb.append(palo).append(": ");
            Deque<Carta> paloActual = palos[i];
            for (Carta carta : paloActual) {
                sb.append(carta.getNumero()).append(" ");
            }
        sb.append("\n");
    }
        
    return sb.toString();
    }
}

/*
Una salida ejemplo sería:
Oros: 1 2 3 4 
Copas: 
Espadas: 7 8 
Bastos: 5 6 
*/