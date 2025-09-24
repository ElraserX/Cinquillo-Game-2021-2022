/*
* Representa una carta, formada por un número y su palo correspondiente
*/

package es.uvigo.esei.aed1.core;

public class Carta{
    // Atributos.
    private final int numero;
    private final String palo;

    // Constructor.
    public Carta(int numero, String palo){
        this.numero = numero;
        this.palo = palo;
    }
    
    // Getters.
    public int getNumero(){
        return numero;
    }

    public String getPalo(){
        return palo;
    }

    // Método toString que devuelve una cadena que representa un objeto Carta en forma de texto.
    // CAMBIAR PARA QUE OCUPE MENOS.
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("Carta: ").append("\n");
        sb.append("Numero: ").append(numero).append(".\n");
        sb.append("Palo: ").append(palo).append(".\n");
        return sb.toString();
    }
}