package com.alex.controlers;

import com.alex.ConfigurationControllers;
import com.alex.domain.User;
import com.alex.service.EmailUserService;
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
import java.util.List;

public class EditEmailTabController {

    @FXML private TextField email;

    @FXML private TextField password;

    @FXML private Button btnOk;

    @FXML private Button btnCancel;

    @FXML private AnchorPane editEmail;

    @Autowired
    @Qualifier("editEmail")
    private ConfigurationControllers.View view;

    @Autowired
    private EmailUserService emailUserService;

    @Autowired
    private MainController mainController;

    @FXML
    void openModal() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditCompanyTabController.class.getResource("/fxml/editEmail.fxml"));
        AnchorPane anchorPane = (AnchorPane) loader.load();

        Stage stage = new Stage();
        stage.setTitle("Add email");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.centerOnScreen();
        if (editEmail.getScene() == null) {
            Scene scene = new Scene(editEmail);
            stage.setScene(scene);
        } else {
            stage.setScene(editEmail.getScene());
        }

        stage.show();
    }

    @FXML
    public void editEmail(ActionEvent event){
        String fromEmail = email.getText();
        String fromPassword = password.getText();
        if(fromEmail.equals("") || fromPassword.equals(""))
            return;

        List<User> userList = emailUserService.getAll();
        if(userList.isEmpty()) {
            User newUser = new User();
            newUser.setEmail(fromEmail);
            newUser.setPassword(fromPassword);
            User created = emailUserService.create(newUser);
            setLabelsWorkAsAndEmail(created);
        }else {
            User current = userList.get(0);
            current.setEmail(fromEmail);
            current.setPassword(fromPassword);
            emailUserService.update(current);
            setLabelsWorkAsAndEmail(current);
        }

        email.setText("");
        password.setText("");
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancelEdit(ActionEvent event){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private void setLabelsWorkAsAndEmail(User user){
        Label workAs = mainController.getWorkAs();
        workAs.setStyle("");
        workAs.setText("You work as ");
        Label emailLabel = mainController.getCurrentEmail();
        emailLabel.setText(user.getEmail());
    }
}
