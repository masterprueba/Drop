/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Controller.Backup;
import Controller.Login_Controller;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class MainDesktop extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public MainDesktop() {        
        initComponents();
        CerraAplicacion();        
        jMenuBar1.add(Box.createHorizontalGlue());        
        jMenu2.setText(Login_Controller.getUsuarioLogueado().getTDatosBasicosPersona().getTdbpNombre()+" "+Login_Controller.getUsuarioLogueado().getTDatosBasicosPersona().getTdbpApellido());
        jMenuBar1.add(jMenu2);
        //Maximizar la ventana en inicio
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    private void CerraAplicacion() {
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    Confirmar();
                }
            });
        } catch (Exception e) {
        }
    }

    private void Confirmar() {
        int Valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea cerrar la aplicacion?", "Advertencia", JOptionPane.YES_NO_OPTION);
        if (Valor == JOptionPane.YES_OPTION) {
            System.exit(0);
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

        DesktopPaneMain = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Drop");
        setMinimumSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().getSize());
        setUndecorated(true);

        DesktopPaneMain.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout DesktopPaneMainLayout = new javax.swing.GroupLayout(DesktopPaneMain);
        DesktopPaneMain.setLayout(DesktopPaneMainLayout);
        DesktopPaneMainLayout.setHorizontalGroup(
            DesktopPaneMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 706, Short.MAX_VALUE)
        );
        DesktopPaneMainLayout.setVerticalGroup(
            DesktopPaneMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        jMenuBar1.setMaximumSize(new java.awt.Dimension(145, 32769));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(143, 50));

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/servicio-al-cliente.png"))); // NOI18N
        jMenu4.setText("Cliente");
        jMenu4.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/1465696263_Plus.png"))); // NOI18N
        jMenuItem1.setText("Nuevo Cliente ");
        jMenuItem1.setPreferredSize(new java.awt.Dimension(141, 30));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar1.add(jMenu4);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/prestamo.png"))); // NOI18N
        jMenu3.setText("Prestamo");
        jMenu3.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jMenu3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jMenuItem4.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/1465696263_Plus.png"))); // NOI18N
        jMenuItem4.setText("Prestar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem7.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/1465696263_Plus.png"))); // NOI18N
        jMenuItem7.setText("Abonar");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/monedas.png"))); // NOI18N
        jMenu1.setText("Gastos");
        jMenu1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jMenuItem5.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/1465696263_Plus.png"))); // NOI18N
        jMenuItem5.setText("Nuevo Gasto");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/equipo.png"))); // NOI18N
        jMenu5.setText("Usuarios");
        jMenu5.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N

        jMenuItem6.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/1465696263_Plus.png"))); // NOI18N
        jMenuItem6.setText("Usuarios");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/selec_row.png"))); // NOI18N
        jMenu6.setText("Historial");
        jMenu6.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N

        jMenuItem9.setText("Historial inicio de sesion");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem9);

        jMenuItem10.setText("Historial de prestamos");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem10);

        jMenuBar1.add(jMenu6);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/signing.png"))); // NOI18N
        jMenu7.setText("Informes");
        jMenu7.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N

        jMenuItem11.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/1465696263_Plus.png"))); // NOI18N
        jMenuItem11.setText("Prestamos");
        jMenuItem11.setPreferredSize(new java.awt.Dimension(141, 30));
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem11);

        jMenuBar1.add(jMenu7);

        jMenu2.setForeground(new java.awt.Color(255, 102, 0));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/usuario.png"))); // NOI18N
        jMenu2.setText("Nick");
        jMenu2.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jMenu2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jMenuItem2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/hacia-atras.png"))); // NOI18N
        jMenuItem2.setText("Cerrar Sesion");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem8.setText("Backup");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem3.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/salida.png"))); // NOI18N
        jMenuItem3.setText("Salir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);
        jMenu2.getAccessibleContext().setAccessibleDescription("");

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DesktopPaneMain)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DesktopPaneMain)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       JInternalFrame cli = new Cliente_UI();
        checkInstance(cli);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        int Respuesta = JOptionPane.showConfirmDialog(this, "¿Esta seguro que decea cerrar su sesión?", "Advertencia", JOptionPane.YES_NO_OPTION);
        if (Respuesta == JOptionPane.YES_OPTION) {
            Login in = new Login();
            in.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        int Valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea cerrar la aplicacion?", "Advertencia", JOptionPane.YES_NO_OPTION);
        if (Valor == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        JInternalFrame in = new Prestamo_ui();
        //DesktopPaneMain.add(in);
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        JInternalFrame in = new Gastos_UI();
        //DesktopPaneMain.add(in);
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        JInternalFrame in = new Usuarios_UI();
        //DesktopPaneMain.add(in);
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        JInternalFrame in = new Cuota_UI();
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        Backup.execute();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
       JInternalFrame in = new Bitacora_UI("INICIO");
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
       JInternalFrame in = new Bitacora_UI("PRESTAMO");
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        checkInstance(new InformeCliente());
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    public static void checkInstance(Object ven) {
        JInternalFrame InternalWindow = (JInternalFrame) ven;
        boolean notExist = true;

        // Verificar si es instancia de algun componente que ya este en el jDesktopPane
        for (int a = 0; a < DesktopPaneMain.getComponentCount(); a++) {
            if (InternalWindow.getClass().isInstance(DesktopPaneMain.getComponent(a))) {
                System.out.println("La ventana ya esta abierta");
                notExist = false;
            }
        }
        //Si no es una instancia existente, muestra la ventana
        if (notExist) {

            DesktopPaneMain.add(InternalWindow);
            calcWidthHeight(InternalWindow);

            InternalWindow.show();
        }
    }

    public static void calcWidthHeight(JInternalFrame jif) {
        int width = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width - jif.getWidth()) / 2;
        int Height = (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height - jif.getHeight()) / 4;
        jif.setLocation(width, Height);
    }

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
            java.util.logging.Logger.getLogger(MainDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainDesktop().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane DesktopPaneMain;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}
