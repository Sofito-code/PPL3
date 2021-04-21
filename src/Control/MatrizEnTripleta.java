package Control;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * MatrizEnTripleta dispersa representada en forma de tripletas.
 *
 * @author camilo
 */
public class MatrizEnTripleta {

    private Tripleta v[], aux[];
    private int numeroDeMinas;
    private String minas = "";

    public MatrizEnTripleta(int f, int c, int cantidadMinas) {
        int valor = f*c;
        Tripleta t = new Tripleta(f, c, valor);
        numeroDeMinas = cantidadMinas;
        v = new Tripleta[valor+2];
        v[0] = t;
        inicializarMatriz();
        generarMinas();
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
        double val;
        p = numeroTripletas();
        f = v[0].retornaFila();
        c = v[0].retornaColumna();
        val = (int) v[0].retornaValor();
        System.out.println("\n\nCabeza: " + Integer.toString(f) + ", " + Integer.toString(c) + ", " + Double.toString(val));
        for (i = 1; i <= p; i++) {
            if (v[i] == null) {
                System.err.println("Nulo en i: " + i);
                return;
            }
            f = v[i].retornaFila();
            c = v[i].retornaColumna();
            val = (Double) v[i].retornaValor();
            //Necesaria modificación para GUI.
            System.out.println(Integer.toString(f) + ", " + Integer.toString(c) + ", " + Double.toString(val));
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
        if (t.retornaValor() instanceof Double) {
            double val = (Double) t.retornaValor();
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
        i = 0;
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
        while (j >= i) {
            v[j + 1] = v[j];
            j = j - 1;
        }
        v[i] = ti;
        asignaNumeroTripletas(datos);
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
        int p = 1;
        for(int i=1; i<numeroFilas();i++){
            for(int j=1;j<numeroColumnas();j++){
                Tripleta t = new Tripleta(i,j,0);
                this.asignaTripleta2(t,p);
                p++;
            }
        }
    }

    private void generarMinas(){
        int minasGeneradas = 0;
        while(minasGeneradas != numeroDeMinas){
            int posTemporalFila = (int) (Math.random()*numeroFilas());
            int posTemporalColumna = (int)(Math.random()*numeroColumnas());
            if(minas.contains(String.valueOf(posTemporalFila) + String.valueOf(posTemporalColumna))){
                continue;
            }else{
                minas += String.valueOf(posTemporalFila) + String.valueOf(posTemporalColumna) + ",";
                Tripleta t = new Tripleta(posTemporalFila,posTemporalColumna, -1);
                this.asignaTripleta(t,minasGeneradas+1);
                //this.insertaTripleta(t);
                minasGeneradas++;
            }

        }
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
}
