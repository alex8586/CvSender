package com.alex.repositories;

import com.alex.domain.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObservableDataImpl implements ObservableData{

    private ObservableList<Company> observableList = FXCollections.observableArrayList();

    @Override
    public void add(Company company){
        observableList.add(company);
    }

    @Override
    public void remove(Company company){
        observableList.remove(company);
    }

    @Override
    public void addAll(List<Company> companies){
        observableList.addAll(companies);
    }

    @Override
    public ObservableList<Company> getAll(){
        return observableList;
    }

    @Override
    public void clear() {
        observableList.clear();
    }
}
