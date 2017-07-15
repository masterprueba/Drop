/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Controller.Bitacora_Controller;
import Entity.TPersona;
import Entity.TReferencia;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JMenuItem;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andres
 */
public final class Bitacora_Individual extends Views {

    /**
     * Creates new form Bitacora_Usuario
     *
     * @param modelo
     * @param nombre //1 -cliente 2.- codeudor - 3 referencia
     * @param controller
     */
    int flag;
    Bitacora_Controller controller;

    public Bitacora_Individual(DefaultTableModel modelo, String nombre, int flag, Bitacora_Controller controller) {
        this.flag = flag;
        this.controller = controller;
        initComponents();
        jLabel2.setText(nombre);
        jTable1.setModel(modelo);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(1);
        switch (Bitacora_UI.bitacora) {
            case "INICIO":
                setTitle("HISTORIAL DE UN USUARIO");
                jLabel1.setText("HISTORIAL DE UN USUARIO");
                setSize(520, 420);
                break;
            case "PRESTAMO":
                setTitle("HISTORIAL DE UN PRESTAMO");
                jLabel1.setText("HISTORIAL DE UN PRESTAMO");
                setCellRender(jTable1, null);
                setSize(1300, 420);
                break;
            case "CLIENTE":
                setCellRender(jTable1, null);
                if (flag != 3) {
                    if (flag == 1) {
                        setTitle("HISTORIAL DE CLIENTE");
                        jLabel1.setText("HISTORIAL DE CLIENTE");
                    } else if (flag == 2) {
                        setTitle("HISTORIAL DE CODEUDOR");
                        jLabel1.setText("HISTORIAL DE CODEUDOR");
                    }
                    setSize(1300, 420);
                    jTable1.removeColumn(jTable1.getColumnModel().getColumn(13));
                    jTable1.removeColumn(jTable1.getColumnModel().getColumn(12));
                    crearPopup();

                } else {
                    jLabel1.setText("HISTORIAL DE UNA REFERENCIA");
                    setSize(720, 420);
                }
                break;
            case "GASTOS":
                setTitle("HISTORIAL DE UN GASTO");
                jLabel1.setText("HISTORIAL DE UN GASTO");
                setSize(680, 420);
                jTable1.removeColumn(jTable1.getColumnModel().getColumn(7));
                jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
                setCellRender(jTable1, null);
                break;
            case "ABONO":
                setTitle("HISTORIAL DE CUOTAS");
                jLabel1.setText("HISTORIAL DE CUOTAS");
                setSize(900, 420);
                jTable1.removeColumn(jTable1.getColumnModel().getColumn(10));
                setCellRender(jTable1, null);
                //jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
                break;
            case "MULTA":
                setTitle("HISTORIAL INTERES EXTRA");
                jLabel1.setText("HISTORIAL INTERES EXTRA");
                setSize(1200, 420);
                jTable1.removeColumn(jTable1.getColumnModel().getColumn(11));
                setCellRender(jTable1, null);
                break;
        }
    }

    private void crearPopup() {
        ArrayList<Integer> idReferencia = new ArrayList<>();
        JMenuItem[] popupReferencia;
        int ref = 0;
        int j = 0;
        if (flag == 1) {
            boolean noCodeudor = false;
            for (int i = 0; i < Bitacora_Controller.listObject.size(); i++) {
                if (Bitacora_Controller.lBitacora.get(i).getTbitClassname().equals("Entity.TPersona")) {
                    TPersona codeud = (TPersona) Bitacora_Controller.listObject.get(i);
                    if (codeud.getTperTipo().equals("CODEUDOR") && codeud.getTDatosBasicosPersona().getTdbpCedula().equals(jTable1.getModel().getValueAt(0, 12).toString())) {
                        TPersona codeudor = codeud;
                        JMenuItem popupCodoudor = new JMenuItem(codeudor.getTDatosBasicosPersona().getTdbpNombre() + " " + codeudor.getTDatosBasicosPersona().getTdbpApellido());
                        jPopupMenu1.add(popupCodoudor);
                        final int id = codeudor.getTperId();
                        popupCodoudor.addActionListener(e -> {
                            Bitacora_Controller.detalleBitacoraIndividual(id, "CODEUDOR");
                        });
                        noCodeudor = true;
                        break;
                    }
                }
            }
            if (!noCodeudor) {
                jPopupMenu1.add(new JMenuItem("No hay Codeudor para este cliente"));
            }
        }
        if (flag != 3) {
            for (int i = 0; i < Bitacora_Controller.listObject.size(); i++) {
                if (Bitacora_Controller.lBitacora.get(i).getTbitClassname().equals("Entity.TReferencia")) {
                    TReferencia referencia = (TReferencia) Bitacora_Controller.listObject.get(i);
                    if (referencia.getTDatosBasicosPersona().getTdbpId() == Integer.parseInt(jTable1.getModel().getValueAt(0, 13).toString())) {
                        ref++;
                    }
                }
            }
            if (ref != 0) {
                popupReferencia = new JMenuItem[ref];
                for (int i = 0; i < Bitacora_Controller.listObject.size(); i++) {
                    if (Bitacora_Controller.lBitacora.get(i).getTbitClassname().equals("Entity.TReferencia")) {
                        TReferencia referencia = (TReferencia) Bitacora_Controller.listObject.get(i);
                        if (referencia.getTDatosBasicosPersona().getTdbpId() == Integer.parseInt(jTable1.getModel().getValueAt(0, 13).toString())) {
                            boolean continu = true;
                            for (int k = 0; k < idReferencia.size(); k++) {
                                if (Objects.equals(referencia.getTrefId(), idReferencia.get(k))) {
                                    continu = false;
                                    break;
                                }
                            }
                            if (continu) {
                                if (Bitacora_Controller.lBitacora.get(i).getTbitIdentificador().equals("ELIMINO")) {
                                    popupReferencia[j] = new JMenuItem(referencia.getTrefNombre() + " " + referencia.getTrefApellido() + " - ELIMINADO");
                                } else {
                                    popupReferencia[j] = new JMenuItem(referencia.getTrefNombre() + " " + referencia.getTrefApellido());
                                }
                                final int id = referencia.getTrefId();
                                jPopupMenu2.addSeparator();
                                jPopupMenu2.add(popupReferencia[j]);
                                jPopupMenu2.addSeparator();
                                popupReferencia[j].addActionListener(e -> {
                                    Bitacora_Controller.detalleBitacoraIndividual(id, "REFERENCIA");
                                });
                                idReferencia.add(referencia.getTrefId());
                                j++;
                            }
                        }
                    }
                }
            }
            if (idReferencia.isEmpty() || ref == 0) {
                String mensaje = flag == 1 ? "No hay referencias para este cliente" : "No hay referencias para este codeudor";
                popupReferencia = new JMenuItem[1];
                popupReferencia[0] = new JMenuItem(mensaje);
                jPopupMenu2.add(popupReferencia[0]);
            }
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);

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
        jScrollPane2.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            jPopupMenu1.show(evt.getComponent(),
                    evt.getX(), evt.getY());
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            jPopupMenu2.show(evt.getComponent(),
                    evt.getX(), evt.getY());
        }
        if (controller.vistaBitacora.bitacora.equals("MULTA")) {
            controller.bitacoraGeneralIndividual(evt, 3);
        }
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
