/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Backup {
    public static void execute(){
         try {
            String[] tablas = {"t_bitacora","t_cobrador","t_cuota","t_datos_basicos_persona","t_gasto","t_login","t_persona","t_prestamo","t_referencia","t_multa","t_pago"};
            Calendar c1 = Calendar.getInstance();
            String fechaactual = c1.get(Calendar.YEAR) + "-" + (c1.get(Calendar.MONTH) + 1) + "-" + c1.get(Calendar.DATE);
            File archivo = new File("exp.bat");
            try (FileWriter escribir = new FileWriter(archivo, true)) {
                File fecha = new File("backup/"+fechaactual);
                fecha.mkdirs();
                for (int i = 0; i < tablas.length; i++) {
                    escribir.write("@echo off\n mysql -h 192.168.1.12 -u droti --password=admin13082016 drop_db -e \"SELECT * FROM "+tablas[i]+"\" -B > backup/"+fechaactual+"/"+tablas[i]+".csv \n");
                }
                escribir.write(" exit");
            }
            Process p = Runtime.getRuntime().exec("cmd.exe /C start exp.bat -cp");
            JOptionPane.showMessageDialog(null, "Backup creado exitosamente");
            archivo.delete();
        } catch (Exception e) {
            System.out.println("Error al escribir");
        }
    }
}
