/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Backup {
    public static void execute(){
        try {
            String[] tablas = {"t_banco","t_bitacora","t_cierre","t_cobrador","t_cuenta_pagar","t_cuota","t_datos_basicos_persona","t_gasto","t_login","t_movimiento_banco","t_movimiento_cuenta","t_multa","t_pago","t_persona","t_prestamo","t_reajuste","t_referencia","t_refinanciacion","t_remanente"};
            Calendar c1 = Calendar.getInstance();
            String fechaactual = c1.get(Calendar.YEAR) + "-" + (c1.get(Calendar.MONTH) + 1) + "-" + c1.get(Calendar.DATE);
            File archivo = new File("exp.bat");
            try (FileWriter escribir = new FileWriter(archivo, true)) {
                File fecha = new File("backup/" + fechaactual);
                fecha.mkdirs();
                for (String tabla : tablas) {
                    escribir.write("@echo off\n mysql -h localhost -u droti --password=admin13082016 drop_db -e \"SELECT * FROM " + tabla + "\" -B > backup/" + fechaactual + "/" + tabla + ".csv \n ");
                }
                escribir.write(" exit");
            }
            Process p = Runtime.getRuntime().exec("cmd.exe /C start exp.bat -cp");
            JOptionPane.showMessageDialog(null, "Backup creado exitosamente");
            archivo.delete();
        } catch (HeadlessException | IOException e) {
            System.out.println("Error al escribir");
        }
    }
}
