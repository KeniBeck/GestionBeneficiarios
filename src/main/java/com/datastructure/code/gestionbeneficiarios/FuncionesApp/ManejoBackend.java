package com.datastructure.code.gestionbeneficiarios.FuncionesApp;

import com.datastructure.code.gestionbeneficiarios.FuncionesApp.Clases.cls_beneficiario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ManejoBackend {
    private static List<cls_beneficiario> beneficios = new ArrayList<>();
    private static ObservableList<cls_beneficiario> busqueda = FXCollections.observableArrayList();
    public static void mostrarPanel(Pane panel) {
        // Lógica para mostrar el panel
        panel.setVisible(true);
        panel.setManaged(true);
        // Otras configuraciones según sea necesario
    }
    public static void ocultarPanel(Pane panel) {
        panel.setVisible(false);
        panel.setManaged(false);

    }
    public static void limpiarCampos(TextField txtDocumento, TextField txtNombre, TextField txtPuntaje, RadioButton radioNo) {
        // Limpia los campos
        txtDocumento.clear();
        txtNombre.clear();
        txtPuntaje.clear();
        radioNo.setSelected(false);
        txtDocumento.requestFocus();
    }
    public static void guardarBeneficiario(TextField txtDocumento, TextField txtNombre, TextField txtPuntaje, RadioButton radioNo) {
        if (todosLosCamposLlenos(txtDocumento, txtNombre, txtPuntaje)) {
            String documento = txtDocumento.getText();
            String nombre = txtNombre.getText();
            int puntaje = Integer.parseInt(txtPuntaje.getText());
            boolean estado = !radioNo.isSelected(); // Suponiendo que el radio button representa el estado contrario


            cls_beneficiario beneficiario = new cls_beneficiario(documento, nombre, puntaje, estado);


            beneficios.add(beneficiario);

            limpiarCampos(txtDocumento, txtNombre, txtPuntaje, radioNo);
            mostrarAlerta("Éxito", "Beneficiario guardado correctamente.");
        } else {
            // Mostrar mensaje de error
            mostrarAlerta("Error", "Todos los campos deben estar llenos.");
        }
    }

    private static boolean todosLosCamposLlenos(TextField... campos) {
        for (TextField campo : campos) {
            if (campo.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    private static void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    public static ObservableList<cls_beneficiario> buscarBeneficiarios(String tipoBusqueda, String valorBusqueda) {
        ObservableList<cls_beneficiario> resultados = FXCollections.observableArrayList();

        for (cls_beneficiario beneficiario : beneficios) {
            if (("Documento".equals(tipoBusqueda) && beneficiario.getId().equals(valorBusqueda)) ||
                    ("Nombre".equals(tipoBusqueda) && beneficiario.getNombre().equals(valorBusqueda))) {
                resultados.add(beneficiario);
            }
        }

        return resultados;
    }
    public static ObservableList<cls_beneficiario> listarTodosBeneficiarios() {
        return FXCollections.observableArrayList(beneficios);
    }
    public static void actualizarBeneficiario(cls_beneficiario beneficiario) {
        // Lógica para actualizar al beneficiario
        System.out.println("Actualizar beneficiario: " + beneficiario.getId());
        mostrarAlerta("Éxito", "Beneficiario actualizado correctamente.");
    }
    public static void eliminarBeneficiario(cls_beneficiario beneficiario) {
        // Lógica para eliminar al beneficiario
        beneficios.remove(beneficiario);
        mostrarAlerta("Éxito", "Beneficiario eliminado correctamente.");
    }
}


