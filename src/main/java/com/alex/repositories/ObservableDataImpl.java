package com.alex.repositories;

import com.alex.domain.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObservableDataImpl implements ObservableData{

    private ObservableList<Company> observableList = FXCollections.observableArrayList();

    public void add(Company company){
        observableList.add(company);
    }

    public void remove(Company company){
        observableList.remove(company);
    }

    public void addAll(List<Company> companies){
        observableList.addAll(companies);
    }

    public ObservableList<Company> getAll(){
        return observableList;
    }
}
