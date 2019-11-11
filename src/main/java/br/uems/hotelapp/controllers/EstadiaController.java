package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.ConsumoDao;
import br.uems.hotelapp.persistence.dao.EstadiaDao;
import br.uems.hotelapp.persistence.dao.ItemConsumoDao;
import br.uems.hotelapp.persistence.entities.Consumo;
import br.uems.hotelapp.persistence.entities.Consumo.ItemConsumoTable;
import br.uems.hotelapp.persistence.entities.Estadia;
import br.uems.hotelapp.persistence.entities.ItemConsumo;
import br.uems.hotelapp.persistence.entities.Reserva;
import br.uems.hotelapp.persistence.entities.TipoAcomodacao;
import br.uems.hotelapp.utils.AppUtils;
import br.uems.hotelapp.utils.DateUtils;
import br.uems.hotelapp.utils.MasksUtils;
import br.uems.hotelapp.utils.NumberUtils;
import br.uems.hotelapp.utils.ValidatorUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.apache.commons.lang3.StringUtils;

public class EstadiaController implements Initializable {

    @FXML
    private Pane pnlEstadia;

    @FXML
    private ImageView btnBack;

    @FXML
    private Label labelCustomerName, labelStartDate, labelEndDate, labelRoomType, labelQtdeAdultos, labelQtdeCriancas;

    @FXML
    private JFXButton btnAddItemConsumo, btnPay;

    @FXML
    private TableView<ItemConsumoTable> tableItensConsumo;

    @FXML
    private TableColumn<ItemConsumoTable, String> colNome;

    @FXML
    private TableColumn<ItemConsumoTable, String> colTipo;

    @FXML
    private TableColumn<ItemConsumoTable, Integer> colQtde;

    @FXML
    private TableColumn<ItemConsumoTable, String> colData;

    @FXML
    private TableColumn<ItemConsumoTable, String> colValor;

    @FXML
    private Label labelPrecoEstadia, labelPrecoConsumo, labelPrecoTotal;

    @FXML
    private JFXComboBox<ItemConsumo> cbItensConsumo;

    @FXML
    private JFXTextField inputQtdeItensConsumo;
        
    ItemConsumoDao itemConsumoDao = new ItemConsumoDao();
    
    ObservableList<ItemConsumo> obItensConsumo;
    
    ObservableList<ItemConsumoTable> obConsumos;
    
    EstadiaDao estadiaDao = new EstadiaDao();
    
    Estadia estadia;
    
    ConsumoDao consumoDao = new ConsumoDao();
    
    ItemConsumo itemConsumo;
    
    Double valorConsumo = 0.0, valorTotal = 0.0;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        reset();
        loadValidators();
    }

    @FXML
    void save(MouseEvent event) {
        PagamentoController pgtoController = (PagamentoController) AppUtils.loadWindow(getClass().getResource("/fxml/Pagamento.fxml"), "Pagamento", null);
//        pgtoController.setData(estadia);
        
        if (estadia.getStatus() == Estadia.STATUS_PAGO) {
            // TODO: Exibir recibo
            return;
        }
        estadia.setStatus(Estadia.STATUS_PAGO);
        estadiaDao.update(estadia);
        statusPago();
    }
    
    private void initTable() {
        tableItensConsumo.getColumns().clear();
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colQtde.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tableItensConsumo.getColumns().addAll(colNome, colTipo, colQtde, colData, colValor);
    }
    
    private void loadConsumo(Estadia estadia) {
        
        List<Consumo> consumos = estadia.getConsumos();
        
        List<ItemConsumoTable> listConsumos = new ArrayList<>();
        
        consumos.forEach(consumo -> {
            valorConsumo += consumo.getQuantidade() * consumo.getValor();
            valorTotal += consumo.getQuantidade() * consumo.getValor();
            listConsumos.add(consumo.getRowTable());
        });
        
        obConsumos = FXCollections.observableArrayList(listConsumos);
        tableItensConsumo.setItems(obConsumos);
        labelPrecoConsumo.setText(NumberUtils.formatCurrency(valorConsumo));
        labelPrecoTotal.setText(NumberUtils.formatCurrency(valorTotal));
    }
    
    private void loadItensConsumo() {
        obItensConsumo = FXCollections.observableArrayList(itemConsumoDao.getAll());
        cbItensConsumo.setItems(obItensConsumo);
    }
    
    private void loadValidators() {
        MasksUtils.onlyDigitsValue(inputQtdeItensConsumo);
        ValidatorUtils.setValidator(inputQtdeItensConsumo, "Informe a quantidade");
        ValidatorUtils.setValidator(cbItensConsumo, "Informe o item de consumo");
    }

    @FXML
    void addConsumo(MouseEvent event) {
        if (StringUtils.strip(inputQtdeItensConsumo.getText(), "0").isEmpty()) {
            inputQtdeItensConsumo.clear();
            inputQtdeItensConsumo.validate();
            return;
        }
        
        Consumo consumo = new Consumo();
        consumo.setEstadia(estadia);
        consumo.setDataHora(new Date());
        consumo.setItemConsumo(itemConsumo);
        consumo.setValor(itemConsumo.getPreco());
        consumo.setQuantidade(Integer.parseInt(inputQtdeItensConsumo.getText()));
        consumoDao.save(consumo);
        obConsumos.add(consumo.getRowTable());
        valorConsumo += consumo.getQuantidade() * consumo.getValor();
        valorTotal += consumo.getQuantidade() * consumo.getValor();
        labelPrecoConsumo.setText(NumberUtils.formatCurrency(valorConsumo));
        labelPrecoTotal.setText(NumberUtils.formatCurrency(valorTotal));
        resetConsumoForm();
    }
    
    private void resetConsumoForm() {
        itemConsumo = null;
        cbItensConsumo.getSelectionModel().select(itemConsumo);
        inputQtdeItensConsumo.clear();
        cbItensConsumo.setDisable(false);
        inputQtdeItensConsumo.setDisable(false);
        btnAddItemConsumo.setDisable(false);
    }

    @FXML
    void setItemConsumo(ActionEvent event) {
        itemConsumo = cbItensConsumo.getSelectionModel().getSelectedItem();
    }
    
    public void setData(Estadia estadia) {
        this.estadia = estadia;
        Reserva reserva = estadia.getReserva();
        labelCustomerName.setText(estadia.getHospede().getNome());
        labelStartDate.setText(DateUtils.format(estadia.getDataHoraInicio()));
        labelEndDate.setText(DateUtils.format(estadia.getDataHoraTermino()));
        labelRoomType.setText(estadia.getAcomodacao().toString());
        labelQtdeAdultos.setText(reserva.getQtdeAdulto().toString());
        labelQtdeCriancas.setText(reserva.getQtdeCrianca().toString());
        
        Integer dias = DateUtils.diffInDays(estadia.getDataHoraInicio(), estadia.getDataHoraTermino());
        Double valorEstadia =  dias * estadia.getReserva().getValorDiaria();
        
        valorConsumo = 0.0;
        valorTotal = valorEstadia;
        labelPrecoEstadia.setText(NumberUtils.formatCurrency(valorEstadia));
        labelPrecoTotal.setText(NumberUtils.formatCurrency(valorTotal));
        
        loadConsumo(estadia);
        
        if (estadia.getStatus() == Estadia.STATUS_PAGO) {
            statusPago();
        }
    }
    
    private void statusPago() {
        cbItensConsumo.getSelectionModel().select(null);
        inputQtdeItensConsumo.clear();
        cbItensConsumo.setDisable(true);
        inputQtdeItensConsumo.setDisable(true);
        btnAddItemConsumo.setDisable(true);
        btnPay.getStyleClass().remove("btn-accent");
        btnPay.getStyleClass().add("btn-success");
        btnPay.setText("Exibir comprovante");
    }
    
    private void reset() {
        resetConsumoForm();
        loadItensConsumo();
        btnPay.getStyleClass().remove("btn-success");
        btnPay.getStyleClass().add("btn-accent");
        btnPay.setText("Pagar Estadia");
    }
    
    @FXML
    void back(MouseEvent event) {
        reset();
        HomeController.getController().showOverview();
    }

}
