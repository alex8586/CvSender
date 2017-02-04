package com.alex.controlers;

import com.alex.domain.Company;
import com.alex.domain.SendingEmailHistory;
import com.alex.repositories.ObservableData;
import com.alex.service.CompanyService;
import com.alex.service.MailSendService;
import com.alex.service.SendingEmailHistoryService;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MainController {

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
    private TableColumn<Company, Long> sendCvId;

    @FXML
    private TableColumn<Company, String> company;

    @FXML
    private TableColumn<Company, Date> lastSent;

    @FXML
    private TableColumn<Company, Integer> times;

    @FXML
    private TextField fromField;

    @FXML
    private TextField subjectField;

    @FXML
    private TextArea messageField;

    @FXML
    private Button browse;

    @FXML
    private TextField filePath;

    @FXML
    private Label successfully;

    @FXML
    private Tab companyTab;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EditCompanyTabController editCompanyTabController;

    @Autowired
    private MailSendService mailSendService;

    @Autowired
    private ObservableData observableData;

    @Autowired
    private SendingEmailHistoryService historyService;

    @Autowired
    private HistoryTabController historyTabController;

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        id.setCellValueFactory(new PropertyValueFactory<Company, Long>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Company, String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<Company, String>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<Company, String>("phone"));
        observableData.addAll(companyService.getAll());
        tableCompanies.setItems(observableData.getAll());

        sendCvId.setCellValueFactory(new PropertyValueFactory<Company, Long>("id"));
        company.setCellValueFactory(new PropertyValueFactory<Company, String>("name"));
        lastSent.setCellValueFactory(new PropertyValueFactory<Company, Date>("lastTimeSent"));
        times.setCellValueFactory(new PropertyValueFactory<Company, Integer>("timesSent"));

        sentCv.setItems(observableData.getAll());

        sentCv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        handleEventForHistoryModalWindow();
        handleEventForClearingSuccessfullyLabel();

        successfully.setText("");
    }

    public void handleEventForHistoryModalWindow(){
        sentCv.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    try {
                        Company company = sentCv.getSelectionModel().getSelectedItem();
                        historyTabController.openModal(company);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void handleEventForClearingSuccessfullyLabel(){
        companyTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                successfully.setStyle("");
                successfully.setText("");
            }
        });
    }

    @FXML
    public void saveCompany() {
        if (companyName.getText().equals("") && companyEmail.getText().equals("") && companyPhone.getText().equals(""))
            return;

        Company company = new Company();
        company.setName(companyName.getText());
        company.setEmail(companyEmail.getText());
        company.setPhone(companyPhone.getText());
        company.setTimesSent(0);
        companyService.create(company);
        observableData.add(company);

        companyName.setText("");
        companyEmail.setText("");
        companyPhone.setText("");
    }

    @FXML
    public void removeSelected() {
        Company company = tableCompanies.getSelectionModel().getSelectedItem();
        tableCompanies.getSelectionModel().clearSelection();
        if (company == null)
            return;
        companyService.delete(company.getId());
        observableData.remove(company);
    }

    @FXML
    public void editCompany() throws IOException {
        Company company = tableCompanies.getSelectionModel().getSelectedItem();
        if (company == null)
            return;
        editCompanyTabController.openModal(company);
    }

    @FXML
    public void sendMessage() {
        successfully.setStyle("");
        ObservableList<Company> companyList = sentCv.getSelectionModel().getSelectedItems();
        if(companyList.isEmpty()){
            successfully.setStyle("-fx-text-fill: red");
            successfully.setText("Company is not selected");
            return;
        }
        for (Company selected : companyList) {
            String from = fromField.getText();
            String to = selected.getEmail();
            String subject = subjectField.getText();
            String textMessage = messageField.getText();
            String attached = filePath.getText();

            try {
                mailSendService.sendEmail(from, to, subject, textMessage, attached);
            } catch (MessagingException e) {
                successfully.setStyle("-fx-text-fill: red");
                successfully.setText("Error during sending email");
                setSendEmailFieldsEmpty();
                return;
            }

            SendingEmailHistory history = new SendingEmailHistory();
            history.setCompanyId(selected.getId());
            history.setSentDate(new Date());
            historyService.create(history);

            selected.setLastTimeSent(new Date());
            selected.setTimesSent(selected.getTimesSent() + 1);
            companyService.editCompany(selected);
        }
            setSendEmailFieldsEmpty();

        observableData.clear();
        observableData.addAll(companyService.getAll());
        successfully.setText("Mail was sent");
    }

    public void attachFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("choose file");
        File file = fileChooser.showOpenDialog(browse.getScene().getWindow());
        if (file != null) {
            String filePath = file.getPath();
            this.filePath.setText(filePath);
        }
    }

    public void setSendEmailFieldsEmpty(){
        fromField.setText("");
        subjectField.setText("");
        messageField.setText("");
        filePath.setText("");
    }
}

