/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Controller.Bitacora_Controller;
import Controller.TableModel;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andres
 */
public class Bitacora_UI extends Views {

    public DefaultTableModel modeloTabla1, modeloTabla2;
    public static Bitacora_Controller bitController;
    public static String bitacora;

    /**
     * Creates new form Bitacora_UI
     *
     * @param bit
     */
    public Bitacora_UI(String bit) {
        bitacora = bit;
        bitController = new Bitacora_Controller(this);
        initComponents();
        cargarComponentes();
        bitController.desactivarFechas(1);
        bitController.verBitacoraGeneral();
    }

    private void cargarComponentes() {
        switch (bitacora) {
            case "INICIO":
                modeloTabla1 = new TableModel().bitacoraGeneralInicioSession();
                modeloTabla2 = new TableModel().bitacoraIndividualInicioSession();
                jTable1.setModel(modeloTabla1);
                jTable2.setModel(modeloTabla2);
                jTable1.setRowSorter(filtrarTabla(modeloTabla1));
                jTable2.setRowSorter(filtrarTabla(modeloTabla2));
                jTable1.getColumnModel().getColumn(2).setPreferredWidth(300);
                jLabel1.setText("HISTORIAL DE INICIO DE SESSION");
                setTitle("HISTORIAL DE INICIO DE SESSION");
                jTable2.removeColumn(jTable2.getColumnModel().getColumn(3));
                jTable2.getColumnModel().getColumn(0).setPreferredWidth(1);
                bitController.verPersonas(1);
                break;
            case "PRESTAMO":
                modeloTabla1 = new TableModel().bitacoraGeneralPrestamo();
                jTable1.setModel(modeloTabla1);
                jTable1.removeColumn(jTable1.getColumnModel().getColumn(6));
                jLabel1.setText("HISTORIAL DE PRESTAMOS");
                setTitle("HISTORIAL DE PRESTAMOS");
                this.getContentPane().remove(2);
                jPanel1.setPreferredSize(new Dimension(790, 470));
                //jLabel3.setVisible(false);
                setSize(825, 565);
                break;
            case "CLIENTE":
                modeloTabla1 = new TableModel().bitacoraGeneralClientes();
                jTable1.setModel(modeloTabla1);
                jTable1.removeColumn(jTable1.getColumnModel().getColumn(6));
                jLabel1.setText("HISTORIAL DE CLIENTES");
                setTitle("HISTORIAL DE CLIENTES");
                this.getContentPane().remove(2);
                jPanel1.setPreferredSize(new Dimension(790, 470));
                //jLabel3.setVisible(false);
                setSize(825, 565);
                break;
            case "GASTOS":
                modeloTabla1 = new TableModel().bitacoraGeneralGastos();
                jTable1.setModel(modeloTabla1);
                jTable1.removeColumn(jTable1.getColumnModel().getColumn(7));
                jTable1.getColumnModel().getColumn(2).setPreferredWidth(300);
                jLabel1.setText("HISTORIAL DE GASTOS");
                setTitle("HISTORIAL DE GASTOS");
                this.getContentPane().remove(2);
                //jLabel3.setVisible(false);
                setSize(825, 565);
                jPanel1.setPreferredSize(new Dimension(789, 465));
                break;
            case "ABONO":
                modeloTabla1 = new TableModel().bitacoraGeneralAbonos();
                jTable1.setModel(modeloTabla1);
                jTable1.removeColumn(jTable1.getColumnModel().getColumn(10));
                //jTable1.getColumnModel().getColumn(2).setPreferredWidth(300);
                jLabel1.setText("HISTORIAL DE ABONOS");
                setTitle("HISTORIAL DE ABONOS");
                this.getContentPane().remove(2);
                //jLabel3.setVisible(false);
                setSize(1000, 555);
                jPanel1.setPreferredSize(new Dimension(965, 460));
                //jPanel1.setSize(d);
                break;
            case "MULTA":
                modeloTabla1 = new TableModel().bitacoraGeneralMulta();
                modeloTabla2 = new TableModel().bitacoraIndividualInicioSession();
                jTable1.setModel(modeloTabla1);
                jTable2.setModel(modeloTabla2);
                jTable1.removeColumn(jTable1.getColumnModel().getColumn(11));
                jTable2.removeColumn(jTable2.getColumnModel().getColumn(3));
                jLabel1.setText("HISTORIAL INTERES EXTRA");
                setTitle("HISTORIAL INTERES EXTRA");
                setSize(1300, 600);
                jPanel1.setPreferredSize(new Dimension(830, jPanel1.getPreferredSize().height));
                jTable2.getColumnModel().getColumn(0).setPreferredWidth(1);
                jTable2.getColumnModel().getColumn(0).setPreferredWidth(1);
                bitController.verPersonas(2);
                break;
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        Comp_Fecha_Desde2 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        Comp_Fecha_Desde1 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setClosable(true);
        setTitle("BITACORA");
        setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HISTORIAL GENERAL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 18))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 16))); // NOI18N

        jButton3.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/busqueda.png"))); // NOI18N
        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel8.setText("Hasta:");

        jLabel7.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel7.setText("Desde:");

        jComboBox1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoy", "Este mes", "Filtrar por fechas" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Comp_Fecha_Desde1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Comp_Fecha_Desde2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(180, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Comp_Fecha_Desde2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(Comp_Fecha_Desde1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HISTORIAL INDEPENDIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Cambria", 0, 18))); // NOI18N
        jPanel2.setToolTipText("");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        bitController.bitacoraGeneralIndividual(evt, 2);
    }//GEN-LAST:event_jTable2MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (jComboBox1.getSelectedIndex() == 2) {
            bitController.desactivarFechas(2);
            modeloTabla1.setNumRows(0);
        } else {
            bitController.desactivarFechas(1);
            bitController.verBitacoraGeneral();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        bitController.verBitacoraGeneral();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        bitController.bitacoraGeneralIndividual(evt, 1);
    }//GEN-LAST:event_jTable1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static com.toedter.calendar.JDateChooser Comp_Fecha_Desde1;
    public static com.toedter.calendar.JDateChooser Comp_Fecha_Desde2;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
