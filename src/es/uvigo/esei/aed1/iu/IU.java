/*
* Representa la interfaz del juego del Cinquillo-Oro, en este proyecto va a ser una E/S en modo texto.
* Se recomienda una implementación modular.
*/

package es.uvigo.esei.aed1.iu;

import es.uvigo.esei.aed1.core.Jugador;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import java.util.concurrent.ThreadLocalRandom;

public class IU{
    // Atributos.
    private final Scanner teclado;
    
    // Constructor.
    public IU(){
        teclado = new Scanner(System.in).useDelimiter("\r?\n");
    }

    public int leeNum(String msg){
        do{
            System.out.print(msg);

            try{
                return teclado.nextInt();
            } catch(InputMismatchException exc){
                teclado.next();
                mostrarMensaje("Entrada no válida. Debe ser un entero.");
            }
        } while(true);
    }

    public String leeString(String msg){
        System.out.print(msg);
        return teclado.next();
    }

    public String leeString(String msg, Object... args){
        System.out.printf(msg, args);
        return teclado.next();
    }

    public void mostrarMensaje(String msg){
        System.out.println(msg);
    }

    public void mostrarMensaje(String msg, Object... args){
        System.out.printf(msg, args);
    }
    
    public Jugador leeJugador() {
        String nombre;
        nombre = leeString("Nombre del jugador: ");
        return new Jugador(nombre);
    }
 
    public static int numeroAleatorio(int minimo, int maximo){
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }
    
    public int numJugadores(){
        int numJugadores;
        
        // Pedimos el número de jugadores que van a participar en la partida hasta que sean correctos.
        boolean aux=true;
        do{
            numJugadores = leeNum("\nIntroduzca el numero de Jugadores: ");
            if((numJugadores<3)||(numJugadores>4)){
                System.err.println("\n\tSolamente pueden jugar 3 o 4 Jugadores.");
            } else{
                aux=false;
            }
        } while(aux==true);
        
        return numJugadores;        
    }
    
    public List<Jugador> pedirDatosJugadores(int numJugadores){
        List<Jugador> jugadores = new ArrayList<>();
        
        for(int i=0; i<numJugadores; i++){
        mostrarMensaje("\nJugador [" + (i + 1) + "]: ");
        String nombre= leeString("Nombre: ");
        Jugador jugador = new Jugador(nombre);
        jugadores.add(jugador);
        }
        
        return jugadores;
    }
    
    public void mostrarJugador(Jugador jugador){

    }

    public void mostrarJugadores(Collection<Jugador> jugadores){ 
        
    }
    
    // Menú Incial que te permite ver las normas del CINQUILLO o comenzar el juego.
    public void menuInicial(){
        int op;
        boolean aux = false;
        
        do{
            op = leeNum("\n[0] -> Para visualizar las normas del CINQUILLO.\n[1] -> Para comenzar la partida.\n");
            if((op==0)||(op==1)){
                aux=true;
            } else{
                System.err.println("\n\tNo ha introducido ninguna opcion valida.");
            }
        } while(aux==false);
        
        
        switch(op){
            case 0:
                mostrarMensaje("\n\tTRAS MOSTRAR LAS NORMAS COMENZARA LA CONFIGURACIÓN INICIAL.");
                mostrarMensaje(normas());
                mostrarMensaje("\n----- CONFIGURACIÓN INICIAL -----");break;
            case 1:
                mostrarMensaje("\n");
                break;
            default:break;
        }
    }
    
    // Por acabar.
    private String normas(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nNORMAS CINQUILLO:");
        sb.append("\nNumero de Jugadores: 3 o 4.");
        sb.append("\nSolamente se puede empezar con un 5, de no empezar con un 5 se salta al turno del siguiente jugador.");
        sb.append("\nGana el que se quede antes sin cartas.");
        return sb.toString();
    }
}