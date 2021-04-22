package Control;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Stack;

/**
 * MatrizEnTripleta dispersa representada en forma de tripletas.
 *
 * @author Zzatanas-Sofi
 */
public class MatrizEnTripleta {

    private Tripleta v[], aux[];
    private int numeroDeMinas;
    private String minas = "";

    public String retornaMinas() {
        return minas;
    }

    public MatrizEnTripleta(int numeroDeMinas){
        int val = numeroDeMinas*8;
        Tripleta t = new Tripleta(val,val,0);
        v = new Tripleta[val+2];
        v[0] = t;
    }

    public MatrizEnTripleta(int f, int c, int cantidadMinas) {
        int valor = f*c;
        numeroDeMinas = cantidadMinas;
        Tripleta t = new Tripleta(f, c, 0);
        v = new Tripleta[valor+2];
        v[0] = t;
    }

    public boolean esVacia() {
        Tripleta t = retornaTripleta(0);
        if ((Integer) t.retornaValor() == 0) {
            return true;
        }
        return false;
    }

    public int numeroFilas() {
        return v[0].retornaFila();
    }

    public int numeroColumnas() {
        return v[0].retornaColumna();
    }

    public int numeroTripletas() {
        return (int) v[0].retornaValor();
    }

    public Tripleta retornaTripleta(int i) {
        return v[i];
    }

    public void asignaNumeroTripletas(int datos) {
        System.out.println("Antiguo número de tripletas: " + numeroTripletas());
        System.out.println("Nuevo número de tripletas: " + datos);
        int antiguo = numeroTripletas();
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
        int p, f, c, i;
        int val;
        p = numeroTripletas();
        f = v[0].retornaFila();
        c = v[0].retornaColumna();
        val = (int) v[0].retornaValor();
        System.out.println("\n\nCabeza: " + Integer.toString(f) + ", " + Integer.toString(c) + ", " + Integer.toString(val));
        for (i = 1; i <= p; i++) {
            if (v[i] == null) {
                System.err.println("Nulo en i: " + i);
                return;
            }
            f = v[i].retornaFila();
            c = v[i].retornaColumna();
            val = (Integer) v[i].retornaValor();
            //Necesaria modificación para GUI.
            System.out.println(Integer.toString(f) + ", " + Integer.toString(c) + ", " + Integer.toString(val));
        }
    }

    public void asignaTripleta(Tripleta t, int i) {
        if (t == null) {
            System.err.println("Tripleta vacía.");
            return;
        }
        /*
         if (i > numeroTripletas()) {
         System.err.println("No existen tantas tripletas como: " + i + ", n = " + numeroTripletas());
         return;
         }
         */
        int c = t.retornaColumna();
        int f = t.retornaFila();
        Tripleta nt;
        if (t.retornaValor() instanceof Integer) {
            int val = (int) t.retornaValor();
            nt = new Tripleta(f, c, val);
        } else {
            nt = new Tripleta(f, c, (Integer) t.retornaValor());
        }
        v[i] = nt;
    }

    /**
     * Inserta la tripleta t en su lugar correspondiente. La matriz está
     * ordenada por filas y luego por columnas de menor a mayor.
     *
     * @param ti Tripleta a insertar.
     */
    public void insertaTripleta(Tripleta ti) {
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

    public void asignaTripleta2(Tripleta t, int x){
        v[x] = t;
    }

    private int comparar(int numero1, int numero2) {
        if (numero1 < numero2) {
            return -1;
        }
        if (numero1 > numero2) {
            return 1;
        }
        return 0;
    }

    private void inicializarMatriz(){
        Tripleta t = new Tripleta(1,1,-1);
        this.asignaTripleta(t,1);
        minas += "11";
    }

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
        System.out.println(minas);
    }

    public void mostrarTripletas(){
        int p = v.length;
        for(int i=1; i<=p;i++){
            System.out.print(v[i].retornaValor());
        }
    }

    public void muestraMatriz2() {
        int p, f, c, i,val;
        p = numeroTripletas();
        f = v[0].retornaFila();
        c = v[0].retornaColumna();
        val = (int) v[0].retornaValor();
        System.out.println("\n\nMatriz: " + Integer.toString(f) + " * " + Integer.toString(c) + " con " + Integer.toString(val) + " elementos.");
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

    public Stack apilarMinas(){
        Stack<String> pila = new Stack<String>();
        for(int i=0; i<minas.length();i++){
            String num = minas.substring(i,i+1);
            if(num.compareTo(",") == 0){
                continue;
            }else{
                pila.push(num);
            }
        }
        return pila;
    }

    public Tripleta crearTripletasAlrededor(int x, Tripleta t){
        Stack<String> pila = apilarMinas();
        int f,c,v;
        Tripleta tv;
        c = t.retornaColumna();
        f = t.retornaFila();
        switch (x){
            case 1: {
                if (f - 1 <= 0 || c - 1 <= 0 || f - 1 > numeroFilas() || c - 1 > numeroColumnas()) {
                    return new Tripleta();                                                      //Retorna tripleta vacía
                } else {
                    v = 1;
                    tv = new Tripleta(f - 1, c - 1, v);
                    return tv;
                }
            }
            case 2: {
                if (f - 1 <= 0 || c <= 0 || f - 1 > numeroFilas() || c > numeroColumnas()) {
                    return new Tripleta();
                } else {
                    v = 1;
                    tv = new Tripleta(f - 1, c, v);
                    return tv;
                }
            }
            case 3: {
                if (f - 1 <= 0 || c + 1 <= 0 || f - 1 > numeroFilas() || c + 1 > numeroColumnas()) {
                    return new Tripleta();
                } else {
                    v = 1;
                    tv = new Tripleta(f - 1, c + 1, v);
                    return tv;
                }
            }
            case 4: {
                if (f <= 0 || c + 1 <= 0 || f > numeroFilas() || c + 1 > numeroColumnas()) {
                    return new Tripleta();
                } else {
                    v = 1;
                    tv = new Tripleta(f, c + 1, v);
                    return tv;
                }
            }
            case 5: {
                if (f + 1 <= 0 || c + 1 <= 0 || f + 1 > numeroFilas() || c + 1 > numeroColumnas()) {
                    return new Tripleta();
                } else {
                    v = 1;
                    tv = new Tripleta(f + 1, c + 1, v);
                    return tv;
                }
            }
            case 6: {
                if (f + 1 <= 0 || c <= 0 || f + 1 > numeroFilas() || c > numeroColumnas()) {
                    return new Tripleta();
                } else {
                    v = 1;
                    tv = new Tripleta(f + 1, c, v);
                    return tv;
                }
            }
            case 7: {
                if (f + 1 <= 0 || c - 1 <= 0 || f + 1 > numeroFilas() || c - 1 > numeroColumnas()) {
                    return new Tripleta();
                } else {
                    v = 1;
                    tv = new Tripleta(f + 1, c - 1, v);
                    return tv;
                }
            }
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
                System.out.println("Este límite no existe.");
                return new Tripleta();
            }
        }
    }

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

}
