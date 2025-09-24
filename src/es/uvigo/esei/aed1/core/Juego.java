/*
* Representa el juego del Cinquillo-Oro, con sus reglas (definidas en el documento de la primera entrega).
* Se recomienda una implementación modular.
*/

package es.uvigo.esei.aed1.core;

import es.uvigo.esei.aed1.iu.IU;
import java.util.ArrayList;
import java.util.List;

public class Juego{
    // Atributos.
    private final IU iu;
    private final Mesa mesa = new Mesa();
    private final Baraja baraja = new Baraja();
    private List<Jugador> jugadores = null;

    // Constructor.
    public Juego(IU iu){
        this.iu = iu;
    }

    public void jugar(){
        // Mensaje inicial de Bienvenida.
        iu.mostrarMensaje("\tBienvenidos a CINQUILLO");
        
        iu.menuInicial();

        /*
        CONFIGURACIÓN INICIAL DEL JUEGO.
        - Se piden los datos de los jugadores.
        - Se baraja.
        - Se reparte cartas.
        - Se elige el jugador inicial aleatoriamente.
        - Se crea la mesa vacía.
        */
        
        // Pedimos el número de jugadores.
        int numJugadores = iu.numJugadores();
        
        // Pedimos la información de los jugadores y los vamos añadiendo a la Lista "jugadores".
        jugadores = iu.pedirDatosJugadores(numJugadores);
        
        // Creamos una nueva Baraja "miBaraja" que barajamos.
        baraja.barajar();
 
        // Para cada jugador se crea una Lista "manoActual" a la que se añaden las cartas que le corresponden en la partida.
        for(Jugador j : jugadores){
            ArrayList<Carta> manoActual = new ArrayList<>();
            
            for(int i=0; i<(48/jugadores.size()); i++){                
                manoActual.add(baraja.devolverCarta());
            }
            
            // Se define "manoActual" como la Mano del jugador actual.
            j.setMano(manoActual);
        }
        
        // ~ EMPIEZA EL JUEGO ~ //
        
        boolean finPartida=false;
        // Posición aleatoria que también sirve para determinar el jugador inicial de forma aleatoria.
        int turno=(int)(Math.random()*numJugadores);
        do{
            iu.mostrarMensaje("TURNO DEL JUGADOR ", jugadores.get(turno).getNombre());
            iu.mostrarMensaje(jugadores.get(turno).toString());
            iu.mostrarMensaje(mesa.toString());
            colocarCartaMesa(jugadores.get(turno));
                
            iu.mostrarMensaje("---------------------------");
                
            finPartida=comprobarVictoria(jugadores.get(turno));
               
            // Para que una vez se llegue al final del array vuelva a la posición 0.
            if ((turno + 1)== numJugadores){
                turno = -1;  
            }
            
            turno++;
        } while(finPartida==false);
    }
    
    // Hay que comprobar si el Jugador se ha quedado sin cartas en la mano para la condición de victoria.
    private boolean comprobarVictoria(Jugador jugador){
        if(jugador.getMano()==null){
            System.out.println("\n\n\t\tGANADOR ---> " + jugador.toString());
            return true;
        }else{
            return false;
        }
    }
    
    // Por acabar.
    private void colocarCartaMesa(Jugador jugador){
        boolean aux=false;
        int num;
        String palo;
        
        // Comprobación número.
        do{
            num = iu.leeNum("Introduzca el numero de la carta que quiere jugar: ");
            if(jugador.comprobarNumeroCartaMano(num)){
                aux=true;
            } else{
                System.err.println("\n\tNo posee ninguna carta en su mano con el numero introducido. Revise sus cartas e intentelo de nuevo.");
            }   
        } while(aux==false);
        
        // Comprobación palo.
        do{
            palo = iu.leeString("Intoduzca el palo de la carta que quiere jugar (la primera letra en mayuscula): ");
            if(jugador.comprobarPaloCartaMano(palo)){
                aux=false;
            } else{
                System.err.println("\n\tNo posee ninguna carta en su mano con el palo introducido. Revise sus cartas e intentelo de nuevo.");
            }   
        } while(aux==true);
        
        // Una vez llegado aquí significa que sí que tiene la carta en su mano. Ahora hay que comprobar si se puede jugar en la mesa.
        // REVISAR. QUITAR EL SWITCH.
        if(mesa.esPosibleColocar(jugador.jugarCartaMesa(num, palo))){
            int op=-1;
            do{
                op= iu.leeNum("Arriba [0]\nAbajo [1]\n");
                if((op==0)||(op==1)){
                    switch(op){
                        case 0:
                            mesa.colocarCartaPrincipio(jugador.jugarCartaMesa(num, palo));
                            jugador.getMano().remove(jugador.jugarCartaMesa(num, palo));
                            break;
                        case 1:
                            mesa.colocarCartaFinal(jugador.jugarCartaMesa(num, palo));
                            jugador.getMano().remove(jugador.jugarCartaMesa(num, palo));
                            break;
                        default:break;
                    }
                } else{
                    System.err.println("No ha introducido una opcion valida.");
                }

            } while(op<0||op>1);
        } else{
            System.err.println("No es posible colocar dicha carta en la mesa.");
        } 
    }
}

/*
Se imprime por pantalla la información de cada jugador.
for(Jugador j : jugadores){
System.out.println(j.toString());
}
*/