module com.rioma.listacesce {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires poi;
    requires poi.ooxml;

    opens com.rioma.listacesce to javafx.fxml;
    exports com.rioma.listacesce;
}
