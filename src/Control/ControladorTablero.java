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
    private Consumer<List<Tripleta>> casillaAbierta;
    
    public void index(int filas, int cols, int minas) {        
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
            List<Tripleta> existe = new LinkedList<>();
            int i = tablero.posicionTripleta(t);  
            existe.add(tablero.retornaTripleta(i));
            casillaAbierta.accept(existe);
            //mina
            if (tablero.retornaTripleta(i).esMina()) {
                eventoPartidaPerdida.accept(tablero.minas());
            }
            else{
                //numero
                tablero.retornaTripleta(i).setAbierta(true);
            }
            
        }
        else{
            // es cero
            List<Tripleta> cero = new LinkedList<>();
            cero.add(t);
            casillaAbierta.accept(cero);
            for(Tripleta casilla: tablero.obtenerCasillasAlrededor(x, y)){                
                if (!casilla.esAbierta()){
                    System.out.println("Control.ControladorTablero.seleccionarCasilla() valor =" + t.retornaValor());
                    seleccionarCasilla(casilla.retornaFila(), casilla.retornaColumna());
                }
            }
        }
        
    }
        
    public void setEventoPartidaPerdida(Consumer<List<Tripleta>> eventoPartidaPerdida) {
        this.eventoPartidaPerdida = eventoPartidaPerdida;
    }

    public void setCasillaAbierta(Consumer<List<Tripleta>> casillaAbierta) {
        this.casillaAbierta = casillaAbierta;
    }    
    
}
