package com.alex.controlers;

import com.alex.domain.Company;
import com.alex.service.CompanyService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class MainController{

    @FXML
    private TextField companyName;

    @FXML
    private TextField companyEmail;

    @FXML
    private TextField companyPhone;

    @FXML
    private TableColumn<Company, Long> id;

    @FXML
    private TableColumn<Company, String> name;

    @FXML
    private TableColumn<Company, String> email;

    @FXML
    private TableColumn<Company, String> phone;

    @FXML
    private TableView<Company> tableCompanies;

    @Autowired
    private CompanyService companyService;

    private ObservableList<Company> list = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init(){
        id.setCellValueFactory(new PropertyValueFactory<Company, Long>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Company, String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<Company, String>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<Company, String>("phone"));

        list.addAll(companyService.getAll());
        tableCompanies.setItems(list);
    }

    @FXML
    public void saveCompany() {
        Company company = new Company();
        company.setName(companyName.getText());
        company.setEmail(companyEmail.getText());
        company.setPhone(companyPhone.getText());

        companyService.addCompany(company);
        list.add(company);

        companyName.setText("");
        companyEmail.setText("");
        companyPhone.setText("");
    }
}

