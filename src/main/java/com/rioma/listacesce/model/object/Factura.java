package com.rioma.listacesce.model.object;

public class Factura {
    private String tipoDoc;
    private String numFac;
    private String clienteId;
    private String nombreCliente;
    private String metodoPago;
    private String fechaFactura;
    private String fechaVencimiento;
    private double importeVencimiento;
    private double totalFactura;
    private String nif;
    private String cycPoliza;
    private String nombrePais;
    private double creditoAseg;

    /*
        * CONSTRUCTORES
     */
    public Factura(String tipoDoc, String numFac, String clienteId, String nombreCliente, String metodoPago, String fechaFactura, String fechaVencimiento, double importeVencimiento, double totalFactura, String nif, String cycPoliza, String nombrePais, double creditoAseg) {
        this.tipoDoc = tipoDoc;
        this.numFac = numFac;
        this.clienteId = clienteId;
        this.nombreCliente = nombreCliente;
        this.metodoPago = metodoPago;
        this.fechaFactura = fechaFactura;
        this.fechaVencimiento = fechaVencimiento;
        this.importeVencimiento = importeVencimiento;
        this.totalFactura = totalFactura;
        this.nif = nif;
        this.cycPoliza = cycPoliza;
        this.nombrePais = nombrePais;
        this.creditoAseg = creditoAseg;
    }

    /**
     * GETTERS
     * AND
     * SETTERS
     */

    public Factura() {
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNumFac() {
        return numFac;
    }

    public void setNumFac(String numFac) {
        this.numFac = numFac;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getImporteVencimiento() {
        return importeVencimiento;
    }

    public void setImporteVencimiento(double importeVencimiento) {
        this.importeVencimiento = importeVencimiento;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getCycPoliza() {
        return cycPoliza;
    }

    public void setCycPoliza(String cycPoliza) {
        this.cycPoliza = cycPoliza;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public double getCreditoAseg() {
        return creditoAseg;
    }

    public void setCreditoAseg(double creditoAseg) {
        this.creditoAseg = creditoAseg;
    }

    /**
     * TO STRING
     */
    @Override
    public String toString() {
        return "Factura{" +
                "tipoDoc='" + tipoDoc + '\'' +
                ", numFac='" + numFac + '\'' +
                ", clienteId='" + clienteId + '\'' +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", metodoPago='" + metodoPago + '\'' +
                ", fechaFactura='" + fechaFactura + '\'' +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                ", importeVencimiento=" + importeVencimiento +
                ", totalFactura=" + totalFactura +
                ", nif='" + nif + '\'' +
                ", cycPoliza='" + cycPoliza + '\'' +
                ", nombrePais='" + nombrePais + '\'' +
                ", creditoAseg=" + creditoAseg +
                '}';
    }
}
