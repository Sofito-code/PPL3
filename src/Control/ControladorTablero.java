/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.MatrizEnTripleta;
import Modelo.Tripleta;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Sofito-Chan
 */
public class ControladorTablero {

    private MatrizEnTripleta tablero;
    private Consumer<List<Tripleta>> eventoPartidaPerdida;
    private Consumer<List<Tripleta>> eventoPartidaGanada;
    private Consumer<List<Tripleta>> casillaAbierta;
    private int casillasAbiertas;
    private int filas;
    private int cols;
    private int minas;
    private List<Tripleta> casillasSeleccionadas;

    /**
     * Crea un Tablero de Juego que obtiene la distribución de una MatrizEnTripletas.
     * @param filas Filas de la matriz
     * @param cols Columnas de la matriz
     * @param minas Número de minas en la matriz
     */
    public void crear(int filas, int cols, int minas) {
        tablero = new MatrizEnTripleta(0);
        casillasAbiertas = 0;
        casillasSeleccionadas = new ArrayList<>();
        this.filas = filas;
        this.cols = cols;
        this.minas = minas;
        tablero = new MatrizEnTripleta(filas,cols,minas);
        tablero.generarMinas();
        tablero.completarMatriz();
    }

    /**
     * Este metodo se encarga de que pasa cuando se de clic a una casilla del tablero.
     * Cuando una casilla se le da clic puede ser una mina, una pista o un cero.
     * Si la casilla es un cero entonces se pregunta por las casillas alrededor para 
     * revelar las pistas cercanas, abriendose todas las que son cero.
     * @param x Posición de la fila
     * @param y Posición de la columna
     */
    public void seleccionarCasilla(int x, int y) {        
        Tripleta t = new Tripleta(x, y, 0);       
        if (tablero.existeTripleta(t)) {            
            int i = tablero.posicionTripleta(t);            
            if (tablero.retornaTripleta(i).esMina()) {
                //mina
                eventoPartidaPerdida.accept(tablero.minas());
            }
            else{
                //pista                 
                casillasSeleccionadas.add(tablero.retornaTripleta(i));                
            }            
        }
        else{
            //cero
            casillasSeleccionadas.add(t);           
            List<Tripleta> casilasAlrededor = tablero.obtenerCasillasAlrededor(x, y);            
            for(Tripleta casilla: casilasAlrededor){                 
                if (!casillasSeleccionadas.contains(casilla)){                    
                   seleccionarCasilla(casilla.retornaFila(), casilla.retornaColumna());
                }
            }  
        }
        casillasAbiertas = casillasSeleccionadas.size();           
        casillaAbierta.accept(casillasSeleccionadas); 
        if(partidaGanada()){
            eventoPartidaGanada.accept(tablero.minas());
        }
    }

    /**
     * 
     * @return Es true cuando las casillas abiertas sean mayor o igual al
     * numero de casillas sin minas.
     */
    boolean partidaGanada(){
        return casillasAbiertas>=(filas*cols)-minas;
    }

    /**
     * 
     * @param eventoPartidaPerdida Es una lista con las minas del tablero
     */
    public void setEventoPartidaPerdida(Consumer<List<Tripleta>> eventoPartidaPerdida) {
        this.eventoPartidaPerdida = eventoPartidaPerdida;
    }

    /**
     *
     * @param casillaAbierta Es una lista con las casillas por abrir.
     */
    public void setCasillaAbierta(Consumer<List<Tripleta>> casillaAbierta) {
        this.casillaAbierta = casillaAbierta;
    }

    /**
     *
     * @param eventoPartidaGanada Es una lista con las minas del tablero 
     * para que se muestren cuando el usuario gano el juego.
     */
    public void setEventoPartidaGanada(Consumer<List<Tripleta>> eventoPartidaGanada) {
        this.eventoPartidaGanada = eventoPartidaGanada;
    }   
}