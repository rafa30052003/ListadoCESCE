package com.rioma.listacesce.utils;

import javafx.scene.control.Alert;

public class Controls {
    /*
     * Envia una alerta de cuando ha ocurrido un error
     */
    public static void alertError(String titulo, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setTitle(titulo);
        alert.setContentText(content);
        alert.showAndWait();
    }



    /*
     * Envia una alerta de cuando este bien
     */
    public static void alertCorrect(String titulo, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setTitle(titulo);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
