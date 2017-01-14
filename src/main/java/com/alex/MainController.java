package com.alex;

import com.alex.domain.Company;
import com.alex.service.CompanyService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @FXML
    private TextField companyName;

    @FXML
    private TextField companyMail;

    @FXML
    private TextField companyPhone;

    @Autowired
    private CompanyService companyService;

    @FXML
    public void saveCompany() {
        Company company = new Company();
        company.setName(companyName.getText());
        company.setMail(companyMail.getText());
        company.setPhone(companyPhone.getText());

        companyService.addCompany(company);

        companyName.setText("");
        companyMail.setText("");
        companyPhone.setText("");
    }
}

