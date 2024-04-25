package com.rioma.listacesce;

import com.rioma.listacesce.model.DAO.FacturaDAO;
import com.rioma.listacesce.model.object.Factura;
import com.rioma.listacesce.utils.Controls;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class FacturaController {
   @FXML private TableView<Factura> tableFactura;

    @FXML private TableColumn<Factura, String> colTipoDoc;
    @FXML private TableColumn<Factura, String> colNumFac;
    @FXML private TableColumn<Factura, String> colClienteId;
    @FXML private TableColumn<Factura, String> colNombreCliente;
    @FXML private TableColumn<Factura, String> colMetodoPago;
    @FXML private TableColumn<Factura, String> colFechaFactura;
    @FXML private TableColumn<Factura, String> colFechaVencimiento;
    @FXML private TableColumn<Factura, Double> colImporteVencimiento;
    @FXML private TableColumn<Factura, Double> colTotalFactura;
    @FXML private TableColumn<Factura, String> colNif;
    @FXML private TableColumn<Factura, String> colCycPoliza;
    @FXML private TableColumn<Factura, String> colNombrePais;
    @FXML private TableColumn<Factura, Double> colCreditoAseg;

    @FXML private DatePicker datepickerInicio;
    @FXML private DatePicker datepickerFin;
    @FXML private Button exportButton;

    private FacturaDAO facturaDAO = new FacturaDAO();

 public void initialize() {
  exportButton.setDisable(true);
  ((TextField) datepickerInicio.getEditor()).setEditable(false);
  ((TextField) datepickerFin.getEditor()).setEditable(false);


  datepickerInicio.getEditor().setOnMouseClicked(event -> {
   datepickerInicio.show();
  });

  datepickerFin.getEditor().setOnMouseClicked(event -> {
   datepickerFin.show();
  });
 }

 /**
  * Método que se llama cuando se hace clic en el botón "Buscar Facturas". Obtiene las fechas de inicio y fin
  * seleccionadas de los elementos de interfaz de usuario datepicker, llama a FacturaDAO para obtener la lista de
  * Facturas dentro del rango de fechas especificado y actualiza la tabla con los datos obtenidos. También habilita
  * el botón "Exportar" si hay Facturas para exportar. Si no se selecciona la fecha de inicio o fin, se muestra un
  * mensaje de error.
  *

  * @return void
  */
 @FXML
 private void buscarFacturas() {
  LocalDate fechaInicio = datepickerInicio.getValue();
  LocalDate fechaFin = datepickerFin.getValue();


  if (fechaInicio != null && fechaFin != null) {

   List<Factura> facturas = facturaDAO.obtenerFacturasPorFecha(fechaInicio, fechaFin);


   tableFactura.getItems().clear();
   tableFactura.getItems().addAll(facturas);
   mostrarFacturasEnTabla(facturas);
   exportButton.setDisable(false);
  } else {
   Controls.alertError("ERROR", "Fallo al introducir fechas","Introduzca o complete bien tanto la fecha de inicio como la de fin");

  }
 }

 private void mostrarFacturasEnTabla(List<Factura> facturas) {
  colTipoDoc.setCellValueFactory(new PropertyValueFactory<>("tipoDoc"));
  colNumFac.setCellValueFactory(new PropertyValueFactory<>("numFac"));
  colClienteId.setCellValueFactory(new PropertyValueFactory<>("clienteId"));
  colNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
  colMetodoPago.setCellValueFactory(new PropertyValueFactory<>("metodoPago"));
  colFechaFactura.setCellValueFactory(new PropertyValueFactory<>("fechaFactura"));
  colFechaVencimiento.setCellValueFactory(new PropertyValueFactory<>("fechaVencimiento"));
  colImporteVencimiento.setCellValueFactory(new PropertyValueFactory<>("importeVencimiento"));
  colTotalFactura.setCellValueFactory(new PropertyValueFactory<>("totalFactura"));
  colNif.setCellValueFactory(new PropertyValueFactory<>("nif"));
  colCycPoliza.setCellValueFactory(new PropertyValueFactory<>("cycPoliza"));
  colNombrePais.setCellValueFactory(new PropertyValueFactory<>("nombrePais"));
  colCreditoAseg.setCellValueFactory(new PropertyValueFactory<>("creditoAseg"));


  tableFactura.getItems().clear();

  tableFactura.getItems().addAll(facturas);
 }


/**
 * Exporta los datos de la tabla a un archivo de Excel.
 *
 */
 @FXML
 private void exportToExcel() {

  ObservableList<Factura> facturas = tableFactura.getItems();


  FileChooser fileChooser = new FileChooser();
  fileChooser.setTitle("Guardar archivo Excel");
  fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos Excel (*.xlsx)", "*.xlsx"));


  File file = fileChooser.showSaveDialog(null);
  if (file != null) {
   try (Workbook workbook = new XSSFWorkbook()) {

    Sheet sheet = workbook.createSheet("Facturas");


    Row headerRow = sheet.createRow(0);
    String[] headers = {"Tipo de Documento", "Número de Factura", "Cliente ID", "Nombre del Cliente",
            "Método de Pago", "Fecha de Factura", "Fecha de Vencimiento", "Importe de Vencimiento",
            "Total de Factura", "NIF", "Cyc Poliza", "Nombre del País", "Crédito Asegurado"};
    for (int i = 0; i < headers.length; i++) {
     Cell cell = headerRow.createCell(i);
     cell.setCellValue(headers[i]);
    }


    for (int i = 0; i < facturas.size(); i++) {
     Factura factura = facturas.get(i);
     Row row = sheet.createRow(i + 1);
     row.createCell(0).setCellValue(factura.getTipoDoc());
     row.createCell(1).setCellValue(factura.getNumFac());
     row.createCell(2).setCellValue(factura.getClienteId());
     row.createCell(3).setCellValue(factura.getNombreCliente());
     row.createCell(4).setCellValue(factura.getMetodoPago());
     row.createCell(5).setCellValue(factura.getFechaFactura());
     row.createCell(6).setCellValue(factura.getFechaVencimiento());
     row.createCell(7).setCellValue(factura.getImporteVencimiento());
     row.createCell(8).setCellValue(factura.getTotalFactura());
     row.createCell(9).setCellValue(factura.getNif());
     row.createCell(10).setCellValue(factura.getCycPoliza());
     row.createCell(11).setCellValue(factura.getNombrePais());
     row.createCell(12).setCellValue(factura.getCreditoAseg());
    }


    try (FileOutputStream fileOut = new FileOutputStream(file)) {
     workbook.write(fileOut);
     Controls.alertCorrect("Correcto", "Generacion de archivo Excel", "El archivo se ha creado con exito");
    }
   } catch (IOException e) {
    e.printStackTrace();
    Controls.alertError("ERROR", "Generacion de archivo Excel","A ocurrido el siguiente  error el archivo: "+e.toString());
   }
  }
 }



}