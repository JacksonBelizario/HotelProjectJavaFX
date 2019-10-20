/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp;

import br.uems.hotelapp.persistence.dao.FuncionarioDao;
import br.uems.hotelapp.persistence.entities.Funcionario;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jackson
 */
public class UsersController implements Initializable {
    
    @FXML
    private VBox pnListUsers;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        fillEmployeeTable();

    }
    
    
    public void fillEmployeeTable() {
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        
        List<Funcionario> funcionarios = funcionarioDao.getAll();

        Iterator<Funcionario> funcionariosIterator = funcionarios.iterator();
        while (funcionariosIterator.hasNext()){
            Funcionario funcionario = (Funcionario) funcionariosIterator.next();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UserItem.fxml"));
                pnListUsers.getChildren().add(loader.load());

                UserItemController controller = loader.<UserItemController>getController();
                controller.setUser(funcionario);

            } catch (Exception e) {
            }

        }
    }
}
