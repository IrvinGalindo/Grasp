/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grasp.panels;

import grasp.Cosas;
import grasp.GraspInterfaz;
import java.util.ArrayList;

/**
 *
 * @author L55-C5211R
 */
public class PanelMejora extends javax.swing.JPanel {

    private int contador, sAux[];
    private final int solucionParcial[];
    private int mejorSolucion[];
    private double sumaUtilidad, sumaPesos, mayorUtilidad, mayorPeso;
    private String vecino;
    private Cosas objetoAux;
    private ArrayList<Cosas> cosas = new ArrayList<>();

    /**
     * Creates new form PanelMejora
     * @param cosas
     * @param solucionParcial
     */
    public PanelMejora(ArrayList<Cosas> cosas, int solucionParcial[]) {
        initComponents();

        this.cosas = cosas;
        this.solucionParcial = solucionParcial;

        mejorSolucion = new int[solucionParcial.length];
        contador = 0;
        sumaPesos = 0.0;
        sumaUtilidad = 0.0;
        vecino = "";
    }

     /**Metodo que contiene los pasos utilizados en la fase de construccion para obtener al mejor vecino y compararlo con la solucion parcial
     donde:
     Se crea la solucion parcial por medio de
     * @param orden - variable que incrementa dependiendo del numero de paso que es
     * @return  variable booleana que valida que cada paso termino correctamente. true si se termino correcto false si aun no termina el paso*/
    public boolean stepsContinue(int orden) {
        boolean siStep = false;
        switch (orden) {
            case 6:
                lblKgUt.setText("U = " + PanelConstruccion.getSumatoriaUtilidad() + ", Kg = " + PanelConstruccion.getSumatoriaPeso());
                tblSolParcialMej.setModel(PanelConstruccion.getModal());
                lblMejora.setText("<html><body>Paso 9: Una vez obtenida la solución parcial, genera soluciones vecinas en donde<br> se almacenarán nuevas soluciones.<br>"
                        + "Paso 10: Se compara dentro de la lista de la solución parcial cual es 0 y cual es 1, y en base a ello<br>"
                        + "va creando nuevas listas de vecinos modificando únicamente esa posición del objeto. <br> Paso 11: Los vecinos son comparados con lo solución parcial para verificar <br>"
                        + " cual es el que contiene la utilidad máxima y no pase la capacidad de la mochila, y en caso de que un vecino sea mejor que la primera solución obtenida, esta lo reemplazará."
                        + "</body></html>");

                if (contador < solucionParcial.length) {
                    sAux = new int[solucionParcial.length];
                    System.arraycopy(solucionParcial, 0, sAux, 0, solucionParcial.length);
                    if (sAux[contador] == 0) {
                        sAux[contador] = 1;
                    } else {
                        sAux[contador] = 0;
                    }

                    sumaUtilidad = 0.0;
                    sumaPesos = 0.0;
                    vecino += "[ ";
                    for (int j = 0; j < sAux.length; j++) {
                        if (j == sAux.length - 1) {
                            vecino += sAux[j];
                        } else {
                            vecino += sAux[j] + " ,";
                        }
                        if (sAux[j] == 1) {
                            objetoAux = cosas.get(j);
                            sumaUtilidad += objetoAux.getUtilidad();
                            sumaPesos += objetoAux.getPeso();
                        }

                    }
                    vecino += "] Utilidad: " + sumaUtilidad + " Peso: " + sumaPesos + "\n";
                    txtAVecinos.append(vecino);
                    vecino = "";
                    if (sumaPesos <= GraspInterfaz.CAPACIDADMOCHILAKG && sumaUtilidad > mayorUtilidad) {
                        mayorUtilidad = sumaUtilidad;
                        mayorPeso = sumaPesos;
                        System.arraycopy(sAux, 0, mejorSolucion, 0, sAux.length);
                    }
                    vecino = "----------------- Mejor Vecino ----------\n";
                    imprimirVecino(mayorUtilidad, mayorPeso);
                    contador++;

                } else {
                    contador = 0;
                    siStep = true;
                }

                break;

            case 7:
                lblMejora.setText("<html><body>Paso 11: Se compara la solucion parcial contra la mejor solucion para obtener <br>cual es la mejor combinacion de cosas"
                        + "que puede llevar en la mochila y utilizara con frecuencia</html></body>");
                mejorSolucion = sumatoriaUtilidad();
                vecino = "\n----------------- Mejor Solución ----------\n";
                imprimirVecino(mayorUtilidad, mayorPeso);
                GraspInterfaz.btnSiguiente.setText("Salir");
                siStep = true;
                break;
            case 8:
                siStep = true;
                break;
        }
        return siStep;
    }

    /*Este metodo imprime al mejor vecino de cada una de las iteraciones de la fase de mejora, los cuales son muestrados en
     un JTextArea*/
    private void imprimirVecino(double mayorUtilidad, double mayorPeso) {
        vecino += "[ ";
        for (int j = 0; j < mejorSolucion.length; j++) {
            if (j == mejorSolucion.length - 1) {
                vecino += mejorSolucion[j];
            } else {
                vecino += mejorSolucion[j] + " ,";
            }
        }
        vecino += " ] Utilidad: " + mayorUtilidad + " Peso: " + mayorPeso + "\n\n";
        txtAVecinos.append(vecino);
        vecino = "";
    }

    /*Metodo que realiza la sumatoria de la utilidad y del peso del vecino de la iteracion actual y lo compara con el mejor vecino de
    una iteracion anterior
     * @return E*/
    private int[] sumatoriaUtilidad() {
        sumaUtilidad = 0.0;
        sumaPesos = 0.0;
        for (int i = 0; i < cosas.size(); i++) {
            if (solucionParcial[i] == 1) {
                sumaUtilidad += cosas.get(i).getUtilidad();
                sumaPesos += cosas.get(i).getPeso();
            }
        }
        if (sumaUtilidad > mayorUtilidad) {
            mayorUtilidad = sumaUtilidad;
            mayorPeso = sumaPesos;
            return this.solucionParcial;
        } else if(sumaUtilidad == mayorUtilidad && sumaPesos < mayorPeso){
            return solucionParcial;
        }else{
            return mejorSolucion;
        }
    
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSolParcialMej = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAVecinos = new javax.swing.JTextArea();
        lblMejora = new javax.swing.JLabel();
        lblKgUt = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(640, 400));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Fase de mejora");
        jLabel2.setToolTipText("");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tblSolParcialMej.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", "", "", "", "", ""
            }
        ));
        jScrollPane2.setViewportView(tblSolParcialMej);

        txtAVecinos.setColumns(20);
        txtAVecinos.setRows(5);
        jScrollPane1.setViewportView(txtAVecinos);

        lblKgUt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblKgUt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKgUt.setToolTipText("");
        lblKgUt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblMejora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblKgUt, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblKgUt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblMejora, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JLabel lblKgUt;
    public javax.swing.JLabel lblMejora;
    public javax.swing.JTable tblSolParcialMej;
    public javax.swing.JTextArea txtAVecinos;
    // End of variables declaration//GEN-END:variables
}
