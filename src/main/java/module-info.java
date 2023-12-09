module com.datastructure.code.gestionbeneficiarios {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.datastructure.code.gestionbeneficiarios to javafx.fxml;
    exports com.datastructure.code.gestionbeneficiarios;
    opens com.datastructure.code.gestionbeneficiarios.FuncionesApp.Clases to javafx.base;
}