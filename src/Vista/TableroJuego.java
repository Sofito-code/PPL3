/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import Control.ControladorTablero;
import Modelo.Tripleta;
import java.util.List;
import java.util.function.Consumer;
import Negocio.NegocioTablero;
/**
 *
 * @author Sofito-Chan
 */
public class TableroJuego extends javax.swing.JFrame {

    int filas=8;
    int cols=8;
    int minas=10;
    JButton[][] botonesTablero;
    ControladorTablero tableroBuscaminas = new ControladorTablero();
    NegocioTablero negocio;
    /**
     * Creates new form Controles
     */
    public TableroJuego() {
        initComponents();
        juegoNuevo();
    }
    
    private void juegoNuevo(){
        descargarControles();        
        cargarControles(filas, cols);
        crearTablero();
        repaint();
        
    }

    public void crearTablero(){
        tableroBuscaminas.crear(filas, cols, minas);
        tableroBuscaminas.setEventoPartidaPerdida(new Consumer<List<Tripleta>>() {
            @Override
            public void accept(List<Tripleta> t) {
                for(Tripleta casilla: t){
                    botonesTablero[casilla.retornaFila()-1][casilla.retornaColumna()-1].setText("*");
                } 
                for (int i = 0; i < botonesTablero.length; i++) {
                    for (int j = 0; j < botonesTablero[i].length; j++) {
                        if(botonesTablero[i][j].isEnabled()){
                            botonesTablero[i][j].setEnabled(false);
                        }                        
                    }
                }
            }
            
        });
        tableroBuscaminas.setEventoPartidaGanada(new Consumer<List<Tripleta>>() {
            @Override
            public void accept(List<Tripleta> t) {
                for(Tripleta casilla: t){
                    botonesTablero[casilla.retornaFila()-1][casilla.retornaColumna()-1].setText("Ü");
                }                
            }
        });
        tableroBuscaminas.setCasillaAbierta(new Consumer<List<Tripleta>>() {
            @Override
            public void accept(List<Tripleta> ti) {
                for (Tripleta t : ti) {
                    if (t.retornaFila() == 0 || t.retornaColumna() == 0) {
                        JOptionPane.showMessageDialog(rootPane, "Algo anda mal...");
                        break;
                    }
                    if (botonesTablero[t.retornaFila() - 1][t.retornaColumna() - 1].isEnabled()) {
                        botonesTablero[t.retornaFila() - 1][t.retornaColumna() - 1].setEnabled(false);
                    }
                    int valor = (int) t.retornaValor();
                    if (0 != (int) t.retornaValor()) {
                        botonesTablero[t.retornaFila() - 1][t.retornaColumna() - 1].setText(t.retornaValor() + "");
                    } else {
                        botonesTablero[t.retornaFila() - 1][t.retornaColumna() - 1].setText("");
                    }
                }         
            }
        });
        tableroBuscaminas.getTablero().muestraMatriz();
    }
    
    public void cargarControles(int filas, int cols){
        int posXInicial = 5;
        int posYInicial = 5;
        botonesTablero = new JButton[filas][cols];
        for (int i = 0; i < botonesTablero.length; i++) {
            for (int j = 0; j < botonesTablero[i].length; j++) {
                botonesTablero[i][j] = new JButton();
                botonesTablero[i][j].setName((i+1) + "," + (j+1));
                botonesTablero[i][j].setText((i+1) + "," + (j+1));
                botonesTablero[i][j].setBorder(null);
                if(i ==0 && j==0){
                    botonesTablero[i][j].setBounds(posXInicial, posYInicial, 30, 30);
                }
                else if(i==0 && j !=0 ){
                    botonesTablero[i][j].setBounds(
                            botonesTablero[i][j-1].getX()+botonesTablero[i][j-1].getWidth(), posYInicial, 30, 30);
                }
                else{
                    botonesTablero[i][j].setBounds(
                            botonesTablero[i-1][j].getX(),
                            botonesTablero[i-1][j].getY() + botonesTablero[i-1][j].getHeight(), 30, 30);            
                }
                botonesTablero[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        btnClick(e);
                    }
                });
                MinasPane.add(botonesTablero[i][j]);
            }
        }
        this.setSize(botonesTablero[this.filas-1][this.cols-1].getX()+ botonesTablero[this.filas-1][this.cols-1].getWidth()+20,
                botonesTablero[this.filas-1][this.cols-1].getY()+ botonesTablero[this.filas-1][this.cols-1].getHeight()+95
                );
    }
    
    void descargarControles(){
        if (botonesTablero!=null){
            for (int i = 0; i < botonesTablero.length; i++) {
                for (int j = 0; j < botonesTablero[i].length; j++) {
                    if (botonesTablero[i][j]!=null){
                        MinasPane.removeAll();
                    }
                }
            }
        }
    }
    
    private void btnClick(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        String[]coordenada = btn.getName().split(",");
        int fila = Integer.valueOf(coordenada[0]);
        int col = Integer.valueOf(coordenada[1]);
        JOptionPane.showMessageDialog(rootPane, fila +","+col);
        tableroBuscaminas.seleccionarCasilla(fila, col);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jMenu1 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jFrame1 = new javax.swing.JFrame();
        MinasPane = new javax.swing.JPanel();
        textoMinas = new javax.swing.JLabel();
        textoNivel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        nuevoJuego = new javax.swing.JMenu();
        nivelMenu = new javax.swing.JMenu();
        nivelFacil = new javax.swing.JMenuItem();
        nivelIntermedio = new javax.swing.JMenuItem();
        nivelDificil = new javax.swing.JMenuItem();
        nivelPersonalizado = new javax.swing.JMenuItem();

        jTextField1.setText("jTextField1");

        jMenu1.setText("jMenu1");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MinasPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout MinasPaneLayout = new javax.swing.GroupLayout(MinasPane);
        MinasPane.setLayout(MinasPaneLayout);
        MinasPaneLayout.setHorizontalGroup(
            MinasPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        MinasPaneLayout.setVerticalGroup(
            MinasPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 299, Short.MAX_VALUE)
        );

        textoMinas.setText("Minas: 10");

        textoNivel.setText("Nivel: Fácil");

        nuevoJuego.setText("Nuevo Juego");
        nuevoJuego.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nuevoJuegoMouseClicked(evt);
            }
        });
        jMenuBar1.add(nuevoJuego);

        nivelMenu.setText("Nivel");

        nivelFacil.setText("Fácil");
        nivelFacil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nivelFacilActionPerformed(evt);
            }
        });
        nivelMenu.add(nivelFacil);

        nivelIntermedio.setText("Intermedio");
        nivelIntermedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nivelIntermedioActionPerformed(evt);
            }
        });
        nivelMenu.add(nivelIntermedio);

        nivelDificil.setText("Difícil");
        nivelDificil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nivelDificilActionPerformed(evt);
            }
        });
        nivelMenu.add(nivelDificil);

        nivelPersonalizado.setText("Personalizado");
        nivelPersonalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nivelPersonalizadoActionPerformed(evt);
            }
        });
        nivelMenu.add(nivelPersonalizado);

        jMenuBar1.add(nivelMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textoNivel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                .addComponent(textoMinas)
                .addContainerGap())
            .addComponent(MinasPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoMinas)
                    .addComponent(textoNivel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MinasPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoJuegoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuevoJuegoMouseClicked
        juegoNuevo();
    }//GEN-LAST:event_nuevoJuegoMouseClicked

    private void nivelPersonalizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nivelPersonalizadoActionPerformed
        negocio = new NegocioTablero();
        try{
            int f = Integer.parseInt(JOptionPane.showInputDialog("Elija un numero de filas (Max: 16, Mín: 8): "));
            if(!negocio.verificarX(f)){
                JOptionPane.showMessageDialog(rootPane, "El número no es válido.");
                return;
            }
            int c = Integer.parseInt(JOptionPane.showInputDialog("Elija un numero de columnas(Max: 30, Mín: 8): "));
            if(!negocio.verificarY(c)){
                JOptionPane.showMessageDialog(rootPane, "El número no es válido.");
                return;
            }
            int m = Integer.parseInt(JOptionPane.showInputDialog("Elija un numero de minas (Max: 99, Min: 10): "));
            if(!negocio.verificarMinas(m)){
                JOptionPane.showMessageDialog(rootPane, "No se puede crear " + m + " cantidad de minas");
                return;
            }
            filas=f;
            cols=c;
            minas=m;
            juegoNuevo();
            textoNivel.setText("Nivel: Personalizado");
            textoMinas.setText("Minas: " + m);
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(rootPane, "Por favor coloque valores númericos válidos");
            return;
        }       
        
    }//GEN-LAST:event_nivelPersonalizadoActionPerformed

    private void nivelDificilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nivelDificilActionPerformed
        filas=16;
        cols=40;
        minas=99;
        juegoNuevo();
        textoNivel.setText("Nivel: Difícil");
        textoMinas.setText("Minas: 99");
    }//GEN-LAST:event_nivelDificilActionPerformed

    private void nivelIntermedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nivelIntermedioActionPerformed
        filas=16;
        cols=16;
        minas=40;
        juegoNuevo();
        textoNivel.setText("Nivel: Intermedio");
        textoMinas.setText("Minas: 40");
    }//GEN-LAST:event_nivelIntermedioActionPerformed

    private void nivelFacilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nivelFacilActionPerformed
        filas=8;
        cols=8;
        minas=10;
        juegoNuevo();
        textoNivel.setText("Nivel: Fácil");
        textoMinas.setText("Minas: 10");
    }//GEN-LAST:event_nivelFacilActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TableroJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TableroJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TableroJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TableroJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TableroJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MinasPane;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenuItem nivelDificil;
    private javax.swing.JMenuItem nivelFacil;
    private javax.swing.JMenuItem nivelIntermedio;
    private javax.swing.JMenu nivelMenu;
    private javax.swing.JMenuItem nivelPersonalizado;
    private javax.swing.JMenu nuevoJuego;
    private javax.swing.JLabel textoMinas;
    private javax.swing.JLabel textoNivel;
    // End of variables declaration//GEN-END:variables
}
