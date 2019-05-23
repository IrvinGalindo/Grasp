/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grasp.panels;

import grasp.Cosas;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author L55-C5211R
 */
public class PanelIntro extends javax.swing.JPanel {
    private final ArrayList<Cosas> cosas;
    private final DefaultTableModel modalCosas;
    public static String []cosasNombre;
    /**
     * Creates new form PanelIntro
     * @param cosas
     */
    public PanelIntro(ArrayList<Cosas> cosas) {
        initComponents();
        this.cosas  = cosas;
        modalCosas = new DefaultTableModel();
        jTableIntro.setModel(modalCosas);
        cosasNombre = new String[this.cosas.size()+1];
        
        inflaTabla();
               
    }
    
    private void inflaTabla(){
        cosasNombre[0] = "";
        for (int i = 0; i < cosas.size(); i++) {
            cosasNombre[i+1] = cosas.get(i).getNombre();
        }
        
        Object cosasPesoUtilidad[][] = new Object[2][this.cosas.size()+1];
        cosasPesoUtilidad[0][0] = "Peso";
        cosasPesoUtilidad[1][0] = "Utilidad";
        for(int data = 1; data < cosasPesoUtilidad[0].length; data++){
            cosasPesoUtilidad[0][data] = cosas.get(data-1).getPeso();
            cosasPesoUtilidad[1][data] = cosas.get(data-1).getUtilidad();
        }
        modalCosas.setDataVector(cosasPesoUtilidad, cosasNombre);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableIntro = new javax.swing.JTable();
        lblIteracion = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(640, 400));
        setPreferredSize(new java.awt.Dimension(660, 400));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Problema de la mochila");
        jLabel2.setToolTipText("");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("<html><body> Considérese un excursionista que debe preparar su mochila para un viaje que realizara de una semana. <br> Considérese asimismo que hay una serie de objetos (brújula, tienda de acampar, cantimplora con agua, botiquín de primeros auxilios, cuerda, lámpara, cuchillo, alimentos no perecederos) <br>  de utilidad para el excursionista, pero que el excursionista sólo puede llevar un número limitado de objetos debido a la capacidad máxima que puede cargar su mochila.<br>   El problema consiste en elegir un subconjunto de objetos de tal forma que se maximice la utilidad que el excursionista obtiene, pero sin rebasar su capacidad de acarrear objetos.   </body></html>");
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTableIntro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Peso", "2", "9", "1", "1", "2", "1", ".5", "5"},
                {"Utilidad", "5", "7", "28", "4", "10", "7", "5", "21"}
            },
            new String [] {
                "", "Brujula", "Tienda", "Cantimplora  con agua", "Botiquin", "Cuerda", "Lampara", "Cuchillo", "Alimentos no perecederos"
            }
        ));
        jTableIntro.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane2.setViewportView(jTableIntro);

        lblIteracion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblIteracion, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(lblIteracion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTableIntro;
    public javax.swing.JLabel lblIteracion;
    // End of variables declaration//GEN-END:variables
}