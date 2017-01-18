package com.alex.controlers;

import com.alex.ConfigurationControllers;
import com.alex.domain.Company;
import com.alex.service.CompanyService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

public class EditCompanyController {

    @FXML
    private TextField companyName;

    @FXML
    private TextField companyEmail;

    @FXML
    private TextField companyPhone;

    @FXML
    private Label idLabel;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    @FXML
    private AnchorPane editTable;


    @Autowired
    @Qualifier("editCompany")
    private ConfigurationControllers.View view;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private MainController mainController;

    @FXML
    public void openModal(Company company) throws IOException {
        System.out.println("initialize ");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditCompanyController.class.getResource("/fxml/editCompany.fxml"));
        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        stage.setTitle("Edit company");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.centerOnScreen();
        if (editTable.getScene() == null) {
            Scene scene = new Scene(editTable);
            stage.setScene(scene);
        } else {
            stage.setScene(editTable.getScene());
        }

        companyName.setText(company.getName());
        companyEmail.setText(company.getEmail());
        companyPhone.setText(company.getPhone());
        System.out.println("company id" + company.getId());
        idLabel.setText(String.valueOf(company.getId()));

        System.out.println("in modal " + company.toString());
        stage.show();
    }

    @FXML
    public void editCompany(ActionEvent event){
        Company company = new Company();
        company.setId(Long.parseLong(idLabel.getText()));
        company.setName(companyName.getText());
        company.setEmail(companyEmail.getText());
        company.setPhone(companyPhone.getText());

        companyService.editCompany(company);
        ObservableList<Company> list = mainController.getObserableList();
        list.clear();
        list.addAll(companyService.getAll());

        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancelEdit(ActionEvent event){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}