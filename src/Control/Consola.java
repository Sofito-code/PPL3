/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

/**
 *
 * @author Sofito-Chan
 */
public class Consola {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MatrizEnTripleta tablero = new MatrizEnTripleta(4,4,2);
//        Tripleta sofi = new  Tripleta(5,1,3);
//        tablero.insertaTripleta(sofi);
        tablero.muestraMatriz2();

    }
    
}
