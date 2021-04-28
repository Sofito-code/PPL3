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

    public MatrizEnTripleta getTablero() {
        return tablero;
    }
    
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
    
    boolean partidaGanada(){
        return casillasAbiertas>=(filas*cols)-minas;
    }
        
    public void setEventoPartidaPerdida(Consumer<List<Tripleta>> eventoPartidaPerdida) {
        this.eventoPartidaPerdida = eventoPartidaPerdida;
    }

    public void setCasillaAbierta(Consumer<List<Tripleta>> casillaAbierta) {
        this.casillaAbierta = casillaAbierta;
    }    

    public void setEventoPartidaGanada(Consumer<List<Tripleta>> eventoPartidaGanada) {
        this.eventoPartidaGanada = eventoPartidaGanada;
    }
    
    
}
