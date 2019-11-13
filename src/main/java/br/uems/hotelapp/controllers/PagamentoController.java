/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;


import br.uems.hotelapp.enums.FormaPagamento;
import br.uems.hotelapp.enums.TipoDocumento;
import br.uems.hotelapp.persistence.dao.PagamentoDao;
import br.uems.hotelapp.persistence.entities.Estadia;
import br.uems.hotelapp.persistence.entities.Hospede;
import br.uems.hotelapp.persistence.entities.Pagamento;
import br.uems.hotelapp.utils.DateUtils;
import br.uems.hotelapp.utils.MasksUtils;
import br.uems.hotelapp.utils.NumberUtils;
import br.uems.hotelapp.utils.ValidatorUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PagamentoController implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private Label labelPrecoEstadia, labelPrecoConsumo, labelPrecoTotal;

    @FXML
    private JFXComboBox<FormaPagamento> cbFormaPagamento;

    @FXML
    private JFXDatePicker inputDataVenc;

    @FXML
    private JFXTextField inputDesconto, inputMulta;

    @FXML
    private JFXButton btnCancel, btnSave, btnPay;
    
    private FormaPagamento formaPagamento;
    
    private Hospede hospede;
    
    private Double valorConsumo, valorEstadia, valorTotal;
    
    Estadia estadia;
    
    PagamentoDao pagamentoDao = new PagamentoDao();
    Pagamento pagamento;
    
    LocalDate dataVencimento = LocalDate.now();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MasksUtils.maxField(inputMulta, 2);
        MasksUtils.maxField(inputDesconto, 2);
        MasksUtils.onlyIntegerValue(inputMulta);
        MasksUtils.onlyIntegerValue(inputDesconto);
        ValidatorUtils.setValidator(cbFormaPagamento, "Informe a forma de pagamento");
        ValidatorUtils.setValidator(inputDataVenc, "Informe a data de Vencimento");
        
        btnSave.setDisable(true);
        cbFormaPagamento.getItems().setAll(Arrays.asList(FormaPagamento.values()));
        inputDataVenc.setValue(dataVencimento);
    }

    @FXML
    void setFormaPagamento(ActionEvent event) {
        formaPagamento = cbFormaPagamento.getSelectionModel().getSelectedItem();
        if (formaPagamento == FormaPagamento.DINHEIRO) {
            dataVencimento = LocalDate.now();
            inputDataVenc.setValue(dataVencimento);
            inputDataVenc.setDisable(true);
        } else {
            inputDataVenc.setDisable(false);
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void save(ActionEvent event) {
        gerarPagamento(false);
    }

    @FXML
    void pay(ActionEvent event) {
        gerarPagamento(true);
    }
    
    void gerarPagamento(Boolean quitar) {
        if (formaPagamento == null || inputDataVenc.getValue() == null) {
            cbFormaPagamento.validate();
            inputDataVenc.validate();
            return;
        }
        pagamento = pagamentoDao.findByEstadia(estadia.getId());
        
        Boolean edit = pagamento != null;
        
        if (!edit) {
            pagamento = new Pagamento();
        }
        if (quitar) {
            pagamento.setStatus(Pagamento.STATUS_PAGO);
        } else {
            pagamento.setStatus(Pagamento.STATUS_ABERTO);
        }
        pagamento.setDataHora(new Date());
        pagamento.setDataVencimento(DateUtils.toDate(dataVencimento));
        pagamento.setEstadia(estadia);
        pagamento.setValorTotalDiaria(valorEstadia);
        pagamento.setValorTotalConsumo(valorConsumo);
        pagamento.setValor(valorTotal);
        pagamento.setDesconto(NumberUtils.parseInt(inputDesconto.getText()));
        pagamento.setMulta(NumberUtils.parseInt(inputMulta.getText()));
        pagamento.setFormaPagamento(formaPagamento.ordinal());
        
        if (!edit) {
            pagamentoDao.save(pagamento);
        } else {
            pagamentoDao.update(pagamento);
        }
        closeWindow();
    }
    
    private void closeWindow() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void onDate(ActionEvent event) {
        dataVencimento = inputDataVenc.getValue();
        if (dataVencimento == null) {
            dataVencimento = LocalDate.now();
            inputDataVenc.setValue(dataVencimento);
        }
        if (DateUtils.isToday(dataVencimento)) {
            btnSave.setDisable(true);
            btnPay.setDisable(false);
        } else {
            btnSave.setDisable(false);
            btnPay.setDisable(true);
        }
    }
    
    @FXML
    void onChange(KeyEvent event) {
        atualizarTotal();
    }

    public void setData(Estadia estadia, Double valorEstadia, Double valorConsumo) {
        this.estadia = estadia;
        this.valorEstadia = valorEstadia;
        this.valorConsumo = valorConsumo;
        
        labelPrecoConsumo.setText(NumberUtils.formatCurrency(valorConsumo));
        labelPrecoEstadia.setText(NumberUtils.formatCurrency(valorEstadia));
        DateUtils.setDatePickerLimit(inputDataVenc, LocalDate.now(), null);
        pagamento = pagamentoDao.findByEstadia(estadia.getId());
        System.out.println("Estadia pgto: " + pagamento);
        if (pagamento != null) {
            if (pagamento.getDesconto() != null) {
                inputDesconto.setText(pagamento.getDesconto().toString());
            }
            if (pagamento.getMulta() != null) {
                inputMulta.setText(pagamento.getMulta().toString());
            }
            if (pagamento.getDataVencimento() != null) {
                dataVencimento = DateUtils.toLocalDate(pagamento.getDataVencimento());
                inputDataVenc.setValue(dataVencimento);
            }
            formaPagamento = FormaPagamento.values()[pagamento.getFormaPagamento()];
            cbFormaPagamento.getSelectionModel().select(formaPagamento);
            cbFormaPagamento.setDisable(true);
            inputDataVenc.setDisable(true);
            inputDesconto.setDisable(true);
            inputMulta.setDisable(true);
            
            if (pagamento.getStatus() == Pagamento.STATUS_PAGO) {
                btnPay.setDisable(true);
            } else if(DateUtils.isBeforeDay(pagamento.getDataVencimento(), Calendar.getInstance().getTime())) {
                btnPay.setDisable(true);
            }
        }
        atualizarTotal();
    }
    
    private void atualizarTotal() {
        Double valorOrig = valorConsumo + valorEstadia;
        Double desconto = NumberUtils.parseDouble(inputDesconto.getText());
        Double multa = NumberUtils.parseDouble(inputMulta.getText());
    
        desconto = (desconto / 100.0) * valorOrig;
        multa = (multa / 100.0) * valorOrig;
        
        valorTotal = valorOrig - desconto + multa;
        labelPrecoTotal.setText(NumberUtils.formatCurrency(valorTotal));
    }

}
