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
                    ManejoBackend.actualizarBeneficiario(beneficiario,panel_actulizarBeneficiaro);
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
            // Mostrar todos los beneficiarios en la TableView
            ObservableList<cls_beneficiario> todos = ManejoBackend.listarTodosBeneficiarios();
            tableView.getItems().clear();
            tableView.setItems(todos);
        } else if (tipoBusqueda != null && !valorBusqueda.isEmpty()) {
            // Realizar la búsqueda utilizando el backend
            ObservableList<cls_beneficiario> resultados = ManejoBackend.buscarBeneficiarios(tipoBusqueda, valorBusqueda);

            // Mostrar los resultados en la TableView
            tableView.getItems().clear();
            tableView.setItems(resultados);
        } else {
            // Mostrar un mensaje o manejar la falta de selección/tipo de búsqueda
            System.out.println("Seleccione un tipo de búsqueda y proporcione un valor.");
        }
    }

}

