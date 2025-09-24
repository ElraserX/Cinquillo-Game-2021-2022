/*
* Representa la BARAJA ESPAÑOLA incluyendo el 8 y el 9, en total 48 cartas, divididas en 4 palos, valores de las cartas de 1 a 12. 
* Estructura: se utilizará un TAD adecuado.
* Funcionalidad: barajar las cartas, devolver la carta situada encima del montón de cartas
*/

package es.uvigo.esei.aed1.core;

import java.util.Random;
import java.util.Stack;

public class Baraja{
    // Atributos.
    private Stack<Carta> baraja;

    // Constructor. Crea una BARAJA usando un TAD Pila al que se le van añadiendo los palos con sus consiguientes cartas en orden.
    public Baraja(){
        System.out.println("");
        baraja = new Stack<>();

        for(int i=1; i<13; i++){
            Carta espada = new Carta(i, "Espadas");
            baraja.add(espada);
        }
        
        for(int i=1; i<13; i++){
            Carta bastos = new Carta(i, "Bastos");
            baraja.add(bastos);
        }
        
        for(int i=1; i<13; i++){
            Carta oros = new Carta(i, "Oros");
            baraja.add(oros);
        }
        
        for(int i=1; i<13; i++){
            Carta copas = new Carta(i, "Copas");
            baraja.add(copas);
        }
    }
    
    // * MÉTODOS DE LA BARAJA * //
    
    // Baraja usando un array "C" de tipo carta con tamaño "tam".
    public void barajar(){
        int n=0;
        int tam=baraja.size();
        Carta[] c = new Carta[tam];
        Random r = new Random();
        
        for(int i=0; i<tam; i++) {
            do{
                // Número aleatorio entre 0 y "tam".
                n=r.nextInt(tam);
            } while(c[n]!=null);
            // Mientras la posición aleatoria no este vacía.
            
            
            // Hemos salido del bucle, por lo tanto esta posición está vacía y podemos añadir la carta actual.
            c[n]=baraja.get(i);
        }
        // Aquí nuestra pila ya esta vacía.
        
        // Volvemos a llenar la pila, estando ahora barajada.
        for (int i=0; i<tam; i++){
            baraja.add(c[i]);
        }
    }

    
    public Carta devolverCarta(){
       return  baraja.pop();
    }
}