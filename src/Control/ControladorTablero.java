/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.MatrizEnTripleta;
import Modelo.Tripleta;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Sofito-Chan
 */
public class ControladorTablero {

    private MatrizEnTripleta tablero;
    private Consumer<List<Tripleta>> eventoPartidaPerdida;
    public void index(int filas, int cols, int minas) {        
        tablero = new MatrizEnTripleta(filas,cols,minas);
        tablero.generarMinas();
        tablero.completarMatriz();
    }

    public MatrizEnTripleta getTablero() {
        return tablero;
    }

    public void setTablero(MatrizEnTripleta tablero) {
        this.tablero = tablero;
    }
    
    public void seleccionarCasilla(int x, int y) {
        Tripleta t = new Tripleta(x, y, 0);
        List<Tripleta> tripletasConMina = new LinkedList<>();
        if (tablero.existeTripleta(t)) {
            int i = tablero.posicionTripleta(t);
            if (tablero.retornaTripleta(i).esMina()) {
                tripletasConMina = tablero.minas();
            }
            eventoPartidaPerdida.accept(tripletasConMina);
        }
    }

    public Consumer<List<Tripleta>> getEventoPartidaPerdida() {
        return eventoPartidaPerdida;
    }

    public void setEventoPartidaPerdida(Consumer<List<Tripleta>> eventoPartidaPerdida) {
        this.eventoPartidaPerdida = eventoPartidaPerdida;
    }
    
}
