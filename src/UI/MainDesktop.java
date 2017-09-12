/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Controller.Backup;
import Controller.Login_Controller;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

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
        jMenu2.setText(Login_Controller.getUsuarioLogueado().getTDatosBasicosPersona().getTdbpNombre() + " " + Login_Controller.getUsuarioLogueado().getTDatosBasicosPersona().getTdbpApellido());
        jMenuBar1.add(jMenu2);
        if (!Login_Controller.getUsuarioLogueado().getTDatosBasicosPersona().getTdbpCedula().equals("31922118")) {
            jMenuItem1.setVisible(false);
            jMenuItem21.setVisible(false);
            jMenu5.setVisible(false);
        }
        //Maximizar la ventana en inicio
        //this.setExtendedState(MAXIMIZED_BOTH);
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

        ImageIcon icon = new ImageIcon(getClass().getResource("/Icons/menu1366.png"));
        Image image = icon.getImage();
        DesktopPaneMain = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(image,0,0,getWidth(),getHeight(),this);}
        };
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Drop");
        setExtendedState(6);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icons/icono.png")));
        setMinimumSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().getSize());

        DesktopPaneMain.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout DesktopPaneMainLayout = new javax.swing.GroupLayout(DesktopPaneMain);
        DesktopPaneMain.setLayout(DesktopPaneMainLayout);
        DesktopPaneMainLayout.setHorizontalGroup(
            DesktopPaneMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1008, Short.MAX_VALUE)
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

        jMenuItem18.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/user.png"))); // NOI18N
        jMenuItem18.setText("Gestion de Clientes");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem18);

        jMenuBar1.add(jMenu4);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/prestamo.png"))); // NOI18N
        jMenu3.setText("Prestamo");
        jMenu3.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jMenu3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jMenuItem4.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/prestamo.png"))); // NOI18N
        jMenuItem4.setText("Prestar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem7.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/monedas.png"))); // NOI18N
        jMenuItem7.setText("Abonar");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem16.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cancel.png"))); // NOI18N
        jMenuItem16.setText("Interes Extra");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem16);

        jMenuItem1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/eliminar.png"))); // NOI18N
        jMenuItem1.setText("Eliminar Prestamo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem21.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/eliminar.png"))); // NOI18N
        jMenuItem21.setText("Eliminar Cuota");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem21);

        jMenuItem25.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/refresh.png"))); // NOI18N
        jMenuItem25.setText("Reajuste");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem25);

        jMenuItem27.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/monedas.png"))); // NOI18N
        jMenuItem27.setText("Antiguo Abono");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem27);

        jMenuBar1.add(jMenu3);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/monedas.png"))); // NOI18N
        jMenu1.setText("Gastos");
        jMenu1.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jMenuItem5.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/1496118261_Money.png"))); // NOI18N
        jMenuItem5.setText("Gestion de Gastos");
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
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/usuario.png"))); // NOI18N
        jMenuItem6.setText("Gestion de Usuarios");
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

        jMenuItem9.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/1496118189_circle-clock-time-history-recent-2-outline-stroke.png"))); // NOI18N
        jMenuItem9.setText("Historial Inicio de Sesion");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem9);

        jMenuItem10.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/1496118196_circle-clock-time-history-recent-2-glyph.png"))); // NOI18N
        jMenuItem10.setText("Historial de Prestamos");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem10);

        jMenuItem12.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/1496118212_circle-clock-time-history-recent-outline-stroke.png"))); // NOI18N
        jMenuItem12.setText("Historial de Clientes");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem12);

        jMenuItem14.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/1496118202_circle-clock-time-history-recent-glyph.png"))); // NOI18N
        jMenuItem14.setText("Historial de Gastos");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem14);

        jMenuItem15.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/1496118207_alarm_alert_clock_event_history_schedule_time_watch.png"))); // NOI18N
        jMenuItem15.setText("Historial de Abonos");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem15);

        jMenuItem17.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/1496118202_circle-clock-time-history-recent-glyph.png"))); // NOI18N
        jMenuItem17.setText("Historial Interes extra");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem17);

        jMenuItem26.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/1496118189_circle-clock-time-history-recent-2-outline-stroke.png"))); // NOI18N
        jMenuItem26.setText("Historial General de Cliente");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem26);

        jMenuBar1.add(jMenu6);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/signing.png"))); // NOI18N
        jMenu7.setText("Informes");
        jMenu7.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N

        jMenuItem11.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/selec_row.png"))); // NOI18N
        jMenuItem11.setText("Prestamos Cliente");
        jMenuItem11.setPreferredSize(new java.awt.Dimension(241, 30));
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem11);

        jMenuItem13.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/selec_row.png"))); // NOI18N
        jMenuItem13.setText("General");
        jMenuItem13.setPreferredSize(new java.awt.Dimension(141, 30));
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem13);

        jMenuItem23.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/selec_row.png"))); // NOI18N
        jMenuItem23.setText("Prestamos Fecha");
        jMenuItem23.setPreferredSize(new java.awt.Dimension(141, 30));
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem23);

        jMenuItem24.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/selec_row.png"))); // NOI18N
        jMenuItem24.setText("Abonos Fecha");
        jMenuItem24.setPreferredSize(new java.awt.Dimension(141, 30));
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem24);

        jMenuBar1.add(jMenu7);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/bank.png"))); // NOI18N
        jMenu8.setText("Banco");
        jMenu8.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N

        jMenuItem19.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/selec_row.png"))); // NOI18N
        jMenuItem19.setText("Movimientos de Banco");
        jMenuItem19.setPreferredSize(new java.awt.Dimension(248, 30));
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem19);

        jMenuBar1.add(jMenu8);

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/factura.png"))); // NOI18N
        jMenu9.setText("Cuentas * Pagar");
        jMenu9.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N

        jMenuItem22.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/selec_row.png"))); // NOI18N
        jMenuItem22.setText("Movimientos de Cuentas * Pagar");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem22);

        jMenuBar1.add(jMenu9);

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

        jMenuItem8.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/guardar.png"))); // NOI18N
        jMenuItem8.setText("Backup");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem20.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/info.png"))); // NOI18N
        jMenuItem20.setText("Informacion");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem20);

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

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        int Respuesta = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea cerrar la sesión?", "Advertencia", JOptionPane.YES_NO_OPTION);
        if (Respuesta == JOptionPane.YES_OPTION) {
            Login in = new Login();
            in.setVisible(true);
            in.jProgressBar1.setValue(100);
            in.jPanel1.setVisible(true);
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

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        JInternalFrame in = new Bitacora_UI("CLIENTE");
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        checkInstance(new InformeGeneral());
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        JInternalFrame in = new Bitacora_UI("GASTOS");
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        JInternalFrame in = new Bitacora_UI("ABONO");
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        JInternalFrame in = new Multa_Ui();
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        JInternalFrame in = new Bitacora_UI("MULTA");
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        JInternalFrame in = new Persona_UI();
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        JInternalFrame in = new Banco_UI();
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JInternalFrame in = new EliminarP_UI();
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        JInternalFrame in = new About().cargarVentana();
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        JInternalFrame in = new EliminarA_UI();
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        // TODO add your handling code here:
        JInternalFrame in = new Cuentas_UI();
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        JInternalFrame in = new PrestamosyAbonos_x_fecha_UI("PRESTAMO");
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        JInternalFrame in = new PrestamosyAbonos_x_fecha_UI("ABONO");
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        JInternalFrame in = new ReajustePrestamo_UI();
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        JInternalFrame in = new Bitacora_HistorialGeneral();
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        // TODO add your handling code here:
        JInternalFrame in = new Abonos();
        checkInstance(in);
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    public static void checkInstance(Object ven) {
        JInternalFrame InternalWindow = (JInternalFrame) ven;
        DesktopPaneMain.add(InternalWindow);
        calcWidthHeight(InternalWindow);
        InternalWindow.show();

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
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}
