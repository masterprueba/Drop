/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TDatosBasicosPersona;
import Entity.TPersona;
import Model.Persona_Model;
import UI.Cliente_UI;
import java.util.List;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

/**
 *
 * @author Andres
 */
public class Persona_Controller {

    private TPersona p;
    private final Persona_Model perModel;
    private final Cliente_UI cli_UI;
    private int ObjectIdAfterInserting;

    private TPersona pCliente;
    private TDatosBasicosPersona dbpCliente;

    private TPersona pCodeudor;
    private TDatosBasicosPersona dbpCodeudor;

    private DatosBasicosPersona_Controller dbp_Controller;

    private Cliente_Controller cli_Controller;
    private Codeudor_Controller cod_Controller;
    private Referencia_Controller ref__Controller;
    private List<TPersona> listPer;

    public Persona_Controller(Cliente_UI cli_UI) {
        this.cli_UI = cli_UI;
        perModel = new Persona_Model();
        dbp_Controller = new DatosBasicosPersona_Controller();
        ref__Controller = new Referencia_Controller(cli_UI, null);
    }

//<editor-fold defaultstate="collapsed" desc="Method SET and GET">
    public TPersona getP() {
        return p;
    }

    public void setP(TPersona p) {
        this.p = p;
    }

    public Cliente_UI getCli_UI() {
        return cli_UI;
    }

    public int getObjectIdAfterInserting() {
        return ObjectIdAfterInserting;
    }

    public void setObjectIdAfterInserting(int ObjectIdAfterInserting) {
        this.ObjectIdAfterInserting = ObjectIdAfterInserting;
    }

    public TPersona getpCliente() {
        return pCliente;
    }

    public void setpCliente(TPersona pCliente) {
        this.pCliente = pCliente;
    }

    public TDatosBasicosPersona getDbpCliente() {
        return dbpCliente;
    }

    public void setDbpCliente(TDatosBasicosPersona dbpCliente) {
        this.dbpCliente = dbpCliente;
    }

    public TPersona getpCodeudor() {
        return pCodeudor;
    }

    public void setpCodeudor(TPersona pCodeudor) {
        this.pCodeudor = pCodeudor;
    }

    public TDatosBasicosPersona getDbpCodeudor() {
        return dbpCodeudor;
    }

    public void setDbpCodeudor(TDatosBasicosPersona dbpCodeudor) {
        this.dbpCodeudor = dbpCodeudor;
    }

    public DatosBasicosPersona_Controller getDbp_Controller() {
        return dbp_Controller;
    }

    public void setDbp_Controller(DatosBasicosPersona_Controller dbp_Controller) {
        this.dbp_Controller = dbp_Controller;
    }

    public Cliente_Controller getCli_Controller() {
        return cli_Controller;
    }

    public Codeudor_Controller getCod_Controller() {
        return cod_Controller;
    }

    public Referencia_Controller getRef__Controller() {
        return ref__Controller;
    }

    public List<TPersona> getListPer() {
        return listPer;
    }

    public void setListPer(List<TPersona> listPer) {
        this.listPer = listPer;
    }

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Method to INSERT return boolean">
    public boolean insert(TPersona objPer) {
        boolean boo = false;
        ObjectIdAfterInserting = Integer.parseInt("" + perModel.insertar(objPer,"TPersona"));

        if (ObjectIdAfterInserting != 0) {
            p = objPer;
            p.setTperId(ObjectIdAfterInserting);
            boo = true;
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrio un error durante el registro!!");
        }
        return boo;
    }
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="Method to SELECT ONE">
    public boolean selectOne(TPersona objPer) {
        boolean boo = false;
        try {
            //Resultado de Consulta
            TPersona pResult = perModel.SelectOne(objPer);
            if (pResult != null) {
                p = pResult;
                boo = true;
            }
        } catch (Exception e) {
            printStackTrace(e);
        }
        return boo;
    }
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="Method to SELECT ALL WHERE">
    public void SelectAll(TPersona objPer) {
        listPer = perModel.SelectAllWhere(objPer);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Method to UPDATE">
    public boolean update(TPersona objPer) {
        return perModel.editar(objPer,"TPersona");
    }
//</editor-fold>     

//<editor-fold defaultstate="collapsed" desc="initObjects to DML">
    public void createObjectClienteTo() {
        dbpCliente = new TDatosBasicosPersona();
        //dbpCliente.setTdbpId(cli_UI.objectCliente.getTDatosBasicosPersona().getTdbpId());
        dbpCliente.setTdbpCedula(cli_UI.jtfCedulaCliente.getText());
        dbpCliente.setTdbpNombre(cli_UI.jtfNombreCliente.getText());
        dbpCliente.setTdbpApellido(cli_UI.jtfApellidoCliente.getText());
        dbpCliente.setTdbpTel(cli_UI.jtfTelefonoCliente.getText());

        pCliente = new TPersona();
        pCliente.setTDatosBasicosPersona(dbpCliente);
        pCliente.setTperCasDir(cli_UI.jtfCasaDirCliente.getText());
        pCliente.setTperCasPro(cli_UI.jtfCasaPropieCliente.getText());
        pCliente.setTperCasTipo((String) cli_UI.jcbTipoCasaCliente.getSelectedItem());
        pCliente.setTperEmpNom(cli_UI.jtfRazonSocialEmpresaCliente.getText());
        pCliente.setTperEmpDir(cli_UI.jtfDireccionEmpresaCliente.getText());
        pCliente.setTperEmpTel(cli_UI.jtfTelefonoEmpresaCliente.getText());
        pCliente.setTperTipo("CLIENTE");
    }

    public void createObjectCodeudorTo() {
        dbpCodeudor = new TDatosBasicosPersona();
        //dbpCodeudor.setTdbpId(cli_UI.objectCodeudor.getTDatosBasicosPersona().getTdbpId());
        dbpCodeudor.setTdbpCedula(cli_UI.jtfCedulaCodeudor.getText());
        dbpCodeudor.setTdbpNombre(cli_UI.jtfNombreCodeudor.getText());
        dbpCodeudor.setTdbpApellido(cli_UI.jtfApellidoCodeudor.getText());
        dbpCodeudor.setTdbpTel(cli_UI.jtfTelefonoCodeudor.getText());

        pCodeudor = new TPersona();
        pCodeudor.setTDatosBasicosPersona(dbpCodeudor);
        pCodeudor.setTperCasDir(cli_UI.jtfCasaDirCodeudor.getText());
        pCodeudor.setTperCasPro(cli_UI.jtfCasaPropieCodeudor.getText());
        pCodeudor.setTperCasTipo((String) cli_UI.jcbTipoCasaCodeudor.getSelectedItem());
        pCodeudor.setTperEmpNom(cli_UI.jtfRazonSocialEmpresaCodeudor.getText());
        pCodeudor.setTperEmpDir(cli_UI.jtfDireccionEmpresaCodeudor.getText());
        pCodeudor.setTperEmpTel(cli_UI.jtfTelefonolEmpresaCodeudor.getText());
        pCodeudor.setTperTipo("CODEUDOR");
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="set DATA">
    public void setDataJTextCliente() {
        cli_UI.objectCliente = getP();
        cli_UI.jtfCedulaCliente.setText(getP().getTDatosBasicosPersona().getTdbpCedula());
        cli_UI.jtfNombreCliente.setText(getP().getTDatosBasicosPersona().getTdbpNombre());
        cli_UI.jtfApellidoCliente.setText(getP().getTDatosBasicosPersona().getTdbpApellido());
        cli_UI.jtfTelefonoCliente.setText(getP().getTDatosBasicosPersona().getTdbpTel());
        cli_UI.jtfCasaDirCliente.setText(getP().getTperCasDir());
        cli_UI.jtfCasaPropieCliente.setText(getP().getTperCasPro());
        cli_UI.jcbTipoCasaCliente.setSelectedItem(getP().getTperCasTipo());
        cli_UI.jtfRazonSocialEmpresaCliente.setText(getP().getTperEmpNom());
        cli_UI.jtfDireccionEmpresaCliente.setText(getP().getTperEmpDir());
        cli_UI.jtfTelefonoEmpresaCliente.setText(getP().getTperEmpTel());
    }

    public void setDataJTextCodeudor() {
        cli_UI.objectCodeudor = getP();
        cli_UI.jtfCedulaCodeudor.setText(getP().getTDatosBasicosPersona().getTdbpCedula());
        cli_UI.jtfNombreCodeudor.setText(getP().getTDatosBasicosPersona().getTdbpNombre());
        cli_UI.jtfApellidoCodeudor.setText(getP().getTDatosBasicosPersona().getTdbpApellido());
        cli_UI.jtfTelefonoCodeudor.setText(getP().getTDatosBasicosPersona().getTdbpTel());
        cli_UI.jtfCasaDirCodeudor.setText(getP().getTperCasDir());
        cli_UI.jtfCasaPropieCodeudor.setText(getP().getTperCasPro());
        cli_UI.jcbTipoCasaCodeudor.setSelectedItem(getP().getTperCasTipo());
        cli_UI.jtfRazonSocialEmpresaCodeudor.setText(getP().getTperEmpNom());
        cli_UI.jtfDireccionEmpresaCodeudor.setText(getP().getTperEmpDir());
        cli_UI.jtfTelefonolEmpresaCodeudor.setText(getP().getTperEmpTel());
    }
//</editor-fold>  

    /*//<editor-fold defaultstate="collapsed" desc="init TABLE">
    public void initTable() {
    TPersona temp = new TPersona();
    temp.setTperTipo("CLIENTE");
    
    SelectAll(temp);
    
    DefaultTableModel dtm = new DefaultTableModel();
    cli_UI.jtClientes.setModel(dtm);
    
    dtm.setColumnIdentifiers(new Object[]{"Cédula", "Nombre"});
    
    for (int i = 0; i < listPer.size(); i++) {
    dtm.addRow(new Object[]{
    listPer.get(i).getTDatosBasicosPersona().getTdbpCedula(),
    listPer.get(i).getTDatosBasicosPersona().getTdbpNombre() + " "
    + listPer.get(i).getTDatosBasicosPersona().getTdbpApellido()
    });
    
    }
    }
    //</editor-fold>*/


}
