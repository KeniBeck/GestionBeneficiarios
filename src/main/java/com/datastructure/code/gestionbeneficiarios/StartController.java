package com.datastructure.code.gestionbeneficiarios;


import com.datastructure.code.gestionbeneficiarios.FuncionesApp.Clases.cls_beneficiario;
import com.datastructure.code.gestionbeneficiarios.FuncionesApp.ManejoBackend;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class StartController {
    public Pane panel_aggBeneficiaro;
    public TextField txt_documento;
    public TextField txt_nombre;
    public TextField txt_puntaje;
    public RadioButton radio_No;
    public TextField txtBuscar;
    public TableColumn<cls_beneficiario,Void> colAcciones;
    public Pane panel_actulizarBeneficiaro;
    public TextField txt_documentoActulizar;
    public TextField txt_nombreActualizar;
    public TextField txt_puntajeActualizar;
    public RadioButton radio_NoActualizar;
    @FXML
    private TableView<cls_beneficiario> tableView;
    @FXML
    private TableColumn<cls_beneficiario,String> colDocumento;
    @FXML
    private TableColumn<cls_beneficiario,String> colNombre;
    @FXML
    private TableColumn<cls_beneficiario,Boolean> colPuntaje;
    public TableColumn colEstado;
    public ChoiceBox<String> choiceBoxBuscarPor;
    ManejoBackend manejoBackend = new ManejoBackend();


    public void initialize() {
        choiceBoxBuscarPor.getItems().addAll("Documento", "Nombre","Listar a Todos");
        choiceBoxBuscarPor.setValue("Documento"); // Valor predeterminado

        colAcciones.setCellFactory(param -> new TableCell<cls_beneficiario, Void>() {
            final Button btnActualizar = new Button("Actualizar");
            final Button btnEliminar = new Button("Eliminar");

            {
                btnActualizar.setOnAction(event -> {
                    cls_beneficiario beneficiario = getTableView().getItems().get(getIndex());
                    ManejoBackend.cargarInformacionBeneficiarioEnPanel(beneficiario, txt_documentoActulizar, txt_nombreActualizar, txt_puntajeActualizar, radio_NoActualizar);
                    ManejoBackend.mostrarPanel(panel_actulizarBeneficiaro);
                });

                btnEliminar.setOnAction(event -> {
                    cls_beneficiario beneficiario = getTableView().getItems().get(getIndex());
                    ManejoBackend.eliminarBeneficiario(beneficiario);
                    getTableView().getItems().remove(beneficiario);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(btnActualizar, btnEliminar);
                    setGraphic(hbox);
                }
            }
        });

        // ...


        // Configurar las columnas de la TableView
        colDocumento.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPuntaje.setCellValueFactory(new PropertyValueFactory<>("puntaje"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // Configurar la TableView



    }
    public Pane getPanelAggBeneficiaro() {
        return panel_aggBeneficiaro;
    }
    public Pane getPanel_actulizarBeneficiaro() {
        return panel_actulizarBeneficiaro;
    }

    public void OnAddPersonButtonPressed(MouseEvent mouseEvent) {
    manejoBackend.mostrarPanel(panel_aggBeneficiaro);
    }

    public void OnCancelarAgg(MouseEvent mouseEvent) {
        manejoBackend.ocultarPanel(panel_aggBeneficiaro);
    }

    public void OnLimpiarPanelBeneficiaro(MouseEvent mouseEvent) {
        manejoBackend.limpiarCampos(txt_documento,txt_nombre,txt_puntaje,radio_No);
    }

    public void OnGuardarBeneficiaro(MouseEvent mouseEvent) {
        manejoBackend.guardarBeneficiario(txt_documento,txt_nombre,txt_puntaje,radio_No);
    }


    public void onBuscarButtonClicked(ActionEvent actionEvent) {
        String tipoBusqueda = choiceBoxBuscarPor.getValue();
        String valorBusqueda = txtBuscar.getText();

        if ("Listar a Todos".equals(tipoBusqueda)) {
            ObservableList<cls_beneficiario> todos = ManejoBackend.listarTodosBeneficiarios();
            tableView.getItems().clear();
            tableView.setItems(todos);
        } else if (tipoBusqueda != null && !valorBusqueda.isEmpty()) {
            ObservableList<cls_beneficiario> resultados = ManejoBackend.buscarBeneficiarios(tipoBusqueda, valorBusqueda);

            tableView.getItems().clear();
            tableView.setItems(resultados);
        } else {
            System.out.println("Seleccione un tipo de búsqueda y proporcione un valor.");
        }
    }

    public void OnCancelarUpdate(MouseEvent mouseEvent) {
        ManejoBackend.ocultarPanel(panel_actulizarBeneficiaro);
        tableView.refresh();

    }

    public void OnLimpiarPanelActualizar(MouseEvent mouseEvent) {
        ManejoBackend.limpiarCamposActualizar(txt_documentoActulizar,txt_nombreActualizar,txt_puntajeActualizar,radio_NoActualizar);
    }

    public void OnActualizarBeneficiaro(MouseEvent mouseEvent) {
        String documento = txt_documentoActulizar.getText();
        ManejoBackend.actualizarBeneficiario(documento,txt_documentoActulizar, txt_nombreActualizar, txt_puntajeActualizar, radio_NoActualizar);

    }
}

