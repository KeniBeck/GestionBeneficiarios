package com.datastructure.code.gestionbeneficiarios.FuncionesApp;

import com.datastructure.code.gestionbeneficiarios.FuncionesApp.Clases.cls_beneficiario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ManejoBackend {
    private static List<cls_beneficiario> beneficios = new ArrayList<>();
    public static Queue<cls_beneficiario> colaTurnos = new LinkedList<>();

    public static void mostrarPanel(Pane panel) {
        // Lógica para mostrar el panel
        panel.setVisible(true);
        panel.setManaged(true);

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
    public static void limpiarCamposActualizar(TextField txtDocumento, TextField txtNombre, TextField txtPuntaje, RadioButton radioNo) {
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
    public static cls_beneficiario encontrarBeneficiarioPorDocumento(String documento) {
        for (cls_beneficiario beneficiario : beneficios) {
            if (beneficiario.getId().equals(documento)) {
                return beneficiario;
            }
        }
        return null; // Si no se encuentra el beneficiario con el documento dado
    }


    private static boolean todosLosCamposLlenos(TextField... campos) {
        for (TextField campo : campos) {
            if (campo.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    public static void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    public static void actualizarBeneficiario(String documento, TextField txtDocumento, TextField txtNombre, TextField txtPuntaje, RadioButton radioNo) {
        cls_beneficiario beneficiario = encontrarBeneficiarioPorDocumento(documento);

        if (beneficiario != null) {
            // Actualizar la información del beneficiario
            beneficiario.setNombre(txtNombre.getText());
            beneficiario.setPuntaje(Integer.parseInt(txtPuntaje.getText()));
            beneficiario.setEstado(!radioNo.isSelected()); // Suponiendo que el radio button representa el estado contrario

            // Limpiar los campos
            limpiarCampos(txtDocumento, txtNombre, txtPuntaje, radioNo);

            // Mostrar mensaje de éxito

            mostrarAlerta("Éxito", "Beneficiario actualizado correctamente.");

        } else {
            // Manejar el caso en que el beneficiario no se encuentra
            mostrarAlerta("Error", "No se encontró el beneficiario con documento: " + documento);
        }
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
    public static cls_beneficiario obtenerBeneficiarioPorId(int id) {
        for (cls_beneficiario beneficiario : beneficios) {
            if (beneficiario.getId().equals(String.valueOf(id))) {
                return beneficiario;
            }
        }
        return null;  // Devuelve null si no se encuentra el beneficiario
    }
    public static ObservableList<cls_beneficiario> listarTodosBeneficiarios() {
        return FXCollections.observableArrayList(beneficios);
    }
    public static Queue<cls_beneficiario> obtenerColaTurnos() {
        return colaTurnos;
    }
    public static ObservableList<Integer> obtenerListaTurnos() {
        List<Integer> listaTurnos = new ArrayList<>();
        for (cls_beneficiario beneficiario : colaTurnos) {
            listaTurnos.add(beneficiario.getTurno());
        }
        return FXCollections.observableList(listaTurnos);
    }
    private static  int  contadorTurnos = 0;

    public static void asignarTurno(cls_beneficiario beneficiario) {
        contadorTurnos++;
        agregarBeneficiarioAColaTurnos(beneficiario);

    }

    public static void agregarBeneficiarioAColaTurnos(cls_beneficiario beneficiario) {
        colaTurnos.add(beneficiario);
        ManejoBackend.mostrarAlerta("Turnos", "turno " + contadorTurnos + " asignado");
    }


    public static void cargarInformacionBeneficiarioEnPanel(cls_beneficiario beneficiario, TextField txtDocumento, TextField txtNombre, TextField txtPuntaje, RadioButton radioNo) {
        // Cargar la información del beneficiario en los controles del panel
        txtDocumento.setText(beneficiario.getId());
        txtNombre.setText(beneficiario.getNombre());
        txtPuntaje.setText(Integer.toString(beneficiario.getPuntaje()));
        radioNo.setSelected(!beneficiario.isEstado());
    }

    public static void eliminarBeneficiario(cls_beneficiario beneficiario) {
        // Lógica para eliminar al beneficiario
        beneficios.remove(beneficiario);
        mostrarAlerta("Éxito", "Beneficiario eliminado correctamente.");
    }
}


