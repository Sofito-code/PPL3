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
    public boolean verificarX(int x){
        if(x < 8 || x > 16){
            return false;
        }        
        return true;
    }
    
    public boolean verificarY(int y){
        if(y < 8 || y > 30){
            return false;
        }
        return true;
    }
    
    public boolean verificarMinas(int m){
        if(m < 10 || m > 100){
            return false;
        }
        return true;
    }
}
