/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp;

import br.uems.hotelapp.persistence.dao.FuncionarioDao;
import br.uems.hotelapp.persistence.entities.Funcionario;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jackson
 */
public class UsersController implements Initializable {
    
    NumberFormat numberFormat = NumberFormat.getNumberInstance();
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    
    @FXML
    final private VBox pnListUsers = null;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        fillEmployeeTable();

    }
    
    public void dummyData() {
        try {
        
            final Node[] nodes = new Node[3];
            for (int i = 0; i < nodes.length; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Item.fxml"));
                nodes[i] = loader.load();
//                nodes[i] = FXMLLoader.load(getClass().getResource("/fxml/Item.fxml"));

                pnListUsers.getChildren().add(nodes[i]);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void fillEmployeeTable() {
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        
            List<Funcionario> funcionarios = funcionarioDao.getAll();

            Iterator<Funcionario> funcionariosIterator = funcionarios.iterator();
            while (funcionariosIterator.hasNext()){
                Funcionario funcionario = (Funcionario) funcionariosIterator.next();
                System.out.println(funcionario.getNome());
    //            System.out.println(getFuncionarioRow(funcionario));
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Item.fxml"));
                    pnListUsers.getChildren().add(loader.load());

                } catch (Exception e) {
                    System.out.println(e.getMessage());
//                    e.printStackTrace();
                }
        
            }
    }
    
    private Object[] getFuncionarioRow(Funcionario funcionario) {
        return new Object[] {
            funcionario.getId(),
            funcionario.getNome(),
            funcionario.getEndereco(),
            funcionario.getCidade(),
            funcionario.getEstado(),
            funcionario.getTelefone(),
            formatDate(funcionario.getDataNascimento()),
            "R$ " + formatNumber(funcionario.getSalario())
        };
    }
    
    private String formatDate(Date date) {
        String dateStr =  "";
        try {
            dateStr = df.format(date);
        } catch(Exception ex) {
            //
        }
        return dateStr;
    }
    
    private String formatNumber(Double value) {
        String valueStr =  "0";
        try {
            valueStr = numberFormat.format(value);
        } catch(Exception ex) {
            //
        }
        return valueStr;
    }
}
