/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.Controllers.numerarTabla;
import Entity.TCuota;
import Entity.TDatosBasicosPersona;
import Entity.TPersona;
import Entity.TPrestamo;
import Entity.TReferencia;
import Model.Persona_Model;
import UI.InformeCliente;
import UI.Persona_UI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

/**
 *
 * @author afvc2203
 */
public class Persona_ extends Controllers {

    private Persona_UI perUI;
    private List<TPersona> listPer;
    private final Persona_Model perModel;
    private TPersona perCliente, perCodeudor;
    private TDatosBasicosPersona dbpCliente, dbpCodeudor;

    private DatosBasicosPersona_Controller dbp_Controller;
    private Referencia_Controller ref__Controller;

    private InformeCliente infCli;

    private DefaultTableModel dtm;
    private TableRowSorter<TableModel> tr;

    public Persona_() {
        perModel = new Persona_Model();
        dbp_Controller = new DatosBasicosPersona_Controller();
        ref__Controller = new Referencia_Controller(null, null);
    }

    public Persona_(InformeCliente infCli) {
        this.infCli = infCli;
        perModel = new Persona_Model();
        dbp_Controller = new DatosBasicosPersona_Controller();
    }

    public Persona_(Persona_UI perUI) {
        this.perUI = perUI;
        perModel = new Persona_Model();
        perCliente = new TPersona();
        dbp_Controller = new DatosBasicosPersona_Controller();
        ref__Controller = new Referencia_Controller(null, null);
    }

//<editor-fold defaultstate="collapsed" desc="GET and SET">
    public Persona_UI getPerUI() {
        return perUI;
    }

    public void setPerUI(Persona_UI perUI) {
        this.perUI = perUI;
    }

    public List<TPersona> getListPer() {
        return listPer;
    }

    public void setListPer(List<TPersona> listPer) {
        this.listPer = listPer;
    }

    public TPersona getPerCliente() {
        return perCliente;
    }

    public void setPerCliente(TPersona perCliente) {
        this.perCliente = perCliente;
    }

    public TDatosBasicosPersona getDbpCliente() {
        return dbpCliente;
    }

    public void setDbpCliente(TDatosBasicosPersona dbpCliente) {
        this.dbpCliente = dbpCliente;
    }

    public DatosBasicosPersona_Controller getDbp_Controller() {
        return dbp_Controller;
    }

    public void setDbp_Controller(DatosBasicosPersona_Controller dbp_Controller) {
        this.dbp_Controller = dbp_Controller;
    }

    public Referencia_Controller getRef__Controller() {
        return ref__Controller;
    }

    public void setRef__Controller(Referencia_Controller ref__Controller) {
        this.ref__Controller = ref__Controller;
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }

    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }

    public TableRowSorter<TableModel> getTr() {
        return tr;
    }

    public void setTr(TableRowSorter<TableModel> tr) {
        this.tr = tr;
    }

    public TDatosBasicosPersona getDbpCodeudor() {
        return dbpCodeudor;
    }

    public void setDbpCodeudor(TDatosBasicosPersona dbpCodeudor) {
        this.dbpCodeudor = dbpCodeudor;
    }

    public TPersona getPerCodeudor() {
        return perCodeudor;
    }

    public void setPerCodeudor(TPersona perCodeudor) {
        this.perCodeudor = perCodeudor;
    }

    public InformeCliente getInfCli() {
        return infCli;
    }

    public void setInfCli(InformeCliente infCli) {
        this.infCli = infCli;
    }

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="prepareSelectCliente">
    public void prepareSelectCliente(String cC) {

        //Objeto Datos Basicos de Persona
        TDatosBasicosPersona tdba = new TDatosBasicosPersona();
        tdba.setTdbpCedula(cC);

        //Objeto Persona
        TPersona tper = new TPersona();
        tper.setTDatosBasicosPersona(tdba);
        tper.setTperTipo("CLIENTE");

        TPersona res = selectOne(tper);
        if (res != null) {
            cleanDataJTextCliente();
            cleanDataJTextCodeudor(); //Limpiar Campos de Texto
           
            getPerUI().btnEditar.setEnabled(true);
            enabledForEdit(false);
            colorJText(new java.awt.Color(205, 205, 255));
            

            setDataJTextCliente(res); //Insertar datos en Capos de Texto

            if (res.getTperCodeudor() != null) {
                prepareSelectCodeudor(res.getTperCodeudor()); //Traer Codeudor
            }
        } else {
            int r = JOptionPane.showConfirmDialog(null, "Cliente no Registrado... ¿Desea registrar?", "Cliente NO registrado", JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {

                
                
            }

        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="prepareSelectCodeudor">
    public void prepareSelectCodeudor(String cC) {

        //Objeto Datos Basicos de Persona
        TDatosBasicosPersona tdba = new TDatosBasicosPersona();
        tdba.setTdbpCedula(cC);

        //Objeto Persona
        TPersona tper = new TPersona();
        tper.setTDatosBasicosPersona(tdba);

        TPersona res = selectOne(tper);
        if (res != null) {
            setDataJTextCodeudor(res); //Insertar datos en Capos de Texto

        }
    }
//</editor-fold>   

    public void processClientForm() {
        //--CODEUDOR
        if (getPerUI().objectCodeudor.getTperId() == null) { //Si ID objeto codeudor es null PrepareInsert
            if (getPerUI().jtfCedulaCodeudor.getText().trim().length() != 0) { //Si el capo cedula de codeudor no esta vacio
                if (validar(true)) {
                    createObjectCodeudorTo(); //Crear objeto Codeudor
                    setDbpCodeudor(dbp_Controller.insert(getDbpCodeudor())); // Insertar datos basicos y Obtener ID

                    if (getDbpCodeudor().getTdbpId() != null) { //Si los datos basicos se registraron correctamente
                        getPerCodeudor().getTDatosBasicosPersona().setTdbpId(getDbpCodeudor().getTdbpId()); //Agregamos al objeto Codeudor el ID de sus datos basicos

                        if (insert(getPerCodeudor()) != 0) { //Si se registro correctamente el codeudor

                            //--REFERENCIAS CODEUDOR -> INSERT 
                            if (getPerUI().objectRefeCod.size() > 0) {
                                for (int i = 0; i < getPerUI().objectRefeCli.size(); i++) {
                                    getPerUI().objectRefeCli.get(i).setTDatosBasicosPersona(getDbpCliente());

                                    getRef__Controller().prepareInsert(getPerUI().objectRefeCli.get(i));

                                }
                            }
                            //--END REFERENCIAS CODEUDOR -> INSERT 

                            client(getPerCodeudor().getTDatosBasicosPersona().getTdbpCedula());
                        }
                    }
                }
            } else { //Si el capo cedula de CODEUDOR esta vacio
                client(null);
            }
        } else //Si ID objeto codeudor NO es null PrepareUpdate
        {
            if (getPerUI().jtfCedulaCodeudor.getText().trim().length() != 0) { //Si el capo cedula de codeudor no esta vacio
                if (validar(true)) {
                    createObjectCodeudorTo(); //Crear objeto Codeudor
                    getDbpCodeudor().setTdbpId(getPerUI().objectCodeudor.getTDatosBasicosPersona().getTdbpId()); //Obtener ID de datos Basicos

                    if (getDbp_Controller().update(getDbpCodeudor())) {
                        getPerCodeudor().setTDatosBasicosPersona(getDbpCodeudor()); //Agregar datos basicos al objeto CODEUDOR
                        getPerCodeudor().setTperId(getPerUI().objectCodeudor.getTperId());

                        getPerCodeudor().setTperTipo(getPerUI().objectCodeudor.getTperTipo());
                        getPerCodeudor().setTperCodeudor(getPerUI().objectCodeudor.getTperCodeudor());
                        if (update(getPerCodeudor())) {

                            //--REFERENCIAS CODEUDOR -> UPDATE 
                            if (getPerUI().objectRefeCod.size() > 0) {
                                for (int i = 0; i < getPerUI().objectRefeCod.size(); i++) {
                                    getPerUI().objectRefeCod.get(i).setTDatosBasicosPersona(getDbpCodeudor());
                                    if (getPerUI().objectRefeCod.get(i).getTrefId() != null) {
                                        getRef__Controller().update(getPerUI().objectRefeCod.get(i));
                                    } else {
                                        getRef__Controller().prepareInsert(getPerUI().objectRefeCod.get(i));
                                    }
                                }
                            }
                            if (getPerUI().refDeleteCode.size() > 0) {
                                for (int i = 0; i < getPerUI().refDeleteCode.size(); i++) {
                                    TReferencia reElimin = new TReferencia();
                                    reElimin.setTrefId(getPerUI().refDeleteCode.get(i));
                                    reElimin.setTDatosBasicosPersona(getDbpCodeudor());
                                    getRef__Controller().prepareDelete(reElimin);
                                }
                            }

                            //--END REFERENCIAS CODEUDOR -> UPDATE
                            client(getPerCodeudor().getTDatosBasicosPersona().getTdbpCedula());
                        }

                    }
                }
            }
        }
        //--END CODEUDOR
    }

    public void client(String cCodeudor) {
        //--CLIENTE
        if (getPerUI().objectCliente.getTperId() == null) { //Si ID objeto CLIENTE es null PrepareInsert
            if (getPerUI().jtfCedulaCliente.getText().trim().length() != 0) { //Si el capo cedula de CLIENTE no esta vacio

                if (validar(false)) {
                    createObjectClienteTo(); //Crar objeto Persona
                    setDbpCliente(dbp_Controller.insert(getDbpCliente())); //Obtener ID de datos Basicos

                    if (getDbpCliente().getTdbpId() != null) { //Si se registro correctamente el CLIENTE
                        getPerCliente().getTDatosBasicosPersona().setTdbpId(getDbpCliente().getTdbpId()); //Agregamos al objeto CODEUDOR el ID de sus datos basicos

                        //getPerCliente().setTperCodeudor(getPerCodeudor().getTDatosBasicosPersona().getTdbpCedula()); //Agregar CODEUDOR al CLIENTE
                        getPerCliente().setTperCodeudor(cCodeudor); //Agregar CODEUDOR al CLIENTE

                        if (insert(getPerCliente()) != 0) { //Si se registro correctamente el CLIENTE

                            //--REFERENCIAS CLIENTE -> INSERT
                            if (getPerUI().objectRefeCod.size() > 0) {
                                for (int i = 0; i < getPerUI().objectRefeCli.size(); i++) {
                                    getPerUI().objectRefeCli.get(i).setTDatosBasicosPersona(getDbpCliente());

                                    getRef__Controller().prepareInsert(getPerUI().objectRefeCli.get(i));

                                }
                            }
                            //--END REFERENCIAS CLIENTE -> INSERT

                            JOptionPane.showMessageDialog(null, "Datos Guardados con exito", "Datos Guardados", JOptionPane.INFORMATION_MESSAGE);
                            initTable(getPerUI().jtbClientes);
                           
                            enabledForEdit(false);
                            colorJText(new java.awt.Color(205, 205, 255));
                            cleanDataJTextCliente();
                            cleanDataJTextCodeudor();
                            
                            
                        }
                    }
                }
            }
        } else //Si ID objeto CLIENTE es no es null PrepareUpdate
        {
            if (getPerUI().jtfCedulaCliente.getText().trim().length() != 0) { //Si el capo cedula de CLIENTE no esta vacio
                if (validar(false)) {
                    createObjectClienteTo(); //Crar objeto Persona
                    getDbpCliente().setTdbpId(getPerUI().objectCliente.getTDatosBasicosPersona().getTdbpId()); //Obtener ID de datos Basicos

                    if (getDbp_Controller().update(getDbpCliente())) {
                        getPerCliente().setTDatosBasicosPersona(getDbpCliente()); //Agregar datos basicos al objeto Cliente
                        getPerCliente().setTperId(getPerUI().objectCliente.getTperId());
                        //getPerCliente().setTperCodeudor(getPerCodeudor().getTDatosBasicosPersona().getTdbpCedula()); //Agregar CODEUDOR al CLIENTE
                        getPerCliente().setTperCodeudor(cCodeudor); //Agregar CODEUDOR al CLIENTE

                        if (update(getPerCliente())) {

                            //--REFERENCIAS CLIENTE -> UPDATE 
                            if (getPerUI().objectRefeCli.size() > 0) {
                                for (int i = 0; i < getPerUI().objectRefeCli.size(); i++) {
                                    getPerUI().objectRefeCli.get(i).setTDatosBasicosPersona(getDbpCliente());
                                    if (getPerUI().objectRefeCli.get(i).getTrefId() != null) {
                                        getRef__Controller().update(getPerUI().objectRefeCli.get(i));
                                    } else {
                                        getRef__Controller().prepareInsert(getPerUI().objectRefeCli.get(i));
                                    }
                                }
                            }
                            if (getPerUI().refDeleteClie.size() > 0) {
                                for (int i = 0; i < getPerUI().refDeleteClie.size(); i++) {
                                    TReferencia reElimin = new TReferencia();
                                    reElimin.setTrefId(getPerUI().refDeleteClie.get(i));
                                    reElimin.setTDatosBasicosPersona(getDbpCliente());
                                    getRef__Controller().prepareDelete(reElimin);
                                }
                            }
                            //--END REFERENCIAS CLIENTE -> UPDATE

                            JOptionPane.showMessageDialog(getPerUI(), "Datos Guardados con exito", "Datos Guardados", JOptionPane.INFORMATION_MESSAGE);
                            initTable(getPerUI().jtbClientes);

                            enabledForEdit(false);
                            colorJText(new java.awt.Color(205, 205, 255));
                            cleanDataJTextCliente();
                            cleanDataJTextCodeudor();
                        }
                    }
                }
            }
        }
        //--END CLIENTE
    }

//<editor-fold defaultstate="collapsed" desc="SELECT all WHERE">
    public void selectAll(TPersona objPer) {
        setListPer(perModel.SelectAllWhere(objPer));
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="SELECT one">
    public TPersona selectOne(TPersona objPer) {
        TPersona tper = null;
        try {
            //Resultado de Consulta
            tper = perModel.SelectOne(objPer);
            return tper;
        } catch (Exception e) {
            printStackTrace(e);
        }
        return tper;
    }
//</editor-fold>      

//<editor-fold defaultstate="collapsed" desc="Method to INSERT return boolean">
    public int insert(TPersona objPer) {
        return Integer.parseInt("" + perModel.insertar(objPer, "CLIENTE"));
    }
//</editor-fold>      

//<editor-fold defaultstate="collapsed" desc="Method to UPDATE">
    public boolean update(TPersona objPer) {
        return perModel.editar(objPer, "CLIENTE");
    }
//</editor-fold>      

//<editor-fold defaultstate="collapsed" desc="TABLE init Cliente">
    public void initTable(JTable jt) {

        //Objeto Persona
        TPersona temp = new TPersona();
        temp.setTperTipo("CLIENTE");

        //Consulta (Traer todos las pesonas que sean clientes)
        selectAll(temp);
        
        ArrayList<TDatosBasicosPersona> listtdbTemp = new ArrayList<TDatosBasicosPersona>();
        
        for (int l = 0; l < getListPer().size(); l++) {
            listtdbTemp.add(getListPer().get(l).getTDatosBasicosPersona());
        }
        
        listtdbTemp.sort(Comparator.comparing(TDatosBasicosPersona::getTdbpNombre));
        
        setDtm((DefaultTableModel) jt.getModel());

        while (getDtm().getRowCount() > 0) {
            getDtm().removeRow(0); //Limpiar Tabla
        }
        Object[] f = new Object[4];
        for (int i = 0; i < getListPer().size(); i++) {
            f[1] = listtdbTemp.get(i).getTdbpCedula();
            f[2] = listtdbTemp.get(i).getTdbpNombre() + " " + listtdbTemp.get(i).getTdbpApellido();
            getDtm().addRow(f);
        }
        numerarTabla(getDtm());
    }
//</editor-fold> 

//<editor-fold defaultstate="collapsed" desc="TABLE filter Cliente">
    public void filter(JTable jt, String textBuscar, int columna) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(getDtm());
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + textBuscar, columna));
        jt.setRowSorter(tr);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="initObjects to DML">
    public void createObjectClienteTo() {
        dbpCliente = new TDatosBasicosPersona();
        //dbpCliente.setTdbpId(getPerUI().objectCliente.getTDatosBasicosPersona().getTdbpId());
        dbpCliente.setTdbpCedula(getPerUI().jtfCedulaCliente.getText());
        dbpCliente.setTdbpNombre(getPerUI().jtfNombreCliente.getText());
        dbpCliente.setTdbpApellido(getPerUI().jtfApellidoCliente.getText());
        dbpCliente.setTdbpTel(getPerUI().jtfTelefonoCliente.getText());
        dbpCliente.setTdbpCel(getPerUI().jtfCelularCliente.getText());

        perCliente = new TPersona();
        getPerCliente().setTDatosBasicosPersona(dbpCliente);
        getPerCliente().setTperCasDir(getPerUI().jtfCasaDirCliente.getText());
        getPerCliente().setTperCasPro(getPerUI().jtfCasaPropieCliente.getText());
        getPerCliente().setTperCasTipo((String) getPerUI().jcbTipoCasaCliente.getSelectedItem());
        getPerCliente().setTperEmpNom(getPerUI().jtfRazonSocialEmpresaCliente.getText());
        getPerCliente().setTperEmpDir(getPerUI().jtfDireccionEmpresaCliente.getText());
        getPerCliente().setTperEmpTel(getPerUI().jtfTelefonoEmpresaCliente.getText());
        getPerCliente().setTperTipo("CLIENTE");
        getPerCliente().setTperCodeudor(null);
    }

    public void createObjectCodeudorTo() {
        dbpCodeudor = new TDatosBasicosPersona();
        //dbpCodeudor.setTdbpId(getPerUI().objectCodeudor.getTDatosBasicosPersona().getTdbpId());
        dbpCodeudor.setTdbpCedula(getPerUI().jtfCedulaCodeudor.getText());
        dbpCodeudor.setTdbpNombre(getPerUI().jtfNombreCodeudor.getText());
        dbpCodeudor.setTdbpApellido(getPerUI().jtfApellidoCodeudor.getText());
        dbpCodeudor.setTdbpTel(getPerUI().jtfTelefonoCodeudor.getText());
        dbpCodeudor.setTdbpCel(getPerUI().jtfCelularCodeudor.getText());

        perCodeudor = new TPersona();
        perCodeudor.setTDatosBasicosPersona(dbpCodeudor);
        perCodeudor.setTperCasDir(getPerUI().jtfCasaDirCodeudor.getText());
        perCodeudor.setTperCasPro(getPerUI().jtfCasaPropieCodeudor.getText());
        perCodeudor.setTperCasTipo((String) getPerUI().jcbTipoCasaCodeudor.getSelectedItem());
        perCodeudor.setTperEmpNom(getPerUI().jtfRazonSocialEmpresaCodeudor.getText());
        perCodeudor.setTperEmpDir(getPerUI().jtfDireccionEmpresaCodeudor.getText());
        perCodeudor.setTperEmpTel(getPerUI().jtfTelefonolEmpresaCodeudor.getText());
        perCodeudor.setTperTipo(null);
        perCodeudor.setTperCodeudor(null);
    }
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="setDataJText">
    public void setDataJTextCliente(TPersona tper) {
        getPerUI().objectCliente = tper;
        getPerUI().jtfCedulaCliente.setText(tper.getTDatosBasicosPersona().getTdbpCedula());
        getPerUI().jtfNombreCliente.setText(tper.getTDatosBasicosPersona().getTdbpNombre());
        getPerUI().jtfApellidoCliente.setText(tper.getTDatosBasicosPersona().getTdbpApellido());
        getPerUI().jtfCelularCliente.setText(tper.getTDatosBasicosPersona().getTdbpCel());
        getPerUI().jtfCasaDirCliente.setText(tper.getTperCasDir());
        getPerUI().jtfCasaPropieCliente.setText(tper.getTperCasPro());
        getPerUI().jcbTipoCasaCliente.setSelectedItem(tper.getTperCasTipo());
        getPerUI().jtfTelefonoCliente.setText(tper.getTDatosBasicosPersona().getTdbpTel());
        getPerUI().jtfRazonSocialEmpresaCliente.setText(tper.getTperEmpNom());
        getPerUI().jtfDireccionEmpresaCliente.setText(tper.getTperEmpDir());
        getPerUI().jtfTelefonoEmpresaCliente.setText(tper.getTperEmpTel());
    }

    public void setDataJTextCodeudor(TPersona tper) {
        getPerUI().objectCodeudor = tper;
        getPerUI().jtfCedulaCodeudor.setText(tper.getTDatosBasicosPersona().getTdbpCedula());
        getPerUI().jtfNombreCodeudor.setText(tper.getTDatosBasicosPersona().getTdbpNombre());
        getPerUI().jtfApellidoCodeudor.setText(tper.getTDatosBasicosPersona().getTdbpApellido());
        getPerUI().jtfCelularCodeudor.setText(tper.getTDatosBasicosPersona().getTdbpCel());
        getPerUI().jtfCasaDirCodeudor.setText(tper.getTperCasDir());
        getPerUI().jtfCasaPropieCodeudor.setText(tper.getTperCasPro());
        getPerUI().jcbTipoCasaCodeudor.setSelectedItem(tper.getTperCasTipo());
        getPerUI().jtfTelefonoCodeudor.setText(tper.getTDatosBasicosPersona().getTdbpTel());
        getPerUI().jtfRazonSocialEmpresaCodeudor.setText(tper.getTperEmpNom());
        getPerUI().jtfDireccionEmpresaCodeudor.setText(tper.getTperEmpDir());
        getPerUI().jtfTelefonolEmpresaCodeudor.setText(tper.getTperEmpTel());
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="cleanDataJText">
    public void cleanDataJTextCliente() {
        getPerUI().objectCliente = new TPersona();
        //getPerUI().jtfCedulaCliente.setText("");
        getPerUI().jtfNombreCliente.setText("");
        getPerUI().jtfApellidoCliente.setText("");
        getPerUI().jtfCelularCliente.setText("");
        getPerUI().jtfCasaDirCliente.setText("");
        getPerUI().jtfCasaPropieCliente.setText("");
        getPerUI().jcbTipoCasaCliente.setSelectedIndex(0);
        getPerUI().jtfTelefonoCliente.setText("");
        getPerUI().jtfRazonSocialEmpresaCliente.setText("");
        getPerUI().jtfDireccionEmpresaCliente.setText("");
        getPerUI().jtfTelefonoEmpresaCliente.setText("");

        getPerUI().btnRefCliente.setEnabled(false);
    }

    public void cleanDataJTextCodeudor() {

        getPerUI().objectCodeudor = new TPersona();
        getPerUI().jtfCedulaCodeudor.setText("");
        getPerUI().jtfNombreCodeudor.setText("");
        getPerUI().jtfApellidoCodeudor.setText("");
        getPerUI().jtfCelularCodeudor.setText("");
        getPerUI().jtfCasaDirCodeudor.setText("");
        getPerUI().jtfCasaPropieCodeudor.setText("");
        getPerUI().jcbTipoCasaCodeudor.setSelectedIndex(0);
        getPerUI().jtfTelefonoCodeudor.setText("");
        getPerUI().jtfRazonSocialEmpresaCodeudor.setText("");
        getPerUI().jtfDireccionEmpresaCodeudor.setText("");
        getPerUI().jtfTelefonolEmpresaCodeudor.setText("");

        getPerUI().btnRefCodeudor.setEnabled(false);
        getPerUI().btnQuitarCodeudor.setEnabled(false);
    }
//</editor-fold>   

//<editor-fold defaultstate="collapsed" desc="colorJText">
    public void colorJText(java.awt.Color c) {
        getPerUI().jtfNombreCliente.setBackground(c);
        getPerUI().jtfApellidoCliente.setBackground(c);
        getPerUI().jtfCelularCliente.setBackground(c);
        getPerUI().jtfCasaDirCliente.setBackground(c);
        getPerUI().jtfCasaPropieCliente.setBackground(c);
        getPerUI().jtfTelefonoCliente.setBackground(c);
        getPerUI().jtfRazonSocialEmpresaCliente.setBackground(c);
        getPerUI().jtfDireccionEmpresaCliente.setBackground(c);
        getPerUI().jtfTelefonoEmpresaCliente.setBackground(c);

        getPerUI().jtfCedulaCodeudor.setBackground(c);
        getPerUI().jtfNombreCodeudor.setBackground(c);
        getPerUI().jtfApellidoCodeudor.setBackground(c);
        getPerUI().jtfCelularCodeudor.setBackground(c);
        getPerUI().jtfCasaDirCodeudor.setBackground(c);
        getPerUI().jtfCasaPropieCodeudor.setBackground(c);
        getPerUI().jtfTelefonoCodeudor.setBackground(c);
        getPerUI().jtfRazonSocialEmpresaCodeudor.setBackground(c);
        getPerUI().jtfDireccionEmpresaCodeudor.setBackground(c);
        getPerUI().jtfTelefonolEmpresaCodeudor.setBackground(c);
    }
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="enabledForEdit">
    public void enabledForEdit(boolean b) {
        getPerUI().jtfNombreCliente.setFocusable(b);
        getPerUI().jtfApellidoCliente.setFocusable(b);
        getPerUI().jtfCelularCliente.setFocusable(b);
        getPerUI().jtfCasaDirCliente.setFocusable(b);
        getPerUI().jtfCasaPropieCliente.setFocusable(b);
        getPerUI().jcbTipoCasaCliente.setEnabled(b);
        getPerUI().jtfTelefonoCliente.setFocusable(b);
        getPerUI().jtfRazonSocialEmpresaCliente.setFocusable(b);
        getPerUI().jtfDireccionEmpresaCliente.setFocusable(b);
        getPerUI().jtfTelefonoEmpresaCliente.setFocusable(b);

        getPerUI().btnCodeudor.setEnabled(b);
        getPerUI().jtfCedulaCodeudor.setFocusable(b);
        getPerUI().jtfNombreCodeudor.setFocusable(b);
        getPerUI().jtfApellidoCodeudor.setFocusable(b);
        getPerUI().jtfCelularCodeudor.setFocusable(b);
        getPerUI().jtfCasaDirCodeudor.setFocusable(b);
        getPerUI().jtfCasaPropieCodeudor.setFocusable(b);
        getPerUI().jcbTipoCasaCodeudor.setEnabled(b);
        getPerUI().jtfTelefonoCodeudor.setFocusable(b);
        getPerUI().jtfRazonSocialEmpresaCodeudor.setFocusable(b);
        getPerUI().jtfDireccionEmpresaCodeudor.setFocusable(b);
        getPerUI().jtfTelefonolEmpresaCodeudor.setFocusable(b);
        
        //Buttons
        getPerUI().btnRefCliente.setEnabled(b);
        getPerUI().btnRefCodeudor.setEnabled(b);
        getPerUI().btnQuitarCodeudor.setEnabled(b);
        getPerUI().btnGuardar.setEnabled(b);
    }
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="validar">
    public boolean validar(boolean val) {
        String mensaje = "";
        if (getPerUI().jtfCedulaCliente.getText().trim().equals("")) {
            mensaje += "[- Cedula de Cliente esta vacío -] \n";
        }

        if (getPerUI().jtfNombreCliente.getText().trim().equals("")) {
            mensaje += "[- Nombre de Cliente esta vacío -] \n";
        }

        if (getPerUI().jtfApellidoCliente.getText().trim().equals("")) {
            mensaje += "[- Apellido de Cliente esta vacío -] \n";
        }

        if (getPerUI().jtfCasaDirCliente.getText().trim().equals("")) {
            mensaje += "[- Direccion de Cliente esta vacío -] \n \n";
        }

        if (val == true) {
            if (getPerUI().jtfCedulaCodeudor.getText().trim().equals("")) {
                mensaje += "[- Cedula de Codeudor esta vacío -] \n";
            }

            if (getPerUI().jtfNombreCodeudor.getText().trim().equals("")) {
                mensaje += "[-Nombre de Codeudor esta vacío -] \n";
            }

            if (getPerUI().jtfApellidoCodeudor.getText().trim().equals("")) {
                mensaje += "[- Apellido de Codeudor esta vacío -] \n";
            }

            if (getPerUI().jtfCasaDirCodeudor.getText().trim().equals("")) {
                mensaje += "[- Direccion de Codeudor esta vacío -] \n";
            }
        }

        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, "Se Presentaron los siguientes inconvenientes: \n \n" + mensaje, "Error!!", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
//</editor-fold>    

    public void initTablePrestamo(JTable jt, JTable jt2) {

        setDbpCliente(new TDatosBasicosPersona());
        getDbpCliente().setTdbpCedula(String.valueOf(getInfCli().jtbClientes.getValueAt(getInfCli().jtbClientes.getSelectedRow(), 1)));

        setPerCliente(new TPersona());
        getPerCliente().setTDatosBasicosPersona(getDbpCliente());
        getPerCliente().setTperTipo("CLIENTE");

        setPerCliente(selectOne(getPerCliente()));

        Set temp = getPerCliente().getTPrestamos();
        List<TPrestamo> tp = new ArrayList<>();
        tp.addAll(temp);
        
        tp.sort(Comparator.comparing(TPrestamo::getTpreFechaEntrega));
        
        ordenarPrestamo(tp);
        DefaultTableModel dtm = new TableModel().historialPrestamo();
        jt.setModel(dtm);
        
        DefaultTableModel dtm2 = new TableModel().historialCuota();
        jt2.setModel(dtm2);

        Object[] f = new Object[11];
        for (int i = 0; i < tp.size(); i++) {
            f[1] = tp.get(i).getTpreId();
            f[2] = tp.get(i).getTpreValorPrestamo();
            f[3] = tp.get(i).getTpreNumCuotas();
            f[4] = tp.get(i).getTpreIntereses();
            f[5] = tp.get(i).getTpreMetodPago();
            f[6] = tp.get(i).getTpreFechaEntrega();
            f[7] = tp.get(i).getTpreValorTotal();
            f[8] = tp.get(i).getTpreValorCuota();
            f[9] = tp.get(i);
            f[10] = tp.get(i).getTPersona();
            dtm.addRow(f);

        }
        numerarTabla(dtm);
        InformeCliente.text_totalprestamo.setText(totalDeUnaTabla(dtm, 2) + "");
        int[] position = {0, 9, 10};
        setVisibleColumnTable(jt, position);
    }

    public void initTableCuotas(JTable jtbCuota, JTable jtbPrestamo) {
        Set temp = ((TPrestamo) jtbPrestamo.getValueAt(jtbPrestamo.getSelectedRow(), 9)).getTCuotas();
        List<TCuota> tc = new ArrayList<>();

        tc.addAll(temp);
        
        tc.sort(Comparator.comparing(TCuota::getTcuoFecha));

        DefaultTableModel dtm = new TableModel().historialCuota();
        jtbCuota.setModel(dtm);

        Object[] f = new Object[12];
        for (int i = 0; i < tc.size(); i++) {
            f[1] = tc.get(i).getTcuoId();
            f[2] = tc.get(i).getTcuoFecha();
            f[3] = tc.get(i).getTcuoAbono();
            f[4] = tc.get(i).getTcuoNuevoSaldo();
            f[5] = tc.get(i).getTcuoCuotasPagadas();
            f[6] = tc.get(i).getTPago().getTipo();
            f[7] = tc.get(i).getTCobrador().getTcobNombre();
            f[8] = tc.get(i);
            f[9] = tc.get(i).getTPago();
            f[10] = tc.get(i).getTCobrador();
            f[11] = tc.get(i).getTPrestamo();
            dtm.addRow(f);

        }
        numerarTabla(dtm);
        InformeCliente.txt_totalcuota.setText(totalDeUnaTabla(dtm, 3) + "");
        InformeCliente.txt_debe.setText((Integer.parseInt(String.valueOf(jtbPrestamo.getValueAt(jtbPrestamo.getSelectedRow(), 7)))-Float.parseFloat(totalDeUnaTabla(dtm, 3) + ""))+"");
        int[] position = {0, 8, 9, 10, 11};
        setVisibleColumnTable(jtbCuota, position);
    }

    private void ordenarPrestamo(List prestamos) {
        Collections.sort(prestamos, new Comparator<TPrestamo>() {
            public int compare(TPrestamo o1, TPrestamo o2) {
                if (o1.getTpreFechaEntrega() == null || o2.getTpreFechaEntrega() == null) {
                    return 0;
                }
                return o1.getTpreFechaEntrega().compareTo(o2.getTpreFechaEntrega());
            }
        });
    }
}
