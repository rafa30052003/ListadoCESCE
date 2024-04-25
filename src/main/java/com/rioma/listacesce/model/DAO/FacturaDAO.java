package com.rioma.listacesce.model.DAO;

import com.rioma.listacesce.conexion.Connect;
import com.rioma.listacesce.model.object.Factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {
    /**
     * QUERY
     */
    private final static String SELECT_FACTURAS_POR_FECHA ="SELECT cab.xtipodoc_id as tipo_doc, cab.xnumdoc_id as num_fac, cab.xcliente_id, cli.xnombre, " +
                                                            "met.xdescripcion as metodo_pago, convert(char(10), cab.xfecha, 103) as fecha_factura, " +
                                                            "convert(char(10), vto.xfecha_vto, 103) as fecha_vto, " +
                                                            "Convert(float, Isnull(Round(vto.ximporte, 2), 0)) as impor_vto, " +
                                                            "Convert(float, Isnull(Round(cab.xtotal_fra, 2), 0)) as total_factura, " +
                                                            "cli.xnif, clil.xcyc_poliza, pai.xnombre, " +
                                                            "Convert(float, Isnull(Round(clil.p856_credito_aseg, 2), 0)) as credito_aseg " +
                                                            "FROM imp.pl_fracli_cab cab " +
                                                            "INNER JOIN imp.pl_clientes clil ON clil.xempresa_id = cab.xempresa_id AND clil.xcliente_id = cab.xcliente_id " +
                                                            "INNER JOIN imp.pc_clientes cli ON cli.xempgen_id = cab.xempresa_id AND cli.xcliente_id = cab.xcliente_id " +
                                                            "INNER JOIN imp.pc_paises pai ON pai.xpais_id = cli.xpais_id " +
                                                            "INNER JOIN imp.pl_fracli_vto vto ON vto.xempresa_id = cab.xempresa_id AND vto.xciclo_id = cab.xciclo_id AND vto.xtipodoc_id = cab.xtipodoc_id AND vto.xnumdoc_id = cab.xnumdoc_id AND vto.xseccion_id = cab.xseccion_id " +
                                                            "INNER JOIN imp.pl_cli_cob cob ON cob.xempresa_id = cab.xempresa_id AND cob.xcliente_id = cli.xcliente_id AND cab.xlocal_cob_id = cob.xlocal_id " +
                                                            "INNER JOIN imp.pc_fpago pag ON pag.xfpago_id = cob.xfpago_id " +
                                                            "INNER JOIN imp.pc_fpago_lin lin ON pag.xfpago_id = lin.xfpago_id " +
                                                            "INNER JOIN imp.pc_metpago met ON lin.xmetodo_id = met.xmetodo_id " +
                                                            "WHERE cab.xempresa_id = 'RIOM' " +
                                                            "AND cab.xfecha >= ? AND cab.xfecha <= ? " +
                                                            "AND cab.xtipodoc_id NOT LIKE '234%' AND cab.xtipodoc_id NOT LIKE '235%' " +
                                                            "ORDER BY cab.xfecha";


    /**
     * CONEXION
     */

    private Connection conn;

    public FacturaDAO(Connection conn){
        this.conn = conn;
    }
    public FacturaDAO(){
        this.conn = Connect.getConnect();
    }



    /**
     * Obtiene una lista de facturas entre las fechas de inicio y fin especificadas.
     *
     * @param fechaInicio la fecha de inicio del rango
     * @param fechaFin la fecha de fin del rango
     * @return una lista de facturas que se encuentran dentro del rango de fechas especificado
     */
    public List<Factura> obtenerFacturasPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Factura> facturas = new ArrayList<>();

        try (PreparedStatement pst = this.conn.prepareStatement(SELECT_FACTURAS_POR_FECHA)) {
            pst.setDate(1, java.sql.Date.valueOf(fechaInicio));
            pst.setDate(2, java.sql.Date.valueOf(fechaFin));
            try (ResultSet resultSet = pst.executeQuery()) {
                while (resultSet.next()) {
                    String tipoDoc = resultSet.getString("tipo_doc");
                    String numFac = resultSet.getString("num_fac");
                    String clienteId = resultSet.getString("xcliente_id");
                    String nombreCliente = resultSet.getString("xnombre");
                    String metodoPago = resultSet.getString("metodo_pago");
                    String fechaFactura = resultSet.getString("fecha_factura");
                    String fechaVencimiento = resultSet.getString("fecha_vto");
                    double importeVencimiento = resultSet.getDouble("impor_vto");
                    double totalFactura = resultSet.getDouble("total_factura");
                    String nif = resultSet.getString("xnif");
                    String cycPoliza = resultSet.getString("xcyc_poliza");
                    String nombrePais = resultSet.getString("xnombre");
                    double creditoAseg = resultSet.getDouble("credito_aseg");
                    Factura f = new Factura(tipoDoc, numFac, clienteId, nombreCliente, metodoPago, fechaFactura, fechaVencimiento, importeVencimiento, totalFactura, nif, cycPoliza, nombrePais, creditoAseg);
                    facturas.add(f);

                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return facturas;
    }




}
