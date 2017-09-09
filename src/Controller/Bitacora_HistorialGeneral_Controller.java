/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TBitacora;
import Entity.TCuota;
import Entity.TDatosBasicosPersona;
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
public final class Bitacora_HistorialGeneral_Controller extends Controllers {

    class Prestamo extends TPrestamo {

        private String tipoAccion;
        private Date fechaAccion;
        private TDatosBasicosPersona TDatosBasicosPersonaBitacora;

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

        public TDatosBasicosPersona getTDatosBasicosPersonaBitacora() {
            return TDatosBasicosPersonaBitacora;
        }

        public void setTDatosBasicosPersonaBitacora(TDatosBasicosPersona tDatosBasicosPersona) {
            this.TDatosBasicosPersonaBitacora = tDatosBasicosPersona;
        }
    }

    class Abono extends TCuota {

        private String tipoAccion;
        private Date fechaAccion;
        private TDatosBasicosPersona TDatosBasicosPersonaBitacora;

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

        public TDatosBasicosPersona getTDatosBasicosPersonaBitacora() {
            return TDatosBasicosPersonaBitacora;
        }

        public void setTDatosBasicosPersonaBitacora(TDatosBasicosPersona tDatosBasicosPersona) {
            this.TDatosBasicosPersonaBitacora = tDatosBasicosPersona;
        }
    }
    
    private final Persona_Model pModel;
    private final Bitacora_Model bModel;
    private final Bitacora_HistorialGeneral vistaHistorial;
    private List<TBitacora> listBitacora;
    private List<Prestamo> listPrestamos;
    private List<Abono> listCuotas;
    private final Gson gson;
    private int idPersona;

    public int obtenerRadiobuttonSeleccionado() {
        if (vistaHistorial.jRadioButton1.isSelected() == true) {
            return 1;
        } else {
            return 2;
        }
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public Bitacora_HistorialGeneral_Controller(Bitacora_HistorialGeneral vistaHistorial) {
        pModel = new Persona_Model();
        bModel = new Bitacora_Model();
        gson = new Gson();
        this.vistaHistorial = vistaHistorial;
        refrescar();
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

    public void cargarPrestamosCliente() {
        DefaultTableModel model = (DefaultTableModel) vistaHistorial.jTablePrestamo.getModel();
        model.setNumRows(0);
        listPrestamos.stream().forEach((p) -> {
            if (p.getTPersona().getTperId() == idPersona && p.getTipoAccion().equals("AGREGO")) {
                model.addRow((new String[]{"", p.getTpreId().toString(), "" + p.getTpreValorPrestamo(), "" + p.getTpreNumCuotas(), "" + p.getTpreIntereses(), p.getTpreMetodPago(), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(p.getTpreFechaEntrega()), "" + p.getTpreValorTotal(), "" + p.getTpreValorCuota()}));
            }
        });
        vistaHistorial.jTextTotalPrestamos.setText("" + Math.round(totalDeUnaTabla(model, 2)));
    }

    public void cargarAbonos(int idPretsamo) {
        DefaultTableModel model = (DefaultTableModel) vistaHistorial.jTableAbonos.getModel();
        model.setNumRows(0);
        listCuotas.stream().forEach((a) -> {
            if (obtenerRadiobuttonSeleccionado() == 1) {
                if (a.getTPrestamo().getTpreId() == idPretsamo && a.getTipoAccion().equals("AGREGO")) {
                    model.addRow((new String[]{a.getTcuoId().toString(), "", "" + a.getTcuoAbono(), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(a.getTcuoFecha()), a.getTCobrador().getTcobNombre(), a.getTPago().getTipo(), "" + a.getTcuoNuevoSaldo(), "" + a.getTcuoCuotasPagadas()}));
                }
            } else {
                if (a.getTPrestamo().getTPersona().getTperId() == idPersona) {
                    model.addRow((new String[]{a.getTcuoId().toString(), "", "" + a.getTcuoAbono(), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(a.getTcuoFecha()), a.getTCobrador().getTcobNombre(), a.getTPago().getTipo(), "" + a.getTcuoNuevoSaldo(), "" + a.getTcuoCuotasPagadas()}));
                }
            }
        });
        vistaHistorial.jTextTotalAbonos.setText("" + Math.round(totalDeUnaTabla(model, 2)));
    }

    public void cargarHistrialPrestamo(int idPrestamo) {
        DefaultTableModel model = (DefaultTableModel) vistaHistorial.jTableCambiosPrestamo.getModel();
        model.setNumRows(0);
        listPrestamos.stream().forEach((p) -> {
            if (p.getTpreId() == idPrestamo) {
                model.addRow((new String[]{p.getTpreId().toString(), p.getTPersona().getTDatosBasicosPersona().getTdbpNombre() + " " + p.getTPersona().getTDatosBasicosPersona().getTdbpApellido(),
                    p.getTDatosBasicosPersonaBitacora().getTdbpNombre() + " " + p.getTDatosBasicosPersonaBitacora().getTdbpApellido(), p.getTpreMetodPago(),
                    new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(p.getTpreFechaEntrega()), "" + p.getTpreNumCuotas(), "" + p.getTpreIntereses(), "" + p.getTpreValorPrestamo(),
                    "" + p.getTpreValorCuota(), "" + p.getTpreValorTotal(), p.getTipoAccion(), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(p.getFechaAccion())}));
            }
        });
    }

    public void cargarHistorialAbono(int idAbono) {
        DefaultTableModel model = (DefaultTableModel) vistaHistorial.jTableCambiosAbonos.getModel();
        model.setNumRows(0);
        listCuotas.stream().forEach((a) -> {
            if (a.getTcuoId() == idAbono) {
                model.addRow((new String[]{a.getTcuoId().toString(),
                    a.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpNombre() + "" + a.getTPrestamo().getTPersona().getTDatosBasicosPersona().getTdbpApellido(),
                    a.getTipoAccion(), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(a.getTcuoFecha()), "" + a.getTcuoAbono(), "" + a.getTcuoCuotasPagadas(),
                    a.getTPago().getTipo(), a.getTCobrador().getTcobNombre(), a.getTDatosBasicosPersonaBitacora().getTdbpNombre() + " " + a.getTDatosBasicosPersonaBitacora().getTdbpApellido(), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(a.getFechaAccion())}));
            }
        });
    }

    public void refrescar() {
        listBitacora = bModel.consultarAllModulo("ABONO");
        listPrestamos = listBitacora.stream().filter((bitaacora) -> bitaacora.getTbitClassname().equals("Entity.TPrestamo")).map((bitacora) -> {
            Prestamo p = gson.fromJson(bitacora.getTbitRegistro(), Prestamo.class);
            p.setFechaAccion(bitacora.getTbitFecha());
            p.setTipoAccion(bitacora.getTbitIdentificador());
            p.setTDatosBasicosPersonaBitacora(bitacora.getTLogin().getTDatosBasicosPersona());
            return p;
        }).collect(Collectors.toList());
        listCuotas = listBitacora.stream().filter((bitaacora) -> bitaacora.getTbitClassname().equals("Entity.TCuota")).map((bitacora) -> {
            Abono a = gson.fromJson(bitacora.getTbitRegistro(), Abono.class);
            a.setFechaAccion(bitacora.getTbitFecha());
            a.setTipoAccion(bitacora.getTbitIdentificador());
            a.setTDatosBasicosPersonaBitacora(bitacora.getTLogin().getTDatosBasicosPersona());
            return a;
        }).collect(Collectors.toList());
    }

    public void limpiar() {
        cargarPrestamosCliente();
        ((DefaultTableModel) vistaHistorial.jTablePrestamo.getModel()).setNumRows(0);
        ((DefaultTableModel) vistaHistorial.jTableAbonos.getModel()).setNumRows(0);
        ((DefaultTableModel) vistaHistorial.jTableCambiosPrestamo.getModel()).setNumRows(0);
        ((DefaultTableModel) vistaHistorial.jTableCambiosAbonos.getModel()).setNumRows(0);
        vistaHistorial.jTextTotalAbonos.setText("0");
        vistaHistorial.jTextTotalPrestamos.setText("0");
        vistaHistorial.jLabel1.setText("No ha seleccionado un prestamo para ver sus cambios");
        vistaHistorial.jLabel4.setText("No ha seleccionado un abono para ver sus cambios");
    }
}
