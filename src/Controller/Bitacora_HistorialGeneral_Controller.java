/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TBitacora;
import Entity.TCuota;
import Entity.TMulta;
import Entity.TPersona;
import Entity.TPrestamo;
import Model.Bitacora_Model;
import Model.Persona_Model;
import UI.Bitacora_HistorialGeneral;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ITERIA
 */
public class Bitacora_HistorialGeneral_Controller extends Controllers {

    class Prestamo extends TPrestamo {

        private String tipoAccion;
        private Date fechaAccion;

        public String getTipoAccion() {
            return tipoAccion;
        }

        public void setTipoAccion(String tipoAccion) {
            this.tipoAccion = tipoAccion;
        }

        public Date getFechaAccion() {
            return fechaAccion;
        }

        public void setFechaAccion(Date fechaAccion) {
            this.fechaAccion = fechaAccion;
        }
    }

    class Abono extends TCuota {

        private String tipoAccion;
        private Date fechaAccion;

        public String getTipoAccion() {
            return tipoAccion;
        }

        public void setTipoAccion(String tipoAccion) {
            this.tipoAccion = tipoAccion;
        }

        public Date getFechaAccion() {
            return fechaAccion;
        }

        public void setFechaAccion(Date fechaAccion) {
            this.fechaAccion = fechaAccion;
        }
    }
    private final Persona_Model pModel;
    private final Bitacora_Model bModel;
    private final Bitacora_HistorialGeneral vistaHistorial;
    private final List<TBitacora> listBitacora;
    private List<Prestamo> listPrestamos;
    private List<Abono> listCuotas;
    private List<TMulta> listMultas;
    private final Gson gson;

    public Bitacora_HistorialGeneral_Controller(Bitacora_HistorialGeneral vistaHistorial) {
        pModel = new Persona_Model();
        bModel = new Bitacora_Model();
        gson = new Gson();
        listBitacora = bModel.consultarAllModulo("ABONO");
        this.vistaHistorial = vistaHistorial;
        cargarClientes();
    }

    private void cargarClientes() {
        TPersona persona = new TPersona();
        persona.setTperTipo("CLIENTE");
        List<TPersona> clientes = pModel.SelectAllWhere(persona);
        DefaultTableModel model = (DefaultTableModel) vistaHistorial.jtbClientes.getModel();
        clientes.stream().forEach((p) -> {
            model.addRow((new String[]{"", p.getTDatosBasicosPersona().getTdbpCedula(), p.getTDatosBasicosPersona().getTdbpNombre() + " " + p.getTDatosBasicosPersona().getTdbpApellido(), p.getTperId().toString()}));
        });
        numerarTabla(model);
    }

    public void cargarPrestamosCliente(int idPersona) {
        DefaultTableModel model = (DefaultTableModel) vistaHistorial.jTablePrestamo.getModel();
        model.setNumRows(0);
        listPrestamos = listBitacora.stream().filter((bitaacora) -> bitaacora.getTbitClassname().equals("Entity.TPrestamo")).map((bitacora) -> {
            Prestamo p = gson.fromJson(bitacora.getTbitRegistro(), Prestamo.class);
            p.setFechaAccion(bitacora.getTbitFecha());
            p.setTipoAccion(bitacora.getTbitIdentificador());
            return p;
        }).collect(Collectors.toList());
        listPrestamos.stream().forEach((p) -> {
            if (p.getTPersona().getTperId() == idPersona) {
                model.addRow((new String[]{"", p.getTpreId().toString(), "" + p.getTpreValorPrestamo(), "" + p.getTpreNumCuotas(), "" + p.getTpreIntereses(), p.getTpreMetodPago(), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(p.getTpreFechaEntrega()), "" + p.getTpreValorTotal(), "" + p.getTpreValorCuota()}));
            }
        });
        vistaHistorial.jTextTotalPrestamos.setText("" + Math.round(totalDeUnaTabla(model, 2)));
    }

    public void cargarAbonos(int idPretsamo) {
    }
}
