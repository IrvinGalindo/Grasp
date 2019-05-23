/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grasp.panels;

import grasp.Cosas;
import grasp.GraspInterfaz;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author L55-C5211R
 */
public class PanelConstruccion extends javax.swing.JPanel {
    
    private final DefaultTableModel modalLC, modalLCR;
    private final int solucionParcial[];
    private final ArrayList<Cosas> cosas;

    /**
     * Rl: Lista de candidatos Rl2: Copia de la lista candidatos Rlc: Lista de
     * restringida de candidatos
     */
    private final double Rl[], Rl2[], Rlc[];

    /**
     * sumatoriaPeso: Suma de los pesos de los elementos que pueden ser
     * introducidos en la mochila sumatoriaUtilidad: Suma de las utilidades de
     * los elementos que pueden ser introducidos en la mochila
     */
    private static double sumatoriaPeso, sumatoriaUtilidad;
    private static DefaultTableModel modalLcand;

    /**
     * borrarUno: almacena la posicion del elemento que sera eliminado del
     * arreglo la lista de candidatos(Rlc)
     */
    private int contador, borrarUno;

    /**
     * limiteLR: variable que almacena el numero de elementos que podra contener
     * la lista de canditados
     */
    private final int limiteLR;

    /**
     * Creates new form PanelConstruccion
     *
     * @param cosas
     *
     */
    public PanelConstruccion(ArrayList<Cosas> cosas) {
        initComponents();

        this.solucionParcial = new int[cosas.size()];
        this.cosas = cosas;

        sumatoriaPeso = 0.0;
        sumatoriaUtilidad = 0.0;

        contador = 0;
        borrarUno = 0;
        limiteLR = (int) (cosas.size() / 2);

        Rl = new double[cosas.size()];
        Rlc = new double[limiteLR];
        Rl2 = new double[Rl.length];

        modalLcand = new DefaultTableModel();
        modalLC = new DefaultTableModel();
        modalLCR = new DefaultTableModel();
        tblSolParcial1.setModel(modalLcand);
        tblSolLc.setModel(modalLC);
        tblSolLcr.setModel(modalLCR);
    }

    /**Metodo que contiene los pasos de la fase de construccion para obtener la solucion parcial
     donde:
     Se crea la solucion parcial por medio de
     * @param orden - variable que incrementa dependiendo del numero de paso que es
     * @return  variable booleana que valida que cada paso termino correctamente. true si se termino correcto false si aun no termina el paso*/
    public boolean steps(int orden) {
        boolean isStep = false;
        switch (orden) {
            case 1:
                lblTexto.setText("Primero se ejecuta la fase de construccion en la cual se creara la soluion parcial");

                Object ob[] = new Object[cosas.size()];
                for (int i = 0; i < ob.length; i++) {
                    modalLcand.addColumn(ob[i] = "");
                }
                modalLcand.addRow(ob);

                modalLC.addColumn("Objeto");
                modalLC.addColumn("Indice utilidad");

                modalLCR.addColumn("Mejores candidatos");
                isStep = true;
                break;
            case 2:

                lblTexto.setText("<html><body>Paso 1: Genera una lista del mismo tamaño que la lista de candidatos llamada solución parcial,<br> en la que cada posición de la lista hará alusión a la posición del objeto<br>"
                        + " que se encuentra en la lista de candidatos y estará llena de 0’s<html><body>");
                for (int sol = 0; sol < solucionParcial.length; sol++) {
                    modalLcand.setValueAt(0, 0, sol);
                }
                isStep = true;
                break;
            case 3:
                lblTexto.setText("<html><body>Paso 2: Elabora una lista de candidatos con los objetos dados en la tabla"
                        + "<br>dividiendo su utilidad sobre peso (U/P), para obtener el índice de utilidad de cada uno de los objetos</html></body>");

                for (int i = 0; i < cosas.size(); i++) {
                    Rl[i] = (cosas.get(i).getUtilidad() / cosas.get(i).getPeso());

                }
                Object obLC[] = new Object[2];
                for (int i = 0; i < cosas.size(); i++) {
                    obLC[0] = PanelIntro.cosasNombre[i + 1];
                    obLC[1] = Rl[i];
                    modalLC.addRow(obLC);
                }
                System.arraycopy(Rl, 0, Rl2, 0, Rl.length);
                isStep = true;
                break;
            case 4:
                double mayor = 0;
                lblTexto.setText("<html><body>Paso 3: Elabora una lista restringida de los 4 mejores candidatos que arrojen el mayor índice de utilidad.<br>"
                        + "Paso 4: Aleatoriamente selecciona uno de los 4 objetos de la lista restringida, <br>"
                        + "Paso 5: Asigna el obtenido a la lista llamada solución parcial,cambiando el 0 que tenía por un 1 <br>"
                        + "en la posición que se encontraba en la lista de candidatos, cuidando no sobrepasar <br>"
                        + "la capacidad de carga de la mochila y del excursionista, es 15 en ambos casos <br>"
                        + "1 significa que si entra en la mochila, 0 que no va dentro de la mochila.</body></html>");
                Object obLCR[] = new Object[1];
                while (contador < limiteLR) {
                    for (int z = 0; z < Rl2.length; z++) {
                        if (Rl2[z] > mayor) {
                            mayor = Rl2[z];
                            borrarUno = z;
                        }
                    }
                    Rlc[contador] = mayor;
                    obLCR[0] = Rlc[contador];
                    modalLCR.addRow(obLCR);
                    Rl2[borrarUno] = 0.0;
                    mayor = 0;
                    contador++;
                }
                contador = 0;
                isStep = true;
                break;

            case 5:
                if (contador < 10) {
                    Random geraRandom = new Random();
                    int random = geraRandom.nextInt(limiteLR);
                    double cosaRand = Rlc[random];
                    while (cosaRand == 0) {
                        random = geraRandom.nextInt(limiteLR);
                        cosaRand = Rlc[random];
                    }
                    for (int ele = 0; ele < cosas.size(); ele++) {
                        if (cosaRand == Rl[ele]) {
                            sumatoriaPeso += cosas.get(ele).getPeso();

                            if (sumatoriaPeso <= GraspInterfaz.CAPACIDADMOCHILAKG) {
                                solucionParcial[ele] = 1;
                                modalLcand.setValueAt(solucionParcial[ele], 0, ele);
                                Rl[ele] = 0.0;
                                modalLC.setValueAt("", ele, 1);
                                lblTotalKg.setText(sumatoriaPeso + "");
                                sumatoriaUtilidad += cosas.get(ele).getUtilidad();
                                lblSumUti.setText(sumatoriaUtilidad + "");
                                RCLMofi(random);
                                lblTexto.setText("<html><body>Paso 6: Elimina de la lista de candidatos el objeto introducido a la lista solución parcial. <br>"
                                        + "Paso 7: Elabora de nuevo una lista de candidatos restringida de 4 objetos ya sin el  que se haya seleccionado.<br>"
                                        + "Paso 8: Repite el paso 4, 5, 6 y 7  hasta que el algoritmo haya verificado que no se pueda ingresar <br>"
                                        + "otro candidato a la lista de solución parcial debido a la restricción del peso. (15 kg)<html><body>");
                                break;
                            } else {
                                sumatoriaPeso -= cosas.get(ele).getPeso();
                                modalLcand.setValueAt(0, 0, ele);
                                break;
                            }
                        }
                    }
                    contador++;
                } else {
                    contador = 0;
                    isStep = true;

                }
                break;
        }
        return isStep;
    }

    /**
     * Metodo encargado de eliminar el elemento de la lista de candidatos para
     * despues insertarlo en el arreglo de la solucion parcial
     */
    private void RCLMofi(int x) {
        double mayor = 0;
        int cero = 0;
        for (int z = 0; z < Rl2.length; z++) {
            if (Rl2[z] > mayor) {
                mayor = Rl2[z];
                cero = z;
            }
        }
        Rlc[x] = mayor;
        modalLCR.setValueAt(mayor, x, 0);

        Rl2[cero] = 0.0;

    }

    /**
     * @return la sumatoria de pesos de la solución parcia
     */
    public static double getSumatoriaPeso() {
        return sumatoriaPeso;
    }

    /**
     * @return la sumatoria de utilidad de la solución parcia
     */
    public static double getSumatoriaUtilidad() {
        return sumatoriaUtilidad;
    }

    /**
     * @return el modal que contiene la solucion parcial
     */
    public static DefaultTableModel getModal() {
        return modalLcand;
    }

     /**
     * @return solucion parcial
     */
    public int[] getSolucionParcial() {
        return solucionParcial;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSolLc = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSolParcial1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSolLcr = new javax.swing.JTable();
        lblTexto = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTotalKg = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        x = new javax.swing.JLabel();
        lblSumUti = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(640, 400));
        setPreferredSize(new java.awt.Dimension(640, 400));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Kilos.");
        jLabel2.setToolTipText("");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tblSolLc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Objeto", "Indice de utilidad"
            }
        ));
        jScrollPane1.setViewportView(tblSolLc);

        tblSolParcial1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", "", "", "", "", ""
            }
        ));
        tblSolParcial1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane2.setViewportView(tblSolParcial1);

        tblSolLcr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane3.setViewportView(tblSolLcr);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Lista de candidatos Restringidos");
        jLabel3.setToolTipText("");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblTotalKg.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTotalKg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalKg.setToolTipText("");
        lblTotalKg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Lista de candidatos");
        jLabel4.setToolTipText("");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        x.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        x.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        x.setText("Sumatoria utilidad :");
        x.setToolTipText("");
        x.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblSumUti.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSumUti.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSumUti.setToolTipText("");
        lblSumUti.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Fase de construcción");
        jLabel5.setToolTipText("");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(x, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSumUti, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel3))
                                .addContainerGap(107, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTotalKg, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(32, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTotalKg, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSumUti, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(x, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addComponent(lblTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JLabel lblSumUti;
    public javax.swing.JLabel lblTexto;
    public javax.swing.JLabel lblTotalKg;
    public javax.swing.JTable tblSolLc;
    public javax.swing.JTable tblSolLcr;
    public javax.swing.JTable tblSolParcial1;
    public javax.swing.JLabel x;
    // End of variables declaration//GEN-END:variables
}
