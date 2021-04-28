/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import javax.swing.JOptionPane;

/**
 *
 * @author Sofito-Chan
 */
public class NegocioTablero {
    /**
     * Veriricar si un número esta entre el rango
     * de 8 a 16.
     * @param x Número a verificar
     * @return true si está en el rango, false de lo contrario
     */
    public boolean verificarX(int x){
        if(x < 8 || x > 16){
            return false;
        }        
        return true;
    }

    /**
     * Veriricar si un número está en el rango
     * entre 8 y 30.
     * @param y Número a verificar
     * @return true si está en el rango, false de lo contrario
     */
    public boolean verificarY(int y){
        if(y < 8 || y > 30){
            return false;
        }
        return true;
    }

    /**
     * Verificar si el número de minas es el permitido
     * @param m Número de minas
     * @return true si está en el rango, false de lo contrario
     */
    public boolean verificarMinas(int m){
        if(m < 10 || m > 100){
            return false;
        }
        return true;
    }
}
