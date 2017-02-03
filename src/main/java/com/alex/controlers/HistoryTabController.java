package com.alex.controlers;

import com.alex.ConfigurationControllers;
import com.alex.domain.Company;
import com.alex.domain.SendingEmailHistory;
import com.alex.service.SendingEmailHistoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import java.util.Date;

public class HistoryTabController {

    @FXML
    private Label companyName;

    @FXML
    private Label companyPhone;

    @FXML
    private Label companyEmail;

    @FXML
    private TableView<SendingEmailHistory> historyTable;

    @FXML
    private TableColumn<SendingEmailHistory, Long> id;

    @FXML
    private TableColumn<SendingEmailHistory, Date> sendingEmail;

    @FXML
    private AnchorPane historyAnchorPane;

    @Autowired
    @Qualifier("history")
    private ConfigurationControllers.View view;

    @Autowired
    private SendingEmailHistoryService historyService;

    private ObservableList<SendingEmailHistory> observableList = FXCollections.observableArrayList();

    @FXML
    public void openModal(Company company) throws IOException {
        observableList.clear();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditCompanyTabController.class.getResource("/fxml/history.fxml"));
        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        stage.setTitle("History of sent emails");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.centerOnScreen();
        if (historyAnchorPane.getScene() == null) {
            Scene scene = new Scene(historyAnchorPane);
            stage.setScene(scene);
        } else {
            stage.setScene(historyAnchorPane.getScene());
        }
        companyName.setText(company.getName());
        companyPhone.setText(company.getPhone());
        companyEmail.setText(company.getEmail());
        id.setCellValueFactory(new PropertyValueFactory<SendingEmailHistory, Long>("id"));
        sendingEmail.setCellValueFactory(new PropertyValueFactory<SendingEmailHistory, Date>("sentDate"));

        observableList.addAll(historyService.getByCompanyId(company.getId()));
        historyTable.setItems(observableList);

        stage.show();
    }

}
