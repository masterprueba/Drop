/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Controller.Prestamos_Abonos_x_fecha_Controller;
import Controller.TableModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class PrestamosyAbonos_x_fecha_UI extends Views {

    /**
     * Creates new form Perstamos_x_fecha
     *
     * @param vist
     */
    static String vista;
    public DefaultTableModel modelo;
    private final Prestamos_Abonos_x_fecha_Controller paControler;
    private String fecha;

    public PrestamosyAbonos_x_fecha_UI(String vist) {
        initComponents();
        vista = vist;
        paControler = new Prestamos_Abonos_x_fecha_Controller(this);
        modelo(vist);
        paControler.desactivarFechas(1);
        if (!vista.equals("ABONO")) {
            jComboCobrador.setVisible(false);
            jComboMetodoPago.setVisible(false);
            jLabel3.setVisible(false);
            jLabel4.setVisible(false);
        }

    }

    private void modelo(String vista) {
        switch (vista) {
            case "PRESTAMO":
                jLabel2.setText("PRESTAMOS POR FECHA");
                modelo = new TableModel().prestamosPorFecha();
                paControler.verPrestamos();
                break;
            case "ABONO":
                jLabel2.setText("ABONOS POR FECHA");
                modelo = new TableModel().abonoPorFecha();
                paControler.verAbonos();
                break;
            case "MULTA":
                jLabel2.setText("INTERES EXTRA POR FECHA");
                modelo = new TableModel().multaPorFecha();
                paControler.verMultas();
                break;
        }
        jTable1.setModel(modelo);
        jTable1.setRowSorter(filtrarTabla(modelo));
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        Comp_Fecha_Desde1 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Comp_Fecha_Desde2 = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboCobrador = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboMetodoPago = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);

        jComboBox1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoy", "Este mes", "Filtrar por fechas" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel7.setText("Desde:");

        jLabel8.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel8.setText("Hasta:");

        jButton3.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/busqueda.png"))); // NOI18N
        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

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
        jScrollPane1.setViewportView(jTable1);

        jTextField1.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel1.setText("Total");

        jLabel3.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel3.setText("Cobrador");

        jComboCobrador.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jComboCobrador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));
        jComboCobrador.setMinimumSize(new java.awt.Dimension(152, 26));
        jComboCobrador.setPreferredSize(new java.awt.Dimension(152, 26));
        jComboCobrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboCobradorActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jLabel4.setText("Metodo Pago");

        jComboMetodoPago.setFont(new java.awt.Font("Cambria", 0, 16)); // NOI18N
        jComboMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));
        jComboMetodoPago.setMinimumSize(new java.awt.Dimension(152, 26));
        jComboMetodoPago.setPreferredSize(new java.awt.Dimension(152, 26));
        jComboMetodoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboMetodoPagoActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/printer.png"))); // NOI18N
        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Comp_Fecha_Desde1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Comp_Fecha_Desde2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboCobrador, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Comp_Fecha_Desde2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jComboMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboCobrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(Comp_Fecha_Desde1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(18, 31, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (vista.equals("PRESTAMO")) {
            paControler.verPrestamos();
        } else if (vista.equals("ABONO")) {
            paControler.verAbonos();
        } else {
            paControler.verMultas();
        }
        fecha = "(" + Comp_Fecha_Desde1.getJCalendar().getDayChooser().getDay() + "-" + (Comp_Fecha_Desde1.getJCalendar().getMonthChooser().getMonth() + 1) + "-" + Comp_Fecha_Desde1.getJCalendar().getYearChooser().getYear() + ") - "
                + "(" + Comp_Fecha_Desde2.getJCalendar().getDayChooser().getDay() + "-" + (Comp_Fecha_Desde2.getJCalendar().getMonthChooser().getMonth() + 1) + "-" + Comp_Fecha_Desde2.getJCalendar().getYearChooser().getYear() + ")";
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (jComboBox1.getSelectedIndex() == 2) {
            paControler.desactivarFechas(2);
            modelo.setNumRows(0);
        } else {
            paControler.desactivarFechas(1);
            if (vista.equals("PRESTAMO")) {
                paControler.verPrestamos();
            } else if (vista.equals("ABONO")) {
                paControler.verAbonos();
            } else {
                paControler.verMultas();
            }

        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboCobradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboCobradorActionPerformed
        // TODO add your handling code here:
        paControler.verAbonos();
    }//GEN-LAST:event_jComboCobradorActionPerformed

    private void jComboMetodoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboMetodoPagoActionPerformed
        // TODO add your handling code here:
        paControler.verAbonos();
    }//GEN-LAST:event_jComboMetodoPagoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTable1.getRowCount() > 0) {
            ((DefaultTableModel) jTable1.getModel()).addRow(new String[]{});
            ((DefaultTableModel) jTable1.getModel()).addRow(new String[]{});
            ((DefaultTableModel) jTable1.getModel()).addRow(new String[]{"", "TOTAL :", jTextField1.getText()});
            String titulo = vista.equals("PRESTAMO") ? "Historial de prestamos por fecha " : vista.equals("ABONO") ? "Historial de abonos por fecha " : "Historial de interes extra por fecha ";
            paControler.imprimirTabla(jTable1, titulo + fecha, "DrotiSoftwares", true);
            ((DefaultTableModel) jTable1.getModel()).removeRow(((DefaultTableModel) jTable1.getModel()).getRowCount() - 1);
            ((DefaultTableModel) jTable1.getModel()).removeRow(((DefaultTableModel) jTable1.getModel()).getRowCount() - 1);
            ((DefaultTableModel) jTable1.getModel()).removeRow(((DefaultTableModel) jTable1.getModel()).getRowCount() - 1);
        } else {
            JOptionPane.showMessageDialog(rootPane, "No hay filas para imprimir.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static com.toedter.calendar.JDateChooser Comp_Fecha_Desde1;
    public static com.toedter.calendar.JDateChooser Comp_Fecha_Desde2;
    private javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JComboBox<String> jComboBox1;
    public static javax.swing.JComboBox<String> jComboCobrador;
    public static javax.swing.JComboBox<String> jComboMetodoPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
