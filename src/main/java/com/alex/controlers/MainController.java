package com.alex.controlers;

import com.alex.domain.Company;
import com.alex.service.CompanyService;
import com.alex.service.MailSendService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;

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

    @FXML
    private TableView<Company> sentCv;

    @FXML
    private TableColumn<Company, String> company;

    @FXML
    private TextField fromField;

    @FXML
    private TextField toField;

    @FXML
    private TextField subjectField;

    @FXML
    private TextArea messageField;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EditCompanyController editCompanyController;

    @Autowired
    private MailSendService mailSendService;

    private ObservableList<Company> observableList = FXCollections.observableArrayList();

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
        observableList.addAll(companyService.getAll());
        tableCompanies.setItems(observableList);

        company.setCellValueFactory(new PropertyValueFactory<Company, String>("name"));
        sentCv.setItems(observableList);
    }

    @FXML
    public void saveCompany() {
        if(companyName.getText().equals("") && companyEmail.getText().equals("") && companyPhone.getText().equals(""))
            return;

        Company company = new Company();
        company.setName(companyName.getText());
        company.setEmail(companyEmail.getText());
        company.setPhone(companyPhone.getText());
        companyService.addCompany(company);
        observableList.add(company);

        companyName.setText("");
        companyEmail.setText("");
        companyPhone.setText("");
    }

    @FXML
    public void removeSelected(){
        Company company = tableCompanies.getSelectionModel().getSelectedItem();
        tableCompanies.getSelectionModel().clearSelection();
        if(company == null)
            return;
        companyService.delete(company.getId());
        observableList.remove(company);
    }

    @FXML
    public void editCompany() throws IOException {
        Company company = tableCompanies.getSelectionModel().getSelectedItem();
        if(company == null)
            return;
        editCompanyController.openModal(company);
    }

    public ObservableList getObserableList(){
        return observableList;
    }

    @FXML
    public void sendMessage(){
        Company selected = sentCv.getSelectionModel().selectedItemProperty().getValue();
        String from = fromField.getText();
        String to = selected.getEmail();
        String subject = subjectField.getText();
        String textMessage = messageField.getText();
        mailSendService.sendEmail(from, to, subject, textMessage);
    }
}

