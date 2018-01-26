/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.TBanco;
import Entity.TMovimientoBanco;
import Entity.TMovimientoCuenta;
import Persistence.hibernateUtil;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author afvc2203
 */
public class Banco_Model<T> extends Models {

    public Banco_Model() {
    }

    public List<TBanco> SelectAllWhere(char tipoBanco) {

        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "FROM TBanco WHERE tbanTipo = '" + tipoBanco + "'";
        List<TBanco> result = s.createQuery(query).list();
        s.getTransaction().commit();

        return result;
    }

    public long getSaldoDebitoCredito(String fechaIni, String fechaFin, char tipo) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "FROM "
                + "TMovimientoBanco as tmb, "
                + "TBanco as tb "
                + "WHERE tb.tbanCuenta= tmb.TBanco.tbanCuenta "
                + "AND tb.tbanTipo = '" + tipo + "' "
                + "AND tmb.tmovFecha BETWEEN  DATE(DATE_FORMAT('" + fechaIni + "', '%Y-%m-%d')) AND DATE(DATE_FORMAT('" + fechaFin + "', '%Y-%m-%d'))";
        List<T> result = s.createQuery(query).list();
        s.getTransaction().commit();

        Iterator itr = result.iterator();
        long valor = 0;
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            TMovimientoBanco temp = (TMovimientoBanco) obj[0];

            if (tipo == 'C') {
                valor += (((temp.getTmovSaldo() * temp.getTmovPorcentaje()) / 100) + temp.getTmovSaldo());
            } else {
                valor += temp.getTmovSaldo();
            }

        }
        return valor;
    }

    public long getSaldoCuentas(String fechaIni, String fechaFin) {
        s = hibernateUtil.getSessionFactory();
        s.beginTransaction();
        String query = "FROM TMovimientoCuenta AS tmc "
                + "WHERE tmc.tmocFecha BETWEEN  DATE(DATE_FORMAT('" + fechaIni + "', '%Y-%m-%d')) AND DATE(DATE_FORMAT('" + fechaFin + "', '%Y-%m-%d'))";;
        List<TMovimientoCuenta> result = s.createQuery(query).list();
        s.getTransaction().commit();

        Iterator itr = result.iterator();
        long valor = 0;

        for (int i = 0; i < result.size(); i++) {
            valor += result.get(i).getTmocSaldo();
        }
        return valor;
    }
}
