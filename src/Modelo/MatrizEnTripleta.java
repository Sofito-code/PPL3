package Modelo;

import java.util.LinkedList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * MatrizEnTripleta dispersa representada en forma de tripletas.
 * Esta clase se realizaron algunos métodos para operarar con matrices en tripletas.
 *
 * @author Sofia Vanegas Córdoba
 * @author Jovan Alejandro Zambrano Bello
 */
public class MatrizEnTripleta {

    private Tripleta v[], aux[];
    private int numeroDeMinas;
    private String minas = ""; 
    private String abiertas = "";

    /**
     * Constructor auxiliar vacío. Este método se usa más adelante
     * para configurar las casillas alrededor de una mina.
     * @param numeroDeMinas - Parametro para crear el tamaño del arreglo de tripletas
     */
    public MatrizEnTripleta(int numeroDeMinas){
        int val = numeroDeMinas;
        Tripleta t = new Tripleta(val,val,0);
        v = new Tripleta[val+2];
        v[0] = t;
    }

    /**
     * Constructor principal. Este es el constructor en el cual se
     * creara el tablero principal del juego.
     * @param f Número de filas
     * @param c Número de columnas
     * @param cantidadMinas - Cantidad de minas por juego
     */
    public MatrizEnTripleta(int f, int c, int cantidadMinas) {
        int valor = f*c;
        numeroDeMinas = cantidadMinas;
        Tripleta t = new Tripleta(f, c, 0);
        v = new Tripleta[valor+2];
        v[0] = t;
    }

    /**
     * Méotodo para verificar si una matriz en tripleta esta vacía o no.
     * @return true, si está vacía , falso de lo contrario
     */
    public boolean esVacia() {
        Tripleta t = retornaTripleta(0);
        if ((Integer) t.retornaValor() == 0) {
            return true;
        }
        return false;
    }

    /**
     * @return Numero de Filas
     */
    public int numeroFilas() {
        return v[0].retornaFila();
    }

    /**
     * @return Numero de columnas
     */
    public int numeroColumnas() {
        return v[0].retornaColumna();
    }

    /**
     * @return Número de tripletas
     */
    public int numeroTripletas() {
        return (int) v[0].retornaValor();
    }

    /**
     * @param i Posición
     * @return Tripleta en la posición i
     */
    public Tripleta retornaTripleta(int i) {
        return v[i];
    }

    public Tripleta cabeza(){
        return v[0];
    }

    /**
     * @return Asigna un número a la cantidad de tripletas de la matriz
     */
    private void asignaNumeroTripletas(int datos) {
        aux = v;
        Tripleta ti, tj;
        ti = aux[0];
        tj = new Tripleta(ti.retornaFila(), ti.retornaColumna(), datos);
        v = new Tripleta[datos + 2];
        v[0] = tj;
        for (int i = 1; i < datos; i++) {
            v[i] = aux[i];
        }

    }

    /**
     * Muestra todos los elementos de la matriz.
     */
    public void muestraMatriz() {
        int p, f, c, i,val;
        p = numeroTripletas();
        f = v[0].retornaFila();
        c = v[0].retornaColumna();
        val = (int) v[0].retornaValor();
        System.out.println("\nMatriz: " + Integer.toString(f) + " * " + Integer.toString(c) + " con " + Integer.toString(val) + " elementos.");
        for (i = 1; i <= p; i++) {
            if (v[i] == null) {
                System.err.println("Nulo en i: " + i);
                return;
            }
            f = v[i].retornaFila();
            c = v[i].retornaColumna();
            val = (int) v[i].retornaValor();
            //Necesaria modificación para GUI.
            System.out.println(Integer.toString(f) + ", " + Integer.toString(c) + ", " + Integer.toString(val));
        }
    }

    /**
     * Inserta la tripleta t en su lugar correspondiente. La matriz está
     * ordenada por filas y luego por columnas de menor a mayor.
     *
     * @param ti Tripleta a insertar.
     */
    private void insertaTripleta(Tripleta ti) {
        int i, j, datos;
        Tripleta t, tx;
        tx = retornaTripleta(0);
        datos = (Integer) tx.retornaValor();
        i = 1;
        t = retornaTripleta(i);
        while (i <= datos && t.retornaFila() < ti.retornaFila()) {
            i = i + 1;
            t = retornaTripleta(i);
        }
        while (i <= datos && t.retornaFila() == ti.retornaFila() && t.retornaColumna() < ti.retornaColumna()) {
            i = i + 1;
            t = retornaTripleta(i);
        }
        j = datos;
        datos = datos + 1;
        asignaNumeroTripletas(datos);
        while (j >= i) {
            v[j + 1] = v[j];
            j = j - 1;
        }
        v[i] = ti;
        
    }

    /**
     * Genera las tripletas correspondientes a las minas del tablero
     */
    public void generarMinas(){
        int minasGeneradas = 0;
        Tripleta t;
        while(minasGeneradas != numeroDeMinas){
            int posTemporalFila = (int) (Math.random()*numeroFilas()+1);
            int posTemporalColumna = (int)(Math.random()*numeroColumnas()+1);
            if(minas.contains(String.valueOf(posTemporalFila) + String.valueOf(posTemporalColumna+ ","))){
                continue;
            }else{
                minas += String.valueOf(posTemporalFila) + String.valueOf(posTemporalColumna) + ",";
                t = new Tripleta(posTemporalFila,posTemporalColumna, -1);
                t.asignaMina(true);
                this.insertaTripleta(t);
                minasGeneradas++;
            }
        }
    }

    /**
     * Por cada tripleta que contenga una mina, se crearan sus casillas alrededor
     * con su número correspondiente y se retornará. Cuando las casillas estan por
     * fuera de los límites , se retorna una tripleta vacía.
     * @param x Indicador de la posición
     * @param t Tripleta que contiene una mina
     * @return tripleta nueva que va alrededor de una mina
     */
    public Tripleta crearTripletasAlrededor(int x, Tripleta t){       
        int f,c,v;
        Tripleta tv;
        c = t.retornaColumna();
        f = t.retornaFila();
        //Se hace recorrido en sentido de agujas del reloj. Se empieza desde la posición derecha superior hasta la izquierda.
        switch (x){
            /*
            Izquierda superior
             */
            case 1: {
                if (f - 1 <= 0 || c - 1 <= 0 || f - 1 > numeroFilas() || c - 1 > numeroColumnas()) {
                    return new Tripleta();     //Retorna tripleta vacía
                } else {
                    v = 1;
                    tv = new Tripleta(f - 1, c - 1, v);
                    return tv;
                }
            }
            /*
            Arriba
             */
            case 2: {
                if (f - 1 <= 0 || c <= 0 || f - 1 > numeroFilas() || c > numeroColumnas()) {
                    return new Tripleta();
                } else {
                    v = 1;
                    tv = new Tripleta(f - 1, c, v);
                    return tv;
                }
            }
            /*
            Derecha superior
             */
            case 3: {
                if (f - 1 <= 0 || c + 1 <= 0 || f - 1 > numeroFilas() || c + 1 > numeroColumnas()) {
                    return new Tripleta();
                } else {
                    v = 1;
                    tv = new Tripleta(f - 1, c + 1, v);
                    return tv;
                }
            }
            /*
            Derecha
             */
            case 4: {
                if (f <= 0 || c + 1 <= 0 || f > numeroFilas() || c + 1 > numeroColumnas()) {
                    return new Tripleta();
                } else {
                    v = 1;
                    tv = new Tripleta(f, c + 1, v);
                    return tv;
                }
            }
            /*
            Derecha inferior
             */
            case 5: {
                if (f + 1 <= 0 || c + 1 <= 0 || f + 1 > numeroFilas() || c + 1 > numeroColumnas()) {
                    return new Tripleta();
                } else {
                    v = 1;
                    tv = new Tripleta(f + 1, c + 1, v);
                    return tv;
                }
            }
            /*
            Abajo
             */
            case 6: {
                if (f + 1 <= 0 || c <= 0 || f + 1 > numeroFilas() || c > numeroColumnas()) {
                    return new Tripleta();
                } else {
                    v = 1;
                    tv = new Tripleta(f + 1, c, v);
                    return tv;
                }
            }
            /*
            Izquierda inferior
             */
            case 7: {
                if (f + 1 <= 0 || c - 1 <= 0 || f + 1 > numeroFilas() || c - 1 > numeroColumnas()) {
                    return new Tripleta();
                } else {
                    v = 1;
                    tv = new Tripleta(f + 1, c - 1, v);
                    return tv;
                }
            }
            /*
            Izquierda
             */
            case 8: {
                if (f <= 0 || c - 1 <= 0 || f > numeroFilas() || c - 1 > numeroColumnas()) {
                    return new Tripleta();
                } else {
                    v = 1;
                    tv = new Tripleta(f, c - 1, v);
                    return tv;
                }
            }
            default: {
                System.out.println("Este límite no existe en el tablero.");
                return new Tripleta();
            }
        }
    }

    /**
     * Verificar si la tripleta t existe en una matríz
     * @param t Tripleta a verificar
     * @return true si está, false de lo contrario
     */
    public boolean existeTripleta(Tripleta t){
        int f,c;
        for(int i=1; i<=this.numeroTripletas(); i++){
            f = t.retornaFila();
            c = t.retornaColumna();
            if(f == v[i].retornaFila() && c == v[i].retornaColumna()){
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna la posición en la que está la matriz
     * @param t Tripleta a buscar
     * @return Posición
     */
    public int posicionTripleta(Tripleta t){
        if( t != null){
            int f,c;
            for(int i=1; i<=this.numeroTripletas(); i++){
                f = t.retornaFila();
                c = t.retornaColumna();
                if(f == v[i].retornaFila() && c == v[i].retornaColumna()){
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * Completa la matriz con todas las tripletas.
     */
    public void completarMatriz(){
        MatrizEnTripleta a = new MatrizEnTripleta(numeroDeMinas);
        for(int i=1; i<=numeroTripletas();i++){
            if(v[i].esMina()){
                for(int j=1; j<=8;j++){
                    Tripleta t = crearTripletasAlrededor(j,v[i]);
                    if(!t.esVacia()){
                        if(!a.esVacia()){
                            if(a.existeTripleta(t)){
                                int k = a.posicionTripleta(t);
                                a.retornaTripleta(k).incrementarValor();
                            }else{
                                a.insertaTripleta(t);
                            }
                        }
                        else{
                            a.insertaTripleta(t);
                        }
                    }
                }
            }
        }
        for(int l=1;l<=a.numeroTripletas();l++){
            Tripleta ta = a.retornaTripleta(l);
            if(!this.existeTripleta(ta)){
                this.insertaTripleta(ta);
            }
        }
    }   

    public int getNumeroDeMinas() {
        return numeroDeMinas;
    }

    /**
     *
     * @return
     */
    public List<Tripleta> minas(){
        List<Tripleta> tripletasConMina = new LinkedList<>();
        for(int i = 1; i <= this.numeroTripletas(); i++){
            if (this.retornaTripleta(i).esMina()) {
                tripletasConMina.add(this.retornaTripleta(i));                
            }
        }
        return tripletasConMina;
    }

    /**
     *
     * @param posFila
     * @param posColumna
     * @return
     */
    public List<Tripleta> obtenerCasillasAlrededor(int posFila, int posColumna) {
        List<Tripleta> listaCasillas = new LinkedList<>();
        if(!abiertas.contains(posFila+""+posColumna+",")){
            abiertas += posFila+""+posColumna+",";
        }
        Tripleta t;        
        for (int i = 1; i <= 8; i++) {
            int tmpPosFila = posFila;
            int tmpPosColumna = posColumna;
            switch (i) {
                case 1:
                    tmpPosFila--;
                    tmpPosColumna--;
                    break; //Izquierda Arriba
                case 2:
                    tmpPosFila--;
                    break; //Arriba
                case 3:
                    tmpPosFila--;
                    tmpPosColumna++;
                    break; //Arriba Derecha                    
                case 4:
                    tmpPosColumna++;
                    break; //Derecha
                case 5:
                    tmpPosColumna++;
                    tmpPosFila++;
                    break; //Derecha Abajo
                case 6:
                    tmpPosFila++;
                    break; //Abajo
                case 7:
                    tmpPosFila++;
                    tmpPosColumna--;
                    break; //Abajo Izquierda
                case 8:
                    tmpPosColumna--;
                    break; //Izquierda
            }
            
            if ((tmpPosFila >= 1) && (tmpPosFila <= this.numeroFilas()) && (tmpPosColumna >= 1) && (tmpPosColumna <= this.numeroColumnas())) {
                boolean dentro = true;
                for(int x = 1; x <= this.numeroTripletas(); x++){                    
                    if(tmpPosFila == v[x].retornaFila() && tmpPosColumna == v[x].retornaColumna()){ 
                        if (!abiertas.contains(String.valueOf(tmpPosFila) + String.valueOf(tmpPosColumna + ","))) {
                            abiertas += String.valueOf(tmpPosFila) + String.valueOf(tmpPosColumna) + ",";
                            listaCasillas.add(v[x]);
                        }
                        dentro = false;
                        break;
                    }
                }
                if(dentro){
                    if(!abiertas.contains(String.valueOf(tmpPosFila) + String.valueOf(tmpPosColumna+ ","))){                    
                        abiertas += String.valueOf(tmpPosFila) + String.valueOf(tmpPosColumna) + ",";
                        t = new Tripleta(tmpPosFila, tmpPosColumna, 0);
                        listaCasillas.add(t);  
                    }                
                }
            }
            
        }        
        return listaCasillas;
    }
    
//    public void imprimirMatrizMinas(){
//        for(int i =1; i <= this.numeroTripletas(); i++){
//            v[i]
//        }
//    }

}
