package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.ConsumoDao;
import br.uems.hotelapp.persistence.dao.ItemConsumoDao;
import br.uems.hotelapp.persistence.entities.Consumo;
import br.uems.hotelapp.persistence.entities.Consumo.ItemConsumoTable;
import br.uems.hotelapp.persistence.entities.Estadia;
import br.uems.hotelapp.persistence.entities.ItemConsumo;
import br.uems.hotelapp.persistence.entities.Reserva;
import br.uems.hotelapp.persistence.entities.TipoAcomodacao;
import br.uems.hotelapp.utils.DateUtils;
import br.uems.hotelapp.utils.MasksUtils;
import br.uems.hotelapp.utils.NumberUtils;
import br.uems.hotelapp.utils.ValidatorUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
    private Label labelCustomerName;

    @FXML
    private Label labelStartDate;

    @FXML
    private Label labelEndDate;

    @FXML
    private Label labelRoomType;

    @FXML
    private Label labelQtdeAdultos;

    @FXML
    private Label labelQtdeCriancas;

    @FXML
    private JFXButton btnAddItemConsumo;

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
    private Label labelTotalPrice;

    @FXML
    private JFXButton btnPay;

    @FXML
    private JFXComboBox<ItemConsumo> cbItensConsumo;

    @FXML
    private JFXTextField inputQtdeItensConsumo;
        
    ItemConsumoDao itemConsumoDao = new ItemConsumoDao();
    
    ObservableList<ItemConsumo> obItensConsumo;
    
    ObservableList<ItemConsumoTable> obConsumos;
    
    ConsumoDao consumoDao = new ConsumoDao();
    
    Estadia estadia;
    
    ItemConsumo itemConsumo;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reset();
        loadValidators();
        loadConsumo();
    }

    @FXML
    void save(MouseEvent event) {

    }
    
    private void loadConsumo() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colQtde.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        
        List<Consumo> consumos = consumoDao.getAll();
        
        List<ItemConsumoTable> listConsumos = new ArrayList<>();
        
        consumos.forEach(consumo -> {
            listConsumos.add(consumo.getRowTable());
        });
        
        obConsumos = FXCollections.observableArrayList(listConsumos);
        tableItensConsumo.setItems(obConsumos);
        tableItensConsumo.getColumns().addAll(colNome, colTipo, colQtde, colData, colValor);
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
        resetConsumoForm();
    }
    
    private void resetConsumoForm() {
        itemConsumo = null;
        cbItensConsumo.getSelectionModel().select(itemConsumo);
        inputQtdeItensConsumo.clear();
    }

    @FXML
    void setItemConsumo(ActionEvent event) {
        itemConsumo = cbItensConsumo.getSelectionModel().getSelectedItem();
    }
    
    public void setData(Estadia estadia) {
        this.estadia = estadia;
        TipoAcomodacao tipoAcomodacao = estadia.getAcomodacao().getTipoAcomodacao();
        Reserva reserva = estadia.getReserva();
        labelCustomerName.setText(estadia.getHospede().getNome());
        labelStartDate.setText(DateUtils.format(estadia.getDataHoraInicio()));
        labelEndDate.setText(DateUtils.format(estadia.getDataHoraTermino()));
        labelRoomType.setText(tipoAcomodacao.toString());
        labelQtdeAdultos.setText(reserva.getQtdeAdulto().toString());
        labelQtdeCriancas.setText(reserva.getQtdeCrianca().toString());
        
        Integer dias = DateUtils.diffInDays(estadia.getDataHoraInicio(), estadia.getDataHoraTermino());
        Double valorEstadia =  dias * estadia.getReserva().getValorDiaria();
        
        labelTotalPrice.setText(NumberUtils.formatCurrency(valorEstadia));
    }
    
    private void reset() {
        loadItensConsumo();
    }
    
    @FXML
    void back(MouseEvent event) {
        reset();
        HomeController.getController().showOverview();
    }

}
