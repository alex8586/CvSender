package com.alex;

import com.alex.controlers.EditCompanyTabController;
import com.alex.controlers.HistoryTabController;
import com.alex.controlers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ConfigurationControllers {

    @Bean(name = "mainView")
    public View getMainView() throws IOException {
        return loadView("fxml/main.fxml");
    }

    @Bean(name = "editCompany")
    public View getEditCompany() throws IOException {
        return loadView("fxml/editCompany.fxml");
    }

    @Bean(name = "history")
    public View getHistory() throws IOException {
        return loadView("fxml/history.fxml");
    }

    @Bean
    public MainController getMainController() throws IOException {
        return (MainController) getMainView().getController();
    }

    @Bean
    public EditCompanyTabController getEditCompanyController() throws IOException {
        return (EditCompanyTabController) getEditCompany().getController();
    }

    @Bean
    public HistoryTabController getHistoryTabController() throws IOException {
        return (HistoryTabController) getHistory().getController();
    }

    protected View loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return new View(loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

    public class View {
        private Parent view;
        private Object controller;

        public View(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
    }

}
