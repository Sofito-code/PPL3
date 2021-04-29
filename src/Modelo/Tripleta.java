package Modelo;

/**
 * @author Sofia Vanegas Córdoba
 * @author Jovan Alejandro Zambrano Bello
 * @author Camilo Sampedro
 */
public class Tripleta {
    
    private int fila;
    private int columna;
    private Object valor;
    private boolean mina;

    /**
     * Método constructor. Inicializa una tripleta no vacía con los valores.
     *
     * @param f valor de la fila que contendrá la tripleta.
     * @param c valor de la columna que contendrá la tripleta.
     * @param val valor contenido dentro de la tripleta.
     */
    public Tripleta(int f, int c, Object val) {
        fila = f;
        columna = c;
        valor = val;
        mina = false;
    }

    /**
     * Método constructor. Inicializa una tripleta vacía con las variables de la clase en ceros.
     */
    public Tripleta(){
        fila=0;
        columna=0;
        valor= (int) 0;
        mina = false;
    }    

    /**
     * @return Retorna la fila de la tripleta.
     */
    public int retornaFila() {
        return fila;
    }

    /**
     * @return Retorna la columna de la tripleta.
     */
    public int retornaColumna() {
        return columna;
    }

    /**
     * @return Retorna el valor de la tripleta.
     */
    public Object retornaValor() {
        return valor;
    }

    /**
     * Asigna un nuevo valor a la fila de la tripleta.
     * @param f valor de la fila nueva de la tripleta.
     */
    public void asignaFila(int f) {
        fila = f;
    }

    /**
     * Asigna un nuevo valor a la columna de la tripleta.
     * @param c valor de la columna nueva de la tripleta.
     */
    public void asignaColumna(int c) {
        columna = c;
    }

    /**
     * Asigna un nuevo valor a la tripleta.
     * @param val valor de la fila nueva de la tripleta.
     */
    public void asignaValor(Object val) {
        valor = val;
    }

    public boolean esMina() {
        return mina;
    }

    public void asignaMina(boolean mina) {
        this.mina = mina;
    }

    public boolean esVacia(){
        return fila == 0 && columna==0;        
    }

    @Override
    public String toString() {
        return "Tripleta{" + "fila: " + fila + ", columna: " + columna + ", valor: " + valor + '}';
    }

    public void incrementarValor(){
        this.valor = (int)valor +1;
    }
}
